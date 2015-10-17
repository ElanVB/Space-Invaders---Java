/**
 * This Class can be used to create EnemyBullet objects
 * @author elan
 *
 */
public class EnemyBullet extends Bullet
{
	
	/**
	 * Cunstructor for the EnemyBullet object
	 * @param enemy Bullet will be created with same x, y, and angle as this entity
	 * @param index Give the EnemyBullet a unique index to identify it
	 */
	public EnemyBullet(Entity enemy, int index)
	{
		super(enemy, index);
		angle = enemy.getAngle() + 180;
	}

	/**
	 * Cunstructor for the EnemyBullet object
	 * @param enemy Bullet will be created with same x, y, and angle as this entity
	 * @param index Give the EnemyBullet a unique index to identify it
	 * @param angleDeviation this angle will be added to the entity's angle and the bullet will be created with the resulting angle
	 */
	public EnemyBullet(Entity enemy, int index, double a)
	{
		super(enemy, index);
		angle = enemy.getAngle() + 180 + a;
	}

	/**
	 * Does all nessisary updates for the Bullet object
	 */
	public void update()
	{
		updatePos();
		checkBounds();
	}

	/**
	 * updates the Bullets x and y co-ordinates
	 */
	protected void updatePos()
	{
		moveLeft(States.enemySpeed*Math.cos(Math.toRadians(90 - angle)));
		moveUp(States.enemySpeed*Math.sin(Math.toRadians(90 - angle)));
	}

	/**
	 * Returns how much damage this bullet does
	 * @return damage of the bullet
	 */
	public int getDamage()
	{
		return States.enemyDamage;
	}

	/**
	 * checks if the bullet is still on the screen, if it is not it is destroyed
	 */
	private void checkBounds()
	{
		if(!(canMoveLeft() && canMoveRight() && canMoveUp() && canMoveDown()))
		{
			try
			{
				Controller.enemyBullets.remove(index);
			}
			catch(IndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * draws the Bullet on the screen
	 */
	public void render()
	{
		String type = "";
		
		if(Level.getLevel() > 14)
			type = "enemyBullet4.png";
		else if(Level.getLevel() > 9)
			type = "enemyBullet3.png";
		else if(Level.getLevel() > 4)
			type = "enemyBullet2.png";
		else
			type = "enemyBullet1.png";
		
		StdDraw.picture(xPos, yPos, type, States.BULLET_SIZE, States.BULLET_SIZE, angle);
	}
}
