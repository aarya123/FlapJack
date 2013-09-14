package Engine;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 3:21 PM
 */
public class Casino {
    boolean hitOnSoftSeventeen;
    private double blackjackPayoutMultiple;
    private int numberOfDecks, numberOfGames;
    private boolean hitOnSoft17s, doubleAfterSplit, resplitAfterAce;
    private String name;

    public Casino(String name, double blackjackPayoutMultiple, int numberOfDecks, boolean hitOnSoft17s, boolean doubleAfterSplit, boolean resplitAfterAce) {
        this.name = name;
        this.numberOfDecks = numberOfDecks;
        this.hitOnSoft17s = hitOnSoft17s;
        this.doubleAfterSplit = doubleAfterSplit;
        this.resplitAfterAce = resplitAfterAce;
        this.blackjackPayoutMultiple = blackjackPayoutMultiple;
    }

    public boolean getHitOnSoftSeventeen() {
        return hitOnSoftSeventeen;
    }

    public double getBlackjackMultiplier() {
        return blackjackPayoutMultiple;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public boolean isHitOnSoft17s() {
        return hitOnSoft17s;
    }

    public boolean isDoubleAfterSplit() {
        return doubleAfterSplit;
    }

    public boolean isResplitAfterAce() {
        return resplitAfterAce;
    }

    public double getBlackjackPayoutMultiple() {
        return blackjackPayoutMultiple;
    }
}
