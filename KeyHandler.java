import java.awt.event.KeyEvent;
/**
 * This Class handles all the key presses in my game
 * @author elan
 *
 */
public class KeyHandler
{
	/**
	 * checks the keyboard for input and handles it
	 */
	public static void processKey()
	{
		//movement
		if(StdDraw.isKeyPressed(KeyEvent.VK_A))//a
		{
			Controller.player.moveLeft(States.SHOOTER_MOVE_SPEED);
			
			if(!States.controlsKeyboard)
				Controller.player.setAngle();
			
			if(States.debug)
				System.out.println("KeyHandle: a");
		}

		if(StdDraw.isKeyPressed(KeyEvent.VK_D))//d
		{
			Controller.player.moveRight(States.SHOOTER_MOVE_SPEED);

			if(!States.controlsKeyboard)
				Controller.player.setAngle();
			
			if(States.debug)
				System.out.println("KeyHandle: d");
		}

		//rotation
		if(States.controlsKeyboard)
		{
			if(StdDraw.isKeyPressed(KeyEvent.VK_Q))//q
			{
				Controller.player.rotateAntiClockWise(States.SHOOTER_ROTATE_SPEED);

				if(States.debug)
					System.out.println("KeyHandle: q");
			}

			if(StdDraw.isKeyPressed(KeyEvent.VK_E))//e
			{
				Controller.player.rotateClockWise(States.SHOOTER_ROTATE_SPEED);

				if(States.debug)
					System.out.println("KeyHandle: e");
			}

			if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE))//space
			{
				Controller.player.shoot();

				if(States.debug)
					System.out.println("KeyHandle: space");
			}
		}

		if(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE))
		{
			Controller.exitConfirmation();
		}
	}
}