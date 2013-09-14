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

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Card card : cards) {
            buffer.append(card);
            buffer.append(" ");
        }
        return buffer.toString().trim();
    }
    void setAmountWagered(double wager) {
    	this.amountWagered = wager;
    }
    double getAmountWagered() {
    	return this.amountWagered;
    }
    // Called when player wants to hit
    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
    

    public boolean containsAce() {
        for(int i=0; i<cards.size(); i++) {
            if (cards.get(i).getRank().equals("A"))
                return true;
        }
        return false;
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

    
    public boolean frozen() {
    	return this.frozen;
    }
    
    public boolean active() {
    	return (!this.frozen && !this.isBusted() && !this.twentyOne());
    }

    // returns true if hand is soft 17
    public boolean softSeventeen() {
        int[] softSeventeen = new int[]{7, 17};
        return Arrays.equals(getValues(), softSeventeen);
    }

    public boolean twentyOne() {
    	boolean twentyOne = false;
    	int[] values = getValues();
    	for (int x: values) {
    		if (x == 21) {
    			twentyOne = true;
    		}
    	}
    	return twentyOne;
    }
    
    // returns true if hand is A, 10/J/Q/K
    public boolean blackjack() {
        //int[] blackjack = new int[]{10, 11};
        //return Arrays.equals(getValues(), blackjack); //TODO fix
    	ArrayList<String> ranks = new ArrayList<String>();
    	for (Card x : cards) {
    		ranks.add(x.getRank());
    	}
    	return (ranks.contains("A") && containsFace(ranks) && ranks.size() == 2);
    }
    
    private boolean containsFace(ArrayList<String> ranks) {
    	boolean containsFace = false;
    	for (String r : ranks ) {
    		if (r.equals("K")) {
    			containsFace = true;
    		} else if (r.equals("Q")) {
    			containsFace = true;
    		} else if (r.equals("J")) {
    			containsFace = true;
    		} else {
    			// Not a face
    		}
    	}
    	return containsFace;
    }

    public boolean isBusted() {
        boolean busted = true;
        int[] values = getValues();
        for (int x : values) {
            if (x <= 21) {
                busted = false;
            }
        }
        return busted;
    }

   public Hand[] split() {
        // Can split only if hand has 2 cards
        if (cards.size() != 2)
            return null;

        Hand[] newHands = new Hand[2];
        //newHands[0] = new Hand(new ArrayList<Card>(), this.amountWagered);
        newHands[1] = new Hand(new ArrayList<Card>(), this.amountWagered);

        // Split cards from original hand
        newHands[1].addCard(cards.remove(cards.size() - 1));
        //newHands[0].addCard(cards.remove(cards.size() - 1));
        newHands[0] = this;

        // Add new cards from shoe
        //newHands[0].addCard(shoe.removeTopCard());
        //newHands[1].addCard(shoe.removeTopCard());

        return newHands;
    }
}
