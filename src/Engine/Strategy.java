package Engine;
import java.util.Random;

import java.util.HashMap;

public class Strategy {

    HashMap<String, Double> hotnessMap;

    // bettingFunction is an array of length 5; 
    double getHottnessForCard(Card card) {
        //return hotnessMap.get(card.getRank());
    	return 0;
    }

    double getBetMultiplier(double hotness) {
        return 1;
    }
}
