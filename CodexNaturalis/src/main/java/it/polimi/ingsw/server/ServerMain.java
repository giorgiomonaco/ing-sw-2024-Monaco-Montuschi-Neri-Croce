package it.polimi.ingsw.server;

import it.polimi.ingsw.network.ServerConfigNetwork;
import java.util.Scanner;

/**
 * Main class of the server, it starts both the RMI server and the socket server.
 */
public class ServerMain {
    public static void main(String[] args) {
        String serverIP;
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert the server address IP if you want, instead press only ENTER: ");
        serverIP = scan.nextLine().trim();
        ServerConfigNetwork data = new ServerConfigNetwork();

        if(!serverIP.isEmpty()) {
            // if(isValid(serverIP)){
            data.setServerIP(serverIP);
            // }
        }

        System.out.println("---Summary---");
        System.out.println("-Server IP address: " + data.getServerIP());
        System.out.println("-------------");

        ServerHandler handler = new ServerHandler(data);
        handler.init();


    }
}
