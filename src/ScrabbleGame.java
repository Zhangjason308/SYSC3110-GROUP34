import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ScrabbleGame {


    public static final int SIZE = 15;
    public static final int HAND_SIZE = 7;
    public static final int BOARD_MIDDLE = 7;
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

    public void changeTurn() {
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

    public void updateStatus() {
        if(endConditionIsMet()){
            status = calculateWinner();
        }else{
            status = Status.UNDECIDED;
        }
    }
    public boolean endConditionIsMet(){
        if((player1Hand.sizeOfHand() < 7 || player2Hand.sizeOfHand() < 7) && bag.numberOfRemainingPieces() <= 0){
            return true;
        }
        return false;
    }

    public Status calculateWinner(){
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
    } // comment
    public void placePiece(SelectionData data){
        scrabbleBoard.placePiece(data);
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
            refillHand(player1Hand);
        }
        else{
            refillHand(player2Hand);
        }
        changeTurn();
        updateViews();
    }
    public void refillHand(Hand hand){ // only to be called in the swap function
        hand.addPieces(bag.grabPieces(HAND_SIZE - hand.sizeOfHand())); // gets rid of pieces doesn't add them to bag
    }

    public boolean firstTurnPlayedCenter(){
        if(scrabbleBoard.getPiece(BOARD_MIDDLE, BOARD_MIDDLE).getLetter() != ' '){
            return true;
        }
        return false;
    }

    public boolean surroundingPiecesArentEmpty(ArrayList<SelectionData> sd){ // doesnt quite work, will always be true beacause letters they place count as a surrounding piece to otehr letter they place in that turn
        boolean hasPieceBeside = false;
        for (SelectionData data : sd) {
            if(scrabbleBoard.getPiece(data.getX()-1, data.getY()).getLetter() != ' ' || scrabbleBoard.getPiece(data.getX()+1, data.getY()).getLetter() != ' '){
                return true;
            }
            if(scrabbleBoard.getPiece(data.getX(), data.getY()-1).getLetter() != ' ' || scrabbleBoard.getPiece(data.getX(), data.getY()+1).getLetter() != ' '){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getBranchWords( ArrayList<SelectionData> selectedBoardButtons) {

        ArrayList<String> branchWords = new ArrayList<>(ScrabbleGame.HAND_SIZE);

        if (isXAligned(selectedBoardButtons)) {
            for (SelectionData sd : selectedBoardButtons) {
                StringBuilder word = new StringBuilder();

                int x = sd.getX();
                int y = sd.getY();
                Piece tracker = sd.getPiece();
                while (tracker.getLetter() != ' ') {
                    if (y==0) {
                        y--;
                        break;
                    }
                    //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
                    y--;
                    tracker =getBoard().getPiece(x, y);
                }// tracker has position of first letter in branch
                y++;
                tracker = getBoard().getPiece(x, y);

                while (tracker.getLetter() != ' ') {
                    if (y == 14) {
                        break;
                    }
                    //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
                    tracker = getBoard().getPiece(x, y);
                    if(tracker.getLetter() != ' '){
                        word.append(tracker.getLetter());
                    }
                    y++;
                }
                branchWords.add(word.toString());
            }
        } else {
            // foreach selData d in selectedBoardButtons in x dir
            // get vertical word by iterating all the way up
            // Create a String word that iterates down until theres no more letters -> This is the main word
            // For every selectedBoardButton added, iterate left until theres no more words, then iterate all the way right -> these are the branch words
            for (SelectionData sd : selectedBoardButtons) {
                StringBuilder word = new StringBuilder();

                int x = sd.getX();
                int y = sd.getY();
                Piece tracker = sd.getPiece();
                while (tracker.getLetter() != ' ') {
                    if (x==0) {
                        x--;
                        break;
                    }
                    //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
                    x--;
                    tracker = getBoard().getPiece(x, y);
                }// tracker has position of first letter in branch
                x++;
                tracker = getBoard().getPiece(x, y);

                while (tracker.getLetter() != ' ') {
                    if (x == 14) {
                        break;
                    }
                    //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
                    tracker = getBoard().getPiece(x, y);
                    if(tracker.getLetter() != ' '){
                        word.append(tracker.getLetter());
                    }
                    x++;
                }
                branchWords.add(word.toString());
            }
            //for every word in branchWords and word, check if they are all valid words in the dictionary, if Yes, then call calculateScore()
        }
        int index = 0;
        ArrayList<String> toReturn = new ArrayList<>();
        for (String s : branchWords) {
            String str = s.trim();
            if(str.length() != 0 && str.length() != 1){
                toReturn.add(str);
            }
        }
        System.out.println("Branch words length: " + toReturn.size());
        return toReturn;
    }

    public String getWord( ArrayList<SelectionData> selectedBoardButtons){

        if(selectedBoardButtons.isEmpty()){
            System.out.println("in getWord Function: selectedBoardButtons is empty");
            return "";
        }

        StringBuilder word = new StringBuilder();

        int x = selectedBoardButtons.get(0).getX();
        int y = selectedBoardButtons.get(0).getY();
        //Piece tracker = model.getBoard().getPiece(x,y);
        Piece tracker = selectedBoardButtons.get(0).getPiece();
        if (isXAligned(selectedBoardButtons)) {
            while (tracker.getLetter() != ' ') {

                if(x == 0){
                    x--;
                    break;
                }
                x--;
                tracker = getBoard().getPiece(x, y);
            }// tracker has position of first letter in branch
            x++;
            tracker = getBoard().getPiece(x, y);
            System.out.println(tracker.getLetter());

            while (tracker.getLetter() != ' ') {

                tracker = getBoard().getPiece(x, y);
                System.out.println(word);
                word.append(tracker.getLetter());
                if(x == 14){
                    break;
                }
                x++;
            }

        } else {

            while (tracker.getLetter() != ' ') {
                //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
                if(y == 0){
                    y--;
                    break;
                }
                y--;
                tracker = getBoard().getPiece(x, y);
            }// tracker has position of first letter in branch
            y++;
            tracker = getBoard().getPiece(x, y);

            while (tracker.getLetter() != ' ') {

                //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
                tracker = getBoard().getPiece(x, y);
                word.append(tracker.getLetter());
                if(y == 14){
                    break;
                }
                y++;
            }
        }
        String checkSizeStr = word.toString().trim();
        if(checkSizeStr.length() == 1 && !getBranchWords(selectedBoardButtons).isEmpty()){
            return "";
        }
        return checkSizeStr;
    }


    public boolean isValidWord(String word) throws IOException {  // this function works as is

        Path path = Path.of("src/Dictionary.txt");
        String dictionary = Files.readString(path);
        String[] temp = dictionary.split("\n");

        for (String s : temp) {
            String str = s.trim();
            if(str.compareTo(word) == 0){
                return true;
            }
        }
        return false;
    }

    public boolean[] lettersAreInLine( ArrayList<SelectionData> selectedBoardButtons) {

        boolean xAligned = true;
        boolean yAligned = true;

        SelectionData previous = null;

        for (SelectionData sd : selectedBoardButtons) {
            if (previous == null) {
                previous = sd;
                continue;
            }
            if (sd.getX() != previous.getX()) {
                yAligned = false;
            }
            if (sd.getY() != previous.getY()) {
                xAligned = false;
            }
            previous = sd;
        }
        boolean[] booleans = new boolean[2];
        booleans[0] = xAligned;
        booleans[1] = yAligned;
        return booleans;
    } // comment
    public boolean isXAligned(ArrayList<SelectionData> selectedBoardButtons){
        boolean[] bool = lettersAreInLine(selectedBoardButtons);
        return bool[0];
    }
    public boolean isYAligned(ArrayList<SelectionData> selectedBoardButtons){
        boolean[] bool = lettersAreInLine(selectedBoardButtons);
        return bool[1];
    }
    public void play() {
        if(getTurn()){
            refillHand(player1Hand);
        }
        else {refillHand(player2Hand);}
        changeTurn();
        updateViews();

        System.out.println("play was pressed");
    }
}