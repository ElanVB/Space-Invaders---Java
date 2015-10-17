/**
 * This class handles everything to do with the level or difficulty of the game
 * @author elan
 *
 */
public class Level 
{
	/**
	 * some Static class variables
	 */
	private static int level = 1;
	private static int enemyRows = 2;
	private static int enemyCols = 5;
	
	/**
	 * adjusts the difficulty of the level
	 */
	public static void levelUp()
	{
		PowerUp.clearPowerUps();
		
		level++;
		States.enemySpeed *= 1.1;
		States.enemyBulletSpeed *= 1.1;
		States.enemyDamage *= 1.2;
		States.enemyShootDelay *= 0.9;
		States.enemyHealth *= 1.1;
		
		GameTimer.updateTimers();
		
		if(level % 3  == 0)
			enemyRows++;
		
		if(level % 4 == 0)
			enemyCols++;
		
		if(States.debug)
			System.out.println("LEVEL UP!");
	}
	
	/**
	 * resets the level back to one
	 */
	public static void resetLevel()
	{
		level = 1;
		enemyRows = 2;
		enemyCols = 5;
	}
	
	/**
	 * @return the amount of rows of enemies
	 */
	public static int getEnemyRows()
	{
		return enemyRows;
	}
	
	/**
	 * @return the amount of columns of enemies
	 */
	public static int getEnemyCols()
	{
		return enemyCols;
	}
	
	/**
	 * @return integer representing how difficult the level is, 
	 * the higher the more difficult
	 */
	public static int difficultyIndex()
	{
		return (int) (level + enemyRows*enemyCols + (States.enemySpeed + States.enemyBulletSpeed)/(double)States.WINDOW_CENTRE + States.enemyDamage + (1500 - States.enemyShootDelay) + States.enemyHealth);
	}
	
	/**
	 * @return level
	 */
	public static int getLevel()
	{
		return level;
	}
	
	/**
	 * @return the chance of a power being dropped by an enemy, 
	 * depending on the difficulty of the level
	 */
	public static boolean powerUpChance()
	{
		int difficulty = difficultyIndex();
		if((int)(Math.random()*(difficulty - (int)(Math.random()*(difficulty)))) > level*70)
			return true;
		
		return false;
	}
	
	/**
	 * draws the level variables on the screen
	 */
	public static void render()
	{
		StdDraw.text(States.WINDOW_RESOLUTION/(double)8, States.WINDOW_RESOLUTION - States.WINDOW_RESOLUTION/(double)30, "LEVEL: " + level);
	}
}
