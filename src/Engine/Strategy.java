package Engine;

import java.util.HashMap;
import java.util.Random;

public class Strategy {

    double[] hotnessMap;

    // Creates a random strategy
    public Strategy() {
      this.hotnessMap = randomHotnessMap();
    }

    public Strategy(double[] hotnessMap) {
      this.hotnessMap = hotnessMap;
    }

    double[] getHotnessMap() {
      return hotnessMap;
    }

    double getHottnessForCard(Card card) {
        return hotnessMap[card.getValues()[0] - 1];
    }

    double getBetMultiplier(double hotness) {
       return hotness;
    }

    // Randomizer functions
    
    // Returns array mapping card values -> hotness values
    // Indices are getValue()[0] - 1 (A -> 0, 2 -> 1, ... 10 -> 9)
    double[] randomHotnessMap() {
      Random r = new Random();
      double[] randomHotnessMap = new double[10];

      for(int i=0; i<randomHotnessMap.length; i++) {
        int randomSign = (Math.round(r.nextDouble()) == 1) ? 1 : -1; // random number: -1 or 1
        double randomDouble = r.nextDouble() * 5; // random double between 0 and 5
        randomHotnessMap[i] = randomDouble * randomSign; // random double between -5 and 5
      }

      return randomHotnessMap;
    }
}
