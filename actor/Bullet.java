package ar.zgames.zshot.actor;

import java.awt.Graphics2D;
import java.util.ArrayList;

import ar.zgames.zshot.animations.Animation;
import ar.zgames.zshot.level.GameLevel;
import ar.zgames.zshot.weapons.Weapon;

/**
 * The bullet object deals damage to enemies or the player upon collision
 */
public class Bullet extends Actor {
	
	private boolean playerBullet;		// Indicates whether bullet was fired by player
	private int damage;					// Damage the bullet deals on impact
	protected Animation hitAnimation;		// Animation displayed when bullet hits something
	
	/**
	 * Sets the bullet with initial data
	 * @param xo
	 * - initial X coordinate
	 * @param yo
	 * - initial Y coordinate
	 * @param xa
	 * - X coordinate acceleration
	 * @param ya
	 * - Y coordinate acceleration
	 * @param weapon
	 * - weapon that fired the bullet
	 * @param playerBullet
	 * - pass as true if bullet was fired by player
	 */
	public void set(int xo, int yo, float xa, float ya, Weapon weapon, boolean playerBullet, GameLevel level) {
		this.x = xo;
		this.y = yo;
		invincibleTime = 0;
		deadTime = 0;
		removed = false;
		this.playerBullet = playerBullet;
		this.damage = weapon.getDamage();
		this.sprite = weapon.getSprite().clone();
		this.y -= sprite.getImage().getHeight(null)/2;
		this.xa = xa;
		this.ya = ya;
		this.hitAnimation = weapon.getHitAnimation();
		this.level = level;
		available = false;
		level.addBullet(this);
	}
	
	@Override
	public void tick() {
		sprite.tick();
		move();
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(sprite.getImage(), (int)x, (int)y, null);
	}
	
	/**
	 * Move bullet according to xa and ya attributes.
	 * If the bullet's position moves out of the level bounds + 200 pixels, it is set as removed
	 */
	public void move() {
		x += xa;
		y += ya;
		if(x < -200 || x > level.getWidth()+200 || y < -200 || y > level.getHeight()+200)
			remove();
	}

	/**
	 * Indicates if the bullet was shot by the player
	 * 
	 * @return True if the bullet was shot by the player, false otherwise
	 */
	public boolean isPlayerBullet() {
		return playerBullet;
	}
	
	/**
	 * Returns the bullet's damage
	 * @return
	 * Bullet damage
	 */
	public int getDamage(){
		return damage;
	}

	/**
	 * Checks the bullet's bounds against the player's and enemies'
	 * If a coliision is detected, the bullet hurts the colliding object dealing its damage
	 * and is then removed
	 * @param enemies
	 * - enemy ArrayList
	 * @param player
	 * - player object
	 */
	public void checkCollisions(ArrayList<Enemy> enemies, Player player) {
		if(!playerBullet){
			if(player.getBounds().intersects(getBounds())){
				player.hurt(damage);
				createHitEffect();
				remove();
			}
		}else{
			for(int i = 0; i < enemies.size(); i++){
				if(enemies.get(i).isDead())
					continue;
				if(enemies.get(i).getBounds().intersects(getBounds())){
					enemies.get(i).hurt(damage);
					createHitEffect();
					remove();
					break;
				}
			}
		}
	}

	/**
	 * Creates the bullet's hit effect on the coordinates the bullet hit something
	 */
	public void createHitEffect() {
		Effect e = level.getObjectPool().getEffect();
		e.set(x + sprite.getImage().getWidth(null)/2, 
				   y + sprite.getImage().getHeight(null)/2,
				   xa/5, ya/5, hitAnimation, 85);
		level.addEffect(e);
	}

	@Override
	protected void hurt(int damage) {
		// Do nothing
	}	
}
