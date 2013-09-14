package Engine;

import java.util.ArrayList;

/* Skeleton code for the Game class*/

public class Game {
	Strategy strategy;
	Casino casino;
	Shoe shoe;
	Game(Strategy strategy, Casino casino, Shoe shoe) {
		this.strategy = strategy;
		this.casino = casino;
		this.shoe = shoe;
	}
	
	/* Returns the dealer's hidden card after the initial deal*/
	Card distributeCards(Hand dealerVisibleHand, Hand playerHand) {
		Card hidden;
		hit(playerHand);
		hidden = shoe.removeTopCard();
		hit(playerHand);
		hit(dealerVisibleHand);
		return hidden;
	}
	
	public void hit(Hand hand) {
		hand.addCard(shoe.removeTopCard());
	}
	
	public String won(Hand playerHand, Hand dealerHand, Card dealerHiddenCard) {
		// call playerHand.getValue(), dealerVisibleHand.getValue()
		int playerValue;
		int dealerValue;
		
		// set best case player hand
		int[] playerValueArray = playerHand.getValues();
		if ( playerValueArray.length == 2 )
			playerValue = getBetterHand( playerValueArray[0], playerValueArray[1] );
		else
			playerValue = playerValueArray[0];
		
		// set best case dealer hand
		int[] dealerValueArray = dealerHand.getValues();
		if ( dealerValueArray.length == 2 )
			dealerValue = getBetterHand( playerValueArray[0], playerValueArray[1] );
		else
			dealerValue = dealerValueArray[0];
		
		if ( playerValue > dealerValue )
			return "true";
		else if ( playerValue < dealerValue )
			return "false";
		else
			return "tie";
	}
	
	private int getBetterHand( int hand1, int hand2 ) {
		if ( hand1 <= 21 && hand2 <= 21 ) 
			return hand1 > hand2 ? hand1 : hand2;
		else
			return hand1 > hand2 ? hand2 : hand1;
	}
	
	//double getAmountWagered()
	//double getProfit()
	ArrayList<Card> removeTopNCards(int numberCards) {
		return new ArrayList<Card>();
	}
	
	void completeDealerHand(Hand dealerVisibleHand, Card dealerHiddenCard) {
		;
	}
	
	public String play() {
		boolean playing = true;
		boolean won = false;
		Move move;
		Hand dealerVisibleHand = new Hand(new ArrayList<Integer>());
		Hand playerHand = new Hand(new ArrayList<Integer>());
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
		return won;
	}
}
