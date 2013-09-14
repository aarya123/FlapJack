package Engine;

public class Strategy {

    double[] hotnessMap, bettingFunction;

    public Strategy() {
      this.hotnessMap = randomHotnessMap(); // TODO
      this.bettingFunction = randomBettingFunction(); // TODO
    }

    // bettingFunction is an array of length 5; 
    public Strategy(double[] hotnessMap, double[] bettingFunction) {
      this.hotnessMap = hotnessMap;
      this.bettingFunction = bettingFunction;
    }

    double[] getHotnessMap {
      return hotnessMap;
    }

    double[] getBettingFunction {
      return bettingFunction;
    }

    double getHottnessForCard(Card card) {
      return hotnessMap[card.getValues()[0] - 1];
    }

    double getBetMultiplier(double hotness) {
      return 1; // TODO
    }

    // Randomizer functions
    
    // Returns array mapping card values -> hotness values
    // Indices are getValue()[0] - 1 (A -> 0, 2 -> 1, ... 10 -> 9)
    double[] randomHotnessMap() {
      Random r = new Random();

      for(int i=0; i<10; i++) {
        int randomSign = (Math.round(r.nextDouble()) == 1) ? 1 : -1; // random number: -1 or 1
        double randomDouble = r.nextDouble() * 5; // random double between 0 and 5
        randomHotnessMap[i] = randomDouble * randomSign; // random double between -5 and 5
      }

      return randomHotnessMap;
    }

    double[] randomBettingFunction() {
      
    }

}
