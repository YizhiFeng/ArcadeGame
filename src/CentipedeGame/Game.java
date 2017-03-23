package CentipedeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {
	public static final Lock lock = new ReentrantLock();
	private GameComponent gameComponent;
	private JFrame myFrame;
	private Container myContainer;
	private KeyboardListener listener;
	private int mapLevel;
	private boolean STOP;
	private ScoreBoard scoreBoard;
	private JLabel point;
	private int labelBlink;
	private MapReader reader;
	private boolean restarting;
	private int heroLife;
	private JButton restartButton;
	private double fleaRate;
	private double scorpionRate;
	private boolean won;
	private JLabel label;
	private ImageIcon bg;
	private HighestScoreReader hsReader;
	private JLabel hs;
	public static final int UPDATE_INTERVAL_MS = 5;

	public Game(int mapLevel, ScoreBoard scoreBoard, boolean playMusic) {

		if (playMusic) {
			Thread music = new Thread(new BackgroundMusic("src/CentipedeGame/clannad - bgm1.wav"));
			music.start();
		}
		int frameWidth = 950;
		int frameHeight = 690;
		this.myFrame = new JFrame();
		this.bg = new ImageIcon("src/CentipedeGame/bkgnd.jpg");
		this.label = new JLabel(this.bg);
		this.label.setBounds(0, 0, frameWidth, frameHeight);
		this.myFrame.getLayeredPane().add(this.label, new Integer(Integer.MIN_VALUE));
		JPanel jp = (JPanel) this.myFrame.getContentPane();
		jp.setOpaque(false);

		this.mapLevel = mapLevel;
		this.scoreBoard = scoreBoard;
		this.restarting = false;
		this.fleaRate = (this.mapLevel + 1) * 0.001;
		this.scorpionRate = (this.mapLevel + 1) * 0.001;

		this.STOP = false;
		this.won = false;

		this.myContainer = new Container(scoreBoard);

		this.myFrame.setSize(frameWidth, frameHeight);
		this.myFrame.setTitle("Arcade game!");

		// JPanel with ScoreBoard
		JPanel score = new JPanel();
		score.setOpaque(false);
		this.point = new JLabel("Score:  " + this.scoreBoard.getScore());
		this.point.setFont(new Font("Bernard MT Condensed", Font.BOLD, 25));
		this.point.setForeground(new Color(255, 69, 0));
		this.labelBlink = 0;
		score.add(this.point);
		this.myFrame.add(score, BorderLayout.NORTH);

		// JPanel with restart button
		JPanel restartPanel = new JPanel();
		restartPanel.setOpaque(false);
		this.restartButton = new JButton("RESTART");
		this.restartButton.addActionListener(new RestartListener(this));
		restartPanel.add(this.restartButton);
		this.restartButton.setEnabled(false);
		this.myFrame.add(restartPanel, BorderLayout.SOUTH);

		// JPanel with game rules and instruction
		GuideReader guideReader = new GuideReader();
		JPanel guide = new JPanel();
		guide.setOpaque(false);
		JLabel info = new JLabel();
		info.setText(guideReader.handleText());
		guide.add(info);
		this.myFrame.add(guide, BorderLayout.WEST);

		// JPanel with top 15 scores
		this.hsReader = new HighestScoreReader();
		JPanel hsPanel = new JPanel();
		hsPanel.setOpaque(false);
		this.hs = new JLabel();
		this.hs.setText(this.hsReader.handleScores(this.hsReader.readFile()));
		hsPanel.add(this.hs);
		this.myFrame.add(hsPanel, BorderLayout.EAST);

		this.restart(this.getMapLevel());
		this.heroLife = this.myContainer.getHeros().get(0).getLives();

		this.gameComponent = new GameComponent(this.myContainer);
		this.myFrame.add(this.gameComponent);

		this.listener = new KeyboardListener(this.myFrame, this.myContainer, this);
		this.myFrame.addKeyListener(this.listener);

		////////////////////////////
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(UPDATE_INTERVAL_MS);
						if (!Game.this.STOP)
							timePassed();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		// Thread for centipede
		Runnable centipedeControl = new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(Game.UPDATE_INTERVAL_MS);
						if (Game.this.STOP || Game.this.restarting)
							continue;
						for (int i = 0; i < Game.this.myContainer.getCentipedes().size(); i++) {
							Game.this.myContainer.getCentipedes().get(i).move();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};

		new Thread(r).start();
		new Thread(centipedeControl).start();
		/////////////////////////////

		this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.myFrame.setVisible(true);

	}

	////////////////////////////////////////////////////// End of constructor
	public void restart(int level) {
		this.restarting = true;
		this.myContainer.getBullets().clear();
		this.myContainer.getCentipedes().clear();
		this.myContainer.getHeros().clear();
		this.myContainer.getMushrooms().clear();
		this.myContainer.getMonsters().clear();
		this.myContainer.getFleas().clear();
		this.myContainer.getPoisonMushrooms().clear();
		this.myContainer.getScorpions().clear();
		this.myContainer.getBuffs().clear();
		this.myContainer.getSupbullets().clear();
		this.myContainer.getNewbullets().clear();
		;
		if (level < 0) {
			level = 0;
		}
		if (level > 2) {
			level = 2;
		}
		this.mapLevel = level;
		this.reader = new MapReader(this.myContainer);
		String[] map = this.reader.openFile(this.mapLevel);
		this.reader.readFile(map);

		this.restarting = false;
	}

	private void timePassed() {
		if (this.restarting)
			return;

		handleObjectMovement();
		handleScoreBoard();
		handleHeroDied();
		handleCentipedeDied();

		this.myFrame.repaint();
	}

	private void handleObjectMovement() {
		if (Math.random() <= this.fleaRate) {
			int pos = (int) (Math.random() * 20);
			this.myContainer.addObject(new Flea(pos * 30, this.myContainer));
		}
		if (Math.random() <= this.scorpionRate) {
			int pos = (int) (Math.random() * 11);
			// System.out.println(pos);
			this.myContainer.addObject(new Scorpion(pos * 30, this.myContainer));
		}
		for (Hero hero : this.myContainer.getHeros()) {
			hero.move();
		}
		for (int i = 0; i < this.myContainer.getScorpions().size(); i++) {
			this.myContainer.getScorpions().get(i).move();
		}
		for (int i = 0; i < this.myContainer.getFleas().size(); i++) {
			this.myContainer.getFleas().get(i).move();
		}
		for (int i = 0; i < this.myContainer.getBullets().size(); i++) {
			this.myContainer.getBullets().get(i).move();
		}
		for (int i = 0; i < this.myContainer.getNewbullets().size(); i++) {
			this.myContainer.getNewbullets().get(i).move();
		}
		for (int i = 0; i < this.myContainer.getMonsters().size(); i++) {
			this.myContainer.getMonsters().get(i).move();
		}
		for (int i = 0; i < this.myContainer.getBuffs().size(); i++) {
			this.myContainer.getBuffs().get(i).move();
		}
		for (int i = 0; i < this.myContainer.getSupbullets().size(); i++) {
			this.myContainer.getSupbullets().get(i).move();
		}
	}

	private void handleScoreBoard() {
		this.point.setText("lvl " + (this.mapLevel + 1) + "   Score:  " + this.scoreBoard.getScore() + "    Lives: "
				+ this.myContainer.getHeros().get(0).getLives() + "    Weapon lvl: "
				+ this.myContainer.getHeros().get(0).getWeapon().getWeaponlvl());
		if (this.labelBlink % 3 == 0) {
			this.point.setForeground(new Color(255, 69, 0));
			this.labelBlink++;
		} else {
			this.point.setForeground(new Color(34, 139, 34));
			this.labelBlink++;
		}
	}

	private void handleHeroDied() {
		if (this.myContainer.getHeros().size() > 0 && !this.won) {
			if (!this.restarting && this.myContainer.getHeros().get(0).getLives() != this.heroLife) {
				if (this.myContainer.getHeros().get(0).getLives() <= 0) {
					this.point.setText("GAME OVER!  TRY AGAIN!");
					this.point.setForeground(Color.RED);
					this.myContainer.getHeros().remove(0);
					this.STOP = true;
					this.restartButton.setEnabled(true);
				} else {
					this.heroLife--;
					this.myContainer.getScoreBoard().updateScore(-200);
					this.restart(this.mapLevel);
					this.myContainer.getHeros().get(0).setLives(this.heroLife);
				}
			}
		}
	}

	private void handleCentipedeDied() {
		if (this.myContainer.getCentipedes().size() <= 0 && !this.restarting) {
			if (this.mapLevel == 2) {
				this.point.setText("!!VICTORY!! " + this.myContainer.getScoreBoard().getScore() + " PTS");
				this.restartButton.setEnabled(true);
				this.point.setForeground(new Color(255, 128, 0));

				ArrayList<String> scores = this.hsReader.getScores(this.hsReader.readFile());
				this.hsReader
						.writeToFile(this.hsReader.updateScores(scores, this.myContainer.getScoreBoard().getScore()));
				this.hs.setText(this.hsReader.handleScores(this.hsReader.readFile()));
				this.setSTOP(true);

			} else {
				restart(this.mapLevel + 1);
			}
		}
	}

	/**
	 * @return the scoreBoard
	 */
	public ScoreBoard getScoreBoard() {
		return this.scoreBoard;
	}

	/**
	 * 
	 * get the restart button
	 *
	 * @return
	 */
	public JButton getRestart() {
		return this.restartButton;
	}

	/**
	 * @return the myCoontainer
	 */
	public Container getMyContainer() {
		return this.myContainer;
	}

	/**
	 * @return the gameComponet
	 */
	public GameComponent getGamecomponet() {
		return this.gameComponent;
	}

	/**
	 * @return the myFrame
	 */
	public JFrame getMyframe() {
		return this.myFrame;
	}

	/**
	 * @return the sTOP
	 */
	public boolean isSTOP() {
		return this.STOP;
	}

	/**
	 * @param sTOP
	 *            the sTOP to set
	 */
	public void setSTOP(boolean sTOP) {
		this.STOP = sTOP;
	}

	/**
	 * @return the mapLevel
	 */
	public int getMapLevel() {
		return this.mapLevel;
	}

}
