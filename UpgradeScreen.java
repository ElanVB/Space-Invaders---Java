public class UpgradeScreen extends Screen implements AddButtons
{
	public UpgradeScreen()
	{
		super("UpgradeScreen.png");
		addButtons(9, new String[] {"Fire Rate", "Bullet Damage", "HP", "Power-Ups", "Multi-shot", "Upgrade Bunkers", "Warp Drive", "Buy Lives x5", "Next Level"});
	}
	
	public void render()
	{
		super.render();
		Store.renderCosts();
	}
	
	public void addButtons(int numButtons, String[] types) 
	{
		buttons = new Button[numButtons];
		for (int j = 0; j < numButtons; j++) 
		{
			buttons[j] = new Button(States.WINDOW_CENTRE * 0.75, States.WINDOW_RESOLUTION*0.75*j/(double)numButtons + States.WINDOW_RESOLUTION/((double)(numButtons)*2), States.WINDOW_RESOLUTION/(double)1.5, States.WINDOW_RESOLUTION/(double)((numButtons + 1)*1.5), types[numButtons - j - 1]);
		}
	}
}
