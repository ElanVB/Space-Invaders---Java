/**
 * This Class manages all the unique identifiers of my objects, 
 * it makes sure they are all unique and in order
 * @author elan
 *
 */
public class Index 
{
	/**
	 * Updates the Bullets identifiers
	 */
	public static void updateBulletIndex()
	{
		for(int i = Controller.bullets.size() - 1; i >= 0; i--)
			if(Controller.bullets.get(i).getIndex() != i)
				Controller.bullets.get(i).updateIndex(i);
			else
				break;
	}

	/**
	 * Updates the Enemies identifiers
	 */
	public static void updateEnemyIndex()
	{
		for(int i = Controller.enemies.size() - 1; i >= 0; i--)
			if(Controller.enemies.get(i).getIndex() != i)
				Controller.enemies.get(i).updateIndex(i);
			else
				break;
	}

	/**
	 * Updates the EnemyBullets identifiers
	 */
	public static void updateEnemyBulletIndex()
	{
		for(int i = Controller.enemyBullets.size() - 1; i >= 0; i--)
			if(Controller.enemyBullets.get(i).getIndex() != i)
				Controller.enemyBullets.get(i).updateIndex(i);
			else
				break;
	}
	
	/**
	 * Updates the PowerUpBoxes identifiers
	 */
	public static void updatePowerUpBoxesIndex()
	{
		for(int i = Controller.powerUpBoxes.size() - 1; i >= 0; i--)
			if(Controller.powerUpBoxes.get(i).getIndex() != i)
				Controller.powerUpBoxes.get(i).updateIndex(i);
			else
				break;
	}
	
	/**
	 * Updates the Bunkers identifiers
	 */
	public static void updateBunkerIndex()
	{
		for(int i = Controller.bunkers.size() - 1; i >= 0; i--)
			if(Controller.bunkers.get(i).getIndex() != i)
				Controller.bunkers.get(i).updateIndex(i);
			else
				break;
	}
}
