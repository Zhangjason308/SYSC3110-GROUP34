import javax.swing.*;
import java.awt.*;

public class ScrabbleFrame extends JFrame implements ScrabbleView {
    JTextArea playerName = new JTextArea("Player 1"); //Make it have an actionlistener to chenge player everytime
    public ScrabbleFrame() {
        super("Scrabble");
        Bag bag = new Bag();
        Hand hand = new Hand();
        hand.addPieces(bag.grabPieces(7));
        HandFrame handF = new HandFrame(hand);
        BoardFrame boardF = new BoardFrame(new Board(), handF);
        GameButtonFrame gameButtonF = new GameButtonFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(20,20));
        this.add(playerName, BorderLayout.NORTH);
        this.add(handF, BorderLayout.SOUTH);
        this.add(gameButtonF, BorderLayout.LINE_START);
        this.add(boardF, BorderLayout.CENTER);
        this.setSize(500,500);
        boardF.setSize(100,100);
        gameButtonF.setSize(100,100);

        this.setVisible(true);

    }
    @Override
    public void update(int x, int y, Piece selectedPiece) {


    }

    public static void main(String args[]) {
        new ScrabbleFrame();
    }
}
