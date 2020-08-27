package ar.zgames.zshot.level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ar.zgames.zshot.actor.Actor;
import ar.zgames.zshot.actor.Bullet;
import ar.zgames.zshot.actor.Effect;
import ar.zgames.zshot.actor.Enemy;
import ar.zgames.zshot.actor.Player;
import ar.zgames.zshot.animations.Animation;
import ar.zgames.zshot.background.Background;
import ar.zgames.zshot.system.InputHandler;
import ar.zgames.zshot.system.Main;
import ar.zgames.zshot.system.ObjectPool;
import ar.zgames.zshot.system.ScreenManager;
import ar.zgames.zshot.system.SoundManager;
import ar.zgames.zshot.upgrades.PowerUp;
import ar.zgames.zshot.weapons.Weapon;

/**
 * Class that handles level processing
 */
public abstract class Level {

	protected static final Color[] COLORS = new Color[256]; // Color array with alpha value
	protected int x; // Level width
	protected int y; // Level height
	protected Player player; // Player object
	protected Background background; // Level background object
	protected Console console; // Console object
	protected SoundManager soundManager; // SoundManager object
	protected ArrayList<Enemy> enemies; // Enemy array
	protected ArrayList<Bullet> bullets; // Bullet array
	private ArrayList<Effect> effects; // Effect array
	private ArrayList<PowerUp> powerUps; // PowerUp array
	private boolean gameOver; // Indicates when player loses
	private boolean win; // Indicates when player wins
	protected InputHandler input; // InputHandler object
	protected int fadeOut; // Indicates the screen fade out value when level ends
	protected int fadeIn; // Indicates the screen fade in value when the level begins
	protected int ticks; // Tick counter
	protected Actor boss; // Boss object
	protected ObjectPool objectPool; // ObjectPool object

	/**
	 * Constructs a new Level object
	 * @param input
	 * - InputHandler object
	 * @param soundManager
	 * - SoundManager object
	 */
	public Level(InputHandler input, SoundManager soundManager) {
		this.input = input;
		this.soundManager = soundManager;
		ticks = 0;
		fadeIn = 255;
		win = false;
		gameOver = false;
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		effects = new ArrayList<Effect>();
		powerUps = new ArrayList<PowerUp>();
		objectPool = new ObjectPool();
		Weapon.setObjectPool(objectPool);
		Animation.setObjectPool(objectPool);
		if (COLORS[0] == null) {
			for (int i = 0; i <= 255; i++) {
				COLORS[i] = new Color(255, 255, 255, i);
			}
		}
	}

	/**
	 * Process the level main tick
	 */
	public void tick() {
		++ticks;
		if (fadeOut == 255) {
			if (gameOver)
				Main.level = new GameOver(soundManager, input);
			else
				nextLevel();
		}
		objectsTick();
		remove();
		if (fadeIn > 0)
			--fadeIn;
	}

	/**
	 * Process the level's objects' individual ticks
	 */
	private void objectsTick() {
		background.tick();
		player.tick();
		
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isRemoved())
				continue;
			enemies.get(i).tick();
			enemies.get(i).checkCollisions(player);
		}

		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isRemoved())
				continue;
			bullets.get(i).tick();
			bullets.get(i).checkCollisions(enemies, player);
		}

		for (int i = 0; i < effects.size(); i++) {
			if (effects.get(i).isRemoved())
				continue;
			effects.get(i).tick();
		}

		for (int i = 0; i < powerUps.size(); i++) {
			if (powerUps.get(i).isRemoved())
				continue;
			powerUps.get(i).tick();
		}
	}

	/**
	 * Checks each object to determine which objects need to be removed from arrays
	 */
	private void remove() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isRemoved()) {
				enemies.get(i).setAvailable();
				enemies.remove(i--);
			}
		}

		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isRemoved()) {
				bullets.get(i).setAvailable();
				bullets.remove(i--);
			}
		}

		for (int i = 0; i < effects.size(); i++) {
			if (effects.get(i).isRemoved()) {
				effects.get(i).setAvailable();
				effects.remove(i--);
			}
		}

		for (int i = 0; i < powerUps.size(); i++) {
			if (powerUps.get(i).isRemoved())
				powerUps.remove(i--);
		}
	}

	/**
	 * Draws the level
	 * @param g
	 * - Graphics2D
	 */
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ScreenManager.getHeight(), ScreenManager.getWidth());
		background.draw(g);
		player.draw(g);

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		if(boss != null && !boss.isRemoved())
			boss.draw(g);

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}

		for (int i = 0; i < effects.size(); i++) {
			effects.get(i).draw(g);
		}

		for (int i = 0; i < powerUps.size(); i++) {
			powerUps.get(i).draw(g);
		}

		getConsole().draw(g);
		if (fadeIn > 0) {
			g.setColor(COLORS[fadeIn]);
			g.fillRect(0, 0, ScreenManager.getWidth(),
					ScreenManager.getHeight());
		}
		if (gameOver || win) {
			if (fadeOut > 255)
				fadeOut = 255;
			g.setColor(COLORS[fadeOut++]);
			g.fillRect(0, 0, ScreenManager.getWidth(),
					ScreenManager.getHeight());
		}
	}

	/**
	 * Adds the specified Bullet object to the Bullet array
	 * @param b
	 * - Bullet object to be added
	 */
	public void addBullet(Bullet b) {
		bullets.add(b);
	}

	/**
	 * Adds the specified Effect object to the Effect array
	 * @param e
	 * - Effect object to be added
	 */
	public void addEffect(Effect e) {
		effects.add(e);
	}

	/**
	 * Adds the specified Enemy object to the Enemy array
	 * @param e
	 * - Enemy object to be added
	 */
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	/**
	 * Adds the specified PowerUp object to the Power Up array
	 * @param p
	 * - PowerUp object to be added
	 */
	public void addPowerUp(PowerUp p) {
		powerUps.add(p);
	}

	/**
	 * Sets the gameOver variable as true to indicate the player has lost the game
	 */
	public void gameOver() {
		gameOver = true;
	}

	/**
	 * Sets the win variable as true to indicate the player has won the level
	 */
	public void win() {
		win = true;
	}

	/**
	 * Returns the reference to the Player object
	 * @return
	 * Player object
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the reference to the Console object
	 * @return
	 * Console object
	 */
	public Console getConsole() {
		return console;
	}

	/**
	 * Returns the level's width
	 * @return
	 * Level's width
	 */
	public int getWidth() {
		return x;
	}

	/**
	 * Returns the level's height
	 * @return
	 * Level's height
	 */
	public int getHeight() {
		return y;
	}

	/**
	 * Returns the reference to the ObjectPool
	 * @return
	 * Reference to the ObjectPool
	 */
	public ObjectPool getObjectPool() {
		return objectPool;
	}
	
	/**
	 * Handles level transitions
	 */
	protected abstract void nextLevel();
	
	public ArrayList<Bullet> getBullets(){
		return bullets;
	}
}
