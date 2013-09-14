package Engine;
import java.util.Random;

import java.util.HashMap;

public class Strategy {

    HashMap<String, Double> hotnessMap;
    // bettingFunction is an array of length 5; 
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

    public double getHottnessForCard(Card card) {
        return hotnessMap.get(card.getRank());
    	}



    double getBetMultiplier(double hotness) {
        return 1;
    }
}
