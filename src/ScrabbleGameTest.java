import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
class ScrabbleGameTest {


    @Test
    void placeFirstWord() { //Makes sure first word has 1 piece in the center

    }

    @Test
    void bagisOutofPieces()  { //Bag is out of pieces so game is over
        ScrabbleGame game = new ScrabbleGame();
        assertEquals(game.getBag().numberOfRemainingPieces(), 86);
        Hand hand = new Hand();
        hand.addPieces(game.getBag().grabPieces(86));
        assertEquals(game.getBag().numberOfRemainingPieces(), 0);
    }



    @Test
    void addScore(){
        ScrabbleGame game = new ScrabbleGame();
        int score1 = 100;
        int score2 = 200;
        assertTrue(game.getTurn());

        game.addScore(score1);
        assertEquals(game.getPlayer1Score(), score1);

        game.changeTurn();
        game.addScore(score2);

        assertEquals(game.getPlayer2Score(), score2);

        game.addScore(score1);

        assertEquals(game.getPlayer2Score(), score1 + score2);
    }

    @Test
    void updateStatus() {

        ScrabbleGame game = new ScrabbleGame();
        game.getBag().grabPieces(84);
        for (int i = 0; i< ScrabbleGame.HAND_SIZE;i++ ){
            game.getPlayer1Hand().removePiece(0);
            game.getPlayer2Hand().removePiece(0);
        }
        assertTrue(game.getPlayer1Hand().getHandPieces().isEmpty());
        assertTrue(game.getPlayer2Hand().getHandPieces().isEmpty());

        int x = game.getPlayer1Hand().sizeOfHand();
        int y = game.getBag().numberOfRemainingPieces();
        int x2 =  game.getPlayer2Hand().sizeOfHand();

        assertTrue((x <7) | (x2 <7) && (y <=0));

        assertEquals(game.endConditionIsMet(),true);

        game.updateStatus();

        assertEquals(game.getStatus(),game.calculateWinner());

    }

    @Test
    void calculateWinner(){
        ScrabbleGame game = new ScrabbleGame();

        int score1 = 100;
        int score2 = 100;
        assertTrue(game.getTurn());

        game.addScore(score1);
        assertEquals(game.getPlayer1Score(), score1);

        game.changeTurn();
        game.addScore(score2);

        assertEquals(game.getPlayer2Score(), score2);

        assertEquals(game.calculateWinner(), ScrabbleGame.Status.TIE);

        int score3 = 300;
        int score4 = 100;
        game.changeTurn();
        assertTrue(game.getTurn());

        game.addScore(score3);
        assertEquals(game.getPlayer1Score(), score3+100);

        game.changeTurn();
        game.addScore(score4);

        assertEquals(game.getPlayer2Score(), score4+100);

        assertEquals(game.calculateWinner(), ScrabbleGame.Status.PLAYER_1_WON);

        int score5 = 100;
        int score6 = 1000;
        game.changeTurn();
        assertTrue(game.getTurn());

        game.addScore(score5);
        assertEquals(game.getPlayer1Score(), score5+400);

        game.changeTurn();
        game.addScore(score6);

        assertEquals(game.getPlayer2Score(), score6+200);

        assertEquals(game.calculateWinner(), ScrabbleGame.Status.PLAYER_2_WON);

    }
    @Test
    void swapLettersFromHand(){
        ScrabbleGame game = new ScrabbleGame();
    Hand h = new Hand();
    Piece p = new Piece('p');
        h.addPiece(p);
        h.addPiece(p);
        h.addPiece(p);
        h.addPiece(p);
        h.addPiece(p);
        game.refillHand(h);

        assertEquals(7,h.sizeOfHand());



    }
    @Test
    void removeFromHand(){ // test to ensure the correct hand is used

        ScrabbleGame game = new ScrabbleGame();
        int index = 4;
        //test for player1
        assertTrue(game.getTurn());
        int x1 = game.getPlayer1Hand().sizeOfHand();
        Piece p = game.removeFromHand(index);
        assertEquals(game.getPlayer1Hand().sizeOfHand(),x1-1);
        assertNotEquals(game.getPlayer1Hand().getHandPieces().get(index), p.getLetter());
        //may not account for duplicate letters
        game.changeTurn();

        //test for player 2
        assertFalse(game.getTurn());
        int x2 = game.getPlayer2Hand().sizeOfHand();
        Piece p2 = game.removeFromHand(index);
        assertEquals(game.getPlayer2Hand().sizeOfHand(),x2-1);
        assertNotEquals(game.getPlayer2Hand().getHandPieces().get(index), p2.getLetter());

        //assertNull(game.removeFromHand(index));



    }
    @Test
    void endConditionIsMet() {
        ScrabbleGame game = new ScrabbleGame();


        game.getBag().grabPieces(84);

        for (int i = 0; i< ScrabbleGame.HAND_SIZE;i++ ){
            game.getPlayer1Hand().removePiece(0);
            game.getPlayer2Hand().removePiece(0);
    }
        assertTrue(game.getPlayer1Hand().getHandPieces().isEmpty());
        assertTrue(game.getPlayer2Hand().getHandPieces().isEmpty());

        int x = game.getPlayer1Hand().sizeOfHand();
        int y = game.getBag().numberOfRemainingPieces();
        int x2 =  game.getPlayer2Hand().sizeOfHand();


        assertTrue((x <7) | (x2 <7) && (y <=0));

        assertEquals(game.endConditionIsMet(),true);

    }


 */