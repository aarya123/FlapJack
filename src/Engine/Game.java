package Engine;

import java.util.ArrayList;

/* Skeleton code for the Game class*/

public class Game {
	Strategy strategy;
	Casino casino;
	Shoe shoe;
	double initialAmountWagered, actualAmountWagered, totalProfit;
	Game(Strategy strategy, Casino casino, Shoe shoe, double initialAmountWagered) {
		if(initialAmountWagered < 0)
			System.out.println("BAD!");
		this.strategy = strategy;
		this.casino = casino;
		this.shoe = shoe;
		this.initialAmountWagered = initialAmountWagered;
		this.actualAmountWagered = 0;
		this.totalProfit = 0.0;
	}

    void addToActualAmountWagered(double value) {
        actualAmountWagered += value;
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
        String results = "tie";

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

        if (playerHand.isBusted())
            results = "false";
        else if (dealerHand.isBusted())
            results = "true";
        else if (playerValue > dealerValue)
            results = "true";
        else if (playerValue < dealerValue)
            results = "false";

        return results;
    }


    private double calculateProfit(String won, Hand playerHand) {
        double blackjackMultiplier = casino.getBlackjackMultiplier();

        if (won.equals("true")) {
            if (playerHand.blackjack()) {
                return playerHand.getAmountWagered() * blackjackMultiplier;
            } else {
                return playerHand.getAmountWagered();
            }
        }
        else if (won.equals("tie")){
            return 0;
        } 
        else {
            //tie or loss
            return (-1) * playerHand.getAmountWagered();
        }
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
    private int numberActiveHands(ArrayList<Hand> playerHands) {
    	int count = 0;
    	for (Hand hand : playerHands) {
    		if (hand.active()) {
    			++ count;
    		}
    	}
    	return count;
    }
    private void stand(Hand hand) {
    	hand.freeze();
    }
    
    // returns a hand if it splits, returns null otherwise
    public void playHand(Hand playerHand, Hand dealerHand, Card dealerHiddenCard, ArrayList<Hand> playerHands) {
    	if (playerHand.isBusted())
            return;

    	String move = BasicStrategy.nextMove(playerHand, dealerHiddenCard);

    	if (move.equals("S")) {
            addToActualAmountWagered(playerHand.getAmountWagered());
    		stand(playerHand);
            playerHand.freeze();
    	}

        else if (move.equals("D")) {
            addToActualAmountWagered(playerHand.getAmountWagered() * 2);
    		hit(playerHand);
    		playerHand.freeze();
    	}

        else if (move.equals("P")) {
    		Hand[] splitHands = playerHand.split();
			Hand oldHand = splitHands[0];
			Hand newHand = splitHands[1];
            hit(oldHand);
			hit(newHand);

            playerHands.add(newHand);
            playHand(oldHand, dealerHand, dealerHiddenCard, playerHands);
            playHand(newHand, dealerHand, dealerHiddenCard, playerHands);     

    	} else {
            addToActualAmountWagered(playerHand.getAmountWagered());
    		hit(playerHand); //move was HIT
    	}
    }

    public void play() {
        Hand dealerHand = new Hand(new ArrayList<Card>(), initialAmountWagered);
        Hand playerHand = new Hand(new ArrayList<Card>(), initialAmountWagered);
        Card dealerHiddenCard = distributeCards(dealerHand, playerHand);

        // Add player hand to arraylist        
        ArrayList<Hand> playerHands = new ArrayList<Hand>();
        playerHands.add(playerHand);
        
        playHand(playerHand, dealerHand, dealerHiddenCard, playerHands);
        completeDealerHand(dealerHand, dealerHiddenCard);
        
        for (Hand hand : playerHands) {
        	String won = won(hand, dealerHand);
            totalProfit += calculateProfit(won, playerHand);
        }
    }
}
