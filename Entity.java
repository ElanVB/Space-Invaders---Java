/**
 * 
 * @author elan
 *
 */
public class Entity
{
	/**
	 * these variable are all instance variable because they will be different for every instance
	 */
	protected double xPos, yPos, angle;
	protected int index, HP;
	protected boolean dead = false;

	/**
	 * Constructor for my Entity
	 * @param x x co-ordinate where it will be created
	 * @param y y co-ordinate where it will be created
	 * @param a angle it will start with
	 */
	public Entity(double x, double y, double a)
	{
		xPos = x;
		yPos = y;
		angle = a;
	}

	/**
	 * Constructor for my Entity
	 * @param x x co-ordinate where it will be created
	 * @param y y co-ordinate where it will be created
	 * @param a angle it will start with
	 * @param i unique identifier
	 */
	public Entity(double x, double y, double a, int i)
	{
		xPos = x;
		yPos = y;
		angle = a;
		index = i;
	}

	/**
	* Constructor for my Entity
	 * @param x x co-ordinate where it will be created
	 * @param y y co-ordinate where it will be created
	 * @param a angle it will start with
	 * @param i unique identifier
	 * @param HP hit points it will start with
	 */
	public Entity(double x, double y, double a, int i, int HP)
	{
		xPos = x;
		yPos = y;
		angle = a;
		index = i;
		this.HP = HP;
	}

	/**
	 * Draws the picture on the screen
	 * @param filename Name of the image to be drawn
	 */
	public void render(String filename)
	{
		StdDraw.picture(xPos, yPos, filename, States.SHOOTER_SIZE, States.SHOOTER_SIZE, angle);
	}
	
	/**
	 * @return Returns the X co-ordinate of the entity
	 */
	public double getX()
	{
		return xPos;
	}

	/**
	 * @return Returns the X co-ordinate of the entity
	 */
	public double getY()
	{
		return yPos;
	}

	/**
	 * @return Returns the angle of the entity
	 */
	public double getAngle()
	{
		return angle;
	}
	
	/**
	 * @return Returns the hit points of the entity
	 */
	public int getHP()
	{
		return HP;
	}

	/**
	 * @param speed moves the entity Left by this amount
	 */
	public void moveLeft(double speed)
	{
		if(canMoveLeft())
		{
			xPos -= speed;
		}
	}

	/**
	 * @param speed moves the entity Right by this amount
	 */
	public void moveRight(double speed)
	{
		if(canMoveRight())
		{
			xPos += speed;
		}
	}

	/**
	 * @param speed moves the entity Up by this amount
	 */
	public void moveUp(double speed)
	{
		if(canMoveUp())
		{
			yPos += speed;
		}
	}

	/**
	 * @param speed moves the entity Down by this amount
	 */
	public void moveDown(double speed)
	{
		if(canMoveDown())
		{
			yPos -= speed;
		}
	}

	/**
	 * @param speed rotates the entity clockwise by this amount
	 */
	public void rotateClockWise(double speed)
	{
		if(canRotateClockWise())
		{
			angle -= speed;
		}
	}

	/**
	 * @param speed rotates the entity anti-clockwise by this amount
	 */
	public void rotateAntiClockWise(double speed)
	{
		if(canRotateAntiClockWise())
		{
			angle += speed;
		}
	}

	/**
	 * @return true if entity can move left
	 */
	protected boolean canMoveLeft()
	{
		if(xPos <= (States.SHOOTER_SIZE * 0.5 + States.SHOOTER_MOVE_SPEED * 1.1))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return true if entity can move right
	 */
	protected boolean canMoveRight()
	{
		if(xPos >= (States.WINDOW_RESOLUTION - (States.SHOOTER_SIZE * 0.5 + States.SHOOTER_MOVE_SPEED * 1.1)))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return true if entity can move up
	 */
	protected boolean canMoveUp()
	{
		if(yPos >= States.WINDOW_RESOLUTION)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return true if entity can move down
	 */
	protected boolean canMoveDown()
	{
		if(yPos <= 0)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return true if entity can rotate anti-clockwise
	 */
	private boolean canRotateAntiClockWise()
	{
		if(angle >= 90 - States.SHOOTER_ROTATE_SPEED)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return true if entity can rotate clockwise
	 */
	private boolean canRotateClockWise()
	{
		if(angle <= -90 + States.SHOOTER_ROTATE_SPEED)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the entity's unique identifier
	 */
	public int getIndex()
	{
		return index;
	}

	/**
	 * @param i sets the entity's unique identifier to this
	 */
	public void updateIndex(int i)
	{
		index = i;
	}
	
	/**
	 * This method check whether these two entities are touching
	 * @param entity1
	 * @param entity2
	 * @return true if they are in contact
	 */
	protected boolean inRadius(Entity entity1, Entity entity2)
	{
		try
		{
		if(entity1 instanceof Shooter || entity2 instanceof Shooter)
			if(Math.abs(entity1.getX() - entity2.getX()) < States.SHOOTER_SIZE*0.5 && Math.abs(entity1.getY() - entity2.getY()) < States.SHOOTER_SIZE*0.5)
				return true;

		if(entity1 instanceof Enemy || entity2 instanceof Enemy)
			if(Math.abs(entity1.getX() - entity2.getX()) < States.enemySize*0.5 && Math.abs(entity1.getY() - entity2.getY()) < States.enemySize*0.5)
				return true;

		if(entity1 instanceof PowerUpBox || entity2 instanceof PowerUpBox)
			if(Math.abs(entity1.getX() - entity2.getX()) < States.SHOOTER_SIZE*0.5 && Math.abs(entity1.getY() - entity2.getY()) < States.SHOOTER_SIZE*0.5)
				return true;

		if(entity1 instanceof Bunker && entity1 instanceof Bunker)
			if(Math.abs(entity1.getX() - entity2.getX()) < (((Bunker) entity1).getWidth() + ((Bunker) entity2).getWidth())/(double)2)
				return true;

		if(entity2 instanceof Bunker)
			if(Math.abs(entity1.getX() - entity2.getX()) < ((Bunker) entity2).getWidth()/(double)2 && Math.abs(entity1.getY() - entity2.getY()) < States.BUNKER_HEIGHT)
				return true;
		
		if(entity1 instanceof Boss || entity2 instanceof Boss)
			if(Math.abs(entity1.getX() - entity2.getX()) < States.BOSS_SIZE*0.5 && Math.abs(entity1.getY() - entity2.getY()) < States.BOSS_SIZE*0.5)
				return true;
		}
		catch(Exception e)	{e.printStackTrace();}

		return false;
	}

	/**
	 * Decreases the hit points of the entity
	 * @param bullet the damage of this is subtracted from hit points
	 * @return result of checHP
	 */
	public boolean damage(Bullet bullet)
	{
		HP -= bullet.getDamage();
		return checkHP();
	}

	/**
	 * @return true if entity's hit points is 0 or less
	 */
	private boolean checkHP()
	{
		if(HP <= 0)
		{
			if(this instanceof Enemy)
			{
				Score.increaseScore(Controller.player, (Enemy) this);
				return true;
			}
			else if(this instanceof Shooter)
				if(((Shooter)this).getLives() < 1)
					return true;
				else
				{
					((Shooter)this).loseLife();
					Controller.player = new Shooter();
				}
			else if(this instanceof Bunker)
				return true;
		}
		return false;
	}
}