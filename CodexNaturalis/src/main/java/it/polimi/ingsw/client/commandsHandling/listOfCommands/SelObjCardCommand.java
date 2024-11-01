package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.SelectionObjCard;
import it.polimi.ingsw.server.model.ObjectiveCard;

import java.rmi.RemoteException;

public class SelObjCardCommand implements CommandManager {

    private final Client client;

    public SelObjCardCommand(Client client){
        this.client = client;
    }


    /**
     * Handles a selection of the objective card command message.
     *
     * @param commands array of command parameters
     * @param clientState the current state of the client
     * @throws RemoteException if a remote communication issue occurs
     * @throws CommandNotAvailableException if the command is not available in the current client state
     * @throws WrongInsertionException if the command insertion is incorrect
     */
    @Override
    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {

        if(!client.getCurrentState().equals(stateEnum.SELECT_OBJECTIVE)){
            throw new CommandNotAvailableException();
        }

        int selection = Integer.parseInt(commands[1]);
        if(selection != 1 && selection != 2){
            throw new WrongInsertionException("WRONG SELECTION!\nYou have to select a card from the available ones.");
        }

        SelectionObjCard toSend = new SelectionObjCard(client.getUsername(), selection);
        ObjectiveCard card = client.getListObjective().get(selection-1);
        client.setObjective(card);
        client.sendMessage(toSend);
    }
}
