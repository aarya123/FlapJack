package Engine;

import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TestStrategies {
	Session session;
	Casino casino;
	
	public TestStrategies() {
		casino = new Casino("Bellagio", 1.5, 6, true, true, true);
        casino.setNumberOfGames(1000);
        
        session = new Session(casino, new Strategy());
	}
	
	public void test( Strategy[] strategies ) {
		Strategy[] best100 = runSimulation(10000, strategies);
		Strategy[] best10 = runSimulation(100, best100);
		// somehow get this down to best 9?
	}
	
	
	/*
	 * Runs simulation for given population size
	 * returns best sqrt(size) strategies
	 */
	private Strategy[] runSimulation( int size, Strategy[] strategies ) {
		int n = (int) Math.sqrt(size);
		Strategy[] best = new Strategy[n];
		
		double[] profitResults = new double[size];
		double[] wageResults = new double[size];
		
		// run strategies and somehow populate these arrays
		// indexes for profitResults and wageResults need to correspond to indexes in strategies
		
		// need to map strategy -> expected value
		
		// calculate strategies index -> expected value percent map with firstEntry = max
		TreeMap<Double, Integer> indexToExpectedValue = getExpectedValues(profitResults, wageResults, size);
		
		for ( int i=0; i<n; i++ ) {
			best[i] = strategies[indexToExpectedValue.pollLastEntry().getValue()];
		}
		
		return best;
		
	}
	
	// maps expectedValue -> strategyIndex
	private TreeMap<Double, Integer> getExpectedValues( double[] profits, double[] wages, int size ) {
		// creates a max tree map (firstEntry = max)
		TreeMap<Double, Integer> map = new TreeMap<Double, Integer>();
		
		for ( int i=0; i<size; i++ ) {
			map.put( expectedValuePercent( profits[i], wages[i]), i );
		}
		return map;
	}
	
	private double expectedValuePercent( double profit, double wage ) {
		return 100 * ( profit / wage );
	}
}