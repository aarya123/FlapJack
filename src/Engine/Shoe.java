package Engine;

import java.util.ArrayList;
import java.util.Collections;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 3:00 PM
 */
public class Shoe {
    private ArrayList<Card> deck;
    private int numberOfDecks;
    private int hottness;
    private Strategy strategy;

    public Shoe(int numberOfDecks, Strategy strategy) {
        this.numberOfDecks = numberOfDecks;
        this.strategy = strategy;
        shuffle();
    }
    
    public int size() {
    	return deck.size();
    }

    public int getHottness() {
      return hottness;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void shuffle() {
        initDeck();
        Collections.shuffle(deck);
        hottness = 0;
    }

    private void initDeck() {
        deck = new ArrayList<Card>();
        for (int j = 0; j < numberOfDecks * 4; j++) {
            for (int i = 1; i < 14; i++) {
                if (i == 1)
                    deck.add(new Card("A"));
                else if (i == 11)
                    deck.add(new Card("J"));
                else if (i == 12)
                    deck.add(new Card("Q"));
                else if (i == 13)
                    deck.add(new Card("K"));
                else
                    deck.add(new Card(Integer.toString(i)));
            }
        }
    }

    public ArrayList<Card> getHand(int size) {
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < size; i++)
            hand.add(deck.remove(0));
        return hand;
    }

    public String toString() {
        String toReturn = "";
        for (Card card : deck)
            toReturn += card.toString() + ",";
        return toReturn;
    }

    public Card removeTopCard() {
        if (deck.size() == 1)
            shuffle();
        Card removedCard = deck.remove(deck.size()-1);
        hottness += strategy.getHottnessForCard(removedCard);
        return removedCard;
    }
}