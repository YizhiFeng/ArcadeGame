package CentipedeGame;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Game Start!");
		
		@SuppressWarnings("unused")
		Game newgame = new Game(0, new ScoreBoard(),true);
	}

}
