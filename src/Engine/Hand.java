package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<Card>();
    double amountWagered;
    boolean frozen;

    // Constructor
    public Hand(ArrayList<Card> cards, double amountWagered) {
        this.cards = cards;
        this.amountWagered = amountWagered;
        this.frozen = false;
    }

    // Called when player wants to hit
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public List<Card> getCards() {
        return cards;
    }
    

    // Returns an array of length 1 or 2 containing possible values of the hand
    public int[] getValues() {
        int val0 = 0; // Hand value assuming first ace is valued at 1
        int val1 = 0; // Hand value assuming first ace is valued at 11
        boolean hasAce = false;
        int[] cardValue;

        for (int i = 0; i < cards.size(); i++) {
            cardValue = cards.get(i).getValues();

            // If Ace
            if (cardValue.length == 2) {
                // If Ace already exists in hand, just add 1
                if (hasAce) {
                    val0 += cardValue[0]; // 1
                    val1 += cardValue[0]; // 1
                } else {
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

        if (val0 == val1) {
            return new int[]{val0};
        } else {
            return new int[]{val0, val1};
        }
    }
    
    public void freeze() {
    	this.frozen = true;
    }
    
    public boolean busted() {
    	int[] values = getValues();
    	for (int i : values) {
    		if (values[i] < 22) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean frozen() {
    	return this.frozen;
    }
    
    public boolean active() {
    	return (!this.frozen && !this.busted());
    }

    // returns true if hand is soft 17
    public boolean softSeventeen() {
    	int[] softSeventeen = new int[] { 7, 17 };
    	return Arrays.equals( getValues(), softSeventeen );
    }

    // returns true if hand is A, 10/J/Q/K
    public boolean blackjack() {
    	int[] blackjack = new int[] { 10, 11 };
        return Arrays.equals( getValues(), blackjack );
    }

   public Hand[] split() {
        // Can split only if hand has 2 cards
        if(cards.size() != 2)
            return null;

        Hand[] newHands = new Hand[2];
        newHands[0] = new Hand(new ArrayList<Card>(), this.amountWagered);
        newHands[1] = new Hand(new ArrayList<Card>(), this.amountWagered);

        // Split cards from original hand
        newHands[1].addCard(cards.remove(cards.size() - 1));
        newHands[0].addCard(cards.remove(cards.size() - 1));

        // Add new cards from shoe
        //newHands[0].addCard(shoe.removeTopCard());
        //newHands[1].addCard(shoe.removeTopCard());

        return newHands;
   }
}
