import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MenuBarController implements ActionListener {
    private ScrabbleGame model;
    public MenuBarController(ScrabbleGame model) {
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JMenuItem) {
            JMenuItem menuItem = (JMenu)
        }
    }
}
