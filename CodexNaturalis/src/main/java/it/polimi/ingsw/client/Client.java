package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.client.states.stateEnum;

import java.io.Serializable;
import java.rmi.RemoteException;

public abstract class Client implements Serializable {
    private stateEnum currentState;
    private String username;
    private UserInterface UI;


    public stateEnum getCurrentState() {
        return currentState;
    }

    public void setCurrentState(stateEnum currentState) {
        this.currentState = currentState;
    }

    public void sendMessage(Message msg) throws RemoteException {}

    public void setUI(UserInterface UI) {
        this.UI = UI;
    }

    public UserInterface getUI() {
        return UI;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void manageMessage(Message msg) {
        if(msg.getType().equals(messEnum.PING)){

        } else {
            updateState(msg);
        }
    }

    public void updateState(Message msg){

        switch(msg.getType()){
            case LOGIN_RESPONSE:
                LoginResponse response = (LoginResponse) msg;
                if(response.getResult() == 1){
                    setCurrentState(stateEnum.LOGIN_SUCCESSFUL);
                } else {
                    setCurrentState(stateEnum.LOGIN_FAILED);
                }
                break;
            case WAITING_FOR_LOBBY:
                setCurrentState(stateEnum.WAITING_LOBBY);
                break;
            case SELECT_NUM_PLAYERS:
                setCurrentState(stateEnum.SELECT_NUM_PLAYERS);
                break;
            case REJECTED:
                setCurrentState(stateEnum.REJECTED);
                break;
            case ALREADY_STARTED:
                setCurrentState(stateEnum.ALREADY_STARTED);
                break;
            case SHOW_CARD:
                ShowCards showMsg = (ShowCards) msg;
                getUI().viewCards(showMsg.getPlayerHand());
                break;

            case DRAW_CARD_RESPONSE:
                    DrawCardResponse drawMsg = (DrawCardResponse) msg;
                    getUI().viewCard(drawMsg.getCard());
                break;

            case SHOW_PLAYER_RESOURCES:
                     ShowPlayerResources showResources = (ShowPlayerResources) msg;
                     getUI().viewResources(showResources.getResources());
                 break;

            case SHOW_PLAYER_BOARD:
                ShowPlayerBoard showBoard = (ShowPlayerBoard) msg;
                getUI().viewBoard(showBoard.getGameBoard());
                break;

        }

        getUI().run();
    }
}
