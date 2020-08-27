package ar.zgames.zshot.weapons;

import ar.zgames.zshot.animations.Animation;
import ar.zgames.zshot.level.GameLevel;
import ar.zgames.zshot.system.ObjectPool;

/**
 * Superclass to handle weapon behavior
 */
public abstract class Weapon {
	protected static ObjectPool objectPool; // ObjectPool reference
	protected int fireRate; // Weapon's fire rate
	protected int power; // Weapon's power level
	protected int damage; // Damage to deal on impact
	protected Animation animation; // Bullet animation
	protected Animation hitAnimation; // Bullet collision animation
	
	/**
	 * Shoots the weapon, creating one or more bullet objects on the level
	 * @param xo
	 * - Shot initial X position
	 * @param yo
	 * - Shot initial Y position
	 * @param xf
	 * - Shot directed X position
	 * @param yf
	 * - Shot directed Y position
	 * @param playerShot
	 * - Whether the bullet was fired by the player
	 * @param level
	 * - Reference to GameLevel object
	 */
	public abstract void shoot(int xo, int yo, int xf, int yf, boolean playerShot, GameLevel level);
	
	/**
	 * Increases weapon's power level
	 */
	public abstract void incPower();
	
	/**
	 * Decreases weapon's power level
	 */
	public abstract void decPower();
	
	/**
	 * Returns weapon's fire rate
	 * @return
	 * Weapon's fire rate
	 */
	public int getFireRate(){
		return fireRate;
	}
	
	/**
	 * Returns weapon's power level
	 * @return
	 * Weapon's power level
	 */
	public int getPower(){
		return power;
	}

	/**
	 * Returns damage weapon deals on impact
	 * @return
	 * Weapon's damage
	 */
	public int getDamage(){
		return damage;
	}
	
	/**
	 * Returns weapon's bullet animation
	 * @return
	 * Weapon's bullet animation
	 */
	public Animation getSprite(){
		return animation;
	}
	
	/**
	 * Returns bullet's hit animation
	 * @return
	 * Bullet's hit animation
	 */
	public Animation getHitAnimation() {
		return hitAnimation;
	}
	
	/**
	 * Sets the ObjectPool object from which to draw new bullets
	 * @param pool
	 * ObjectPool object from which to draw new bullets
	 */
	public static void setObjectPool(ObjectPool pool){
		Weapon.objectPool = pool;
	}
}

