import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameWindow extends JFrame implements ActionListener {
    //J panel
    Home homepage = new Home();
    Playground gameStart = new Playground();    //play in here

    //winGame gameOver = new winGame(); //test screeen

    //main
    public static void main(String[] args){
        JFrame jf = new gameWindow();
        jf.setSize(800, 600);
        jf.setTitle("Shark's dinner game");
        jf.setResizable(false);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }

    //constructor
    public gameWindow() {

        this.add(homepage);
        homepage.BStartFromHome.addActionListener(this);
        homepage.BExitFromHome.addActionListener(this);
        gameStart.BPauseShell.addActionListener(this);
        gameStart.BresumeShell.addActionListener(this);
        gameStart.overscreen.BexitFromOver.addActionListener(this);
        gameStart.wining.BexitFromWin.addActionListener(this);
    }

    //Action listener
    public void actionPerformed(ActionEvent e) {
        //start from home
        if (e.getSource() == homepage.BStartFromHome) {
            this.setLocationRelativeTo(null);
            this.remove(homepage);
            this.add(gameStart);
            gameStart.requestFocusInWindow();
            gameStart.score = 0;
            gameStart.HP = 3;
            gameStart.fishrun = false;
            gameStart.pausestatus = false;
        }
        //exit from home
        else if (e.getSource() == homepage.BExitFromHome) {
            System.exit(0);
        }
        //exit from over
        else if (e.getSource() == gameStart.overscreen.BexitFromOver) {
            System.exit(0);
        }
        //exit from win
        else if (e.getSource() == gameStart.wining.BexitFromWin) {
            System.exit(0);
        }
        //pause in game
        else if (e.getSource() == gameStart.BPauseShell) {
            this.setLocationRelativeTo(null);
            this.add(gameStart);
            gameStart.requestFocusInWindow();
            gameStart.sharkThread.suspend();
            gameStart.fishes.suspend();
            for (int i = 0; i < gameStart.fish.size(); i++) {
                gameStart.fish.get(i).runner.suspend();
            }
        }
        //resume in game
        else if (e.getSource() == gameStart.BresumeShell) {
            this.setLocationRelativeTo(null);
            this.add(gameStart);
            gameStart.requestFocusInWindow();
            gameStart.sharkThread.resume();
            gameStart.fishes.resume();
            for (int i = 0; i < gameStart.fish.size(); i++) {
                gameStart.fish.get(i).runner.resume();
            }
        }
        this.validate();
        this.repaint();
    }
}