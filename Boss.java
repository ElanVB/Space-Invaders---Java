
import java.awt.Color;
/**
 * Extends my Enemy class to create a stronger enemy
 * @see Enemy
 * @author elan
 *
 */
public class Boss extends Enemy
{
	/**
	 * Here are some instance and class variables:
	 * The maxHp may vary from enemy to enemy thus it is an instance variable
	 * The rotational displacement and the boolean is rotating are both static class variables
	 * as all the bosses will be rotating at the same time 
	 */
	private final int maxHP = Level.getLevel() * 400;
	private static double rotationalDisplacement = 0;
	private static boolean isRotatingClock = true;
	
	/**
	 * Constructor for the Boss object
	 * @param level this integer determines what sort of Boss is created
	 */
	public Boss(int level)
	{
		super(States.WINDOW_CENTRE, States.WINDOW_RESOLUTION - States.BOSS_SIZE/(double)1.3, 45, Controller.enemies.size(), level * 400);
		
		if(level <= 10)
			angle = 0;
	}

	/**
	 * Draws the Boss object on the screen
	 */
	public void render()
	{
		StdDraw.picture(xPos, yPos, "Boss.png", States.BOSS_SIZE, States.BOSS_SIZE, angle);
		if(HP < maxHP)
			renderHPbar();
	}
	
	/**
	 * Draws the Health Bar for the Boss
	 */
	public void renderHPbar()
	{
		double HPBarWidth = States.BOSS_SIZE*0.7;
		double HPBarHeight = States.enemySize/(double)10;
		
		StdDraw.setPenColor(Color.RED);
		StdDraw.filledRectangle(xPos, yPos + States.BOSS_SIZE/(double)2, HPBarWidth/(double)2, HPBarHeight/(double)2);
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledRectangle(xPos, yPos + States.BOSS_SIZE/(double)2, HPBarWidth/(double)2 - (1 - HP/(double)maxHP)*(HPBarWidth/(double)2), HPBarHeight/(double)2);
	}
	
	/**
	 * Casues the Boss to always aim at the player
	 */
	public void lockOntoPlayer()
	{
		if(Controller.player.getX() <= xPos)
			angle = Math.toDegrees(Math.atan((yPos - Controller.player.getY())/(double)(xPos - Controller.player.getX()))) - 90;
		else
			angle = Math.toDegrees(Math.atan((yPos - Controller.player.getY())/(double)(xPos - Controller.player.getX()))) + 90;

		if(angle > 90)
			angle = 90;
		else if(angle < -90)
			angle = -90;
	}
	
	/**
	 * Causes the Boss to rotate in the opposite direction
	 * to which it is currently rotating
	 */
	public static void invertRotation()
	{
		isRotatingClock = !isRotatingClock;
	}
	
	/**
	 * Resets the rotational displacement 
	 */
	public static void resetRotationalDisplacement()
	{
		rotationalDisplacement = 0;
	}
	
	/**
	 * Returns weather the Boss is rotating clockwise or not
	 * @return true if Boss is rotating clockwise
	 */
	public static boolean isRotatingClock()
	{
		return isRotatingClock;
	}
	
	/**
	 * Increases the rotational displacement
	 * @param speed this double is added to the rotational displacement
	 */
	public static void increaseRotationalDisplacement(double speed)
	{
		rotationalDisplacement += speed;
	}
	
	/**
	 * Returns how far the Boss has rotated in this direction
	 * @return double representing how far this Boss has rotated
	 */
	public static double getRotationalDisplacement()
	{
		return rotationalDisplacement;
	}
	
	/**
	 * Causes the Boss to shoot
	 */
	public void shoot()
	{
		int bossBullet = (Level.getLevel() + 1)/2;
		if(bossBullet % 2 != 0)
		{
			createBullet();

			for(int j = 1; j <= bossBullet/2; j++)
			{
				createBullet(5*j);
				createBullet(-5*j);
			}
		}
		else
		{
			for(int j = 1; j <= bossBullet/2; j++)
			{
				createBullet(5*j -2.5);
				createBullet(-5*j + 2.5);
			}
		}

	}

	/**
	 * creates a EnemyBullet or HomingBullet object
	 * @see EnemyBullet
	 * @see HomingBullet
	 */
	private void createBullet()
	{
		try 
		{
			if((int)(Math.random()*30 - 2*Level.getLevel()) <= Level.getLevel())
				Controller.enemyBullets.add(Controller.enemyBullets.size(), new HomingBullet(Controller.enemies.get(index), Controller.enemyBullets.size()));
			else
				Controller.enemyBullets.add(Controller.enemyBullets.size(), new EnemyBullet(Controller.enemies.get(index), Controller.enemyBullets.size()));
		} 
		catch (IndexOutOfBoundsException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * creates a EnemyBullet or HomingBullet object with an angle offset 
	 * @see EnemyBullet
	 * @see HomingBullet
	 */
	public void createBullet(double angle)
	{
		try
		{
			Controller.enemyBullets.add(new EnemyBullet(Controller.enemies.get(index), Controller.enemyBullets.size(), angle));
		}
		catch (IndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns true if Boss can move left
	 */
	public boolean canMoveLeft()
	{
		if(xPos < States.BOSS_SIZE*0.55)
			return false;
		
		return true;
	}
	
	/**
	 * Returns true if Boss can move right
	 */
	public boolean canMoveRight()
	{
		if(xPos > States.WINDOW_RESOLUTION - States.BOSS_SIZE*0.55)
			return false;
		
		return true;
	}
}
