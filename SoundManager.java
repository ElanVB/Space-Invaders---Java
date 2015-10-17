
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * This is my class I use to play my sound in my game, 
 * all music and sound effects are handled here
 * @author elan
 *
 */
public class SoundManager implements Runnable
{
	/**
	 * Some private class variables that store the audio that is going to be played
	 */
	private static boolean isRunning = false;
	private static boolean first = true;
	private static AudioInputStream audioIn;
	private static Clip currentSong;
	private static Clip currentSound;
	
	/**
	 * Constructor for the SoundManager Class
	 */
	public SoundManager()
	{
		isRunning = true;
		new Thread(this).start();
	}
	
	/**
	 * One of the most important methods in SoundManager, 
	 * it takes the file name of the sound file you want played and plays it, 
	 * this should stricktly be used for short sound effects
	 * @param filename 
	 */
	private void play(String filename)
	{
		try
		{
			audioIn = AudioSystem.getAudioInputStream(new File(filename));
			currentSound = AudioSystem.getClip();
			currentSound.open(audioIn);
			currentSound.start();
			audioIn = null;
			currentSound = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * One of the most important methods in SoundManager, 
	 * it takes the file name of the sound file you want played and plays it,
	 * Should be used for music
	 * @param filename
	 * @param songLength in mili-seconds
	 */
	private void loop(String filename, long songLength)
	{
		try
		{
			audioIn = AudioSystem.getAudioInputStream(getClass().getResource(filename));
			currentSong = AudioSystem.getClip();
			currentSong.open(audioIn);
			currentSong.start();
			Thread.sleep(songLength);
			audioIn = null;
			currentSong = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * playes one of the two songs that are pre loaded in
	 */
	public void playGameMusic()
	{
		if((int)(Math.random()*2)%2 == 0)
			loop("inGame.wav", 506000);
		else
			loop("inGame2.wav", 279000);
	}
	
	public void playMenuMusic()
	{
		loop("menues.wav", 133000);
	}
	
	public void playShoot()
	{
		play("shoot.wav");
	}
	
	public void playClick()
	{
		play("click.wav");
	}
	
	public void playPowerUp()
	{
		play("power-up.wav");
	}
	
	public void playExplode()
	{
		play("explode.wav");
	}
	
	public void run()
	{
		while(isRunning)
		{
			if(first)
			{
				playMenuMusic();
				first = false;
			}
			else
				playGameMusic();
			try
			{
				Thread.sleep(1000/(States.FPS*2));
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
