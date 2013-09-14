// Session.java

public class Session {
	
	public Casino casino;
	public Player player;
	public Shoe shoe;
	final int numberOfGames = 5000;
	public games[numberOfGames];

	public Session(Casin casino, Player player, Shoe shoe) {
		this.casino = casino;
		this.player = player;
		this.shoe = shoe;

		games = new games[numberOfGames];
	}

	static game simulate() {
		for (int i = 0; i < numberOfGames; i++) {
			games[i]
		}
	}

	Casino getCasino() {
		return casino;
	}

	Player getPlayer() {
		return player;
	}
	Shoe getShoe() {
		return shoe;
	}

	void setCasino(Casino casino) {
		this.casino = casino;
	}
	void setPlayer(Player player) {
		this.player = player;
	}	
	void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	public static void main(String args[]) {

	} 
}