import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardFrame extends JPanel {
    private JButton[][] buttons;
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
                    public void actionPerformed(ActionEvent e) {//gonna have to embed this inside hand actoinperformed so that
                                                                // it places the letter on the board only if you click a letter in the hand
                        if (b.getText() == " ") {
                            b.setText(hand.getSelectedButton().getText());
                        }
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

