package CentipedeGame;

/**
 * 
 * This class defines the final weapon which level4 weapon for hero. This bullet
 * can firce all of the objects during its movement This bullet has its own icon
 * 
 * @author huangj3. Created Feb 17, 2016.
 */
public class Supbullet extends GameObject {
	public Supbullet(double x, double y, double speedX, double speedY, Container container) {
		super(x, y, speedX, speedY, container);
		move();
		this.height = 29;
		this.width = 29;
	}

	/**
	 * 
	 * check if this supbullet is hit a object in the game frame do the
	 * corresponding method when supbullet hit a object
	 *
	 * @return if this supbullet is hit a object in the game frame
	 */

	public boolean hitobject() {

		/*
		 * following if culses remove objects that be hit and let the supbullet
		 * keep moving
		 */
		if (this.collideWithPoisonMushroom() != null) {
			Mushroom change = this.collideWithPoisonMushroom();
			change.getContainer().getPoisonMushrooms().remove(change);
			return false;
		}
		if (this.collideWithMushroom() != null) {
			Mushroom change = this.collideWithMushroom();
			change.getContainer().getMushrooms().remove(change);
			return false;
		}
		if (this.collideWithCentipede() != null) {
			Centipede remove = this.collideWithCentipede();
			remove.getContainer().getCentipedes().remove(remove);
			this.getContainer().getScoreBoard().updateScore(100);
			return false;
		}

		if (this.collideWithMonster() != null) {
			Monster remove = this.collideWithMonster();
			this.getContainer().getMonsters().remove(remove);
			this.getContainer().getScoreBoard().updateScore(300);
			return false;
		}

		if (this.collideWithFlea() != null) {
			this.collideWithFlea().remove();
			return false;
		}

		return false;
	}

	/**
	 * move upwards to the upboarder of the game frame remove bullet if
	 * supbullet is out of frame
	 */
	@Override
	public void move() {
		if (!this.isInsideTheMap()) {
			this.remove();

		} else if (!this.hitobject()) {
			super.move();
		} else {
			this.remove();
		}

	}

	/**
	 * remove bullet from its container
	 */
	@Override
	public void remove() {
		this.getContainer().getSupbullets().remove(this);
	}

}
