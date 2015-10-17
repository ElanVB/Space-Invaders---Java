/**
 * I store all my state variables in here
 * @author elan
 *
 */
public class States
{
  public static boolean debug = false;
  
  public static boolean isPlayRunning = false;
  public static boolean isWelcomeRunning = true;
  public static boolean isOptionRunning = false;
  public static boolean isUpgradeRunning = false;
  public static boolean isInstructionsRunning = false;
  public static boolean isHighScoreRunning = false;
  public static boolean controlsKeyboard = true;
  public static boolean mouseClicked = false;
  public static boolean canShoot = true;
  public static boolean pause = false;
  public static boolean gameOver = false;
  
  public static final double WINDOW_RESOLUTION = 600;
  public static final double WINDOW_CENTRE = WINDOW_RESOLUTION/2;
  public static final double SHOOTER_SIZE = WINDOW_RESOLUTION * 0.1;
  public static final double SHOOTER_START_X = WINDOW_CENTRE;
  public static final double SHOOTER_START_Y = (WINDOW_RESOLUTION/(double)10) - 30;
  public static final double SHOOTER_MOVE_SPEED = WINDOW_RESOLUTION/(double)70;
  public static final double SHOOTER_ROTATE_SPEED = 8;
  public static final double BULLET_SPEED = SHOOTER_MOVE_SPEED * 3;
  public static final double BULLET_SIZE = SHOOTER_SIZE / (double) 2;
  public static final double BUNKER_SPEED = SHOOTER_MOVE_SPEED/(double)6;
  public static final double BUNKER_HEIGHT = SHOOTER_SIZE/2.5;
  public static final double BOSS_SIZE = SHOOTER_SIZE * 3;
  
  public static final int FPS = 50;
  public static final int PAUSE_TIME = 3000;
  
  public static int fireRate = 500;
  public static int playerDamage = 10;
  public static int enemyDamage = 4;
  public static int enemyShootDelay = 1500;
  public static int enemyHealth = 10;
  public static int[] playerLives = {3, 3};
  public static int powerUpDuration = 5000;
  public static int powerUpSpawnDelay = 3000;
  public static int numberBulletsPlayerShoots = 1;
  public static int playerHP = 10;
  public static int bulletPhaseChance = 1;
  public static int bunkerHP = 50;
  
  public static double enemySpeed = SHOOTER_MOVE_SPEED * 0.5;
  public static double enemyBulletSpeed = enemySpeed*1.5;
  public static double enemySize = SHOOTER_SIZE * 0.9;
  public static double bunkerWidth = States.WINDOW_CENTRE/(double)3;
}