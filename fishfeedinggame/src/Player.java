import javax.swing.*;
import java.awt.geom.Rectangle2D;

public class Player {
    public int hp;
    ImageIcon img;
    public int x;
    public int y;

    public int w = 90;
    public int h = 60;
    private int level = 1;

    ////////////////////////////
    //velocity - ความเร็ว จะได้วิ่งสมูทๆ
    public int velX = 0;
    public int velY = 0;
    ////////////////////////////
    Player(){
        img = new ImageIcon(this.getClass().getResource("image/Shark.png"));
    }

    public void flipLeft(){
        img = new ImageIcon(this.getClass().getResource("image/Shark.png"));
    }
    public void flipRight(){
        img = new ImageIcon(this.getClass().getResource("image/SharkF.png"));
    }
    ////////////////////////////
    public void tick(){
        x += velX;
        y += velY;

        //เซ็ทกระจัดการเมื่อฉลามไปวุ่นวายกับขอบจอ
        if(x <= -25){
            x = 900;
        }
        if(x > 900){
            x = -25;
        }
        if(y <= 70){
            y = 70;
        }
        if(y >= 500){
            y = 500;
        }
    }
    public  void setVelX(int velX){
        this.velX = velX;
    }
    public  void setVelY(int velY){
        this.velY = velY;
    }
    ////////////////////////////
    public int getLevel(){
        return this.level;
    }
    public void resetLevel(){
        this.level = 1;
    }
    public void checkLevel(int score){
        if (score >= 17 && this.level <= 3) {
            upLevel();
        }
        else if (score >= 12 && this.level <= 2) {
            upLevel();
        }
        else if (score >= 5 && this.level <= 1) {
            upLevel();
        }
    }
    public void upLevel(){
        this.level += 1;
    }

    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, 45, 45));
    }
}