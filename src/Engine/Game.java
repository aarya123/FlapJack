package Engine;

import java.util.ArrayList;

/* Skeleton code for the Game class*/

public class Game {
	Strategy strategy;
	Casino casino;
	Shoe shoe;
	double amountWagered, profit;
	Game(Strategy strategy, Casino casino, Shoe shoe) {
		this.strategy = strategy;
		this.casino = casino;
		this.shoe = shoe;
	}
	
	double getAmountWagered() {
		return amountWagered;
	}
	double getProfit() {
		return profit;
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
	
	String won(Hand playerHand, Hand dealerHand, Card dealerHiddenCard) {
		//TODO call playerHand.getValue(), dealerVisibleHand.getValue()
		return "true";
	}
	
	private void calculateProfit(String won, Hand playerHand) {
		double blackjackMultiplier = casino.getBlackjackMultiplier();
		if (won.equals("true")) {
			if (playerHand.blackjack()) {
				profit = amountWagered * blackjackMultiplier;
			} else {
				profit = amountWagered;
			}
		} else {
			//tie or loss
			profit = (-1) * amountWagered;
		}
	}
	
	ArrayList<Card> removeTopNCards(int numberCards) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i=0; i<numberCards; i++) {
			cards.add(shoe.removeTopCard());
		}
		return cards;
	}
	
	void completeDealerHand(Hand dealerVisibleHand, Card dealerHiddenCard) {
		dealerVisibleHand.addCard(dealerHiddenCard); // The dealer's hidden card becomes known.
		while (!reachedN(dealerVisibleHand, 17)) {
			hit(dealerVisibleHand);
		}
		if (dealerVisibleHand.softSeventeen()) {
			hit(dealerVisibleHand);
		}
	}
	boolean reachedN(Hand hand, int n) {
		boolean reached  = false;
		int[] values = hand.getValues();
		for (int x : values) {
			if (x >= n) {
				reached = true;
			}
		}
		return reached;
	}
	
	public void play(Casino casino) {
		boolean playing = true;
		String won = "false";
		Move move;
		Hand dealerVisibleHand = new Hand(new ArrayList<Card>());
		Hand playerHand = new Hand(new ArrayList<Card>());
		Card dealerHiddenCard;

		dealerHiddenCard = distributeCards(dealerVisibleHand, playerHand);
		while (playing) {
			move = strategy.getNextMove();
			if (move == Move.STAND) {
				break;
			} 
			playerHand.addCard(shoe.removeTopCard());
		}
		
		completeDealerHand(dealerVisibleHand, dealerHiddenCard);
		won = won(strategy.getHand(), dealerVisibleHand, dealerHiddenCard); //check hand.handValues
		calculateProfit(won, playerHand);
	}
	
}
