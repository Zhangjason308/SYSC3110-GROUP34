import javax.swing.*;
import java.awt.*;
public class InfoPanel extends JPanel implements ScrabbleView{
    private JLabel tilesLeftLabel;
    private JLabel playerOne;
    private JLabel playerTwo;
    private JLabel gameTurnLabel;
    private JLabel playerOneScore;
    private JLabel playerTwoScore;

    private int tilesLeft;
    private int p1ActualScore;
    private int p2ActualScore;
    private boolean gameTurn;

    public InfoPanel(){
        super();
        tilesLeft = 0;
        p1ActualScore = 0;
        p2ActualScore = 0;
        gameTurn = true;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        tilesLeftLabel = new JLabel(String.valueOf(tilesLeft));
        add(tilesLeftLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        playerOne = new JLabel("Player One Score:");
        add(playerOne, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        playerTwo = new JLabel("Player Two Score:");
        add(playerTwo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gameTurnLabel = new JLabel("turn: " + (gameTurn ? "Player 1" : "Player 2"));
        add(gameTurnLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        playerOneScore = new JLabel(String.valueOf(p1ActualScore));
        add(playerOneScore, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        playerTwoScore = new JLabel(String.valueOf(p2ActualScore));
        add(playerTwoScore, gbc);

        setVisible(true);

    }
    @Override
    public void update(ScrabbleGame model) {
        p1ActualScore = model.getPlayer1Score();
        p2ActualScore = model.getPlayer2Score();
        tilesLeft = model.getBag().numberOfRemainingPieces();
        gameTurn = model.getTurn();

        playerOneScore.setText(String.valueOf(p1ActualScore));
        playerTwoScore.setText(String.valueOf(p2ActualScore));
        tilesLeftLabel.setText(String.valueOf(tilesLeft));
        gameTurnLabel.setText("turn: " + (gameTurn ? "Player 1" : "Player 2"));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new InfoPanel());
        frame.setSize(800, 1000);
        frame.setVisible(true);

    }
}
