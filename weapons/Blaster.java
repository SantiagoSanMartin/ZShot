package ar.zgames.zshot.weapons;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

/**
 * Blaster weapon
 */
public class Blaster extends Weapon {

	private static final int DAMAGE = 30; // Damage constant
	private static final int DAMAGE_INC = 15; // Damage increase constant
	private static final float SPEED = 6; // Speed constant
	private static final int FIRE_RATE = 125; // Fire rate constant
	private static final int FIRE_RATE_DEC = 25; // Fire rate decrease constant
	
	/**
	 * Constructs a new Blaster with the specified power
	 * @param pow
	 * - Power level (should normally be 1 to 5)
	 */
	public Blaster(int pow){
		fireRate = FIRE_RATE - FIRE_RATE_DEC * (pow - 1);
		power = pow;
		damage = DAMAGE + DAMAGE_INC * (pow - 1);
		animation = Animations.BLASTER.getAnimation();
		hitAnimation = Animations.BULLET_HIT_CANNON1.getAnimation();
	}
	
	@Override
	public void shoot(int xo, int yo, int xf, int yf, boolean playerShot, GameLevel level) {
		float xa;
		if(xo > xf)
			xa = -SPEED;
		else
			xa = SPEED;
		level.getSoundManager().playBlaster();
		objectPool.getBullet().set(xo, yo, xa, 0, this, playerShot, level);
	}
	
	@Override
	public void incPower(){
		if(power < 5 ){
			damage += DAMAGE_INC;
			fireRate -= FIRE_RATE_DEC;
			++power;
		}
	}
	
	@Override
	public void decPower(){
		if(power > 1 ){
			damage -= DAMAGE_INC;
			fireRate += FIRE_RATE_DEC;
			--power;
		}
	}
}
