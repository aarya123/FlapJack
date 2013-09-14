package Engine;

import java.util.HashMap;

public class Strategy {

    HashMap<String, Double> hotnessMap;

    // bettingFunction is an array of length 5; 
<<<<<<< HEAD
    public Strategy() {
        hotnessMap = new HashMap<String, Double>();
        for (int i = 1; i < 14; i++) {
            if (i == 1)
                hotnessMap.put("A", 1.0);
            else if (i == 11)
                hotnessMap.put("J", 1.0);
            else if (i == 12)
                hotnessMap.put("Q", 1.0);
            else if (i == 13)
                hotnessMap.put("K", 1.0);
            else
                hotnessMap.put(i + "", 1.0);
        }
    }

    double getHottnessForCard(Card card) {
        return hotnessMap.get(card.getRank());
=======
    public Strategy(double[] hotnessMap, int[] bettingFunction) {
      this.hotnessMap = hotnessMap;
      this.bettingFunction = bettingFunction;
    }

    double getHottnessForCard(Card card) {
      return hotnessMap[card.getValues()[0] - 1];
>>>>>>> parent of 706d131... Fix bug in strategy
    }

    double getBetMultiplier(double hotness) {
        return 1;
    }
}
