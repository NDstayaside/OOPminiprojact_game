import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.net.URL;

//คำนวณขนากปลาทั้งหมดเทียบกับขนาดฉลาม เพราะขนาดปลามันน่าเกลียดมากถ้าเทียบกับตัวฉลามตอนนี้ ใหญ่เกินไปก็ไม่ได้ มันน่าเกลียดจริง
//-1 วิ่งไปขวา L  1 วิ่งไปซ้ายR
abstract public class Fish {
    protected String imageLocation = "image/f1L.png";
    private Image img;
    public int direction;
    public int x = -50;
    public int y = (int) ((Math.random() * (400 - 40)) + 70);

    Fish(){
        setDirection();
        imageLocation = getImageLocation();
        URL imageURL = this.getClass().getResource(imageLocation);
        img = Toolkit.getDefaultToolkit().getImage(imageURL);
        runner.start();
    }

    Thread runner = new Thread(new Runnable() {
        public void run() {
            while (true) {
                x += 2 * direction;
                if (x > 850 && direction == 1) { //ตอนมันเกินจอ และปลาเกิดทางขวา
                    img = null;
                    runner = null;
                    x = -100;
                    y = -200;    //random only y
                }
                else if (x < -100 && direction == -1) { //ตอนมันเกินจอ และปลาเกิดทางซ้าย
                    img = null;
                    runner = null;
                    x = 850;
                    y = -200;    //random only y
                }
                try {
                    runner.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
    });
    //สุ่มฝั่งเกิด และทางที่มปลาวิ่ง
    public void setDirection(){
        if ((int) ((Math.random() * 2)) == 0){  //เกิดทางซ้าย
            x = -100;
            direction = 1;
        } else{ //เกิดทางขวา
            x = 850;
            direction = -1;
        }
    }

    public Image getImage() {
        return img;
    }
    abstract public String getImageLocation();
    public int getX() {
        return x;
    }

    public int getY() {
        //System.out.println(y);
        return y;
    }
    abstract int getWeight();
    abstract int getHeight();
    abstract public int getScore();

    abstract public Rectangle2D getbound();
}

class Fish1 extends Fish{
    //constructor
    Fish1(){
        super();
    }

    @Override
    public String getImageLocation() {
        if (direction == -1)
            return "image/f1L.png";
        else
            return "image/f1R.png";
    }

    @Override
    int getWeight() {
        return 50;
    }

    @Override
    int getHeight() {
        return 37;
    }

    @Override
    public int getScore() {
        return 1;
    }

    @Override
    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, this.getWeight()-15, this.getHeight()-15));
    }
}
class Fish2 extends Fish{

    //constructor
    Fish2(){
        super();
    }

    @Override
    public String getImageLocation() {
        if (direction == -1)
            return "image/f2L.png";
        else
            return "image/f2R.png";
    }

    @Override
    int getWeight() {
        return 70;
    }

    @Override
    int getHeight() {
        return 58;
    }

    @Override
    public int getScore() {
        return 2;
    }

    @Override
    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, this.getWeight()-15, this.getHeight()-15));
    }
}
class Fish3 extends Fish{

    //constructor
    Fish3(){
        super();
    }

    @Override
    public String getImageLocation() {
        if (direction == -1)
            return "image/f3L.png";
        else
            return "image/f3R.png";
    }

    @Override
    int getWeight() {
        return 85;
    }

    @Override
    int getHeight() {
        return 67;
    }

    @Override
    public int getScore() {
        return 3;
    }

    @Override
    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, this.getWeight()-15, this.getHeight()-15));
    }
}
class Fish4 extends Fish{

    //constructor
    Fish4(){
        super();
    }

    @Override
    public String getImageLocation() {
        if (direction == -1)
            return "image/f4L.png";
        else
            return "image/f4R.png";
    }

    @Override
    int getWeight() {
        return 100;
    }

    @Override
    int getHeight() {
        return 82;
    }

    @Override
    public int getScore() {
        return 4;
    }

    @Override
    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, this.getWeight()-15, this.getHeight()-15));
    }
}