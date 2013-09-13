package Engine;

import java.util.ArrayList;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 3:00 PM
 */
public class Shoe {
    private ArrayList<Card> deck;
    private int numberOfDecks;

    public Shoe(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        initDeck();
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void shuffle() {
        initDeck();
    }

    private void initDeck() {
        deck = new ArrayList<Card>();
        for (int j = 0; j < numberOfDecks * 4; j++)
            for (int i = 1; i < 14; i++) {
                deck.add(new Card(i));
            }
    }

    public ArrayList<Card> getHand(int size) {
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < size; i++)
            hand.add(deck.remove((int) (Math.random() * deck.size())));
        return hand;
    }

    public String toString() {
        String toReturn = "";
        for (Card card : deck)
            toReturn += card.toString() + ",";
        return toReturn;
    }
}
