import javax.swing.*;
import java.awt.*;

public class ScrabbleFrame extends JFrame implements ScrabbleView {
    JTextArea playerName = new JTextArea("Player 1"); //Make it have an actionlistener to chenge player everytime
    public ScrabbleFrame() {
        super("Scrabble");
        BoardFrame board = new BoardFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(playerName, BorderLayout.NORTH);
        //this.add(handFrame, BorderLayout.CENTER);
        this.add(board, BorderLayout.CENTER);
        this.add(board, BorderLayout.CENTER);
        this.setSize(800,800);
        this.setVisible(true);

    }
    @Override
    public void update(int x, int y, Piece selectedPiece) {

    }

    public static void main(String args[]) {
        new ScrabbleFrame();
    }
}
