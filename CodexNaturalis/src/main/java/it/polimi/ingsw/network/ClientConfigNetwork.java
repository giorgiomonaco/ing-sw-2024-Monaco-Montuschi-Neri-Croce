package it.polimi.ingsw.network;

import java.util.Scanner;

public class ClientConfigNetwork {
    private int portRMI;
    private int portTCP;
    private String registryName;
    private String serverIP;

    /*
    public ClientConfigNetwork(){
        this.portRMI = 1234;
        this.portTCP = 1235;
        this.registryName = "RMIServerInterface";
        try {
            this.socketIP = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
            System.err.println("Couldn't get the LocalHost IP address.");
        }
    }
    */

    public ClientConfigNetwork(){
    }

    public ClientConfigNetwork createConfig(){
        System.out.println("Please insert the following data if you want, " +
                "instead press only ENTER if you want to use the suggested () information.");

        Scanner scan = new Scanner(System.in);
        System.out.println("Insert the IP address of the server (127.0.0.1) : ");
        serverIP = scan.nextLine().trim();
        if(serverIP.isEmpty()) {
            serverIP = "127.0.0.1";
        }

        System.out.println("Insert the number of the TCP port (1235) : ");
        String tcpPort = scan.nextLine().trim();
        if(tcpPort.isEmpty()) {
            portTCP = 1235;
        } else {
            portTCP = Integer.parseInt(tcpPort);
        }

        System.out.println("Insert the name of the RMI registry (RMIServerInterface) : ");
        registryName = scan.nextLine().trim();
        if(registryName.isEmpty()) {
            registryName = "RMIServerInterface";
        }

        System.out.println("Insert the number of the RMI port (1234) : ");
        String rmiPort = scan.nextLine().trim();
        if(rmiPort.isEmpty()) {
            portRMI = 1234;
        } else {
            portRMI = Integer.parseInt(rmiPort);
        }

        System.out.println("---Summary---");
        System.out.println("-Server IP address: " + serverIP);
        System.out.println("-TCP port:          " + portTCP);
        System.out.println("-RMI port:          " + portRMI);
        System.out.println("-Registry name:     " + registryName);
        System.out.println("-------------");


        // Salvataggio dati su file da fare

        return this;
    }

    public int getPortRMI() {
        return portRMI;
    }

    public int getPortTCP() {
        return portTCP;
    }

    public String getRegistryName() {
        return registryName;
    }

    public String getServerIP() {
        return serverIP;
    }


}
