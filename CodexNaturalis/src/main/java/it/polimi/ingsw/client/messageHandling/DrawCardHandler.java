package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.DrawCardRequest;

public class DrawCardHandler implements MessageHandler {

    @Override
    public void handle(Message msg, Client client) {
        DrawCardRequest drawCardRequest = (DrawCardRequest) msg;
        client.setCurrentState(stateEnum.DRAW_CARD);
        client.setVisibleGoldCards(drawCardRequest.getGoldCards());
        client.setVisibleResourceCards(drawCardRequest.getResourceCards());
        client.getUI().run();

    }
}