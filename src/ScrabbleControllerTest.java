import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import static org.junit.Assert.*;


public class ScrabbleControllerTest {
    ScrabbleGame testGame = new ScrabbleGame();
    ScrabbleController testController = new ScrabbleController(testGame);
    Board testBoard = new Board();
    boolean[] bool = new boolean[2];

    @Test
    public void lettersAreInLine() {
        bool = new boolean[2];
        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        SelectionData d1 = new SelectionData(1,2, new Piece('h'));
        SelectionData d2 = new SelectionData(1,3, new Piece('a'));
        SelectionData d3 = new SelectionData(1,4, new Piece('b'));
        SelectionData d4 = new SelectionData(1,5, new Piece('c'));

        testBoard.placePiece(d1);
        testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        testController.addToSelectedBoardButtonsForTesting(d4);

        bool[0] = true;
        bool[1] = false;
        assertEquals(testController.lettersAreInLine(), bool);


        bool = new boolean[2];
        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('h'));
        d2 = new SelectionData(2,1, new Piece('a'));
        d3 = new SelectionData(2,1, new Piece('b'));
        d4 = new SelectionData(2,1, new Piece('c'));

        testBoard.placePiece(d1);
        testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        testController.addToSelectedBoardButtonsForTesting(d4);

        bool[0] = false;
        bool[1] = true;
        assertEquals(testController.lettersAreInLine(), bool);


        bool = new boolean[2];
        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('h'));
        d2 = new SelectionData(3,3, new Piece('a'));
        d3 = new SelectionData(5,5, new Piece('b'));
        d4 = new SelectionData(7,7, new Piece('c'));

        testBoard.placePiece(d1);
        testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        testController.addToSelectedBoardButtonsForTesting(d4);

        bool[0] = false;
        bool[1] = false;
        assertEquals(testController.lettersAreInLine(), bool);


    }

    @Test
    public void getWord() {
        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        SelectionData d1 = new SelectionData(1,2, new Piece('l'));
        SelectionData d2 = new SelectionData(1,3, new Piece('e'));
        SelectionData d3 = new SelectionData(1,4, new Piece('a'));
        SelectionData d4 = new SelectionData(1,5, new Piece('n'));

        testBoard.placePiece(d1);
        testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        testController.addToSelectedBoardButtonsForTesting(d4);

        assertEquals(testController.getWord(), "lean");


        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('l'));
        d2 = new SelectionData(2,1, new Piece('e'));
        d3 = new SelectionData(3,1, new Piece('a'));
        d4 = new SelectionData(4,1, new Piece('n'));

        testBoard.placePiece(d1);
        testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        testController.addToSelectedBoardButtonsForTesting(d4);

        assertEquals(testController.getWord(), "lean");


        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('l'));
        d2 = new SelectionData(2,1, new Piece('e'));
        d3 = new SelectionData(3,1, new Piece('a'));
        d4 = new SelectionData(4,1, new Piece('n'));

        testBoard.placePiece(d1);
        testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);

        assertEquals(testController.getWord(), "lean");


        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('l'));
        d2 = new SelectionData(2,1, new Piece('b'));
        d3 = new SelectionData(3,1, new Piece('c'));
        d4 = new SelectionData(4,1, new Piece('d'));

        testBoard.placePiece(d1);
        testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        testController.addToSelectedBoardButtonsForTesting(d4);

        assertEquals(testController.getWord(), "");
    }

    @Test
    public void isValidWord() {


        try {
            testController = new ScrabbleController(testGame);
            assertTrue(testController.isValidWord("hi"));

            testController = new ScrabbleController(testGame);
            assertFalse(testController.isValidWord("asdfkljasdlfj"));


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    public void calculateScore() {
        testController = new ScrabbleController(testGame);
        assertEquals(testController.calculateScore("hi"), 5);
    }

    @Test
    public void revertSelections() {
        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        SelectionData d1 = new SelectionData(1,2, new Piece('l'));
        SelectionData d2 = new SelectionData(1,3, new Piece('e'));
        SelectionData d3 = new SelectionData(1,4, new Piece('a'));
        SelectionData d4 = new SelectionData(1,5, new Piece('n'));

        testBoard.placePiece(d1);
        testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        testController.addToSelectedBoardButtonsForTesting(d2);
        testController.addToSelectedHandButtonsForTesting(d3);
        testController.addToSelectedHandButtonsForTesting(d4);
        testController.getWord();
        assertTrue(testController.getSelectedBoardButtonsForTesting().isEmpty());
        assertTrue(testController.getSelectedHandButtonsForTesting().isEmpty());
    }

    @Test
    public void actionPerformed() {

    }

    }