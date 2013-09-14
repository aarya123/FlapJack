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
	
	void hit(Hand hand) {
		hand.addCard(shoe.removeTopCard());
	}
	
	boolean won(Hand playerHand, Hand dealerVisibleHand, Card dealerHiddenCard) {
		// call playerHand.getValue(), dealerVisibleHand.getValue()
		return true;
	}
	
	//double getAmountWagered()
	//double getProfit()
	ArrayList<Card> removeTopNCards(int numberCards) {
		return new ArrayList<Card>();
	}
	
	void completeDealerHand(Hand dealerVisibleHand, Card dealerHiddenCard) {
		;
	}
	
	public boolean play() {
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
