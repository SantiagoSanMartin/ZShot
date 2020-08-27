package ar.zgames.zshot.actor;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;
import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.upgrades.PowerUp;
import ar.zgames.zshot.weapons.Blaster;
import ar.zgames.zshot.weapons.Cannon;
import ar.zgames.zshot.weapons.GatlingGun;
import ar.zgames.zshot.weapons.Stream;
import ar.zgames.zshot.weapons.Weapon;

/**
 * Level 1 boss
 */
public class Boss2 extends Actor {
	private static int eyeTicks; // tick counter for eye movement
	private final Weapon[] weapons = new Weapon[4]; // weapons
	private int ticks; // tick counter
	private int moveTicks; // move tick counter
	private int weaponMode; // current weapon for eyes
	private int shootingEye; // eye array position that will shoot
	private int deadTicks; // dead ticks counter
	private Player player; // reference to player
	private ArrayList<Eye> eyes; // eye minions array
	private BufferedImage eye; // current eye image
	private final BufferedImage body = ResourceManager.createCompatible(ResourceManager.getBoss2()); // image for boss
	private final BufferedImage eyeBlue = ResourceManager.createCompatible(ResourceManager.getEyeBlue()); // image for eye with weapon mode 0
	private final BufferedImage eyeYellow = ResourceManager.createCompatible(ResourceManager.getEyeYellow()); // image for eye with weapon mode 1
	private final BufferedImage eyeRed = ResourceManager.createCompatible(ResourceManager.getEyeRed()); // image for eye with weapon mode 2
	private boolean reviveEyes; // Indicates the boss is reviving his eye minions
	private boolean resetEyes; // Indicates the boss is resetting the position of his eye minions
	private int eyeCounter; // counter for handling eyes, it should match the eyes' ID
	private boolean gatherSoundPlayer; // Indicates revive sound has been played
	private boolean giveHp; // indicates when the boss dropped HP power up
	private boolean giveEn; // indicates when the boss dropped EN power up

	/**
	 * Constructor for level 1 boss
	 * @param x
	 * - X starting position
	 * @param y
	 * - Y starting position
	 * @param player
	 * - Player object
	 * @param level
	 * - GameLevel object
	 */
	public Boss2(int x, int y, Player player, GameLevel level) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.level = level;
		reviveEyes = true;
		weapons[0] = new GatlingGun(5);
		weapons[1] = new Stream(2);
		weapons[2] = new Blaster(5);
		weapons[3] = new Cannon(3);
		hitPoints = 7500;
		deadTime = 3000;
		eye = eyeBlue;
		eyes = new ArrayList<Eye>();
		giveHp = false;
		giveEn = false;
	}

	@Override
	public void draw(Graphics2D g) {
		int bossIntro = level.getBossIntro();
		AlphaComposite ac;
		float alpha = 1;
		
		// Draw boss
		if (isDead())
			alpha = 1 - (float) deadTicks / deadTime;
		if (bossIntro < 3000)
			alpha = (float) bossIntro / 3000;
		if(alpha != 1){
			ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g.setComposite(ac);
			g.drawImage(body, (int) x - body.getWidth(null)/2, (int) y - body.getHeight(null)/2, null);
			ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
			g.setComposite(ac);
		}else{
			g.drawImage(body, (int) x - body.getWidth(null)/2, (int) y - body.getHeight(null)/2, null);
		}
		
		// Draw eye minions
		for(int i = 0; i < eyes.size(); i++){
			Eye e = eyes.get(i);
			float alpha2 = alpha;
			if (e.isDead())
				alpha2 = 1 - (float) e.eyeDeadTicks / Eye.DEAD_TIME;
			if(alpha2 != 1){
				ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha2);
				g.setComposite(ac);
				g.drawImage(eye, (int) e.eyeX - eye.getWidth(null)/2, (int) e.eyeY - eye.getHeight(null)/2, null);
				ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
				g.setComposite(ac);
			}else{
				g.drawImage(eye, (int) e.eyeX - eye.getWidth(null)/2, (int) e.eyeY - eye.getHeight(null)/2, null);
			}
		}
	}

	@Override
	public void tick() {
		ticks++;
		if (deadTicks != 0){
			if(!isRemoved()){
				++deadTicks;
				death();
			}
		} else {
			if (reviveEyes) {
				reviveEyes();
			} else if (resetEyes && x == 1400) {
				gatherEyes();
			} else {
				move();
				shoot();
				changeWeaponMode();
				dropPowerUp();
				if (ticks == 6000 || eyes.size() == 0) {
					resetEyes = true;
				}
			}
		}
		checkCollisions(level.getBullets());
		for(int i = 0; i < eyes.size(); i++){
			if(eyes.get(i).eyeDeadTicks != 0) {
				++eyes.get(i).eyeDeadTicks;
				eyes.get(i).death();
				if(eyes.get(i).eyeDeadTicks >= Eye.DEAD_TIME){
					eyes.remove(i--);
				}
			}
		}
	}
	
	/**
	 * Changes active weapon and eye minions' sprite
	 */
	private void changeWeaponMode(){
		if(ticks % 2000 == 0){
			weaponMode++;
			weaponMode %= 3;
			switch(weaponMode){
			case 0:
				eye = eyeBlue;
				break;
			case 1:
				eye = eyeYellow;
				break;
			case 2:
				eye = eyeRed;
				break;
			}
		}
	}
	
	/**
	 * Shoots with weapons according to frequency
	 */
	private void shoot(){
		int shootSpeed;
		if(hitPoints > 5000)
			shootSpeed = 3;
		else if (hitPoints > 2500)
			shootSpeed = 2;
		else
			shootSpeed = 1;
		

		if(eyes.size() > 0){
			switch(weaponMode){
			case 0:
				if(ticks % (25*shootSpeed) == 0){
					shootingEye %= eyes.size();
					if(!eyes.get(shootingEye).isDead()){
						weapons[0].shoot((int) eyes.get(shootingEye).eyeX, 
										 (int) eyes.get(shootingEye).eyeY, 
										 (int) player.getX() + player.sprite.getImage().getWidth(null)/2, 
										 (int) player.getY() + player.sprite.getImage().getHeight(null)/2,
										 false, level);
						shootingEye++;
					}
				}
				break;
			case 1:
				if(ticks % (50*shootSpeed) == 0){
					shootingEye %= eyes.size();
					if(!eyes.get(shootingEye).isDead()){
						weapons[1].shoot((int) eyes.get(shootingEye).eyeX, 
										 (int) eyes.get(shootingEye).eyeY, 
										 (int) player.getX() + player.sprite.getImage().getWidth(null)/2, 
										 (int) player.getY() + player.sprite.getImage().getHeight(null)/2,
										 false, level);
						shootingEye++;
					}
				}
				break;
			case 2:
				if(ticks % (50*shootSpeed) == 0){
					shootingEye %= eyes.size();
					if(!eyes.get(shootingEye).isDead()){
						weapons[2].shoot((int) eyes.get(shootingEye).eyeX, 
										 (int) eyes.get(shootingEye).eyeY, 
										 (int) player.getX() + player.sprite.getImage().getWidth(null)/2, 
										 (int) player.getY() + player.sprite.getImage().getHeight(null)/2,
										 false, level);
						shootingEye++;
					}
				}
				break;
			}
		}
		if(ticks % (250*shootSpeed) == 0)
			weapons[3].shoot((int) x, (int) y, 
							 (int) player.getX() + player.sprite.getImage().getWidth(null)/2, 
							 (int) player.getY() + player.sprite.getImage().getHeight(null)/2,
							 false, level);
	}

	/**
	 * Revives eye minions
	 */
	private void reviveEyes(){
		while(eyeCounter < 5){
			boolean addEye = true;
			eyeCounter++;
			for(int i = 0; i < eyes.size(); i++){
				if(eyeCounter == eyes.get(i).id){
					addEye = false;
				}
			}
			if(addEye){
				eyes.add(new Eye(eyeCounter));
			}
		}
		for(int i = 0; i < eyes.size(); i++)
			eyes.get(i).position();
		if(ticks > 800){
			reviveEyes = false;
			ticks = 0;
			moveTicks = 0;
			eyeTicks = 0;
		}
	}
	
	/**
	 * Gathers all eye minions to center of boss
	 */
	private void gatherEyes(){
		if(!gatherSoundPlayer){
			level.getSoundManager().playEyesGather();
			gatherSoundPlayer = true;
		}
		for(int i = 0; i < eyes.size(); i++){
			eyes.get(i).gather();
		}
		if(isReset()){
			gatherSoundPlayer = false;
			reviveEyes = true;
			resetEyes = false;
			ticks = 0;
			eyeCounter = 0;
			level.addPowerUp(new PowerUp((int)x, (int)y, 2, player, level));
			level.getSoundManager().playEyesRevive();
		}
	}
	
	/**
	 * Drops a Power Up when HP reaches a certain amount
	 */
	private void dropPowerUp(){
		if(giveHp == false && ((hitPoints < 6000 && hitPoints > 4000) || hitPoints < 2000)){
			level.addPowerUp(new PowerUp((int)x, (int)y, 0, player, level));
			giveHp = true;
		}
		if(giveEn == false && hitPoints < 4000){
			level.addPowerUp(new PowerUp((int)x, (int)y, 1, player, level));
			giveEn = true;
			giveHp = false;
		}
	}
	
	/**
	 * Checks if eyes' positions are equal to body position
	 * @return
	 * True if all eyes' positions are equal to body position, false otherwise
	 */
	private boolean isReset() {
		for(int i = 0; i < eyes.size(); i++){
			if(eyes.get(i).eyeX != x || eyes.get(i).eyeY != y)
				return false;
		}
		return true;
	}

	/**
	 * Actions to take while boss is dying
	 */
	private void death() {
		if(deadTicks % 20 == 0){
			int x, y;
			x = (int)this.x - body.getWidth(null)/2 + RANDOM.nextInt(body.getWidth(null));
			y = (int)this.y - body.getHeight(null)/2 + RANDOM.nextInt(body.getHeight(null));
			Effect e;
			if(deadTicks % 160 == 0){
				e = level.getObjectPool().getEffect();
				e.set(x, y, 0, 0, Animations.EXPLOSION_BIG.getAnimation(), 40);
				level.addEffect(e);
				level.getSoundManager().playBigExplosion();
			}
			e = level.getObjectPool().getEffect();
			e.set(x, y, 0, 0, Animations.EXPLOSION_SMALL.getAnimation(), 40);
			level.addEffect(e);
			level.getSoundManager().playSmallExplosion();
		}
		x += 0.00002f * deadTicks;
		y += 0.0002f * deadTicks;
		
		if (deadTicks > deadTime){
			player.addScrap(7500);
			level.getSoundManager().playBossDeath();
			remove();
			level.win();
		}
	}

	@Override
	public void hurt(int damage) {
		hitPoints -= damage;
		if (isDead()) {
			++deadTicks;
			for(int i = 0; i < eyes.size(); i++){
				eyes.get(i).eyeDeadTicks++;
			}
			hitPoints = 0;
		}
	}

	/**
	 * Moves the boss
	 */
	private void move() {
		double t;
		int rW = 600;
		int rH = 380;
		int speedFactor;
		if(hitPoints > 2500)
			speedFactor = 6;
		else
			speedFactor = 4;
		if(ticks % speedFactor == 0)
			moveTicks++;
    	int angularSpeed = 90 + moveTicks % 360;
    	t = Math.toRadians(angularSpeed);
    	x = (float) (800 + Math.sin(t)*rW);
    	y = (float) (480 + Math.cos(t)*rH);
		
		for(int i = 0; i < eyes.size(); i++){
			eyes.get(i).move();
		}
	}

	/**
	 * Checks collisions with player and bullets
	 */
	public void checkCollisions(ArrayList<Bullet> bullets) {
		bounds.setBounds((int)x - body.getWidth(null)/2, (int)y - body.getHeight(null)/2, body.getWidth(null), body.getHeight(null));
		
		if (!player.isInvincible() && !player.isDead() && !isDead()) {
			for (int i = 0; i < eyes.size(); i++) {
				if(eyes.get(i).isDead())
					continue;
				if (player.getBounds().intersects(eyes.get(i).getBounds())) {
					player.hurt(250);
					player.invincibleTime = 500;
				}
			}
			if (player.getBounds().intersects(bounds)) {
				player.hurt(250);
				player.invincibleTime = 500;
			}
		}
		
		Bullet b;
		for (int i = 0; i < bullets.size(); i++) {
			b = bullets.get(i);
			if(!b.isPlayerBullet())
				continue;
			for (int j = 0; j < eyes.size(); j++) {
				if(!eyes.get(j).isDead() && eyes.get(j).getBounds().intersects(b.getBounds())){
					eyes.get(j).hurt(b.getDamage());
					b.createHitEffect();
					b.remove();
					break;
				}
			}
			
			if (!isDead() && !b.isRemoved()){
				if(b.getBounds().intersects(bounds)){
					hurt(b.getDamage());
					b.createHitEffect();
					b.remove();
				}
			}
		}
	}
	
	@Override
	public void remove() {
		removed = true;
	}
	
	/**
	 * Inner class to handle eye minions
	 */
	private class Eye{
		private int id;	// Eye minion id
		private float eyeX; // X axis position
		private float eyeY; // Y axis position
		private int hp; // Hit points
		private int startAngle; // Initial angle in degrees
		private int eyeDeadTicks; // Dead ticks counter
		private static final int DEAD_TIME = 300; // Time in ticks for eye to be removed after it is killed
		private Rectangle bounds; // Bounds for collision detection
		private int boundX; // X position for collision rectangle
		private int boundY; // Y position for collision rectangle
		private int boundW; // Width for collision rectangle
		private int boundH; // Height for collision rectangle
		
		Eye(int id){
			bounds = new Rectangle();
			hp = 500;
			boundX = -eye.getWidth(null)/2 + 10;
			boundY = -eye.getHeight(null)/2 + 10;
			boundW = eye.getWidth(null) - 20;
			boundH = eye.getHeight(null) - 20;
			this.eyeX = x;
			this.eyeY = y;
			this.id = id;
			switch(id){
			case 1:
				startAngle = 180;
				break;
			case 2:
				startAngle = 108;
				break;
			case 3:
				startAngle = 36;
				break;
			case 4:
				startAngle = 324;
				break;
			case 5:
				startAngle = 252;
				break;
			}
		}
		
		/**
		 * Moves eye to the center of the boss
		 */
		public void gather() {
			float xa, ya;
			float xr = eyeX - x;
			float yr = eyeY - y;
			if(xr == 0)
				xr = 0.001f;
			double alpha = Math.atan(yr/xr);
			ya = (float) Math.abs(Math.sin(alpha)*0.5f);
			xa = (float) Math.abs(Math.cos(alpha)*0.5f);
			
			if (eyeX > x) {
				eyeX -= xa;
				if (eyeX < x)
					eyeX = x;
			} else if (eyeX < x) {
				eyeX += xa;
				if (eyeX > x)
					eyeX = x;
			}

			if (eyeY > y) {
				eyeY -= ya;
				if (eyeY < y)
					eyeY = y;
			} else if (eyeY < y) {
				eyeY += ya;
				if (eyeY > y)
					eyeY = y;
			}
		}

		/**
		 * Deals damage to the eye. 
		 * If the boss is invincible, does nothing
		 * 
		 * @param damage
		 *            - Amount of damage to be dealt
		 */
		public void hurt(int damage) {
			if(!isInvincible()){
				hp -= damage;
				if(isDead()){
					eyeDeadTicks++;
					hp = 0;
				}
			}
		}

		/**
		 * Returns the area for collision detection
		 * @return
		 * - Rectangle area for collision detection
		 */
		private Rectangle getBounds(){
			bounds.setBounds((int)eyeX + boundX, (int)eyeY + boundY, boundW, boundH);
			return bounds;
		}
		
		/**
		 * Moves the eyes
		 */
		private void move(){
			double t;
			int r = 200;
			int speedFactor;
			if(hitPoints > 2500)
				speedFactor = 6;
			else
				speedFactor = 4;
			if(ticks % speedFactor == 0)
				Boss2.eyeTicks++;
	    	int angularSpeed = startAngle + (eyeTicks) % 360;
	    	t = Math.toRadians(angularSpeed);
	    	eyeX = (float) (x + Math.sin(t)*r);
	    	eyeY = (float) (y + Math.cos(t)*r);
		}
		
		/**
		 * Positions the eyes around the boss in a pentagon fashion
		 */
		private void position(){
			switch(id){
			case 1:
				if(eyeY > y - 200)
					eyeY -= 0.4f;
				break;
			case 2:
				if(eyeY > y - 61.8f)
					eyeY -= 0.12f;
				if(eyeX < x + 190.2f)
					eyeX += 0.4f;
				break;
			case 3:
				if(eyeY < y + 161.8f)
					eyeY += 0.4f;
				if(eyeX < x + 117.6f)
					eyeX += 0.28f;
				break;
			case 4:
				if(eyeY < y + 161.8f)
					eyeY += 0.4f;
				if(eyeX > x - 117.6f)
					eyeX -= 0.28f;
				break;
			case 5:
				if(eyeY > y - 61.8f)
					eyeY -= 0.12f;
				if(eyeX > x - 190.2f)
					eyeX -= 0.4f;
				break;
			}
		}
		
		/**
		 * Actions to take when eye is dying
		 */
		private void death(){
			if(eyeDeadTicks % 40 == 0){
				int x, y;
				x = (int)eyeX - eye.getWidth(null) + RANDOM.nextInt(eye.getWidth(null));
				y = (int)eyeY - eye.getHeight(null) + RANDOM.nextInt(eye.getHeight(null));
				Effect e;
				if(eyeDeadTicks % 160 == 0){
					e = level.getObjectPool().getEffect();
					e.set(x, y, 0, 0, Animations.EXPLOSION_BIG.getAnimation(), 40);
					level.addEffect(e);
					level.getSoundManager().playBigExplosion();
				}
				e = level.getObjectPool().getEffect();
				e.set(x, y, 0, 0, Animations.EXPLOSION_SMALL.getAnimation(), 40);
				level.addEffect(e);
				level.getSoundManager().playSmallExplosion();
			}
			eyeX += 0.00005f * deadTicks;
			eyeY += 0.0005f * deadTicks;
		}
		
		private boolean isDead(){
			if (hp > 0)
				return false;
			else
				return true;
		}
	}
}
