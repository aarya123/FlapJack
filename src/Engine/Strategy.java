package Engine;

import java.util.HashMap;

public class Strategy {

    static public HashMap<String, Integer> hotnessMap;

    public Strategy() {
        hotnessMap = new HashMap<String, Integer>();
        for (int i = 1; i < 14; i++) {
            switch (i) {
                case 2:
                    hotnessMap.put(i + "", 1);
                    break;
                case 3:
                    hotnessMap.put(i + "", 1);
                    break;
                case 4:
                    hotnessMap.put(i + "", 2);
                    break;
                case 5:
                    hotnessMap.put(i + "", 2);
                    break;
                case 6:
                    hotnessMap.put(i + "", 1);
                    break;
                case 7:
                    hotnessMap.put(i + "", 1);
                    break;
                case 8:
                    hotnessMap.put(i + "", 0);
                    break;
                case 9:
                    hotnessMap.put(i + "", 0);
                    break;
                case 10:
                    hotnessMap.put(i + "", -2);
                    break;
                case 11:
                    hotnessMap.put("J", -2);
                    break;
                case 12:
                    hotnessMap.put("Q", -2);
                    break;
                case 13:
                    hotnessMap.put("K", -2);
                    break;
                case 1:
                    hotnessMap.put("A", 0);
                    break;
            }
        }
    }

    double getHottnessForCard(Card card) {
        return hotnessMap.get(card.getRank());
    }

    double getBetMultiplier(double hotness) {
        if (hotness < 25)
            return 1;
        else if (hotness < 30)
            return 10;
        else
            return 15;
    }

    public void print(double profit) {
        System.out.println(profit);
        for (int i = 0; i < hotnessMap.keySet().size(); i++)
            System.out.println(hotnessMap.keySet().toArray()[i] + ":" + hotnessMap.get(hotnessMap.keySet().toArray()[i]));
    }
}
