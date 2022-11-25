import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel implements ScrabbleView {

    static private JButton[][] buttons;

    final static int NUM_OF_BLUE_POSITIONS = 8;
    final static int NUM_OF_RED_POSITIONS = 16;
    final static int[][] MULTIPLIER_POSITIONS_BLUE = {{0, 14, 0, 14, 5, 9, 5, 9}, {0, 0, 14, 14, 5, 5, 9, 9}};

    final static int[][] MULTIPLIER_POSITIONS_RED = {{1, 2, 3, 4, 13, 12, 11, 10, 1, 2, 3, 4, 13, 12, 11, 10}, {1, 2, 3, 4, 1, 2, 3, 4, 13, 12, 11, 10, 13, 12, 11, 10}};

    public BoardPanel(ScrabbleController controller) {
        super();
        this.setLayout(new GridLayout(Board.SIZE, Board.SIZE));
        this.setSize(200,200);


        buttons = new JButton[Board.SIZE][Board.SIZE];

        for (int i  = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                JButton b = new JButton(" ");
                //b.setBackground(Color.RED);
                b.setOpaque(true);
                b.setActionCommand(j + " " +i);
                b.addActionListener(controller);
                buttons[j][i] = b;
                this.add(b);
            }
        }
        //this.setForeground(Color.RED);
        //this.setOpaque(true);

        for (int i = 0; i < NUM_OF_BLUE_POSITIONS; i++){
            buttons[MULTIPLIER_POSITIONS_BLUE[0][i]][MULTIPLIER_POSITIONS_BLUE[1][i]].setBackground(Color.blue);
        }
        for (int i = 0; i < NUM_OF_RED_POSITIONS; i++){
            buttons[MULTIPLIER_POSITIONS_RED[0][i]][MULTIPLIER_POSITIONS_RED[1][i]].setBackground(Color.red);
        }
        buttons[7][7].setBackground(Color.orange);
        this.setVisible(true);

            }

    static public void disableButtons(ArrayList<SelectionData> sData){
        for (SelectionData sd : sData) {
            int x = sd.getX();
            int y = sd.getY();
            buttons[x][y].setEnabled(false);
        }
    }
    @Override
    public void update(ScrabbleGame model) {
        Board board = model.getBoard();

        for (int i  = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                buttons[j][i].setText(Character.toString(board.getPiece(j, i).getLetter()));
            }
        }
    }
}

