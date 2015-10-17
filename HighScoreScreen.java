
public class HighScoreScreen extends Screen implements AddButtons
{
	public HighScoreScreen()
	{
		super("HighScoreScreen.png");
		addButtons(1, new String[] {"Back"});
	}
	
	public void render()
	{
		super.render();
		Score.displayHighScores();
	}
	
	public void addButtons(int numButtons, String[] types) 
	{
		buttons = new Button[numButtons];
		for (int j = 0; j < numButtons; j++) 
		{
			buttons[j] = new Button(States.WINDOW_CENTRE, States.WINDOW_CENTRE/((double)6), States.WINDOW_RESOLUTION/(double)3, States.WINDOW_CENTRE/(double)6, types[numButtons - j - 1]);
		}
	}
}
