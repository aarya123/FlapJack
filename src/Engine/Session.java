package Engine;

public class Session {

    Casino casino;
    Player player;
    Shoe shoe;
    Game[] games;
    final int numberOfGames = 5000;

    double totalProfit;
    double finalPnl;
    double totalWage;
    double averageProfit;
    double gameWonPercentage;

    int numberOfWonGame;


    public Session(Casino casino, Player player, Shoe shoe) {
        this.casino = casino;
        this.player = player;
        this.shoe = shoe;

        totalProfit = 0.0;
        finalPnl = 0.0;
        totalWage = 0.0;
        averageProfit = 0.0;
        gameWonPercentage = 0.0;
        numberOfWonGame = 0;

        games = new Game[numberOfGames];
        solve();
    }

    public Game simulate() {
        for (int i = 0; i < numberOfGames; i++) {
            //
        }
        return null;
    }

    public Casino getCasino() {
        return casino;
    }

    public Player getPlayer() {
        return player;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setCasino(Casino casino) {
        this.casino = casino;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    private void solve() {
        for (int i = 0; i < numberOfGames; i++) {
            finalPnl += games[i].getPnl(); //???
            totalProfit += games[i].getProfit(); //??
            totalWage += games[i].getWager();  //???
            numberOfWonGame += totalProfit > 0 ? 1 : 0;
        }
    }

    public double getAverageProfit() {
        return finalPnl / totalWage;
    }

    public double getFinalPnl() {
        return finalPnl;
    }

    public double getTotalWage() {
        return totalWage;
    }

    public double getTotalProfit() {
        return totalProfit;
    }


    public double getGameWonPercentage() {
        return numberOfWonGame * 100.0 / numberOfGames;
    }
}