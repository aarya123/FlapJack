package Engine;

import java.util.ArrayList;
import java.util.Iterator;

public class testStrategy extends Strategy {
	ArrayList<String> moves;
	Iterator<String> movesIterator;
	public testStrategy(ArrayList<String> moves) {
		this.moves = moves;
		movesIterator = this.moves.iterator();
	}
	
	public String nextMove() {
		if (movesIterator.hasNext()) {
			return movesIterator.next();
		} else {
			return null;
		}
	}
	
	public double getHotnessForCard(Card card) {
		return 0;
	}
}
