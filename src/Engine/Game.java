package Engine;

import java.util.ArrayList;

/* Skeleton code for the Game class*/

public class Game {
	Strategy strategy;
	Casino casino;
	Shoe shoe;
	double initialAmountWagered, actualAmountWagered, totalProfit;
	Game(Strategy strategy, Casino casino, Shoe shoe, double initialAmountWagered) {
		this.strategy = strategy;
		this.casino = casino;
		this.shoe = shoe;
		this.initialAmountWagered = initialAmountWagered;
		this.actualAmountWagered = initialAmountWagered;
		this.totalProfit = 0.0;
	}
	
	void setActualAmountWagered(double value) {
		actualAmountWagered = value;
	}
	
	double getActualAmountWagered() {
		return actualAmountWagered;
	}
	
	double getInitialAmountWagered() {
		return initialAmountWagered;
	}
	
	double getProfit() {
		return totalProfit;
	}
	
	/* Returns the dealer's hidden card after the initial deal*/
	Card distributeCards(Hand dealerHand, Hand playerHand) {
		Card hidden;
		hit(playerHand);
		hidden = hit(dealerHand);
		hit(playerHand);
		hit(dealerHand);
		return hidden;
	}

    Card hit(Hand hand) {
        Card card = shoe.removeTopCard();
        hand.addCard(card);
        return card;
    }

    public String won(Hand playerHand, Hand dealerHand) {
        // call playerHand.getValue(), dealerVisibleHand.getValue()
        int playerValue;
        int dealerValue;

        // set best case player hand
        int[] playerValueArray = playerHand.getValues();
        if (playerValueArray.length == 2)
            playerValue = getBetterHand(playerValueArray[0], playerValueArray[1]);
        else
            playerValue = playerValueArray[0];

        // set best case dealer hand
        int[] dealerValueArray = dealerHand.getValues();
        if (dealerValueArray.length == 2)
            dealerValue = getBetterHand(dealerValueArray[0], dealerValueArray[1]);
        else
            dealerValue = dealerValueArray[0];

        if ((playerValue > dealerValue) || (dealerValue > 21))
            return "true";
        else if ((playerValue < dealerValue) || (playerValue > 21))
            return "false";
        else
            return "tie";
    }


    private double calculateProfit(String won, Hand playerHand) {
    	double profit;
        double blackjackMultiplier = casino.getBlackjackMultiplier();
        if (won.equals("true")) {
            if (playerHand.blackjack()) {
                profit = actualAmountWagered * blackjackMultiplier;
            } else {
                profit = actualAmountWagered;
            }
        } else {
            //tie or loss
            profit = (-1) * actualAmountWagered;
        }
        
        return profit;
    }


    private int getBetterHand(int hand1, int hand2) {
        if (hand1 <= 21 && hand2 <= 21)
            return hand1 > hand2 ? hand1 : hand2;
        else
            return hand1 > hand2 ? hand2 : hand1;
    }

    ArrayList<Card> removeTopNCards(int numberCards) {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < numberCards; i++) {
            cards.add(shoe.removeTopCard());
        }
        return cards;
    }

    void completeDealerHand(Hand dealerHand, Card dealerHiddenCard) {
        while (!reachedN(dealerHand, 17)) {
            hit(dealerHand);
        }
        if (dealerHand.softSeventeen()) {
            hit(dealerHand);
        }
    }

    boolean reachedN(Hand hand, int n) {
        boolean reached = false;
        int[] values = hand.getValues();
        for (int x : values) {
            if (x >= n) {
                reached = true;
            }
        }
        return reached;
    }

	// returns the number of hands that are not busted
    int numberActiveHands(ArrayList<Hand> playerHands) {
    	int count = 0;
    	for (Hand hand : playerHands) {
    		if (hand.active()) {
    			++ count;
    		}
    	}
    	return count;
    }
    
    // returns a hand if it splits, returns null otherwise
    public Hand playHand(Hand playerHand, Hand dealerHand) {
    	Hand newHand = null;
    	Move move;
    	while (true) {
        	if (reachedN(playerHand, 22)) {
        		break;
        	}
            move = strategy.getNextMove();
            if (move == Move.STAND) {
                break;
            } else if (move == Move.DOUBLE) {
            	setActualAmountWagered(actualAmountWagered*2);
            	hit(playerHand);
            	playerHand.freeze();
            	continue;
            } else if (move == Move.SPLIT) {
            	Hand[] splitHands = playerHand.split();
            	playerHand = splitHands[0];
            	newHand = splitHands[1];
            	hit(newHand);
            	setActualAmountWagered(getActualAmountWagered() + getInitialAmountWagered());
            }
            hit(playerHand);
        }
    	return newHand;
    }

    public void play() {
        Hand dealerHand = new Hand(new ArrayList<Card>(), initialAmountWagered);
        
        ArrayList<Hand> playerHands;
        playerHands = new ArrayList<Hand>();
        playerHands.add(new Hand(new ArrayList<Card>(), initialAmountWagered));
        
        Card dealerHiddenCard;
        dealerHiddenCard = distributeCards(dealerHand, playerHands.get(0));
        
        while (numberActiveHands(playerHands) > 0) {
        	for (int i = 0; i < playerHands.size(); i++) {
        		if (playerHands.get(i).active()) {
        			Hand newHand = playHand(playerHands.get(i), dealerHand);
        			if (newHand != null) {
        				playerHands.add(i+1, newHand);
        			}
        		}
        	
        	}
        }
        completeDealerHand(dealerHand, dealerHiddenCard);
        
        
        String won;
        for (Hand playerHand : playerHands) {
        	won = won(playerHand, dealerHand);
            totalProfit += calculateProfit(won, playerHand);
        }
        
    }
}
