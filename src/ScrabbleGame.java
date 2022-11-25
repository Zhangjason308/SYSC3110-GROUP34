
import javax.swing.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class ScrabbleGame {


    public static final int SIZE = 15;
    public static final int HAND_SIZE = 7;
    public static final int BOARD_MIDDLE = 7;
    public static final boolean player1 = true;
    public static final boolean player2 = false;

    public ArrayList<SelectionData> getSelectedBoardButtons() {
        return selectedBoardButtons;
    }

    public ArrayList<SelectionData> getSelectedHandButtons() {
        return selectedHandButtons;
    }

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

    private ArrayList<SelectionData> specialBlueButtons;
    private ArrayList<SelectionData> specialRedButtons;
    private ArrayList<SelectionData> selectedBoardButtons;
    private ArrayList<SelectionData> selectedHandButtons;
    private ArrayList<SelectionData> specialButtons;

    private ArrayList<ArrayList<Character>> list;

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
        specialBlueButtons = new ArrayList<>();
        specialRedButtons = new ArrayList<>();
        selectedBoardButtons = new ArrayList<>();
        selectedHandButtons = new ArrayList<>();
        specialButtons = new ArrayList<>();
        list = new ArrayList<ArrayList<Character>>();
    }

    public void addScrabbleView(ScrabbleView v) {
        views.add(v);
    }

    public void changeTurn() {
        if (turn == player1) {
            turn = player2;
        } else {
            turn = player1;
        }
    }

    public Status getStatus() {
        return status;
    }

    public boolean getTurn() {
        return turn;
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

    public List<ScrabbleView> getViews() {
        return views;
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
    } // comment

    public void placePiece(SelectionData data) {
        scrabbleBoard.placePiece(data);
    }

    public Piece removeFromBoard(int x, int y) {
        return scrabbleBoard.removePiece(x, y);
    }

    public void skip() {
        System.out.println("skip was pressed");
        changeTurn();
        updateViews();
    }

    public void swap() {
        System.out.println("swap was pressed");
        if (turn) {
            refillHand(player1Hand);
        } else {
            refillHand(player2Hand);
        }
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

    public boolean surroundingPiecesArentEmpty(ArrayList<SelectionData> sd) { // doesnt quite work, will always be true beacause letters they place count as a surrounding piece to otehr letter they place in that turn
        boolean hasPieceBeside = false;
        for (SelectionData data : sd) {
            if (scrabbleBoard.getPiece(data.getX() - 1, data.getY()).getLetter() != ' ' || scrabbleBoard.getPiece(data.getX() + 1, data.getY()).getLetter() != ' ') {
                return true;
            }
            if (scrabbleBoard.getPiece(data.getX(), data.getY() - 1).getLetter() != ' ' || scrabbleBoard.getPiece(data.getX(), data.getY() + 1).getLetter() != ' ') {
                return true;
            }
        }
        return false;
    }
    public void revertSelections() {

        for (SelectionData sd : selectedBoardButtons) {
            if (getTurn()) {
                getPlayer1Hand().addPiece(sd.getPiece());
            } else {
                getPlayer2Hand().addPiece(sd.getPiece());
            }
            removeFromBoard(sd.getX(), sd.getY());
        }
        selectedBoardButtons = new ArrayList<>();
        for (SelectionData sd : selectedHandButtons) {
            if (getTurn()) {
                getPlayer1Hand().addPiece(sd.getPiece());
            } else {
                getPlayer2Hand().addPiece(sd.getPiece());
            }
        }
    }

    public void clearSelections() {
        selectedBoardButtons.clear();
        selectedHandButtons.clear();
    }


    public ArrayList<String> getBranchWords(ArrayList<SelectionData> selectedBoardButtons) {

        ArrayList<String> branchWords = new ArrayList<>(ScrabbleGame.HAND_SIZE);

        if (isXAligned(selectedBoardButtons)) {
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
                    //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
                    y--;
                    tracker = getBoard().getPiece(x, y);
                }// tracker has position of first letter in branch
                y++;
                tracker = getBoard().getPiece(x, y);

                while (tracker.getLetter() != ' ') {
                    if (y == 14) {
                        break;
                    }
                    //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
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
                    if (tracker.getLetter() != ' ') {
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
            if (str.length() != 0 && str.length() != 1) {
                toReturn.add(str);
            }
        }
        System.out.println("Branch words length: " + toReturn.size());
        return toReturn;
    }

    public String getWord(ArrayList<SelectionData> selectedBoardButtons) {

        if (selectedBoardButtons.isEmpty()) {
            System.out.println("in getWord Function: selectedBoardButtons is empty");
            return "";
        }

        StringBuilder word = new StringBuilder();

        int x = selectedBoardButtons.get(0).getX();
        int y = selectedBoardButtons.get(0).getY();

        for (int i = 0; i < selectedBoardButtons.size(); i++) {
            for (int j = 0; j < BoardPanel.NUM_OF_BLUE_POSITIONS; j++) {

                if (selectedBoardButtons.get(i).getX() == BoardPanel.MULTIPLIER_POSITIONS_BLUE[0][j] && selectedBoardButtons.get(i).getY() == BoardPanel.MULTIPLIER_POSITIONS_BLUE[1][j]) {
                    specialBlueButtons.add(selectedBoardButtons.get(i));
                }
            }
            for (int j = 0; j < BoardPanel.NUM_OF_RED_POSITIONS; j++) {
                if (selectedBoardButtons.get(i).getX() == BoardPanel.MULTIPLIER_POSITIONS_RED[0][j] && selectedBoardButtons.get(i).getY() == BoardPanel.MULTIPLIER_POSITIONS_RED[1][j]) {
                    specialRedButtons.add(selectedBoardButtons.get(i));
                }
            }

        }
        //Piece tracker = model.getBoard().getPiece(x,y);
        Piece tracker = selectedBoardButtons.get(0).getPiece();
        if (isXAligned(selectedBoardButtons)) {
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
            System.out.println(tracker.getLetter());

            while (tracker.getLetter() != ' ') {

                tracker = getBoard().getPiece(x, y);
                System.out.println(word);
                word.append(tracker.getLetter());
                if (x == 14) {
                    break;
                }
                x++;
            }

        } else {

            while (tracker.getLetter() != ' ') {
                //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
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

                //tracker = new SelectionData(x, y - 1, model.getBoard().getPiece(x, y - 1));
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


    public boolean isValidWord(String word) throws IOException {  // this function works as is

        Path path = Path.of("src/Dictionary.txt");
        String dictionary = Files.readString(path);
        String[] temp = dictionary.split("\n");

        for (String s : temp) {
            String str = s.trim();
            if (str.compareTo(word) == 0) {
                return true;
            }
        }
        return false;
    }

    public int calculateScore(String s) {
        char[] arr = s.toCharArray();
        int score = 0;
        for (char c : arr) {
            score += Piece.pieceMap.get(c);
        }
        for (SelectionData sd : specialBlueButtons) {
            score += sd.getPiece().pieceMap.get(sd.getPiece().getLetter()) * 3;
            score -= sd.getPiece().pieceMap.get(sd.getPiece().getLetter());
        }

        for (SelectionData sd : specialRedButtons) {
            score += sd.getPiece().pieceMap.get(sd.getPiece().getLetter()) * 2;
            score -= sd.getPiece().pieceMap.get(sd.getPiece().getLetter());
        }
        specialBlueButtons.clear();
        specialRedButtons.clear();
        return score;
    }

    public boolean[] lettersAreInLine(ArrayList<SelectionData> selectedBoardButtons) {

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

    public boolean isXAligned(ArrayList<SelectionData> selectedBoardButtons) {
        boolean[] bool = lettersAreInLine(selectedBoardButtons);
        return bool[0];
    }

    public boolean isYAligned(ArrayList<SelectionData> selectedBoardButtons) {
        boolean[] bool = lettersAreInLine(selectedBoardButtons);
        return bool[1];
    }


    public ArrayList<ArrayList<Character>> getList() throws IOException {

        Path path = Path.of("src\\Dictionary.txt");
        String dictionary = Files.readString(path);
        String[] temp = dictionary.split("\n");


        for (String s : temp) {
            ArrayList<Character> tempChar = new ArrayList<Character>();
            for (int i = 0; i < s.length(); i++) {
                tempChar.add(s.charAt(i));
            }
            list.add(tempChar);
        }

        return list;
    }

    public void play() {
        if (getTurn()) {
            refillHand(player1Hand);
        } else {
            refillHand(player2Hand);
            //playBot();
        }
        changeTurn();
        updateViews();


        System.out.println("play was pressed");
    }

    public void playBot() {
    }
    public Boolean playWordOnBoard(ArrayList<SelectionData> selectedBoardButtons) {
        if (firstTurnPlayedCenter()) {
            if (isXAligned(selectedBoardButtons) || isYAligned(selectedBoardButtons)) { // all x or y indexes are same
                String word = getWord(selectedBoardButtons); //gets the word (including the letters in potential spaces)
                ArrayList<String> branches = getBranchWords(selectedBoardButtons);
                System.out.println(branches);
                System.out.println(word);
                int score = 0;

                try {
                    if (word.length() == 0 || isValidWord(word)) {
                        score += calculateScore(word);
                        for (String s : branches) {
                            if (isValidWord(s)) {
                                score += calculateScore(s);
                            } else {
                                System.out.println("Invalid word: " + s);
                                revertSelections();
                                score = 0;
                                updateViews();
                                return false;
                            }
                        }
                    } else {
                        System.out.println("Invalid word: " + word);
                        updateViews();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (score == 0) {
                    System.out.println("invalid word");
                    updateViews();
                } else {
                    BoardPanel.disableButtons(selectedBoardButtons);
                    addScore(score);
                    play();
                    updateViews();
                    return true;

                }
            } else {
                System.out.println("Invalid Placements");
                revertSelections();
                updateViews();
                return false;

            }

        }
        updateViews();
        return false;
    }
    // }

    public ArrayList<ArrayList<Character>>  playAI(Hand hand) { //calebs implementation

        ArrayList<ArrayList<Character>> possibleSolutions = new ArrayList<ArrayList<Character>>();
        ArrayList<Character> handList = new ArrayList<Character>();

        for (Piece piece : hand.getHandPieces()) {
            System.out.println(piece.getLetter());
            handList.add(piece.getLetter());

        }

        try {
            for (ArrayList<Character> dict : getList()) {
                ArrayList<Character> tempDictList = new ArrayList<Character>(dict);
                ArrayList<Character> tempHandList = new ArrayList<Character>(handList);


                for (Character letter : dict) {
                    if (tempHandList.contains(letter)) {
                        tempHandList.remove(tempHandList.indexOf(letter));
                        tempDictList.remove(tempDictList.indexOf(letter));
                    }
                }
                //for(Character temp : tempDictList){
                // System.out.println(temp);
                // }
                if (tempDictList.size() == 1) {
                    System.out.println("Dict word:");
                    ArrayList<Character> tempChar = new ArrayList<Character>();
                    for (Character letter : dict) {
                        tempChar.add(letter);
                    }
                    possibleSolutions.add(tempChar);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return possibleSolutions;



        /*
            ArrayList<ArrayList<Character>> possibleWord = new ArrayList<ArrayList<Character>>();

            ArrayList<Character> handList = new ArrayList<Character>();
            HashMap<Character,Integer> map = new HashMap<Character,Integer>();
            ArrayList<Character> count = new ArrayList<Character>();

            for(Piece piece : hand.getHandPieces()){

                handList.add(piece.getLetter());

            }
            for (char letter : bag.getAlph()){
                if(Collections.frequency(handList, letter) > 0){
                    map.put(letter, Collections.frequency(handList, letter));
                    if(count.contains(letter) == false){
                        count.add(letter);
                    }

                }
            }
        try {
            for(ArrayList<Character> dict : getList()){
                if (dict.size() <= 7){
                    //System.out.println(dict.get(0));
                    HashMap<Character,Integer> mapDict = new HashMap<Character,Integer>();
                    ArrayList<Character> countDict = new ArrayList<Character>();
                    for (char letter : count) {
                        //System.out.println("letters");
                        //System.out.println(letter);
                        if (Collections.frequency(dict, letter) > 0) {
                            mapDict.put(letter, Collections.frequency(dict, letter));
                            if(countDict.contains((letter)) == false){
                                countDict.add(letter);
                            }
                        }
                    }
                    int validWord = 0;
                    for(Character diffLettersDict : countDict){
                        for(Character diffLettersInput : count){
                            System.out.println(diffLettersDict);
                            System.out.println(diffLettersInput);
                            System.out.println("boop");
                            if (diffLettersDict == diffLettersInput){
                                //System.out.println(diffLettersDict);
                                //System.out.println(mapDict.get(diffLettersDict));
                                //System.out.println(diffLettersInput);
                                //System.out.println(map.get(diffLettersInput));
                                if (mapDict.get(diffLettersDict) <= map.get(diffLettersInput)){
                                    validWord++;
                                }
                            }
                        }
                    }
                    System.out.println("done loop");
                    //System.out.println("got here boo");
                    //System.out.println(validWord);
                    //System.out.println(count.size());
                    //System.out.println(countDict.size());
                    if(validWord == count.size()){
                        //System.out.println(validWord);
                        possibleWord.add(dict);
                        //System.out.println("these are dict");
                        for(Character test : dict){
                            System.out.println(test);
                        }
                        //System.out.println("these are hand");
                        //System.out.println(hand.toString());
                    }
                }

        }
            System.out.println("got here 1");
            for (ArrayList<Character> test : possibleWord){
                //System.out.println(test.toString());
                //System.out.println("got here");
            }
            return possibleWord;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

         */
    }


}

