package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.network.message.Message;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote{
    void receiveFromServer(Message message) throws RemoteException;
}
