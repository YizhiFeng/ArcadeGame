package CentipedeGame;

import java.io.File;
import java.util.Scanner;

/**
 * This class is to be used for loading the pre-existed levels of game.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class MapReader {
	Container container;

	/**
	 * @param container
	 */
	public MapReader(Container container) {
		super();
		this.container = container;
	}

	/**
	 * 
	 * Read from the level file and convert it into a string array.
	 *
	 * @param level
	 * @return a string array that is used to load the map
	 */
	public String[] openFile(int level) {
		Scanner readCodes = null;
		try {
			readCodes = new Scanner(new File("src/CentipedeGame/Level" + level));
		} catch (Exception e) {
			System.out.println("FILE NOT FOUND");
		}
		int i = 0;
		String[] codes = new String[99];
		while (readCodes.hasNext()) {
			codes[i] = readCodes.next();
			i++;
		}
		return codes;
	}

	/**
	 * 
	 * usde the string array to the add game objects to the map.
	 *
	 * @param map
	 */
	public void readFile(String[] map) {
		int i = 0;
		while (map[i] != null) {
			for (int j = 0; j < map[i].length(); j++) {
				if (map[i].charAt(j) == 'm') {
					this.container.addObject(new Mushroom(30 * j, 30 * i, this.container));
				} else if (map[i].charAt(j) == 'h') {
					this.container.addObject(new Hero(30 * j, 30 * i, 0, 0, this.container));
				} else if (map[i].charAt(j) == 'c') {
					this.container.addObject(new Centipede(30 * j, 30 * i, this.container));
				} else if (map[i].charAt(j) == 'g') {
					this.container.addObject(new Monster(30 * j, 30 * i, this.container));
				} else if (map[i].charAt(j) == 'b') {
					this.container.addObject(new Buff(30 * j, 30 * i, this.container));
				}
			}
			i++;
		}
	}

}
