package CentipedeGame;

import java.util.ArrayList;

/**
 * 
 * This class is a container that contains all the objects in the game.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class Container {
	private ScoreBoard scoreBoard;
	private volatile ArrayList<Centipede> centipedes;
	private volatile ArrayList<Mushroom> mushrooms;
	private volatile ArrayList<Hero> heros;
	private volatile ArrayList<Bullet> bullets;
	private volatile ArrayList<Monster> monsters;
	private volatile ArrayList<Buff> buffs;
	private volatile ArrayList<PoisonMushroom> poisonMushrooms;
	private volatile ArrayList<Flea> fleas;
	private volatile ArrayList<Scorpion> scorpions;
	private volatile ArrayList<Newbullet> newbullets;
	private volatile ArrayList<Supbullet> supbullets;

	public Container(ScoreBoard scoreBoard) {
		this.scoreBoard = scoreBoard;
		this.fleas = new ArrayList<Flea>();
		this.scorpions = new ArrayList<Scorpion>();
		this.centipedes = new ArrayList<Centipede>();
		this.mushrooms = new ArrayList<Mushroom>();
		this.heros = new ArrayList<Hero>();
		this.bullets = new ArrayList<Bullet>();
		this.monsters = new ArrayList<Monster>();
		this.poisonMushrooms = new ArrayList<PoisonMushroom>();
		this.buffs = new ArrayList<Buff>();
		this.newbullets = new ArrayList<Newbullet>();
		this.supbullets = new ArrayList<Supbullet>();
	}

	/**
	 * 
	 * add game objects to the map.
	 *
	 * @param object
	 */
	public synchronized void addObject(GameObject object) {
		Game.lock.lock();
		if (object instanceof PoisonMushroom)
			this.poisonMushrooms.add((PoisonMushroom) object);
		else if (object instanceof Mushroom)
			this.mushrooms.add((Mushroom) object);
		else if (object instanceof Centipede)
			this.centipedes.add((Centipede) object);
		else if (object instanceof Hero)
			this.heros.add((Hero) object);
		else if (object instanceof Supbullet)
			this.supbullets.add((Supbullet) object);
		else if (object instanceof Newbullet)
			this.newbullets.add((Newbullet) object);
		else if (object instanceof Bullet)
			this.bullets.add((Bullet) object);
		else if (object instanceof Monster)
			this.monsters.add((Monster) object);
		else if (object instanceof Buff)
			this.buffs.add((Buff) object);
		else if (object instanceof Flea)
			this.fleas.add((Flea) object);
		else if (object instanceof Scorpion)
			this.scorpions.add((Scorpion) object);
		Game.lock.unlock();
	}

	/**
	 * @return the scoreBoard
	 */
	public synchronized ScoreBoard getScoreBoard() {
		return this.scoreBoard;
	}

	/**
	 * @return the scorpions
	 */
	public synchronized ArrayList<Scorpion> getScorpions() {
		return this.scorpions;
	}

	/**
	 * @return the fleas
	 */
	public synchronized ArrayList<Flea> getFleas() {
		return this.fleas;
	}

	/**
	 * @return the centipedes
	 */
	public synchronized ArrayList<Centipede> getCentipedes() {
		return this.centipedes;
	}

	/**
	 * @return the mushrooms
	 */
	public synchronized ArrayList<Mushroom> getMushrooms() {
		return this.mushrooms;
	}

	/**
	 * @return the heros
	 */
	public synchronized ArrayList<Hero> getHeros() {
		return this.heros;
	}

	/**
	 * @return the bullet
	 */
	public synchronized ArrayList<Bullet> getBullets() {
		return this.bullets;
	}

	/**
	 * @return the newbullet
	 */
	public ArrayList<Newbullet> getNewbullets() {
		return this.newbullets;
	}

	/**
	 * @return the monster
	 */
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	/**
	 * @return the buff
	 */
	public ArrayList<Buff> getBuffs() {
		return this.buffs;
	}

	/**
	 * @return the supbullets
	 */
	public ArrayList<Supbullet> getSupbullets() {
		return this.supbullets;
	}

	/**
	 * @return the poisonMushrooms
	 */
	public ArrayList<PoisonMushroom> getPoisonMushrooms() {
		return this.poisonMushrooms;
	}

}
