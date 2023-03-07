import javax.swing.*;

public class Card {

   public int cardNumber;
   public int Value;
   public int suitValue;
   public String suit;
   public String cardName;
   public Card(int aSuit, int aValue) {
      suitValue = aSuit;
      Value = aValue;
      switch (this.Value) {
         case 1:
            cardName = "Ace";
            cardNumber=11;
            break;
         case 2:
            cardName = "Two";
            cardNumber=2;
            break;
         case 3:
            cardName = "Three";
            cardNumber=3;
            break;
         case 4:
            cardName = "Four";
            cardNumber=4;
            break;
         case 5:
            cardName = "Five";
            cardNumber=5;
            break;
         case 6:
            cardName = "Six";
            cardNumber=6;
            break;
         case 7:
            cardName = "Seven";
            cardNumber=7;
            break;
         case 8:
            cardName = "Eight";
            cardNumber=8;
            break;
         case 9:
            cardName = "Nine";
            cardNumber=9;
            break;
         case 10:
            cardName = "Ten";
            cardNumber=10;
            break;
         case 11:
            cardName = "Jack";
            cardNumber=10;
            break;
         case 12:
            cardName = "Queen";
            cardNumber=10;
            break;
         case 13:
            cardName = "King";
            cardNumber=10;
            break;
      }
      switch (suitValue) {

         case 1:
            suit = "Hearts";
            break;

         case 2:
            suit = "Spades";
            break;

         case 3:
            suit = "Diamonds";
            break;

         case 4:
            suit = "Clubs";
            break;
         default:
            System.out.println("Bad suit");


      }

   }
   Player player=new Player();
      public void printCard () {
         JOptionPane.showMessageDialog(null,cardName+" of "+suit,player.name,JOptionPane.PLAIN_MESSAGE);
      }
      public int getValue () {
         return Value;
      }
   }

