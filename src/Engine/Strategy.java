package Engine;

import java.util.HashMap;

public class Strategy {

    static HashMap<String, Double> hotnessMap;
    static int deckHotness = 0;

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
        if (deckHotness < 25)
            return 1;
        else if(deckHotness<30)
            return 10;
        else
            return 15;
    }

    public static void shuffled() {
        deckHotness = 0;
    }

    static int total = 0;

    public static void assess(Game game) {
        for (int i = 0; i < game.playerHands.size(); i++)
            for (int j = 0; j < game.playerHands.get(i).getCards().size(); j++)
                deckHotness += hotnessMap.get(game.playerHands.get(i).getCards().get(j).getRank());
        deckHotness += hotnessMap.get(game.dealerHiddenCard.getRank());
        total += deckHotness;
    }
}
