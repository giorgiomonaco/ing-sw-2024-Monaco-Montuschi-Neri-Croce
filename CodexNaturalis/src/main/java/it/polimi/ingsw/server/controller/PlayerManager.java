package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.client.view.ViewTry;

public class PlayerManager {
    //This class manages the creation of players based on how many wants to play,
    //and it adds the players to the game
    private final Game game;

    //Constructor
    public PlayerManager(Game game){
        this.game = game;
    }

   public void playersInitialization(int numOfPlayers){
        //we get how many players are playing from the view interaction with the user

        //check if the num of players is valid
        //if not we repeat the question
       /* while(numOfPlayers < 1 || numOfPlayers > 4) {
            numOfPlayers = view.askAgainNumOfPlayer();
        //}*/
        //For each we want the name
        //And which token he/she wants from the available pool
        for (int a = 0; a < numOfPlayers; a++) {
            //we collect the name of that player
         //   String playerName = view.getPlayerName(a + 1);
            //then we add it to the game
            addPlayerToGame(playerName);
        }
    }

    public void addPlayerToGame(String playerName){
        Player p = new Player(game,playerName);
        game.addPlayer(p);
    }

}
