package CentipedeGame;

/**
 * 
 * This class define the Newbullet object working as the level2 weapon for hero
 * This bullet can move through mushrooms and hit centipedes directly
 *
 * @author huangj3. Created Feb 17, 2016.
 */
public class Newbullet extends GameObject {

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
	public Newbullet(double x, double y, double speedX, double speedY, Container container) {
		super(x, y, speedX, speedY, container);
		move();
		this.height = 29;
		this.width = 29;
	}

	/**
	 * 
	 * check if this newbullet hit any object if hit any object, do
	 * corresponding method
	 *
	 * @return if this newbullet is hit any object in the frame
	 */
	public boolean hitobject() {
		if (this.collideWithPoisonMushroom() != null) {
			Mushroom change = this.collideWithPoisonMushroom();
			change.changeStatus();
			this.remove();
			return true;
		}
		/*
		 * this bullet can move through mushrooms
		 */
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
			this.getContainer().getMonsters().remove(remove);
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
		// all the function before this line will remove this newbullet
		// automatically after it hit any object
	}

	@Override
	public void move() {
		if (!this.isInsideTheMap()) {
			this.getContainer().getNewbullets().remove(this);
		} else if (!this.hitobject()) {
			super.move();
		} else {
			this.getContainer().getNewbullets().remove(this);
		}

	}

	@Override
	public void remove() {
		this.getContainer().getNewbullets().remove(this);
	}

}
