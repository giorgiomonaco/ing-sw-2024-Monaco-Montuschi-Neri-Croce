package it.polimi.ingsw.client.commands.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.CommandManager;
import it.polimi.ingsw.network.message.allMessages.SelectionNumPlayers;

public class SelNumPlayersCommand implements CommandManager {

    private final Client tcpClient;

    public SelNumPlayersCommand(Client client){
        tcpClient = client;
    }

    @Override
    public void handleMessage(String[] commands) {
        int numOfPlayers = Integer.parseInt(commands[1]);
        SelectionNumPlayers toSend = new SelectionNumPlayers(tcpClient.getUsername(), numOfPlayers);
        tcpClient.sendMessage(toSend);
    }
}
