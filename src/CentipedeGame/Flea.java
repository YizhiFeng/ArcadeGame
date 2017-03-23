package CentipedeGame;
/**
 * 
 * This class define the flea object
 *
 * @author huangj3.
 *         Created Feb 17, 2016.
 */
public class Flea extends GameObject {
	public static final double SPEED = 1; 
	public static final double RATE = 0.1;
	/**
	 * 
	 * constructor
	 *
	 * @param x
	 * @param container
	 */
	public Flea(double x, Container container) {
		super(x, 0, 0, SPEED, container);
		this.height=30;
		this.width=30;
	}

	@Override
	public void move() {
		super.move();
		if (this.collideWithHero() != null) {
			this.hitHero();
			this.setSpeedY(0);
			return;
		}
		
		if (this.collideWithMushroom()==null&&this.getPosition().getY()%30==0){
			if(Math.random()<=RATE){
				this.getContainer().addObject(new Mushroom(this.getPosition().getX(),this.getPosition().getY(),this.getContainer()));
			}
		}
		if(!this.isInsideTheMap())this.remove();
	}

	@Override
	public void remove() {
		this.setSpeedY(0);
		this.getContainer().getFleas().remove(this);
	}
	
	

}
