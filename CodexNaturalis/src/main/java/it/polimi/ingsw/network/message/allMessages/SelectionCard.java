package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;

public class SelectionCard extends Message {
    Card card;
    int x;
    int y;

    public SelectionCard(String senderUsername, Card card, int x, int y) {
        super(messEnum.SELECTION_CARD, senderUsername);
        this.card = card;
        this.x = x;
        this.y = y;
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }

    public Card getCard() {
        return card;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
