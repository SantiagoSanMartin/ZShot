package ar.zgames.zshot.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import ar.zgames.zshot.actor.Player;
import ar.zgames.zshot.background.Space;
import ar.zgames.zshot.system.InputHandler;
import ar.zgames.zshot.system.Main;
import ar.zgames.zshot.system.ScreenManager;
import ar.zgames.zshot.system.SoundManager;

public class Credits extends Level {
	private final Font font =  new Font("Helvetica", Font.BOLD, 40); // Predefined Font
	private final Space background = new Space();
	private final String[] credits = new String[]{	// Credits' text
			"GAME DESIGN",
			"Santiago San Martin",
			"",
			"PROGRAMMING",
			"Santiago San Martin",
			"",
			"SOUND EFFECTS STOLEN FROM...",
			"Megaman X4",
			"",
			"MUSIC STOLEN FROM...",
			"Legend of Mana",
			"Megaman 2",
			"Megaman X5",
			"Megaman X6",
			"Sim City 4",
			"Valkyrie Profile",
			"Valkyrie Profile 2",
			"Wild Arms",
			"Wild Arms 2",
			"Xenogears",
			"",
			"MUSIC PROGRAMMING",
			"Javazoom - JLayer",
			"",
			"BACKGROUND ART",
			"Debora Holsinger",
			"http://didiher.deviantart.com/",
			"http://didiher.carbonmade.com/",
			"",
			"Kris Lachowski",
			"http://opengameart.org/users/krislachowski",
			"",
			"Fraang",
			"http://opengameart.org/users/fraang",
			"",
			"PIXEL ART",
			"Skorpio",
			"http://opengameart.org/users/skorpio",
			"",
			"wubitog",
			"http://opengameart.org/users/wubitog",
			"",
			"kanadaj",
			"http://opengameart.org/users/kanadaj",
			"",
			"Justin Nichol",
			"http://justinnichol.wordpress.com/",
			"",
			"Jonas Wagner",
			"http://29a.ch/",
			"",
			"Rawdanitsu",
			"http://opengameart.org/users/rawdanitsu",
			"",
			"MrBeast",
			"http://opengameart.org/users/mrbeast",
			"",
			"Santiago San Martin",
			"",
			"Fernando Ariel Villa",
			"",
			"ZSHOT LOGO",
			"Santiago San Martin",
			"",
			"BETA TESTERS",
			"Juan Patron",
			"Matias Orona",
			"Fernando Villa",
			"Julian Guerra",
			"",
			"SPECIAL THANKS TO",
			"Fernando Villa",
			"Pablo Listingart",
			"",
			"And you...",
			"",
			"Thank you for playing! =)"};
	
	public Credits(SoundManager soundManager, InputHandler input){
		super(input, soundManager);
		soundManager.stopMusic();
		soundManager.playMusic(0, false);
		player = new Player(0, 1200, input, null, Main.getUpgrades());
		player.setYLevelAcc(3);
		background.setLevel(this);
	}
	
	@Override
	public void tick(){
		ticks++;
		if (fadeOut == 255)
			nextLevel();
		if (fadeIn > 0)
			--fadeIn;
		background.tick();
		if(ticks >= 41000)
			fadeOut++;
	}
	
	@Override
	public void draw(Graphics2D g){
		background.draw(g);
		g.setFont(font);
		g.setColor(Color.WHITE);
		for(int i = 0; i < credits.length; i++){
			g.drawString(credits[i], 400, 1200 + i * 50 - ticks/10);
		}
		if (fadeOut != 0){
			g.setColor(COLORS[fadeOut]);
			g.fillRect(0, 0, ScreenManager.getWidth(),
					ScreenManager.getHeight());
		}
		if (fadeIn != 0) {
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
