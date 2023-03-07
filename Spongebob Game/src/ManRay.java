import java.awt.*;

class ManRay {


    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;
    Rectangle rec;
    public int width;
    public int height;
    public boolean isAlive;

    public ManRay() {
        xpos = xpos+dx;
        ypos = ypos+dy;
        dx = 5;
        dy = 5;
        width = 100;
        height = 100;
        isAlive = false;
        rec = new Rectangle(xpos, ypos, width, height);


    }





    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);

        if (xpos <1 || xpos > 1000) {
            dy = (int) (Math.random() * 10);
            dx = -1 * dx;

        }

        if (ypos < 1 || ypos > 700-height) {
            dx = (int) (Math.random() * 10);
            dy = -1 * dy;


        }
    }
}
