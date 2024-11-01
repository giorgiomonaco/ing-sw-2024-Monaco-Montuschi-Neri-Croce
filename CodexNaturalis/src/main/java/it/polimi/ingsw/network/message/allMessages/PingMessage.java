package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class PingMessage extends Message {
    public PingMessage(String senderUsername) {
        super(messEnum.PING, senderUsername);
    }

    @Override
    public MessageHandler genHandler() {
        return null;
    }
}
