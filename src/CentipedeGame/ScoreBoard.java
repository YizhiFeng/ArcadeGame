package CentipedeGame;

/**
 * 
 * This class contains the score of the game and update the score if necessary.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class ScoreBoard {
	int score;

	public ScoreBoard() {
		this.score = 0;
	}

	public ScoreBoard(int current) {
		this.score = current;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * 
	 * update the score by the assigned points.
	 *
	 * @param points
	 */
	public void updateScore(int points) {
		if (this.score + points < 0) {
			this.score = 0;
		} else {
			this.score += points;
		}
	}

	/**
	 * 
	 * changes the score to be zero.
	 *
	 */
	public void zero() {
		this.score = 0;
	}
}
