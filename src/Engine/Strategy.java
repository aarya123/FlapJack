package Engine;
public class Strategy {
	Hand hand;
	Move getNextMove() {
		return Move.STAND;
	};
	Hand getHand() {
		return hand;
	}
	
}
