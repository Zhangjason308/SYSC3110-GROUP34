import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JPanel {
    private JButton[][] buttons;
    public BoardFrame(Board model) {
        super();
        this.setLayout(new GridLayout(Board.SIZE, Board.SIZE));
        //this.setSize(200,200);


        buttons = new JButton[Board.SIZE][Board.SIZE];

        for (int i  = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                JButton b = new JButton(" ");
                buttons[i][j] = b;
                int x = i;
                int y = j;
                this.add(b);
            }
        }
       this.setVisible(true);
    }
    public static void main(String args[]) {
        new BoardFrame(new Board());
    }
}
