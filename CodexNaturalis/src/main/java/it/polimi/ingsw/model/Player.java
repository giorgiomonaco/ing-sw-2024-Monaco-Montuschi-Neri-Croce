package it.polimi.ingsw.model;

import java.util.List;
<<<<<<< HEAD

public class Player {
    public String colour;
    public int points;
    public boolean beginner;
    public int[] elements;
    private List<Card> availableCards;


    public String getColour() {
        return colour;
    }

    public int getPoints() {
        return points;
    }

    public int[] getElements() {
        return elements;
=======
import java.util.ArrayList;
public class Player {
    private String colour;
    private int points;
    private List<Card> availableCards;
    private boolean beginner;
    private int[] elements;

    public Player(String colour, boolean beginner, List<Card> cards ) {
        this.elements = new int[7];
        this.points = 0;
        this.colour = colour;
        this.beginner = beginner;
        this.availableCards = cards;
    }

    //methods

    public int getPoints(){
        return this.points;
>>>>>>> origin/main
    }

    public List<Card> getAvailableCards() {
        return availableCards;
    }

<<<<<<< HEAD
    public void setBeginner(boolean beginner) {
        this.beginner = beginner;
    }

    public List<Card> drawCard(Card drawed){
        availableCards = getAvailableCards();
        availableCards.add(drawed);
        return availableCards;
    }

    public List<Card> removeCard(Card removed){
        availableCards = getAvailableCards();
        availableCards.remove(removed);
        return availableCards;
    }


=======
    public int[] getElements() {
        return elements;
    }

    public void setBeginner() {
        this.beginner = true;
    }

    public void playCard(Card card){
        availableCards.remove(card);

    }

    // choosing where to draw the card, either from the visible ones, or from the covered deck
    public void drawCardDeck(){
        Card tempCard = Deck.drawDeck();

        availableCards.add(tempCard);
    }

    public void drawCardVisible(){
        Card tempCard = Deck.drawVisible();

        availableCards.add(tempCard);
    }
>>>>>>> origin/main
}
