import javax.swing.*;
import java.awt.*;

public class HandFrame extends JPanel implements ScrabbleView{
    private JButton[] buttons;
    public HandFrame(Bag bag, Hand hand) {
        super();
        this.setLayout(new GridLayout(1, ScrabbleGame.HAND_SIZE)); // 1X7

        buttons = new JButton[ScrabbleGame.HAND_SIZE];

        //ScrabbleController sc = new ScrabbleController(model);

        for (int i = 0; i < ScrabbleGame.HAND_SIZE; i++) {
            Piece p = hand.getHandPieces().get(i);
            JButton b = new JButton("" + p.getLetter());
            //b.setActionCommand(Character.toString(hand.getHandPieces().get(i).getLetter())); // just the label of the button
            buttons[i] = b;
            //b.addActionListener(sc); //pass it the controller to run actionPerformed when clicked, with that button's info
            this.add(buttons[i]);
        }
        this.setVisible(true); // add a new set of buttons for hand, and change visibility of hand based on players turn (they overlap if thats possible)
    }
    @Override
    public void update(int x, int y, Piece selectedPiece) {
        // to update hand needs a reference to the x, y, and selectedPiece

        buttons[x].setText(Character.toString(selectedPiece.getLetter()));
    }

    public static void main(String args[]) {
        Bag bag = new Bag();
        Hand hand = new Hand();
        new HandFrame(bag, hand);

    }
}
