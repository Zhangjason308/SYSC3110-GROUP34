import javax.swing.*;
import java.awt.*;

public class ScrabbleFrame extends JFrame implements ScrabbleView {

    public ScrabbleFrame() {
        super("Scrabble");
        ScrabbleGame model = new ScrabbleGame();
        //Bag bag = new Bag();
        //Hand hand = new Hand();
        HandFrame handF = new HandFrame(model.player1Hand);
        BoardFrame boardF = new BoardFrame(model.scrabbleBoard, handF);
        GameButtonFrame gameButtonF = new GameButtonFrame();
        JTextArea gameName = new JTextArea("SCRABBLE"); //Make it have an actionlistener to change player everytime
        //gameName.setAlignmentX();
        InfoPanel infoP = new InfoPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(20,20));
        this.add(gameName, BorderLayout.NORTH);
        this.add(handF, BorderLayout.SOUTH);
        this.add(gameButtonF, BorderLayout.LINE_START);
        this.add(boardF, BorderLayout.CENTER);
        this.add(infoP, BorderLayout.LINE_END);
        this.setSize(1000,800);
        boardF.setSize(100,100);
        gameButtonF.setSize(100,100);
        this.setVisible(true);

    }


    public static void main(String args[]) {
        new ScrabbleFrame();
    }

    @Override
    public void updateHandFrame(int x, int y, Piece selectedPiece) {

    }

    @Override
    public void updateInfoPanel(int p1, int p2, Bag baggy) {

    }
}
