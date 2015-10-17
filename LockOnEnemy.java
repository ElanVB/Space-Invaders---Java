/**
 * This is an extension of the enemy Class, 
 * This enemy simply aims at the player the whole time
 * @author elan
 *
 */
public class LockOnEnemy extends Enemy
{
	public LockOnEnemy(double x, double y, double a, int i, int hp)
	{
		super(x, y, a, i, hp);
	}
	
	public void update()
	{
		super.update();
		updateAngle();
	}
	
	public void updateAngle()
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
	
	public void render()
	{
		String type = "";

		if(States.enemyHealth > 23)
			type = "LockOnEnemy4.png";
		else if(States.enemyHealth > 18)
			type = "LockOnEnemy3.png";
		else if(States.enemyHealth > 13)
			type = "LockOnEnemy2.png";
		else
			type = "LockOnEnemy1.png";

		StdDraw.picture(xPos, yPos, type, States.enemySize, States.enemySize, angle);
		
		if(HP < States.enemyHealth)
			renderHPbar();
	}
}
