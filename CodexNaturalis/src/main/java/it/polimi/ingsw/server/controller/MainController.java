package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.server.ServerHandler;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.client.view.ViewTry;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GameState;
import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private Game game;
    private final ServerHandler serverHandler;
    private GameSetUpper gameSetUpper;

    // Index of the current player
    private int currPlayerIndex;

    // Index of the first player to reach 21 points
    private int finalPlayerIndex;

    // If it's the first turn the players have to choose the token and the personal objective cards.
    private int firstTurn = 0;

    //Constructor, it only needs a game to control
    public MainController(ServerHandler serverHandler){
        this.serverHandler =  serverHandler;
    }

    //The main controller has to start the view as soon as the game is created
    //then has to process every response from the view and use the managers to
    //modify the state of the game and use the managers to modify everything


    //Here it plays the game
    public void gameCreation(String username, int numOfPlayers){

        game = new Game(numOfPlayers);
        //Then creates  the game set up
        gameSetUpper = new GameSetUpper(game);
        //Start the setup of the game
        gameSetUpper.CreateGame(username);

        serverHandler.sendMessageToPlayer(username,
                new LobbyCreation(ServerHandler.HOSTNAME,
                        "--Lobby created!--\nWaiting for the other " + (numOfPlayers-1) + " player/s to join...",
                        game.getUserList()));

    }

    public void playGame(){

    }

    public Game getGame() {
        return game;
    }


    public void joinPlayer(String username){
        if (!game.getGameState().equals(gameStateEnum.ACCEPT_PLAYER)) {
            serverHandler.sendMessageToPlayer(username,
                    new AlreadyStarted(ServerHandler.HOSTNAME));
            return;
        }
        try {

            game.addPlayer(new Player(username));
            List <String> userList = game.getUserList();
            serverHandler.sendMessageToAllExcept(username,
                    new NewPlayerJoin(ServerHandler.HOSTNAME,
                            "\nNew player logged!: " + username + "\nWaiting for the other " +
                                    (game.getPlayersNumber()-game.getPlayerList().size()) +
                                    " player/s to join...",
                            username));
            serverHandler.sendMessageToPlayer(username,
                    new LobbyCreation(ServerHandler.HOSTNAME,
                            "You joined the lobby!\nWaiting for the other " +
                                    (game.getPlayersNumber()-game.getPlayerList().size()) +
                                    " player/s to join...",
                            userList));

        } catch (IllegalStateException e) {
            System.err.println("Max number of player already reached.");
            serverHandler.sendMessageToPlayer(username,
                    new AlreadyStarted(ServerHandler.HOSTNAME));
        }

        if(game.getGameState().equals(gameStateEnum.START)){
            gameStarting();
            for(int i = 0; i < game.getUserList().size(); i++) {
                serverHandler.sendMessageToPlayer(game.getUserList().get(i),
                        new GameStarting(
                                ServerHandler.HOSTNAME,
                                game.getPlayerList().get(i).getPlayerHand()));
            }
            beginFirstTurn();
        }
    }

    public void beginTurn(){
        currPlayerIndex = (currPlayerIndex+1)%(game.getUserList().size());

        if(getGame().getGameState().equals(gameStateEnum.FINAL_TURN) && currPlayerIndex == finalPlayerIndex){
            endGame();
        }
        else {

            serverHandler.sendMessageToPlayer(game.getUserList().get(currPlayerIndex), );}
    }

    public void beginFirstTurn(){

        List<String> availableToken = game.getAvailableTokens();

        for(int i = 0; i < game.getUserList().size(); i++) {

            if(i == 0){
                serverHandler.sendMessageToPlayer(game.getUserList().getFirst(),
                        new FirstTurn(
                                ServerHandler.HOSTNAME,
                                "black",
                                getPlayerByUsername(game.getUserList().getFirst()).getSelObjectiveCard()));
            } else {
                serverHandler.sendMessageToPlayer(game.getUserList().get(i),
                        new FirstTurn(
                                ServerHandler.HOSTNAME,
                                availableToken,
                                getPlayerByUsername(game.getUserList().get(i)).getSelObjectiveCard()));
            }

            while(i == firstTurn){
            }

        }
    }

    public void endGame(){

    }

    public void gameStarting(){
        gameSetUpper.gameSetUp();

        // Initial and final player index is set at -1
        currPlayerIndex = -1;
        finalPlayerIndex = -1;


    }


    public Player getPlayerByUsername(String username) {
        for (Player player : game.getPlayerList()) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }


    public Card drawCard(String username, String whereToDraw, int cardIndex) {
        Player p = getPlayerByUsername(username);
        Card card = cardSelector(whereToDraw, cardIndex);
        if (card instanceof ResourceCard) {
            p.addResourceCard((ResourceCard) card);
            return card;
        } else if (card instanceof GoldCard) {
            p.addGoldCard((GoldCard) card);
            return card;
        }
        return null;
    }
    public Card cardSelector(String whereToDraw, int cardIndex) {
        return switch (whereToDraw) {
            case "CRD" -> game.drawResourceCard();
            case "VRD" -> game.visibleResourceCards.remove(cardIndex);
            case "CGD" -> game.drawGoldCard();
            case "VGD" -> game.visibleGoldCards.remove(cardIndex);
            default -> null;
        };
    }
    public List<Card> getUncoveredCards(){
        List<Card> uncoveredCards = new ArrayList<>();
        uncoveredCards.addAll(game.getVisibleGoldCards());
        uncoveredCards.addAll(game.getVisibleResourceCards());
        return uncoveredCards;
    }


}
