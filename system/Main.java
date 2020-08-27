package ar.zgames.zshot.system;

import java.awt.Graphics2D;

import ar.zgames.zshot.level.Level;
import ar.zgames.zshot.level.TitleScreen;
import ar.zgames.zshot.upgrades.Upgrades;

/**
 * ZShot - Side shoot'em up game
 * @author Zanma
 * @version BETA 1.3
 */

public class Main {

	public static Level level;				// Reference to current level
	public static ScreenManager screen;		// Reference to ScreenManager object
	private static Upgrades upgrades;		// Reference to Upgrades object

	/**
	 * Creates game instance
	 * @param args
	 */
	public static void main(String[] args) {
		Main game = new Main();
		game.run();   
	}

	/**
	 * Main process
	 */
	private void run() {
		initialize();
		gameLoop();
	}

	/**
	 * Initialize system:
	 *<br> - Screen Manager
	 *<br> - Input Handler
	 *<br> - Sound Manager
	 *<br> - Title Screen
	 */
	private void initialize() {
		screen = new ScreenManager();
        InputHandler input = new InputHandler(screen.getFullScreenWindow());
        SoundManager soundManager = new SoundManager();
        SaveManager saveManager = new SaveManager();
        upgrades = saveManager.load();
        Main.level = new TitleScreen(input, soundManager, upgrades);
	}

	/**
	 * Starts the game loop. 
	 */
	private void gameLoop() {
		long prevTime = System.nanoTime();							// Stores the last time the system checked for game tick processing
		double unprocessedTicks = 0d;								// Number of ticks that should be processed according to time passed
		double nsPerTick = 1000000000d / 240d;						// Number of nanoseconds that should pass between game ticks
		boolean shouldRender = true;								// Indicates the game process the screen needs to be redrawn
		int ticks = 0;
		long frameCheckTime = System.currentTimeMillis();
		int frames = 0;
		while(true) {
			long currTime = System.nanoTime();						// Gets current time in nanoseconds
			unprocessedTicks += (currTime - prevTime) / nsPerTick;	// Calculates number of ticks that should be processed
			prevTime = currTime;		
			while (unprocessedTicks >= 1) {							// If there are unprocessed ticks, process them
				if(unprocessedTicks > 10d){
					unprocessedTicks = 1;
				}
				level.tick();
				unprocessedTicks -= 1;
				ticks++;
				shouldRender = true;
			}
			if (shouldRender) {										// If a tick was processed, render
				render();
				frames++;
				shouldRender = false;
			}
			if (System.currentTimeMillis() - frameCheckTime > 1000L) { // Show ticks/second and fps on console every second
				frameCheckTime += 1000L;
				System.out.println(ticks + " ticks/second, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}

	/**
	 * Draws the screen
	 */
	private void render() {
		Graphics2D g = screen.getGraphics();
		level.draw(g);								
        g.dispose();
        screen.update();
	}

	/**
	 * Returns the Upgrades object
	 * @return
	 * Upgrades object
	 */
	public static Upgrades getUpgrades() {
		return upgrades;
	}
}
