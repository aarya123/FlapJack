package Engine;

public class Strategy {

    double[] hotnessMap, bettingFunction;

    // bettingFunction is an array of length 5; 
    public Strategy(double[] hotnessMap, int[] bettingFunction) {
      this.hotnessMap = hotnessMap;
      this.bettingFunction = bettingFunction;
    }

    double getHottnessForCard(Card card) {
      return hotnessMap[card.getValues()[0] - 1];
    }

    double getBetMultiplier(double hotness) {
      return 1; // TODO
    }
}
