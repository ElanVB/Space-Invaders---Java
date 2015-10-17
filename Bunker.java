import java.awt.Color;

/**
 * This Class lets you create a Bunker object
 * @author elan
 *
 */
public class Bunker extends Entity
{
	/**
	 * width is private and final as it is only determined once and may not be seen outside of the class
	 * is moving right is not final the bunker can move right or left and is private as it may not be seen outside of the class
	 */
	private final double width;
	private boolean isMovingRight;

	/**
	 * Constructor for a Bunker object
	 * @param x x value where it will be created
	 * @param y x value where it will be created
	 * @param a angle that it will be created at
	 * @param i the unique index it will get
	 * @param hp the hit points it starts with
	 * @param w the width of the bunker
	 * @param r	if the bunker is moving right initially
	 */
	public Bunker(double x, double y, double a, int i, int hp, double w, boolean r)
	{
		super(x, y, a, i, hp);
		width = w;
		isMovingRight = r;
	}

	/**
	 * creates the bunkers at the beginning of each level
	 */
	public static void initializeBunkers()
	{
		Controller.bunkers.add(new Bunker(States.WINDOW_CENTRE/(double)2, States.SHOOTER_START_Y * 3 + States.BUNKER_HEIGHT, 0, Controller.bunkers.size(), States.bunkerHP, States.bunkerWidth, true));
		Controller.bunkers.add(new Bunker(States.WINDOW_CENTRE/(double)2 + States.WINDOW_CENTRE, States.SHOOTER_START_Y * 3 + States.BUNKER_HEIGHT, 0, Controller.bunkers.size(), States.bunkerHP, States.bunkerWidth, false));
	}

	/**
	 * Draws the bunker on the screen
	 */
	public void render()
	{
		StdDraw.picture(xPos, yPos, "bunker.png", width, States.BUNKER_HEIGHT, angle);
		if(HP < States.bunkerHP)
			renderHPbar();
	}

	/**
	 * Draws the bullets Health Bar
	 */
	public void renderHPbar()
	{
		double HPBarWidth = width*0.7;
		double HPBarHeight = States.BUNKER_HEIGHT/(double)10;

		StdDraw.setPenColor(Color.RED);
		StdDraw.filledRectangle(xPos, yPos + States.BUNKER_HEIGHT/(double)2, HPBarWidth/(double)2, HPBarHeight/(double)2);
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledRectangle(xPos, yPos + States.BUNKER_HEIGHT/(double)2, HPBarWidth/(double)2 - (1 - HP/(double)States.bunkerHP)*(HPBarWidth/(double)2), HPBarHeight/(double)2);
	}

	/**
	 * updates the bunker
	 */
	public void update()
	{
		updatePos();
		checkCollision();

		if(dead)
			Controller.bunkers.remove(index);
	}

	/**
	 * updates the bunkers x co-ordinates
	 */
	private void updatePos()
	{
		if(isMovingRight)
		{
			moveRight(States.BUNKER_SPEED);
		}
		else
		{
			moveLeft(States.BUNKER_SPEED);
		}
	}

	/**
	 * Causes the bunker to move in the opposite direction
	 */
	public void invertMovement()
	{
		isMovingRight = !isMovingRight;
	}

	/**
	 * Returns the width of the bunker
	 * @return width of bunker
	 */
	public double getWidth()
	{
		return width;
	}

	/**
	 * Returns if the bunker can move left or not (if not, inverts its movement)
	 * @return true if bunker can move left
	 */
	public boolean canMoveLeft()
	{
		if(xPos > width/(double)2)
			return true;

		invertMovement();
		return false;
	}

	/**
	 * Returns if the bunker can move right or not (if not, inverts its movement)
	 * @return true if bunker can move right
	 */
	public boolean canMoveRight()
	{
		if(xPos < States.WINDOW_RESOLUTION - width/(double)2)
			return true;

		invertMovement();
		return false;
	}

	/**
	 * checks the various things a bunker can come in contact with
	 * if two bunkers touch
	 * if a bunker touches a Bullet
	 * if a bunker touches a EnemyBullet
	 */
	private void checkCollision()
	{
		for(int j = 0; j < Controller.bunkers.size(); j++)
		{
			if(index != j)
			{
				if(inRadius(Controller.bunkers.get(index), Controller.bunkers.get(j)))
				{
					collideBunkers(index, j);
				}
			}
		}

		for(int j = 0; j < Controller.bullets.size(); j++)
		{
			if(Controller.bunkers.size() < 1)
				break;

			if(inRadius(Controller.bullets.get(j), Controller.bunkers.get(index)))
			{
				collideBunkerAndBullet(Controller.bullets.get(j));
				Index.updateBulletIndex();
			}
		}

		for(int j = Controller.enemyBullets.size() - 1; j >= 0 ; j--)
		{
			if(Controller.bunkers.size() < 1)
				break;

			if(inRadius(Controller.enemyBullets.get(j), Controller.bunkers.get(index)))
			{
				collideBunkerAndBullet(Controller.enemyBullets.get(j));
				Index.updateEnemyBulletIndex();
			}
		}
	}

	/**
	 * Causes the bunkers that collided to invert their movement
	 * @param bunkIndex1 the unique index of the first bunker
	 * @param bunkIndex2 the unique index of the second bunker
	 */
	private void collideBunkers(int bunkIndex1, int bunkIndex2)
	{
		Controller.bunkers.get(bunkIndex1).invertMovement();
		Controller.bunkers.get(bunkIndex1).updatePos();

		Controller.bunkers.get(bunkIndex2).invertMovement();
		Controller.bunkers.get(bunkIndex2).updatePos();
	}

	/**
	 * Collides the bunker with a Bullet or an EnemyBullet and damages the bunker proportionally
	 * @param bull the Bullet object that the bunker has collided with
	 */
	private void collideBunkerAndBullet(Bullet bull)
	{
		if(bull instanceof EnemyBullet)
		{
			Controller.enemyBullets.remove(bull.getIndex());

			if(this.damage(bull))
				dead = true;
		}
		else
		{
			if(!bull.isPhasable())//gives the bullet a chance to phase through the bunker
			{
				try 
				{
					Controller.bullets.remove(bull.getIndex());	
				}
				catch(IndexOutOfBoundsException e) 
				{
					e.printStackTrace();
				}

				if(this.damage(bull))
					dead = true;
			}
		}
	}
}
