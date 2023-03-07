//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;


//*******************************************************************************
// Class Definition Section

public class Start implements Runnable, KeyListener {


    //  private final Object Spongebob;

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    public int WIDTH = 1000;
    public int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public Clip clip;

    public BufferStrategy bufferStrategy;
    public Image SpongebobPic;
    public Image JellyfishPic;
    public Image BikiniBottom;
    public Image Endgame;
    public Image ManRayPic;
    public Image StartScreen;
    public Image Instructions;
    public Image Easy;
    public Image Medium;
    public Image Hard;
    public Image Impossible;
    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    public Spongebob sb;
    public Jellyfish j;
    public ManRay[] mr;
    //    public ManRay mr2;
    public int lives = 3;
    public int score = 0;
    public int highscore;
    public boolean endscreen = false;
    public boolean gamestart = false;
    public boolean instructionscreen = false;
    public int enemyCount = 20;
    public boolean easyscreen = false;
    public boolean startscreen = true;
    public boolean mediumscreen = false;
    public boolean hardscreen = false;
    public boolean impossiblescreen = false;


    // Main method definition
    // This is the code that runs first and automatically
/*	public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
	}
*/

    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public Start() {
//        sound("ThemeSong.mp3");

        SpongebobPic = Toolkit.getDefaultToolkit().getImage("Spongebob1.PNG"); //load the picture
        JellyfishPic = Toolkit.getDefaultToolkit().getImage("Jellyfish.PNG");
        ManRayPic = Toolkit.getDefaultToolkit().getImage("RanMay.png");
        BikiniBottom = Toolkit.getDefaultToolkit().getImage("BikiniBottom.png");
        Endgame = Toolkit.getDefaultToolkit().getImage("Endgame.jpg");
        StartScreen = Toolkit.getDefaultToolkit().getImage("StartScreen.PNG");
        Instructions = Toolkit.getDefaultToolkit().getImage("Instructions.PNG");
        Endgame = Toolkit.getDefaultToolkit().getImage("Endgame.jpg");
        StartScreen = Toolkit.getDefaultToolkit().getImage("StartScreen.PNG");
        Instructions = Toolkit.getDefaultToolkit().getImage("Instructions.PNG");
        Easy = Toolkit.getDefaultToolkit().getImage("Easy.jpg");
        Medium = Toolkit.getDefaultToolkit().getImage("Medium.PNG");
        Hard = Toolkit.getDefaultToolkit().getImage("Hard.PNG");
        Impossible = Toolkit.getDefaultToolkit().getImage("Impossible.PNG");
        setUpGraphics();
        reset();


    }// BasicGameApp()

    private void reset() {

       score=0;
       lives=3;

        endscreen = false;

        instructionscreen = false;
        enemyCount = 20;
        easyscreen = false;
        startscreen = true;

        gamestart = false;
        sb = new Spongebob(600, 100);
        j = new Jellyfish(500, 250);
        mr = new ManRay[enemyCount];


        for (int j = 0; j < mr.length; j++) {
            mr[j] = new ManRay();
        }

        for (int j = 0; j < enemyCount; j++) {
            mr[j].isAlive = true;
        }

    }

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            collisions();
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }


    public void moveThings() {
        //calls the move( ) code in the objects
        sb.move();
        j.move();
        for (int j = 0; j < mr.length; j++) {
            if (mr[j].isAlive == true) {
                mr[j].move();
            }
        }

    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
        canvas.addKeyListener(this);
        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    public void collisions() {

        if (sb.rec.intersects(j.rec) && sb.isAlive == true) {

            j.dx = -1 * j.dx;
            j.dy = -1 * j.dy;

            sb.xpos = sb.xpos + 2 * sb.dx;
            sb.ypos = sb.ypos + 2 * sb.dy;
            j.xpos = (int) (Math.random() * 500);
            j.ypos = (int) (Math.random() * 500);

            score = score + 1;

            j.speed = j.speed + 1;


        }
        if (score % 5 == 0) {
            if (enemyCount < 30) {

                for (int j = 0; j < enemyCount; j++) {
                    mr[j].isAlive = true;
                }
            }
        }

        for (int j = 0; j < mr.length; j++) {
            if (sb.rec.intersects(mr[j].rec) && sb.isAlive == true && mr[j].isAlive == true) {
                sb.dx = -2 * sb.dx;
                sb.dy = -2 * sb.dy;
                mr[j].dx = -2 * mr[j].dx;
                mr[j].dy = -2 * mr[j].dy;
                lives = lives - 1;
            }
        }

        if (lives < 1) {
            gamestart = false;


        }
    }


    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if (startscreen == true) {
            g.drawImage(StartScreen, 0, 0, WIDTH, HEIGHT, null);
        } else {
            if (instructionscreen == true) {
                g.drawImage(Instructions, 0, 0, WIDTH, HEIGHT, null);
            } else if (easyscreen == true) {
                g.drawImage(Easy, 0, 0, WIDTH, HEIGHT, null);
            } else if (mediumscreen==true){
                g.drawImage(Medium,0,0,WIDTH,HEIGHT,null);
            }
            else if (hardscreen==true){
                g.drawImage(Hard,0,0,WIDTH,HEIGHT,null);
            }
            else if (impossiblescreen==true){
                g.drawImage(Impossible,0,0,WIDTH,HEIGHT,null);
            }
            else if (gamestart == true) {

                g.drawImage(BikiniBottom, 0, 0, WIDTH, HEIGHT, null);
                //draw the image of the astronaut
                g.drawImage(SpongebobPic, sb.xpos, sb.ypos, sb.width, sb.height, null);
                g.drawImage(JellyfishPic, j.xpos, j.ypos, j.width, j.height, null);
                for (int j = 0; j < mr.length; j++) {
                    if (mr[j].isAlive == true) {
                        g.drawImage(ManRayPic, mr[j].xpos, mr[j].ypos, mr[j].width, mr[j].height, null);
                    }
                }
                if (score > highscore) {
                    highscore = score;
                }

                g.setColor(new Color(255, 255, 255));
                g.setFont(new Font("Times New Roman", Font.BOLD, 25));
                g.drawString(score + " Points", 900, 50);
                g.drawString(lives + " lives left", 50, 50);
            } else {
                g.drawImage(Endgame, 0, 0, WIDTH, HEIGHT, null);
                g.setFont(new Font("Pacifico", Font.BOLD, 30));
                g.drawString("HIGHSCORE: " + highscore, 350, 50);

            }
        }


        g.dispose();
        bufferStrategy.show();
    }


    public void sound(String path) {
        try {
            File file = new File(path);
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();


        } catch (Exception e) {
            System.out.println("check " + path + "\n");
            e.printStackTrace();
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {


    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()=='q'){
            lives=3;
            startscreen=true;
            score=0;
            gamestart=false;
            sb.isAlive=false;
            mr[enemyCount].isAlive=false;
            endscreen=false;
        }
        if (gamestart == true && sb.isAlive == true) {
            if (e.getKeyChar() == 'w' && sb.ypos > 1) {
                sb.dy = -5;
            }
            if (e.getKeyChar() == 'a') {
                sb.dx = -5;
            }
            if (e.getKeyChar() == 's' && sb.ypos < 1000) {
                sb.dy = 5;
            }
            if (e.getKeyChar() == 'd') {
                sb.dx = 5;

            }
        }
        if (score >= 10 && gamestart == true && sb.isAlive == true) {
            if (e.getKeyChar() == 'w') {
                sb.dy = -7;
            }
            if (e.getKeyChar() == 'a') {
                sb.dx = -7;
            }
            if (e.getKeyChar() == 's') {
                sb.dy = 7;
            }
            if (e.getKeyChar() == 'd') {
                sb.dx = 7;

            }}
            if (score >= 20 && gamestart == true && sb.isAlive == true) {
                if (e.getKeyChar() == 'w') {
                    sb.dy = -12;
                }
                if (e.getKeyChar() == 'a') {
                    sb.dx = -12;
                }
                if (e.getKeyChar() == 's') {
                    sb.dy = 12;
                }
                if (e.getKeyChar() == 'd') {
                    sb.dx = 12;

                }
            }
            if (easyscreen == true) {
                if (e.getKeyChar() == 's') {


                    gamestart = true;
                    enemyCount = 5;
                    mr = new ManRay[enemyCount];
                    score = 0;
                    startscreen = false;
                    instructionscreen = false;
                    endscreen = false;
                    easyscreen = false;
                    lives = 5;
                    sb.isAlive = true;
                    for (int i = 0; i < mr.length; i++) {
                        mr[i] = new ManRay();
                    }

                }
            }
            if (mediumscreen == true) {
                if (e.getKeyChar() == 's') {

                    mediumscreen = false;
                    gamestart = true;
                    enemyCount = 10;
                    mr = new ManRay[enemyCount];
                    score = 0;
                    startscreen = false;
                    instructionscreen = false;
                    endscreen = false;
                    easyscreen = false;
                    lives = 3;
                    sb.isAlive = true;
                    for (int i = 0; i < mr.length; i++) {
                        mr[i] = new ManRay();
                    }

                }}
                if (hardscreen == true) {
                    if (e.getKeyChar() == 's') {

                        hardscreen = false;
                        gamestart = true;
                        enemyCount = 15;
                        mr = new ManRay[enemyCount];
                        score = 0;
                        startscreen = false;
                        instructionscreen = false;
                        endscreen = false;
                        easyscreen = false;
                        lives = 2;
                        sb.isAlive = true;
                        for (int i = 0; i < mr.length; i++) {
                            mr[i] = new ManRay();
                        }

                    }

                    if (e.getKeyChar() == 'b') {
                        hardscreen = false;
                        startscreen = true;
                        endscreen = false;
                    }
                }
                if (easyscreen = true) {
                    if (e.getKeyChar() == 'b') {
                        easyscreen = false;
                        startscreen = true;
                        endscreen = false;
                    }
                }
        if (impossiblescreen == true) {
            if (e.getKeyChar() == 's') {

                impossiblescreen = false;
                gamestart = true;
                enemyCount = 20;
                mr = new ManRay[enemyCount];
                score = 0;
                startscreen = false;
                instructionscreen = false;
                endscreen = false;
                easyscreen = false;
                lives = 1;
                sb.isAlive = true;
                for (int i = 0; i < mr.length; i++) {
                    mr[i] = new ManRay();
                }

            }
        if (e.getKeyChar()=='b') {
            impossiblescreen = false;
            startscreen = true;
            gamestart = false;

        }
            }

                if (instructionscreen == true) {
                    if (e.getKeyChar() == 'b') {
                        instructionscreen = false;
                        startscreen = true;
                    }
                }
                if (gamestart == true) {
                    instructionscreen = false;
                    startscreen = false;
                    easyscreen = false;
                    endscreen = false;
                }
                if (startscreen == true) {
                    if (e.getKeyChar()=='p'){
                        impossiblescreen=true;
                        startscreen=false;
                        endscreen=false;
                        easyscreen=false;
                    }
                    if (e.getKeyChar() == 'm') {
                        endscreen = false;
                        instructionscreen = false;
                        mediumscreen = true;
                        startscreen = false;
                        gamestart = false;
                        easyscreen = false;
                    }
                    if (e.getKeyChar() == 'h') {
                        endscreen = false;
                        instructionscreen = false;
                        hardscreen = true;
                        startscreen = false;
                        gamestart = false;
                        easyscreen = false;
                        mediumscreen=false;
                    }
                    if (e.getKeyChar() == 'i') {
                        endscreen = false;
                        instructionscreen = true;
                        startscreen = false;
                        gamestart = false;
                        easyscreen = false;
                    }

                    if (e.getKeyChar() == 'e') {
                        endscreen = false;
                        easyscreen = true;
                        startscreen = false;
                        gamestart = false;
                        instructionscreen = false;


                    }
                }

                if (endscreen == true) {
                    if (e.getKeyChar() == 'r') {
                        reset();

                        gamestart = false;
                        SpongebobPic = Toolkit.getDefaultToolkit().getImage("Spongebob1.PNG"); //load the picture
                        sb = new Spongebob(10, 100);
                        JellyfishPic = Toolkit.getDefaultToolkit().getImage("Jellyfish.PNG");
                        j = new Jellyfish(500, 250);
                        ManRayPic = Toolkit.getDefaultToolkit().getImage("RanMay.png");
                        BikiniBottom = Toolkit.getDefaultToolkit().getImage("BikiniBottom.png");
                        endscreen = false;

                        lives = 3;
                        score = 0;
                        StartScreen = Toolkit.getDefaultToolkit().getImage("StartScreen.PNG");
                        Instructions = Toolkit.getDefaultToolkit().getImage("Instructions.PNG");
                    }

                }


    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            sb.dy = 0;
        }
        if (e.getKeyChar() == 'a') {
            sb.dx = 0;
        }
        if (e.getKeyChar() == 's') {
            sb.dy = 0;
        }
        if (e.getKeyChar() == 'd') {
            sb.dx = 0;

        }
        if (score >= 10) {
            if (e.getKeyChar() == 'w') {
                sb.dy = 0;
            }
            if (e.getKeyChar() == 'a') {
                sb.dx = 0;
            }
            if (e.getKeyChar() == 's') {
                sb.dy = 0;
            }
            if (e.getKeyChar() == 'd') {
                sb.dx = 0;
            }
        }
    }
}