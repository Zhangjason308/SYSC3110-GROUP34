import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbleGameTest {


    @Test
    void placeFirstWord() { //Makes sure first word has 1 piece in the center

    }
    @Test
    void createHorizontalWord() { //A horizontal word is valid

    }
    @Test
    void createVerticalWord() { //Vertical word is valid

    }
    @Test
    void placeScatteredLetters() { //Make sure you can only place letters that are connected to each other linearly and at one starting point

    }

    @Test
    void swapWorks() { //Allows a swap of letters in the hand

    }

    @Test
    void swapDoesntWork() { //Swapped pieces you don't have in your hand

    }

    @Test
    void placePieceThatMakesaHorizontalandVerticalWord() { //Place a piece that makes a horizontal and vertical word

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
    void cantMakeAnyMoreWords()  { //Board can't formulate any more words

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
    }

    @Test
    void calculateWinner(){

    }
    @Test
    void swapLettersFromHand(){

    }
    @Test
    void removeFromHand(){ // test to ensure the correct hand is used

    }
    @Test
    void endConditionIsMet(){



    }
}