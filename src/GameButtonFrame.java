import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtonFrame extends JPanel implements ActionListener {
    JButton play;
    JButton skip;
    JButton swap;
    public GameButtonFrame() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(600,500);
         play = new JButton("Play");
         skip = new JButton("Skip");
         swap = new JButton("Swap");
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(play);
        this.add(skip);
        this.add(swap);
        play.addActionListener(this);
        skip.addActionListener(this);
        swap.addActionListener(this);

        this.setVisible(true);

    }
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        GameButtonFrame gameFrame = new GameButtonFrame();
        frame.add(gameFrame);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {
            JButton button = (JButton)o;

            if(button == play){
                //do something
            }
            else if(button == skip){

            }

            else if(button == swap){

            }
        }
    }
}
