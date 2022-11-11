import javax.swing.*;
import java.awt.*;

public class GameButtonFrame extends JPanel {
    public GameButtonFrame() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(600,500);
        JButton play = new JButton("Play");
        JButton skip = new JButton("Skip");
        JButton swap = new JButton("Swap");
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(play);
        this.add(skip);
        this.add(swap);
        this.setVisible(true);

    //kk

    }
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        GameButtonFrame gameFrame = new GameButtonFrame();
        frame.add(gameFrame);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
}
