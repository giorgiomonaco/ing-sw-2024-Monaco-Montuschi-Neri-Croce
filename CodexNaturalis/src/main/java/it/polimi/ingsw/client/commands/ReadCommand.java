package it.polimi.ingsw.client.commands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.listOfCommands.LoginCommand;
import it.polimi.ingsw.client.stateManager.stateEnum;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.TCP.ClientTCP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadCommand extends Thread{

    private Tui tui;
    private stateEnum currState;
    private String command;
    private Map<String, CommandManager> mapOfCommand;
    private Client client;

    public ReadCommand(Tui tui, Client tcpClient){
        this.tui = tui;
        client = tcpClient;
        currState = tui.getCurrentState();
        mapOfCommand = new HashMap<>();
        initializeMap();
    }

    public void run(){
        Scanner scan = new Scanner(System.in);

        while(!Thread.currentThread().isInterrupted()) {
            command = scan.nextLine().trim();

            mapCommand(command);
        }
    }

    public void mapCommand(String command) {
        String[] commands = command.split(" ");

        if(!mapOfCommand.containsKey(commands[0])) {
            System.err.println("Command not found. Try another command.");
        }
        else {
            mapOfCommand.get(commands[0]).handleMessage(commands);
        }
    }

    public void initializeMap(){
        mapOfCommand.put("login", new LoginCommand(client));
    }
}
