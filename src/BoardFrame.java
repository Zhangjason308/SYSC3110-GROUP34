import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardFrame extends JPanel {
    private JButton[][] buttons;
    public ArrayList<JButton> selectedButtons = new ArrayList<>();
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
                        if (hand.getSelectedButton() != null) {
                            if (b.getText() == " ") {
                                b.setText(hand.getSelectedButton().getText());
                                selectedButtons.add(hand.getSelectedButton());
                                hand.remove(hand.getSelectedButton());
                                hand.setSelectedButton(null);
                            }
                                if (b.getText() != " ") {
                                    System.out.println("Deselected Button: " + b.getText());
                                    //b.setText("");
                                    hand.add(new JButton(b.getText()));
                                    hand.getHand().getHandPieces().remove(new Piece('a'));
                                    selectedButtons.remove(b);
                                    hand.setSelectedButton(null);

                            }
                        }



                        
                        System.out.print("Hand: ");
                        for (int i = 0; i < hand.getHand().getHandPieces().size(); i++) {
                            System.out.print(hand.getHand().getHandPieces().get(i).getLetter() + " ");
                        }
                        System.out.println("");
                        System.out.print("Selected Buttons: ");
                        for (int i = 0; i < selectedButtons.size(); i++) {
                            System.out.print(selectedButtons.get(i).getText() + " ");
                        }
                        System.out.println("");
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

