package it.polimi.ingsw.client.view.GUI.Panels;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionCard;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GoldCard;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

public class MainPanel extends JPanel {

    private Client client;
    private GridBagConstraints gbc;
    private ChatPanel chat;
    private PersonalBoardPanel board;
    private HandPanel hand;
    private ObjectivePanel objectivePanel;
    private TurnOfPanel other;
    private int xCoord;
    private int yCoord;
    private boolean side;
    private Card card;
    private int turn;
    private boolean yourTurn;
    private int index;
    private Image backgroundImage;

    public MainPanel(Client client, int turn){

        this.client = client;
        this.turn = turn;
        this.card = null;
        xCoord = -1;
        yCoord = -1;
        side = true;
        //setting right layout to manage the panel
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("images/backGround3.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        createElements();
    }

    public void createElements(){
        // Create all the panels in the main panel
        chat = new ChatPanel(client);
        board = new PersonalBoardPanel(client, this);
        objectivePanel = new ObjectivePanel(client);
        other = new TurnOfPanel(client);
        hand = new HandPanel(client, this);


        gbc.insets = new Insets(5, 5, 5, 5); // Add some space around components

        // Set proportions for each panel
        // Board panel will cover 70% of y and 80% of x space
        // BOARD-----
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 150;
        gbc.gridheight = 170;
        gbc.weightx = 0.75;
        gbc.weighty = 0.85;
        gbc.fill = GridBagConstraints.BOTH;
        add(board, gbc);


        // CARDS---
        gbc.gridx = 0;
        gbc.gridy = 170;
        gbc.gridwidth = 120;
        gbc.gridheight = 30;
        gbc.weightx = 0.6;
        gbc.weighty = 0.15;
        gbc.fill = GridBagConstraints.BOTH;
        add(hand, gbc);


        // PLACE BUTTON----
        gbc.gridx = 120;
        gbc.gridy = 170;
        gbc.gridwidth = 30;
        gbc.gridheight = 30;
        gbc.weightx = 0.15;
        gbc.weighty = 0.15;
        gbc.fill = GridBagConstraints.BOTH;
        JButton button = new JButton("PLACE");
        button.addMouseListener(new buttonListener(this, client));
        button.setPreferredSize(new Dimension(100, 100));
        add(button, gbc);


        // OTHERS---
        gbc.gridx = 150;
        gbc.gridy = 0;
        gbc.gridwidth = 50;
        gbc.gridheight = 45;
        gbc.weightx = 0.25;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        add(other, gbc);


        //OBJECTIVES---
        gbc.gridx = 150;
        gbc.gridy = 45;
        gbc.gridwidth = 50;
        gbc.gridheight = 90;
        gbc.weightx = 0.25;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(objectivePanel, gbc);



        // CHAT---
        gbc.gridx = 150;
        gbc.gridy = 135;
        gbc.gridwidth = 50;
        gbc.gridheight = 65;
        gbc.weightx = 0.25;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        add(chat, gbc);



        board.setOpaque(false);
        hand.setOpaque(false);
        objectivePanel.setOpaque(false);
        chat.setOpaque(false);
        other.setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    public void setIndex(int index) {
        this.index = index;
        for (JLabel l : other.getPlayersLab()) {
            if(other.getPlayersLab().indexOf(l) == index) {
                l.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
            } else {
                l.setBorder(null);
            }
        }
    }


    private static class buttonListener extends MouseAdapter {
        private MainPanel mp;
        private Client client;

        public buttonListener(MainPanel mp, Client client) {
            this.mp = mp;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // for debugging
            // System.out.println(mp.getxCoord() + " " + mp.getyCoord());
            if ((mp.getxCoord() != -1) && (mp.getyCoord() != -1) && (mp.getCard() != null) && availableResources(mp.getCard(), mp.isSide()) && mp.isYourTurn()) {
                try {

                    //send message with the selection of the card
                    System.out.println("invio");
                    client.sendMessage(new SelectionCard(client.getUsername(), mp.getCard(), mp.getxCoord(), mp.getyCoord(), mp.isSide()));
                    System.out.println("fatto");
                    mp.getCard().setTurn(mp.getTurn() * 10);
                    mp.setCard(null);
                    mp.setxCoord(-1);
                    mp.setyCoord(-1);
                    mp.setSide(true);

                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (mp.getxCoord() == -1 || mp.getyCoord() == -1){
                client.getUI().printErrorMessage("WRONG SELECTION! You have to select a place in the board first.");
            } else if (mp.getCard() == null) {
                client.getUI().printErrorMessage("WRONG SELECTION! You have to select a card first.");
            } else if (!availableResources(mp.getCard(), mp.isSide())){
                client.getUI().printErrorMessage("You do not have enough resources to play this card. Please choose another one.");
            } else {
                client.getUI().printErrorMessage("IT'S NOT YOUR TURN. Wait for your turn.");

            }

        }

        private boolean availableResources(Card card, boolean side){
            if(card instanceof GoldCard && side){
                for (int i = 0; i < 4; i++) {
                    if(!(((GoldCard) card).getNeededSymbols()[i] <= client.getResources()[i])){
                        return false;
                    }

                }
            }
            return true;
        }
    }

    public ChatPanel getChat() {
        return chat;
    }

    public PersonalBoardPanel getBoard() {
        return board;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int x) {
        this.xCoord = x;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int y) {
        this.yCoord = y;
    }

    public boolean isSide() {
        return side;
    }

    public void setSide(boolean side) {
        this.side = side;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getTurn() {
        return turn;
    }

    public void updatePanel(){
        remove(board);
        remove(hand);

        board = new PersonalBoardPanel(client, this);
        hand = new HandPanel(client, this);

        gbc.insets = new Insets(5, 5, 5, 5); // Add some space around components

        // Set proportions for each panel
        // Board panel will cover 70% of y and 80% of x space
        // BOARD-----
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 150;
        gbc.gridheight = 170;
        gbc.weightx = 0.75;
        gbc.weighty = 0.85;
        gbc.fill = GridBagConstraints.BOTH;
        add(board, gbc);


        // CARDS---
        gbc.gridx = 0;
        gbc.gridy = 170;
        gbc.gridwidth = 120;
        gbc.gridheight = 30;
        gbc.weightx = 0.6;
        gbc.weighty = 0.15;
        gbc.fill = GridBagConstraints.BOTH;
        add(hand, gbc);

        board.setOpaque(false);
        hand.setOpaque(false);
    }
}
