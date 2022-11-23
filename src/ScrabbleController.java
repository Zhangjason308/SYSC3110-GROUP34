import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ScrabbleController implements ActionListener {
    private ScrabbleGame model;

    public static final char BLANK = '!';

    private ArrayList<SelectionData> selectedBoardButtons;
    private ArrayList<SelectionData> selectedHandButtons;
    private ArrayList<SelectionData> specialButtons;

    private static String PLAY = "Play";
    private static String SWAP = "Swap";
    private static String SKIP = "Skip";

    public ScrabbleController(ScrabbleGame model) {
        this.model = model;
        selectedBoardButtons = new ArrayList<>();
        selectedHandButtons = new ArrayList<>();
        specialButtons = new ArrayList<>();
    }

    public void getspecialButtons(ArrayList<SelectionData> sbb) {
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

    public void clearSelections(){
        selectedBoardButtons.clear();
        selectedHandButtons.clear();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {  //since it's implemented like this we can also make a JBoardButton class and JHandButton and then just say if o is instance of each
            JButton button = (JButton)o;
            String[] input = button.getActionCommand().split(" ");

            if(button.getText() == PLAY){

                if(model.firstTurnPlayedCenter() && model.surroundingPiecesArentEmpty(selectedBoardButtons)){
                    if (model.isXAligned(selectedBoardButtons) || model.isYAligned(selectedBoardButtons)){ // all x or y indexes are same

                        String word = model.getWord(selectedBoardButtons); //gets the word (including the letters in potential spaces)
                        ArrayList<String> branches = model.getBranchWords(selectedBoardButtons);
                        System.out.println(branches);
                        System.out.println(word);
                        int score = 0;
                        try {
                            if(word.length() == 0 || model.isValidWord(word)){
                                score += calculateScore(word);
                                for (String s : branches) {
                                    if(model.isValidWord(s)){
                                        score += calculateScore(s);
                                    }
                                    else{
                                        System.out.println("Invalid word: " + s);
                                        revertSelections();
                                        score = 0;
                                        break;
                                    }
                                }
                            }
                            else{
                                System.out.println("Invalid word: " + word);
                            }

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        if(score == 0){
                            System.out.println("invalid word");
                        }
                        else{
                            BoardPanel.disableButtons(selectedBoardButtons);
                            model.addScore(score);
                            model.play();
                            clearSelections();
                        }
                    }
                    else{
                        System.out.println("Invalid Placements");
                        revertSelections();
                    }
                }
            }
            else if(button.getText() == SKIP){
                revertSelections();
                clearSelections();
                model.skip();
            }
            else if(button.getText() == SWAP ){ // doesn't return to bag (deletes them)
                clearSelections();
                model.swap();
            }
            else if(input.length == 1){ //it's a button from hand deal with accordingly
                // add to selected Pieces or buttons or whatever
                int handIndex = Integer.parseInt(input[0]);
                //remove piece from hand
                System.out.println("button from hand, index: " + handIndex + " text: " + button.getText());

                if(button.getText().charAt(0) == BLANK){
                    String newChar = JOptionPane.showInputDialog("Enter a single letter: ").trim().toLowerCase();

                    if(newChar.length() == 1){
                        if(model.getTurn()){
                            model.getPlayer1Hand().changeBlankValue(handIndex, newChar.charAt(0));
                        }
                        else{
                            model.getPlayer2Hand().changeBlankValue(handIndex, newChar.charAt(0));
                        }

                    }
                    model.updateViews();
                    return;
                }

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
                    int index = 0;
                    for (SelectionData sd : selectedBoardButtons) {
                        if(sd.getX() == x && sd.getY() == y){
                            index = selectedBoardButtons.indexOf(sd);
                        }
                    }
                    selectedBoardButtons.remove(index);
                }
                //if piece is not null return piece to hand
                // otherwise add selected piece to board
                // add to selected Pieces or buttons or whatever
                // check if anything has been selected if so add to board
                System.out.println("button from board");
                System.out.println(x);
                System.out.println(y);
            }
            model.updateViews();
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