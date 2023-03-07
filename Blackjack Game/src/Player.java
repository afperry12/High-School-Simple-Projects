public class Player {

    public String name;
    public Card[] hand;
    public int numberOfCards;
    public int total;
    public boolean done=false;
    public Player(){hand=new Card[13];}

    public Player(String aName){
        name=aName;
        hand=new Card[13];
//        emptyHand();

    }
//    public void emptyHand(){
//        for (int c=0;c<12;c++){
//            hand[c]=null;
//        }
//        numCards=0;
//    }


}


