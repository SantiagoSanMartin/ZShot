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
 * Class that handles Game Over event
 */
public class GameOver extends Level{
	
	private BufferedImage background = ResourceManager.createCompatible(ResourceManager.getTitle()); // Background for Game Over
	private static final Font FONT = new Font("verdana", Font.PLAIN, 60); // Predefined Font
	private static final String GAME_OVER = "Game Over"; // String to display "Game Over"
	
	/**
	 * Constructs a new GameOver object
	 * @param soundManager
	 * - Reference to SoundManager object
	 * @param input
	 * - Reference to InputManager object
	 */
	public GameOver(SoundManager soundManager, InputHandler input){
		super(input, soundManager);
		soundManager.stopMusic();
		soundManager.playMusic(5, false);
	}
	
	@Override
	public void tick() {
		if (fadeIn > 0)
			--fadeIn;
		if(input.menu.down){
			input.menu.release();
			nextLevel();
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		g.drawImage(background, 0, 0, null);
		g.setColor(Color.RED);
		g.setFont(FONT);
		g.drawString(GAME_OVER, ScreenManager.getWidth()/2 - 160, ScreenManager.getHeight()/2 - 30);
		if (fadeIn > 0) {
			g.setColor(COLORS[fadeIn]);
			g.fillRect(0, 0, ScreenManager.getWidth(),
					ScreenManager.getHeight());
		}
	}

	@Override
	protected void nextLevel() {
		Main.level = new TitleScreen(input, soundManager, Main.getUpgrades());
	}
}
