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

    public boolean lettersAreInLine() {
        int xCoord = selectedBoardButtons.get(0).getX();
        int yCoord = selectedBoardButtons.get(0).getY();
        int xCount = 0;
        int yCount = 0;
                    // ,,m(0,1)a(1,1)t(2,1)
        for (int i = 1; i< selectedBoardButtons.size(); i++) {
            if (selectedBoardButtons.get(i).getX() != xCoord) {  // if x coord is not same, then y coord must be same and must be horizontal word
                if (selected)
            }
        }
        for (int i = 1; i< selectedBoardButtons.size(); i++) {
            if (selectedBoardButtons.get(i).getY() != yCoord) {  // if x coord is not same, then y coord must be same and must be horizontal word
                return false;
            }
        }
        //for each SelectionData in ArrayList, continue if x or y is the same, return false if not
        return true;
    }

    private boolean isValidWord(String word) throws IOException {  // this function works as is

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

    private int calculateScore(String s){
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

                if (lettersAreInLine()){ // all x or y indexes are same

                if (lettersAreInLine(selectedBoardButtons)){ // all x or y indexes are same

                    String word = getWord(selectedBoardButtons); //gets the word (including the letters in potential spaces)
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
}
