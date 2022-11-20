import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScrabbleFrame extends JFrame {

    public ScrabbleFrame() {
        super("Scrabble");
        ScrabbleGame model = new ScrabbleGame();
        ScrabbleController sc = new ScrabbleController(model);
        HandPanel handPanel = new HandPanel(sc);
        BoardPanel boardPanel = new BoardPanel(sc);
        GameButtonPanel gameButtonPanel = new GameButtonPanel(sc);
        InfoPanel infoPanel = new InfoPanel();
        model.addScrabbleView(handPanel);
        model.addScrabbleView(boardPanel);
        model.addScrabbleView(infoPanel);
        final JTextArea gameName = new JTextArea("SCRABBLE"); //Make it have an actionlistener to change player everytime
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(20,20));
        this.add(gameName, BorderLayout.NORTH);
        this.add(handPanel, BorderLayout.SOUTH);
        this.add(gameButtonPanel, BorderLayout.LINE_START);
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.LINE_END);
        this.setSize(1000,800);
        handPanel.setSize(800, 100);
        boardPanel.setSize(700 ,700);
        gameButtonPanel.setSize(100,100);
        infoPanel.setSize(100, 800);
        this.setVisible(true);

        model.updateViews();
    }


    public static void main(String args[]) {
        new ScrabbleFrame();
    }

//Notes: when the play button is pressed

    //1: word and branch words are gotten
    //2: if valid continue
    //3: the turn is changed
    //4: selectedBoardButtons are erased and the pieces on the board stay
}
