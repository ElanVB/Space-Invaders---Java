public class WelcomeScreen extends Screen implements AddButtons
{
	public WelcomeScreen() 
	{
		super("welcomeBack.png");
		addButtons(4, new String[]{"Play Now!", "Options", "Instructions", "High-Scores"});
	}
	
	public void addButtons(int numButtons, String[] types) 
	{
		buttons = new Button[numButtons];
		for (int j = 0; j < numButtons; j++) 
		{
			buttons[j] = new Button(States.WINDOW_CENTRE, States.WINDOW_CENTRE*j/(double)numButtons + States.WINDOW_CENTRE/((double)(numButtons)*2), States.WINDOW_RESOLUTION/(double)2, States.WINDOW_CENTRE/(double)((numButtons + 1)), types[numButtons - j - 1]);
		}
	}
}