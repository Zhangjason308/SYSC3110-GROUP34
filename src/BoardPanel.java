import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel implements ScrabbleView {
    private JButton[][] buttons;

    public BoardPanel(ScrabbleController controller) {
        super();
        this.setLayout(new GridLayout(Board.SIZE, Board.SIZE));
        this.setSize(200,200);

        buttons = new JButton[Board.SIZE][Board.SIZE];

        for (int i  = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                JButton b = new JButton(" ");
                b.setActionCommand(i + " " +j);
                b.addActionListener(controller);
                buttons[i][j] = b;
                this.add(b);
            }
        }
        this.setVisible(true);

            }

    @Override
    public void update(ScrabbleGame model) {
        Board board = model.getBoard();

        for (int i  = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                buttons[i][j].setText(Character.toString(board.getPiece(i, j).getLetter()));
            }
        }
    }
}
