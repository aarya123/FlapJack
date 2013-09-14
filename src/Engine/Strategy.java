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
            hotnessMap.put("A", -2.1756303249843305);
            hotnessMap.put("2", 1.471905289907332);
            hotnessMap.put("3", 2.493300707741514);
            hotnessMap.put("4", 1.047077213721035);
            hotnessMap.put("5", 1.3295495093771246);
            hotnessMap.put("6", 0.6128952100955669);
            hotnessMap.put("7", 1.5146489202223947);
            hotnessMap.put("8", -1.4334882339141861);
            hotnessMap.put("9", 0.99583494590328);
            hotnessMap.put("10", -2.8206387996600006);
            hotnessMap.put("J", -0.20367587058540937);
            hotnessMap.put("Q", -0.49090966940460135);
            hotnessMap.put("K", -1.2287144995422192);
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
          //if (hotness != 1)
            //  System.out.println("Hotness not 1 bitch " + hotness);

          return hotness;
      }
    }
}
