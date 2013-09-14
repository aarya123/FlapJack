package Engine;

import java.util.Random;

public class Strategy {
	Hand hand;
	Move getNextMove() {
		Random r = new Random();
		int move = r.nextInt(Move.values().length);
		return Move.values()[move];
	}
	
}
