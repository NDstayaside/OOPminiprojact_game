import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Playground extends JPanel implements ActionListener{
    //attribute--------------------------------------------------
    //page
    //Home backHome = new Home();
    gameover overscreen = new gameover();
    winGame wining = new winGame();

    //BG-------------------------------------------------------------------------
    //level
    //private final ImageIcon bgLV1 = new ImageIcon(this.getClass().getResource("image/backgroundLV1.jpg"));
    private final ImageIcon bgLV[] = new ImageIcon[3];
    private final ImageIcon pauseBG = new ImageIcon(this.getClass().getResource("image/pauseBG.png"));
    private String levellist[] = {"image/backgroundLV1.jpg","image/backgroundLV2.jpg","image/backgroundLV3.jpg"};
    //gamover
    private ImageIcon gameoverBG = new ImageIcon(this.getClass().getResource("image/gameoverBG.jpg"));
    //win game
    private ImageIcon winBG = new ImageIcon(this.getClass().getResource("image/winscreen.jpg"));

    //just bar
    private final ImageIcon eatableFishBar = new ImageIcon(this.getClass().getResource("image/bar.png"));

    //health bar
    private final ImageIcon heart = new ImageIcon(this.getClass().getResource("image/heart.png"));

    //eatable fish bar var
    private String eatableFishInBar[] = {"image/f1L.png","image/f2L.png","image/f3L.png","image/f4L.png"};
    private ImageIcon eatableFishInBarImg[] = new ImageIcon[4];
    //private int xFish = 630, yFish = 30;

    //pause stop home button
    private final ImageIcon pauseShell = new ImageIcon(this.getClass().getResource("image/stopshell.png"));
    private final ImageIcon resumeShell = new ImageIcon(this.getClass().getResource("image/shell.png"));
    private final ImageIcon gotohome = new ImageIcon(this.getClass().getResource("image/homeButton.png"));
    public JButton BPauseShell = new JButton(pauseShell);
    public JButton BresumeShell = new JButton(resumeShell);

    //player
    Player shark = new Player();
    //boolean sharkDirect = false;    //false left true right

    //Fish
    public ArrayList<Fish> fish = new ArrayList<Fish>();

    //game system status
    int levelNow = 0;
    int score = 0;
    int HP = 3;
    boolean fishrun = false;
    boolean pausestatus = false;

    //Thread---------------------------------------------------
    Thread sharkThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                try{
                    shark.tick();
                    Thread.sleep(10);
                } catch (Exception e) {}
                repaint();
            }
        }
    });

    Thread fishes = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    if (fishrun == false) {
                        Thread.sleep((long) (Math.random() * 2000) + 500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (fishrun == false) { //summon fish
                    int getFishType = (int) ((Math.random() * 5));
                    //System.out.println(getFishType);

                    switch (getFishType){
                        case 0:
                            fish.add(new Fish1());
                            break;
                        case 1:
                            fish.add(new Fish2());
                            break;
                        case 2:
                            fish.add(new Fish3());
                            break;
                        case 3:
                            fish.add(new Fish4());
                            break;
                        case 4:
                            fish.add(new Fish1());
                            break;
                    }
                }
            }
        }
    });

    //action listener------------------------------------------
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BPauseShell) {
            add(BresumeShell);
            remove(BPauseShell);
            pausestatus = true;

        } else if (e.getSource() == BresumeShell) {
            pausestatus = false;
            remove(BresumeShell);
            add(BPauseShell);
        }
        else if (e.getSource() == overscreen.BexitFromOver) {
            System.exit(0);
        }
    }

    //constructor----------------------------------------------
    Playground(){
        //set Layout
        setLayout(null);
        setFocusable(true);

        //set button---------------------------------------
        //set button transparent BG
        BPauseShell.setOpaque(false);
        BPauseShell.setContentAreaFilled(false);
        BPauseShell.setBorderPainted(false);

        BresumeShell.setOpaque(false);
        BresumeShell.setContentAreaFilled(false);
        BresumeShell.setBorderPainted(false);

        //set button bound
        BPauseShell.setBounds(380, 25, 50, 50);
        BresumeShell.setBounds(380, 25, 50, 40);

        //set button listener
        BPauseShell.addActionListener(this);
        BresumeShell.addActionListener(this);
        this.add(BPauseShell);  //resume ไว้ค่อยไปเพิ่มตอนกด pause

        //set picture---------------------------------------
        //bg
        for (int i = 0; i < bgLV.length; i++) {
            bgLV[i] = new ImageIcon(this.getClass().getResource(levellist[i]));
            //System.out.println("check");
        }

        //fish in bar
        for (int i = 0; i < eatableFishInBarImg.length; i++) {
            eatableFishInBarImg[i] = new ImageIcon(this.getClass().getResource(eatableFishInBar[i]));
            //System.out.println("check");
        }

        //shark movement---------------------------------------
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                if (a == KeyEvent.VK_LEFT) {
                    shark.flipLeft();
                    shark.setVelX(-5);
                } else if (a == KeyEvent.VK_RIGHT) {
                    shark.flipRight();
                    shark.setVelX(5);
                }
                else if (a == KeyEvent.VK_UP) {
                    shark.setVelY(-5);

                }
                else if (a == KeyEvent.VK_DOWN) {
                    shark.setVelY(5);

                }
            }
            public void keyReleased(KeyEvent e) {
                int a = e.getKeyCode();
                if (a == KeyEvent.VK_LEFT) {
                    shark.setVelX(0);
                } else if (a == KeyEvent.VK_RIGHT) {
                    shark.setVelX(0);
                }
                else if (a == KeyEvent.VK_UP) {
                    shark.setVelY(0);
                }
                else if (a == KeyEvent.VK_DOWN) {
                    shark.setVelY(0);
                }
            }
        });

        //player first position
        shark.x = 350;
        shark.y = 350;

        //start threads

    } //constructor end here

    //collision check--------------------------------------------
    public boolean Intersect(Rectangle2D a, Rectangle2D b) {
        return (a.intersects(b));
    }

    //paint the graphic------------------------------------------
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //lose
        if (HP <= 0){
            this.remove(BPauseShell);
            this.setLayout(null);
            g.drawImage(gameoverBG.getImage(),0,0,800,600,this);
            this.add(overscreen.BexitFromOver);
            //this.overscreen.BexitFromOver.addActionListener(this);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //win the game
        else if(levelNow >= 3){
            this.remove(BPauseShell);
            this.setLayout(null);
            g.drawImage(winBG.getImage(),0,0,800,600,this);
            this.add(wining.BexitFromWin);
        }
        //play and change level
        else{
            //test กันเหนียว
            shark.checkLevel(score);

            //very start at the game
            //background
            g.drawImage(bgLV[levelNow].getImage(),0,0,800,600,this);

            //health bar
            g.drawImage(eatableFishBar.getImage(),25,25,165,32,this);
            for (int i = 0; i < HP; i++) {
                g.drawImage(heart.getImage(),59+(i*40),33,17,17,this);
            }

            //eatable fish bar
            g.drawImage(eatableFishBar.getImage(),595,25,165,32,this);
            for (int i = 0; i < shark.getLevel(); i++) {
                g.drawImage(eatableFishInBarImg[i].getImage(),615+(i*35),33,20,17,this);
            }

            //fishes swim draw
            for (int i = 0; i < fish.size(); i++) {
                g.drawImage(fish.get(i).getImage(), fish.get(i).getX(), fish.get(i).getY(), fish.get(i).getWeight(), fish.get(i).getHeight(), this);
            }

            for (int i = 0; i < fish.size(); i++) {
                //when collision happened
                if (Intersect(fish.get(i).getbound(), shark.getbound())) {
                    //shark level >= fish
                    if (fish.get(i).getScore() <= shark.getLevel()){
                        score += fish.get(i).getScore();
                        shark.checkLevel(score);
                        fish.remove(i);
                    }
                    //shark level < fish ไม่เจียมตัวเลยน้า
                    else if (fish.get(i).getScore() > shark.getLevel()) {
                        --HP;
                        fish.remove(i);
                    }
                }
                //when ปลาวิ่งเลยขอบ happened
                else {
                    if (fish.get(i).getX() >= 850){
                        fish.remove(i);
                    } else if (fish.get(i).getX() <= -100){
                        fish.remove(i);
                    }
                }
            }


            //player
            g.drawImage(shark.img.getImage(), shark.x,shark.y,shark.w,shark.h,this);
            if (pausestatus){
                g.drawImage(pauseBG.getImage(),0,0,800,600,this);
            }

            //change level
            if (score >= 25){
                levelNow++;
                HP = 3;
                score = 0;
                shark.resetLevel();
            }
        }
    }
}
