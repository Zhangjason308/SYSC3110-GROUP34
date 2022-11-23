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

        list = new ArrayList<ArrayList<Character>>();
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

    public void play() {
        if(getTurn()){
            refillHand(player1Hand);
        }
        else {refillHand(player2Hand);}
        changeTurn();
        updateViews();

        System.out.println("play was pressed");
    }

    public ArrayList<ArrayList<Character>> getList() throws IOException{

        Path path = Path.of("src\\Dictionary.txt");
        String dictionary = Files.readString(path);
        String[] temp = dictionary.split("\n");


        for(String s : temp){
            ArrayList<Character> tempChar = new ArrayList<Character>();
            for(int i = 0; i < s.length(); i++){
                tempChar.add(s.charAt(i));
            }
            list.add(tempChar);
        }

        return list;
    }

    public  void playAI(Hand hand) {
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
                    if(tempHandList.contains(letter)) {
                        System.out.println(letter);
                        tempHandList.remove(tempHandList.indexOf(letter));
                        tempDictList.remove(tempDictList.indexOf(letter));
                    }
                }
                //for(Character temp : tempDictList){
                   // System.out.println(temp);
               // }
                if(tempDictList.size() == 0) {
                    for(Character letter : dict) {
                        System.out.println(letter);

                    }
                    System.out.println("boop");


                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



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
    public static void main(String[] args) {
        ScrabbleGame gam = new ScrabbleGame();
        Bag bag1 = new Bag();
        Hand han = new Hand();
        han.addPieces(bag1.grabPieces(7));
        gam.playAI(han);
    }

}