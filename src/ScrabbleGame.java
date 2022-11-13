import javax.swing.*;
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
    Hand player2Hand;//welp
    Board scrabbleBoard;
    Bag bag;
    boolean turn; // false meaning its player2's turn
    private Status status;
    private List<ScrabbleView> views; //always use a list

    private Piece selectedPiece;

    // tracker arrays to hold the elements that were recently selected, might not need selectedBoardButtons
    // i think selecting a handButton should add the button to this and then swap takes from this list, as does selecting a button on the baoard
    private ArrayList<JButton> selectedHandButtons;

    // selectedBoardButtons might be helpful in calculating score and finding if the word is valid
    private ArrayList<JButton> selectedBoardButtons;

    public ScrabbleGame() {
        // initializing game elements
        status = Status.UNDECIDED;
        player1Score = 1;
        player2Score = 1;
        bag = new Bag();
        player1Hand = new Hand();
        player2Hand = new Hand();
        player1Hand.addPieces(bag.grabPieces(7));
        player2Hand.addPieces(bag.grabPieces(7));
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

    /*
   Update the player score after play button is selected
   Update the hand with new pieces after the swap button is selected
   Update the player turn after skip, play, and swap are selected
    */
    public Piece setSelectedPiece(Piece p){
        return selectedPiece = p;
    }
    private void updateStatus() {
        if(endConditionIsMet()){
            status = calculateWinner();
        }else{
            status = Status.UNDECIDED;
        }
    }
    private boolean endConditionIsMet(){ //idk if this is what it should be, at very least its a placeholder
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

    public void skip(){
        changeTurn();
    }

    public void swap(){
        if(turn){
            swapLettersFromHand(player1Hand);
        }
        else{
            swapLettersFromHand(player2Hand);
        }
    }
    private void swapLettersFromHand(Hand hand){ // only to be called in the swap function
        ArrayList<Character> selectedHandButtonsAsChars = new ArrayList<>();
        for (JButton b : selectedHandButtons) {
            selectedHandButtonsAsChars.add(b.getText().charAt(0));
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        for (char c : selectedHandButtonsAsChars) {
            for (char c1 : hand.getHandAsChars()) {
                if(c == c1){
                    indexes.add(hand.getHandAsChars().indexOf(c));
                }
            }
        }
        for (int i : indexes) {
            hand.removePiece(i);
        }
    }

    public void play(int x, int y) {// dont think it needs the x and y
        if(selectedBoardButtons.isEmpty()){
            System.out.println("No selected Buttons");
        }
        else { // checking if valid word needs to be added and changeTurn is only updated if validWord
            for (JButton b : selectedBoardButtons) {
                String[] position = b.getActionCommand().split(" ");
                int q = Integer.parseInt(position[0]);
                int z = Integer.parseInt(position[1]);
                Hand playerHand = (turn)? player1Hand : player2Hand;
                InputData info = new InputData(q, z, playerHand.getPieceWithChar(b.getText().charAt(0)));
                scrabbleBoard.placePiece(info);
            }
        }
        updateStatus();

        // need to alter the game updating logic: didnt know exactly how this should be done
        for (ScrabbleView v: views) { v.updateHandFrame(x, y, selectedPiece);}
        changeTurn();

        // **************************** old code *****************************
            //for ()
               // x = selButtons.get(i).getActionCommand();
               // y = selButtons.get(i).getActionCommand();
               // scrabbleBoard.placePiece(x,y,Piece);
            //for ()

//        if(selectedPiece.getLetter() == ' '){  // need a way for the player to select a piece from their hand by clicking it
//            System.out.println("no piece is selected to place at position: " + x + ", " + y);
//            return;
//        }
//        else{
//            if (scrabbleBoard.getPiece(x, y).getLetter() == ' '){  // places the selected piece on the board
//                //scrabbleBoard.placePiece(x, y, selectedPiece);
//            }


        //updateStatus();

        //for (ScrabbleView v: views) { v.updateHandFrame(x, y, selectedPiece);}
        //for (ScrabbleView v: views) { v.updateInfoPanel(player1Score, player2Score, bag);}

        //changeTurn();
    }
}


