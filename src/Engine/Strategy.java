package Engine;

public class Strategy {

    Hand hand;

    String getNextMove() {
        return "S";
    }

    int getHottnessForCard(Card card) {
        return 0;
    }
}
