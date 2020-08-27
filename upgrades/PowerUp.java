package ar.zgames.zshot.upgrades;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import ar.zgames.zshot.actor.Actor;
import ar.zgames.zshot.actor.Player;
import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

/**
 * PowerUp is the superclass for the different power ups
 */
public class PowerUp extends Actor {
	
	private Player player; // Player object
	private float transparency; // transparency to create a fade in effect
	private int type; // PowerUp type
	
	/**
	 * Constructs a new PowerUp object with the specified type<p>
	 * Types:<br>
	 * 0) HP restore<br>
	 * 1) Energy restore<br>
	 * 2) Weapon power increase<br>
	 * 3) Movement upgrade<br>
	 * 4) Scrap bonus<br>
	 * 5) Life up<p>
	 * @param x
	 * - X starting position
	 * @param y
	 * - Y starting position
	 * @param type
	 * - PowerUp type
	 * @param player
	 * - Player object
	 * @param level
	 * - GameLevel object
	 */
	public PowerUp(int x, int y, int type, Player player, GameLevel level) {
		this.x = x;
		this.y = y;
		this.level = level;
		if(x < 500)
			xa = 8;
		this.player = player;
		this.type = type;
		transparency = 0.05f;
		switch(type){
		case 0:
			this.sprite = Animations.POWERUP_HP.getAnimation();
			break;
		case 1:
			this.sprite = Animations.POWERUP_EN.getAnimation();
			break;
		case 2:
			this.sprite = Animations.POWERUP_WPN.getAnimation();
			break;
		case 3:
			this.sprite = Animations.POWERUP_MOVE.getAnimation();
			break;
		case 4:
			this.sprite = Animations.POWERUP_SCRAP.getAnimation();
			break;
		case 5:
			this.sprite = Animations.POWERUP_LIFE.getAnimation();
			break;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if(transparency < 1){
			AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
			g.setComposite(ac);
			g.drawImage(sprite.getImage(), (int)x, (int)y, null);
			ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
			g.setComposite(ac);
			transparency += 0.05f;
		}else
			g.drawImage(sprite.getImage(), (int)x, (int)y, null);
	}

	@Override
	public void tick() {
		float xaLevel = player.getXLevelAcc() / 8;
		float yaLevel = player.getYLevelAcc() / 8;
		
		if(level.getId() == 3)
			yaLevel = 0;
		
		if(xa > 0){
			xa -= 0.05;
			if(xa < 0)
				xa = 0;
		}
		if(x < -100)
			remove();
		x += xa + xaLevel - 0.5f;
		y += ya + yaLevel;
		checkCollisions();
	}

	/**
	 * Checks collisions with player
	 */
	public void checkCollisions() {
		if(!player.isRemoved()){
			if (getBounds().intersects(player.getBounds())) {
				level.getSoundManager().playChime();
				activate();
				remove();
			}
		}
	}

	/**
	 * Activates PowerUp effect according to type
	 */
	public void activate(){
		switch(type){
		case 0:
			player.restoreHP(500);
			break;
		case 1:
			player.restoreEN(500);
			break;
		case 2:
			player.incWeaponPower();
			break;
		case 3:
			player.incManeuver();
			break;
		case 4:
			player.addScrap(1000);
			break;
		case 5:
			player.addLife();
			break;
		}
	}
	
	@Override
	protected void hurt(int damage) {
		// Do nothing
	}
}


