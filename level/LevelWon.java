package ar.zgames.zshot.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ar.zgames.zshot.system.InputHandler;
import ar.zgames.zshot.system.Main;
import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.system.SaveManager;
import ar.zgames.zshot.system.ScreenManager;
import ar.zgames.zshot.system.SoundManager;
import ar.zgames.zshot.upgrades.Upgrades;

/**
 * Handles level win events and purchase of upgrades
 */
public class LevelWon extends Level {

    private Upgrades upgrades; // Shop object
    private int level; // Level id
    private boolean newWeapon; // Indicates if a new weapon was acquired from beating the previous level
    private int usedScrap; // Amount of scrap used
    private static final String TOTAL = "Total Scrap"; // String to display "Total Scrap"
    private static final String REMAINING = "Remaining Scrap"; // String to display "Remaining Scrap"
    private static final String CONFIRM_TEXT = "CONFIRM"; // String to display "CONFIRM"
    private static final String ACQUIRED = "Acquired"; // String to display "Acquired"
    private static final String[] INFO = new String[]{"Gatling weapon power", "SpreadGun weapon power", "Cannon weapon power", "Maneuverability: acceleration/deceleration", "Damage reduction: Reduces damage taken", "Not enough scrap", "Cannot reduce anymore", "Cannot increase anymore", "GOOD LUCK!"};
    private static final Font FONT1 = new Font("Helvetica", Font.BOLD, 40); // Predefined Font
    private static final Font FONT2 = new Font("Helvetica", Font.ITALIC, 30); // Predefined Font
    private static final int PRICE = 10000; // Price constant for upgrades
    private static final BufferedImage BG_IMAGE = ResourceManager.createCompatible(ResourceManager.getStarfield()); // Background image
    private static final BufferedImage GATLING = ResourceManager.createCompatible(ResourceManager.getGatling()); // GatlingGun icon
    private static final BufferedImage SPREAD = ResourceManager.createCompatible(ResourceManager.getSpread()); // SpreadGun icon
    private static final BufferedImage CANNON = ResourceManager.createCompatible(ResourceManager.getCannon()); // Cannon icon
    private static final BufferedImage MOVE = ResourceManager.createCompatible(ResourceManager.getMove()); // Move upgrade icon
    private static final BufferedImage DAMAGE_REDUCTION = ResourceManager.createCompatible(ResourceManager.getDamageReduction()); // Damage Reduction upgrade icon
    private static final BufferedImage LEFT_ARROW = ResourceManager.createCompatible(ResourceManager.getLeftArrow()); // Left arrow icon
    private static final BufferedImage RIGHT_ARROW = ResourceManager.createCompatible(ResourceManager.getRightArrow()); // Right arrow icon
    private String weaponAcquired; // Name of weapon acquired from previous level
    private String info;
    private static final Rectangle ADD1 = new Rectangle(340, 185, 30, 30); // Increase Gatling button
    private static final Rectangle SUB1 = new Rectangle(170, 185, 30, 30); // Decrease Gatling button
    private static final Rectangle ADD2 = new Rectangle(340, 335, 30, 30); // Increase SpreadGun button
    private static final Rectangle SUB2 = new Rectangle(170, 335, 30, 30); // Decrease SpreadGun button
    private static final Rectangle ADD3 = new Rectangle(340, 485, 30, 30); // Increase Cannon button
    private static final Rectangle SUB3 = new Rectangle(170, 485, 30, 30); // Decrease Cannon button
    private static final Rectangle ADD4 = new Rectangle(340, 635, 30, 30); // Increase Move button
    private static final Rectangle SUB4 = new Rectangle(170, 635, 30, 30); // Decrease Move button
    private static final Rectangle ADD5 = new Rectangle(340, 785, 30, 30); // Increase Gatling button
    private static final Rectangle SUB5 = new Rectangle(170, 785, 30, 30); // Decrease Gatling button
    private static final Rectangle CONFIRM = new Rectangle(1220, 1020, 260, 60); // Confirm purchase button
    
    /**
     * Constructs a new LevelWon object
     * @param soundManager
     * - SoundManager object
     * @param input
     * - InputHandler object
     * @param level
     * - Previous level id
     */
    public LevelWon(SoundManager soundManager, InputHandler input, int level) {
        super(input, soundManager);
        upgrades = Main.getUpgrades();
        this.level = level;
        newWeapon = newWeapon();
        calculateScrap();
        soundManager.stopMusic();
        info = "";
        if(newWeapon)
        	soundManager.playMusic(6, false);
        else
        	soundManager.playMusic(4, true);
    }
    
    /**
     * Calculates amount of scrap used
     */
    private void calculateScrap() {
        usedScrap = (upgrades.getWeapons()[0].getPower() -1) * PRICE;
        if(upgrades.getWeapons()[1] != null)
            usedScrap += (upgrades.getWeapons()[1].getPower() -1) * PRICE;
        if(upgrades.getWeapons()[2] != null)
            usedScrap += (upgrades.getWeapons()[2].getPower() -1) * PRICE;
        usedScrap += (upgrades.getManeuverability() -1) * PRICE;
        usedScrap += (upgrades.getDamageReduction()) * PRICE;
    }

    @Override
    public void tick(){
    	ticks++;
        if (fadeIn > 0)
            --fadeIn;
        if (fadeOut == 255)
            nextLevel();
        if(fadeOut == 0){
            if(!newWeapon){
                if(input.shoot.down){
                    input.shoot.release();
                    process(input.getMX(), input.getMY());
                    if(info.equals(INFO[5]) || info.equals(INFO[6]) || info.equals(INFO[7]))
                        soundManager.playBuzzer();
                    calculateScrap();
                }
            }else if(input.menu.down){
                newWeapon = false;
                this.soundManager.stopMusic();
                this.soundManager.playMusic(4, true);
                input.menu.release();
            }
        }
    }

    @Override
    public void draw(Graphics2D g){
        g.drawImage(BG_IMAGE, 0, 0, null);
        if(newWeapon){
	        	if(fadeIn == 0){
	        	g.setFont(FONT1);
	        	g.setColor(Color.MAGENTA);
	        	g.drawString(ACQUIRED, Math.min(ticks, 500), 500);
	            g.setColor(Color.RED);
	            g.setClip(0, 0, ticks, 1200);
	            g.drawString(weaponAcquired, 800 - weaponAcquired.length()*10, 600);
        	}
        }else{
        	// Windows
            g.setColor(Color.WHITE);
            g.fillRoundRect(100, 100, 650, 800, 20, 20);
            g.fillRoundRect(900, 100, 650, 300, 20, 20);
            g.fillRoundRect(100, 1000, 1000, 100, 20, 20);
            if(fadeOut > 0 && fadeOut < 10)
            	g.setColor(Color.RED);
            g.fillRoundRect(1200, 1000, 300, 100, 20, 20);
            
            g.setColor(Color.BLACK);
            g.fillRoundRect(120, 120, 610, 760, 20, 20);
            g.fillRoundRect(920, 120, 610, 260, 20, 20);
            g.fillRoundRect(120, 1020, 960, 60, 20, 20);
            g.fillRoundRect(1220, 1020, 260, 60, 20, 20);
            
            // Gatling
            g.drawImage(LEFT_ARROW, 170, 185, null);
            g.drawImage(GATLING, 220, 150, null);
            g.drawImage(RIGHT_ARROW, 340, 185, null);
            
            int j = 10;
            for(int i = 1; i <= 5; i++){
                if(upgrades.getWeapons()[0].getPower() >= i){
                    g.setColor(Color.BLUE);
                    g.fillRect(120 + 400 + j, 200, 15, 15);
                }
                g.setColor(Color.WHITE);
                g.drawRect(120 + 400 + j, 200, 15, 15);
                j += 15;
            }
            
            // Spread
            if(upgrades.getWeapons()[1] != null){
                g.drawImage(LEFT_ARROW, 170, 335, null);
                g.drawImage(SPREAD, 220, 300, null);
                g.drawImage(RIGHT_ARROW, 340, 335, null);
                
                j = 10;
                for(int i = 1; i <= 5; i++){
                    if(upgrades.getWeapons()[1].getPower() >= i){
                        g.setColor(Color.BLUE);
                        g.fillRect(120 + 400 + j, 350, 15, 15);
                    }
                    g.setColor(Color.WHITE);
                    g.drawRect(120 + 400 + j, 350, 15, 15);
                    j += 15;
                }
            }
            
            // Cannon
            if(upgrades.getWeapons()[2] != null){
                g.drawImage(LEFT_ARROW, 170, 485, null);
                g.drawImage(CANNON, 220, 450, null);
                g.drawImage(RIGHT_ARROW, 340, 485, null);
                
                j = 10;
                for(int i = 1; i <= 5; i++){
                    if(upgrades.getWeapons()[2].getPower() >= i){
                        g.setColor(Color.BLUE);
                        g.fillRect(120 + 400 + j, 500, 15, 15);
                    }
                    g.setColor(Color.WHITE);
                    g.drawRect(120 + 400 + j, 500, 15, 15);
                    j += 15;
                }
            }
            
            // Move
            g.drawImage(LEFT_ARROW, 170, 635, null);
			g.drawImage(MOVE, 220, 600, null);
			g.drawImage(RIGHT_ARROW, 340, 635, null);

			j = 10;
			for (int i = 1; i <= 5; i++) {
				if (upgrades.getManeuverability() >= i) {
					g.setColor(Color.BLUE);
					g.fillRect(120 + 400 + j, 650, 15, 15);
				}
				g.setColor(Color.WHITE);
				g.drawRect(120 + 400 + j, 650, 15, 15);
				j += 15;
			}
			
			// Damage Reduction
            g.drawImage(LEFT_ARROW, 170, 785, null);
			g.drawImage(DAMAGE_REDUCTION, 220, 750, null);
			g.drawImage(RIGHT_ARROW, 340, 785, null);

			j = 10;
			for (int i = 1; i <= 5; i++) {
				if (upgrades.getDamageReduction() >= i) {
					g.setColor(Color.BLUE);
					g.fillRect(120 + 400 + j, 800, 15, 15);
				}
				g.setColor(Color.WHITE);
				g.drawRect(120 + 400 + j, 800, 15, 15);
				j += 15;
			}
            
			// Totals, info and confirm button
            g.setFont(FONT2);
            g.drawString(String.valueOf(upgrades.getScrap()), 950, 210);
            g.drawString(String.valueOf(upgrades.getScrap() - usedScrap), 950, 350);    
			
            g.setFont(FONT1);
            g.drawString(TOTAL, 950, 160);
            g.drawString(REMAINING, 950, 300);
            g.drawString(info, 150, 1065);   
            
            if(fadeOut > 0 && fadeOut < 10)
            	g.setColor(Color.RED);
            g.drawString(CONFIRM_TEXT, 1250, 1065);
                 
        }
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
    	upgrades.setLevel(level);
    	SaveManager saveManager = new SaveManager();
        saveManager.saveGame(upgrades);
        level++;
        Main.level = new StageStart(input, soundManager, level);
    }
    
    
    /**
     * Process button click
     * @param x
     * - Mouse X position
     * @param y
     * - Mouse Y position
     */
    private void process(int x, int y){
		if (ADD1.contains(x, y)) {
			if (usedScrap + PRICE <= upgrades.getScrap()) {
				info = INFO[0];
				if (upgrades.getWeapons()[0].getPower() < 5) {
					soundManager.playIncrease();
					upgrades.getWeapons()[0].incPower();
				} else
					info = INFO[7];
			} else
				info = INFO[5];
			return;
		}
		if (ADD2.contains(x, y)) {
			if (usedScrap + PRICE <= upgrades.getScrap()) {
				info = INFO[1];
				if (upgrades.getWeapons()[1].getPower() < 5) {
					soundManager.playIncrease();
					upgrades.getWeapons()[1].incPower();
				} else
					info = INFO[7];
			} else
				info = INFO[5];
			return;
		}
		if (ADD3.contains(x, y)) {
			if (usedScrap + PRICE <= upgrades.getScrap()) {
				info = INFO[2];
				if (upgrades.getWeapons()[2].getPower() < 5) {
					soundManager.playIncrease();
					upgrades.getWeapons()[2].incPower();
				} else
					info = INFO[7];
			} else
				info = INFO[5];
			return;
		}
		if (ADD4.contains(x, y)) {
			if (usedScrap + PRICE <= upgrades.getScrap()) {
				info = INFO[3];
				if (upgrades.getManeuverability() < 5) {
					soundManager.playIncrease();
					upgrades.incManeuverability();
				} else
					info = INFO[7];
			} else
				info = INFO[5];
			return;
		}
		if (ADD5.contains(x, y)) {
			if (usedScrap + PRICE <= upgrades.getScrap()) {
				info = INFO[4];
				if (upgrades.getDamageReduction() < 5) {
					soundManager.playIncrease();
					upgrades.incDamageReduction();
				} else
					info = INFO[7];
			} else
				info = INFO[5];
			return;
		}

    	if(SUB1.contains(x, y)){
    		if(upgrades.getWeapons()[0].getPower() > 1){
	    		soundManager.playDecrease();
	    		upgrades.getWeapons()[0].decPower();
	    		info = INFO[0];
    		}else
    			info = INFO[6];
    		return;
    	}
    	if(SUB2.contains(x, y)){
    		if(upgrades.getWeapons()[1].getPower() > 1){
	    		soundManager.playDecrease();
	    		upgrades.getWeapons()[1].decPower();
	    		info = INFO[1];
    		} else
    			info = INFO[6];
    		return;
    	}
    	if(SUB3.contains(x, y)){
    		if(upgrades.getWeapons()[2].getPower() > 1){
	    		soundManager.playDecrease();
	    		upgrades.getWeapons()[2].decPower();
	    		info = INFO[2];
    		} else
    			info = INFO[6];
    		return;
    	}
    	if(SUB4.contains(x, y)){
    		if(upgrades.getManeuverability() > 1){
	    		soundManager.playDecrease();
	    		upgrades.decManeuverability();
	    		info = INFO[3];
    		} else
    			info = INFO[6];
    		return;
    	}
    	if(SUB5.contains(x, y)){
    		if(upgrades.getDamageReduction() > 0){
    			soundManager.playDecrease();
    			upgrades.decDamageReduction();
    			info = INFO[4];
    		} else
    			info = INFO[6];
			return;
    	}
    	if(CONFIRM.contains(x, y)){
    		soundManager.playChime();
    		fadeOut++;
    		info = INFO[8];
    		return;
    	}
    	info = "";
    }
    
    /**
     * Checks if a new Weapon should be added and, if so, adds it
     * @return
     * true if a weapon was added, false otherwise
     */
    private boolean newWeapon(){
        switch(level){
        case 1:
            if(upgrades.getWeapons()[1] == null){
                upgrades.addSpreadGun();
                weaponAcquired = "SpreadGun";
                return true;
            }
            break;
        case 2:
            if(upgrades.getWeapons()[2] == null){
                upgrades.addCannon();
                weaponAcquired = "Cannon";
                return true;
            }
            break;
        }
        return false;
    }
}