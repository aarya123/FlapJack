package Engine;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 3:00 PM
 */
public class Card {
    private int value;

    public Card(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        switch (value) {
            case 1:
                return "Ace";
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return value + "";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
        }
        return "";
    }
}
