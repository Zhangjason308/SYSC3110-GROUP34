import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SavedGameState implements Serializable {

    private int player1Score;
    private int player2Score;
    private Hand player1Hand;
    private Hand player2Hand;
    private Board scrabbleBoard;
    private Bag bag;
    private boolean turn;
    SelectionController selectionController;

    public SavedGameState(ScrabbleGame game){
        player1Score = game.getPlayer1Score();
        player2Score = game.getPlayer2Score();
        player1Hand = game.getPlayer1Hand();
        player2Hand = game.getPlayer2Hand();
        scrabbleBoard = game.getBoard();
        bag = game.getBag();
        turn = game.getTurn();
        selectionController = game.getSelectionController();
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    public Board getScrabbleBoard() {
        return scrabbleBoard;
    }

    public Bag getBag() {
        return bag;
    }

    public boolean getTurn() {
        return turn;
    }

    public SelectionController getSelectionController() {
        return selectionController;
    }

}
