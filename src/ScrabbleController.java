import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public void skip(){
        revertSelections();
        clearSelections();
        model.skip();
    }

    public void swap(){
        clearSelections();
        model.swap();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {  //since it's implemented like this we can also make a JBoardButton class and JHandButton and then just say if o is instance of each
            JButton button = (JButton)o;
            String[] input = button.getActionCommand().split(" ");

            if(button.getText() == PLAY){

<<<<<<< HEAD
                if (model.getTurn()) {
                    System.out.println("Player 1 turn");
                    if (model.playWordOnBoard(model.getSelectedBoardButtons())) {
                        model.clearSelections();
                    } else {
                        model.clearSelections();
                    }
                } else {
                    System.out.println("Player 2 turn");
                    istrueword:
                    for (int i = 0; i < Board.SIZE; i++) {
                        for (int j = 0; j < Board.SIZE; j++) {
                            if (model.getBoard().getPiece(j,i ).getLetter() != ' ') {
                                model.getSelectedBoardButtons().clear();
                                System.out.println("Lucky Letter: " +model.getBoard().getPiece(j,i).getLetter() + model.getBoard().toString());
                                Piece p = model.getPlayer2Hand().getHandPieces().get(0);
                                if (model.getBoard().getPiece(j-1, i).getLetter() == ' ') {
                                    SelectionData sd = new SelectionData(j-1,i, p);
                                    model.getSelectedBoardButtons().add(sd);
                                    //model.getPlayer2Hand().removePiece();
                                    model.getBoard().placePiece(sd);
                                    System.out.println(model.getSelectedBoardButtons().size()+"=====");
                                    System.out.println("selectedAIButton: " + model.getSelectedBoardButtons().get(0).getPiece().getLetter());
                                    if (model.playWordOnBoard(model.getSelectedBoardButtons())) {
                                        System.out.println("Player 2 successfully placed a letter :" + model.getSelectedBoardButtons().get(0).getPiece().getLetter());
                                        model.getPlayer2Hand().getHandPieces().remove(p);
                                        //model.changeTurn();
                                        //model.updateViews();
                                        //model.refillHand(model.getPlayer2Hand());
                                        model.clearSelections();
                                        break istrueword;
                                    }
                                    else {
                                        model.getBoard().removePiece(j-1,i);
                                        model.getSelectedBoardButtons().clear();
                                        continue;
                                    }


                                }


                            }

                        }




                    }
                }


            } else if (button.getText() == SKIP) {
                model.revertSelections();
                model.clearSelections();
                model.skip();
            } else if (button.getText() == SWAP) { // doesn't return to bag (deletes them)
                model.clearSelections();
                model.swap();
            } else if (input.length == 1) { //it's a button from hand deal with accordingly
=======
                if(model.playWordOnBoard(selectedBoardButtons)){
                    clearSelections();
                }
                else {
                    clearSelections();
                }

            }
            else if(button.getText() == SKIP){
               skip();
            }
            else if(button.getText() == SWAP ){ // doesn't return to bag (deletes them)
                swap();
            }
            else if(input.length == 1){ //it's a button from hand deal with accordingly
>>>>>>> c964b12c42faa0667cd8637529423f5aaedc8b2c
                // add to selected Pieces or buttons or whatever

                int handIndex = Integer.parseInt(input[0]);
                //remove piece from hand
                System.out.println("button from hand, index: " + handIndex + " text: " + button.getText());

<<<<<<< HEAD
                if (button.getText().charAt(0) == BLANK) {
                    String newChar = JOptionPane.showInputDialog("Enter a single letter: ").trim().toLowerCase();

                    if (newChar.length() == 1) {
                        if (model.getTurn()) {
                            model.getPlayer1Hand().changeBlankValue(handIndex, newChar.charAt(0));
                        } else {
=======
                if(button.getText().charAt(0) == BLANK){
                    String newChar = JOptionPane.showInputDialog("Enter a single letter: ").trim().toLowerCase();

                    if(newChar.length() == 1){
                        if(model.getTurn()){
                            model.getPlayer1Hand().changeBlankValue(handIndex, newChar.charAt(0));
                        }
                        else{
>>>>>>> c964b12c42faa0667cd8637529423f5aaedc8b2c
                            model.getPlayer2Hand().changeBlankValue(handIndex, newChar.charAt(0));
                        }

                    }
                    model.updateViews();
                    return;
                }

<<<<<<< HEAD
                if (button.getText().equals(" ") || button.getText().equals("")) {
                    System.out.println("No piece there");
                } else {
                    SelectionData data;
                    if (model.getTurn()) {
                        data = new SelectionData(handIndex, handIndex, model.getPlayer1Hand().getHandPieces().get(handIndex));
                        model.getPlayer1Hand().removePiece(handIndex);
                    } else {
=======
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
>>>>>>> c964b12c42faa0667cd8637529423f5aaedc8b2c
                        data = new SelectionData(handIndex, handIndex, model.getPlayer2Hand().getHandPieces().get(handIndex));
                        model.getPlayer2Hand().removePiece(handIndex);
                    }
                    System.out.println(handIndex);
<<<<<<< HEAD
                    model.getSelectedHandButtons().add(data);

                    System.out.println(model.getSelectedHandButtons().get(0).getX() + " " + model.getSelectedHandButtons().get(0).getX() + " ");
                }
            } else if (input.length == 2) {      // it's a button from board deal with accordingly
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                if (button.getText().equals(" ")) { // empty spot, check if and pieces are selected
                    if (model.getSelectedHandButtons().isEmpty()) {
                        System.out.println("nothing from hand is selected to be placed");
                    } else {
                        SelectionData data = new SelectionData(x, y, model.getSelectedHandButtons().get(0).getPiece());
                        model.getSelectedBoardButtons().add(data);
                        model.getBoard().placePiece(data);
                        model.getSelectedHandButtons().remove(0);
=======
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
>>>>>>> c964b12c42faa0667cd8637529423f5aaedc8b2c
                    }
                } else {
                    if (model.getTurn()) {
                        model.getPlayer1Hand().addPiece(model.getBoard().getPiece(x, y));
                    } else {
                        model.getPlayer2Hand().addPiece(model.getBoard().getPiece(x, y));
                    }
                    model.getBoard().removePiece(x, y);
                    int index = 0;
                    for (SelectionData sd : model.getSelectedBoardButtons()) {
                        if (sd.getX() == x && sd.getY() == y) {
                            index = model.getSelectedBoardButtons().indexOf(sd);
                        }
                    }
                    model.getSelectedBoardButtons().remove(index);
                }
<<<<<<< HEAD
=======
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
>>>>>>> c964b12c42faa0667cd8637529423f5aaedc8b2c
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
<<<<<<< HEAD
=======
    }
    public ArrayList<SelectionData> getSelectedBoardButtonsForTesting() {
        return selectedBoardButtons;
    }
>>>>>>> c964b12c42faa0667cd8637529423f5aaedc8b2c

    public ArrayList<SelectionData> getSelectedHandButtonsForTesting(){
        return selectedHandButtons;
    }
<<<<<<< HEAD
=======

    //function is ONLY USED FOR TESTING PURPOSES pog
    public void addToSelectedBoardButtonsForTesting(SelectionData sd) {
        selectedBoardButtons.add(sd);
    }

    public void addToSelectedHandButtonsForTesting(SelectionData sd){
        selectedHandButtons.add(sd);
    }

>>>>>>> c964b12c42faa0667cd8637529423f5aaedc8b2c
}