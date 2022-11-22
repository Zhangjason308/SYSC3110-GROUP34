import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel implements ScrabbleView {
    static private JButton[][] buttons;

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
                b.setActionCommand(i + " " +j);
                b.addActionListener(controller);
                buttons[i][j] = b;
                this.add(b);
            }
        }
        //this.setForeground(Color.RED);
        //this.setOpaque(true);
        buttons[0][0].setBackground(Color.blue);
        buttons[14][0].setBackground(Color.blue);
        buttons[0][14].setBackground(Color.blue);
        buttons[14][14].setBackground(Color.blue);
        buttons[1][1].setBackground(Color.red);
        buttons[2][2].setBackground(Color.red);
        buttons[3][3].setBackground(Color.red);
        buttons[4][4].setBackground(Color.red);
        buttons[5][5].setBackground(Color.blue);
        buttons[13][1].setBackground(Color.red);
        buttons[12][2].setBackground(Color.red);
        buttons[11][3].setBackground(Color.red);
        buttons[10][4].setBackground(Color.red);
        buttons[9][5].setBackground(Color.blue);
        buttons[1][13].setBackground(Color.red);
        buttons[2][12].setBackground(Color.red);
        buttons[3][11].setBackground(Color.red);
        buttons[4][10].setBackground(Color.red);
        buttons[5][9].setBackground(Color.blue);
        buttons[13][13].setBackground(Color.red);
        buttons[12][12].setBackground(Color.red);
        buttons[11][11].setBackground(Color.red);
        buttons[10][10].setBackground(Color.red);
        buttons[9][9].setBackground(Color.blue);
        buttons[7][7].setBackground(Color.orange);
        this.setVisible(true);

            }
    public int getMultipliers(int x, int y) {
        int multiplier;
        if (buttons[x][y].getBackground() == Color.WHITE) {
             multiplier = 1;
        }
        else {
            if (buttons[x][y].getBackground() == Color.blue) {
                multiplier = 3;
            } else { //color red
                multiplier = 2;
            }
            buttons[x][y].setBackground(Color.WHITE);
        }
        return multiplier;
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
                buttons[i][j].setText(Character.toString(board.getPiece(i, j).getLetter()));
            }
        }
    }
}

