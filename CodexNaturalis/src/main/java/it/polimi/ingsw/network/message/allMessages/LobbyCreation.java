package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

import java.util.List;

public class LobbyCreation extends Message {

    private List<String> playersInLobby;

    public LobbyCreation(String senderUsername) {
        super(messEnum.LOBBY, senderUsername);
    }

    public LobbyCreation(String senderUsername, String optDescription) {
        super(messEnum.LOBBY, senderUsername, optDescription);
    }

    public LobbyCreation(String senderUsername, String optDescription, List<String> players){
        super(messEnum.LOBBY, senderUsername, optDescription);
        playersInLobby = players;
    }

    public List<String> getPlayersInLobby() {
        return playersInLobby;
    }
}
