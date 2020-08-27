package ar.zgames.zshot.animations;

import java.awt.image.BufferedImage;
import ar.zgames.zshot.system.ResourceManager;

/**
 * This class builds the different animations for the game
 */
public enum Animations {
	EXPLOSION_SMALL(Images.EXPLOSION, 16, 5, 40, true, true),
	EXPLOSION_BIG(Images.EXPLOSION, 16, 5, 80, true, true),
	BULLET_HIT(Images.BULLET_HIT, 15, 5, 25, true, true),
	BULLET_HIT_CANNON1(Images.BULLET_HIT_CANNON, 14, 5, 60, true, true),
	BULLET_HIT_CANNON2(Images.BULLET_HIT_CANNON, 14, 5, 75, true, true),
	BULLET_HIT_CANNON3(Images.BULLET_HIT_CANNON, 14, 5, 90, true, true),
	BULLET_HIT_CANNON4(Images.BULLET_HIT_CANNON, 14, 5, 100, true, true),
	BULLET_HIT_CANNON5(Images.BULLET_HIT_CANNON, 14, 5, 120, true, true),
	DRONE1(ResourceManager.getDrone1(), 0),
	DRONE2(ResourceManager.getDrone2(), 0),
	TANK1(ResourceManager.getTank1(), 0),
	RAM1(ResourceManager.getRam1(), 0),
	RAM2(ResourceManager.getRam2(), 0),
	BAT1(ResourceManager.getBat1(), 0),
	KAISER1(ResourceManager.getKaiser1(), 0),
	TITAN1(ResourceManager.getTitan1(), 0),
	WALL1(ResourceManager.getRock1(), 0),
	MINERAM1(ResourceManager.getMineRam1(), 0),
	HYPERION1(ResourceManager.getHyperion1(), 0),
	OPEN_EYE(ResourceManager.getEyeOpen(), 0),
	CLOSED_EYE(ResourceManager.getEyeClosed(), 0),
	MINE1(ResourceManager.getMine1(), 0),
	SPREAD(ResourceManager.getSpreadBullet(), 0),
	GATLING(ResourceManager.getGatlingBullet(), 0),
	BLASTER(ResourceManager.getBlasterBullet(), 0),
	STREAM(ResourceManager.getStreamBullet(), 0),
	PLAYER100(ResourceManager.getPlayer100(), 0),
	PLAYER75(ResourceManager.getPlayer75(), 0),
	PLAYER50(ResourceManager.getPlayer50(), 0),
	PLAYER25(ResourceManager.getPlayer25(), 0),
	PLAYER_DEAD(ResourceManager.getPlayerDead(), 0),
	POWERUP_HP(ResourceManager.getHpUp(), 75),
	POWERUP_EN(ResourceManager.getEnUp(), 75),
	POWERUP_WPN(ResourceManager.getWeaponUp(), 75),
	POWERUP_MOVE(ResourceManager.getMoveUp(), 75),
	POWERUP_SCRAP(ResourceManager.getScrapUp(), 75),
	POWERUP_LIFE(ResourceManager.getLifeUp(), 75),
	CANNON1(Images.CANNON, 23, 5, 60, false, true),
	CANNON2(Images.CANNON, 23, 5, 75, false, true),
	CANNON3(Images.CANNON, 23, 5, 90, false, true),
	CANNON4(Images.CANNON, 23, 5, 100, false, true),
	CANNON5(Images.CANNON, 23, 5, 120, false, true), 
	SHIELD(Images.SHIELD, 9, 7, 150, false, true);
	
	private Animation animation;	// Animation object
	
	/**
	 * Constructs a new Animation
	 * @param images
	 * - enum Images constant to be used
	 * @param frames
	 * - Total number of frames
	 * @param time
	 * - Total ticks before the animation ends
	 * @param size
	 * - Value to resize the animation images
	 * @param increase
	 * - True if animation should gradually increase size in each frame
	 * @param rotation
	 * - True if animation should rotate gradually in each frame
	 */
	Animations(Images images, int frames, int time, int size, boolean increase, boolean rotation){
		BufferedImage[] imgs = images.getImages();
		animation = new Animation();
		BufferedImage image;
		int j = 0;
		int sizeInc = 0;
		for(int i = 1; i <= frames; i++){
			if(imgs.length != (j+1)){
				if(i % ((int)Math.ceil((double)frames/imgs.length)) == 0){
					j++;
				}
			}
			image = ResourceManager.deepCopy(imgs[j]);
			if(size != 0){
				if(increase){
					sizeInc += (int)((double)size / 10);
					image = ResourceManager.resize(image, size + sizeInc, size + sizeInc);
				}else
					image = ResourceManager.resize(image, size, size);
			}
			if(rotation){
				int angle = 360/frames;
				ResourceManager.rotate(image, i*angle);
			}
			image = ResourceManager.createCompatible(image);
			animation.addFrame(image, time);
		}
	}
	
	/**
	 * Constructs a new static Animation
	 * @param image
	 * - Image to be displayed
	 * @param size
	 * - Size of image
	 */
	Animations(BufferedImage image, int size){
		animation = new Animation();
		BufferedImage newImage = ResourceManager.deepCopy(image);
		if(size != 0){
			newImage = ResourceManager.resize(newImage, size, size);
		}
		newImage = ResourceManager.createCompatible(newImage);
		animation.addFrame(newImage, 0);
	}
	
	/**
	 * Returns the constructed Animation
	 * @return
	 * Animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * This class gets the images from the resource manager and clones them to work on them individually
	 */
	private enum Images{
		BULLET_HIT(new BufferedImage[]{ResourceManager.getBulletHit1(), ResourceManager.getBulletHit2(), ResourceManager.getBulletHit3(), ResourceManager.getBulletHit4(), ResourceManager.getBulletHit5()}),
		BULLET_HIT_CANNON(new BufferedImage[]{ResourceManager.getBulletHitCannon1(), ResourceManager.getBulletHitCannon2(), ResourceManager.getBulletHitCannon3(), ResourceManager.getBulletHitCannon4(), ResourceManager.getBulletHitCannon5()}),
		CANNON(new BufferedImage[]{ResourceManager.getCannonBullet()}),
		SHIELD(new BufferedImage[]{ResourceManager.getShield1(), ResourceManager.getShield2()}),
		EXPLOSION(new BufferedImage[]{ResourceManager.getExplosion1(), ResourceManager.getExplosion1(), ResourceManager.getExplosion2(), ResourceManager.getExplosion3(), ResourceManager.getExplosion4(), ResourceManager.getExplosion5()});
		
		private BufferedImage[] images; // Image array
		
		/**
		 * Constructs a new image array
		 * @param images
		 * Image array
		 */
		Images(BufferedImage[] images){
			this.images = images;
		}
		
		/**
		 * Returns the image array
		 * @return
		 * Image array
		 */
		private BufferedImage[] getImages(){
			return images;
		}
	}
}

