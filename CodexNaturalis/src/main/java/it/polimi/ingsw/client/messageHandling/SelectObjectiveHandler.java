package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;

public class SelectObjectiveHandler implements MessageHandler{
    @Override
    public void handle(Message msg, Client client) {
        client.setCurrentState(stateEnum.SELECT_OBJECTIVE);
        client.getUI().run();
    }
}
