package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Panels.LoginPanel;
import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.InitialCard;

import java.awt.*;
import java.util.List;

public class Gui implements UserInterface {

    //layout and constraints
    private GridBagLayout gbl;
    private GridBagConstraints gbc;

    private final Client client;

    private MyFrame frame;

    public Gui (Client client){
        this.client = client;
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

    }

    @Override
    public void run() {
        switch (client.getCurrentState()){
            case LOGIN:
                createFrame();
                addLoginPanel();
                break;
            default:
                break;
        }

    }
    @Override
    public void printChat(){

    }

    @Override
    public void endGame() {

    }

    @Override
    public void printErrorMessage(Message msg) {

    }

    @Override
    public void printMessage(Message msg) {

    }


    private void createFrame(){
        frame = new MyFrame(gbl);
    }

    private void addLoginPanel(){
        LoginPanel logPanel = new LoginPanel(gbl, client);
        //now we add it to the frame
        frame.add(logPanel);
        //we make the panel visible to be displaced
        frame.setVisible(true);
    }

}
