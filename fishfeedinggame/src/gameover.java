import javax.swing.*;
import java.awt.*;

public class gameover extends JPanel {
    private ImageIcon gameoverBG = new ImageIcon(this.getClass().getResource("image/gameoverBG.jpg"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("image/exitButton.png"));
    //private ImageIcon restart = new ImageIcon("image/restartButton.png");
    //public JButton BrestartFromOver = new JButton(restart);
    public JButton BexitFromOver  = new JButton(exit);

    gameover(){
        this.setLayout(null);
        BexitFromOver.setBounds(280, 320, 212,78);
        BexitFromOver.setOpaque(false);
        BexitFromOver.setContentAreaFilled(false);
        BexitFromOver.setBorderPainted(false);
        this.add(BexitFromOver);

        System.out.println("sus");

        /*BrestartFromOver.setBounds(400,650,150,90);
        BrestartFromOver.setOpaque(false);
        BrestartFromOver.setContentAreaFilled(false);
        BrestartFromOver.setBorderPainted(false);
        add(BrestartFromOver);*/

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(gameoverBG.getImage(),0,0,800,600,this);
    }
}