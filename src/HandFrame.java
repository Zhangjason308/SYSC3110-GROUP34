import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandFrame extends JPanel implements ScrabbleView{
    private JButton[] buttons;
    public HandFrame(Hand model) {
        super();
        GridLayout buttonGrid = new GridLayout(1, ScrabbleGame.HAND_SIZE);

        this.setSize(800, 100);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // fix all this

        gbc.gridheight = 1;
        this.setLayout(buttonGrid); // 1X7
        buttons = new JButton[ScrabbleGame.HAND_SIZE];

        //ScrabbleController sc = new ScrabbleController(model);

        for (int i = 0; i < ScrabbleGame.HAND_SIZE; i++) {
            Piece p = model.getHandPieces().get(i);
            JButton b = new JButton("" + p.getLetter());
            b.setActionCommand("" + i);

            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // these are the 1x7 buttons in the hand
                    // they need to add the piece associated to the hand to the selectedPiece variable
                }
            });

            //b.setActionCommand(Character.toString(hand.getHandPieces().get(i).getLetter())); // just the label of the button
            buttons[i] = b;
            //b.addActionListener(sc); //pass it the controller to run actionPerformed when clicked, with that button's info
            this.add(buttons[i]);
        }
        this.setVisible(true); // add a new set of buttons for hand, and change visibility of hand based on players turn (they overlap if thats possible)
    }
    @Override
    public void update(int x, int y, Piece selectedPiece) {
        // to update hand needs a reference to the actionCommand of the button pressed

        Hand hand = new Hand(); // this will need to be passed as a parameter (added to so code will work for now)
        buttons[x].setText(Character.toString(selectedPiece.getLetter()));
    }

    public static void main(String args[]) {

        Bag bag = new Bag();
        Hand hand = new Hand();
        hand.addPieces(bag.grabPieces(7));
        JFrame fream = new JFrame();
        fream.add(new HandFrame(hand));
        fream.setVisible(true); // edit
    }
}
