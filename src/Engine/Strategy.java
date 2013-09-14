package Engine;

public class Strategy {

    double[] hotnessMap, bettingFunction;

    // bettingFunction is an array of length 5; 
    public Strategy() {
        //Setup hotnessMap and bettingFunction
    }

    double getHottnessForCard(Card card) {
        return 0;
    }

    double getBetMultiplier(double hotness) {
        return 1; // TODO
    }
}
