package CentipedeGame;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is the super class of all the game objects.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public abstract class GameObject {
	private Point2D position;
	private double speedX;
	private double speedY;
	protected double width;
	protected double height;
	private BufferedImage icon;
	private Container container;

	/**
	 * @param position
	 * @param speedX
	 * @param speedY
	 */
	public GameObject(double x, double y, double speedX, double speedY, Container container) {
		this.position = new Point2D.Double(x, y);
		this.speedX = speedX;
		this.speedY = speedY;
		this.importIcon();
		this.container = container;
	}

	public GameObject(Point2D pos, Container container) {
		this.position = pos;
		this.container = container;
		this.importIcon();
	}

	/**
	 * @return the container
	 */
	public Container getContainer() {
		return this.container;
	}

	public void move() {
		this.position = new Point2D.Double(this.position.getX() + this.speedX, this.position.getY() + this.speedY);
	}

	/**
	 * @return the position
	 */
	public Point2D getPosition() {
		return this.position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Point2D position) {
		this.position = position;
	}

	/**
	 * @return the speedX
	 */
	public double getSpeedX() {
		return this.speedX;
	}

	/**
	 * @param speedX
	 *            the speedX to set
	 */
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	/**
	 * @return the speedY
	 */
	public double getSpeedY() {
		return this.speedY;
	}

	/**
	 * @param speedY
	 *            the speedY to set
	 */
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public abstract void remove();

	/**
	 * 
	 * returns true if the edges of a object collide with other objects.
	 *
	 * @param x
	 * @param y
	 * @return true if the edges of a object collide with other objects.
	 */
	private boolean withinRange(double x, double y) {
		if (x > this.position.getX() && x < this.position.getX() + this.width && y > this.position.getY()
				&& y < this.position.getY() + this.height)
			return true;
		return false;

	}

	/**
	 * 
	 * The following functions return the specific type of the collided game
	 * object if a game object collides with another object.
	 *
	 * @return the specific type of the collided game object if a game object
	 *         collides with another object
	 */
	public synchronized PoisonMushroom collideWithPoisonMushroom() {
		for (PoisonMushroom mushroom : this.container.getPoisonMushrooms()) {
			if (collide(mushroom))
				return mushroom;
		}
		return null;
	}

	public synchronized Mushroom collideWithMushroom() {
		for (int i = 0; i < this.container.getMushrooms().size(); i++) {
			if (collide(this.container.getMushrooms().get(i)))
				return this.container.getMushrooms().get(i);
		}
		return null;
	}

	public synchronized Centipede collideWithCentipede() {
		for (Centipede centipede : this.container.getCentipedes()) {
			if (collide(centipede))
				return centipede;
		}
		return null;
	}

	public synchronized Hero collideWithHero() {
		for (Hero hero : this.container.getHeros()) {
			if (collide(hero))
				return hero;
		}
		return null;
	}

	public synchronized Monster collideWithMonster() {
		for (Monster monster : this.container.getMonsters()) {
			if (collide(monster))
				return monster;
		}
		return null;
	}

	public synchronized Flea collideWithFlea() {
		for (Flea flea : this.container.getFleas()) {
			if (this.collide(flea))
				return flea;
		}
		return null;
	}

	public synchronized void hitHero() {
		if (this.collideWithHero() != null) {
			this.collideWithHero().die();
			this.container.getScoreBoard().updateScore(-300);
		}
	}

	/**
	 * 
	 * This function handle the collision between two objects. It returns true
	 * if a object collides with another.
	 *
	 * @param object
	 * @return true if a object collides with another
	 */
	private synchronized boolean collide(GameObject object) {
		if (this.getSpeedY() < 0) {
			if (object.withinRange(this.getPosition().getX() + 1, this.getPosition().getY() + 1)
					|| object.withinRange(this.getPosition().getX() + this.width - 1, this.getPosition().getY() + 1))
				return true;
		}
		if (this.getSpeedY() > 0) {
			if (object.withinRange(this.getPosition().getX() + 1, this.getPosition().getY() + this.height - 1)
					|| object.withinRange(this.getPosition().getX() + this.width - 1,
							this.getPosition().getY() + this.height - 1))
				return true;
		}
		if (this.getSpeedX() < 0) {
			if (object.withinRange(this.getPosition().getX() + 1, this.getPosition().getY() + 1)
					|| object.withinRange(this.getPosition().getX() + 1, this.getPosition().getY() + this.height - 1))
				return true;
		}
		if (this.getSpeedX() > 0) {
			if (object.withinRange(this.getPosition().getX() + this.width - 1, this.getPosition().getY() + 1)
					|| object.withinRange(this.getPosition().getX() + this.width - 1,
							this.getPosition().getY() + this.height - 1))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * returns true if the object is inside the map.
	 *
	 * @return true if the object is inside the map
	 */
	public boolean isInsideTheMap() {
		if (this.getPosition().getX() + this.getSpeedX() >= 0
				&& (this.getPosition().getX() + this.getSpeedX() + this.width <= 620)) {
			if (this.getPosition().getY() + this.getSpeedY() >= 0
					&& (this.getPosition().getY() + this.getSpeedY() + this.height <= 550)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the icon
	 */
	public BufferedImage getIcon() {
		return this.icon;
	}

	/**
	 * 
	 * import the icon for the object
	 *
	 */
	private void importIcon() {
		File file = new File("src/CentipedeGame/" + this.getClass().getName().toLowerCase().substring(14) + ".png");
		try {
			this.icon = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}