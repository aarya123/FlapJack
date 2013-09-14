package Engine;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 4:12 PM
 */
public class Simulator {
    Casino casino;

    public Simulator(Casino casino) {
        this.casino = casino;
        new Session(casino, new Strategy()).playGames();
    }
}
