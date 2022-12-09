import javax.swing.*;
import java.awt.*;

public class ScrabbleFrame extends JFrame {
    int selectedButton;
    boolean player2Selected;
    public ScrabbleFrame(int selectedButton, boolean player2Selected ) {
        super("Scrabble");
        ScrabbleGame model = new ScrabbleGame(selectedButton, player2Selected);
        ScrabbleController sc = new ScrabbleController(model);
        HandPanel handPanel = new HandPanel(sc);
        BoardPanel boardPanel = new BoardPanel(sc, selectedButton);
        GameButtonPanel gameButtonPanel = new GameButtonPanel(sc);
        MenuBarController menuBarController = new MenuBarController(model);
        MenuBarPanel menuBarPanel = new MenuBarPanel(menuBarController);
        InfoPanel infoPanel = new InfoPanel();
        model.addScrabbleView(handPanel);
        model.addScrabbleView(boardPanel);
        model.addScrabbleView(infoPanel);
        final JTextArea gameName = new JTextArea("SCRABBLE"); //Make it have an actionlistener to change player everytime
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(20,20));
        this.add(menuBarPanel, BorderLayout.NORTH);
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


//Notes: when the play button is pressed

    //1: word and branch words are gotten
    //2: if valid continue
    //3: the turn is changed
    //4: selectedBoardButtons are erased and the pieces on the board stay
}