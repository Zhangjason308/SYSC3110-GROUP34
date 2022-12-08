import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartFrame extends JFrame {
    private JPanel playerSetting = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20));
    private JPanel start = new JPanel(new BorderLayout(150,50));

    private JPanel boardSetting = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 50));
    private JRadioButton player2 = new JRadioButton();
    private JRadioButton ai = new JRadioButton();

    private JButton starts = new JButton("START");

    private ButtonGroup buttonGroup = new ButtonGroup();
    public StartFrame(){
        super("Scrabble Game");
        BufferedImage boardImage1;
        try {
            boardImage1 = ImageIO.read(new File("src/Board1.jpg"));
            //Graphics2D
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //boardImage1 = boardImage1.getScaledInstance(100,100,Image.SCALE_DEFAULT);
        //Image scaledBoard1 = boardImage1.getScaledInstance(400,400,Image.SCALE_FAST);
        JLabel picLabel = new JLabel(new ImageIcon(boardImage1));
        picLabel.setSize(400,400);
        this.add(picLabel,BorderLayout.CENTER);
        //ScrabbleFrame scrabbleFrame = new ScrabbleFrame();
        this.setLayout(new BorderLayout(20,20));
        final JTextField title = new JTextField("Welcome to Scrabble");
        final JTextField playerOptions = new JTextField("Player Settings");
        player2.setText("Player 2");
        player2.setBounds(120, 30, 120, 50);
        ai.setBounds(250, 30, 80, 50);
        ai.setText("AI");
        buttonGroup.add(player2);
        buttonGroup.add(ai);
        playerSetting.add(playerOptions);
        playerSetting.add(player2);
        playerSetting.add(ai);
        player2.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        ai.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        playerOptions.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        playerOptions.setOpaque(false);
        playerOptions.setBackground(Color.WHITE);
        playerOptions.setEditable(false);
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 50));
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setEditable(false);
        start.add(playerSetting,BorderLayout.CENTER);
        start.add(starts,BorderLayout.SOUTH);
        this.add(picLabel, BorderLayout.NORTH);
        //this.add(playerSetting, BorderLayout.SOUTH);
        this.add(start, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setSize(1000,800);
    }

    public static void main(String args[]) { new StartFrame();}
}
