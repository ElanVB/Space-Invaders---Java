import java.awt.Color;
/**
 * This is my Shooter Object, 
 * it is essentially the player
 * @author elan
 *
 */
public class Shooter extends Entity
{
	/**
	 * constructor for the shooter, 
	 * creates a shooter at the middle bottom of the screen
	 */
	public Shooter()
	{
		super(States.SHOOTER_START_X, States.SHOOTER_START_Y, 0);
		HP = States.playerHP;
	}

	/**
	 * updates the shooter
	 */
	public void update()
	{
		checkCollision();
	}

	/**
	 * check what the shooter has come into contact with
	 * and handles it
	 */
	private void checkCollision()
	{
		for(int i = Controller.enemyBullets.size() - 1; i >= 0; i--)
			if(inRadius(Controller.player, Controller.enemyBullets.get(i)))
			{
				collidePlayerAndEnemyBullet(Controller.enemyBullets.get(i));
				break;
			}
	}

	/**
	 * collides the player with the enemy's bullet, 
	 * destroyes the bullet and if the player is out of health restarts the level
	 * @param enemyBullet made contact with
	 */
	private void collidePlayerAndEnemyBullet(EnemyBullet enemyBullet)
	{
		try
		{
			Controller.enemyBullets.remove(enemyBullet.getIndex());
		}
		catch(IndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}

		if(Controller.player.damage(enemyBullet))
			Controller.loseLevel();
	}

	/**
	 * Causes the shooter to shoot Bullets
	 */
	public void shoot()
	{
		if(States.canShoot && !States.pause)
		{
			if(States.numberBulletsPlayerShoots % 2 != 0)
			{
				createBullet();

				for(int j = 1; j <= States.numberBulletsPlayerShoots/2; j++)
				{
					createBullet(5*j);
					createBullet(-5*j);
				}
			}
			else
			{
				for(int j = 1; j <= States.numberBulletsPlayerShoots/2; j++)
				{
					createBullet(5*j -2.5);
					createBullet(-5*j + 2.5);
				}
			}
			States.canShoot = false;
			GameTimer.shootTimer.start();
			Controller.soundManager.playShoot();
		}
	}

	/**
	 * creates a bullet at the shooters co-ordinates,
	 * with the same angle
	 */
	private void createBullet()
	{
		try
		{
			Controller.bullets.add(Controller.bullets.size(), new Bullet(Controller.player, Controller.bullets.size()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * creates a bullet at the shooters co-ordinates,
	 * with a slight angle offset
	 */
	public void createBullet(double angle)
	{
		try 
		{
			Controller.bullets.add(Controller.bullets.size(), new Bullet(Controller.player, Controller.bullets.size(), angle));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return number of lives the shooter has left
	 */
	public int getLives()
	{
		return States.playerLives[0];
	}

	/**
	 * reduces the number of lives the shooter has
	 */
	public void loseLife()
	{
		States.playerLives[0]--;
		Controller.loseLevel();
	}

	/**
	 * draws the shooter and its health bar on the screen
	 */
	public void render()
	{
		double HPBarWidth = States.WINDOW_RESOLUTION/(double)4;
		double HPBarHeight = States.WINDOW_RESOLUTION/(double)20;

		StdDraw.setPenColor(Color.RED);
		StdDraw.filledRectangle(States.WINDOW_RESOLUTION - HPBarWidth/(double)1.5, States.WINDOW_RESOLUTION - HPBarHeight/(double)1.5, HPBarWidth/(double)2, HPBarHeight/(double)2);
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledRectangle(States.WINDOW_RESOLUTION - HPBarWidth/(double)1.5, States.WINDOW_RESOLUTION - HPBarHeight/(double)1.5, HPBarWidth/(double)2 - (1 - HP/(double)States.playerHP)*(HPBarWidth/(double)2), HPBarHeight/(double)2);

		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(States.WINDOW_RESOLUTION - HPBarWidth/(double)1.5, States.WINDOW_RESOLUTION - HPBarHeight/(double)1.5, ((int)(HP*100/(double)States.playerHP)) + "% x" + (States.playerLives[0]));

		super.render("shooter.png");
	}

	/**
	 * sets the shooters angle when using the mouse
	 */
	public void setAngle()
	{
		if(Screen.mouse.getX() <= xPos)
			angle = Math.toDegrees(Math.atan((yPos - Screen.mouse.getY())/(double)(xPos - Screen.mouse.getX()))) + 90;
		else
			angle = Math.toDegrees(Math.atan((yPos - Screen.mouse.getY())/(double)(xPos - Screen.mouse.getX()))) - 90;

		if(angle > 90)
			angle = 90;
		else if(angle < -90)
			angle = -90;
	}

	/**
	 * @param health sets the shooters hit points to this value
	 */
	public void setHP(int health)
	{
		HP = health;
	}
}