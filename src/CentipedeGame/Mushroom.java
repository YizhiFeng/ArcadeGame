package CentipedeGame;

import java.awt.geom.Point2D;

/**
 * This class defines mushroom in the game
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class Mushroom extends GameObject {
	private int status;

	public Mushroom(double x, double y, Container container) {
		super(x, y, 0, 0, container);
		this.width = 30;
		this.height = 30;
		this.status = 3;
	}

	public Mushroom(Point2D pos, Container container) {
		super(pos, container);
	}

	/**
	 * 
	 * changes the status when hit, and removes the mushroom when its status is
	 * 0 or less.
	 *
	 */
	public void changeStatus() {
		this.status--;
		if (this.status <= 0) {
			this.getContainer().getScoreBoard().updateScore(30);
			this.remove();
		}
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return this.status;
	}

	@Override
	public void remove() {
		this.getContainer().getMushrooms().remove(this);
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
