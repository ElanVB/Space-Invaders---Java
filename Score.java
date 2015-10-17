import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
/**
 * This Class Handles everything to do with the score, 
 * it keeps track of it while you play,
 * it saves the high scores as you go along,
 * it displays the high scores in the high score screen
 * @author elan
 *
 */
public class Score 
{
	/**
	 * Some static class variables
	 */
	private static int score;
	private static int interumScore;
	private static Integer[] highscores;

	/**
	 * increases the score based on a number of factors, 
	 * including current health level of the player and 
	 * distance of the enemy away from the bottom of the screen, 
	 * as well as type of enemy and the difficulty index for the level
	 */
	public static void increaseScore(Shooter player, Enemy enemy)
	{
		if(enemy instanceof Boss)
			interumScore += ((enemy.getY()/(double)States.WINDOW_RESOLUTION) * 200) + player.getHP() + Level.difficultyIndex()*400;
		else if(enemy instanceof LockOnEnemy)
			interumScore += ((enemy.getY()/(double)States.WINDOW_RESOLUTION) * 200) + player.getHP() + Level.difficultyIndex()*10;
		else
			interumScore += ((enemy.getY()/(double)States.WINDOW_RESOLUTION) * 200) + player.getHP() + Level.difficultyIndex();
	}

	/**
	 * if the player has passed the level successfully
	 * the score is taken into permanent affect, 
	 * otherwise it is reset for the level
	 */
	public static void consolidateScore()
	{
		score += interumScore;
		resetInterumScore();
		
		if(score > highscores[0])
		{
			highscores[0] = score;
			updateHighScores();
		}
	}

	/**
	 * @return the current score
	 */
	public static int getScore()
	{
		return interumScore;
	}

	/**
	 * Draws the score on the screen
	 */
	public static void render()
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int) (States.WINDOW_RESOLUTION*0.04)));
		StdDraw.text(States.WINDOW_CENTRE, States.WINDOW_RESOLUTION - States.WINDOW_RESOLUTION/(double)30, "Score: " + (score + interumScore));
	}

	/**
	 * resets the score
	 */
	public static void resetScore()
	{
		score = 0;
	}

	/**
	 * resets the temparary score for the level
	 */
	public static void resetInterumScore()
	{
		interumScore = 0;
	}

	/**
	 * displays the high scores on the screen
	 */
	public static void displayHighScores()
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int) (States.WINDOW_RESOLUTION*0.04)));
		
		for(int i = 0; i < 10; i++)
			StdDraw.text(States.WINDOW_CENTRE, States.WINDOW_RESOLUTION - i*(States.WINDOW_RESOLUTION/(double)15) - States.WINDOW_RESOLUTION/(double)6, "" + (i + 1) + ". " + highscores[9 - i]);
	}

	/**
	 * loads the high scores from the file
	 * (or makes it if it doesnt exist)
	 */
	public static void loadHighScores()
	{
		highscores = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int counter = 0;
		
		try 
		{
			Scanner read = new Scanner(new File("highscores.txt"));
			
			while(read.hasNext() && counter < 10)
				highscores[counter++] = read.nextInt();
			
			read.close();
			
			sort();
		} 
		catch (FileNotFoundException e) 
		{
			updateHighScores();
		}
	}

	/**
	 * writes the new highscores to the file highscores.txt
	 */
	public static void updateHighScores()
	{
		sort();
		
		try 
		{
			PrintWriter write = new PrintWriter("highscores.txt");
			
			for(int i = 0; i < 10; i++)
				write.println(highscores[i]);

			write.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * sorts the high scores
	 */
	public static void sort()
	{
		Quick.sort(highscores);
	}
}
