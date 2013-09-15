package Engine;

import java.util.HashMap;

public class Strategy {

    HashMap<String, Double> hotnessMap;
    HashMap<String, Double> bettingMap;

    // bettingFunction is an array of length 5; 

    public Strategy() {
    }

    public Strategy(HashMap<String, Double> hotnessMap, HashMap<String, Double> bettingMap) {
        this.hotnessMap = hotnessMap;
        this.bettingMap = bettingMap;
    }

    public Strategy(String strategy_type) {

        //System.out.println(strategy_type);
        hotnessMap = new HashMap<String, Double>();

        if (strategy_type.equalsIgnoreCase("Basic Strategy"))
            hotnessMap = null;
        else if (strategy_type.equalsIgnoreCase("Counting Strategy")) {
            hotnessMap.put("A", -1.0);
            hotnessMap.put("2", 1.0);
            hotnessMap.put("3", 1.0);
            hotnessMap.put("4", 1.0);
            hotnessMap.put("5", 1.0);
            hotnessMap.put("6", 1.0);
            hotnessMap.put("7", 0.0);
            hotnessMap.put("8", 0.0);
            hotnessMap.put("9", 0.0);
            hotnessMap.put("10", -1.0);
            hotnessMap.put("J", -1.0);
            hotnessMap.put("Q", -1.0);
            hotnessMap.put("K", -1.0);
        } else {
            hotnessMap.put("A", -0.9726303732021138);
            hotnessMap.put("2", -0.004706292605272333);
            hotnessMap.put("3", -0.08435610144119576);
            hotnessMap.put("4", 1.8449808372042817);
            hotnessMap.put("5", 3.354715514790913);
            hotnessMap.put("6", 0.5427470530533602);
            hotnessMap.put("7", 2.741140155063029);
            hotnessMap.put("8", -0.6172078062524431);
            hotnessMap.put("9", -0.77773512061723);
            hotnessMap.put("10", -1.007584934520013);
            hotnessMap.put("J", -0.011820494223825584);
            hotnessMap.put("Q", -1.5230327260255503);
            hotnessMap.put("K", 1.4175083816178076);
        }

        /*
        for (int i = 1; i < 14; i++) {
            if (i == 1)
                hotnessMap.put("A", -1.0);
            else if (i == 2)
                hotnessMap.put(i + "", 0.5);
            else if (i == 3)
                hotnessMap.put(i + "", 1.0);
            else if (i == 4)
                hotnessMap.put(i + "", 1.0);
            else if (i == 5)
                hotnessMap.put(i + "", 1.5);
            else if (i == 6)
                hotnessMap.put(i + "", 1.0);
            else if (i == 7)
                hotnessMap.put(i + "", 0.5);
            else if (i == 8)
                hotnessMap.put(i + "", 0.0);
            else if (i == 9)
                hotnessMap.put(i + "", -0.5);
            else if (i == 10)
                hotnessMap.put("10", -1.0);
            else if (i == 11)
                hotnessMap.put("J", -1.0);
            else if (i == 12)
                hotnessMap.put("Q", -1.0);
            else
                hotnessMap.put("K", -1.0);
        }
*/

        //TestResult best = StrategyTester.bestStrategy(-1, hotnessMap, 1);

        //choose best strategy
        //System.out.println(best.advantage);
        //hotnessMap = best.hotnessMap;


        //hotnessMap = null;
        bettingMap = null;
    }

    public double getHottnessForCard(Card card) {
        if (hotnessMap == null)
            return 0;
        else
            return hotnessMap.get(card.getRank());
    }

    double getBetMultiplier(double hotness) {
        if (hotness <= 1)
            return 1;
        else {
            //if (hotness != 1)
            //  System.out.println("Hotness not 1 bitch " + hotness);

            double multip = Math.pow(hotness, 1.3) - 2;
            if (multip <= 1)
                multip = 1;

            return multip;

        }
    }
}
