import javax.swing.*;
import java.awt.*;

public class GameButtonPanel extends JPanel {//
    JButton play;
    JButton skip;
    JButton swap; //
    ScrabbleGame game;

    public GameButtonPanel(ScrabbleController controller) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(600,500);
        play = new JButton(ScrabbleController.PLAY);
        skip = new JButton(ScrabbleController.SKIP);
        swap = new JButton(ScrabbleController.SWAP);
        this.add(play);
        this.add(skip);
        this.add(swap);
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        skip.setAlignmentX(Component.CENTER_ALIGNMENT);
        swap.setAlignmentX(Component.CENTER_ALIGNMENT);
        play.addActionListener(controller);
        skip.addActionListener(controller);
        swap.addActionListener(controller);

        this.setVisible(true);

    }

}