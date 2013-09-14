package Engine;
import java.util.ArrayList;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 6:16 PM
 */

enum Move {
	S, DS, H, P, D
}

public class Game {



    public double getWager() {
        return 0;
    }

    public double getProfit() {
        return 0;
    }

    public double getPnl() {
        return 0;
    }	

    public static Move getNextMove(Hand dealerVisibleHand, Hand playerHand) {//ArrayList<Integer>
    	ArrayList<Card>cards = (ArrayList<Card>) playerHand.getCards();
    	boolean hasAce = false;
    	int sum = 0;
    	
    	for (int i = 0; i < cards.size(); i++) 
    		if (cards.get(i).toString().equals("Ace")) {
    			hasAce = true;
    			break;
    		}

    	for (int i = 0; i < playerHand.getValues().length; i++)
    		sum += playerHand.getValues()[i];

    	//don't forget A, A.

    	if (hasAce) {
    		//System.out.println("Ace");
    		if (cards.size() == 2 && cards.get(1).toString().equals( cards.get(0).toString()))
    			return Move.P;
    		
    		int p1 = Integer.parseInt( cards.get(1).toString() );
    		int p2 = Integer.parseInt( dealerVisibleHand.getCards().get(0).toString() );
    		
    		if (p1 == 9 || p1 == 8)
    			return Move.S;
    		else if (p1 == 7) {
    			if (p2 >= 3 && p2 <= 6)
    				return Move.DS;
    			else if (p2 >= 9 && p2 <= 11)
    				return Move.H;
    			else
    				return Move.S;
    		} 
    		
    		if (p1 == 2 || p1 == 3) {
    			//System.out.println("23");
    			if (p2 <= 4 || p2 >= 7)
    				return Move.H;
    			else
    				return Move.D;
    		}
    		
    		if (p1 == 4 || p1 == 5) {
    			if (p2 <= 3 || p2 >= 7)
    				return Move.H;
    			else
    				return Move.D;
    		}
    		
    		if (p1 == 6) {
    			if (p2 == 2 || p2 >= 7)
    				return Move.H;
    			else
    				return Move.D;
    		}
    	} else if (cards.size() == 2 && cards.get(0).toString().equals( cards.get(1).toString() )) {

    		String val = cards.get(0).toString();
    		if (val.equals("Jack") || val.equals("Queen") || val.equals("King"))
    			return Move.S;

    		int p1 = Integer.parseInt( cards.get(0).toString() );
    		int p2 = Integer.parseInt( dealerVisibleHand.getCards().get(0).toString() );
    		
    		if (p1 == 2 || p1 == 3) {
    			if (p2 >= 8)
    				return Move.H;
    			else
    				return Move.P;
    		}
    		if (p1 == 4) {
    			if (p2<=4 || p2 >= 7)
    				return Move.H;
    			else
    				return Move.P;
    		}
    		if (p1 == 5) {
    			if (p2 <= 9)
    				return Move.D;
    			else
    				return Move.H;
    		}
    		if (p1 == 6) {
    			if (p2 <= 6)
    				return Move.P;
    			else
    				return Move.H;
    		}
    		if (p1 == 7) {
    			if (p2 <= 7)
    				return Move.P;
    			else
    				return Move.H;
    		}
    		if (p1 == 8)
    			return Move.P;
    		if (p1 == 9) {
    			if (p2 == 7 || p2 >= 10)
    				return Move.S;
    			else
    				return Move.P;
    		}

    	} else {
    		int p2 = Integer.parseInt( dealerVisibleHand.getCards().get(0).toString() );
    		
    		if (sum == 9 && p2 == 2 || sum == 9 && p2 >= 7 && p2 <= 11)
    			return Move.H;
    		else if (sum == 9)
    			return Move.D;
    		
    		if (sum == 10 && p2 <= 9)
    			return Move.D;
    		else if (sum == 10)
    			return Move.H;
    		
    		if (sum == 11 && p2 <= 10)
    			return Move.D;
    		else if (sum == 11)
    			return Move.H;

    		if (sum == 12 && p2 <= 3 || sum == 12 && p2 >= 7)
    			return Move.H;
    		else if (sum == 12)
    			return Move.S;

    		if (sum >= 13 && sum <= 16 && p2 <= 6)
    			return Move.S;
    		else if (sum >= 13 && sum <= 16 && p2 >= 7)
    			return Move.H;
    		
    		 if (sum == 17)
    		 	return Move.S;
    		 
    		 if (sum == 7 || sum == 8)
     			return Move.H;
    	}
    	return Move.P;
    }

    public static void main(String...args) {
    	
    	ArrayList<Card> pc = new ArrayList<Card>();
    	pc.add(new Card("Q"));
    	pc.add(new Card("Q"));
    	//pc.add(new Card("10"));
    	Hand ph = new Hand(pc);
    	
    	ArrayList<Card> dc = new ArrayList<Card>();
    	dc.add(new Card("5"));
    	Hand dh = new Hand(dc);
    	
    	System.out.println(Game.getNextMove(dh, ph));
    	
    	System.out.println();
    	
    	for (int i = 2; i <= 9; i++) {
    		ArrayList<Card> playerCards = new ArrayList<Card>();
    		playerCards.add(new Card("A"));
    		playerCards.add(new Card(Integer.toString(i)));
    		Hand playerHand = new Hand(playerCards);
    		for (int j = 2; j <= 11; j++) {
    			ArrayList<Card> dCards = new ArrayList<Card>();
    	    	dCards.add(new Card(Integer.toString(j)));
    	    	Hand dHand = new Hand(dCards);
    	    	System.out.print(Game.getNextMove(dHand, playerHand)+" ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    	for (int i = 2; i <= 9; i++) {
    		ArrayList<Card> playerCards = new ArrayList<Card>();
    		playerCards.add(new Card(Integer.toString(i)));
    		playerCards.add(new Card(Integer.toString(i)));
    		Hand playerHand = new Hand(playerCards);
    		for (int j = 2; j <= 11; j++) {
    			ArrayList<Card> dCards = new ArrayList<Card>();
    	    	dCards.add(new Card(Integer.toString(j)));
    	    	Hand dHand = new Hand(dCards);
    	    	System.out.print(Game.getNextMove(dHand, playerHand)+" ");
    		}
    		System.out.println();
    	}
    	
    }

}

