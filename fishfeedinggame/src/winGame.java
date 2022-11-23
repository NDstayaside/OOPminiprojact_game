import javax.swing.*;
import java.awt.*;

public class winGame extends JPanel {
    private ImageIcon winBG = new ImageIcon(this.getClass().getResource("image/winscreen.jpg"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("image/exitButton.png"));
    public JButton BexitFromWin  = new JButton(exit);

    winGame(){
        this.setLayout(null);
        BexitFromWin.setBounds(490, 445, 212,78);
        BexitFromWin.setOpaque(false);
        BexitFromWin.setContentAreaFilled(false);
        BexitFromWin.setBorderPainted(false);
        this.add(BexitFromWin);


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(winBG.getImage(),0,0,800,600,this);
    }
}