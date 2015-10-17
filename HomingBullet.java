/**
 * This Class can be used to create HomingBullet objects
 * @author elan
 *
 */
public class HomingBullet extends EnemyBullet
{
	/**
	 * Cunstructor for the HomingBullet object
	 * @param enemy Bullet will be created with same x, y, and angle as this entity
	 * @param index Give the HomingBullet a unique index to identify it
	 */
	public HomingBullet(Entity enemy, int index)
	{
		super(enemy, index);
		angle = enemy.getAngle() + 180;
	}

	/**
	 * Does all nessisary updates for the Bullet object
	 */
	public void update()
	{
		super.update();
		updateAngle();
	}

	/**
	 * gradually changes the trajectory of the bullet to aim for the player
	 */
	public void updateAngle()
	{
		if(Controller.player.getX() <= xPos)
		{
			if(angle != (Math.toDegrees(Math.atan((yPos - Controller.player.getY())/(double)(xPos - Controller.player.getX()))) - 90))
				angle += (Math.toDegrees(Math.atan((yPos - Controller.player.getY())/(double)(xPos - Controller.player.getX()))) - 90)/(double)50;
		}
		else
			if(angle != (Math.toDegrees(Math.atan((yPos - Controller.player.getY())/(double)(xPos - Controller.player.getX()))) + 90))
				angle += (Math.toDegrees(Math.atan((yPos - Controller.player.getY())/(double)(xPos - Controller.player.getX()))) + 90)/(double)50;
	}

	/**
	 * draws the Bullet on the screen
	 */
	public void render()
	{
		super.render();
		StdDraw.picture(xPos, yPos, "homingBullet.png", States.BULLET_SIZE, States.BULLET_SIZE, angle);
	}
}
