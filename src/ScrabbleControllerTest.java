import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;


public class ScrabbleControllerTest {//
    ScrabbleGame testGame = new ScrabbleGame();
    ScrabbleController testController = new ScrabbleController(testGame);
    Board testBoard = new Board();
    boolean[] bool = new boolean[2];

    @Test
    public void lettersAreInLine() {

        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        SelectionData d1 = new SelectionData(1,1, new Piece('h'));
        SelectionData d2 = new SelectionData(1,2, new Piece('a'));
        SelectionData d3 = new SelectionData(1,3, new Piece('b'));
        SelectionData d4 = new SelectionData(1,4, new Piece('c'));

        ArrayList<SelectionData> selectedBoardButtons = new ArrayList<>();
        selectedBoardButtons.add(d1);
        selectedBoardButtons.add(d2);
        selectedBoardButtons.add(d3);
        selectedBoardButtons.add(d4);

        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        //testController.addToSelectedBoardButtonsForTesting(d4);

        assertTrue(testGame.lettersAreInLine(selectedBoardButtons)[0]);
        assertTrue(testGame.lettersAreInLine(selectedBoardButtons)[1]);


        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('h'));
        d2 = new SelectionData(2,1, new Piece('a'));
        d3 = new SelectionData(3,1, new Piece('b'));
        d4 = new SelectionData(4,1, new Piece('c'));
        ArrayList<SelectionData> selectedBoardButtons1 = new ArrayList<>();
        selectedBoardButtons1.add(d1);
        selectedBoardButtons1.add(d2);
        selectedBoardButtons1.add(d3);
        selectedBoardButtons1.add(d4);
        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        //testController.addToSelectedBoardButtonsForTesting(d4);

        assertTrue(testGame.lettersAreInLine(selectedBoardButtons1)[0]);
        assertFalse(testGame.lettersAreInLine(selectedBoardButtons1)[1]);


        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('h'));
        d2 = new SelectionData(3,3, new Piece('a'));
        d3 = new SelectionData(5,5, new Piece('b'));
        d4 = new SelectionData(7,7, new Piece('c'));
        ArrayList<SelectionData> selectedBoardButtons2 = new ArrayList<>();
        selectedBoardButtons2.add(d1);
        selectedBoardButtons2.add(d2);
        selectedBoardButtons2.add(d3);
        selectedBoardButtons2.add(d4);
        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        //testController.addToSelectedBoardButtonsForTesting(d4);

        assertFalse(testGame.lettersAreInLine(selectedBoardButtons2)[0]);
        assertFalse(testGame.lettersAreInLine(selectedBoardButtons2)[1]);


    }

    @Test
    public void getWord() {
        ArrayList<SelectionData> selectedBoardButtons;
        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        SelectionData d1 = new SelectionData(1,2, new Piece('l'));
        SelectionData d2 = new SelectionData(1,3, new Piece('e'));
        SelectionData d3 = new SelectionData(1,4, new Piece('a'));
        SelectionData d4 = new SelectionData(1,5, new Piece('n'));
        ArrayList<SelectionData> selectedBoardButtons2 = new ArrayList<>();
        selectedBoardButtons2.add(d1);
        selectedBoardButtons2.add(d2);
        selectedBoardButtons2.add(d3);
        selectedBoardButtons2.add(d4);
        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        //testController.addToSelectedBoardButtonsForTesting(d4);

        assertEquals(testGame.getWord(selectedBoardButtons2), "lean");


        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('l'));
        d2 = new SelectionData(2,1, new Piece('e'));
        d3 = new SelectionData(3,1, new Piece('a'));
        d4 = new SelectionData(4,1, new Piece('n'));

        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        //testController.addToSelectedBoardButtonsForTesting(d4);
        ArrayList<SelectionData> selectedBoardButtons1 = new ArrayList<>();
        selectedBoardButtons1.add(d1);
        selectedBoardButtons1.add(d2);
        selectedBoardButtons1.add(d3);
        selectedBoardButtons1.add(d4);
        assertEquals(testGame.getWord(selectedBoardButtons1), "lean");


        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('l'));
        d2 = new SelectionData(2,1, new Piece('e'));
        d3 = new SelectionData(3,1, new Piece('a'));
        d4 = new SelectionData(4,1, new Piece('n'));

        ArrayList<SelectionData> selectedBoardButtons3 = new ArrayList<>();
        selectedBoardButtons3.add(d1);
        selectedBoardButtons3.add(d2);
        selectedBoardButtons3.add(d3);
        selectedBoardButtons3.add(d4);

        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);

        assertEquals(testGame.getWord(selectedBoardButtons3), "lean");



        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(1,1, new Piece('l'));
        d2 = new SelectionData(2,1, new Piece('b'));
        d3 = new SelectionData(3,1, new Piece('c'));
        d4 = new SelectionData(4,1, new Piece('d'));
        ArrayList<SelectionData> selectedBoardButtons4 = new ArrayList<>();
        selectedBoardButtons4.add(d1);
        selectedBoardButtons4.add(d2);
        selectedBoardButtons4.add(d3);
        selectedBoardButtons4.add(d4);


        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        //testController.addToSelectedBoardButtonsForTesting(d4);

        assertEquals(testGame.getWord(selectedBoardButtons4), "");
    }

    @Test
    public void getBranchWords() {
        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        SelectionData d1 = new SelectionData(1,2, new Piece('a'));
        SelectionData d2 = new SelectionData(1,3, new Piece('b'));
        SelectionData d3 = new SelectionData(1,4, new Piece('b'));
        SelectionData d4 = new SelectionData(1,5, new Piece('a'));

        SelectionData d5 = new SelectionData(2,5, new Piece('s'));
        SelectionData d6 = new SelectionData(2,6, new Piece('a'));
        SelectionData d7 = new SelectionData(2,7, new Piece('t'));

        ArrayList<SelectionData> selectedBoardButtons4 = new ArrayList<>();
        selectedBoardButtons4.add(d1);
        selectedBoardButtons4.add(d2);
        selectedBoardButtons4.add(d3);
        selectedBoardButtons4.add(d4);
        selectedBoardButtons4.add(d5);
        selectedBoardButtons4.add(d6);
        selectedBoardButtons4.add(d7);

        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        //testController.addToSelectedBoardButtonsForTesting(d4);
        testBoard.placePiece(d5);
        //testController.addToSelectedBoardButtonsForTesting(d5);
        testBoard.placePiece(d6);
        //testController.addToSelectedBoardButtonsForTesting(d6);
        testBoard.placePiece(d7);
        //testController.addToSelectedBoardButtonsForTesting(d7);

        assertEquals(testGame.getBranchWords(selectedBoardButtons4).get(0), "as");


        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        testBoard = new Board();
        d1 = new SelectionData(2,1, new Piece('a'));
        d2 = new SelectionData(3,1, new Piece('b'));
        d3 = new SelectionData(4,1, new Piece('b'));
        d4 = new SelectionData(5,1, new Piece('a'));

        d5 = new SelectionData(5,2, new Piece('s'));
        d6 = new SelectionData(6,2, new Piece('a'));
        d7 = new SelectionData(7,2, new Piece('t'));
        ArrayList<SelectionData> selectedBoardButtons5 = new ArrayList<>();
        selectedBoardButtons5.add(d1);
        selectedBoardButtons5.add(d2);
        selectedBoardButtons5.add(d3);
        selectedBoardButtons5.add(d4);
        selectedBoardButtons5.add(d5);
        selectedBoardButtons5.add(d6);
        selectedBoardButtons5.add(d7);


        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        testBoard.placePiece(d3);
        //testController.addToSelectedBoardButtonsForTesting(d3);
        testBoard.placePiece(d4);
        //testController.addToSelectedBoardButtonsForTesting(d4);
        testBoard.placePiece(d5);
        //testController.addToSelectedBoardButtonsForTesting(d5);
        testBoard.placePiece(d6);
        //testController.addToSelectedBoardButtonsForTesting(d6);
        testBoard.placePiece(d7);
        //testController.addToSelectedBoardButtonsForTesting(d7);

        assertEquals(testGame.getBranchWords(selectedBoardButtons5).get(0), "as");
    }
    @Test
    public void isValidWord() {


        try {
            testController = new ScrabbleController(testGame);
            assertTrue(testGame.isValidWord("hi"));

            testController = new ScrabbleController(testGame);
            assertFalse(testGame.isValidWord("asdfkljasdlfj"));


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    public void calculateScore() {
        testGame = new ScrabbleGame();
        testController = new ScrabbleController(testGame);
        assertEquals(testGame.calculateScore("HI"), 5);
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
        ArrayList<SelectionData> selectedBoardButtons5 = new ArrayList<>();
        selectedBoardButtons5.add(d1);
        selectedBoardButtons5.add(d2);
        selectedBoardButtons5.add(d3);
        selectedBoardButtons5.add(d4);


        testBoard.placePiece(d1);
        //testController.addToSelectedBoardButtonsForTesting(d1);
        testBoard.placePiece(d2);
        //testController.addToSelectedBoardButtonsForTesting(d2);
        //testController.addToSelectedHandButtonsForTesting(d3);
        //testController.addToSelectedHandButtonsForTesting(d4);
        testGame.getWord(selectedBoardButtons5);
        //testController.revertSelections();

        //assertTrue(testController.getSelectedBoardButtonsForTesting().isEmpty());
        //assertTrue(testController.getSelectedHandButtonsForTesting().isEmpty());

        //assertTrue(testController.getSelectedBoardButtonsForTesting().isEmpty());
        //assertTrue(testController.getSelectedHandButtonsForTesting().isEmpty());

    }

    @Test
    public void actionPerformed() {

    }

}
