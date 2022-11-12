import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardFrame extends JPanel {
    private JButton[][] buttons;
    public ArrayList<JButton> selectedButtons = new ArrayList<>();
    private ArrayList<Piece> selectedPieces = new ArrayList<>();
    public BoardFrame(Board model, HandFrame hand) {
        super();
        this.setLayout(new GridLayout(Board.SIZE, Board.SIZE));
        this.setSize(200,200);


        buttons = new JButton[Board.SIZE][Board.SIZE];

        for (int i  = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                JButton b = new JButton(" ");
                buttons[i][j] = b;
                int x = i;
                int y = j;
                this.add(b);

                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {//gonna have to embed this inside hand actionperformed so that
                                                                // it places the letter on the board only if you click a letter in the hand
                        if (hand.getSelectedButton() != null) {//Checks if you have selected button from HandFrame
                            if (b.getText() == " ") { //Checks the Board button and sees if it is empty
                                b.setText(hand.getSelectedButton().getText()); //Sets the Board button text to the selected button letter
                                //***Ideally want to set this button to the button from HandFrame
                                //*** But b = hand.getSelectedButton() does not work
                                selectedButtons.add(hand.getSelectedButton()); // keep track of all buttons selected
                                selectedPieces.add(new Piece(hand.getSelectedButton().getText().charAt(0))); //succesfully adds the correct piece to selectedPieces
                                //Need to change this later so that once you add a piece onto the board, it removes the piece
                                //from the hand. Currently only removes from the handFrame
                                //Have issues indexing the the hand arraylist while removing items
                                System.out.println(selectedPieces.size());
                                System.out.println(selectedPieces.get(0).getLetter());
                                for (int j=0; j < hand.getHand().getHandPieces().size(); j++) {
                                        if (hand.getHand().getHandPieces().get(j).equals(selectedPieces.get(selectedPieces.size()-1))) {
                                            hand.getHand().removePiece(j);
                                            System.out.println("somehow got here");
                                            break;
                                        }//make copy of hand and remove from hand

                                }
//
                                hand.remove(hand.getSelectedButton()); // remove the selected button from handframe
                                hand.setSelectedButton(null); // make the selectedButton empty once you place it on board
                            }
                        }
    // **************** Testing SelectedButtons, Hand, and SelectedPieces *********************//
                        System.out.println("");
                        System.out.println("");
                        System.out.print("Selected Buttons: ");
                        for (int i = 0; i < selectedButtons.size(); i++) {
                            System.out.print(selectedButtons.get(i).getText() + " ");
                        }
                        System.out.println("");
                        System.out.println("");
                        System.out.print("Hand: ");
                        for (int i = 0; i < hand.getHand().getHandPieces().size(); i++) {
                            System.out.print(hand.getHand().getHandPieces().get(i).getLetter() + " ");
                        }

                        System.out.print("Selected Pieces: ");
                        for (int i = 0; i < selectedPieces.size(); i++) {
                            System.out.print(selectedPieces.get(i).getLetter() + " ");
                        }
                        System.out.println("");
        // ********************* END of testing *********************//              /
                    }
                });

                b.addActionListener(new ActionListener() { // removing a button from the boardFrame
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (b.getText() != " ") { // Makes sure you select a button that is not empty
                            for (int i = 0; i < selectedButtons.size(); i++ ){ //makes sure the button you're removing is the one you added
                                if (selectedButtons.get(i).getText() == b.getText()){
                                    System.out.println("Deselected Button: " + b.getText());
                                    JButton newB = new JButton(b.getText());
                                    newB.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            hand.setSelectedButton(newB);
                                        }
                                    });
                                    hand.add(newB); // add button to back of Handframe

                                    hand.getHand().addPiece(new Piece(b.getText().charAt(0)));
                                    int count = 0;
                                    for (JButton selB : selectedButtons) {
                                        if (selB.getText() == b.getText()) {
                                            selectedButtons.remove(count);
                                            selectedPieces.remove(count);
                                            break;
                                        }
                                        count++;

                                    }
                                    b.setText(" "); //set the button to empty text
                                    hand.setSelectedButton(null);
                                    //we got to remove from selectedButtons and selectedPieces
                                }
                            }

                        }
                    }
                });
            }
        }
       this.setVisible(true);

    }
    }
    /*
    public static void main(String args[]) {
        new BoardFrame(new Board(), new HandFrame());
    }
}
*/

