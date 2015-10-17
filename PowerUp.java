/**
 * This Class handles all the power up attributes, 
 * if you get a power up this class will apply and remove the effect
 * @author elan
 *
 */
public class PowerUp 
{
	/**
	 * Some static variables to store the state of the game befor the power up took effect, 
	 * thus it will be able to be reverted later, 
	 * all private and static
	 */
	private static int fireRate;
	private static int playerDamage;
	private static int enemyDamage;
	private static int numberBulletsPlayerShoots;

	private static double enemySpeed;
	private static double enemyBulletSpeed;

	private static boolean shieldActive = false;
	
	private static String text;

	/**
	 * Constructor for PowerUp
	 * fetches current state values and stores it in the Class
	 */
	public PowerUp()
	{
		fireRate = States.fireRate;
		playerDamage = States.playerDamage;
		enemyDamage = States.enemyDamage;
		numberBulletsPlayerShoots = States.numberBulletsPlayerShoots;

		enemySpeed = States.enemySpeed;
		enemyBulletSpeed = States.enemyBulletSpeed;

		activate();
	}

	/**
	 * randomly (weighted) selects a powerUp and activates it
	 */
	public void activate()
	{
		int randomChance = (int)(Math.random()*100);

		if(randomChance <= 19)
		{
			instaKill();
		}
		else if(randomChance <= 29)
		{
			slowMo();
		}
		else if(randomChance <= 39)
		{
			machineGun();
		}
		else if(randomChance <= 44)
		{
			rapidFire();
		}
		else if(randomChance <= 54)
		{
			shield();
		}
		else if(randomChance <= 64)
		{
			multiShot();
		}
		else if(randomChance <= 69)
		{
			bulletPulse();
		}
		else if(randomChance <= 89)
		{
			heal();
		}
		else
		{
			addLife();
		}

		if(GameTimer.powerUpTimer.isRunning())
			GameTimer.powerUpTimer.restart();
		else
			GameTimer.powerUpTimer.start();
		
		Controller.soundManager.playPowerUp();
	}

	/**
	 * Activates slow Motion Powerup
	 */
	public void slowMo()
	{
		States.enemyBulletSpeed *= 0.3;
		States.enemySpeed *= 0.3;
		text = "SLOW MOOOOO....";
	}

	public void instaKill()
	{
		States.playerDamage = Integer.MAX_VALUE;
		text = "INSTA-KILL!";
	}

	public void machineGun()
	{
		States.fireRate *= 0.25;
		GameTimer.updateShootTimer();
		text = "Fire Rate Up";
	}

	public void rapidFire()
	{
		States.fireRate = 0;
		GameTimer.updateShootTimer();
		text = "Fire Rate WAAAY Up!";
	}

	public void shield()
	{
		States.enemyDamage = 0;
		shieldActive = true;
		text = "Bullet Shield";
	}

	public void multiShot()
	{
		States.numberBulletsPlayerShoots += (int)(Math.random()*5 + 1);
		text = "Multi-Shot";
	}

	public void bulletPulse()
	{
		States.numberBulletsPlayerShoots = 72;
		text = "No one lives....";
	}

	public void heal()
	{
		Controller.player.setHP(States.playerHP);
		text = "HEALED!";
	}

	public void addLife()
	{
		States.playerLives[0]++;
		text = "ADD LIFE!";
	}

	/**
	 * removes the effects of the powerUp
	 */
	public void deActivate()
	{
		States.fireRate = fireRate;
		States.playerDamage = playerDamage;
		States.enemyDamage = enemyDamage;
		States.numberBulletsPlayerShoots = numberBulletsPlayerShoots;

		States.enemySpeed = enemySpeed;
		States.enemyBulletSpeed = enemyBulletSpeed;

		shieldActive = false;

		GameTimer.updateShootTimer();
	}

	/**
	 * Deactivates the power Ups
	 */
	public static void clearPowerUps()
	{
		if(Controller.powerUp != null)
		{
			Controller.powerUp.deActivate();
			Controller.powerUp = null;
		}
		GameTimer.powerUpTimer.stop();
	}

	/**
	 * Draws the power up details on the screen
	 */
	public void render()
	{
		if(shieldActive)
			StdDraw.picture(Controller.player.getX(), Controller.player.getY(), "shield.png", States.SHOOTER_SIZE, States.SHOOTER_SIZE);
		
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		StdDraw.text(States.WINDOW_RESOLUTION - text.length()*StdDraw.getFont().getSize()*0.4, States.SHOOTER_SIZE/(double)4, text);
	}
}
