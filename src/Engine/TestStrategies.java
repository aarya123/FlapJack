//package Engine;
//
//import java.util.Collections;
//import java.util.Map;
//import java.util.PriorityQueue;
//import java.util.TreeMap;
//
//public class TestStrategies {
//	Session session;
//	Casino casino;
//	
//	public TestStrategies() {
//		casino = new Casino("Bellagio", 1.5, 6, true, true, true);
//        casino.setNumberOfGames(1000);
//    }
//	
//	public void test( Strategy[] strategies ) {
//		TreeMap<Double, Integer> results = runSimulation(strategies);
//		
//		Strategy[] next10000 = crossbreed(best100);
//		
//		
//		Strategy[] best10 = runSimulation(best100);
//		// somehow get this down to best 9?
//	}
//	
//	
//	/*
//	 * Runs simulation for given population size
//	 */
//	private TreeMap<Double, Integer> runSimulation(Strategy[] strategies) {
//		int n = (int) Math.sqrt(strategies.length);
//		Strategy[] best = new Strategy[n];
//		TreeMap<Double, Integer> evToIndexMap = new TreeMap<Double, Integer>();
//		
//		// run strategies and somehow populate these arrays
//		// indexes for profitResults and wageResults need to correspond to indexes in strategies
//		Session[] sessions = new Session[strategies.length];
//		for(int i=0; i<sessions.length; i++) {
//			sessions[i] = new Session(casino, strategies[i]);
//			sessions[i].playGames();
//			evToIndexMap.put(sessions[i].getTotalProfit() / sessions[i].getTotalWage(), i); // Save EV
//		}
//	
//		return evToIndexMap;
//	}
//	
//	private StrategyEvPair[] getTopNStrategyEvPairs(int n, TreeMap<Double, Integer> evToIndexMap) {
//		StrategyEvPair[] strategyEvPairs= new StrategyEvPair[n];
//		for(int i=0; i<n; i++){
//			last = evToIndexMap.pollLastEntry();
//			strategyEvPairs[i] = new StrategyEvPair(strategies[last.getValue()], last.getKey());
//		}
//	}
//	
//	private Strategy[] crossbreed(Strategy[] strategies, double[] evs) {
//		int n = strategies.length;
//		int n2 = Math.pow(n, 2);
//		Strategy[] children = new Strategy[n2];
//		
//		for(int i=0; i<n2; i++){
//			for(int j=0; j<n2; j++) {
//				children(i*n + j) = StrategySex.breed(strategies[i], strategies[j])
//			}
//		}
//	}
//}
//
//public class StrategyEvPair {
//	public Strategy strategy;
//	public double ev;
//	
//	public StrategyEvPair(strategy, ev){
//		strategy = strategy;
//		ev = ev;
//	}
//
//}