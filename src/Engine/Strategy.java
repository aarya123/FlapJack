package Engine;

public class Strategy {

    Hand hand;

    Move getNextMove() {
        return Move.S;
    }

    int getHottnessForCard(Card card) {
        return 0;
    }
}
