import java.awt.Color;
import java.awt.Font;
/**
 * This Class creates button objects
 * @author elan
 *
 */
public class Button 
{
	/**
	 * the x, y, height and width are all final private and final as they are never changed
	 * the background colour changes and is thus not final
	 * the caption is added after the object is created and thus cannot be final (the constructor conplains)
	 */
	protected final double x, y, height, width;
	private Color backGround = Color.BLACK;
	protected String text, caption;
	
	/**
	 * Constructor for Button
	 * @param x x co-ordinate where the button will be created
	 * @param y y co-ordinate where the button will be created
	 * @param w the width of the button
	 * @param h the height of the button
	 * @param t the string that will be displayed on the button
	 */
	public Button(double x, double y, double w, double h, String t)
	{
		this.x = x;
		this.y = y;
		height = h;
		width = w;
		text = t;
	}
	
	/**
	 * this will draw the button
	 */
	public void render()
	{
		StdDraw.setPenColor(backGround);
		StdDraw.filledRectangle(x, y, width/(double)2, height/(double)2);
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.setPenRadius((height/(double)3)*0.001);
		StdDraw.rectangle(x, y, width/(double)2, height/(double)2);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int) (width * 0.08)));//change to function of num of letters in button?
		
		if(caption == null)
			StdDraw.text(x, y - height/(double)10, text);
		else
		{
			StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int) (width * 0.06)));
			StdDraw.text(x, y - height/(double)10, text + ": $" + caption);
		}
	}
	
	/**
	 * this will color the background red
	 */
	private void highLight()
	{
		backGround = Color.RED;
	}
	
	/**
	 * this will add onto the text already displayed on the button
	 * @param caption string that will be added to the text
	 */
	public void addCaption(String caption)
	{
		this.caption = caption;
	}
	
	/**
	 * this will add onto the text already displayed on the button
	 * @param caption integer that will be added to the text
	 */
	public void addCaption(int caption)
	{
		this.caption = "" + caption;
	}
	
	/**
	 * checks if the mouse is on the button and highlights it if so
	 */
	public void checkMouse()
	{
		if(this.xInBounds() && this.yInBounds())
		{
			highLight();
		}
		else
		{
			backGround = Color.BLACK;
		}
	}
	
	/**
	 * Returns if the mouse pointer is within the x bounds of the button
	 * @return	true if mouses x bounds are within the buttons
	 */
	private boolean xInBounds()
	{
		return (StdDraw.mouseX() > x - width/((double)2) && StdDraw.mouseX() < x + width/(double)2);
	}
	
	/**
	 * Returns if the mouse pointer is within the y bounds of the button
	 * @return	true if mouses y bounds are within the buttons
	 */
	private boolean yInBounds()
	{
		return (StdDraw.mouseY() > y - height/((double)2) && StdDraw.mouseY() < y + height/(double)2);
	}
	
	/**
	 * decides based on the text that is on the button what it should do if it is clicked
	 */
	public void clicked()
	{
		Controller.soundManager.playClick();
		if(text.equals("Options"))
		{
			States.isOptionRunning = true;
			States.isWelcomeRunning = false;
		}
		else if(text.equals("Instructions"))
		{
			States.isInstructionsRunning = true;
			States.isWelcomeRunning = false;
		}
		else if(text.equals("Play Now!"))
		{
			States.isPlayRunning = true;
			States.isWelcomeRunning = false;
		}
		else if(text.equals("High-Scores"))
		{
			States.isHighScoreRunning = true;
			States.isWelcomeRunning = false;
		}
		else if(text.equals("Back"))
		{
			if(Controller.screen instanceof OptionScreen)
				States.isOptionRunning = false;
			else if(Controller.screen instanceof InstructionsScreen)
				States.isInstructionsRunning = false;
			else if(Controller.screen instanceof HighScoreScreen)
				States.isHighScoreRunning = false;
			
			States.isWelcomeRunning = true;
		}
		else if(text.equals("Next Level"))
		{
			States.isUpgradeRunning = false;
			States.isPlayRunning = true;
		}
		else if(text.equals("Fire Rate"))
		{
			Store.upgradeFireRate();
		}
		else if(text.equals("Bullet Damage"))
		{
			Store.upgradeBulletDamage();
		}
		else if(text.equals("HP"))
		{
			Store.upgradeHP();
		}
		else if(text.equals("Power-Ups"))
		{
			Store.upgradePowerUps();
		}
		else if(text.equals("Multi-shot"))
		{
			Store.upgradeMultiShot();
		}
		else if(text.equals("Upgrade Bunkers"))
		{
			Store.upgradeBunkers();
		}
		else if(text.equals("Buy Lives x5"))
		{
			Store.increaseLives();
		}
		else if(text.equals("Warp Drive"))
		{
			Store.slowEnemies();
		}
	}
	
	/**
	 * checks if the mouse is pressed while on the button
	 */
	public void isClicked()
	{
		if(this.xInBounds() && this.yInBounds())
		{
			clicked();
		}
	}
}
