import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {
    private ImageIcon bg = new ImageIcon(this.getClass().getResource("image/startBG.jpg"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("image/exitButton.png"));
    private ImageIcon start = new ImageIcon(this.getClass().getResource("image/startButton.png"));
    public JButton BStartFromHome = new JButton(start);
    public JButton BExitFromHome  = new JButton(exit);

    Home(){
        setLayout(null);
        BStartFromHome.setBounds(50, 280, 212,78);
        BStartFromHome.setOpaque(false);
        BStartFromHome.setContentAreaFilled(false);
        BStartFromHome.setBorderPainted(false);
        add(BStartFromHome);

        BExitFromHome.setBounds(50,400,212,78);
        BExitFromHome.setOpaque(false);
        BExitFromHome.setContentAreaFilled(false);
        BExitFromHome.setBorderPainted(false);
        add(BExitFromHome);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg.getImage(),0,0,800,600,this);
    }
}
