package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class WaitingForLobby extends Message {
    public WaitingForLobby(String senderUsername) {
        super(messEnum.WAITING_FOR_LOBBY, senderUsername);
    }

    public WaitingForLobby(String senderUsername, String optDescription) {
        super(messEnum.WAITING_FOR_LOBBY, senderUsername, optDescription);
    }
}