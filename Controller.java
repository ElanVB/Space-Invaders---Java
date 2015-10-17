
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/**
 * This Class is the main driver class, it calls and runs the other classes
 * @author elan
 *
 */
public class Controller
{
	/**
	 * these are all public and static as they are changed by many other classes and functions
	 */
	public static ArrayList<Bullet> bullets;
	public static ArrayList<EnemyBullet> enemyBullets;
	public static ArrayList<Enemy> enemies;
	public static ArrayList<PowerUpBox> powerUpBoxes;
	public static ArrayList<Bunker> bunkers;
	public static Screen screen;
	public static Shooter player;
	public static SoundManager soundManager;
	public static PowerUp powerUp;

	/**
	 * Main Method
	 * @param args this is not used
	 */
	public static void main(String[] args)
	{
		initializeScreens();
		Score.loadHighScores();
		soundManager = new SoundManager();

		while(true)
		{
			if(States.isWelcomeRunning)
			{
				welcomeScreen();
			}
			else if(States.isPlayRunning)
			{
				initializeGame();
				playScreen();
			}
			else if(States.isUpgradeRunning)
			{
				upgradeScreen();
			}
			else if(States.isOptionRunning)
			{
				optionScreen();
			}
			else if(States.isInstructionsRunning)
			{
				instructionScreen();
			}
			else if(States.isHighScoreRunning)
			{
				highScoreScreen();
			}
		}
	}

	/**
	 * Sets the Size and scale of the screen
	 */
	private static void initializeScreens()
	{
		StdDraw.setCanvasSize((int)States.WINDOW_RESOLUTION, (int)States.WINDOW_RESOLUTION);
		StdDraw.setXscale(0, States.WINDOW_RESOLUTION);
		StdDraw.setYscale(0, States.WINDOW_RESOLUTION);
	}

	/**
	 * Initializes all the level variables and data structures
	 */
	private static void initializeGame()
	{
		bullets = new ArrayList<Bullet>();
		enemyBullets = new ArrayList<EnemyBullet>();
		enemies = new ArrayList<Enemy>();
		powerUpBoxes = new ArrayList<PowerUpBox>();
		bunkers = new ArrayList<Bunker>();
		AI.initializeEnemies();
		Bunker.initializeBunkers();
		player = new Shooter();
		GameTimer.enemyFireTimer.start();
		GameTimer.powerUpSpawnTimer.start();
	}

	/**
	 * What should happen while the welcome screen is open
	 */
	private static void welcomeScreen()
	{
		screen = new WelcomeScreen();
		GameTimer.initializeTimers();
		
		while(States.isWelcomeRunning)
		{
			render();
			update();

			if(StdDraw.hasNextKeyTyped())
			{
				char keyTyped = StdDraw.nextKeyTyped();
				if(keyTyped == KeyEvent.VK_ENTER)
				{
					States.isWelcomeRunning = false;
					States.isPlayRunning = true;
				}
				else if(keyTyped == KeyEvent.VK_ESCAPE)
				{
					StdAudio.close();
					System.exit(0);
				}
			}
		}

		clearKeyboard();
	}

	/**
	 * What should happen while the play screen is open
	 */
	private static void playScreen()
	{
		screen = new PlayScreen();
		
		while(States.isPlayRunning)
		{
			if(StdDraw.hasNextKeyTyped())
			{
				if(!States.pause)
					KeyHandler.processKey();
			}

			update();
			render();
		}

		GameTimer.enemyFireTimer.stop();
		GameTimer.powerUpSpawnTimer.stop();
		clearKeyboard();
	}

	/**
	 * What should happen while the option screen is open
	 */
	private static void optionScreen()
	{
		screen = new OptionScreen();

		while (States.isOptionRunning)
		{
			if(StdDraw.hasNextKeyTyped())
			{
				char keyTyped = StdDraw.nextKeyTyped();
				if(keyTyped == KeyEvent.VK_ESCAPE)
				{
					States.isOptionRunning = false;
					States.isWelcomeRunning = true;
				}
			}

			update();
			render();
		}

		clearKeyboard();
	}

	/**
	 * What should happen while the high score screen is open
	 */
	private static void highScoreScreen()
	{
		screen = new HighScoreScreen();

		while(States.isHighScoreRunning)
		{
			if(StdDraw.hasNextKeyTyped())
			{
				char keyTyped = StdDraw.nextKeyTyped();
				if(keyTyped == KeyEvent.VK_ESCAPE)
				{
					States.isHighScoreRunning = false;
					States.isWelcomeRunning = true;
				}
			}

			update();
			render();
		}

		clearKeyboard();
	}

	/**
	 * What should happen while the upgrade screen is open
	 */
	private static void upgradeScreen()
	{
		screen = new UpgradeScreen();

		while (States.isUpgradeRunning)
		{
			update();
			render();

			if(StdDraw.hasNextKeyTyped())
			{
				char keyTyped = StdDraw.nextKeyTyped();
				if(keyTyped == KeyEvent.VK_ESCAPE)
				{
					States.isUpgradeRunning = false;
					States.isPlayRunning = true;
				}
			}
		}

		clearKeyboard();
	}

	/**
	 * What should happen while the instructions screen is open
	 */
	private static void instructionScreen()
	{
		screen = new InstructionsScreen();

		while (States.isInstructionsRunning)
		{
			if(StdDraw.hasNextKeyTyped())
			{
				char keyTyped = StdDraw.nextKeyTyped();
				if(keyTyped == KeyEvent.VK_ESCAPE)
				{
					States.isInstructionsRunning = false;
					States.isWelcomeRunning = true;
				}
			}

			update();
			render();
		}

		clearKeyboard();
	}

	/**
	 * Clears the keyboard so the inputs from one screen are not carried over to another
	 */
	private static void clearKeyboard()
	{
		while(StdDraw.hasNextKeyTyped())
			StdDraw.nextKeyTyped();
	}

	/**
	 * pauses the screen for a few seconds
	 */
	public static void pause()
	{
		States.pause = true;
		GameTimer.pauseTimer.start();
		GameTimer.enemyFireTimer.stop();
	}

	/**
	 * calls all the relevant render methods for all other objects and classes
	 */
	private static void render()
	{
		if(!States.pause)
		{
			screen.render();

			if(States.isPlayRunning)
			{
				for(int i = 0; i < bullets.size(); i++)
					bullets.get(i).render();

				for(int i = 0; i < enemyBullets.size(); i++)
					enemyBullets.get(i).render();

				for(int i = 0; i < enemies.size(); i++)
					enemies.get(i).render();

				for(int i = 0; i < powerUpBoxes.size(); i++)
					powerUpBoxes.get(i).render();

				for(int i = 0; i < bunkers.size(); i++)
					bunkers.get(i).render();

				if(powerUp != null)
					powerUp.render();

				Score.render();
				Level.render();
				player.render();
			}
			else if(States.isUpgradeRunning)
			{
				Store.render();
			}
		}

		StdDraw.show(1000/(States.FPS*2));
	}

	/**
	 * calls all the relevant update methods for all other objects and classes
	 */
	private static void update()
	{
		if(!States.pause)
		{
			if(States.isPlayRunning)
			{
				checkWinLevel();

				Index.updateBulletIndex();
				Index.updateEnemyBulletIndex();
				Index.updateEnemyIndex();
				Index.updatePowerUpBoxesIndex();
				Index.updateBunkerIndex();


				player.update();
				Index.updateEnemyBulletIndex();


				for(int i = bullets.size() - 1; i >= 0; i--)
					bullets.get(i).update();

				Index.updateBulletIndex();
				Index.updateEnemyIndex();


				for(int i = enemyBullets.size() - 1; i >= 0 ; i--)
					enemyBullets.get(i).update();

				Index.updateEnemyBulletIndex();
				Index.updateBunkerIndex();


				for(int i = enemies.size() - 1; i >= 0 ; i--)
					enemies.get(i).update();

				Index.updateEnemyIndex();


				for(int i = powerUpBoxes.size() - 1; i >= 0; i--)
					powerUpBoxes.get(i).update();

				Index.updatePowerUpBoxesIndex();


				for(int i = bunkers.size() - 1; i >= 0; i--)
					bunkers.get(i).update();

				Index.updateBunkerIndex();

				AI.enemyAI();
			}
		}

		screen.checkMouseActivity();
		checkGameOver();
	}

	/**
	 * What happens if you leave the play screen
	 */
	public static void exitConfirmation()
	{
		//will check if they really want to exit here
		clearKeyboard();
		States.isPlayRunning = false;
		States.isWelcomeRunning = true;
		resetLevel();
	}

	/**
	 * resets all the variables relevant to the level
	 */
	private static void resetLevel()
	{
		Enemy.resetHorizontalDisplacement();
		Enemy.resetVerticalDisplacement();

		if(!Enemy.isMovingRight())
			Enemy.invertHorizontalMotion();

		if(Enemy.isMovingDown())
			Enemy.startOrStopVerticalMotion();

		if(Level.getLevel() % 5 == 0)
		{
			Boss.resetRotationalDisplacement();

			if(!Boss.isRotatingClock())
			{
				Boss.invertRotation();
			}
		}
		PowerUp.clearPowerUps();

		if(!States.gameOver)
		{
			initializeGame();
			player = new Shooter();
		}
	}

	/**
	 * resets all variables relevant to the beginning of the game
	 */
	public static void resetGame()
	{
		resetStateValues();
		resetLevel();
		Level.resetLevel();
		Score.resetScore();
		Store.resetStore();
		initializeGame();
	}

	/**
	 * resets all variables relevant to the beginning of the game
	 */
	private static void resetStateValues()
	{
		States.fireRate = 500;
		States.playerDamage = 10;
		States.enemyDamage = 4;
		States.enemyShootDelay = 1500;
		States.enemyHealth = 10;
		States.playerLives = new int[]{3, 3};
		States.powerUpDuration = 5000;
		States.numberBulletsPlayerShoots = 1;
		States.playerHP = 10;
		States.bulletPhaseChance = 1;
		States.bunkerHP = 50;

		States.enemySpeed = States.SHOOTER_MOVE_SPEED * 0.5;
		States.enemyBulletSpeed = States.enemySpeed*1.5;
		States.enemySize = States.SHOOTER_SIZE * 0.9;
		States.bunkerWidth = States.WINDOW_CENTRE/(double)3;
	}

	/**
	 * checks if the level has been won by seeing of there are still enemies or not
	 */
	private static void checkWinLevel()
	{
		if(enemies != null && enemies.size() == 0)
			winLevel();
	}

	/**
	 * what should happen when you beat a level
	 */
	private static void winLevel()
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(States.WINDOW_CENTRE, States.WINDOW_CENTRE + States.WINDOW_CENTRE/(double) 1.5, "LEVEL " + Level.getLevel() + " COMPLETED");
		pause();

		States.isUpgradeRunning = true;
		States.isPlayRunning = false;

		Store.addCredits(Score.getScore());
		Score.consolidateScore();

		Level.levelUp();
		resetLevel();
	}

	/**
	 * set a boolean showing that you have lost the game
	 */
	public static void gameOver()
	{
		States.gameOver = true;
	}

	/**
	 * check if you have lost the game and if so resets it
	 */
	public static void checkGameOver()
	{
		if(States.gameOver)
		{
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(States.WINDOW_CENTRE, States.WINDOW_CENTRE + States.WINDOW_CENTRE/(double) 1.5, "THE ALIENS HAVE DEFEATED YOU!");
			StdDraw.text(States.WINDOW_CENTRE, States.WINDOW_CENTRE + States.WINDOW_CENTRE/(double) 1.9, "STARTING AT LEVEL 1 AGAIN!");
			pause();

			resetGame();
			States.gameOver = false;
		}
	}

	/**
	 * resets the level
	 */
	public static void loseLevel()
	{
		soundManager.playExplode();
		if(States.playerLives[0] <= 0)
			States.gameOver = true;
		else
		{
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(States.WINDOW_CENTRE, States.WINDOW_CENTRE + States.WINDOW_CENTRE/(double) 1.5, "YOU LOST THIS LEVEL! GET READY!");
			pause();
		}

		Score.resetInterumScore();

		resetLevel();
		GameTimer.enemyFireTimer.stop();
		States.isPlayRunning = true;
	}
}