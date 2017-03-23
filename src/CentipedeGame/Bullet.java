package CentipedeGame;

/**
 * 
 * This class defines the basic bullet object bullet is the starting weapon for
 * hero bullet implement the required features for this project
 *
 * @author huangj3. Created Feb 17, 2016.
 */
public class Bullet extends GameObject {
	/**
	 * 
	 * constructor
	 *
	 * @param x
	 * @param y
	 * @param speedX
	 * @param speedY
	 * @param container
	 */
	public Bullet(double x, double y, double speedX, double speedY, Container container) {
		super(x, y, speedX, speedY, container);
		move();
		this.height = 29;
		this.width = 29;
	}

	/**
	 * 
	 * this meathod define the bullet in the game: can remove 1/3 of a mushroom
	 * can kill a piece of centipede/ scorpion / monster
	 * 
	 * @return if this bullet hit a object or not
	 */
	public boolean hitobject() {
		if (this.collideWithPoisonMushroom() != null) {
			Mushroom change = this.collideWithPoisonMushroom();
			change.changeStatus();
			this.remove();
			return true;
		}
		if (this.collideWithMushroom() != null) {
			Mushroom change = this.collideWithMushroom();
			change.changeStatus();
			this.remove();
			return true;
		}
		if (this.collideWithCentipede() != null) {
			Centipede remove = this.collideWithCentipede();
			this.getContainer().addObject(
					new Mushroom(remove.getPosition().getX(), remove.getPosition().getY(), remove.getContainer()));
			remove.remove();
			this.getContainer().getScoreBoard().updateScore(100);
			this.remove();
			return true;
		}

		if (this.collideWithMonster() != null) {
			Monster remove = this.collideWithMonster();
			remove.remove();
			this.getContainer().getScoreBoard().updateScore(300);
			this.remove();
			return true;
		}

		if (this.collideWithFlea() != null) {
			this.collideWithFlea().remove();
			this.remove();
			return true;
		}
		return false;

	}

	@Override
	public void move() {
		if (!this.isInsideTheMap()) {
			this.getContainer().getBullets().remove(this);
		} else if (!this.hitobject()) {
			super.move();
		} else {
			this.getContainer().getBullets().remove(this);
		}

	}

	@Override
	public void remove() {
		this.getContainer().getBullets().remove(this);
	}

}
