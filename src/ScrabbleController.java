import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ScrabbleController implements ActionListener {
    private ScrabbleGame model;

    private ArrayList<SelectionData> selectedBoardButtons;
    private ArrayList<SelectionData> selectedHandButtons;


    public ScrabbleController(ScrabbleGame model){
        this.model = model;
        selectedBoardButtons = new ArrayList<>();
        selectedHandButtons = new ArrayList<>();
    }

    public boolean[] lettersAreInLine() {

        boolean xAligned = true;
        boolean yAligned = true;

        SelectionData previous = null;

        for (SelectionData sd : selectedBoardButtons) {
            if(previous == null){
                previous = sd;
                continue;
            }
            if(sd.getX() == previous.getX()){
                previous = sd;
            }
            else {
                xAligned = false;
            }
            if(sd.getY() == previous.getY()){
                previous = sd;
            }
            else {
                yAligned = false;
            }
        }
        boolean[] booleans = new boolean[2];
        booleans[0] = xAligned;
        booleans[1] = yAligned;
        return booleans;
    }

    private void wordIsConnected(){ // idk if its needed

    }
    private void getBranchWords() { // ex: put t_1_2,h_1_3,e_1_4
        int roundScore = 0;
        int x = 0;
        int y = 0;
        Set<String> wordSet = new HashSet<>();
        ArrayList<SelectionData> pieceData = this.selectedBoardButtons;
        for (SelectionData c: pieceData) {
            model.getBoard().placePiece(c);
        }
        for (SelectionData d : pieceData) { // vertical word
            String wordy = "";
            x = d.getX();
            y = d.getY();
            while (model.getBoard().getPiece(x,y).getLetter() != ' ') {
                y--;
            }
            y++;
            while (model.getBoard().getPiece(x,y).getLetter() != ' ') {
                wordy += model.getBoard().getPiece(x,y).getLetter();
                y++;
            }
            wordSet.add(wordy);

        }
        System.out.println("Vertical Words: " + wordSet);

        for (SelectionData d : pieceData) { // horizontal word
            String wordx = "";
            x = d.getX();
            y = d.getY();
            while (model.getBoard().getPiece(x,y).getLetter() != ' ') {
                x--;
            }
            x++;
            while (model.getBoard().getPiece(x,y).getLetter() != ' ') {
                wordx += model.getBoard().getPiece(x,y).getLetter();
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
                if (!isValidWord(word)){
                    wordSet = null;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public String getWord(){

        int smallestIndex = ScrabbleGame.SIZE;
        int largestIndex = 0;
        int associatedCoordinate = 0;
        for (SelectionData sd : selectedBoardButtons) {
            if(isXAligned()){
                if(sd.getX() < smallestIndex){
                    smallestIndex = sd.getY();
                }
                if(sd.getX() > largestIndex){
                    largestIndex = sd.getY();
                }
                associatedCoordinate = sd.getX();
            }
            else {
                if(sd.getY() < smallestIndex){
                    smallestIndex = sd.getX();
                }
                if(sd.getY() > largestIndex){
                    largestIndex = sd.getX();
                }
                associatedCoordinate = sd.getY();
            }
        }
        String out = "";
        int i = smallestIndex;
        if(isXAligned()){
            while(model.getBoard().getPiece(associatedCoordinate, i).getLetter() != ' '){
                if(i != 0){
                    i--;
                }
                else {
                    smallestIndex = 0;
                }
            }

            while(model.getBoard().getPiece(associatedCoordinate, i + 1).getLetter() != ' '){ // while next piece is not ' ' continue
                // add to out and check for holes in selected letters (theyre on the board)
            }
        }
        else if(isYAligned()){
            while(model.getBoard().getPiece(i, associatedCoordinate).getLetter() != ' '){
                if(i != 0){
                    smallestIndex = i;
                    i--;
                }
                else {
                    smallestIndex = 0;
                }
            }

            while(true){ // while next piece is not ' ' continue

            }
        }

        return out;
    }
    private boolean isXAligned(){
        boolean[] bool = lettersAreInLine();
        return bool[0];
    }
    private boolean isYAligned(){
        boolean[] bool = lettersAreInLine();
        return bool[1];
    }

    public boolean isValidWord(String word) throws IOException {  // this function works as is

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

    public int calculateScore(String s){
        char[] arr = s.toCharArray();
        int score = 0;
        for(char c : arr){
            score += Piece.pieceMap.get(c);
        }
        return score;
    }

    public void revertSelections(){
        for (SelectionData sd : selectedBoardButtons) {
            if(model.getTurn()){
                model.getPlayer1Hand().addPiece(sd.getPiece());
            }
            else{
                model.getPlayer2Hand().addPiece(sd.getPiece());
            }
            model.removeFromBoard(sd.getX(), sd.getY());
        }
        selectedBoardButtons = new ArrayList<>();
        for (SelectionData sd : selectedHandButtons) {
            if(model.getTurn()){
                model.getPlayer1Hand().addPiece(sd.getPiece());
            }
            else{
                model.getPlayer2Hand().addPiece(sd.getPiece());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {  //since it's implemented like this we can also make a JBoardButton class and JHandButton and then just say if o is instance of each
            JButton button = (JButton)o;
            String[] input = button.getActionCommand().split(" ");

            if(button.getText() == "Play"){
/*

                if (isXAligned() || isYAligned()){ // all x or y indexes are same

                    String word = getWord(); //gets the word (including the letters in potential spaces)
                    //String[] branches = getBranchWords();
                    int score = 0;
                    try {
                        if(isValidWord(word)){
                            for (String s : branches) {
                                if(!(isValidWord(s))){
                                    System.out.println("Invalid word: " + s);
                                    revertSelections();
                                    score = 0;
                                    break;
                                }
                                else{
                                    score += calculateScore(s);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(score == 0){
                        System.out.println("invalid word");
                    }
                    else{
                        model.addScore(score);
                        model.play(selectedBoardButtons);
                    }
                }
                else{
                    System.out.println("Invalid Placements");
                    revertSelections();
                }


 */
            }
            else if(button.getText() == "Skip"){
                revertSelections();
                model.skip();
            }
            else if(button.getText() == "Swap"){ // doesn't return to bag (deletes them)
                revertSelections();
                model.swap();
            }
            else if(input.length == 1){ //it's a button from hand deal with accordingly
                // add to selected Pieces or buttons or whatever
                int handIndex = Integer.parseInt(input[0]);
                //remove piece from hand
                System.out.println("button from hand, index: " + handIndex + " text: " + button.getText());

                if(button.getText().equals(" ") || button.getText().equals("")){
                    System.out.println("No piece there");
                }
                else {
                    SelectionData data;
                    if(model.getTurn()){
                        data = new SelectionData(handIndex, handIndex, model.getPlayer1Hand().getHandPieces().get(handIndex));
                        model.getPlayer1Hand().removePiece(handIndex);
                    }
                    else{
                        data = new SelectionData(handIndex, handIndex, model.getPlayer2Hand().getHandPieces().get(handIndex));
                        model.getPlayer2Hand().removePiece(handIndex);
                    }
                    System.out.println(handIndex);
                    selectedHandButtons.add(data);

                    System.out.println(selectedHandButtons.get(0).getX() + " "+ selectedHandButtons.get(0).getX() + " ");

                }

                model.updateViews();
            }
            else if(input.length == 2){      // it's a button from board deal with accordingly
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                if(button.getText().equals(" ")){ // empty spot, check if and pieces are selected
                    if(selectedHandButtons.isEmpty()){
                        System.out.println("nothing from hand is selected to be placed");
                    }
                    else{
                        SelectionData data = new SelectionData(x, y, selectedHandButtons.get(0).getPiece());
                        selectedBoardButtons.add(data);
                        model.getBoard().placePiece(data);
                        selectedHandButtons.remove(0);
                    }
                }
                else{
                    if(model.getTurn()){
                        model.getPlayer1Hand().addPiece(model.getBoard().getPiece(x, y));
                    }
                    else{
                        model.getPlayer2Hand().addPiece(model.getBoard().getPiece(x, y));
                    }
                    model.getBoard().removePiece(x, y);
                }
                //if piece is not null return piece to hand
                // otherwise add selected piece to board
                // add to selected Pieces or buttons or whatever
                // check if anything has been selected if so add to board
                System.out.println("button from board");
                System.out.println(x);
                System.out.println(y);
                model.updateViews();
            }
        }
    }
    public ArrayList<SelectionData> getSelectedBoardButtonsForTesting() {
        return selectedBoardButtons;
    }

    public ArrayList<SelectionData> getSelectedHandButtonsForTesting(){
        return selectedHandButtons;
    }

    //function is ONLY USED FOR TESTING PURPOSES pog
    public void addToSelectedBoardButtonsForTesting(SelectionData sd) {
        selectedBoardButtons.add(sd);
    }

    public void addToSelectedHandButtonsForTesting(SelectionData sd){
        selectedHandButtons.add(sd);
    }
}
