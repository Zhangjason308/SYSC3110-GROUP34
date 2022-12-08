import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartFrameController implements ActionListener {

    public static String BOARD1 = "Board1";
    public static String BOARD2 = "Board2";
    public static String BOARD3 = "Board3";

    public static int SEL1 = 0;
    public static int SEL2 = 1;
    public static int SEL3 = 2;

    public static String PLAYER2 = "Player2";
    public static String AI = "AI";
    public static String START = "START";
//    ScrabbleFrame frame;

    StartFrame startFrame;
    int selectedButton;
    boolean player2Selected;

    public StartFrameController(StartFrame startFrame) {
        selectedButton = 0;
        player2Selected = false;
        // frame = new ScrabbleFrame(selectedButton,player2Selected);
        this.startFrame = startFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if(o instanceof JButton){

            JButton button = (JButton) o;
            if (button.getText() == START){
                System.out.println("this ran --------------- yay");

                if(startFrame.getButtonGroup().getSelection() != null){

                    if(startFrame.getButtonGroup().getSelection().getActionCommand() == PLAYER2 ){
                        player2Selected = true;
                    }

                    else if(startFrame.getButtonGroup().getSelection().getActionCommand() == AI ){
                        player2Selected = false;
                    }
                    if(startFrame.getBoardGroup().getSelection().getActionCommand() == BOARD1 ){

                        selectedButton = SEL1;
                    }

                    else if(startFrame.getBoardGroup().getSelection().getActionCommand() == BOARD2 ){

                        selectedButton = SEL2;
                    }
                    else if(startFrame.getBoardGroup().getSelection().getActionCommand() == BOARD3 ){

                        selectedButton = SEL3;
                    }
                }

                new ScrabbleFrame(selectedButton,player2Selected);
                startFrame.dispose();
            }
        }
    }
}