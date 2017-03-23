package CentipedeGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is to be used for reading from the guide file and tranfering to
 * the JLabel.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class GuideReader {

	/**
	 * 
	 * read a list from the guide file and convert the list into a string array.
	 *
	 * @return a string array that contains the text of the file
	 */
	public String[] readFile() {

		Scanner sc = null;
		try {
			sc = new Scanner(new File("src/CentipedeGame/guide"));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}

		String[] output = lines.toArray(new String[0]);
		return output;
	}

	/**
	 * 
	 * adjust the format to let JLabel demonstrate the text.
	 *
	 * @return a string that is to be used by JLabel
	 */
	public String handleText() {
		String[] text = this.readFile();
		String output = "<html>";
		for (int i = 0; i < text.length; i++) {
			if (i == text.length - 1) {
				output += (text[i] + "</html>");
			}
			output += (text[i] + "<BR>");
		}
		return output;
	}
}
