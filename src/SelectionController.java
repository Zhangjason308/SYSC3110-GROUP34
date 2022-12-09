import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class SelectionController implements Serializable {
    ScrabbleGame model;
    private ArrayList<SelectionData> specialBlueButtons;

    public ArrayList<SelectionData> getSpecialBlueButtons() {
        return specialBlueButtons;
    }

    public ArrayList<SelectionData> getSpecialRedButtons() {
        return specialRedButtons;
    }

    public ArrayList<SelectionData> getSelectedBoardButtons() {
        return selectedBoardButtons;
    }


    private ArrayList<SelectionData> specialRedButtons;
    private ArrayList<SelectionData> selectedBoardButtons;
    private ArrayList<SelectionData> selectedHandButtons;


    public SelectionController(ScrabbleGame model){
        this.model = model;

        specialBlueButtons = new ArrayList<>();
        specialRedButtons = new ArrayList<>();

        selectedBoardButtons = new ArrayList<>();
        selectedHandButtons = new ArrayList<>();
    }

    public void addToHandButtons(SelectionData sd){
        selectedHandButtons.add(sd);
    }
    public void addToRedButtons(SelectionData sd){
        specialRedButtons.add(sd);
    }
    public void addToBlueButtons(SelectionData sd){
        specialBlueButtons.add(sd);
    }


    public void clearSpecialButtons(){
        specialRedButtons.clear();
        specialBlueButtons.clear();
    }

    public void clearSelectionButtons(){
        selectedHandButtons.clear();
        selectedBoardButtons.clear();
    }

    public void revertSelections() {

        for (SelectionData sd : selectedBoardButtons) {
            model.getCurrentHand().addPiece(sd.getPiece());
            model.removeFromBoard(sd.getX(), sd.getY());
        }
        selectedBoardButtons = new ArrayList<>();
        for (SelectionData sd : selectedHandButtons) {
            model.getCurrentHand().addPiece(sd.getPiece());
        }
        clearSelectionButtons();
        clearSpecialButtons();
    }

    public boolean[] lettersAreInLine() {

        boolean xAligned = true;
        boolean yAligned = true;

        SelectionData previous = null;

        if(selectedBoardButtons.size() == 1){
            boolean[] booleans = new boolean[2];
            int x = selectedBoardButtons.get(0).getX();
            int y = selectedBoardButtons.get(0).getY();
            if(model.getBoard().getPiece(x - 1, y).getLetter() == ' ' && model.getBoard().getPiece(x + 1, y).getLetter() == ' '){
                xAligned = false;
            }
            if(model.getBoard().getPiece(x, y - 1).getLetter() == ' ' && model.getBoard().getPiece(x, y + 1).getLetter() == ' '){
                yAligned = false;
            }
            if(xAligned && yAligned){ // if both are true then it doesn't matter, defaults to xAligned
                yAligned = false;
            }
            booleans[0] = xAligned;
            booleans[1] = yAligned;
            return booleans;
        }

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
    }

    public boolean isXAligned() {
        boolean[] bool = lettersAreInLine();
        return bool[0];
    }

    public boolean isYAligned() {
        boolean[] bool = lettersAreInLine();
        return bool[1];
    }

    public void selectHandButton(int handIndex, String buttonText){

        if (buttonText.charAt(0) == ScrabbleGame.BLANK) {
            String newChar = JOptionPane.showInputDialog("Enter a single letter: ").trim().toLowerCase();

            if (newChar.length() == 1) {
                model.getCurrentHand().changeBlankValue(handIndex, newChar.charAt(0));
            }
            return;
        }

        if (!buttonText.equals(" ")) {
            SelectionData data;

            data = new SelectionData(handIndex, handIndex, model.getCurrentHand().getHandPieces().get(handIndex));
            model.getCurrentHand().removePiece(handIndex);

            model.selectionController.addToHandButtons(data);
        }
    }

    public void selectBoardButton(int x, int y, String buttonText){
        if (buttonText.equals(" ")) { // empty spot, check if and pieces are selected
            if (!selectedHandButtons.isEmpty()) {
                SelectionData data = new SelectionData(x, y, selectedHandButtons.get(0).getPiece());
                selectedBoardButtons.add(data);
                model.getBoard().placePiece(data);
                selectedHandButtons.remove(0);
            }
        } else {
            model.getCurrentHand().addPiece(model.getBoard().getPiece(x, y));
            model.getBoard().removePiece(x, y);
            int index = 0;
            for (SelectionData sd : selectedBoardButtons) {
                if (sd.getX() == x && sd.getY() == y) {
                    index = selectedBoardButtons.indexOf(sd);
                }
            }
            selectedBoardButtons.remove(index);
        }
    }

}
