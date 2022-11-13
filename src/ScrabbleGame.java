import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ScrabbleGame {


    public static final int SIZE = 15;
    public static final int HAND_SIZE = 7;
    public static final boolean player1 = true;
    public static final boolean player2 = false;

    public enum Status {PLAYER_1_WON, PLAYER_2_WON, TIE, UNDECIDED}

    private int player1Score;
    private int player2Score;
    private Hand player1Hand;
    private Hand player2Hand;
    private Board scrabbleBoard;
    private Bag bag;
    private boolean turn; // false meaning its player2's turn
    private Status status;
    private List<ScrabbleView> views; //always use a list

    public ScrabbleGame() {
        // initializing game elements
        status = Status.UNDECIDED;
        player1Score = 0;
        player2Score = 0;
        bag = new Bag();
        player1Hand = new Hand();
        player2Hand = new Hand();
        player1Hand.setHandPieces(bag.grabPieces(7));
        player2Hand.setHandPieces(bag.grabPieces(7));
        scrabbleBoard = new Board();
        turn = player1;
        views = new ArrayList<>();
    }

    public void addScrabbleView(ScrabbleView v) {
        views.add(v);
    }

    private void changeTurn() {
        if(turn == player1){
            turn = player2;
        }
        else {turn = player1;}
    }

    public Status getStatus() {
        return status;
    }

    public boolean getTurn() {
        return turn;
    }

    public int getPlayer1Score(){
        return player1Score;
    }
    public int getPlayer2Score(){
        return player2Score;
    }

    public Bag getBag() {
        return bag;
    }

    public Hand getPlayer1Hand(){
        return player1Hand;
    }
    public Hand getPlayer2Hand(){
        return player2Hand;
    }

    public Board getBoard(){
        return scrabbleBoard;
    }

    public List<ScrabbleView> getViews(){
        return views;
    }

    public void addScore(int score){
        if(turn){
            player1Score += score;
        }
        else{
            player2Score += score;
        }
    }

    public void updateViews(){
        for (ScrabbleView v : views) {
            v.update(this);
        }
    }

    private void updateStatus() {
        if(endConditionIsMet()){
            status = calculateWinner();
        }else{
            status = Status.UNDECIDED;
        }
    }
    private boolean endConditionIsMet(){
        if((player1Hand.sizeOfHand() < 7 || player2Hand.sizeOfHand() < 7) && bag.numberOfRemainingPieces() <= 0){
            return true;
        }
        return false;
    }

    private Status calculateWinner(){
        if(player1Score == player2Score){
            return Status.TIE;
        }
        return (player1Score > player2Score)? Status.PLAYER_1_WON: Status.PLAYER_2_WON;
    }

    public Piece removeFromHand(int index){
        if(turn){
            return player1Hand.removePiece(index);
        }
        else{
            return player2Hand.removePiece(index);
        }
    }
    public Piece removeFromBoard(int x, int y){
        return scrabbleBoard.removePiece(x, y);
    }

    public void skip(){
        System.out.println("skip was pressed");
        changeTurn();
        updateViews();
    }

    public void swap(){
        System.out.println("swap was pressed");
        if(turn){
            swapLettersFromHand(player1Hand);
        }
        else{
            swapLettersFromHand(player2Hand);
        }
        changeTurn();
        updateViews();
    }
    private void swapLettersFromHand(Hand hand){ // only to be called in the swap function
        hand.addPieces(bag.grabPieces(HAND_SIZE - hand.sizeOfHand())); // gets rid of pieces doesn't add them to bag
    }

    public void play(ArrayList<SelectionData> placedPieces) {

        for (SelectionData sd : placedPieces) {
            getBoard().placePiece(sd);
        }
        changeTurn();
        updateViews();

        System.out.println("play was pressed");
    }
}