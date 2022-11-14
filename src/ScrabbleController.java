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
    private String[] getBranchWords(){
        return new String[0];
    }

    private String getWord(){
        int contstantAxis; // the x or y coord that doesnt change
        int startValue; // x or y for the first letter in the word for the varying coord

        contstantAxis = (isXAligned()) ? selectedBoardButtons.get(0).getX():selectedBoardButtons.get(0).getY();
        ArrayList<SelectionData> positions = new ArrayList<>(ScrabbleGame.SIZE);
        for (int i = 0; i < selectedBoardButtons.size(); i++) {
            if(isXAligned()){
                positions.set(i, selectedBoardButtons.get(i));
            }
            else{
                positions.set(i, selectedBoardButtons.get(i));
            }
        } // now we can iterate through and get the holes from the board

        SelectionData tracker = new SelectionData(0, 0,new Piece(' '));

        while(tracker.getPiece().getLetter() != ' '){
            if(isXAligned()){
                tracker = positions.get(tracker.getX() - 1);
            }
            else{
                tracker = positions.get(tracker.getY() - 1);
            }
            if(tracker.getPiece().getLetter() == ' '){
                if(model.getBoard().getPiece(tracker.getX(), tracker.getY()).getLetter() == ' '){
                    return "";
                }
                else {
                    tracker = new SelectionData(tracker.getX(), tracker.getY(), model.getBoard().getPiece(tracker.getX(), tracker.getY()));
                }
            }
        } // tracker is now the first element of the word
        ArrayList<Character> chars = new ArrayList<>();
        while(tracker.getPiece().getLetter() != ' '){
            int x = tracker.getX();
            int y = tracker.getY();
            if(isXAligned()){
                if(model.getBoard().getPiece(x, y).getLetter() != ' '){
                    chars.add(model.getBoard().getPiece(x, y).getLetter());
                    tracker = new SelectionData(x+1, contstantAxis, model.getBoard().getPiece(x, y));
                }
                else if (positions.get(x).getPiece().getLetter() == ' '){
                    chars.add(positions.get(x).getPiece().getLetter());
                    tracker = new SelectionData(x+1, contstantAxis, positions.get(x).getPiece());
                }
                else {
                    //end of word
                    return chars.toString();
                }
            }
            else{
                if(model.getBoard().getPiece(x, y).getLetter() != ' '){
                    chars.add(model.getBoard().getPiece(x, y).getLetter());
                    tracker = new SelectionData(contstantAxis,y+1, model.getBoard().getPiece(x, y));
                }
                else if (positions.get(y).getPiece().getLetter() == ' '){
                    chars.add(positions.get(y).getPiece().getLetter());
                    tracker = new SelectionData(contstantAxis, y+1, positions.get(y).getPiece());
                }
                else {
                    //end of word
                    return chars.toString();
                }
            }
        }
        return "";
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

    private void revertSelections(){
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


                if (isXAligned() || isYAligned()){ // all x or y indexes are same

                    String word = getWord(); //gets the word (including the letters in potential spaces)
                    String[] branches = getBranchWords();
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
