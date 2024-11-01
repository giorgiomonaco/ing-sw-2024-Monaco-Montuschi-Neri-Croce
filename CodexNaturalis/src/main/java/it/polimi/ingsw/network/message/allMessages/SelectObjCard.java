package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.SelectObjectiveHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class SelectObjCard extends Message {
    public SelectObjCard(String senderUsername) {
        super(messEnum.SELECT_OBJECTIVE, senderUsername);
    }



    @Override
    public MessageHandler genHandler() {
        return new SelectObjectiveHandler();
    }

}
