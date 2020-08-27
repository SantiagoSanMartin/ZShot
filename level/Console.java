package ar.zgames.zshot.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ar.zgames.zshot.actor.Player;
import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.system.ScreenManager;
import ar.zgames.zshot.weapons.Weapon;

/**
 * Console to show on the bottom of levels
 */
public class Console {
	private static final BufferedImage LIFE = ResourceManager.createCompatible(ResourceManager.resize(ResourceManager.deepCopy(ResourceManager.getPlayer100()), 40, 40)); // Life image (Player image scaled down)
	private static final BufferedImage GATLING = ResourceManager.createCompatible(ResourceManager.getGatling()); // GatlingGun weapon icon
	private static final BufferedImage CANNON = ResourceManager.createCompatible(ResourceManager.getCannon()); // Cannon weapon icon
	private static final BufferedImage SPREAD = ResourceManager.createCompatible(ResourceManager.getSpread()); // SpreadGun weapon icon
	private static final BufferedImage CANNON_NULL = ResourceManager.createCompatible(ResourceManager.getCannonNull()); // Grayed out Cannon icon
	private static final BufferedImage SPREAD_NULL = ResourceManager.createCompatible(ResourceManager.getSpreadNull()); // Grayed out SpreadGun icon
	private static final BufferedImage GATLING_SELECTED = ResourceManager.createCompatible(ResourceManager.getGatlingSelected()); // Selected GatlingGun icon
	private static final BufferedImage SPREAD_SELECTED = ResourceManager.createCompatible(ResourceManager.getSpreadSelected()); // Selected SpreadGun weapon icon
	private static final BufferedImage CANNON_SELECTED = ResourceManager.createCompatible(ResourceManager.getCannonSelected()); // Selected Cannon weapon icon
	private static final String HP = "HP"; // String to display "HP"
	private static final String EN = "EN"; // String to display "EN"
	private static final String WEAPONS = "Weapons"; // String to display "Weapons"
	private static final String LIVES = "Lives"; // String to diaplsy "Lives"
	private static final String SCRAP = "Scrap"; // String to display "Scrap"
	private static final Font FONT = new Font("verdana", Font.PLAIN, 20); // Predefined Font 
	private int consoleHeight; // Console's height in pixels
	private Player player; // Reference to Player object
	private static final  Color[] COLORS = new Color[256]; // Array with predefined color gradient
	
	/**
	 * Constructs a new console
	 * @param player
	 * - Reference to Player object
	 */
	public Console(Player player){
		this.player = player;
		consoleHeight = ScreenManager.getHeight()/5;
		if(COLORS[0] == null){
			for(int i = 0; i <= 255; i++){
				COLORS[i] = new Color(170, i, 255);
			}
		}
	}

	/**
	 * Draws the console
	 * @param g
	 * - Graphics2D
	 */
	public void draw(Graphics2D g){
		int x, y, hp;
		float en;
		x = ScreenManager.getWidth();
		y = ScreenManager.getHeight();
		hp = player.getHitPoints();
		en = player.getEnergy();
		g.setFont(FONT);
		
		// Console frame
		g.setColor(Color.WHITE);
		g.fillRoundRect(0, y - consoleHeight, x, consoleHeight, 20, 20);
		g.setColor(Color.RED);
		g.drawRoundRect(0, y - consoleHeight, x, consoleHeight, 20, 20);
		g.setColor(Color.BLACK);
		g.fillRoundRect(0 + 10, y - consoleHeight + 10, x - 20, consoleHeight - 20, 20, 20);
		g.setColor(Color.RED);
		g.drawRoundRect(0 + 10, y - consoleHeight + 10, x - 20, consoleHeight - 20, 20, 20);
		
		// HP info
		if(hp >= 750)
			g.setColor(Color.BLUE);
		else if(hp >= 500)
			g.setColor(Color.GREEN);
		else if(hp >= 250)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.RED);
		g.drawString(HP, 60, y - consoleHeight + 40);
		g.fillRect(60, y - consoleHeight + 60, hp/10, 20);
		g.setColor(Color.WHITE);
		g.drawRect(60, y - consoleHeight + 60, 100, 20);

		//EN info
		int green = (int) (255 - (en * 255 / 1000));
		g.setColor(COLORS[green]);
		g.drawString(EN, 60, y - consoleHeight + 130);
		g.fillRect(60, y - consoleHeight + 150, (int) (en/10), 20);
		g.setColor(Color.WHITE);
		g.drawRect(60, y - consoleHeight + 150, 100, 20);
		
		//Scrap
		g.setColor(Color.BLUE);
		g.drawString(SCRAP, 300, y - 180);
		g.drawString(String.valueOf(player.getScrap()), 300, y - 140);
		
		//Lives
		g.drawString(LIVES, 410, y - 180);
		for(int i = 0; i < player.getLives(); i++){
			g.drawImage(LIFE, 400 + i * 50, y - 150, null);
		}
		
		//Weapons
		Weapon[] weapons = player.getWeaponArray();
		BufferedImage image;
		g.drawString(WEAPONS, x/2 + 120, y - 180);
		
		//GatlingGun
		if(player.getWeaponSlot() == 0)
			image = GATLING_SELECTED;
		else
			image = GATLING;
		
		int j = 10;
		for(int i = 1; i <= 5; i++){
			if(weapons[0].getPower() >= i){
				g.setColor(Color.BLUE);
				g.fillRect(x/2 + j, y - consoleHeight*2/3 + image.getHeight(), 15, 15);
			}
			g.setColor(Color.WHITE);
			g.drawRect(x/2 + j, y - consoleHeight*2/3 + image.getHeight(), 15, 15);
			j += 15;
		}
		g.drawImage(image, x/2, y - consoleHeight*2/3, null);
		
		//SpreadGun
		if(weapons[1] == null)
			image = SPREAD_NULL;
		else{
			if(player.getWeaponSlot() == 1)
				image = SPREAD_SELECTED;
			else
				image = SPREAD;
			
			j = 10;
			for(int i = 1; i <= 5; i++){
				if(weapons[1].getPower() >= i){
					g.setColor(Color.BLUE);
					g.fillRect(x/2 + 120 + j, y - consoleHeight*2/3 + image.getHeight(), 15, 15);
				}
				g.setColor(Color.WHITE);
				g.drawRect(x/2 + 120 + j, y - consoleHeight*2/3 + image.getHeight(), 15, 15);
				j += 15;
			}
		}
		g.drawImage(image, x/2 + 120, y - consoleHeight*2/3, null);
		
		//Cannon
		if(weapons[2] == null)
			image = CANNON_NULL;
		else{
			if(player.getWeaponSlot() == 2)
				image = CANNON_SELECTED;
			else
				image = CANNON;
			
			j = 10;
			for(int i = 1; i <= 5; i++){
				if(weapons[2].getPower() >= i){
					g.setColor(Color.BLUE);
					g.fillRect(x/2 + 240 + j, y - consoleHeight*2/3 + image.getHeight(), 15, 15);
				}
				g.setColor(Color.WHITE);
				g.drawRect(x/2 + 240 + j, y - consoleHeight*2/3 + image.getHeight(), 15, 15);
				j += 15;
			}
		}
		g.drawImage(image, x/2 + 240, y - consoleHeight*2/3, null);	
	}
	
	/**
	 * Returns the console's height in pixels
	 * @return
	 * - Console height in pixels
	 */
	public int getHeight() {
		return consoleHeight;
	}
}
