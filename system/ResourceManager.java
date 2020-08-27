package ar.zgames.zshot.system;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Handles image loading
 */
public class ResourceManager {

	private static BufferedImage starfield;
	private static BufferedImage title;
	private static BufferedImage bulletHit1;
	private static BufferedImage bulletHit2;
	private static BufferedImage bulletHit3;
	private static BufferedImage bulletHit4;
	private static BufferedImage bulletHit5;
	private static BufferedImage bulletHitCannon1;
	private static BufferedImage bulletHitCannon2;
	private static BufferedImage bulletHitCannon3;
	private static BufferedImage bulletHitCannon4;
	private static BufferedImage bulletHitCannon5;
	private static BufferedImage explosion1;
	private static BufferedImage explosion2;
	private static BufferedImage explosion3;
	private static BufferedImage explosion4;
	private static BufferedImage explosion5;
	private static BufferedImage cannonBullet;
	private static BufferedImage gatlingBullet;
	private static BufferedImage spreadBullet;
	private static BufferedImage blasterBullet;
	private static BufferedImage streamBullet;
	private static BufferedImage fireBullet;
	private static BufferedImage drone1;
	private static BufferedImage drone2;
	private static BufferedImage tank1;
	private static BufferedImage ram1;
	private static BufferedImage ram2;
	private static BufferedImage bat1;
	private static BufferedImage kaiser1;
	private static BufferedImage titan1;
	private static BufferedImage rock1;
	private static BufferedImage mine1;
	private static BufferedImage mineRam1;
	private static BufferedImage hyperion1;
	private static BufferedImage playerDead;
	private static BufferedImage player100;
	private static BufferedImage player75;
	private static BufferedImage player50;
	private static BufferedImage player25;
	private static BufferedImage cannon;
	private static BufferedImage move;
	private static BufferedImage damageReduction;
	private static BufferedImage gatling;
	private static BufferedImage spread;
	private static BufferedImage cannonNull;
	private static BufferedImage spreadNull;
	private static BufferedImage gatlingSelected;
	private static BufferedImage cannonSelected;
	private static BufferedImage spreadSelected;
	private static BufferedImage shield1;
	private static BufferedImage shield2;
	private static BufferedImage cursor1;
	private static BufferedImage hpUp;
	private static BufferedImage enUp;
	private static BufferedImage lifeUp;
	private static BufferedImage scrapUp;
	private static BufferedImage weaponUp;
	private static BufferedImage moveUp;
	private static BufferedImage boss1;
	private static BufferedImage eyeClosed;
	private static BufferedImage eyeOpen;
	private static BufferedImage eyeYellow;
	private static BufferedImage eyeRed;
	private static BufferedImage eyeBlue;
	private static BufferedImage boss2;
	private static BufferedImage boss3;
	private static BufferedImage sword1;
	private static BufferedImage sword2;
	private static BufferedImage rockShield;
	private static BufferedImage titleBall;
	private static BufferedImage titleRay;
	private static BufferedImage titleZ1;
	private static BufferedImage titleZ2;
	private static BufferedImage titleZ3;
	private static BufferedImage titleShot;
	private static BufferedImage stage1Intro;
	private static BufferedImage stage2Intro;
	private static BufferedImage stage3Intro;
	private static BufferedImage blueSky;
	private static BufferedImage mountain;
	private static BufferedImage mountain2;
	private static BufferedImage fireCave;
	private static BufferedImage leftArrow;
	private static BufferedImage rightArrow;
	private static BufferedImage unknown;
	private static BufferedImage deepSpace;
	private static BufferedImage greenDreams;
	private static BufferedImage finalSunset;
	
	public static BufferedImage getUnknown() {
		if (unknown == null)
			unknown = loadImage("sprites/planets/Unknown.png");
		return unknown;
	}
	
	public static BufferedImage getDeepSpace() {
		if (deepSpace == null)
			deepSpace = loadImage("sprites/planets/DeepSpace.png");
		return deepSpace;
	}
	
	public static BufferedImage getGreenDreams() {
		if (greenDreams == null)
			greenDreams = loadImage("sprites/planets/GreenDreams.png");
		return greenDreams;
	}
	
	public static BufferedImage getFinalSunset() {
		if (finalSunset == null)
			finalSunset = loadImage("sprites/planets/FinalSunset.png");
		return finalSunset;
	}
	
	public static BufferedImage getLeftArrow() {
		if (leftArrow == null)
			leftArrow = loadImage("images/LeftArrow.png");
		return leftArrow;
	}
	
	public static BufferedImage getRightArrow() {
		if (rightArrow == null)
			rightArrow = loadImage("images/RightArrow.png");
		return rightArrow;
	}

	public static BufferedImage getBlueSky() {
		if (blueSky == null)
			blueSky = loadImage("images/backgrounds/BlueSky.jpg");
		return blueSky;
	}
	
	public static BufferedImage getMountain() {
		if (mountain == null)
			mountain = loadImage("images/backgrounds/Mountain.png");
		return mountain;
	}
	
	public static BufferedImage getMountain2() {
		if (mountain2 == null)
			mountain2 = loadImage("images/backgrounds/Mountain2.png");
		return mountain2;
	}
	
	public static BufferedImage getFireCave() {
		if (fireCave == null)
			fireCave = loadImage("images/backgrounds/FireCave.png");
		return fireCave;
	}

	public static BufferedImage getStage1Intro() {
		if (stage1Intro == null)
			stage1Intro = loadImage("images/backgrounds/stage1intro.jpg");
		return stage1Intro;
	}
	
	public static BufferedImage getStage2Intro() {
		if (stage2Intro == null)
			stage2Intro = loadImage("images/backgrounds/stage2intro.jpg");
		return stage2Intro;
	}
	
	public static BufferedImage getStage3Intro() {
		if (stage3Intro == null)
			stage3Intro = loadImage("images/backgrounds/stage3intro.jpg");
		return stage3Intro;
	}
	
	public static BufferedImage getTitleRay() {
		if (titleRay == null)
			titleRay = loadImage("images/titleray.png");
		return titleRay;
	}
	
	public static BufferedImage getTitleBall() {
		if (titleBall == null)
			titleBall = loadImage("images/titleball.png");
		return titleBall;
	}
	
	public static BufferedImage getTitleZ1() {
		if (titleZ1 == null)
			titleZ1 = loadImage("images/titlez1.png");
		return titleZ1;
	}
	
	public static BufferedImage getTitleZ2() {
		if (titleZ2 == null)
			titleZ2 = loadImage("images/titlez2.png");
		return titleZ2;
	}
	
	public static BufferedImage getTitleZ3() {
		if (titleZ3 == null)
			titleZ3 = loadImage("images/titlez3.png");
		return titleZ3;
	}
	
	public static BufferedImage getTitleShot() {
		if (titleShot == null)
			titleShot = loadImage("images/titleshot.png");
		return titleShot;
	}
	
	public static BufferedImage getHpUp() {
		if (hpUp == null)
			hpUp = loadImage("images/HpUp.png");
		return hpUp;
	}
	
	public static BufferedImage getBoss1() {
		if (boss1 == null)
			boss1 = loadImage("sprites/bosses/Boss1.png");
		return boss1;
	}
	
	public static BufferedImage getEyeClosed() {
		if (eyeClosed == null)
			eyeClosed = loadImage("sprites/bosses/EyeClosed.png");
		return eyeClosed;
	}	
	
	public static BufferedImage getEyeOpen() {
		if (eyeOpen == null)
			eyeOpen = loadImage("sprites/bosses/EyeOpen.png");
		return eyeOpen;
	}
	
	public static BufferedImage getEyeBlue() {
		if (eyeBlue == null)
			eyeBlue = loadImage("sprites/bosses/EyeBlue.png");
		return eyeBlue;
	}
	
	public static BufferedImage getEyeYellow() {
		if (eyeYellow == null)
			eyeYellow = loadImage("sprites/bosses/EyeYellow.png");
		return eyeYellow;
	}
	
	public static BufferedImage getEyeRed() {
		if (eyeRed == null)
			eyeRed = loadImage("sprites/bosses/EyeRed.png");
		return eyeRed;
	}
	
	public static BufferedImage getBoss2() {
		if (boss2 == null)
			boss2 = loadImage("sprites/bosses/Boss2.png");
		return boss2;
	}
	
	public static BufferedImage getBoss3() {
		if (boss3 == null)
			boss3 = loadImage("sprites/bosses/Boss3.png");
		return boss3;
	}
	
	public static BufferedImage getSword1() {
		if (sword1 == null)
			sword1 = loadImage("sprites/bosses/Sword1.png");
		return sword1;
	}
	
	public static BufferedImage getSword2() {
		if (sword2 == null)
			sword2 = loadImage("sprites/bosses/Sword2.png");
		return sword2;
	}
	
	public static BufferedImage getRockShield() {
		if (rockShield == null)
			rockShield = loadImage("sprites/bosses/RockShield.png");
		return rockShield;
	}

	public static BufferedImage getEnUp() {
		if (enUp == null)
			enUp = loadImage("images/ShieldUp.png");
		return enUp;
	}

	public static BufferedImage getLifeUp() {
		if (lifeUp == null)
			lifeUp = loadImage("images/LifeUp.png");
		return lifeUp;
	}

	public static BufferedImage getScrapUp() {
		if (scrapUp == null)
			scrapUp = loadImage("images/ScrapUp.png");
		return scrapUp;
	}

	public static BufferedImage getWeaponUp() {
		if (weaponUp == null)
			weaponUp = loadImage("images/WeaponUp.png");
		return weaponUp;
	}

	public static BufferedImage getMoveUp() {
		if (moveUp == null)
			moveUp = loadImage("images/MoveUp.png");
		return moveUp;
	}

	public static BufferedImage getCannonSelected() {
		if (cannonSelected == null)
			cannonSelected = loadImage("images/CannonSelected.png");
		return cannonSelected;
	}
	
	public static BufferedImage getSpreadSelected() {
		if (spreadSelected == null)
			spreadSelected = loadImage("images/SpreadSelected.png");
		return spreadSelected;
	}
	
	public static BufferedImage getGatlingSelected() {
		if (gatlingSelected == null)
			gatlingSelected = loadImage("images/GatlingSelected.png");
		return gatlingSelected;
	}
	
	public static BufferedImage getCursor(){
		if (cursor1 == null)
			cursor1 = loadImage("sprites/player/cursor.png");
		return cursor1;
	}
	
	public static BufferedImage getShield1() {
		if (shield1 == null)
			shield1 = loadImage("sprites/player/Shield1.png");
		return shield1;
	}

	public static BufferedImage getShield2() {
		if (shield2 == null)
			shield2 = loadImage("sprites/player/Shield2.png");
		return shield2;
	}
	
	public static BufferedImage getCannon() {
		if (cannon == null)
			cannon = loadImage("images/Cannon.png");
		return cannon;
	}
	
	public static BufferedImage getMove() {
		if (move == null)
			move = loadImage("images/Move.png");
		return move;
	}
	
	public static BufferedImage getDamageReduction() {
		if (damageReduction == null)
			damageReduction = loadImage("images/DamageReduction.png");
		return damageReduction;
	}
	
	public static BufferedImage getSpread() {
		if (spread == null)
			spread = loadImage("images/Spread.png");
		return spread;
	}	
	
	public static BufferedImage getGatling() {
		if (gatling == null)
			gatling = loadImage("images/Gatling.png");
		return gatling;
	}	
	
	public static BufferedImage getCannonNull() {
		if (cannonNull == null)
			cannonNull = loadImage("images/CannonNull.png");
		return cannonNull;
	}
	
	public static BufferedImage getSpreadNull() {
		if (spreadNull == null)
			spreadNull = loadImage("images/SpreadNull.png");
		return spreadNull;
	}	
	
	public static BufferedImage getStarfield() {
		if (starfield == null)
			starfield = loadImage("images/backgrounds/Starfield.png");
		return starfield;
	}

	public static BufferedImage getTitle() {
		if (title == null)
			title = loadImage("images/backgrounds/Title.jpg");
		return title;
	}

	public static BufferedImage getBulletHit1() {
		if (bulletHit1 == null)
			bulletHit1 = loadImage("sprites/bullets/BulletHit1.png");
		return bulletHit1;
	}
	
	public static BufferedImage getExplosion1() {
		if (explosion1 == null)
			explosion1 = loadImage("effects/explosions/Explosion1.png");
		return explosion1;
	}
	
	public static BufferedImage getExplosion2() {
		if (explosion2 == null)
			explosion2 = loadImage("effects/explosions/Explosion2.png");
		return explosion2;
	}
	
	public static BufferedImage getExplosion3() {
		if (explosion3 == null)
			explosion3 = loadImage("effects/explosions/Explosion3.png");
		return explosion3;
	}
	
	public static BufferedImage getExplosion4() {
		if (explosion4 == null)
			explosion4 = loadImage("effects/explosions/Explosion4.png");
		return explosion4;
	}
	
	public static BufferedImage getExplosion5() {
		if (explosion5 == null)
			explosion5 = loadImage("effects/explosions/Explosion5.png");
		return explosion5;
	}

	public static BufferedImage getBulletHit2() {
		if (bulletHit2 == null)
			bulletHit2 = loadImage("sprites/bullets/BulletHit2.png");
		return bulletHit2;
	}

	public static BufferedImage getBulletHit3() {
		if (bulletHit3 == null)
			bulletHit3 = loadImage("sprites/bullets/BulletHit3.png");
		return bulletHit3;
	}

	public static BufferedImage getBulletHit4() {
		if (bulletHit4 == null)
			bulletHit4 = loadImage("sprites/bullets/BulletHit4.png");
		return bulletHit4;
	}

	public static BufferedImage getBulletHit5() {
		if (bulletHit5 == null)
			bulletHit5 = loadImage("sprites/bullets/BulletHit5.png");
		return bulletHit5;
	}

	public static BufferedImage getBulletHitCannon1() {
		if (bulletHitCannon1 == null)
			bulletHitCannon1 = loadImage("sprites/bullets/BulletHitCannon1.png");
		return bulletHitCannon1;
	}

	public static BufferedImage getBulletHitCannon2() {
		if (bulletHitCannon2 == null)
			bulletHitCannon2 = loadImage("sprites/bullets/BulletHitCannon2.png");
		return bulletHitCannon2;
	}

	public static BufferedImage getBulletHitCannon3() {
		if (bulletHitCannon3 == null)
			bulletHitCannon3 = loadImage("sprites/bullets/BulletHitCannon3.png");
		return bulletHitCannon3;
	}

	public static BufferedImage getBulletHitCannon4() {
		if (bulletHitCannon4 == null)
			bulletHitCannon4 = loadImage("sprites/bullets/BulletHitCannon4.png");
		return bulletHitCannon4;
	}

	public static BufferedImage getBulletHitCannon5() {
		if (bulletHitCannon5 == null)
			bulletHitCannon5 = loadImage("sprites/bullets/BulletHitCannon5.png");
		return bulletHitCannon5;
	}

	public static BufferedImage getCannonBullet() {
		if (cannonBullet == null)
			cannonBullet = loadImage("sprites/bullets/Cannon1.png");
		return cannonBullet;
	}

	public static BufferedImage getGatlingBullet() {
		if (gatlingBullet == null)
			gatlingBullet = loadImage("sprites/bullets/Gatling1.png");
		return gatlingBullet;
	}

	public static BufferedImage getSpreadBullet() {
		if (spreadBullet == null)
			spreadBullet = loadImage("sprites/bullets/Spread1.png");
		return spreadBullet;
	}
	
	public static BufferedImage getBlasterBullet() {
		if (blasterBullet == null)
			blasterBullet = loadImage("sprites/bullets/Blaster1.png");
		return blasterBullet;
	}
	
	public static BufferedImage getStreamBullet() {
		if (streamBullet == null)
			streamBullet = loadImage("sprites/bullets/Stream1.png");
		return streamBullet;
	}
	
	public static BufferedImage getFireBullet() {
		if (fireBullet == null)
			fireBullet = loadImage("sprites/bullets/FireArrow1.png");
		return fireBullet;
	}

	public static BufferedImage getDrone1() {
		if (drone1 == null)
			drone1 = loadImage("sprites/enemies/Drone1.png");
		return drone1;
	}
	
	public static BufferedImage getDrone2() {
		if (drone2 == null)
			drone2 = loadImage("sprites/enemies/Drone2.png");
		return drone2;
	}

	public static BufferedImage getTank1() {
		if (tank1 == null)
			tank1 = loadImage("sprites/enemies/Tank1.png");
		return tank1;
	}
	
	public static BufferedImage getRam1() {
		if (ram1 == null)
			ram1 = loadImage("sprites/enemies/Ram1.png");
		return ram1;
	}
	
	public static BufferedImage getRam2() {
		if (ram2 == null)
			ram2 = loadImage("sprites/enemies/Ram2.png");
		return ram2;
	}
	
	public static BufferedImage getBat1() {
		if (bat1 == null)
			bat1 = loadImage("sprites/enemies/Bat1.png");
		return bat1;
	}
	
	public static BufferedImage getKaiser1() {
		if (kaiser1 == null)
			kaiser1 = loadImage("sprites/enemies/Kaiser1.png");
		return kaiser1;
	}
	
	public static BufferedImage getTitan1() {
		if (titan1 == null)
			titan1 = loadImage("sprites/enemies/Titan1.png");
		return titan1;
	}
	
	public static BufferedImage getRock1() {
		if (rock1 == null)
			rock1 = loadImage("sprites/enemies/Rock1.png");
		return rock1;
	}
	
	public static BufferedImage getMine1() {
		if (mine1 == null)
			mine1 = loadImage("sprites/enemies/Mine1.png");
		return mine1;
	}
	
	public static BufferedImage getMineRam1() {
		if (mineRam1 == null)
			mineRam1 = loadImage("sprites/enemies/MineRam1.png");
		return mineRam1;
	}
	
	public static BufferedImage getHyperion1() {
		if (hyperion1 == null)
			hyperion1 = loadImage("sprites/enemies/Hyperion1.png");
		return hyperion1;
	}

	public static BufferedImage getPlayerDead() {
		if (playerDead == null)
			playerDead = loadImage("sprites/player/Player_Dead.png");
		return playerDead;
	}

	public static BufferedImage getPlayer100() {
		if (player100 == null)
			player100 = loadImage("sprites/player/Player100.png");
		return player100;
	}

	public static BufferedImage getPlayer75() {
		if (player75 == null)
			player75 = loadImage("sprites/player/Player75.png");
		return player75;
	}

	public static BufferedImage getPlayer50() {
		if (player50 == null)
			player50 = loadImage("sprites/player/Player50.png");
		return player50;
	}

	public static BufferedImage getPlayer25() {
		if (player25 == null)
			player25 = loadImage("sprites/player/Player25.png");
		return player25;
	}

	/**
	 * Loads an image from the specified file path
	 * @param filePath
	 * 		- File path to the image
	 * @return new BufferedImage object
	 */
	private static BufferedImage loadImage(String filePath) {
		try {
			return ImageIO.read(ResourceManager.class.getClassLoader()
					.getResource(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Resizes the specified BufferedImage to the specified width and height
	 * 
	 * @param image
	 *            - image to be resized
	 * @param width
	 * 			  - new width  
	 * @param height
	 * 			  - new height   
	 * @return Resized BufferedImage     
	 */
	public static BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height,
				image.getType());
		Graphics2D g = resizedImage.createGraphics();
		try {
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			g.drawImage(image, 0, 0, width, height, null);
		} finally {
			g.dispose();
		}
		return resizedImage;
	}
	/**
	 * Rotates the specified BufferedImage clockwise by the specified angle
	 * 
	 * @param image
	 *            - image to be rotated
	 * @param angle
	 * 			  - angle to rotate by (in degrees)           
	 */
	public static void rotate(BufferedImage image, int angle) {
		Graphics2D g = image.createGraphics();
		try {
			AffineTransform tx = AffineTransform.getRotateInstance(
					Math.toRadians(angle), image.getWidth() / 2,
					image.getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_BILINEAR);
			g.drawImage(op.filter(image, null), 0, 0, null);
		} finally {
			g.dispose();
		}
	}

	/**
	 * Creates a new BufferedImage compatible with the current graphics configuration
	 * from a BufferedImage passed as a parameter
	 * This effectively causes the original image to be accelerated, resulting in better performance
	 * 
	 * @param image
	 *            - image to be transformed
	 * @return Compatible BufferedImage
	 */
	public static BufferedImage createCompatible(BufferedImage image) {
		GraphicsConfiguration gc = ScreenManager.getGraphicConfig();
		if (image.getColorModel().equals(gc.getColorModel())) {
			image.setAccelerationPriority(1.0f);
			return image;
		} else {
			BufferedImage compatibleImage = gc.createCompatibleImage(
					image.getWidth(null), image.getHeight(null),
					image.getTransparency());
			Graphics2D g = compatibleImage.createGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			return compatibleImage;
		}
	}
	
	/**
	 * Creates a copy of a BufferedImage, to be able to perform operations on it without altering
	 * the original image object in case it needs to be reused
	 * 
	 * @param image
	 *            - BufferedImage to be copied
	 * @return new BufferedImage
	 */
	public static BufferedImage deepCopy(BufferedImage image) {
		ColorModel cm = image.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = image.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
}
