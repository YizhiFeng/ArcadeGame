
package CentipedeGame;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * 
 * This class draws the image of all the game objects.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
@SuppressWarnings("serial")
public class GameComponent extends JComponent {
	private Container container;

	/**
	 * @param container
	 * @param listener
	 */
	public GameComponent(Container container) {
		super();
		this.container = container;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int k = 0; k < this.container.getMushrooms().size(); k++) {
			if (this.container.getMushrooms().get(k) instanceof Mushroom)
				g2.drawImage(this.container.getMushrooms().get(k).getIcon(),
						(int) this.container.getMushrooms().get(k).getPosition().getX(),
						(int) this.container.getMushrooms().get(k).getPosition().getY(), 30,
						this.container.getMushrooms().get(k).getStatus() * 10, null);
		}
		for (Hero gameObj : this.container.getHeros()) {
			if (gameObj instanceof Hero)
				g2.drawImage(gameObj.getIcon(), (int) gameObj.getPosition().getX(), (int) gameObj.getPosition().getY(),
						30, 30, null);
		}

		for (int i = 0; i < this.container.getNewbullets().size(); i++) {
			g2.drawImage(this.container.getNewbullets().get(0).getIcon(),
					(int) this.container.getNewbullets().get(i).getPosition().getX(),
					(int) this.container.getNewbullets().get(i).getPosition().getY(), 30, 30, null);
		}

		for (int i = 0; i < this.container.getBullets().size(); i++) {

			g2.drawImage(this.container.getBullets().get(i).getIcon(),
					(int) this.container.getBullets().get(i).getPosition().getX(),
					(int) this.container.getBullets().get(i).getPosition().getY(), 30, 30, null);
		}
		for (int i = 0; i < this.container.getCentipedes().size(); i++) {
			g2.drawImage(this.container.getCentipedes().get(i).getIcon(),
					(int) this.container.getCentipedes().get(i).getPosition().getX(),
					(int) this.container.getCentipedes().get(i).getPosition().getY(), 30, 30, null);
		}

		for (int i = 0; i < this.container.getMonsters().size(); i++) {
			g2.drawImage(this.container.getMonsters().get(i).getIcon(),
					(int) this.container.getMonsters().get(i).getPosition().getX(),
					(int) this.container.getMonsters().get(i).getPosition().getY(), 30, 30, null);
		}
		for (int i = 0; i < this.container.getBuffs().size(); i++) {
			g2.drawImage(this.container.getBuffs().get(i).getIcon(),
					(int) this.container.getBuffs().get(i).getPosition().getX(),
					(int) this.container.getBuffs().get(i).getPosition().getY(), 30, 30, null);
		}

		for (int i = 0; i < this.container.getFleas().size(); i++) {
			g2.drawImage(this.container.getFleas().get(i).getIcon(),
					(int) this.container.getFleas().get(i).getPosition().getX(),
					(int) this.container.getFleas().get(i).getPosition().getY(), 30, 30, null);
		}

		for (int i = 0; i < this.container.getSupbullets().size(); i++) {
			g2.drawImage(this.container.getSupbullets().get(i).getIcon(),
					(int) this.container.getSupbullets().get(i).getPosition().getX(),
					(int) this.container.getSupbullets().get(i).getPosition().getY(), 30, 30, null);
		}

		for (int i = 0; i < this.container.getScorpions().size(); i++) {
			g2.drawImage(this.container.getScorpions().get(i).getIcon(),
					(int) this.container.getScorpions().get(i).getPosition().getX(),
					(int) this.container.getScorpions().get(i).getPosition().getY(), 30, 30, null);
		}

		for (int i = 0; i < this.container.getPoisonMushrooms().size(); i++) {
			g2.drawImage(this.container.getPoisonMushrooms().get(i).getIcon(),
					(int) this.container.getPoisonMushrooms().get(i).getPosition().getX(),
					(int) this.container.getPoisonMushrooms().get(i).getPosition().getY(), 30,
					this.container.getPoisonMushrooms().get(i).getStatus() * 10, null);
		}
	}

}
