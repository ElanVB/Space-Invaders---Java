/**
 * This Class handles all the interactive menues and Gui for my game,
 * It detects where the mouse is and conveys this to my other classes, 
 * It displayes the backgrounds and buttons
 * @author elan
 *
 */
public class Screen 
{
	protected Button[] buttons;
	public static Point mouse;
	protected String filename;

	public Screen(String filename) 
	{
		this.filename = filename;
		mouse = new Point(StdDraw.mouseX(), StdDraw.mouseY());
	}

	public void setBackGround()
	{
		StdDraw.picture(States.WINDOW_CENTRE, States.WINDOW_CENTRE, filename, States.WINDOW_RESOLUTION, States.WINDOW_RESOLUTION);
	}

	public void render()
	{
		setBackGround();

		if(buttons != null)
			for (int i = 0; i < buttons.length; i++) 
				buttons[i].render();
	}

	public void checkMouseActivity()
	{
		//if mouse x or y changes then check mouse for buttons
		if(mouse.getX() != StdDraw.mouseX() || mouse.getY() != StdDraw.mouseY())
		{
			if(buttons != null)
				for (int i = 0; i < buttons.length; i++) 
					buttons[i].checkMouse();
				
			mouse = new Point(StdDraw.mouseX(), StdDraw.mouseY());
			
			if(this instanceof PlayScreen && !States.controlsKeyboard)
			{
				Controller.player.setAngle();
			}
		}
		
		if(!StdDraw.mousePressed())
			States.mouseClicked = false;

		if(StdDraw.mousePressed())
		{
			if(!States.mouseClicked)
				if(buttons != null)
					for (int i = 0; i < buttons.length; i++) 
						buttons[i].isClicked();
			
			if(this instanceof PlayScreen && !States.controlsKeyboard)
				Controller.player.shoot();
			
			States.mouseClicked = true;
		}
	}
}
