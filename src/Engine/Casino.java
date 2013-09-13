package Engine;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 3:21 PM
 */
public class Casino {

    private int numberOfDecks;
    private boolean hitOnSoft17s, doubleAfterSplit, surrenderOption, resplitAfterAce;
    private String name;

    public Casino(String name, int numberOfDecks, boolean hitOnSoft17s, boolean doubleAfterSplit, boolean surrenderOption, boolean resplitAfterAce) {
        this.name = name;
        this.numberOfDecks = numberOfDecks;
        this.hitOnSoft17s = hitOnSoft17s;
        this.doubleAfterSplit = doubleAfterSplit;
        this.surrenderOption = surrenderOption;
        this.resplitAfterAce = resplitAfterAce;
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

    public boolean isSurrenderOption() {
        return surrenderOption;
    }

    public boolean isResplitAfterAce() {
        return resplitAfterAce;
    }
}
