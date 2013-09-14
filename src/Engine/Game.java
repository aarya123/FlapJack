package Engine;

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

    	for (int i = 0; i < cards.size(); i++)
    		sum += playerHand.getValues()[i];

    	//don't forget A, A.

    	if (hasAce) {

    		if (cards.size() == 2 && cards.get(1).toString().equals( cards.get(0).toString()))
    			return Move.P;

    		boolean hashH[] = new boolean[20000];
    		boolean hashD[] = new boolean[20000];
    		boolean hashDS[] = new boolean[20000];
    		boolean hashS[] = new boolean[20000];
    		for (int i = 2; i < 10; i++) {
    			int hashValue = 11000 + i*100;
    			for (int j = 2; j <= 11; j++ ) {
    				if ( (i == 2 || i == 3 && j <= 4) || (i == 2 || i == 3  && j >= 7 && j <= 11) ) 
    					hashH[hashValue+j] = true;
    				if ( (i == 4 || i == 5 && j < 4)  || (i == 4 || i == 5 && j >= 7 && j <= 11) ) 
    					hashH[hashValue+j] = true;
    				if (i == 6 && j == 2 || j == 6 && j >= 7 && j <= 11)
    					hashH[hashValue+j] = true;
    				if (i == 7 && j >= 9 && j <= 11)
    					hashH[hashValue+j] = true;

    				if (i == 7 && j == 2 || i == 7 && j >= 6 && j <= 8)
    					hashS[hashValue+j] = true;
    				if (i == 8 || i == 9)
    					hashS[hashValue+j] = true;

    				if (i == 7 && j >= 3 && j <= 6)
    					hashDS[hashValue+j] = true;

    				if ( (i == 2 && j >= 5 && j <= 6) )
    					hashD[hashValue+j] = true;
    				if ( (i == 3 && j >= 5 && j <= 6) )
    					hashD[hashValue+j] = true;
    				if ( (i == 4 && j >= 4 && j <= 6) )
    					hashD[hashValue+j] = true;
    				if ( (i == 5 && j >= 4 && j <= 6) )
    					hashD[hashValue+j] = true;
    				if ( (i == 6 && j >= 3 && j <= 6) )
    					hashD[hashValue+j] = true;
    			}
    		}

    		int hashValue = 11000 + Integer.parseInt( cards.get(1).toString() )*100 + Integer.parseInt( dealerVisibleHand.getCards().get(0).toString() );
    		if (hashH[hashValue])
    			return Move.H;
    		else if (hashD[hashValue])
    			return Move.D;
    		else if(hashDS[hashValue])
    			return Move.DS;
    		else if(hashS[hashValue])
    			return Move.S;

    	} else if (cards.size() == 2 && cards.get(0).equals( cards.get(1) )) {

    		String val = cards.get(0).toString();
    		if (val.equals("Jack") || val.equals("Queen") || val.equals("King"))
    			return Move.S;

    		int p1 = Integer.parseInt( cards.get(0).toString() );
    		int p2 = Integer.parseInt( dealerVisibleHand.getCards().get(0).toString() );
    		
    		if (p1 <= 3 && p2 <= 7)
    			return Move.P;
    		else if (p1 <= 3)
    			return Move.H;
    		
    		if (p1 == 4 && p2 >=5 && p2 <= 6)
    			return Move.P;
    		else if (p1 == 4)
    			return Move.H;

    		if (p1 == 5 && p2 <= 9)
    			return Move.D;
    		else if (p1 == 5)
    			return Move.H;

    		if (p1 == 6 && p2 <= 6)
    			return Move.P;
    		else if (p1 == 6)
    			return Move.H;

    		if (p1 == 7 && p2 <= 7)
    			return Move.P;
    		else if (p1 == 7)
    			return Move.H;

    		if (p1 == 8)
    			return Move.P;

    		if (p1 == 9 && p2 <= 6 || p1 == 9 && p2 >= 8 && p2 <= 9)
    			return Move.P;
    		else
    			return Move.S;

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
    	ArrayList<Card> playerCards = new ArrayList<Card>();
    	playerCards.add(new Card("A"));
    	playerCards.add(new Card("2"));
    	Hand playerHand = new Hand(playerCards);
    	
    	ArrayList<Card> dCards = new ArrayList<Card>();
    	dCards.add(new Card("4"));
    	Hand dHand = new Hand(dCards);
    	
    	System.out.print(Game.getNextMove(dHand, playerHand));
    	
    }

}
