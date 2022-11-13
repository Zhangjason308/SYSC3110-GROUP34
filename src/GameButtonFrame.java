import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtonFrame extends JPanel implements ActionListener {
    JButton play;
    JButton skip;
    JButton swap; //
    ScrabbleGame game;

    Game g;
    public GameButtonFrame(ScrabbleGame game) {
        super();
        this.game = game;
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
        GameButtonFrame gameFrame = new GameButtonFrame(new ScrabbleGame());
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
                int x = 0;
                int y = 0;
                game.play(x,y);
                //to be changed later
            }
            else if(button == skip){

                game.skip();
                
            }

            else if(button == swap){

                game.swap();

                String x = "temp";
                //to be changed later

            }
        }
    }
}
