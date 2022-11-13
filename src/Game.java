
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private Parser parser;

    class Player {
        int score = 0;
        Hand hand = new Hand();

    }
    Player p1 = new Player();
    Player p2 = new Player();
    Board scrabbleBoard;
    Bag bag;
    boolean isP1turn; // false meaning its player2's turn

    public Game(){
        // initializing game elements
        scrabbleBoard = new Board(); // New Board
        bag = new Bag();// New Bag of Pieces
        p1.hand.addPieces(bag.grabPieces(7)); //add pieces to p1 hand
        p2.hand.addPieces(bag.grabPieces(7));//add pieces to p2 hand
        isP1turn = true; // p1's turn
        parser = new Parser();
    }

    public void nextTurn(){
        isP1turn = !isP1turn;
    }

    public void refillHand(Hand hand){ //this function works
        int num = 7 - hand.sizeOfHand(); //should prob make a constant for num 7
        hand.addPieces(bag.grabPieces(num));
    }

    public String toString() {
        return scrabbleBoard.toString();
    }

    /************************** Process Commands **************************/

    public void skip(){ //need to add logic to switch turns
        nextTurn();
        System.out.println("skip ran");
    }

    private void help(){
        System.out.println(parser.getCommands());
    }

    private boolean quit(Command command)
    {
        if(command.hasInputWord()) {
            System.out.println("Command unrecognized, if you are trying to quit game, type 'quit'");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    // arrOfInput = ['a','a','z']
    // p1.hand = (a,b,c,d,e,f,a)
    // copy.hand = (b,c,d,e,f)
    public void swap(String letters) { //ex: inputWord = "aab"
        if (isP1turn) {
            p1.hand = swapWithHand(p1.hand, letters);
            refillHand(p1.hand);
        }
        else{
            p2.hand = swapWithHand(p2.hand, letters);
            refillHand(p2.hand);
        }
    }

    private Hand swapWithHand(Hand playersHand, String letters){

        Hand copy = playersHand;
        char[] arrOfInput = letters.toCharArray(); // split input word to character arr
        Object[] arrOfcopy = copy.getHandPieces().toArray();
        if (arrOfInput.length <= 7) {
            for (char input : arrOfInput) {
                System.out.println(input);
                System.out.println(copy.toString());
                for (Piece p : playersHand.getHandPieces()) {
                    System.out.println(p.getLetter());
                    if (p.getLetter() == input) {
                        System.out.println("got here though");
                        copy.getHandPieces().remove(p);
                    } else {
                        System.out.println("swap failed");
                        return playersHand;

                    }

                }
                return copy;
            }
            return playersHand;
        }
        return new Hand();
    }
    // Here, placedPieces.add(InputData)
    //if (placedPiece.get(0).x == placedPiece.get(1).x) //then its a vertical word...
    // for (InputData d: placedPieces) { For each letter placed -> findFirstLetter() -> findWordInLine() -> findWords() -> finds main word as String, finds sub words as Stirng[] then checks isValidWord()

    //1. parse data from inputWord into arraylist<inputData> pieceData
    //2. Check if pieceData has length 1, if so, just get vertical and horizontal word
    //3. If multiple inputted Data, Get the first piece inputted, aka. first InputData
    //4. Check if the next piece has the same x coordinate or y coordinate
    //5. If same x coord, then only need to find vertical word once... if same y coord, then only need to find horizontal word once
    //6. If same x coord, find the first letter in the vertical word, and vice versa
    //7. If same x coord, iterate down until its null, adding each char to a string word
    //8. For each pieceData, get the x and y coordinate, then iterate left until null. Iterate right until null, adding each char to a horizontalsubword
    //9. add all horizontal subwords to an array of strings
    // ...
    //10. if all words are valid, then tally up points
    private void put(String inputWord) { // ex: put t_1_2,h_1_3,e_1_4
        int roundScore = 0;
        int x = 0;
        int y = 0;
        Set<String> wordSet = new HashSet<>();
        ArrayList<SelectionData> pieceData = parser.parsePieceData(inputWord);
        for (SelectionData c: pieceData) {
            scrabbleBoard.placePiece(c);
        }
        for (SelectionData d : pieceData) { // vertical word
            String wordy = "";
            x = d.getX();
            y = d.getY();
            while (scrabbleBoard.getPiece(x,y).getLetter() != ' ') {
                y--;
            }
            y++;
            while (scrabbleBoard.getPiece(x,y).getLetter() != ' ') {
                wordy += scrabbleBoard.getPiece(x,y).getLetter();
                y++;
            }
            wordSet.add(wordy);

        }
        System.out.println("Vertical Words: " + wordSet);

        for (SelectionData d : pieceData) { // horizontal word
            String wordx = "";
            x = d.getX();
            y = d.getY();
            while (scrabbleBoard.getPiece(x,y).getLetter() != ' ') {
                x--;
            }
            x++;
            while (scrabbleBoard.getPiece(x,y).getLetter() != ' ') {
                wordx += scrabbleBoard.getPiece(x,y).getLetter();
                x++;
            }
            wordSet.add(wordx);

        }
        System.out.println("Potential Words: " + wordSet);
        Set<String> copy = new HashSet<>();

        for (String word : wordSet) {
            if (word.length() == 1) {
                copy.add(word);
            }
        }
        System.out.println(copy);
        for (String word : copy) {
            if (wordSet.contains(word)) {
                wordSet.remove(word);
            }
        }
        System.out.println(wordSet);

        for (String word: wordSet) {
            try {
                if (!isValidWords(word)){
                    wordSet = null;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        roundScore = calculateRoundScore(wordSet);
        if (isP1turn) {
            p1.score += roundScore;
        }
        else {
            p2.score += roundScore;
        }
    }

    private boolean processCommand(Command command){ // this function works as is, each case may need to be changed according
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        String inputWord = command.getInputWord();

        switch (commandWord) {
            case "swap" -> {
                System.out.println(inputWord);
                swap(inputWord);
                this.nextTurn();
            }
            case "skip" -> {
                System.out.println(inputWord);
                skip();
                this.nextTurn();
            }
            case "put" -> {  //update board in put()
                System.out.println(inputWord);

                put(inputWord);
                this.nextTurn();
            }
            case "help" -> {
                System.out.println(inputWord);
                help();
            }
            case "quit" -> {
                System.out.println(inputWord);
                wantToQuit = quit(command);
            }
        }
        // else command not recognised.
        return wantToQuit;
    }
    /************************************************************************/

    private int calculateRoundScore(Set<String> words) {
        int roundTotal = 0;
        for (String s : words) {
            char[] chars = s.toCharArray();
            for (char c : chars) {
                int i = 0;
                for (char letter : Bag.ALPHABET) {
                    if (c == letter) {
                        break;
                    }
                    i++;
                }
                roundTotal += Bag.POINTS[i];;
            }
        }
        return roundTotal;
    }

    private boolean isValidWords(String word) throws IOException {  // this function works as is

        Path path = Path.of("src\\Dictionary.txt");
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

    public static void main(String[] args) throws IOException {
        Game scrabble = new Game();
        //System.out.println(scrabble.toString());
        //System.out.println("Bag: " + scrabble.bag.toString());
        //System.out.println("P1 Hand: " + scrabble.p1.hand.toString());
        //System.out.println("P2 Hand: " + scrabble.p2.hand.toString());
        //scrabble.bag.addPieces(scrabble.p1.hand.removePiece(0));
        //scrabble.bag.addPieces(scrabble.p1.hand.removePiece(1));
        //System.out.println("Bag: " + scrabble.bag.toString());
        //scrabble.refillHand(scrabble.p1.hand);
        //scrabble.swap("e");
        //scrabble.put("t_1_2,h_1_3,e_1_4");
        //System.out.println(scrabble.toString());

        System.out.println(scrabble.scrabbleBoard.toString());
        boolean finished = false;
        while (!finished) {
            if(scrabble.isP1turn){
                System.out.println("Player 1's turn");
                System.out.println("Hand: " + scrabble.p1.hand.toString());
                System.out.println("Score: " + scrabble.p1.score);
            }
            else {
                System.out.println("Player 2's turn");
                System.out.println("Hand: " + scrabble.p2.hand.toString());
                System.out.println("Score: " + scrabble.p2.score);
            }

            Command command = scrabble.parser.getCommand();
            finished = scrabble.processCommand(command);
            System.out.println(scrabble.toString());
            System.out.println();

        }
        System.out.println("you quit the game");


    }

}
