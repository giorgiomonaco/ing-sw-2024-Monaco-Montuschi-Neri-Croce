package it.polimi.ingsw.model;


import java.util.ArrayList;
import java.util.List;

public class GoldCard extends Card {

    //name of the card if it has one
    private final int cardName;
    // Card condition for points (points for each covered angle, symbol, or for just placing the card
    private final int condition;
    //number of points the card gives when played
    private final int cardPoints;
    //Visible symbols needed on the game board to play the card
    private final int[] neededSymbols;

    //Constructor
    public GoldCard(int name, Angle[] frontAngles, Angle[] backAngles, Symbol backSymbol, int condition,
                    int cardPoints, int[] neededSymbols){
        super(frontAngles, backAngles, backSymbol);
        //we assign the name of the card
        this.cardName = name;
        this.condition = condition;
        //We assign the value of the card points
        this.cardPoints = cardPoints;
        //we assign the symbols needed to play the card
        this.neededSymbols = neededSymbols;
    }

    //get of the card name
    public int getCardName() {
        return cardName;
    }

    //get the list of all the symbols needed to play the card
    public int[] getNeededSymbols() {
        return neededSymbols;
    }

    //get how many points the card gives when played
    public int getCardPoints() {
        return cardPoints;
    }

    public int getCondition() {
        return condition;
    }
}