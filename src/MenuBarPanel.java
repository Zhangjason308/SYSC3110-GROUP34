import javax.swing.*;

public class MenuBarPanel extends JPanel {
    private JMenuBar menu = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenu edit = new JMenu("Edit");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem load = new JMenuItem("Load");
    private JMenuItem undo = new JMenuItem("Undo");
    private JMenuItem redo = new JMenuItem("Redo");

    public MenuBarPanel(){
        super();
        this.add(menu);
        menu.add(file);
        menu.add(edit);
        file.add(save);
        file.add(load);
        edit.add(undo);
        edit.add(redo);
        this.setVisible(true);

    }

}
