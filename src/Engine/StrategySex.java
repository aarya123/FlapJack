package Engine;

import java.util.Random;

public class StrategySex {

  public Strategy StrategySex(Strategy s1, double ev1, Strategy s2, double ev2) {
    double[] hotnessMap = new double[10];
    Random r = new Random();
    
    for(int i=0; i<hotnessMap.length; i++) {
    	double random = r.nextDouble();
    	if (random < 0.25) { // s1
    		hotnessMap[i] = s1.getHotnessMap()[i];
    	}
    	else if(random < 0.75) { // hybrid
    		hotnessMap[i] = (s1.getHotnessMap()[i] * ev1 + s2.getHotnessMap()[i] * ev2)/2;
    	}
    	else { // s2
    		hotnessMap[i] = s2.getHotnessMap()[i];
    	}
    }
    
   return new Strategy(hotnessMap);
  }
}
