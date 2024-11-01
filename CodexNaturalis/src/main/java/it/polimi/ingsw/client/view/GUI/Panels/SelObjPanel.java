package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.SelectionObjCard;
import it.polimi.ingsw.server.model.ObjectiveCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelObjPanel extends JPanel {

    private final List<BufferedImage> cardImages = new ArrayList<>();

    private Image backgroundImage;

    public SelObjPanel(Client client){
        setLayout(new BorderLayout());

        // We use html to write the text on different levels
        JLabel title = new JLabel("<html><div style='text-align: center;'>SELECT YOUR<br>OBJECTIVE CARD<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 54));
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

        add(title, BorderLayout.NORTH);

        // We create a panel for the cards
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        ClassLoader cl = this.getClass().getClassLoader();
        String pathFirst = client.getListObjective().getFirst().getImage();
        String pathSecond = client.getListObjective().getLast().getImage();
        InputStream isf = cl.getResourceAsStream(pathFirst);
        InputStream iss = cl.getResourceAsStream(pathSecond);

        try {
            cardImages.add(ImageIO.read(Objects.requireNonNull(isf, "Couldn't read the image.")));
            cardImages.add(ImageIO.read(Objects.requireNonNull(iss, "Couldn't read the image.")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 1;

        // Add cards to the panel
        for (BufferedImage image : cardImages) {

            BufferedImage imgOut = new BufferedImage(300, 200, image.getType());
            Graphics2D g2d = imgOut.createGraphics();
            g2d.drawImage(image, 0, 0, 300, 200, null);
            g2d.dispose();

            JLabel objLabel = new JLabel(new ImageIcon(imgOut));
            objLabel.setHorizontalAlignment(SwingConstants.CENTER);
            objLabel.addMouseListener(new SelObjListener(i, client));
            objLabel.setIcon(new ImageIcon(imgOut));
            cardPanel.add(objLabel, gbc);
            i++;
            gbc.gridx++;

        }

        add(cardPanel, BorderLayout.CENTER);

        try {
            InputStream is = cl.getResourceAsStream("images/backGround3.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private static class SelObjListener extends MouseAdapter {
        private final int sel;
        private final Client client;

        public SelObjListener(int sel, Client client) {
            this.sel = sel;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(client.getCurrentState().equals(stateEnum.SELECT_OBJECTIVE)) {
                try {
                    ObjectiveCard card = client.getListObjective().get(sel-1);
                    client.setObjective(card);
                    client.sendMessage(new SelectionObjCard(client.getUsername(), sel));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }

}