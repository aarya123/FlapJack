package Engine;

public class Session {

    Casino casino;
    Strategy strategy;
    Shoe shoe;
    Game[] games;
    double totalProfit;
    double totalWage;
    double averageProfit;
    double gameWonPercentage;
    int numberOfWonGame;
    double[] cumProfit;

    public Session(Casino casino, Strategy strategy) {
        this.casino = casino;
        //this.strategy = new Strategy();
        this.strategy = strategy; // added so we pass in strategy we want
        this.shoe = new Shoe(casino.getNumberOfDecks(), strategy);

        totalProfit = 0.0;
        totalWage = 0.0;
        averageProfit = 0.0;
        gameWonPercentage = 0.0;
        numberOfWonGame = 0;
        cumProfit = new double[casino.getNumberOfGames()];
        games = new Game[casino.getNumberOfGames()];
    }

    public void playGames() {
        int shoeMax = shoe.size(); // full shoe size
        double standardBet = 10;

        for (int i = 0; i < casino.getNumberOfGames(); i++) {
            double betMultiplier = strategy.getBetMultiplier(shoe.getHotness());
            double bet = standardBet * betMultiplier;

            games[i] = new Game(strategy, casino, shoe, bet);
            games[i].play();

            // shuffle deck when less than 25% remaining
            if ((double) shoe.size() / (double) shoeMax <= .25) {
                shoe.shuffle();
            }
        }
        solve();
    }

    public Casino getCasino() {
        return casino;
    }

    public void setCasino(Casino casino) {
        this.casino = casino;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    private void solve() {
        for (int i = 0; i < casino.getNumberOfGames(); i++) {
            totalProfit += games[i].getProfit();
            totalWage += games[i].getActualAmountWagered();
            cumProfit[i] = totalProfit;
            numberOfWonGame += ((games[i].getProfit() > 0) ? 1 : 0);
        }
        Simulator.finished(this);
    }

    public double getTotalWage() {
        return totalWage;
    }

    public double[] getCumProfit() {
        return cumProfit;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public double getGameWonPercentage() {
        return numberOfWonGame * 100.0 / casino.getNumberOfGames();
    }
}