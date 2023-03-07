import java.awt.*;

class Spongebob {


    public String Name;
    public String Class;
    public boolean Boarder;
    public double GPA;
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;
    Rectangle rec;
    public int width;
    public int height;
    public boolean isAlive;

    public Spongebob(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 0;
        dy = 0;
        width = 60;
        height = 60;
        rec = new Rectangle(xpos, ypos, width, height);
        isAlive=true;

    }

    public void PrintInfo() {
        System.out.println("The Student's Name is: " + Name);
        System.out.println("Peter is in his " + Class + " Year");
        System.out.println("Peter's Boarder Status is: " + Boarder);
        System.out.println("Peter's GPA is: " + GPA);

    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);


        if (xpos < 1 || xpos + width > 1000) {
            dy = (int) (Math.random() * 10);
            dx = -1 * dx;

        }

        if (ypos < 1 || ypos > 700-height) {
            dx = (int) (Math.random() * 10);
            dy = -1 * dy;


        }
    }
}
