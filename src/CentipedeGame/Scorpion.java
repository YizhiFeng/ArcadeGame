package CentipedeGame;

/**
 * This class defines Scorpion.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class Scorpion extends GameObject {
	public static final double SPEED = 1;

	public Scorpion(double y, Container container) {
		super(0, y, SPEED, 0, container);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CentipedeGame.GameObject#move()
	 */
	@Override
	public void move() {
		super.move();

		if (this.collideWithMushroom() != null) {
			Mushroom mushroom = this.collideWithMushroom();
			mushroom.remove();
			Mushroom newMushroom = new PoisonMushroom(mushroom.getPosition(), this.getContainer());
			newMushroom.setStatus(mushroom.getStatus());
		}
		if (!this.isInsideTheMap())
			this.remove();
	}

	@Override
	public void remove() {
		this.getContainer().getScorpions().remove(this);
	}

}
