package ar.zgames.zshot.weapons;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

/**
 * Cannon weapon
 */
public class Cannon extends Weapon {
	
	private static final int DAMAGE_INC = 75; // Damage increase constant
	private static final float SPEED = 5; // Speed constant
	private static final int FIRE_RATE = 600; // Fire rate constant
	
	/**
	 * Constructs a new Cannon with the specified power
	 * @param pow
	 * - Power level (should normally be 1 to 5)
	 */
	public Cannon(int pow){
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
		float xa;
		if(xo > xf)
			xa = -SPEED;
		else
			xa = SPEED;
		level.getSoundManager().playCannon();
		objectPool.getBullet().set(xo, yo, xa, 0, this, playerShot, level);
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
