import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartFrameController implements ActionListener {

    public static String BOARD1 = "Board1";
    public static String BOARD2 = "Board2";
    public static String BOARD3 = "Board3";

    public static String START = "Start";
    ScrabbleFrame frame;
    int selectedButton;
    boolean player2Selected;

    public StartFrameController() {
        selectedButton = 0;
        player2Selected = false;
        frame = new ScrabbleFrame(selectedButton,player2Selected);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JRadioButton) {

            JRadioButton button = (JRadioButton) o;

                if (button.getText() == BOARD1) {
                    //TODO
                }
                if (button.getText() == BOARD2) {
                    //TODO
                }

                if (button.getText() == BOARD3) {
                    //TODO
                }

        if(o instanceof JButton){

        }
            JButton button1 = (JButton) o;
            if (button1.getText() == START){
                
                frame = new ScrabbleFrame(selectedButton,player2Selected);
            }
        }
    }

}