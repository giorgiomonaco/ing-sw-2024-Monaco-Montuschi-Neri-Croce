package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.allMessages.SelectionNumPlayers;
import it.polimi.ingsw.network.message.messEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Scanner;

public class NumOfPlayersPanel extends JPanel {
    Client client;
    GridBagConstraints gbc;

    public NumOfPlayersPanel(Client client){
        this.client = client;

        /*
        Create the text for the message
        Create the field for answer
        Create the button to submit
         */
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //create the label
        JLabel txt = new JLabel("Insert num of players:");

        //create the response field
        JTextField txtField = new JTextField(10);

        //Create the button
        JButton button = new JButton("Submit");

        add(txt);
        add(txtField);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getting the text from text field
                String txt = txtField.getText();

                //now we use scanner as usual
                Scanner scan = new Scanner(txt);
                scan.close();

                int sel = Integer.parseInt(txt);

                try {
                    //send message with the name of the player
                    client.sendMessage(new SelectionNumPlayers(client.getUsername(), sel));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


    }
}