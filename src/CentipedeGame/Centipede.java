package CentipedeGame;

/**
 * 
 * This class handle the movement of Centipede and interaction with other
 * objects.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class Centipede extends GameObject {
	private boolean isTurning;
	private double distanceTraveled;
	private double previousXVelocity;
	private final static double SPEED = 1.5;
	private boolean isGoingUp;
	private boolean poisoned;

	public Centipede(double x, double y, Container container) {
		super(x, y, SPEED, 0, container);
		this.height = 30;
		this.width = 30;
		this.isTurning = false;
		this.distanceTraveled = 0;
		this.previousXVelocity = SPEED;
		this.isGoingUp = false;
		this.poisoned = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CentipedeGame.GameObject#move()
	 */
	@Override
	public void move() {
		this.hitHero();
		if (this.collideWithPoisonMushroom() != null || this.poisoned) {
			if (this.getSpeedX() != 0)
				this.previousXVelocity = this.getSpeedX();
			this.poisoned = true;
			this.goingDown();
			return;
		}

		if (this.isTurning) {
			this.changeDirection();
			return;
		}

		if (!this.hitMushroom() && this.isInsideTheMap()) {
			super.move();
			this.isTurning = false;
			return;
		}
		changeDirection();
	}

	@Override
	public void remove() {
		this.getContainer().getCentipedes().remove(this);
	}

	/**
	 * 
	 * let Centipede go down
	 *
	 */
	private void goingDown() {
		this.setSpeedX(0);
		this.setSpeedY(SPEED);
		super.move();
		if (this.getPosition().getY() >= Hero.UPPER_BOUND) {
			this.poisoned = false;
			this.setSpeedY(0);
			this.setSpeedX(this.previousXVelocity);
		}
	}

	/**
	 * 
	 * returns true if Centipede hits mushroom
	 *
	 * @return true if Centipede hits mushroom
	 */
	private boolean hitMushroom() {
		if (this.collideWithMushroom() != null) {
			return true;
		}
		return false;

	}

	/**
	 * 
	 * change the moving direction of Centipede when hitting mushroom
	 *
	 */
	public void changeDirection() {
		this.hitHero();
		if (!this.isTurning) {
			this.setSpeedX(0);
			if (this.isGoingUp && this.getPosition().getY() <= Hero.UPPER_BOUND)
				this.isGoingUp = false;
			if (this.getPosition().getY() >= 510 || this.isGoingUp) {
				this.isGoingUp = true;
				this.setSpeedY(-1 * SPEED);
			} else
				this.setSpeedY(SPEED);
			this.distanceTraveled += SPEED;
			this.isTurning = true;
		} else {
			super.move();
			this.distanceTraveled += SPEED;
			if (this.distanceTraveled == this.height + SPEED) {
				this.isTurning = false;
				this.setSpeedY(0);
				this.distanceTraveled = 0;
				this.setSpeedX(-1 * this.previousXVelocity);
				this.previousXVelocity = -1 * this.previousXVelocity;
			}
		}
	}

}
