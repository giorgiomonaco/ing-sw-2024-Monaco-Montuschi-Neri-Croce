package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EndgameManagerTest {





    private Game game = new Game(1);
    private Player player = new Player("tom");
    private Boards board = new Boards();
    private EndgameManager endgameManager;

    @BeforeEach
    void setUp() {
        game = new Game(1);
        player = new Player("tom");
        endgameManager = new EndgameManager(game, player);
    }
    private static class Leaf extends Symbol {
        public Leaf() {
            super("leaf", "resource");
        }
    }
    private static class Butterfly extends Symbol {
        public Butterfly() {
            super("butterfly", "resource");
        }
    }

    //new ObjectiveCard(1, 2, "position", "purple", "down-left", "purple", "down-left", "purple", null)


    @Test
    void testObjectivePointsCounterWithNullValues() {
        // Test with player having no objective card
        player.setObjectiveCard(null);
        game.setCommonObjectives(null);

        assertEquals(0, endgameManager.objectivePointsCounter());
    }

    @Test
    void testObjectiveCreatorWithNullGameBoard() {
        player.setBoards(null);
        assertEquals(0, endgameManager.objectivePointsCounter());
    }

    @Test
    void testResourceCounterWithNullResources() {
        for (int i = 0; i < 7; i++) {
            player.setResource(i, 0);

        }
        ObjectiveCard objectiveCard = new ObjectiveCard(1, 2, "position", "purple", "down-left", "purple", "down-left", "purple", null);
        player.setObjectiveCard(objectiveCard);

        assertEquals(0, endgameManager.objectivePointsCounter());
    }

    @Test
    void testSpecialCounterWithNullResources() {
        for (int i = 0; i < 7; i++) {
            player.setResource(i, 0);
        }
        ObjectiveCard objectiveCard = new ObjectiveCard(1, 2, "position", "purple", "down-left", "purple", "down-left", "purple", null);
        player.setObjectiveCard(objectiveCard);

        assertEquals(0, endgameManager.objectivePointsCounter());
    }

    @Test
    void objectivePointsCounter() {

    }


    // Helper method to create a mock Card with the specified symbol name
    private ResourceCard createCard() {
        Symbol symbol = new Leaf();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol);
        return new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);}

    // Mock Boards class to simulate the game board dimensions
    private static class MockBoards extends Boards {
        @Override
        public int getMAX_X() {
            return 6; // Example width of the board
        }

        @Override
        public int getMAX_Y() {
            return 6; // Example height of the board
        }
    }



    @Test
    void testCheckDirectionDown() {
        player.setBoards(board);
        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 2, "position", "green", "down", "green", "down", "green", null);
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);


        // Populate the matrix
        cardMatrix[1][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        assertTrue(manager.checkDirection( cardMatrix, 1, 1, "down", "green"));
        assertTrue(manager.checkDirection( cardMatrix, 1, 2, "down", "green"));
        assertFalse(manager.checkDirection( cardMatrix, 1, 3, "down", "green"));
    }


    @Test
    void testCheckDirectionDownRight() {
        player.setBoards(board);
        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 2, "position", "green", "down", "green", "down", "green", null);
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);


        // Populate the matrix
        cardMatrix[1][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[4][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        assertTrue(manager.checkDirection( cardMatrix, 1, 1, "down-right", "green"));
        assertTrue(manager.checkDirection( cardMatrix, 2, 2, "down-right", "green"));
        assertFalse(manager.checkDirection( cardMatrix, 3, 3, "down-right", "green"));
    }

    @Test
    void testCheckDirectionDownLeft() {
        player.setBoards(board);
        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 2, "position", "green", "down", "green", "down", "green", null);
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);


        // Populate the matrix
        cardMatrix[5][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[4][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        assertTrue(manager.checkDirection( cardMatrix, 5, 1, "down-left", "green"));
        assertTrue(manager.checkDirection( cardMatrix, 4, 2, "down-left", "green"));
        assertFalse(manager.checkDirection( cardMatrix, 3, 3, "down-left", "green"));
    }

    @Test
    void testCheckDirectionOut() {
        player.setBoards(board);
        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 2, "position", "green", "down", "green", "down", "green", null);
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);


        // Populate the matrix
        cardMatrix[1][5] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[5][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        assertThrowsExactly( IllegalArgumentException.class, () -> manager.checkDirection( cardMatrix, 1, 99, "down", "green"));
        assertThrowsExactly( IllegalArgumentException.class, () ->manager.checkDirection( cardMatrix, 99, 2, "down-right", "green"));
        assertThrowsExactly( IllegalArgumentException.class, () ->manager.checkDirection( cardMatrix, 0, 3, "down-left", "green"));
    }

    @Test
    void testFullSequence() {
        player.setBoards(board);
        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 2, "position", "green", "down-left", "green", "down-left", "green", null);
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);
        player.setObjectiveCard(objectiveCard);


        // Populate the matrix
        cardMatrix[5][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[4][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);


        assertEquals(2,manager.findPattern(5,1,cardMatrix));
    }

}

