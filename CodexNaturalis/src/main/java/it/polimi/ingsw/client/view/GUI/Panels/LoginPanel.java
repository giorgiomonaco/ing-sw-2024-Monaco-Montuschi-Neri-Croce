package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.allMessages.LoginResponse;
import it.polimi.ingsw.network.message.messEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Scanner;

public class LoginPanel extends JPanel {

    private GridBagConstraints gbc;
    //Prepare elements: label, text field and button
    private JLabel label1;
    private JLabel title;
    private JTextField textField;
    private JButton button;


    public LoginPanel(Client client){

        this.gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());

        createElements();

        //Add event listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getting the text from text field
                String inputTxt = textField.getText();

                //now we use scanner as usual
                Scanner scan = new Scanner(inputTxt);
                scan.close();

                try {
                    //send message with the name of the player
                    client.sendMessage(new LoginRequest(messEnum.LOGIN_REQUEST, inputTxt));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }

    private void createElements(){

        //the game title
        // title = new JLabel()
        //first we want to ask to insert the username:
        label1= new JLabel("Insert your Username:");
        //create a j text file to catch user writing
        textField = new JTextField(20);
        //create the button to submit the username
        button = new JButton("Submit");

        //Now we add them to the panel with their constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(label1,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(textField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(button, gbc);

    }
}
