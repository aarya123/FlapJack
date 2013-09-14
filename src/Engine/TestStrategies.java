package Engine;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TestStrategies {
	Session session;
	Casino casino;
	
	public TestStrategies() {
		casino = new Casino("Bellagio", 1.5, 6, true, true, true);
        casino.setNumberOfGames(100);
    }
	
	public void test() {
		int start = 3000;
		Strategy[] strategies = new Strategy[start];		
		
		for(int i=0; i<start; i++)
			strategies[i] = new Strategy();
	
		TreeMap<Double, Integer> results = runSimulation(strategies);
		
		Strategy[] top100Strategies = getBestNStrategies(results, strategies, 100);	
		
		StrategyEvPair[] best10pairs = testNTimes(top100Strategies, 3);
		
		for(int i=0; i<10; i++) {
			System.out.println("EV: " + best10pairs[i].ev);
		}
		
		System.out.println("Hotness map: " + Arrays.toString(best10pairs[0].strategy.getHotnessMap()));
	}
	
	// N times, runs simulation for strategies, gets best sqrt(strategies), crossbreeds them and 
	// ultimately returns best sqrt(strategies)
	private StrategyEvPair[] testNTimes( Strategy[] strategies, int numTimes ) {
		int n = (int) Math.sqrt(strategies.length);
		
		TreeMap<Double, Integer> results;
		StrategyEvPair[] bestPairs = new StrategyEvPair[n];
		Strategy[] bestStrategies = new Strategy[n];
		double[] bestEvs = new double[n];
		
		Map.Entry<Double, Integer> entry;
		
		// run X strategies N times
		for ( int i=0; i<numTimes; i++ ) {
			results = runSimulation(strategies);
			// populate best n strategies from results and their corresponding 100 expected values
			for ( int j=0; j<bestPairs.length; j++ ) {
				entry = results.pollLastEntry();
				bestStrategies[j] = strategies[entry.getValue()];
				//System.out.println("Strategy: " + Arrays.toString(bestStrategies[j].getHotnessMap()));
				bestEvs[j] = entry.getKey();
				bestPairs[j] = new StrategyEvPair(bestStrategies[j], bestEvs[j]);
			}
			
			strategies = crossbreed(bestStrategies, bestEvs);
		}
		return bestPairs;
	}
	
	private Strategy[] getBestNStrategies( TreeMap<Double, Integer> results, Strategy[] strategies, int n) {
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
		
		Session[] sessions = new Session[strategies.length];
		for(int i=0; i<sessions.length; i++) {
			sessions[i] = new Session(casino, strategies[i]);
			sessions[i].playGames();
			double ev = sessions[i].getTotalProfit() / sessions[i].getTotalWage();
			evToIndexMap.put(ev, i); // Save EV
		}
	
		return evToIndexMap;
	}
	
	private Strategy[] crossbreed(Strategy[] strategies, double[] evs) {
		int n = strategies.length;
		int n2 = (int) Math.pow(n, 2);
		Strategy[] children = new Strategy[n2];
		
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++) {
				children[i*n + j] = StrategySex.breed(strategies[i], evs[i], strategies[j], evs[j] );
			}
		}
		
		return children;
	}
}
