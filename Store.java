import java.awt.Color;

/**
 * This class handles all the upgrades and currency in the game
 * @author elan
 *
 */
public class Store 
{
	private static Color color = Color.WHITE;
	
	private static int credits = 0;
	
	/**
	 * The initial costs of each upgrade, 
	 * obviously this will change and is thus not final
	 */
	private static int fireRateCost = 2500;
	private static int multiShotCost = 4000;
	private static int HPCost = 1000;
	private static int powerUpCost = 7000;
	private static int slowEnemyCost = 2500;
	private static int increaseLivesCost = 2000;
	private static int bunkersCost = 3000;
	private static int bulletDamageCost = 2500;
	
	/**
	 * Add currency
	 * @param c is added to your currency
	 */
	public static void addCredits(int c)
	{
		credits += c;
	}
	
	/**
	 * resets all costs and currency to default values
	 */
	public static void resetStore()
	{
		credits = 0;
		
		fireRateCost = 2500;
		multiShotCost = 4000;
		HPCost = 1000;
		powerUpCost = 7000;
		slowEnemyCost = 2500;
		increaseLivesCost = 2000;
		bunkersCost = 3000;
		bulletDamageCost = 2500;
	}
	
	/**
	 * purchases the fire rate upgrade
	 */
	public static void upgradeFireRate()
	{
		color = Color.WHITE;
		if(credits >= fireRateCost)
		{
			States.fireRate *= 0.8;
			GameTimer.updateShootTimer();
			credits -= fireRateCost;
			fireRateCost *= 3;
		}
		else
			color = Color.RED;
	}
	
	public static void upgradeMultiShot()
	{
		color = Color.WHITE;
		if(credits >= multiShotCost)
		{
			States.numberBulletsPlayerShoots++;
			credits -= multiShotCost;
			multiShotCost *= 3;
		}
		else
			color = Color.RED;
	}
	
	public static void upgradeHP()
	{
		color = Color.WHITE;
		if(credits >= HPCost)
		{
			States.playerHP *= 1.2;
			credits -= HPCost;
			HPCost *= 3;
		}
		else
			color = Color.RED;
	}
	
	public static void upgradePowerUps()
	{
		color = Color.WHITE;
		if(credits >= powerUpCost)
		{
			States.powerUpDuration *= 1.2;
			credits -= powerUpCost;
			powerUpCost *= 3;
		}
		else
			color = Color.RED;
	}
	
	public static void slowEnemies()
	{
		color = Color.WHITE;
		if(credits >= slowEnemyCost)
		{
			States.enemySpeed *= 0.8;
			States.enemyBulletSpeed *= 0.8;
			credits -= slowEnemyCost;
			slowEnemyCost *= 3;
		}
		else
			color = Color.RED;
	}
	
	public static void increaseLives()
	{
		color = Color.WHITE;
		if(credits >= increaseLivesCost)
		{
			States.playerLives[0] += 5;
			credits -= increaseLivesCost;
			increaseLivesCost *= 3;
		}
		else
			color = Color.RED;
	}
	
	public static void upgradeBunkers()
	{
		color = Color.WHITE;
		if(credits >= bunkersCost)
		{
			States.bulletPhaseChance++;
			States.bunkerWidth *= 1.1;
			States.bunkerHP *= 1.2;
			credits -= bunkersCost;
			bunkersCost *= 3;
		}
		else
			color = Color.RED;
	}
	
	public static void upgradeBulletDamage()
	{
		color = Color.WHITE;
		if(credits >= bulletDamageCost)
		{
			States.playerDamage *= 1.2;
			credits -= bulletDamageCost;
			bulletDamageCost *= 3;
		}
		else
			color = Color.RED;
	}
	
	/**
	 * draws the cost onto its corresponding buttons on the screen
	 * @param button
	 */
	private static void renderCost(Button button)
	{
		if(button.text.contains("Fire Rate"))
			button.addCaption(fireRateCost);
		else if(button.text.contains("Bullet Damage"))
			button.addCaption(bulletDamageCost);
		else if(button.text.contains("HP"))
			button.addCaption(HPCost);
		else if(button.text.contains("Power-Ups"))
			button.addCaption(powerUpCost);
		else if(button.text.contains("Multi-shot"))
			button.addCaption(multiShotCost);
		else if(button.text.contains("Upgrade Bunkers"))
			button.addCaption(bunkersCost);
		else if(button.text.contains("Warp Drive"))
			button.addCaption(slowEnemyCost);
		else if(button.text.contains("Buy Lives x5"))
			button.addCaption(increaseLivesCost);
	}
	
	/**
	 * draws the currency on the screen
	 */
	public static void render()
	{
		StdDraw.setPenColor(color);
		StdDraw.text(States.WINDOW_RESOLUTION - States.WINDOW_CENTRE/(double)2, States.WINDOW_RESOLUTION - States.WINDOW_CENTRE/(double)5, "Credits: " + credits);
	}
	
	/**
	 * draws all the costs onto their corresponding buttons
	 */
	public static void renderCosts()
	{
		for(int i = 0; i < Controller.screen.buttons.length; i++)
			renderCost(Controller.screen.buttons[i]);
	}
}
