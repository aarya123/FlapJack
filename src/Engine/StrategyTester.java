package Engine;

import java.util.HashMap;
import java.util.Random;

public class StrategyTester {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //start with a well known counting strategy like Wong Halves
        HashMap<String, Double> hotnessMap = new HashMap<String, Double>();

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

        TestResult best = bestStrategy(-1, hotnessMap, 5);

        //choose best strategy
        System.out.println(best.advantage);
        printHotnessMap(best.hotnessMap);

    }

    public static TestResult bestStrategy(double startingAdvantage, HashMap<String, Double> startingMap, int remainingChildren) {
        if (remainingChildren == 0)
            return new TestResult(startingAdvantage, startingMap);

        Casino casino = new Casino("Bellagio", 1.4, 6, true, true, true);
        casino.setNumberOfGames((int) (Math.random() * 100000));

        //generate 10,000 random combinations with Wong Halves as mean
        Random r = new Random();
        int num_combos = 10;

        HashMap<String, Double> bestHotnessMap = new HashMap<String, Double>();
        bestHotnessMap = (HashMap<String, Double>) startingMap.clone();
        double bestAdvantage = startingAdvantage;

        for (int c = 0; c < num_combos; c++) {
            HashMap<String, Double> tempHotnessMap = new HashMap<String, Double>();
            //define hotness map
            for (int i = 1; i < 14; i++) {
                double variation = r.nextDouble() * 2 - 1;

                if (i == 1)
                    tempHotnessMap.put("A", startingMap.get("A") + variation);
                else if (i == 2)
                    tempHotnessMap.put(i + "", startingMap.get(i + "") + variation);
                else if (i == 3)
                    tempHotnessMap.put(i + "", startingMap.get(i + "") + variation);
                else if (i == 4)
                    tempHotnessMap.put(i + "", startingMap.get(i + "") + variation);
                else if (i == 5)
                    tempHotnessMap.put(i + "", startingMap.get(i + "") + variation);
                else if (i == 6)
                    tempHotnessMap.put(i + "", startingMap.get(i + "") + variation);
                else if (i == 7)
                    tempHotnessMap.put(i + "", startingMap.get(i + "") + variation);
                else if (i == 8)
                    tempHotnessMap.put(i + "", startingMap.get(i + "") + variation);
                else if (i == 9)
                    tempHotnessMap.put(i + "", startingMap.get(i + "") + variation);
                else if (i == 10)
                    tempHotnessMap.put("10", startingMap.get(i + "") + variation);
                else if (i == 11)
                    tempHotnessMap.put("J", startingMap.get("J") + variation);
                else if (i == 12)
                    tempHotnessMap.put("Q", startingMap.get("Q") + variation);
                else
                    tempHotnessMap.put("K", startingMap.get("K") + variation);
            }

            //test strategy 10k times
            Session s = new Session(casino, new Strategy(startingMap, new HashMap<String, Double>()));
            s.playGames();
            double advantage = s.getExpectedValue();

            System.out.println(advantage);

            //record as bestHotnessMap if it has bestAdvantage
            if (advantage > bestAdvantage) {
                bestAdvantage = advantage;
                bestHotnessMap = (HashMap<String, Double>) tempHotnessMap.clone();
            }

        }

        TestResult t = bestStrategy(bestAdvantage, bestHotnessMap, remainingChildren - 1);
        if (t.advantage > bestAdvantage) {
            bestAdvantage = t.advantage;
            bestHotnessMap = t.hotnessMap;
        }

        return new TestResult(bestAdvantage, bestHotnessMap);
    }


    public static void printHotnessMap(HashMap<String, Double> hotnessMap) {
        for (int i = 1; i < 14; i++) {
            String s = "";

            if (i == 1)
                s += "A " + hotnessMap.get("A");
            else if (i < 11)
                s += i + " " + hotnessMap.get(i + "");
            else if (i == 11)
                s += "J " + hotnessMap.get("J");
            else if (i == 12)
                s += "Q " + hotnessMap.get("Q");
            else
                s += "K " + hotnessMap.get("K");

            System.out.println(s);
        }
    }
}
