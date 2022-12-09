import javax.swing.*;
import java.awt.*;

public class HandPanel extends JPanel implements ScrabbleView{//
    private JButton[] buttons;

    public HandPanel(ScrabbleController controller) {
        super();
        GridLayout buttonGrid = new GridLayout(1, ScrabbleGame.HAND_SIZE);
        this.setLayout(buttonGrid); // 1X7

        buttons = new JButton[ScrabbleGame.HAND_SIZE];

        for(int i = 0; i < ScrabbleGame.HAND_SIZE; i++){

            buttons[i] = new JButton(" ");
            buttons[i].setActionCommand("" + i);
            buttons[i].addActionListener(controller);
            this.add(buttons[i]);
        }

        this.setVisible(true);
    }

    @Override
    public void update(ScrabbleGame model) {
        Hand playerHand = model.getTurn() ? model.getPlayer1Hand(): model.getPlayer2Hand();
        System.out.println(playerHand.toString());

        int missing = playerHand.sizeOfHand();
        if(missing == 0){
            buttons[missing].setText(" ");
        }
        else{
            for (int i = 0; i < playerHand.sizeOfHand(); i++){// Piece p : playerHand.getHandPieces())
                buttons[i].setText(Character.toString(playerHand.getHandPieces().get(i).getLetter()));
                if(missing < 7){
                    buttons[missing].setText(" ");
                    missing++;
                }
            }
        }
    }
}