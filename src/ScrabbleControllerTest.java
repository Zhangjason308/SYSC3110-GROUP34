import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        testBoard.placePiece(d2);
        testBoard.placePiece(d3);
        testBoard.placePiece(d4);

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
        testBoard.placePiece(d2);
        testBoard.placePiece(d3);
        testBoard.placePiece(d4);

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
        testBoard.placePiece(d2);
        testBoard.placePiece(d3);
        testBoard.placePiece(d4);

        bool[0] = false;
        bool[1] = false;
        assertEquals(testController.lettersAreInLine(), bool);


    }

    @Test
    public void actionPerformed() {
    }


}