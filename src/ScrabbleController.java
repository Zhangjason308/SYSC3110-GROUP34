import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScrabbleController implements ActionListener {
    private ScrabbleGame model;

    public static final char BLANK = '!';


    private static String PLAY = "Play";
    private static String SWAP = "Swap";
    private static String SKIP = "Skip";

    public ScrabbleController(ScrabbleGame model) {
        this.model = model;

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {  //since it's implemented like this we can also make a JBoardButton class and JHandButton and then just say if o is instance of each
            JButton button = (JButton) o;
            String[] input = button.getActionCommand().split(" ");

            if (button.getText() == PLAY) {

                if (model.getTurn()) {
                    System.out.println("Player 1 turn");
                    if (model.playWordOnBoard(selectedBoardButtons)) {
                        model.clearSelections();
                    } else {
                        clearSelections();
                    }
                } else {
                    System.out.println("Player 2 turn");
                    for (int i = 0; i < Board.SIZE; i++) {
                        for (int j = 0; j < Board.SIZE; j++) {
                            if (model.getBoard().getPiece(j,i ).getLetter() != ' ') {
                                selectedBoardAIButtons.clear();
                                System.out.println("Lucky Letter: " +model.getBoard().getPiece(j,i).getLetter() + model.getBoard().toString());
                                for (Piece p : model.getPlayer2Hand().getHandPieces()) {
                                    if (model.getBoard().getPiece(j-1, i).getLetter() == ' ') {
                                        SelectionData sd = new SelectionData(j,i-1, p);
                                        selectedBoardAIButtons.add(sd);
                                        //model.getPlayer2Hand().removePiece();
                                        model.getBoard().placePiece(sd);
                                        System.out.println(selectedBoardAIButtons.size()+"=====");
                                        System.out.println("selectedAIButton: " + selectedBoardAIButtons.get(0).getPiece().getLetter());
                                        if (model.playWordOnBoard(selectedBoardAIButtons)) {
                                            System.out.println("Player 2 successfully placed a letter :" + selectedBoardAIButtons.get(0).getPiece().getLetter());
                                            model.getPlayer2Hand().getHandPieces().remove(p);
                                            //model.changeTurn();
                                            //model.updateViews();
                                            //model.refillHand(model.getPlayer2Hand());
                                            clearSelections();
                                            break;
                                        }
                                        else {
                                            model.getBoard().removePiece(j-1,i);
                                            selectedBoardAIButtons.clear();
                                            continue;
                                        }


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
            // add to selected Pieces or buttons or whatever

            int handIndex = Integer.parseInt(input[0]);
            //remove piece from hand
            System.out.println("button from hand, index: " + handIndex + " text: " + button.getText());

            if (button.getText().charAt(0) == BLANK) {
                String newChar = JOptionPane.showInputDialog("Enter a single letter: ").trim().toLowerCase();

                if (newChar.length() == 1) {
                    if (model.getTurn()) {
                        model.getPlayer1Hand().changeBlankValue(handIndex, newChar.charAt(0));
                    } else {
                        model.getPlayer2Hand().changeBlankValue(handIndex, newChar.charAt(0));
                    }

                }
                model.updateViews();
                return;
            }

            if (button.getText().equals(" ") || button.getText().equals("")) {
                System.out.println("No piece there");
            } else {
                SelectionData data;
                if (model.getTurn()) {
                    data = new SelectionData(handIndex, handIndex, model.getPlayer1Hand().getHandPieces().get(handIndex));
                    model.getPlayer1Hand().removePiece(handIndex);
                } else {
                    data = new SelectionData(handIndex, handIndex, model.getPlayer2Hand().getHandPieces().get(handIndex));
                    model.getPlayer2Hand().removePiece(handIndex);
                }
                System.out.println(handIndex);
                selectedHandButtons.add(data);

                System.out.println(selectedHandButtons.get(0).getX() + " " + selectedHandButtons.get(0).getX() + " ");
            }
        } else if (input.length == 2) {      // it's a button from board deal with accordingly
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            if (button.getText().equals(" ")) { // empty spot, check if and pieces are selected
                if (selectedHandButtons.isEmpty()) {
                    System.out.println("nothing from hand is selected to be placed");
                } else {
                    SelectionData data = new SelectionData(x, y, selectedHandButtons.get(0).getPiece());
                    selectedBoardButtons.add(data);
                    model.getBoard().placePiece(data);
                    selectedHandButtons.remove(0);
                }
            } else {
                if (model.getTurn()) {
                    model.getPlayer1Hand().addPiece(model.getBoard().getPiece(x, y));
                } else {
                    model.getPlayer2Hand().addPiece(model.getBoard().getPiece(x, y));
                }
                model.getBoard().removePiece(x, y);
                int index = 0;
                for (SelectionData sd : selectedBoardButtons) {
                    if (sd.getX() == x && sd.getY() == y) {
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