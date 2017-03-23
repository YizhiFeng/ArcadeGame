package CentipedeGame;

import java.awt.geom.Point2D;

/**
 * 
 * This class define the functions for monster object which move randomly in the
 * game frame
 *
 * @author huangj3. Created Feb 17, 2016.
 */
public class Monster extends GameObject {

	public static final double SPEED = 2;

	/**
	 * 
	 * constructor
	 *
	 * @param x
	 * @param y
	 * @param container
	 */
	public Monster(double x, double y, Container container) {
		super(x, y, SPEED, SPEED, container);
		this.width = 30;
		this.height = 30;
	}

	/**
	 * let this monster move randomly
	 */
	@Override
	public void move() {
		if (this.collideWithHero() != null) {
			this.hitHero();
			return;
		}
		this.runRandom();
		return;
	}

	/**
	 * 
	 * handle the zig-zag movement style for the monster.
	 *
	 */
	public void runRandom() {
		double newVelocity = Math.random() * 15 * (Math.random() - 0.5);
		this.setPosition(
				new Point2D.Double(this.getPosition().getX() + newVelocity, this.getPosition().getY() + newVelocity));
	}

	/*
	 * remove this monster out of container
	 */
	@Override
	public void remove() {
		this.getContainer().getMonsters().remove(this);
	}

}
