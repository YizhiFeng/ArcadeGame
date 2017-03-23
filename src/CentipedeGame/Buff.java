package CentipedeGame;

import java.awt.geom.Point2D;

/**
 * 
 * This class difine the buff object buff can upgrade hero's weapon buff move
 * from the leftup cornor to the rightbottom corner
 *
 * @author huangj3. Created Feb 17, 2016.
 */
public class Buff extends GameObject {

	public static final double SPEED = 2;

	/**
	 * 
	 * constructor
	 * 
	 * @param x
	 * @param y
	 * @param container
	 */
	public Buff(double x, double y, Container container) {
		super(x, y, SPEED, SPEED, container);
		this.width = 30;
		this.height = 30;

	}

	@Override
	public void move() {
		if (this.collideWithHero() != null) {
			this.collideWithHero().updateWeapon();
			this.remove();
			this.getContainer().getScoreBoard().updateScore(100);
			return;
		}
		// move from the left cornor to the right cornor
		if (this.isInsideTheMap()) {
			movingDiagonally();
			return;
		}
		if (!this.isInsideTheMap()) {
			setback();
		}
	}

	/**
	 * 
	 * move the monster from the left cornor to the right cornor
	 *
	 */
	public void movingDiagonally() {
		this.setPosition(new Point2D.Double(this.getPosition().getX() + 0.5, this.getPosition().getY() + (0.2)));
	}

	/**
	 * 
	 * monster moves backward if it is not inside the map
	 *
	 */
	public void setback() {
		this.setPosition(new Point2D.Double(0, this.getPosition().getY() + (0.2)));
	}

	@Override
	public void remove() {
		this.getContainer().getBuffs().remove(this);
	}

}
