package ar.zgames.zshot.weapons;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

/**
 * Stream weapon
 */
public class Stream extends Weapon {

	private static final int DAMAGE = 15; // Damage constant
	private static final float SPEED = 6; // Speed constant
	private static final int FIRE_RATE = 125; // Fire rate constant
	private static final int DAMAGE_INC = 5; // damage increase constant
	
	/**
	 * Constructs a new Stream with specified power
	 * @param pow
	 * - Power level (should normally be 1 to 5)
	 */
	public Stream(int pow){
		fireRate = FIRE_RATE;
		power = pow;
		damage = DAMAGE + DAMAGE_INC * (pow - 1);
		animation = Animations.STREAM.getAnimation();
		hitAnimation = Animations.BULLET_HIT.getAnimation();
	}
	
	@Override
	public void shoot(int xo, int yo, int xf, int yf, boolean playerShot, GameLevel level) {
		float ya;
		if(yf < yo){
			ya = -SPEED;
			yo -= animation.getImage().getHeight(null);
		}
		else
			ya = SPEED;
		level.getSoundManager().playStream();
		objectPool.getBullet().set(xo, yo, 0, ya, this, playerShot, level);
		objectPool.getBullet().set(xo+25, yo, 0, ya, this, playerShot, level);
		objectPool.getBullet().set(xo-25, yo, 0, ya, this, playerShot, level);
	}

	@Override
	public void incPower() {
		if(power < 5 ){
			power++;
			damage += DAMAGE_INC;
		}
	}

	@Override
	public void decPower() {
		if(power > 1 ){
			power--;
			damage -= DAMAGE_INC;
		}
	}
}
