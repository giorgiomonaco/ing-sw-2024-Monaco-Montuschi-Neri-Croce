package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.server.model.Boards;
import it.polimi.ingsw.server.model.Card;


import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class PlayCardView implements TuiView{
    private Boards boards;
    private List<Card> playerHand;
    private Client client;
    private Colors colors = new Colors();


    @Override
    public void play(Client client) {
        this.client = client;
        System.out.println("CHOOSE A CARD TO PLAY FROM YOUR HAND \n\nCHOOSE A SPOT ON THE BOARD TO PLACE THE CARD\n");
        playerHand = client.getPlayerHand();
        boards = client.getBoards();
        printBoard();
        printHand();
        askCardToPlay();

    }

    private void askCardToPlay() {
        System.out.println("WHICH CARD DO YOU WANT TO PLAY ?\n\nInsert command card <num> <x> <y> <true>, where:\n-num is the number of the card you want to play\n-x and y are the coordinates on the board\n-Write true if you want to place the card face up, false if you want to place it back up");

    }

    private void printHand(){
        Tui view = (Tui) client.getUI();
        for (Card card : playerHand) {
            view.printCard(card);
        }
    }

    private void printBoard(){
        for (int y = 0; y < boards.getMAX_Y() ; y++){

            System.out.println();
            for (int x = 0; x < boards.getMAX_X() ; x++){
                switch (boards.checkboard[x][y]){
                    case -1:
                        System.out.print(" "+ colors.redColor+ boards.checkboard[x][y]+ colors.resetColor +" ");
                        break;
                    case 0:
                        System.out.print("  "+ colors.greenColor + boards.checkboard[x][y]+ colors.resetColor +" ");
                        break;
                    case 1:
                        System.out.print("  "+ colors.blueColor + boards.checkboard[x][y]+ colors.resetColor + " ");
                        break;

                }
            }
        }
    }

}

