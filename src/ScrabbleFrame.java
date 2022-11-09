import javax.swing.*;
import java.awt.*;
public class ScrabbleFrame extends JFrame implements ScrabbleView {

    private JButton[][] buttons;
    public ScrabbleFrame() {
        super ("Scrabble");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(ScrabbleGame.SIZE, ScrabbleGame.SIZE)); // 15x15

        ScrabbleGame model = new ScrabbleGame();

        model.addScrabbleView(this);

        buttons = new JButton[ScrabbleGame.SIZE][ScrabbleGame.SIZE];

        ScrabbleController sc = new ScrabbleController(model);

        for (int i = 0; i < ScrabbleGame.SIZE; i++) {
            for (int j = 0; j < ScrabbleGame.SIZE; j++) {
                JButton b = new JButton(" ");
                b.setActionCommand(i + " " + j); // just the label of the button
                buttons[i][j] = b;
                b.addActionListener(sc); //pass it the controller to run actionPerformed when clicked, with that button's info
            }
        }
        this.setVisible(true); // add a new set of buttons for hand, and change visibility of hand based on players turn (they overlap if thats possible)
    }
    @Override
    public void update(int x, int y, Piece selectedPiece) {

        buttons[x][y].setText(Character.toString(selectedPiece.getLetter()));
    }
    public static void main(String args[]) {
        new ScrabbleFrame();
    }
}
