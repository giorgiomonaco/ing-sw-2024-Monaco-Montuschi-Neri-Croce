package it.polimi.ingsw.network.server;

import it.polimi.ingsw.model.Message;
import it.polimi.ingsw.network.client.RMIClientInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ServerRMI implements RMIServerInterface {
    static int PORT = 1234;
    Registry registry;
    RMIServerInterface obj;

    public ServerRMI() throws RemoteException {
        super();
    }

    public void start(){
        try {
            obj = new ServerRMI();
            RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(obj, 0);
            registry = LocateRegistry.createRegistry(PORT);
            registry.bind("RMIServerInterface", stub);
        } catch (RemoteException | AlreadyBoundException e) {
            System.err.println("Error while starting server: " + e.toString());
            System.exit(0);
        }
        System.out.println("RMI server is ready on port: " + PORT);
    }

    public void stop() {
        try {
            registry.unbind("RMIServerInterface");
            UnicastRemoteObject.unexportObject(obj, true);
            UnicastRemoteObject.unexportObject(registry, true);
            System.err.println("Server stopped.");
        } catch (Exception e) {
            System.err.println("Error while stopping server: " + e.getMessage());
        }
    }

    @Override
    public void login(String nick) throws RemoteException {
        System.out.println(nick + " is logging...");
    }

    // @Override
   // public void receiveMessage(Message message, RMIClientInterface clientInterface) {
        //TO DO...
   // }
}