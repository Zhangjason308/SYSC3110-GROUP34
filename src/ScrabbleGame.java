import java.util.ArrayList;
import java.util.List;

//TicTacToeModel
public class ScrabbleGame {


    public static final int SIZE = 15;
    public static final int HAND_SIZE = 7;
    public static final boolean player1 = true;
    public static final boolean player2 = false;

    public enum Status {PLAYER_1_WON, PLAYER_2_WON, TIE, UNDECIDED};
    int player1Score;
    int player2Score;  //p possibly want to add a player class since each player has a hand and a score, might make it more complicated tho, idk yet
    Hand player1Hand;
    Hand player2Hand;
    Board scrabbleBoard;
    Bag bag;
    boolean turn; // false meaning its player2's turn
    private Status status;
    private List<ScrabbleView> views; //always use a list

    private Piece selectedPiece;

    public ScrabbleGame() {
        // initializing game elements
        status = Status.UNDECIDED;
        player1Score = 0;
        player2Score = 0;
        bag = new Bag();
        player1Hand = new Hand(bag);
        player2Hand = new Hand(bag);
        scrabbleBoard = new Board();
        turn = player1;
        views = new ArrayList<>();
        selectedPiece = new Piece('a');
    }

    public void addScrabbleView(ScrabbleView v) {
        views.add(v);
    }

    private void changeTurn() {
        turn = !turn;
    }

    public Status getStatus() {return status;}

    public boolean getTurn() {return turn;}

    private void updateStatus() {
        return; //TODO
    }

    public Piece setSelectedPiece(Piece p){
        return selectedPiece = p;
    }

    public void play(int x, int y) {
        if(selectedPiece.getLetter() == ' '){  // need a way for the player to select a piece from their hand by clicking it
            System.out.println("no piece is selected to place at position: " + x + ", " + y);
            return;
        }
        else{
            if (scrabbleBoard.getPiece(x, y).getLetter() == ' '){  // places the selected piece on the board
                scrabbleBoard.placePiece(x, y, selectedPiece);
            }
        }

        updateStatus();

        for (ScrabbleView v: views) { v.update(x, y, selectedPiece);}

        changeTurn();
    }
}
