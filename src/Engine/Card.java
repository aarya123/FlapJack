package Engine;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 3:00 PM
 */
public class Card {
    private String rank;

    public Card(String rank) {
    	this.rank = rank;
    }

    public int[] getValues() {
    	switch (rank) {
    		case "A":
    			return new int[] { 1, 11 };
    		case "J":
    			return new int[] { 10 };
    		case "Q":
    			return new int[] { 10 };
    		case "K":
    			return new int[] { 10 };
    		default:
    			return new int[] { Integer.parseInt(rank) };
    	}
    }

    public String toString() {
        switch (rank) {
            case "A":
                return "Ace";
            case "J":
                return "Jack";
            case "Q":
                return "Queen";
            case "K":
                return "King";
            default:
            	return rank;
        }
    }
}
