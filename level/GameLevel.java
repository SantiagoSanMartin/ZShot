package ar.zgames.zshot.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import ar.zgames.zshot.actor.Boss1;
import ar.zgames.zshot.actor.Boss2;
import ar.zgames.zshot.actor.Boss3;
import ar.zgames.zshot.actor.Player;
import ar.zgames.zshot.system.InputHandler;
import ar.zgames.zshot.system.Main;
import ar.zgames.zshot.system.ScreenManager;
import ar.zgames.zshot.system.SoundManager;
import ar.zgames.zshot.upgrades.Upgrades;

/**
 * Level manager class
 */
public class GameLevel extends Level {

	private static final String WARNING = "WARNING"; // String to display "WARNING" before boss appears
	private static final String PAUSED_TEXT = "Paused: press P to resume"; // String to display while game is paused
	private static final Font FONT = new Font("helvetica", Font.BOLD, 50); // Predefined Font
	private LevelEvent[] events; // Level events array
	private int currEvent; // Current level event
	private int totalEvents; // Total number of events before boss
	private int level; // Level number
	private int bossIntro; // Boss intro counter in ticks
	private int length; // Length for boss' HP bar
	protected boolean paused; // Indicates when the game is paused

	/**
	 * Constructs a new GameLevel object from an enum Levels constant
	 * @param input
	 * - InputHandler object
	 * @param soundManager
	 * - SoundManager object
	 * @param level
	 * - enum Levels constant
	 * @param upgrades
	 * - Upgrades object
	 */
	GameLevel(InputHandler input, SoundManager soundManager, Levels level, Upgrades upgrades) {
		super(input, soundManager);
		paused = false;
		player = new Player(0, 0, input, this, upgrades);
		background = level.getBackground();
		background.setLevel(this);
		console = new Console(player);
		x = ScreenManager.getWidth();
		y = ScreenManager.getHeight() - getConsole().getHeight();
		this.level = level.getId();
		events = level.getEvents();
		totalEvents = events.length;
		currEvent = 0;
		events[currEvent].start(player, this);
		soundManager.stopMusic();
		switch(this.level){
		case 1:
			soundManager.playMusic(1, true);
			break;
		case 2:
			soundManager.playMusic(7, true);
			break;
		case 3:
			soundManager.playMusic(9, true);
			break;
		}
	}

	@Override
	public void tick() {
		if(!paused){
			super.tick();
			if (boss == null) {
				if (currEvent + 1 != totalEvents) {
					if (ticks == events[currEvent].getEndTick() || enemies.size() == 0) {
						++currEvent;
						events[currEvent].start(player, this);
						ticks = 0;
					} 
				} else if (enemies.size() == 0 && bossIntro == 0)
					createBoss();
			} else if (bossIntro == 3000) {
				length = boss.getHitPoints();
				switch (level) {
				case 1:
					soundManager.playMusic(2, true);
					break;
				case 2:
					soundManager.playMusic(8, true);
					break;
				case 3:
					soundManager.playMusic(10, true);
					break;
				}
				bossIntro++;
			} else if (bossIntro > 3000) {
				boss.tick();
			} else {
				bossIntro++;
				if (bossIntro % 250 == 100)
					soundManager.playWarning();
			}
		}
		if(input.pause.down){
			input.pause.release();
			paused = !paused;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		if (boss != null){
			int hpBar;
			g.setColor(Color.RED);
			if(bossIntro <= 3000) {
				if(bossIntro % 250 > 100){
					g.setFont(FONT);
					g.drawString(WARNING, 600, 100);
				}
				hpBar = (bossIntro * 900)/3000;
				g.fillRect(1550, 30, 25, hpBar);
				g.setColor(Color.WHITE);
				
			}else{
				hpBar = (boss.getHitPoints()*900)/length;
				g.fillRect(1550, 30, 25, hpBar);
				g.setColor(Color.WHITE);
			}
			for(int i = 1; i <= 30; i++)
				g.drawRect(1550, 30*i, 25, 30);
		}
		if(paused){
			g.setFont(FONT);
			g.setColor(Color.WHITE);
			g.drawString(PAUSED_TEXT, 500, 300);
		}
	}

	private void createBoss() {
		soundManager.stopMusic();
		switch (level) {
		case 1:
			boss = new Boss1(1200, 300, player, this);
			break;
		case 2:
			boss = new Boss2(1400, 480, player, this);
			break;
		case 3:
			boss = new Boss3(1200, 350, player, this);
		}
	}

	@Override
	protected void nextLevel() {
		Main.getUpgrades().addScrap(player.getScrap() + player.getLives() * 2000);
		if(level != 3)
			Main.level = new LevelWon(soundManager, input, level);
		else
			Main.level = new Credits(soundManager, input);
	}

	/**
	 * Returns the current value for the boss intro counter
	 * @return
	 * Current value for the boss intro counter
	 */
	public int getBossIntro() {
		return bossIntro;
	}

	/**
	 * Returns reference to SoundManager object
	 * @return
	 * Reference to SoundManager object
	 * 
	 */
	public SoundManager getSoundManager() {
		return soundManager;
	}
	
	public int getId(){
		return level;
	}
}
