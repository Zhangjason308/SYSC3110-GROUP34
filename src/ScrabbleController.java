import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrabbleController implements ActionListener{

    private ScrabbleGame model;

    public ScrabbleController(ScrabbleGame model){
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] input = e.getActionCommand().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);

        model.play(x,y);
    }
}
