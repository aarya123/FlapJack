package Engine;

import java.util.ArrayList;

/* Skeleton code for the Game class*/

public class Game {
    Strategy strategy;
    Casino casino;
    Shoe shoe;
    double initialAmountWagered, actualAmountWagered, profit;

    Game(Strategy strategy, Casino casino, Shoe shoe, double initialAmountWagered) {
        this.strategy = strategy;
        this.casino = casino;
        this.shoe = shoe;
        this.initialAmountWagered = initialAmountWagered;
        this.actualAmountWagered = initialAmountWagered;
    }

    public double getActualAmountWagered() {
        return actualAmountWagered;
    }

    double getInitialAmountWagered() {
        return initialAmountWagered;
    }

    double getProfit() {
        return profit;
    }

    /* Returns the dealer's hidden card after the initial deal*/
    Card distributeCards(Hand dealerHand, Hand playerHand) {
        Card hidden;
        hit(playerHand);
        hidden = hit(dealerHand);
        hit(playerHand);
        hit(dealerHand);
        return hidden;
    }

    Card hit(Hand hand) {
        Card card = shoe.removeTopCard();
        hand.addCard(card);
        return card;
    }

    public String won(Hand playerHand, Hand dealerHand) {
        // call playerHand.getValue(), dealerVisibleHand.getValue()
        int playerValue;
        int dealerValue;

        // set best case player hand
        int[] playerValueArray = playerHand.getValues();
        if (playerValueArray.length == 2)
            playerValue = getBetterHand(playerValueArray[0], playerValueArray[1]);
        else
            playerValue = playerValueArray[0];

        // set best case dealer hand
        int[] dealerValueArray = dealerHand.getValues();
        if (dealerValueArray.length == 2)
            dealerValue = getBetterHand(dealerValueArray[0], dealerValueArray[1]);
        else
            dealerValue = dealerValueArray[0];

        if (playerValue > dealerValue)
            return "true";
        else if (playerValue < dealerValue)
            return "false";
        else
            return "tie";
    }

    private void calculateProfit(String won, Hand playerHand, Hand dealerHand) {
        double blackjackMultiplier = casino.getBlackjackMultiplier();
        if (won.equals("true")) {
            if (playerHand.blackjack()) {
                profit = actualAmountWagered * blackjackMultiplier;
            } else {
                profit = actualAmountWagered;
            }
        // dealer won or tie but dealer got blackjack
        } else if (won.equals("false") || dealerHand.blackjack()) {
            profit = (-1) * actualAmountWagered;
        } else {
        	profit = 0; // tie with dealer
        }
    }

    private int getBetterHand(int hand1, int hand2) {
        if (hand1 <= 21 && hand2 <= 21)
            return hand1 > hand2 ? hand1 : hand2;
        else
            return hand1 > hand2 ? hand2 : hand1;
    }

    ArrayList<Card> removeTopNCards(int numberCards) {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < numberCards; i++) {
            cards.add(shoe.removeTopCard());
        }
        return cards;
    }

    void completeDealerHand(Hand dealerHand, Card dealerHiddenCard) {
        while (!reachedN(dealerHand, 17)) {
            hit(dealerHand);
        }
        if (dealerHand.softSeventeen()) {
            hit(dealerHand);
        }
    }

    boolean reachedN(Hand hand, int n) {
        boolean reached = false;
        int[] values = hand.getValues();
        for (int x : values) {
            if (x >= n) {
                reached = true;
            }
        }
        return reached;
    }

    public void play() {
        boolean playing = true;
        String won = "false";
        Move move;
        Hand dealerHand = new Hand(new ArrayList<Card>());
        Hand playerHand = new Hand(new ArrayList<Card>());
        Card dealerHiddenCard;

        dealerHiddenCard = distributeCards(dealerHand, playerHand);
        while (playing) {
            move = strategy.getNextMove();
            if (move == Move.STAND) {
                break;
            }
            playerHand.addCard(shoe.removeTopCard());
        }

        completeDealerHand(dealerHand, dealerHiddenCard);
        won = won(playerHand, dealerHand);
        calculateProfit(won, playerHand, dealerHand);
    }
}
