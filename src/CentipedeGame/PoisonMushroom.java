package CentipedeGame;

import java.awt.geom.Point2D;

/**
 * 
 * This class define the subclass of mushroom
 * which is poisonmushroom
 *
 * @author huangj3. Created Feb 17, 2016.
 */
public class PoisonMushroom extends Mushroom {

	public PoisonMushroom(double x, double y, Container container) {
		super(x, y, container);

	}
	/**
	 * 
	 * constructor
	 *
	 * @param pos
	 * @param container
	 */
	public PoisonMushroom(Point2D pos, Container container) {
		super(pos, container);
		this.getContainer().addObject(this);
		this.height = 30;
		this.width = 30;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CentipedeGame.Mushroom#remove()
	 */
	@Override
	public void remove() {
		this.getContainer().getPoisonMushrooms().remove(this);
	}

}
