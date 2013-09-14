package Engine;

public class Strategy {

	Hand hand;
	Move getNextMove() {
		return Move.STAND;
	}

  int getHottnessForCard( Card card ) {
    return 0;
  }
}
