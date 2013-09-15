package Engine;

import java.util.HashMap;

public class TestResult {
    public double advantage;
    public HashMap<String, Double> hotnessMap;

    public TestResult(double advantage, HashMap<String, Double> hotnessMap) {
        this.advantage = advantage;
        this.hotnessMap = hotnessMap;
    }
}
