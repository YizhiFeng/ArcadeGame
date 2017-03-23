package CentipedeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * This class handles the keyboard listener.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class KeyboardListener implements KeyListener {
	private JFrame frame;
	private Container container;
	private Game game;
	private boolean fPressed;

	/**
	 * @param hero
	 * @param frame
	 */
	public KeyboardListener(JFrame frame, Container container, Game game) {
		this.frame = frame;
		this.container = container;
		this.game = game;
		this.fPressed = false;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();

		// press arrow keys to move the hero
		if (keyCode == KeyEvent.VK_UP) {
			if (this.container.getHeros().size() > 0)
				this.container.getHeros().get(0).setSpeedY(-1);
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			if (this.container.getHeros().size() > 0)
				this.container.getHeros().get(0).setSpeedX(1);
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if (this.container.getHeros().size() > 0)
				this.container.getHeros().get(0).setSpeedY(1);
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			if (this.container.getHeros().size() > 0)
				this.container.getHeros().get(0).setSpeedX(-1);
		}
		if (keyCode == KeyEvent.VK_F && this.container.getHeros().size() != 0 && !this.fPressed) {
			this.container.getHeros().get(0).getWeapon()
					.fire(this.container.getHeros().get(0).getWeapon().getWeaponlvl());
			this.fPressed = true;
		}

		// press U and D to switch game levels
		if (keyCode == KeyEvent.VK_U) {
			this.game.restart(this.game.getMapLevel() + 1);

		}
		if (keyCode == KeyEvent.VK_D) {
			this.game.restart(this.game.getMapLevel() - 1);
		}

		// press P to stop and resume the game
		if (keyCode == KeyEvent.VK_P) {
			if (!this.game.isSTOP())
				this.game.setSTOP(true);
			else
				this.game.setSTOP(false);
		}

		// pressing number keys to switch the level of weapon.
		if (keyCode == KeyEvent.VK_NUMPAD1 && !this.fPressed) {
			this.container.getHeros().get(0).getWeapon().setWeaponlvl(1);
		}

		if (keyCode == KeyEvent.VK_NUMPAD2 && !this.fPressed) {
			this.container.getHeros().get(0).getWeapon().setWeaponlvl(2);
		}

		if (keyCode == KeyEvent.VK_NUMPAD3 && !this.fPressed) {
			this.container.getHeros().get(0).getWeapon().setWeaponlvl(3);
		}

		if (keyCode == KeyEvent.VK_NUMPAD4 && !this.fPressed) {
			this.container.getHeros().get(0).getWeapon().setWeaponlvl(4);
		}
		this.frame.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			if (this.container.getHeros().size() > 0)
				this.container.getHeros().get(0).setSpeedY(0);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (this.container.getHeros().size() > 0)
				this.container.getHeros().get(0).setSpeedX(0);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			if (this.container.getHeros().size() > 0)
				this.container.getHeros().get(0).setSpeedY(0);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if (this.container.getHeros().size() > 0)
				this.container.getHeros().get(0).setSpeedX(0);
		}
		if (arg0.getKeyChar() == 'f')
			this.fPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
