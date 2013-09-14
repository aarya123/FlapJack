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
        if (rank.equals("A"))
            return new int[]{1, 11};
        else if (rank.equals("J") || rank.equals("Q") || rank.equals("K"))
            return new int[]{10};
        else
            return new int[]{Integer.parseInt(rank)};
    }

    public String toString() {
        if (rank.equals("A"))
            return "Ace";
        else if (rank.equals("J"))
            return "Jack";
        else if (rank.equals("Q"))
            return "Queen";
        else if (rank.equals("K"))
            return "King";
        else
            return rank;
    }
}
