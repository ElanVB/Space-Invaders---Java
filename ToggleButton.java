/**
 * this is an extension of the button class to allow the button to have states, 
 * an example would be the top button in the option screen, 
 * it swaps between two states > keyboard and mouse
 * @author elan
 *
 */
public class ToggleButton extends Button
{
	private String[] states;
	private String mainText;
	private int currentState = 0;
	
	public ToggleButton(double x, double y, double w, double h, String t)
	{
		super(x, y, w, h, t);
		mainText = t;
		text = mainText + ": " + states[currentState];
	}
	
	public ToggleButton(Button button)
	{
		super(button.x, button.y, button.width, button.height, button.text);
	}

	public ToggleButton(double x, double y, double w, double h, String t, String[] states)
	{
		super(x, y, w, h, t);
		
		this.states = new String[states.length];

		for (int i = 0; i < states.length; i++)
			this.states[i] = states[i];
		
		mainText = t;
		setText();
	}
	
	public void addStates(String[] states)
	{
		this.states = new String[states.length];

		for (int i = 0; i < states.length; i++)
			this.states[i] = states[i];
		
		mainText = text;
		setText();
	}
	
	private void toggle()
	{
		currentState++;
		
		if(currentState >= states.length)
			currentState = 0;
		
		text = mainText + ": " + states[currentState];
	}
	
	public void clicked()
	{
		toggle();
		
		if(mainText.equals("Controls"))
		{
			States.controlsKeyboard = !States.controlsKeyboard;
		}
	}
	
	public void checkStates()
	{
		if(mainText.equals("Controls"))
		{
			if(!States.controlsKeyboard && currentState != 1)
			{
				currentState = 1;
				setText();
			}
		}
	}
	
	private void setText()
	{
		text = mainText + ": " + states[currentState];
	}
}
