import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartFrame extends JFrame {

    private JPanel playerSetting = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20));
    private JPanel start = new JPanel(new BorderLayout(150,50));
    private JPanel boardSetting = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
    private JPanel boardPanel = new JPanel(new BorderLayout(100,0));
    private JRadioButton player2 = new JRadioButton();
    private JRadioButton ai = new JRadioButton("", true);

    private JPanel boardOption = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
    private JRadioButton board1 = new JRadioButton("", true);
    private JRadioButton board2 = new JRadioButton();
    private JRadioButton board3 = new JRadioButton();
    private JButton starts = new JButton("START");
    private ButtonGroup buttonGroup = new ButtonGroup();
    private ButtonGroup boardGroup = new ButtonGroup();
    final JTextField title = new JTextField("Welcome to Scrabble");
    final JTextField playerOptions = new JTextField("Player Settings");
    final JTextField boardSelection = new JTextField("Board Settings");
    public StartFrame(){

        super("Scrabble Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        BufferedImage boardImage1;
        BufferedImage boardImage2;
        BufferedImage boardImage3;
        try {
            boardImage1 = ImageIO.read(new File("src/Board1.jpg"));
            boardImage2 = ImageIO.read(new File("src/Board2.jpg"));
            boardImage3 = ImageIO.read(new File("src/Board3.jpg"));
            //Graphics2D
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StartFrameController startFrameController = new StartFrameController(this);
        JLabel picLabel1 = new JLabel(new ImageIcon(boardImage1));
        JLabel picLabel2 = new JLabel(new ImageIcon(boardImage2));
        JLabel picLabel3 = new JLabel(new ImageIcon(boardImage3));
        boardPanel.add(boardSetting, BorderLayout.CENTER);
        boardGroup.add(board1);
        boardGroup.add(board2);
        boardGroup.add(board3);
        boardOption.add(boardSelection);
        boardOption.add(board1);
        boardOption.add(board2);
        boardOption.add(board3);
        boardPanel.add(boardOption, BorderLayout.SOUTH);
        boardSetting.add(picLabel1);
        boardSetting.add(picLabel2);
        boardSetting.add(picLabel3);
        this.setLayout(new BorderLayout(20,120));
        player2.setText("Player 2");
        player2.setActionCommand(StartFrameController.PLAYER2);
        player2.setBounds(120, 30, 120, 50);
        ai.setBounds(250, 30, 80, 50);
        ai.setActionCommand(StartFrameController.AI);
        ai.setText("AI");
        board1.setText("Board 1");
        board1.setActionCommand(StartFrameController.BOARD1);
        board2.setText("Board 2");
        board2.setActionCommand(StartFrameController.BOARD2);
        board3.setText("Board 3");
        board3.setActionCommand(StartFrameController.BOARD3);
        board1.setBounds(120, 80, 120, 10);
        board2.setBounds(120, 80, 120, 10);
        board3.setBounds(120, 80, 120, 10);
        buttonGroup.add(player2);
        buttonGroup.add(ai);
        playerSetting.add(playerOptions);
        playerSetting.add(player2);
        playerSetting.add(ai);
        player2.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        ai.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        board1.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        board2.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        board3.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        playerOptions.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        playerOptions.setEditable(false);
        boardSelection.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        boardSelection.setEditable(false);
        boardSelection.setHorizontalAlignment(JTextField.CENTER);
        title.setEditable(false);
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 50));
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setEditable(false);
        start.add(playerSetting,BorderLayout.CENTER);
        starts.addActionListener(startFrameController);
        start.add(starts,BorderLayout.SOUTH);
        this.add(title, BorderLayout.NORTH);
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(start, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setSize(1000,800);
    }
    public ButtonGroup getButtonGroup(){
        return buttonGroup;
    }
    public ButtonGroup getBoardGroup(){
        return boardGroup;
    }
    public static void main(String args[]) { new StartFrame();}
}
