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

    public Simulator(Casino casino) {
        this.casino = casino;
        new Session(casino, new Strategy()).playGames();
    }

    public Simulator(Casino casino, MainWindow ui) {
        this.casino = casino;
        this.ui = ui;
        new Session(casino, new Strategy(), new Shoe(1)).playGames();
    }

    public static void finished(Session session) {
        if (ui != null)
            ui.simulateSession(session);
        else {
            System.out.println("Total Profit: " + session.getTotalProfit());
            System.out.println("Total Wage:" + session.getTotalWage());
            System.out.println("Percent Won:" + session.getGameWonPercentage());
        }
    }
}
