package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GoldCard;
import it.polimi.ingsw.server.model.Symbol;
import it.polimi.ingsw.server.model.VisibleAngle;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoldCardsInitializer {
    private final Game game;
    private int cardNumber = 1;

    //list of the symbols to create the cards
    //remember they are 7 - see card manager for the list
    private final List<Symbol> symbols;
    //Constructor
    public GoldCardsInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }



    /**
     * Creates all the initial cards and adds them to the game's initial cards deck.
     *
     * This method initializes the game by creating and adding cards to the initial cards deck.
     *
     * List of all resource symbols angles can have:
     * <ul>
     *   <li>0 = mushroom (resource)</li>
     *   <li>1 = leaf (resource)</li>
     *   <li>2 = fox (resource)</li>
     *   <li>3 = butterfly (resource)</li>
     *   <li>4 = feather (object)</li>
     *   <li>5 = bottle (object)</li>
     *   <li>6 = scroll (object)</li>
     * </ul>

     * Initialize the gold cards from a JSON file
     */
    public void initializeGoldCards() {

        ClassLoader cl = this.getClass().getClassLoader();
        String item = "goldCards.json";
        InputStream is = cl.getResourceAsStream(item);

        try (Reader reader = new InputStreamReader(Objects.requireNonNull(is, "Couldn't find json file"));
             JsonReader jsonReader = Json.createReader(reader)) {

            JsonArray jsonArray = jsonReader.readArray();

            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                // Store the values of each JSON object
                String topLeft = jsonObject.getString("topLeft");
                String topRight = jsonObject.getString("topRight");
                String bottomLeft = jsonObject.getString("bottomLeft");
                String bottomRight = jsonObject.getString("bottomRight");
                String pointsForEach = jsonObject.getString("pointsForEach");
                String points = jsonObject.getString("points");
                String reqMushroom = jsonObject.getString("reqMushroom");
                String reqLeaf = jsonObject.getString("reqLeaf");
                String reqFox = jsonObject.getString("reqFox");
                String reqButterfly = jsonObject.getString("reqButterfly");
                String type = jsonObject.getString("type");

                // Creating the card front angles
                VisibleAngle[] goldCardFrontAngles =  new VisibleAngle[4];
                String[] corners = {topLeft, topRight, bottomLeft, bottomRight};
                int i = 0;
                // Automation of card angles creation

                for (String corner : corners) {
                    switch (corner) {
                        case "mushroom":
                            goldCardFrontAngles[i] = new VisibleAngle(symbols.get(0));
                            break;
                        case "leaf":
                            goldCardFrontAngles[i] = new VisibleAngle(symbols.get(1));
                            break;
                        case "fox":
                            goldCardFrontAngles[i] = new VisibleAngle(symbols.get(2));
                            break;
                        case "butterfly":
                            goldCardFrontAngles[i] = new VisibleAngle(symbols.get(3));
                            break;
                        case "feather":
                            goldCardFrontAngles[i] = new VisibleAngle(symbols.get(4));
                            break;
                        case "bottle":
                            goldCardFrontAngles[i] = new VisibleAngle(symbols.get(5));
                            break;
                        case "scroll":
                            goldCardFrontAngles[i] = new VisibleAngle(symbols.get(6));
                            break;
                        case "empty":
                            goldCardFrontAngles[i] = new VisibleAngle(null);
                            break;
                        case "null":
                            goldCardFrontAngles[i] = null;
                            break;
                        default:
                            System.out.println("Error while parsing through cards");
                    }
                    i++;
                }

                // Creating backside angles
                VisibleAngle[] goldCardBackAngles = new VisibleAngle[4];
                goldCardBackAngles[0] = new VisibleAngle(null);
                goldCardBackAngles[1] = new VisibleAngle(null);
                goldCardBackAngles[2] = new VisibleAngle(null);
                goldCardBackAngles[3] = new VisibleAngle(null);

                // Getting the points of the card
                int pointsInt = Integer.parseInt(points);

                //getting the symbol of the card
                List<Symbol> backSymbol = new ArrayList<>();
                switch (type){
                    case "orange":
                        backSymbol.add(symbols.getFirst());
                        break;
                    case "green":
                        backSymbol.add(symbols.get(1));
                        break;
                    case "blue":
                        backSymbol.add(symbols.get(2));
                        break;
                    case "purple":
                        backSymbol.add(symbols.get(3));
                        break;
                    default:
                        System.out.println("Error while parsing through cards");
                }

                /////////////////getting the card condition for points
                int condition = 0;
                switch (pointsForEach){
                    case "false":
                        condition = 0;
                        break;
                    case "coveredAngle":
                        condition = 1;
                        break;
                    case "feather":
                        condition = 2;
                        break;
                    case "bottle":
                        condition = 3;
                        break;
                    case "scroll":
                        condition = 4;
                        break;
                    default:
                        System.out.println("Error while parsing through cards");
                }

                //getting the required resources
                int[] neededSymbols ={0,0,0,0};
                String[] reqSymb = {reqMushroom, reqLeaf, reqFox, reqButterfly};
                i = 0;
                for (String symbol : reqSymb){
                    neededSymbols[i] = Integer.parseInt(symbol);
                    i++;
                }

                String front;
                String back;

                front = "images/goldCards/front/" + cardNumber + ".png";
                back = "images/goldCards/back/" + type + ".png";

                GoldCard goldCard = new GoldCard(cardNumber, goldCardFrontAngles, goldCardBackAngles, backSymbol, condition, pointsInt, neededSymbols, front, back);
                addCardToGame(goldCard);

                cardNumber++;
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Couldn't find json file");
        }
    }

    /**
     * This method gets the card and adds it to the game
     * @param card
     */
    public void addCardToGame(GoldCard card){
        game.addGoldCardToDeck(card);
    }
}