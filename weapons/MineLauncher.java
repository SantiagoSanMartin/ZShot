package ar.zgames.zshot.weapons;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

public class MineLauncher extends Weapon {

	private static final int FIRE_RATE = 350; // Fire rate constant
	private static final int DAMAGE = 100; // Damage constant
	private static final float SPEED = 1f; // Bullet speed constant
	
	/**
	 * Constructs a new MineLauncher with the specified power
	 * @param pow
	 * - Power level (should normally be 1 to 5)
	 */
	public MineLauncher(int pow){
		fireRate = FIRE_RATE;
		power = pow;
		damage = DAMAGE * pow;
		animation = Animations.MINE1.getAnimation();
		hitAnimation = Animations.BULLET_HIT_CANNON4.getAnimation();
	}
	
	@Override
	public void shoot(int xo, int yo, int xf, int yf, boolean playerShot, GameLevel level) {
		objectPool.getBullet().set(xo, yo, -SPEED, 0, this, playerShot, level);
		level.getSoundManager().playMine();
	}
	
	@Override
	public void incPower() {
		if(power < 5 ){
			damage += DAMAGE;
			power++;
		}
	}

	@Override
	public void decPower() {
		if(power > 1 ){
			damage -= DAMAGE;
			power--;
		}
	}
}
