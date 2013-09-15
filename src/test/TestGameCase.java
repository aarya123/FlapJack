package test;

import Engine.Card;
import Engine.Shoe;
import Engine.Strategy;
import Engine.testStrategy;

import java.util.ArrayList;
import java.util.Collections;

public class TestGameCase {
    Shoe determShoe;
    ArrayList<String> moves;
    Strategy strategy;
    double initialWager;
    double expectedProfit;

    public TestGameCase(int[] determShoeArray, String moves, double initialWager, double expectedProfit) {
        strategy = new testStrategy(getMoves(moves));
        determShoe = new Shoe(strategy);
        determShoe.setDeck(deckFromStringArray(determShoeArray));
        this.moves = getMoves(moves);
        this.initialWager = initialWager;
        this.expectedProfit = expectedProfit;
    }

    public Shoe getDetermShoe() {
        return determShoe;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public double getInitialWager() {
        return initialWager;
    }

    public double getExpectedProfit() {
        return expectedProfit;
    }

    public ArrayList<String> getMoves(String moves) {
        ArrayList<String> movesStr = new ArrayList<String>();
        char c;
        for (int i = 0; i < moves.length(); i++) {
            c = moves.charAt(i);
            movesStr.add("" + c);
        }
        return movesStr;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

    private ArrayList<Card> deckFromStringArray(int[] cards) {
        ArrayList<Card> deck = new ArrayList<Card>();
        String cardString = "0";
        for (int x : cards) {
            switch (x) {
                case 1:
                    cardString = "A";
                    break;
                case 11:
                case 12:
                case 13:
                    cardString = "J";
                    break;
                default:
                    cardString = "" + x;
            }
            deck.add(new Card(cardString));
        }
        Collections.reverse(deck);
        return deck;
    }
}
