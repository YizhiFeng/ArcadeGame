package CentipedeGame;

/**
 * 
 * This class handle the movement of weapon.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class Weapon {
	private int bulletNum;
	private Container container;
	private Hero hero;

	public Weapon(Hero hero) {
		this.bulletNum = 1;
		this.hero = hero;
		this.container = this.hero.getContainer();
	}

	/**
	 * @return the level of weapon.
	 */
	public int getWeaponlvl() {
		return this.bulletNum;
	}

	/**
	 * 
	 * set the level of weapon.
	 *
	 * @param lvl
	 */
	public void setWeaponlvl(int lvl) {
		this.bulletNum = lvl;
	}

	/**
	 * 
	 * increase the level of weapon by one, up to level four.
	 *
	 */
	public void upgrade() {
		if (this.bulletNum < 4) {
			this.bulletNum++;
		}
	}

	/**
	 * 
	 * fire the weapon at the current level
	 *
	 * @param lvl
	 */
	public void fire(int lvl) {
		if (lvl == 1)
			firelevel1();
		if (lvl == 2)
			firelevel2();
		if (lvl == 3)
			firelevel3();
		if (lvl == 4) {
			firelevel4();
		}
	}

	/**
	 * 
	 * handle the fire function at level 1.
	 *
	 */
	private void firelevel1() {
		this.container.addObject(new Bullet(this.container.getHeros().get(0).getPosition().getX(),
				this.container.getHeros().get(0).getPosition().getY(), 0, -1.5, this.container));
	}

	/**
	 * 
	 * handle the fire function at level 2.
	 *
	 */
	private void firelevel2() {
		this.container.addObject(new Newbullet(this.container.getHeros().get(0).getPosition().getX(),
				this.container.getHeros().get(0).getPosition().getY(), 0, -1.5, this.container));
	}

	/**
	 * 
	 * handle the fire function at level 3.
	 *
	 */
	private void firelevel3() {
		for (int i = 0; i < 2; i++) {
			this.container.addObject(
					new Bullet(this.container.getHeros().get(0).getPosition().getX() - 2 * 30 / 2 + i * 30 + 15,
							this.container.getHeros().get(0).getPosition().getY(), 0, -1.5, this.container));
		}
	}

	/**
	 * 
	 * handle the fire function at level 4.
	 *
	 */
	private void firelevel4() {
		this.container.addObject(new Supbullet(this.container.getHeros().get(0).getPosition().getX(),
				this.container.getHeros().get(0).getPosition().getY(), 0, -1.5, this.container));
	}

}
