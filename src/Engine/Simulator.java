package Engine;

import UI.MainWindow;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 4:12 PM
 */
public class Simulator {
    static Casino casino;
    static MainWindow ui;
    String strategy_type;

    public Simulator(Casino casino, String strategy_type) {
        this.casino = casino;
        new Session(casino, new Strategy(strategy_type)).playGames();
    }

    public Simulator(Casino casino, MainWindow ui, String strategy_type) {
        this.strategy_type = strategy_type;
        this.casino = casino;
        this.ui = ui;
        new Session(casino, new Strategy(strategy_type)).playGames();
    }

    public static void finished(Session session) {
        if (ui != null)
            ui.simulateSession(session);
        else {
            /*
            System.out.println("Total Profit: $" + session.getTotalProfit());
            System.out.println("Total Wage: $" + session.getTotalWage());
            System.out.println("Percent Won: " + session.getGameWonPercentage() + "%");
            System.out.println("Number of Games: " + session.getCasino().getNumberOfGames());
            */
        }
    }
}
