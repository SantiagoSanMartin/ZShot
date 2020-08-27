package ar.zgames.zshot.weapons;

import ar.zgames.zshot.animations.Animation;
import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

/**
 * SpreadGun weapon
 */
public class SpreadGun extends Weapon{
	private static final float SPEED = 3.5f; // Speed constant
	private static final int DAMAGE = 5; // Damage constant
	private static final int FIRE_RATE = 125; // Fire rate constant
	
	/**
	 * Constructs a new SpreadGun with the specified power
	 * @param pow
	 * - Power level (should normally be 1 to 5)
	 */
	public SpreadGun(int pow){
		fireRate = FIRE_RATE;
		power = pow;
		damage = DAMAGE;
		animation = new Animation();
		animation = Animations.SPREAD.getAnimation();
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
		
		for(int i = 0; i <= power; i++){
			double offset = (6 - power) * 0.1 * i;
			for(int j = 0; j < 2; j++){
				offset *= -1;
				ya = (float) (Math.sin(alpha+offset)*SPEED);
				xa = (float) (Math.cos(alpha+offset)*SPEED);
				if(xo > xf){
					xa *= -1;
					ya *= -1;
				}
				objectPool.getBullet().set(xo, yo, xa, ya, this, playerShot, level);
			}
		}
		level.getSoundManager().playSpread();
	}

	@Override
	public void incPower() {
		if(power < 5 ){
			++power;
		}
	}

	@Override
	public void decPower(){
		if(power > 1 ){
			--power;
		}
	}
}
