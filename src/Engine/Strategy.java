package Engine;

import java.util.HashMap;

public class Strategy {

    HashMap<String, Double> hotnessMap;
    HashMap<String, Double> bettingMap;

    // bettingFunction is an array of length 5; 

    public Strategy(HashMap<String, Double> hotnessMap, HashMap<String, Double> bettingMap) {
    	this.hotnessMap = hotnessMap;
    	this.bettingMap = bettingMap;
      }
    
    public Strategy(String strategy_type) {

        System.out.println(strategy_type);

        hotnessMap = new HashMap<String, Double>();
        hotnessMap.put("A", -1.387353121876059);
        hotnessMap.put("2", 1.3991418283387418);
        hotnessMap.put("3", 1.4419554967488624);
        hotnessMap.put("4", 0.9262547816175997);
        hotnessMap.put("5", 2.4310899208072367);
        hotnessMap.put("6", 1.8317592836755148);
        hotnessMap.put("7", 1.05583117235636);
        hotnessMap.put("8", -0.8666335213289367);
        hotnessMap.put("9", -0.2269910738790346);
        hotnessMap.put("10", -0.2786954112602915);
        hotnessMap.put("J", -1.670498922712762);
        hotnessMap.put("Q", -1.80255657253995);
        hotnessMap.put("K", -0.6468274622179151);

    	//hotnessMap = null;
    	bettingMap = null;
    }

    public double getHottnessForCard(Card card) {
    	if (hotnessMap == null)
    		return 1;
    	else
    		return hotnessMap.get(card.getRank());
    }

    double getBetMultiplier(double hotness) {    	
      if (hotness < 0)
        return 1;
      else
      {
        return hotness;
      }
    }
}
