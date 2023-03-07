import java.awt.*;

public class Jellyfish {
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;
    Rectangle rec;
    public int width;
    public int height;
    public int speed;

    public Jellyfish(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 10;
        dy = 10;
        width = 60;
        height = 60;
        speed =1;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);

        if (xpos < 1 || xpos > 1000) {
            dy = (int) (Math.random() * 10);
            dx = -1 * dx;

        }

        if (ypos < 1 || ypos > 700 - height) {
            dx = (int) (Math.random() * 10);
            dy = -1 * dy;


        }


    }
}