package it.polimi.ingsw.client.view.TUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.ReadCommand;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.client.view.TUI.TuiViews.*;
import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.model.Card;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tui implements UserInterface{

    private Client tuiCli;
    private Map<stateEnum, TuiView> phaseView;
    private ReadCommand reader;
    List<Card> playerHand;


    public Tui(Client client){

        tuiCli = client;
        client.setUI(this);
        phaseView = new HashMap<>();
        reader = new ReadCommand(this, client);
        Thread readerThread = new Thread(reader);
        readerThread.start();

        phaseView.put(stateEnum.LOGIN, new LoginView());
        phaseView.put(stateEnum.WAITING_LOBBY, new WaitingLobbyView());
        phaseView.put(stateEnum.ALREADY_STARTED, new AlreadyStartedView());
        phaseView.put(stateEnum.DISCONNECTION, new DisconnectionView());
        phaseView.put(stateEnum.DRAW_CARD, new DrawCardView());
        phaseView.put(stateEnum.LOBBY, new LobbyView());
        phaseView.put(stateEnum.PLAY_CARD, new PlayCardView());
        phaseView.put(stateEnum.SELECT_NUM_PLAYERS, new SelNumPlayerView());
        phaseView.put(stateEnum.SELECT_TOKEN, new SelTokenView());
        phaseView.put(stateEnum.WAITING_TURN, new WaitTurnView());
        phaseView.put(stateEnum.REJECTED, new RejectedView());
        phaseView.put(stateEnum.SHOW_CARDS, new ShowCardsView());
        phaseView.put(stateEnum.SHOW_PLAYER_RESOURCES, new ShowPlayerResources());
        phaseView.put(stateEnum.SHOW_PLAYER_BOARD, new ShowPlayerBoard());
    }


    @Override
    public void run() {
        switch(tuiCli.getCurrentState()){
            case LOGIN:
                phaseView.get(stateEnum.LOGIN).play();
                break;
            case WAITING_LOBBY:
                phaseView.get(stateEnum.WAITING_LOBBY).play();
                break;
            case REJECTED:
                phaseView.get(stateEnum.REJECTED).play();
                break;
            case ALREADY_STARTED:
                phaseView.get(stateEnum.ALREADY_STARTED).play();
                break;
            case DISCONNECTION:
                phaseView.get(stateEnum.DISCONNECTION).play();
                break;
            case DRAW_CARD:
                phaseView.get(stateEnum.DRAW_CARD).play();
                break;
            case LOBBY:
                phaseView.get(stateEnum.LOBBY).play();
                break;
            case PLAY_CARD:
                phaseView.get(stateEnum.PLAY_CARD).play();
                break;
            case SELECT_NUM_PLAYERS:
                phaseView.get(stateEnum.SELECT_NUM_PLAYERS).play();
                break;
            case SELECT_TOKEN:
                phaseView.get(stateEnum.SELECT_TOKEN).play();
                break;
            case WAITING_TURN:
                phaseView.get(stateEnum.WAITING_TURN).play();
                break;
            case SHOW_CARDS:
                phaseView.get(stateEnum.SHOW_CARDS).play();
            case SHOW_PLAYER_BOARD:
                phaseView.get(stateEnum.SHOW_PLAYER_BOARD).play();
            case SHOW_PLAYER_RESOURCES:
                phaseView.get(stateEnum.SHOW_PLAYER_RESOURCES).play();
        }
    }

    @Override
    public void endGame() {

    }

    @Override
    public void printErrorMessage(Message msg) {
        String toPrint = msg.getDescription();
        System.err.println(toPrint);
    }

    @Override
    public void printMessage(Message msg) {
        String toPrint = msg.getDescription();
        System.out.println(toPrint);
    }


    public void viewCards(List<Card> playerHand) {
        ShowCardsView showCardsView = new ShowCardsView();
        showCardsView.play(playerHand);
    }


    public void viewCard(Card card) {
        DrawCardView drawCardView = new DrawCardView();
        drawCardView.response(card);
    }



    public void viewUncoveredCards(List<Card> cardList) {
        ShowUncoveredCardsView showUncoveredCardsView = new ShowUncoveredCardsView();
        showUncoveredCardsView.viewUncoveredCards(cardList);
    }


}
