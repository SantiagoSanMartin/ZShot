package ar.zgames.zshot.weapons;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

public class PlasmaCannon extends Weapon {
	private static final int DAMAGE_INC = 50; // Damage increase constant
	private static final float SPEED = 5; // Speed constant
	private static final int FIRE_RATE = 200; // Fire rate constant
	
	/**
	 * Constructs a new PlasmaCannon with the specified power
	 * @param pow
	 * - Power level (should normally be 1 to 5)
	 */
	public PlasmaCannon(int pow){
		fireRate = FIRE_RATE;
		power = pow;
		damage = DAMAGE_INC * pow;
		setAnim(pow);
	}
	
	/**
	 * Sets animation according to power level
	 * @param power
	 * - Power level
	 */
	private void setAnim(int power) {
		switch(power){
		case 1:
			hitAnimation = Animations.BULLET_HIT_CANNON1.getAnimation();
			animation = Animations.CANNON1.getAnimation();
			break;
		case 2:
			hitAnimation = Animations.BULLET_HIT_CANNON2.getAnimation();
			animation = Animations.CANNON2.getAnimation();
			break;
		case 3:
			hitAnimation = Animations.BULLET_HIT_CANNON3.getAnimation();
			animation = Animations.CANNON3.getAnimation();
			break;
		case 4:
			hitAnimation = Animations.BULLET_HIT_CANNON4.getAnimation();
			animation = Animations.CANNON4.getAnimation();
			break;
		default:
			hitAnimation = Animations.BULLET_HIT_CANNON5.getAnimation();
			animation = Animations.CANNON5.getAnimation();
			break;
		}
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
		level.getSoundManager().playCannon();
		objectPool.getBullet().set(xo, yo, xa, ya, this, playerShot, level);
	}

	@Override
	public void incPower() {
		if(power < 5 ){
			damage += DAMAGE_INC;
			setAnim(++power);
		}
	}
	
	@Override
	public void decPower(){
		if(power > 1 ){
			damage -= DAMAGE_INC;
			setAnim(--power);
		}
	}
}
