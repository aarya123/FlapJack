package Engine;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<Card>();

    // Constructor
    public Hand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    // Called when player wants to hit
    public void addCard(Card card) {
        cards.add(card);
    }

    // Returns an array of length 1 or 2 containing possible values of the hand
    public int[] getValues() {
        int val0 = 0; // Hand value assuming first ace is valued at 1
        int val1 = 0; // Hand value assuming first ace is valued at 11
        boolean hasAce = false;
        int[] cardValue;

        for(int i=0; i<cards.size(); i++) {
            cardValue = cards.get(i).getValues();

            // If Ace
            if(cardValue.length == 2) {
                // If Ace already exists in hand, just add 1
                if(hasAce) {
                    val0 += cardValue[0]; // 1
                    val1 += cardValue[0]; // 1
                }
                else {
                    val0 += cardValue[0]; // 1
                    val1 += cardValue[1]; // 11
                    hasAce = true;
                }
            }

            // Otherwise single-value cards (2-10, J, Q, K)
            else {
                val0 += cardValue[0];
                val1 += cardValue[0];
            }
        }

        if(val0 == val1) {
            return new int[] { val0 };
        }
        else {
            return new int[] {val0, val1};
        }
    }

    // TODO
   public Hand split() {
        if(cards.size() != 2) {
            return null;
        }

        Hand[] newHands = new Hand[2];
        ArrayList<Card> cardForHand0 = new ArrayList<Card>();
        ArrayList<Card> cardForHand1 = new ArrayList<Card>();


        newHands[0] = new Hand();

        

   }
}
