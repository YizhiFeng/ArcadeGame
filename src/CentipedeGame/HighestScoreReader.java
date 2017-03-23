package CentipedeGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * This class reads from the HighestScores file, demonstrate and update the top
 * scores.
 *
 * @author fengy2. Created Feb 17, 2016.
 */
public class HighestScoreReader {

	/**
	 * 
	 * read a list from the guide file and convert the list into a string array.
	 *
	 * @return a string array that contains the text of the file
	 */
	public String[] readFile() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src/CentipedeGame/HighestScores"));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}

		return lines.toArray(new String[0]);
	}

	/**
	 * 
	 * adjust the format to let JLabel demonstrate the text.
	 *
	 * @param input
	 * @return a string that is to be used by JLabel
	 */
	public String handleScores(String[] input) {
		String[] text = input;
		String output = "<html>Top 15 Scores: <BR> <BR>";
		for (int i = 0; i < text.length; i++) {
			int rank = i + 1;
			if (i == text.length - 1) {
				output += (rank + ". " + text[i] + "</html>");

			}
			output += (rank + ". " + text[i] + "<BR>");
		}
		return output;
	}

	/**
	 * 
	 * convert the string array into a arraylist for use of comparision and
	 * update.
	 *
	 * @param input
	 * @return a string arraylist for use of comparision and update.
	 */
	public ArrayList<String> getScores(String[] input) {
		ArrayList<String> output = new ArrayList<String>();
		for (int i = 0; i < 15; i++) {
			output.add(input[i]);
		}
		return output;
	}

	/**
	 * 
	 * update the scores list if the score is ranked in top 15.
	 *
	 * @param scores
	 * @param score
	 * @return a string arraylist that is updated
	 */
	public ArrayList<String> updateScores(ArrayList<String> scores, int score) {

		boolean changed = false;
		int i = 0;
		while (!changed && i < scores.size()) {
			if (Integer.parseInt(scores.get(i)) < score) {
				scores.set(i, String.valueOf(score));
				changed = true;
				break;
			}
			i++;
		}

		return scores;
	}

	/**
	 * 
	 * edit the file by using the arraylist of scores.
	 *
	 * @param scores
	 */
	public void writeToFile(ArrayList<String> scores) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/CentipedeGame/HighestScores"));
			for (String x : scores) {
				writer.write(x);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

}
