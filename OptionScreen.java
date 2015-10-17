

public class OptionScreen extends Screen implements AddButtons
{
	public OptionScreen()
	{
		super("OptionScreen.png");
		addButtons(2, new String[] {"Controls", "Back"});
	}
	
	public void addButtons(int numButtons, String[] types) 
	{
		buttons = new Button[numButtons];
		for (int j = 0; j < numButtons; j++) 
			buttons[j] = new Button(States.WINDOW_CENTRE, States.WINDOW_RESOLUTION*j/(double)numButtons + States.WINDOW_RESOLUTION/((double)(numButtons)*2), States.WINDOW_RESOLUTION/(double)2, States.WINDOW_RESOLUTION/(double)((numButtons + 1)*2), types[numButtons - j - 1]);
	
		buttons[1] = new ToggleButton(buttons[1]);
		((ToggleButton)buttons[1]).addStates(new String[] {"Keyboard", "Mouse"});
		((ToggleButton)buttons[1]).checkStates();
	}
}
