import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel implements ScrabbleView {////

    static private JButton[][] buttons;
    static private int boardNumber;

    final static int NUM_OF_BLUE_POSITIONS = 8;
    final static int NUM_OF_RED_POSITIONS = 16;

    //BOARD 1 SPECIAL TILES - REGULAR
    final static int[][] MULTIPLIER_POSITIONS_BLUE_BOARD1 = {{0, 14, 0, 14, 5, 9, 5, 9}, {0, 0, 14, 14, 5, 5, 9, 9}};
    final static int[][] MULTIPLIER_POSITIONS_RED_BOARD1 = {{1, 2, 3, 4, 13, 12, 11, 10, 1, 2, 3, 4, 13, 12, 11, 10}, {1, 2, 3, 4, 1, 2, 3, 4, 13, 12, 11, 10, 13, 12, 11, 10}};

    //BOARD 2 SPECIAL TILES - INVERTED
    final static int[][] MULTIPLIER_POSITIONS_BLUE_BOARD2 = {{1, 2, 3, 4, 13, 12, 11, 10, 1, 2, 3, 4, 13, 12, 11, 10}, {1, 2, 3, 4, 1, 2, 3, 4, 13, 12, 11, 10, 13, 12, 11, 10}};
    final static int[][] MULTIPLIER_POSITIONS_RED_BOARD2 = {{0, 14, 0, 14, 5, 9, 5, 9}, {0, 0, 14, 14, 5, 5, 9, 9}};

    //BOARD 3 SPECIAL TILES - CHECKERED
    final static int[][] MULTIPLIER_POSITIONS_BLUE_BOARD3 = {{0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,4,4,4,4,4,4,4,4,6,6,6,6,6,6,6,6,8,8,8,8,8,8,8,8,10,10,10,10,10,10,10,10,12,12,12,12,12,12,12,12,14,14,14,14,14,14,14,14}, {0,2,4,6,8,10,12,14,0,2,4,6,8,10,12,14,0,2,4,6,8,10,12,14,0,2,4,6,8,10,12,14,0,2,4,6,8,10,12,14,0,2,4,6,8,10,12,14,0,2,4,6,8,10,12,14,0,2,4,6,8,10,12,14}};
    final static int[][] MULTIPLIER_POSITIONS_RED_BOARD3 = {{1,1,1,1,1,1,1,3,3,3,3,3,3,3,5,5,5,5,5,5,5,7,7,7,7,7,7,9,9,9,9,9,9,9,11,11,11,11,11,11,11,13,13,13,13,13,13,13}, {1,3,5,7,9,11,13,1,3,5,7,9,11,13,1,3,5,7,9,11,13,1,3,5,9,11,13,1,3,5,7,9,11,13,1,3,5,7,9,11,13,1,3,5,7,9,11,13}};

    //I Apologize for the lack of compassion and consideration :)
    final static int[][][][] BOARD_TYPES = {{MULTIPLIER_POSITIONS_BLUE_BOARD1,MULTIPLIER_POSITIONS_RED_BOARD1},{MULTIPLIER_POSITIONS_BLUE_BOARD2,MULTIPLIER_POSITIONS_RED_BOARD2},{MULTIPLIER_POSITIONS_BLUE_BOARD3,MULTIPLIER_POSITIONS_RED_BOARD3}};
    public BoardPanel(ScrabbleController controller, int boardNumber) {
        super();
        this.setLayout(new GridLayout(Board.SIZE, Board.SIZE));
        this.setSize(200,200);
        this.boardNumber = boardNumber;


// why u following me huh

        buttons = new JButton[Board.SIZE][Board.SIZE];

        for (int i  = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                JButton b = new JButton(" ");
                b.setOpaque(true);
                b.setActionCommand(j + " " +i);
                b.addActionListener(controller);
                buttons[j][i] = b;
                this.add(b);
            }
        }

        for (int i = 0; i < BOARD_TYPES[boardNumber][0][0].length; i++){
            buttons[BOARD_TYPES[boardNumber][0][0][i]][BOARD_TYPES[boardNumber][0][1][i]].setBackground(Color.blue);
        }
        for (int i = 0; i < BOARD_TYPES[boardNumber][1][0].length; i++){
            buttons[BOARD_TYPES[boardNumber][1][0][i]][BOARD_TYPES[boardNumber][1][1][i]].setBackground(Color.red);
        }


        buttons[7][7].setBackground(Color.orange);
        this.setVisible(true);

    }

     public void setBoardNumber(int boardNumber) {
        this.boardNumber = boardNumber;
    }

    static public int getBoardNumber() {
        return boardNumber;
    }

    static public void disableButtons(ArrayList<SelectionData> sData){
        for (SelectionData sd : sData) {
            int x = sd.getX();
            int y = sd.getY();
            buttons[x][y].setEnabled(false);
        }
    }

    static public void resetDisabledButtons(){
        for (int i = 0; i<15; i++) {
            for(int j = 0; j<15; j++){
                buttons[j][i].setEnabled(true);
            }
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
