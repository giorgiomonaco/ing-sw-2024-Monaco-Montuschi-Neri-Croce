package it.polimi.ingsw.network.server.TCP;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ConnectionActive;
import it.polimi.ingsw.network.server.ServerHandler;

import java.io.*;
import java.net.Socket;

/**
 * The class manages the communication between server
 * and the client that belongs to the socket assigned.
 */
public class TCPClientHandler implements Runnable{
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public TCPClientHandler(Socket socket){
        this.socket = socket;
    }
    public void run(){

        try {
            synchronized (socket) {
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
            }
        } catch (IOException e) {
            System.err.println("Couldn't open the I/O for the TCP connection via server");
        }

        // send a msg to the client of the received connection

        Message msg;
        try {
            msg = new ConnectionActive(ServerHandler.HOSTNAME);
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            System.err.println("Couldn't connect with the client");
            Thread.currentThread().interrupt();
        }

        // wait for the msg of the client (the first is login)
        // TO DO...

    }
}
