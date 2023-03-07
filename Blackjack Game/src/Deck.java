//import java.util.Random;
//
//public class Deck {
//
////    private Card[] myCards;
////    private int nCards;
////
////    public boolean nonewdeck;
////
////    public boolean noshuffleclick;
////
////    public Deck(int deckCount, boolean shuffle) {
////        nCards = deckCount * 52;
////        myCards = new Card[nCards];
////        if (nonewdeck == true) {
////            deckCount = 1;
////        }
////        if (noshuffleclick == true) {
////            shuffle = false;
////        }
////        int c = 0;
////
////        for (int d = 0; d < deckCount; d++) {
////
////
////            for (int n = 1; n < 14; n++) {
////
////
////                for (int s = 1; s < 5; s++) {
////
////                    myCards[c] = new Card(s,n);
////                    c++;
////                }
////            }
////        }
////
////
////
////    }
////
////
////
////
////    public void printDeck(int numtoPrint) {
////
////        int c;
////        for (c = 0; c < numtoPrint; c++) {
////            System.out.printf("% 3d/%d %s\n", c + 1, nCards,
////                    myCards[c].toString());
////        }
////        System.out.printf("\t\t[%d other]\n", nCards - numtoPrint);
////
////    }
////
////    public Card deal() {
////        Card top = myCards[0];
////        int c;
////        for (c = 1; c < nCards; c++) {
////            myCards[c - 1] = myCards[c];
////        }
////        myCards[nCards - 1] = null;
////        nCards--;
////
////        return top;
////
////    }
//
//}