package Engine;

import java.util.HashMap;

public class Strategy {

    static HashMap<String, Double> hotnessMap;
    static int deckHotness = 0;

    // bettingFunction is an array of length 5;
    public Strategy() {
        hotnessMap = new HashMap<String, Double>();
        for (int i = 1; i < 14; i++) {
            if (i == 1)
                hotnessMap.put("A", -1.0);
            else if (i == 11)
                hotnessMap.put("J", -1.0);
            else if (i == 12)
                hotnessMap.put("Q", -1.0);
            else if (i == 13)
                hotnessMap.put("K", -1.0);
            else if (i > 1 && i < 7)
                hotnessMap.put(i + "", 1.0);
            else
                hotnessMap.put(i + "", 0.0);
        }
    }

    double getHottnessForCard(Card card) {
        return hotnessMap.get(card.getRank());
    }

    double getBetMultiplier(double hotness) {
        if (deckHotness < 20)
            return 1;
        else
            return 10;
    }

    public static void shuffled() {
        deckHotness = 0;
    }

    public static void assess(Game game) {
        for (int i = 0; i < game.playerHand.getCards().size(); i++)
            deckHotness += hotnessMap.get(game.playerHand.getCards().get(i).getRank());
        deckHotness += hotnessMap.get(game.dealerHiddenCard.getRank());
        if (deckHotness > 20)
            System.out.println(deckHotness);
    }
}
