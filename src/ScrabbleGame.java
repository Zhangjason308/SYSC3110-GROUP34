
import javax.swing.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ScrabbleGame implements Serializable{//


    public static final int HAND_SIZE = 7;
    public static final int BOARD_MIDDLE = 7;
    public static final boolean player1 = true;
    public static final boolean player2 = false;
    public static final char BLANK = '!';
    public enum Status {PLAYER_1_WON, PLAYER_2_WON, TIE, UNDECIDED;}
    private int player1Score;
    private int player2Score;
    private Hand player1Hand;
    private Hand player2Hand;
    private Board scrabbleBoard;
    private Bag bag;
    private boolean turn; // false meaning its player2's turn
    private Status status;
    private List<ScrabbleView> views; //always use a list
    private ArrayList<ArrayList<Character>> list;
    SelectionController selectionController;
    private ArrayList<SavedGameState> storedTurns;
    private int turnNumber;
    int selectedBoard;
    boolean player2Selected;

    public ScrabbleGame(int selectedBoard, boolean player2Selected) {
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

        selectionController = new SelectionController(this);

        list = new ArrayList<ArrayList<Character>>();

        storedTurns = new ArrayList<>();
        turnNumber = 0;

        this.selectedBoard = selectedBoard;
        this.player2Selected = player2Selected;
    }

    public void addScrabbleView(ScrabbleView v) {
        views.add(v);
    }

    public void selectHandButton(int handIndex, String buttonText){
        selectionController.selectHandButton(handIndex, buttonText);
        updateViews();
    }

    public void selectBoardButton(int x, int y, String buttonText){
        selectionController.selectBoardButton(x, y, buttonText);
        updateViews();
    }

    public void changeTurn() {

        if(turnNumber == storedTurns.size()){
            SavedGameState current = new SavedGameState(this);
            storedTurns.add(turnNumber,current);

        }
        else{
            storedTurns.set(turnNumber, new SavedGameState(this));
        }
        if(status != Status.UNDECIDED){
            endConditionIsMet();
            JOptionPane.showMessageDialog(null, calculateWinner() + " wins");
            player1Hand.pieces.clear();
            player2Hand.pieces.clear();
        }
        if (turn == player1) {
            turn = player2;
        } else {
            turn = player1;
        }
        turnNumber++;
    }

    public void undo() {
        if(turnNumber == 0){
            JOptionPane.showMessageDialog(null, "Nothing To Undo");
            return;
        }
        turnNumber = turnNumber-1;
        this.setGameContents(storedTurns.get(turnNumber-1));

        BoardPanel.resetDisabledButtons();
        reDisableButtons();
        updateViews();
    }
    public void redo() {
        if(turnNumber == storedTurns.size()){
            JOptionPane.showMessageDialog(null, "Nothing To Redo");
            return;
        }

        this.setGameContents(storedTurns.get(turnNumber));
        BoardPanel.resetDisabledButtons();
        reDisableButtons();
        turnNumber++;
        updateViews();
    }

    public Status getStatus() {
        return status;
    }

    public boolean getTurn() {
        return turn;
    }

    public Hand getCurrentHand(){
        return turn ? player1Hand : player2Hand;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public Bag getBag() {
        return bag;
    }

    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    public Board getBoard() {
        return scrabbleBoard;
    }

    public ArrayList<SavedGameState> getStoredTurns() {
        return storedTurns;
    }

    public SelectionController getSelectionController() {
        return this.selectionController;
    }

    public void addScore(int score) {
        if (turn) {
            player1Score += score;
        } else {
            player2Score += score;
        }
    }

    public void updateViews() {
        for (ScrabbleView v : views) {
            v.update(this);
        }
    }

    public void updateStatus() {
        if (endConditionIsMet()) {
            status = calculateWinner();
        } else {
            status = Status.UNDECIDED;
        }
    }

    public boolean endConditionIsMet() {
        if ((player1Hand.sizeOfHand() < 7 || player2Hand.sizeOfHand() < 7) && bag.numberOfRemainingPieces() <= 0) {
            return true;
        }
        return false;
    }


    public Status calculateWinner() {
        if (player1Score == player2Score) {
            return Status.TIE;
        }
        return (player1Score > player2Score) ? Status.PLAYER_1_WON : Status.PLAYER_2_WON;
    }

    public Piece removeFromHand(int index) {
        if (turn) {
            return player1Hand.removePiece(index);
        } else {
            return player2Hand.removePiece(index);
        }
    }

    public Piece removeFromBoard(int x, int y) {
        return scrabbleBoard.removePiece(x, y);
    }

    public void skip() {
        selectionController.revertSelections();
        changeTurn();
        updateViews();
    }

    public void swap() {
        refillHand(getCurrentHand());
        selectionController.clearSelectionButtons();
        changeTurn();
        updateViews();
    }

    public void refillHand(Hand hand) { // only to be called in the swap function
        hand.addPieces(bag.grabPieces(HAND_SIZE - hand.sizeOfHand())); // gets rid of pieces doesn't add them to bag
    }

    public boolean firstTurnPlayedCenter() {
        if (scrabbleBoard.getPiece(BOARD_MIDDLE, BOARD_MIDDLE).getLetter() != ' ') {
            return true;
        }
        return false;
    }

    public ArrayList<String> getBranchWords(ArrayList<SelectionData> selectedBoardButtons) {

        ArrayList<String> branchWords = new ArrayList<>(ScrabbleGame.HAND_SIZE);

        if (selectionController.isXAligned()) {
            for (SelectionData sd : selectedBoardButtons) {
                StringBuilder word = new StringBuilder();

                int x = sd.getX();
                int y = sd.getY();
                Piece tracker = sd.getPiece();
                while (tracker.getLetter() != ' ') {
                    if (y == 0) {
                        y--;
                        break;
                    }
                    y--;
                    tracker = getBoard().getPiece(x, y);
                }// tracker has position of first letter in branch
                y++;
                tracker = getBoard().getPiece(x, y);

                while (tracker.getLetter() != ' ') {
                    if (y == 14) {
                        break;
                    }
                    tracker = getBoard().getPiece(x, y);
                    if (tracker.getLetter() != ' ') {
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
                    if (x == 0) {
                        x--;
                        break;
                    }
                    x--;
                    tracker = getBoard().getPiece(x, y);
                }// tracker has position of first letter in branch
                x++;
                tracker = getBoard().getPiece(x, y);

                while (tracker.getLetter() != ' ') {
                    if (x == 14) {
                        break;
                    }
                    tracker = getBoard().getPiece(x, y);
                    if (tracker.getLetter() != ' ') {
                        word.append(tracker.getLetter());
                    }
                    x++;
                }
                branchWords.add(word.toString());
            }
            //for every word in branchWords and word, check if they are all valid words in the dictionary, if Yes, then call calculateScore()
        }
        ArrayList<String> toReturn = new ArrayList<>();
        for (String s : branchWords) {
            String str = s.trim();
            if (str.length() != 0 && str.length() != 1) {
                toReturn.add(str);
            }
        }
        return toReturn;
    }

    public String getWord(ArrayList<SelectionData> selectedBoardButtons) {

        if (selectedBoardButtons.isEmpty()) {
            return "";
        }

        StringBuilder word = new StringBuilder();

        int x = selectedBoardButtons.get(0).getX();
        int y = selectedBoardButtons.get(0).getY();

        for (int i = 0; i < selectedBoardButtons.size(); i++) {
            for (int j = 0; j < BoardPanel.BOARD_TYPES[BoardPanel.getBoardNumber()][0][0].length; j++) {

                if (selectedBoardButtons.get(i).getX() == BoardPanel.BOARD_TYPES[BoardPanel.getBoardNumber()][0][0][j] && selectedBoardButtons.get(i).getY() == BoardPanel.BOARD_TYPES[BoardPanel.getBoardNumber()][0][1][j]) {
                    selectionController.addToBlueButtons(selectedBoardButtons.get(i));
                    for (int z = 0; z < selectionController.getSpecialBlueButtons().size(); z++) {
                    }
                }
            }

            for (int j = 0; j < BoardPanel.BOARD_TYPES[BoardPanel.getBoardNumber()][1][0].length; j++) {
                if (selectedBoardButtons.get(i).getX() == BoardPanel.BOARD_TYPES[BoardPanel.getBoardNumber()][1][0][j] && selectedBoardButtons.get(i).getY() == BoardPanel.BOARD_TYPES[BoardPanel.getBoardNumber()][1][1][j]) {
                    selectionController.addToRedButtons(selectedBoardButtons.get(i));
                    for (int z = 0; z < selectionController.getSpecialRedButtons().size(); z++) {
                    }
                }
            }

        }

        Piece tracker = selectedBoardButtons.get(0).getPiece();
        if (selectionController.isXAligned()) {
            while (tracker.getLetter() != ' ') {

                if (x == 0) {
                    x--;
                    break;
                }
                x--;
                tracker = getBoard().getPiece(x, y);
            }// tracker has position of first letter in branch
            x++;
            tracker = getBoard().getPiece(x, y);

            while (tracker.getLetter() != ' ') {

                tracker = getBoard().getPiece(x, y);
                word.append(tracker.getLetter());
                if (x == 14) {
                    break;
                }
                x++;
            }

        } else {

            while (tracker.getLetter() != ' ') {
                if (y == 0) {
                    y--;
                    break;
                }
                y--;
                tracker = getBoard().getPiece(x, y);
            }// tracker has position of first letter in branch
            y++;
            tracker = getBoard().getPiece(x, y);

            while (tracker.getLetter() != ' ') {

                tracker = getBoard().getPiece(x, y);
                word.append(tracker.getLetter());
                if (y == 14) {
                    break;
                }
                y++;
            }
        }
        String checkSizeStr = word.toString().trim();
        if (checkSizeStr.length() == 1 && !getBranchWords(selectedBoardButtons).isEmpty()) {
            return "";
        }
        return checkSizeStr;
    }

    public String[] getDictionary() throws IOException {
        Path path = Path.of("src/Dictionary.txt");
        String dictionary = Files.readString(path);
        String[] temp = dictionary.split("\n");

        return temp;
    }


    public boolean isValidWord(String word){  // this function works as is

        try {
            String[] dictionary = getDictionary();
            for (String s : dictionary) {
                String str = s.trim();
                if (str.compareTo(word) == 0) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int calculateScore(String s) {
        char[] arr = s.toCharArray();
        int score = 0;
        for (char c : arr) {
            score += Piece.pieceMap.get(c);
        }
        for (SelectionData sd : selectionController.getSpecialBlueButtons()) {
            score += sd.getPiece().pieceMap.get(sd.getPiece().getLetter()) * 3;
            score -= sd.getPiece().pieceMap.get(sd.getPiece().getLetter());
        }

        for (SelectionData sd : selectionController.getSpecialRedButtons()) {
            score += sd.getPiece().pieceMap.get(sd.getPiece().getLetter()) * 2;
            score -= sd.getPiece().pieceMap.get(sd.getPiece().getLetter());
        }
        selectionController.clearSpecialButtons();
        return score;
    }

    public int getPieceAround(int i, int j, int iNum, int jNum, Piece p) {
        SelectionData sd = new SelectionData(j + jNum, i+iNum, p);
        selectionController.getSelectedBoardButtons().add(sd);
        scrabbleBoard.placePiece(sd);
        if (playWordOnBoard(selectionController.getSelectedBoardButtons())) {
            player2Hand.getHandPieces().remove(p);
            refillHand(getCurrentHand());
            changeTurn();
            selectionController.clearSelectionButtons();
            return 0;
        } else {
            scrabbleBoard.removePiece(j + jNum, i+iNum);
            selectionController.getSelectedBoardButtons().clear();
            return 1;
        }


    }

    public void play(){

        if(turn){ // player 1 turn
            if(playWordOnBoard(selectionController.getSelectedBoardButtons())){
                selectionController.clearSelectionButtons();
                refillHand(getCurrentHand());
                changeTurn();
            }
            else{
                selectionController.revertSelections();
            }
        }
        else {
            if(player2Selected){
                if(playWordOnBoard(selectionController.getSelectedBoardButtons())){
                    selectionController.clearSelectionButtons();
                    refillHand(getCurrentHand());
                    changeTurn();
                }
                else{
                    selectionController.revertSelections();
                }
            }else {
                boolean aiCantPlay = true;
                istrueword:
                for (int i = 0; i < Board.SIZE; i++) {
                    for (int j = 0; j < Board.SIZE; j++) {
                        if (scrabbleBoard.getPiece(j, i).getLetter() != ' ') {
                            selectionController.getSelectedBoardButtons().clear();
                            for (int k = 0; k < ScrabbleGame.HAND_SIZE; k++) {
                                Piece p = player2Hand.getHandPieces().get(k);
                                if (scrabbleBoard.getPiece(j - 1, i).getLetter() == ' ') {
                                    if(getPieceAround(i, j, 0, -1, p) == 0){
                                        aiCantPlay = false;
                                        break istrueword;
                                    }
                                    else {
                                        continue;
                                    }

                                }


                                else if (scrabbleBoard.getPiece(j + 1, i).getLetter() == ' ') {
                                    if(getPieceAround(i, j, 0, 1, p) == 0){
                                        aiCantPlay = false;
                                        break istrueword;
                                    }
                                    else {
                                        continue;
                                    }

                                }
                                else if (scrabbleBoard.getPiece(j  , i+1).getLetter() == ' ') {
                                    if(getPieceAround(i, j, 1, 0, p) == 0){
                                        aiCantPlay = false;
                                        break istrueword;
                                    }
                                    else {
                                        continue;
                                    }

                                }
                                else if (scrabbleBoard.getPiece(j , i-1).getLetter() == ' ') {
                                    if(getPieceAround(i, j, -1, 0, p) == 0){
                                        aiCantPlay = false;
                                        break istrueword;
                                    }
                                    else {
                                        continue;
                                    }

                                }
                            }
                        }
                    }
                }
                if (aiCantPlay) {
                    skip();
                }
                selectionController.revertSelections();
            }
        }
        updateViews();
    }

    public Boolean playWordOnBoard(ArrayList<SelectionData> selectedBoardButtons) {
        if (firstTurnPlayedCenter() && wordisConnected()) {
            if (selectionController.isXAligned() || selectionController.isYAligned()) { // all x or y indexes are same
                String word = getWord(selectedBoardButtons); //gets the word (including the letters in potential spaces)
                if(word.length() == 0){
                   return false;
                }
                ArrayList<String> branches = getBranchWords(selectedBoardButtons);
                int score = 0;
                    if (isValidWord(word)) {
                        score += calculateScore(word);
                        for (String s : branches) {
                            if (isValidWord(s)) {
                                score += calculateScore(s);
                            } else {
                                selectionController.revertSelections();
                                score = 0;
                                updateViews();
                                return false;
                            }
                        }
                    } else {
                        selectionController.revertSelections();
                        updateViews();
                    }
                if (score == 0) {
                    selectionController.revertSelections();
                    updateViews();
                } else {
                    BoardPanel.disableButtons(selectedBoardButtons);
                    addScore(score);
                    updateViews();
                    return true;
                }
            } else {
                selectionController.revertSelections();
                updateViews();
                return false;
            }

        }
        updateViews();
        return false;
    }

    public void saveSerializable (String fileName){

        try {
            OutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream obOut = new ObjectOutputStream(outputStream);

            obOut.writeObject(this);

            outputStream.flush();
            outputStream.close();
            obOut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setGameContents(ScrabbleGame game){
        this.turn = game.getTurn();
        this.player1Score = game.getPlayer1Score();
        this.player2Score = game.getPlayer2Score();

        this.player1Hand = game.getPlayer1Hand();
        this.player2Hand = game.getPlayer2Hand();

        this.scrabbleBoard = game.getBoard();
        this.bag = game.getBag();

        this.selectionController = game.getSelectionController();
    }
    public void setGameContents(SavedGameState game){
        this.turn = game.getTurn();
        this.player1Score = game.getPlayer1Score();
        this.player2Score = game.getPlayer2Score();

        this.player1Hand = game.getPlayer1Hand();
        this.player2Hand = game.getPlayer2Hand();

        this.scrabbleBoard = game.getScrabbleBoard();
        this.bag = game.getBag();

        this.selectionController = game.getSelectionController();
        this.selectionController.clearSelectionButtons();
    }

    private void reDisableButtons(){
        ArrayList<SelectionData> placedPieces = new ArrayList<>();
        for (int i = 0; i < Board.SIZE; i++) { // y
            for (int j = 0; j < Board.SIZE; j++) { // x
                if (scrabbleBoard.getPiece(j, i).getLetter() != ' ') {
                    placedPieces.add(new SelectionData(j, i, scrabbleBoard.getPiece(j, i)));
                }
            }
        }
        BoardPanel.disableButtons(placedPieces);
    }

    public void setGameFromLoad(ScrabbleGame game){
        BoardPanel.resetDisabledButtons();
        setGameContents(game);
        reDisableButtons();
        updateViews();
    }

    static public ScrabbleGame importGameSerializable(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            ScrabbleGame game = (ScrabbleGame) ois.readObject();
            ois.close();
            fileIn.close();
            return game;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int wordIsConnectedSubMethod(SelectionData d, int xInt, int yInt){
        if (scrabbleBoard.getPiece(d.getX() + xInt, d.getY()+yInt).getLetter() != ' ') {
            boolean selectionDataThing = false;
            for (SelectionData a : selectionController.getSelectedBoardButtons()) {
                if (d.getX() + xInt == a.getX() && d.getY()+yInt == a.getY()) {
                    selectionDataThing = true;
                    break;
                }
            }
            if (!selectionDataThing) {
                return 1;
            }

        }
        return 0;
    }


    private boolean wordisConnected() {
        if(!turn){
           return true;
        }
        if(turnNumber == 0){
            return true;
        }



        if (selectionController.isXAligned() || selectionController.isYAligned()) {
            //int values[] = new int[selectionController.getSelectedBoardButtons().size()];
            for (SelectionData d : selectionController.getSelectedBoardButtons()){
                if(wordIsConnectedSubMethod(d, 1, 0) == 1){
                    return true;
                }
                if(wordIsConnectedSubMethod(d, -1, 0) == 1){
                    return true;
                }
                if(wordIsConnectedSubMethod(d, 0, 1) == 1){
                    return true;
                }
                if(wordIsConnectedSubMethod(d, 0, -1) == 1){
                    return true;
                }
            }



        }
        return false;
    }



}
