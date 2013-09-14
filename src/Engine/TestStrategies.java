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
    }
	
	public void test( Strategy[] strategies ) {
		Strategy[] best100 = testNTimes(strategies, 10);
		
		Strategy[] best10 = testNTimes(best100, 10);
		
		
		// somehow get this down to best 9?
	}
	
	// N times, runs simulation for strategies, gets best sqrt(strategies), crossbreeds them and 
	// ultimately returns best sqrt(strategies)
	private Strategy[] testNTimes( Strategy[] strategies, int numTimes ) {
		int n = (int) Math.sqrt(strategies.length);
		
		TreeMap<Double, Integer> results;
		Strategy[] best = new Strategy[n];
		double[] evs = new double[n];
		
		Map.Entry<Double, Integer> entry;
		
		// run X strategies N times
		for ( int i=0; i<numTimes; i++ ) {
			results = runSimulation(strategies);
			// populate best n strategies from results and their corresponding 100 expected values
			for ( int j=0; j<best.length; j++ ) {
				entry = results.pollLastEntry();
				best[j] = strategies[entry.getValue()];
				evs[j] = entry.getKey();
			}
			
			strategies = crossbreed(best, evs);
		}
		return best;
	}
	
	private Strategy[] getBestNStrategies( TreeMap<Double, Integer> results, Strategy[] strategies, int n ) {
		Strategy[] best = new Strategy[n];
		
		for ( int j=0; j<n; j++ ) {
			best[j] = strategies[results.pollLastEntry().getValue()];
		}
		return best;
	}
	
	/*
	 * Runs simulation for given population size
	 */
	private TreeMap<Double, Integer> runSimulation(Strategy[] strategies) {
		int n = (int) Math.sqrt(strategies.length);
		Strategy[] best = new Strategy[n];
		TreeMap<Double, Integer> evToIndexMap = new TreeMap<Double, Integer>();
		
		// run strategies and somehow populate these arrays
		// indexes for profitResults and wageResults need to correspond to indexes in strategies
		
//		// need to map strategy -> expected value
//		
//		// calculate strategies index -> expected value percent map with firstEntry = max
//		TreeMap<Double, Integer> expectedValueToIndex = getExpectedValues(profitResults, wageResults, size);
//		
//		for ( int i=0; i<n; i++ ) {
//			best[i] = strategies[expectedValueToIndex.pollLastEntry().getValue()];
		
		Session[] sessions = new Session[strategies.length];
		for(int i=0; i<sessions.length; i++) {
			sessions[i] = new Session(casino, strategies[i]);
			sessions[i].playGames();
			evToIndexMap.put(sessions[i].getTotalProfit() / sessions[i].getTotalWage(), i); // Save EV
		}
	
		return evToIndexMap;
	}
	
	private Strategy[] crossbreed(Strategy[] strategies, double[] evs) {
		int n = strategies.length;
		int n2 = (int) Math.pow(n, 2);
		Strategy[] children = new Strategy[n2];
		
		for(int i=0; i<n2; i++){
			for(int j=0; j<n2; j++) {
				children[i*n + j] = StrategySex.breed(strategies[i], evs[i], strategies[j], evs[j] );
			}
		}
		
		return children;
	}
}