package ar.zgames.zshot.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ar.zgames.zshot.system.InputHandler;
import ar.zgames.zshot.system.Main;
import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.system.ScreenManager;
import ar.zgames.zshot.system.SoundManager;

/**
 * Handles a Stage start event
 */
public class StageStart extends Level {

	private static final String ENTERING = "Now entering..."; // String to display "Now entering..."
	private static final Font FONT1 = new Font("helvetica", Font.BOLD, 50); // Predefined Font
	private static final Font FONT2 = new Font("helvetica", Font.ITALIC, 50); // Predefined Font
	private String zone; // Contains the string for the level name
	private int level; // Level id
	private BufferedImage backImage; // Background image
	
	/**
	 * Constructs a StageStart object
	 * @param input
	 * - InputHandler
	 * @param soundManager
	 * - SoundManager
	 * @param level
	 * - Level id
	 */
	public StageStart(InputHandler input, SoundManager soundManager, int level) {
		super(input, soundManager);
		this.level = level;
		switch(level){
		case 1:
			backImage = ResourceManager.getStage1Intro();
			zone = "Deep Space";
			break;
		case 2:
			backImage = ResourceManager.getStage2Intro();
			zone = "Green Dreams";
			break;
		case 3:
			backImage = ResourceManager.getStage3Intro();
			zone = "Final Sunset";
			break;
		}
		soundManager.stopMusic();
		soundManager.playMusic(3, false);
	}

	@Override
	public void tick() {
		++ticks;
		if (fadeIn > 0)
			--fadeIn;
		if(ticks == 2000)
			fadeOut++;
		if (fadeOut == 255)
			nextLevel();
	}
	
	@Override
	public void draw(Graphics2D g){
		int posX = ticks;
		int length = ticks/3;
		if(ticks > 500)
			posX = 500;
		
		g.drawImage(backImage, 0, 0, null);
		g.setFont(FONT1);
		g.setColor(Color.CYAN);
		g.drawString(ENTERING, posX, 300);
		g.setFont(FONT2);
		g.setClip(400, 300, length, 180);
		g.setColor(Color.MAGENTA);
		g.drawString(zone, 650, 400);
		g.setClip(0, 0, ScreenManager.getWidth(), ScreenManager.getHeight());
		
		if (fadeIn > 0) {
			g.setColor(COLORS[fadeIn]);
			g.fillRect(0, 0, ScreenManager.getWidth(),
					ScreenManager.getHeight());
		}
		if (fadeOut != 0) {
			if (fadeOut > 255)
				fadeOut = 255;
			g.setColor(COLORS[fadeOut++]);
			g.fillRect(0, 0, ScreenManager.getWidth(),
					ScreenManager.getHeight());
		}
	}
	
	@Override
	protected void nextLevel() {
		switch(level){
		case 1:
			Main.level = new GameLevel(input, soundManager, Levels.LEVEL1, Main.getUpgrades());
			break;
		case 2:
			Main.level = new GameLevel(input, soundManager, Levels.LEVEL2, Main.getUpgrades());
			break;
		case 3:
			Main.level = new GameLevel(input, soundManager, Levels.LEVEL3, Main.getUpgrades());
			break;
		}
	}

}
