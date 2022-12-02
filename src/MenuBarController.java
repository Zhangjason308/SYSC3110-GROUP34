import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MenuBarController implements ActionListener {
    private ScrabbleGame model;

    private MenuBarPanel panel;

    private static final String SAVE = "Save";
    private static final String LOAD = "Load";
    private static final String REDO = "Redo";
    private static final String UNDO = "Undo";
    public MenuBarController(ScrabbleGame model) {
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JMenuItem) {
            JMenuItem menu = (JMenuItem) o;
            if (menu.getText().equals(SAVE)) {
                String fileName = JOptionPane.showInputDialog("enter name of file to be saved to: ").trim();
                model.saveSerializable(fileName);
            } else if (menu.getText().equals(LOAD)) {
                String fileName = JOptionPane.showInputDialog("enter name of file to be imported: ").trim();
                ScrabbleGame loadedGame = ScrabbleGame.importAddressBookSerializable(fileName);
                model.setGameFromLoad(loadedGame);
                model.updateViews();
            } else if (menu.getText().equals(REDO)) {
                model.redo();
            } else if (menu.getText().equals(UNDO)) {
                model.undo();
            }
        }
    }
}
