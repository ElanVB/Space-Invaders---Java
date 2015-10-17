/**
 * An interface that lets screens be able to add interactive buttons to them
 * @see Screen
 * @author elan
 *
 */
public interface AddButtons 
{
	/**
	 * Adds the Buttons to any class that implements this interface
	 * @param numButtons The number of buttons you would like the child to have
	 * @param types The strings you would like displayed on each button respectively
	 */
	void addButtons(int numButtons, String[] types);
}
