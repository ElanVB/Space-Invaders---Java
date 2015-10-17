public class PlayScreen extends Screen
{
	public PlayScreen()
	{
		super(determineBack());
	}
	
	private static String determineBack()
	{
		int rand = (int)(Math.random()*5);
		
		if(rand == 0)
			return "PlayBack.jpg";
		else if(rand == 1)
			return "PlayBack1.jpg";
		else if(rand == 2)
			return "PlayBack2.jpg";
		else if(rand == 3)
			return "PlayBack3.jpg";
		
		return "PlayBack4.jpg";
	}
	
	public void setBackGround()
	{
		StdDraw.picture(States.WINDOW_CENTRE - States.WINDOW_RESOLUTION*0.3 + (Controller.player.getX()/States.WINDOW_RESOLUTION)*0.6*States.WINDOW_RESOLUTION, States.WINDOW_CENTRE, filename, States.WINDOW_RESOLUTION*2, States.WINDOW_RESOLUTION);
	}
}