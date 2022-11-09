import javax.swing.*;
import java.awt.*;

public class HandFrame extends JFrame implements ScrabbleView{
    private JButton[] buttons;
    public HandFrame(Bag bag, Hand hand) {
        super ("Scrabble");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, ScrabbleGame.HAND_SIZE)); // 7X1

        //ScrabbleGame model = new ScrabbleGame();

        //model.addScrabbleView(this);

        buttons = new JButton[ScrabbleGame.HAND_SIZE];

        //ScrabbleController sc = new ScrabbleController(model);

        for (int i = 0; i < ScrabbleGame.HAND_SIZE; i++) {
            JButton b = new JButton(" ");
            b.setActionCommand(Character.toString(hand.getHandPieces().get(i).getLetter())); // just the label of the button
            buttons[i] = b;
            //b.addActionListener(sc); //pass it the controller to run actionPerformed when clicked, with that button's info
            this.add(b);
        }
        this.setVisible(true); // add a new set of buttons for hand, and change visibility of hand based on players turn (they overlap if thats possible)
    }
    @Override
    public void update(int x, int y, Piece selectedPiece) {

        buttons[x].setText(Character.toString(selectedPiece.getLetter()));

    }
}
