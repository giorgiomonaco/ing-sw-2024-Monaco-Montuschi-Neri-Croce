package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel {

    /*
    The chat panel is composed by:
    text input
    button to submit input
    panel to display the chat
    and a bar to scroll
     */
    private GridBagConstraints gbc;
    private GridBagConstraints gbcChat;
    private int mexNum;
    private JPanel writingPanel;
    private JTextField textField;
    private JButton sendButton;
    private JPanel chatPanel;
    private JScrollPane scrollPane;
    private Client client;

    public ChatPanel(Client client) {
        this.client = client;
        initComponents();
    }

    private void initComponents() {
        // Imposta il layout
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // Pannello della chat
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.9;
        add(scrollPane, gbc);

        // Pannello di scrittura
        writingPanel = new JPanel();
        writingPanel.setLayout(new BorderLayout());

        textField = new JTextField();
        sendButton = new JButton("Send");

        writingPanel.add(textField, BorderLayout.CENTER);
        writingPanel.add(sendButton, BorderLayout.EAST);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        add(writingPanel, gbc);

        // Aggiungi il listener al pulsante di invio
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Aggiungi il listener per premere Enter nel textField
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = textField.getText().trim();
        if (!message.isEmpty()) {
            addMessage("Me: " + message);
            textField.setText("");

            // Qui puoi aggiungere il codice per inviare il messaggio al server
            // client.sendMessageToServer(message);
        }
    }

    public void addMessage(String message) {
        JLabel messageLabel = new JLabel(message);
        chatPanel.add(messageLabel);
        chatPanel.revalidate();
        chatPanel.repaint();

        // Scrolla verso il basso
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
}
