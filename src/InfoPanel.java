import javax.swing.*;
import java.awt.*;
public class InfoPanel extends JPanel{
    private JLabel tilesLeftLabel;
    private JLabel playerOne;
    private JLabel playerTwo;
    private JLabel playerOneScore;
    private JLabel playerTwoScore;

    private int tilesLeft;
    private int p1ActualScore;
    private int p2ActualScore;

    public InfoPanel(){
        super();
        this.setSize(400, 500);
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
    public void updateInfoPanel(Game.Player p1, Game.Player p2, Bag baggy) {
        p1ActualScore = p1.score;
        p2ActualScore = p2.score;
        tilesLeft = baggy.numberOfRemainingPieces();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new InfoPanel());
        frame.setSize(800, 1000);
        frame.setVisible(true);

    }
}
