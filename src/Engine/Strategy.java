package Engine;

public class Strategy {

	Hand hand;
	Move getNextMove() {
		return Move.STAND;
	}

  int getHottnessForCard() {
    return 0;
  }
}
