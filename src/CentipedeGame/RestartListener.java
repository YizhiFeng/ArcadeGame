package CentipedeGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a action listener that is used by the restart button in game.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class RestartListener implements ActionListener {
	private Game game;

	public RestartListener(Game game) {
		this.game = game;
	}

	@Override
	/**
	 * restart a new game when the restart button is triggered.
	 */
	public void actionPerformed(ActionEvent e) {
		this.game.getMyframe().dispose();
		this.game = new Game(0, new ScoreBoard(),false);
		this.game.getRestart().setEnabled(false);
	}

}
