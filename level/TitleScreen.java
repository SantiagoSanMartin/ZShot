package ar.zgames.zshot.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import ar.zgames.zshot.system.InputHandler;
import ar.zgames.zshot.system.Main;
import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.system.SoundManager;
import ar.zgames.zshot.upgrades.Upgrades;

/**
 * Title Screen
 */
public class TitleScreen extends Level {
	private static final Font FONT1 = new Font("Verdana", Font.BOLD, 20); // Predefined font
	private static final Font FONT = new Font("Verdana", Font.BOLD, 40); // Predefined font
	private int selection; // Current selected option
	private boolean stageSelect; // Indicates player has selected "Stage Select" option
	private boolean introDone = false; // Indicates when Title intro is finished
	private int ticks; // Tick counter
	private final String version = "Beta 1.3"; // String to display ZShot version
	private int levelAvail; // Indicates which levels are available for stage select option
	private BufferedImage background; // Background image
	private static final BufferedImage TITLE_BG = ResourceManager.createCompatible(ResourceManager.getTitle()); // Title background image
	private static final BufferedImage STAGE_BG = ResourceManager.createCompatible(ResourceManager.getStarfield()); // Stage select background image
	private static final BufferedImage TITLE_BALL = ResourceManager.createCompatible(ResourceManager.getTitleBall()); // Ball image
	private static final BufferedImage TITLE_RAY = ResourceManager.createCompatible(ResourceManager.getTitleRay()); // Ray image
	private static final BufferedImage TITLE_Z1 = ResourceManager.createCompatible(ResourceManager.getTitleZ1()); // Z upper part
	private static final BufferedImage TITLE_Z2 = ResourceManager.createCompatible(ResourceManager.getTitleZ2()); // Z middle part
	private static final BufferedImage TITLE_Z3 = ResourceManager.createCompatible(ResourceManager.getTitleZ3()); // Z lower part
	private static final BufferedImage TITLE_SHOT = ResourceManager.createCompatible(ResourceManager.getTitleShot()); // SHOT text image
	private static final BufferedImage UNKNOWN = ResourceManager.createCompatible(ResourceManager.getUnknown()); // Deep Space image
	private static final BufferedImage DEEP_SPACE = ResourceManager.createCompatible(ResourceManager.getDeepSpace()); // Deep Space image
	private static final BufferedImage GREEN_DREAMS = ResourceManager.createCompatible(ResourceManager.getGreenDreams()); // Deep Space image
	private static final BufferedImage FINAL_SUNSET = ResourceManager.createCompatible(ResourceManager.getFinalSunset()); // Deep Space image
	private static final BufferedImage LEFT_ARROW = ResourceManager.createCompatible(ResourceManager.resize(ResourceManager.getLeftArrow(), 60, 60)); // arrow image
	private String[] options; // Current option string array
	private static final String[] TITLE_OPTIONS = {"New Game", "Stage Select", "Quit"}; // Title option texts
	private static final String[] STAGE_SELECT_OPTIONS = {"Back", "Deep Space", "Green Dreams", "Final Sunset"}; // Stage select option texts
	private static final String[] CONTROLS = {"CONTROLS", "WASD/Arrow keys:", "Mouse click:", "Shift:", "Q/E:", "P:", "Move", "Shoot", "Shield", "Toggle weapons", "Pause"};
	private static final String UNKNOWN_TEXT = "?????"; // Text to display when level is not available
	private static final String MUSIC_TEXT = "Music";
	private static final String SOUND_TEXT = "Sound";
	private static final Rectangle MUSIC_RECT = new Rectangle(1500, 50, 30, 30);
	private static final Rectangle SOUND_RECT = new Rectangle(1500, 100, 30, 30);
	
	/**
	 * Constructs a TitleScreen object
	 * @param input
	 * - InputHandler
	 * @param soundManager
	 * - SoundManager
	 * @param upgrades
	 * - Upgrades object
	 */
	public TitleScreen(InputHandler input, SoundManager soundManager, Upgrades upgrades) {
		super(input, soundManager);
		selection = 0;
		levelAvail = upgrades.getLevel();
		options = TITLE_OPTIONS;
		background = TITLE_BG;
		soundManager.stopMusic();
		soundManager.playMusic(0, false);
	}

	@Override
	public void tick() {
		if (!introDone) {
			ticks++;
			if (ticks == 500)
				soundManager.playCharging();
			if (ticks == 1300)
				soundManager.playCannon();
			if (ticks == 1530 || ticks == 1670 || ticks == 1810)
				soundManager.playChuing();
			if (input.menu.down) {
				introDone = true;
				input.menu.release();
			}
		} else {
			if(input.shoot.down){
				if(MUSIC_RECT.contains(input.getMX(), input.getMY())){
					soundManager.toggleMusic();
				}
				if(SOUND_RECT.contains(input.getMX(), input.getMY())){
					soundManager.toggleSound();
				}
				input.shoot.release();
			}
			if (input.down.down) {
				selection += 1;
				input.down.release();
				if (selection > options.length - 1)
					selection -= 1;
				else
					soundManager.playOption();
			}
			if (input.up.down) {
				selection -= 1;
				input.up.release();
				if (selection < 0)
					selection = 0;
				else
					soundManager.playOption();
			}
			if (input.menu.down) {
				input.menu.release();
				if (stageSelect) {
					switch (selection) {
					case 0: // Back
						stageSelect = false;
						options = TITLE_OPTIONS;
						background = TITLE_BG;
						soundManager.playChime();
						break;
					case 1:
						soundManager.playChime();
						Main.level = new LevelWon(soundManager, input, 0);
						break;
					case 2:
						if(levelAvail >= 1){
							soundManager.playChime();
							Main.level = new LevelWon(soundManager, input, 1);
						}else
							soundManager.playBuzzer();
						break;
					case 3:
						if(levelAvail >= 2){
							soundManager.playChime();
							Main.level = new LevelWon(soundManager, input, 2);
						}else
							soundManager.playBuzzer();
						break;
					case 4:
						break;
					case 5:
						break;
					}
				} else {
					soundManager.playChime();
					switch (selection) {
					case 0: // New Game
						Main.level = new StageStart(input, soundManager, 1);
						break;
					case 1: // Stage Select
						stageSelect = true;
						options = STAGE_SELECT_OPTIONS;
						background = STAGE_BG;
						selection = 0;
						break;
					case 2: // Quit
						Main.screen.getFullScreenWindow().dispatchEvent(
								new WindowEvent(Main.screen.getFullScreenWindow(),
												WindowEvent.WINDOW_CLOSING));
						break;
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if (!introDone) {
			//Title screen intro
			if (ticks > 500 && ticks < 1300) {
				int size = (ticks - 97) / 6;
				BufferedImage image = ResourceManager.resize(TITLE_BALL, size, size);
				g.drawImage(image, 483 - size / 2, 400 - size / 2, null);
			}
			if (ticks >= 1300 && ticks < 1530) {
				int length = (ticks - 1299) * 2;
				BufferedImage image = ResourceManager.resize(TITLE_RAY, length, 226);
				g.drawImage(image, 553, 290, null);
				image = ResourceManager.resize(TITLE_BALL, 200, 200);
				g.drawImage(image, 383, 300, null);
			}
			if (ticks >= 1530 && ticks < 1670) {
				int length = ticks - 1529;
				BufferedImage image = ResourceManager.resize(TITLE_RAY, 460, 226);
				g.drawImage(image, 553, 290, null);
				image = ResourceManager.resize(TITLE_BALL, 200, 200);
				g.drawImage(image, 383, 300, null);
				image = TITLE_Z1;
				g.setClip(385, 280, length, image.getHeight());
				g.drawImage(image, 385, 280, null);
			}
			if (ticks >= 1670 && ticks < 1810) {
				int length = ticks - 1669;
				BufferedImage image = ResourceManager.resize(TITLE_RAY, 460, 226);
				g.drawImage(image, 553, 290, null);
				image = ResourceManager.resize(TITLE_BALL, 200, 200);
				g.drawImage(image, 383, 300, null);
				image = TITLE_Z1;
				g.drawImage(image, 385, 280, null);
				image = TITLE_Z2;
				g.setClip(525 - length, 280, length, image.getHeight());
				g.drawImage(image, 385, 280, null);
			}
			if (ticks >= 1810 && ticks < 1960) {
				int length = ticks - 1800;
				BufferedImage image = ResourceManager.resize(TITLE_RAY, 460, 226);
				g.drawImage(image, 553, 290, null);
				image = ResourceManager.resize(TITLE_BALL, 200, 200);
				g.drawImage(image, 383, 300, null);
				image = TITLE_Z1;
				g.drawImage(image, 385, 280, null);
				image = TITLE_Z2;
				g.drawImage(image, 385, 280, null);
				g.setClip(375, 280, length, image.getHeight());
				image = TITLE_Z3;
				g.drawImage(image, 385, 280, null);
			}
			if (ticks >= 1960 && ticks < 2240){
				int length = ticks - 1959;
				BufferedImage image = ResourceManager.resize(TITLE_RAY, 460, 226);
				g.drawImage(image, 553, 290, null);
				image = ResourceManager.resize(TITLE_BALL, 200, 200);
				g.drawImage(image, 383, 300, null);
				image = TITLE_Z1;
				g.drawImage(image, 385, 280, null);
				image = TITLE_Z2;
				g.drawImage(image, 385, 280, null);
				image = TITLE_Z3;
				g.drawImage(image, 385, 280, null);
				image = ResourceManager.resize(TITLE_SHOT, 280, 80);
				g.setClip(640,350, length, 80);
				g.drawImage(image, 640, 350, null);
			}
			if (ticks >= 2240)
				introDone = true;
		} else {
			String msg;
			g.setFont(FONT1);
			if (!stageSelect) {

				//Paints logo
				BufferedImage image = ResourceManager.resize(TITLE_RAY, 460, 226);
				g.drawImage(image, 553, 290, null);
				image = ResourceManager.resize(TITLE_BALL, 200, 200);
				g.drawImage(image, 383, 300, null);
				image = TITLE_Z1;
				g.drawImage(image, 385, 280, null);
				image = TITLE_Z2;
				g.drawImage(image, 385, 280, null);
				image = TITLE_Z3;
				g.drawImage(image, 385, 280, null);
				image = ResourceManager.resize(TITLE_SHOT, 280, 80);
				g.drawImage(image, 640, 350, null);
				
				//Options
				for (int i = 0; i < options.length; i++) {
					g.setColor(Color.WHITE);
					msg = options[i];
					if (i == selection) {
						g.setColor(Color.CYAN);
						msg = "> " + msg + " <";
						g.drawString(msg, 665, 900 + i * 30);
					} else
						g.drawString(msg, 650, 900 + i * 30);
				}
				
				//Game version
				g.setColor(Color.RED);
				g.drawString(version, 600, 480);
				
				//Controls
				g.setColor(Color.WHITE);
				g.drawString(CONTROLS[0], 50, 1020);
				for(int i = 1; i < CONTROLS.length; i++){
					if(i < 6)
						g.drawString(CONTROLS[i], 50, 1020 + i * 30);
					else
						g.drawString(CONTROLS[i], 300, 1020 + (i-5) * 30);
				}
				
				//Toggle sound/music
				g.setColor(Color.MAGENTA);
				if(soundManager.isMusic())
					g.fillOval(1500, 50, 30, 30);
				if(soundManager.isSound())
					g.fillOval(1500, 100, 30, 30);
				
				g.setColor(Color.WHITE);
				g.drawOval(1500, 50, 30, 30);
				g.drawOval(1500, 100, 30, 30);
				
				g.setColor(Color.WHITE);
				g.drawString(MUSIC_TEXT, 1400, 70);
				g.drawString(SOUND_TEXT, 1400, 120);
			} else {

				// Paints level select screen
				g.setFont(FONT);
				
				// Back
				if (0 == selection) 
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.GRAY);
				g.drawString(options[0], 100, 100);
				g.drawImage(LEFT_ARROW, 30, 57, null);
				
				// Level 1
				if (1 == selection) 
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.GRAY);
				g.drawString(options[1], 1170, 400);
				g.drawImage(DEEP_SPACE, 1150, 100, null);
	
				
				// Level 2
				if (2 == selection) 
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.GRAY);
				if(levelAvail >= 1){
					g.drawString(options[2], 350, 500);
					g.drawImage(GREEN_DREAMS, 350, 200, null);
				}else{
					g.drawString(UNKNOWN_TEXT, 400, 500);
					g.drawImage(UNKNOWN, 400, 250, null);
				}
				
				// Level 3
				if (3 == selection) 
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.GRAY);
				if(levelAvail >= 2){
					g.drawString(options[3], 650, 800);
					g.drawImage(FINAL_SUNSET, 650, 500, null);
				}else{
					g.drawString(UNKNOWN_TEXT, 700, 800);
					g.drawImage(UNKNOWN, 700, 550, null);
				}
			}
		}
	}

	@Override
	protected void nextLevel() {
		// Do nothing
	}
}
