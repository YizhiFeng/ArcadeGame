package CentipedeGame;

/**
 * 
 * This class difined the fileds and functions of hero object. hero is the the
 * object that can manvipulate by users and fire bullets
 *
 * @author huangj3. Created Feb 17, 2016.
 */
public class Hero extends GameObject {
	private int lives;
	private Weapon weapon;
	private Container container;
	public static final double UPPER_BOUND = 360;

	public Hero(double x, double y, double speedX, double speedY, Container container) {
		super(x, y, speedX, speedY, container);
		this.weapon = new Weapon(this);
		this.container = container;
		this.width = 30;
		this.height = 30;
		this.lives = 3;
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return this.lives;
	}

	/**
	 * @param lives
	 *            the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * 
	 * decreace the numberof hero's lives by one
	 */
	public void die() {
		this.lives--;

	}

	/**
	 * 
	 * updateweapon level
	 *
	 */
	public void updateWeapon() {
		this.weapon.upgrade();
	}

	/**
	 * 
	 * return the level of current weapon
	 *
	 * @return the firenumber of current weapon
	 */
	public Weapon getWeapon() {
		return this.weapon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CentipedeGame.GameObject#move()
	 */
	@Override
	public void move() {
		if (this.collideWithPoisonMushroom() == null && this.collideWithMushroom() == null && isInsideTheMap()
				&& this.inPlayerArea()) {
			super.move();
		}
	}

	/**
	 * 
	 * check if the hero is in the player area limit the hero in the bottom part
	 * of the game frame
	 *
	 * @return ture if hero is in the player area false if hero is not in the
	 *         player area
	 */
	private boolean inPlayerArea() {
		return !(this.getSpeedY() < 0 && this.getPosition().getY() - this.getSpeedY() < Hero.UPPER_BOUND);
	}


	/**
	 * remove hero from container
	 */
	public void remove() {
		this.container.getHeros().remove(this);
	}

}
