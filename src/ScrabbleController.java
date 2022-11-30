import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScrabbleController implements ActionListener {
    private ScrabbleGame model;
        public static String PLAY = "Play";
    public static String SWAP = "Swap";
    public static String SKIP = "Skip";

    public ScrabbleController(ScrabbleGame model) {
        this.model = model;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {  //since it's implemented like this we can also make a JBoardButton class and JHandButton and then just say if o is instance of each
            JButton button = (JButton) o;
            String[] input = button.getActionCommand().split(" ");

            if (button.getText() == PLAY) {
                model.play();
            } else if (button.getText() == SKIP) {
                    model.skip();
            } else if (button.getText() == SWAP) {
                    model.swap();
            } else if (input.length == 1) { // Button From: HAND
                    int handIndex = Integer.parseInt(input[0]);
                    model.selectHandButton(handIndex, button.getText());
            } else if (input.length == 2) { // Button From: BOARD
                    int x = Integer.parseInt(input[0]);
                    int y = Integer.parseInt(input[1]);
                    model.selectBoardButton(x, y, button.getText());
            }
        }
    }
}