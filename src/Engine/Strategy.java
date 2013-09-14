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

        System.out.println(strategy_type);
        hotnessMap = new HashMap<String, Double>();

        if(strategy_type.equalsIgnoreCase("Basic Strategy"))
            hotnessMap = null;
        else if(strategy_type.equalsIgnoreCase("Counting Strategy"))
        {
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
            hotnessMap.put("A", -0.8862665461377872);
            hotnessMap.put("2", 0.8754767395547716);
            hotnessMap.put("3", 1.1280118609466179);
            hotnessMap.put("4", -0.2748576476997078);
            hotnessMap.put("5", 0.6847146657102476);
            hotnessMap.put("6", 2.886921398580731);
            hotnessMap.put("7", 0.7549035038876444);
            hotnessMap.put("8", -1.2861682790598536);
            hotnessMap.put("9", 1.0659929347268373);
            hotnessMap.put("10", -0.5759288761044206);
            hotnessMap.put("J", -1.9197111838717347);
            hotnessMap.put("Q", -1.4491997383971356);
            hotnessMap.put("K", 1.3426116475470553);
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
      if (hotness <= 0)
        return 1;
      else
      {
          if (hotness != 1)
              System.out.println("Hotness not 1 bitch " + hotness);

          return hotness;
      }
    }
}
