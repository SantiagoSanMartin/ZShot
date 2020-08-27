package ar.zgames.zshot.weapons;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

/**
 * GatlingGun weapon
 */
public class GatlingGun extends Weapon {
	
	private static final int DAMAGE = 10; // Damage constant
	private static final int FIRE_RATE = 85; // Fire rate constant
	private static final int FIRE_RATE_DEC = 15; // Fire rate decrease constant
	private static final float SPEED = 6; // Speed constant
	
	/**
	 * Constructs a new GatlingGun with the specified power
	 * @param pow
	 * - Power level (should normally be 1 to 5)
	 */
	public GatlingGun(int pow){
		fireRate = FIRE_RATE - FIRE_RATE_DEC * (pow - 1);
		power = pow;
		damage = DAMAGE;
		animation = Animations.GATLING.getAnimation();
		hitAnimation = Animations.BULLET_HIT.getAnimation();
	}
	
	@Override
	public void shoot(int xo, int yo, int xf, int yf, boolean playerShot, GameLevel level) {
		float xa, ya;
		float x = xo - xf;
		float y = yo - yf;
		if(x == 0)
			x = 0.001f;
		double alpha = Math.atan(y/x);
		ya = (float) (Math.sin(alpha)*SPEED);
		xa = (float) (Math.cos(alpha)*SPEED);
		if(xo > xf){
			xa *= -1;
			ya *= -1;
		}
		level.getSoundManager().playGatling();
		objectPool.getBullet().set(xo, yo, xa, ya, this, playerShot, level);
	}
	
	@Override
	public void incPower(){
		if(power < 5 ){
			fireRate -= FIRE_RATE_DEC;
			++power;
		}
	}

	@Override
	public void decPower(){
		if(power > 1 ){
			fireRate += FIRE_RATE_DEC;
			--power;
		}
	}
}
