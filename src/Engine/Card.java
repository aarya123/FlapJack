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
    	int[] value = {10};
    	switch (rank) {
    		case "A":
    			int[] ace = {1, 11};
    			return ace;
    		case "J":
    			return value;
    		case "Q":
    			return value;
    		case "K":
    			return value;
    		default:
    			value[0] = Integer.parseInt(rank);
    			return value;
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
