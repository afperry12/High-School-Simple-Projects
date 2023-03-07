import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;


public class Blackjack implements  Runnable, KeyListener {
    public int cardHeight;
    public int cardWidth;
    public int topOfDeck;
    public boolean twoPlayer = false;
    public boolean onePlayer = false;
    public boolean startscreen = true;
    public boolean blackjacktable1 = false;
    public boolean blackjacktable2=false;
    public boolean slots = false;
    public Image Startscreen;
    public Image Blackjacktable;
    public Image Slots;
    public Image Cards;
    public Image Instructions;
    public Image numberofPlayers;
    BufferStrategy bufferStrategy;
    int WIDTH = 1000;
    int HEIGHT = 700;
    JFrame frame;
    Canvas canvas;
    JPanel panel;
    public Card[] deck;
    private boolean instructionscreen;
    public boolean blackjackgame;

    public Blackjack(boolean shuffle) {
        setUpGraphics();
        deck = new Card[52];
        Startscreen = Toolkit.getDefaultToolkit().getImage("Startscreen.jpg");
        Blackjacktable = Toolkit.getDefaultToolkit().getImage("Table.PNG");
        Slots = Toolkit.getDefaultToolkit().getImage("slots.jpg");
        Cards = Toolkit.getDefaultToolkit().getImage("cards.jpg");
        numberofPlayers=Toolkit.getDefaultToolkit().getImage("Choose.PNG");

        if (shuffle==true){
            shuffle();
        }

        int c = 0;

        for (int s = 1; s <=4; s++) {

            for (int n = 1; n <=13; n++) {

                    deck[c] = new Card(s,n);
                    c++;
                }
            }

        }

    public void setUpGraphics() {
        frame = new JFrame("THE TITLE OF THE WINDOW GOES HERE");   //Create the program window or frame.  Names it.
        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout
        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);
        panel.add(canvas);  // adds the canvas to the panel.
        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);     //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }

    public void run() {
        while (true) {
            render();
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
    }


    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        if (startscreen == true) {
            g.drawImage(Startscreen, 0, 0, WIDTH, HEIGHT, null);
        } else {
            if (instructionscreen == true) {
                g.drawImage(Instructions, 0, 0, WIDTH, HEIGHT, null);
            } else if (slots == true) {
                g.drawImage(Slots, 0, 0, WIDTH, HEIGHT, null);

            } else if (blackjackgame == true) {
                g.drawImage(numberofPlayers, 0, 0, WIDTH, HEIGHT, null);
            }


            if (blackjacktable1 == true) {

                g.drawImage(Blackjacktable, 0, 0, WIDTH, HEIGHT, null);
                String blackjack = JOptionPane.showInputDialog("Would you like to continue to Blackjack? Enter c to continue or b to return to the casino");
                if (blackjack.compareToIgnoreCase("b") == 0) {
                    blackjacktable1 = false;
                    onePlayer = false;
                    blackjackgame = false;
                    startscreen = true;
                } else {

                    String p1_name = JOptionPane.showInputDialog("Hello! To begin, please have each player enter a name. " +
                            "Player1, what is your name?");
                    System.out.println();
                    Player user = new Player(p1_name);
                    Player dealer = new Player("dealer");
                    int dx1, dy1, dx2, dy2;
                    int sx1, sy1, sx2, sy2;
                    cardHeight = 315;
                    cardWidth = 225;

                    for (int i = 0; i < dealer.numberOfCards; i++) {
                        dx1 = 200 + 100 * i;
                        dy1 = 75;
                        dx2 = dx1 + 115;
                        dy2 = dy1 + 160;

                        sx1 = (dealer.hand[i].Value - 1) * 225;
                        sy1 = (dealer.hand[i].suitValue - 1) * 315;
                        sx2 = sx1 + 225;
                        sy2 = sy1 + 315;
                        g.drawImage(Cards, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);

                    }

                    for (int i = 0; i < user.numberOfCards; i++) {
                        dx1 = 200 + 100 * i;
                        dy1 = 300;
                        dx2 = dx1 + 115;
                        dy2 = dy1 + 160;

                        sx1 = (user.hand[i].Value - 1) * 225;
                        sy1 = (user.hand[i].suitValue - 1) * 315;
                        sx2 = sx1 + 225;
                        sy2 = sy1 + 315;
                        g.drawImage(Cards, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);

                    }

                    if (!user.done && !dealer.done) {


                        shuffle();
                        JOptionPane.showMessageDialog(null, "Cards have been shuffled\n");
                        deal(user);
                        deal(dealer);
                        deal(user);
                        deal(dealer);
                        printHand(true, user);
                        printHand(false, dealer);
                        JOptionPane.showMessageDialog(null, "Cards are dealt\n");

                        if (!user.done) {

                            String hit = JOptionPane.showInputDialog(null, "Hit or Stay? (Type h for hit and s for stay...)");
                            if (hit.compareToIgnoreCase("h") == 0) {
                                deal(user);
                                printHand(true, user);
                            } else {
                                JOptionPane.showMessageDialog(null, "You Stayed");
                            }
                        }


                        if (!dealer.done) {

                            if (dealer.total < 17) {
                                JOptionPane.showMessageDialog(null, "The Dealer Hits\n");
                                deal(dealer);
                                printHand(false, dealer);
                                String hit = JOptionPane.showInputDialog(null, "Hit or Stay? (Type h for hit and s for stay...)");
                                if (hit.compareToIgnoreCase("h") == 0) {
                                    deal(user);
                                    printHand(true, user);
                                } else {
                                    user.done = true;
                                    JOptionPane.showMessageDialog(null, "You Stayed");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "The Dealer Stays\n");
                                dealer.done = true;
                                String hit = JOptionPane.showInputDialog(null, "Hit or Stay? (Type h for hit and s for stay...)");
                                if (hit.compareToIgnoreCase("h") == 0) {
                                    deal(user);
                                    printHand(true, user);
                                } else {
                                    user.done = true;
                                    JOptionPane.showMessageDialog(null, "You Stayed");
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, +user.total, "Your Total: ", JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, +dealer.total, "Dealer's Total: ", JOptionPane.PLAIN_MESSAGE);
                        if (user.total > 21 && dealer.total > 21) {
                            JOptionPane.showMessageDialog(null, "It is a tie!");
                        } else if (user.total > 21 && dealer.total <= 21) {
                            JOptionPane.showMessageDialog(null, "Unfortunately, the house has won this one... Better luck next time!");
                        } else if (dealer.total > 21 && user.total <= 21) {
                            JOptionPane.showMessageDialog(null, "You have beat the house this time... Congratulations!");
                        } else if (21 - user.total < 21 - dealer.total) {
                            JOptionPane.showMessageDialog(null, "You have beat the house this time... Congratulations!");
                        } else if (21 - dealer.total < 21 - user.total) {
                            JOptionPane.showMessageDialog(null, "Unfortunately, the house has won this one... Better luck next time!");
                        }

                    }
                }
            }
            if (blackjacktable2 == true) {
                g.drawImage(Blackjacktable, 0, 0, WIDTH, HEIGHT, null);
                String blackjack = JOptionPane.showInputDialog("Would you like to continue to Blackjack? Enter c to continue or b to return to the casino");
                if (blackjack.compareToIgnoreCase("b") == 0) {
                    blackjacktable1 = false;
                    onePlayer = false;
                    blackjackgame = false;
                    startscreen = true;
                } else {

                    String p1_name = JOptionPane.showInputDialog("Hello! To begin, please have each player enter a name. " +
                            "Player1, what is your name?");
                    String p2_name = JOptionPane.showInputDialog("Player2, what is your name?");
                    Player user = new Player(p1_name);
                    Player user2 = new Player(p2_name);
                    int dx1, dy1, dx2, dy2;
                    int sx1, sy1, sx2, sy2;
                    cardHeight = 315;
                    cardWidth = 225;

                    for (int i = 0; i < user2.numberOfCards; i++) {
                        dx1 = 200 + 100 * i;
                        dy1 = 75;
                        dx2 = dx1 + 115;
                        dy2 = dy1 + 160;

                        sx1 = (user2.hand[i].Value - 1) * 225;
                        sy1 = (user2.hand[i].suitValue - 1) * 315;
                        sx2 = sx1 + 225;
                        sy2 = sy1 + 315;
                        g.drawImage(Cards, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);

                    }

                    for (int i = 0; i < user.numberOfCards; i++) {
                        dx1 = 200 + 100 * i;
                        dy1 = 300;
                        dx2 = dx1 + 115;
                        dy2 = dy1 + 160;

                        sx1 = (user.hand[i].Value - 1) * 225;
                        sy1 = (user.hand[i].suitValue - 1) * 315;
                        sx2 = sx1 + 225;
                        sy2 = sy1 + 315;
                        g.drawImage(Cards, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);

                    }

                    if (!user.done && !user2.done) {
                        shuffle();
                        JOptionPane.showMessageDialog(null, "Cards have been shuffled\n");
                        deal(user);
                        deal(user2);
                        deal(user);
                        deal(user2);
                        JOptionPane.showMessageDialog(null, "About to show "+user.name+ "'s first card, please look away "+user2.name);
                        printHand(true, user);
                        JOptionPane.showMessageDialog(null, "About to show "+user2.name+ "'s first card, please look away "+user.name);
                        printHand(true, user2);
                        JOptionPane.showMessageDialog(null, "Cards are dealt\n");

                        if (!user.done) {

                            String hit = JOptionPane.showInputDialog(null, user.name+", Hit or Stay? (Type h for hit or s for stay...)");
                            if (hit.compareToIgnoreCase("h") == 0) {
                                JOptionPane.showMessageDialog(null, "About to show "+user.name+ "'s first card, please look away "+user2.name);
                                deal(user);
                                printHand(true, user);
                            } else {
                                JOptionPane.showMessageDialog(null, user.name+" Stayed");
                                user.done=true;
                                if (!user2.done) {

                                    String hit2 = JOptionPane.showInputDialog(null, user2.name+", Hit or Stay? (Type h for hit or s for stay...)");
                                    if (hit2.compareToIgnoreCase("h") == 0) {
                                        JOptionPane.showMessageDialog(null, "About to show "+user2.name+ "'s first card, please look away "+user.name);
                                        deal(user);
                                        printHand(true, user);
                                    } else {
                                        user2.done = true;
                                        JOptionPane.showMessageDialog(null, user2.name+" Stayed");
                                        if (user.done==false){
                                            String hit3 = JOptionPane.showInputDialog(null, user.name+" Hit or Stay? (Type h for hit or s for stay...)");
                                            if (hit3.compareToIgnoreCase("h") == 0) {
                                                JOptionPane.showMessageDialog(null, "About to show "+user.name+ "'s first card, please look away "+user2.name);
                                                deal(user);
                                                printHand(true, user);
                                            }
                                        }
                                    }
                                }

                            }
                        }

                        JOptionPane.showMessageDialog(null, +user.total, "Your Total: ", JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, +user2.total, "Dealer's Total: ", JOptionPane.PLAIN_MESSAGE);
                        if (user.total > 21 && user2.total > 21) {
                            JOptionPane.showMessageDialog(null, user.name+" has tied with "+user2.name);
                        } else if (user.total > 21 && user2.total <= 21) {
                            JOptionPane.showMessageDialog(null, user2.name+" has won!! Better luck next time "+user.name);
                        } else if (user2.total > 21 && user.total <= 21) {
                            JOptionPane.showMessageDialog(null, user.name+" has won!! Better luck next time "+user2.name);
                        } else if (21 - user.total < 21 - user2.total) {
                            JOptionPane.showMessageDialog(null, user.name+" has won!! Better luck next time "+user2.name);
                        } else if (21 - user2.total < 21 - user.total) {
                            JOptionPane.showMessageDialog(null, user2.name+" has won!! Better luck next time "+user.name);
                        }
                    }


                }
            }
        }
                g.dispose();
                bufferStrategy.show();
            }

            public void deal (Player player){

                if (player.numberOfCards == 12) {
                    System.err.printf("%s's hand already has 12 cards: " +
                            "cannot add another", player.name);
                    System.exit(1);
                } else
                    player.hand[player.numberOfCards] = deck[topOfDeck];
                topOfDeck++;
                player.numberOfCards++;
                player.total = 0;
                int numAces = 0;

                for (int i = 0; i < player.numberOfCards; i++) {
                    player.total = player.total + player.hand[i].cardNumber;

                    if (player.hand[i].cardNumber == 11) {
                        numAces++;

                    }

                }
                while (player.total > 21 && numAces > 0) {
                    player.total -= 10;
                    numAces--;
                }
//

            }



    public void printHand(boolean FirstCard,Player player) {
        JOptionPane.showMessageDialog(null,"Cards:\n", player.name,JOptionPane.PLAIN_MESSAGE);
        for (int c = 0; c < player.numberOfCards; c++) {
            if (c == 0 && !FirstCard) {
                JOptionPane.showMessageDialog(null,"[hidden]",player.name,JOptionPane.PLAIN_MESSAGE);
            } else {
                player.hand[c].printCard();
            }
        }

        System.out.println();
    }
    public void shuffle(){
        Random rng = new Random();
        Card temp;
        int j;
        for (int i = 0; i < deck.length; i++) {
            j = rng.nextInt(deck.length);

            temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }








    {


    }



    @Override
    public void keyTyped(KeyEvent e) {
        if (startscreen == true && e.getKeyChar() == 'b') {
            startscreen = false;
            blackjackgame = true;
            instructionscreen=false;
        }
        if (blackjackgame==true){
            if (e.getKeyChar()=='1'){
                blackjacktable1=true;
                blackjackgame=false;
                blackjacktable2=false;
            }

        }
        if (blackjackgame==true){
            if (e.getKeyChar()=='2'){
                blackjacktable2=true;
                blackjackgame=false;
                blackjacktable1=false;
            }
        }




        if (startscreen == true && e.getKeyChar() == 's') {
            startscreen = false;
            blackjacktable1 = false;
            blackjackgame=false;
            slots = true;
        }


    }

    @Override
    public void keyPressed(KeyEvent e) {



    }




    @Override
    public void keyReleased(KeyEvent e) {

    }


}



