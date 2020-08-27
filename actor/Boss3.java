package ar.zgames.zshot.actor;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;
import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.upgrades.PowerUp;
import ar.zgames.zshot.weapons.PlasmaCannon;

/**
 * Level 3 boss
 */
public class Boss3 extends Actor {
	private int deadTicks; // dead ticks counter
	private final Random random = new Random(); // Generates random numbers
	private final BufferedImage body = ResourceManager.createCompatible(ResourceManager.getBoss3()); // Image for body
	private final BufferedImage sword1 = ResourceManager.createCompatible(ResourceManager.getSword1()); // Image for sword going up
	private final BufferedImage sword2 = ResourceManager.createCompatible(ResourceManager.getSword2()); // Image for sword going down
	private final BufferedImage fireArrow = ResourceManager.createCompatible(ResourceManager.getFireBullet()); // Image for fire arrows
	private final ArrayList<FireBullet> fireBullets = new ArrayList<FireBullet>(); // Array for fire arrows
	private final BufferedImage shieldImage = ResourceManager.createCompatible(ResourceManager.getRockShield()); // Image for rock shield
	private BufferedImage sword; // Current sword image
	private int ticks; // tick counter
	private Player player; // reference to player
	private int shieldHP; // Shield hit points
	private final int shieldDeadTime = 300; // Total ticks the shield remains dead
	private int shieldDeadTicks; // Tick counter for shield death time
	private int shieldX; // Shield X position
	private int shieldY; // Shield Y position
	private final Rectangle[] shieldBounds = new Rectangle[]{new Rectangle(), new Rectangle()}; // Shield bounds for collision detection
	private final Rectangle swordBounds = new Rectangle(); // Sword bounds for collision detection
	private int swordX; // Sword X position
	private int swordY; // Sword Y position
	private boolean giveHp; // Indicates the boss has dropped HP PowerUp
	private boolean swordUp; // Indicates whether the sword will go up or not
	private final PlasmaCannon cannon = new PlasmaCannon(5); // PlasmaCannon weapon
	
	/**
	 * Constructor for level 3 boss
	 * @param x
	 * - X starting position
	 * @param y
	 * - Y starting position
	 * @param player
	 * - Player object
	 * @param level
	 * - GameLevel object
	 */
	public Boss3(int x, int y,  Player player, GameLevel level) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.level = level;
		hitPoints = 3000;
		deadTime = 3000;
		shieldHP = 1000;
		shieldY = 480;
		shieldX = x + 133;
		swordY = 1129;
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
			g.drawImage(body, (int) x, (int) y, null);
			ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
			g.setComposite(ac);
		}else{
			g.drawImage(body, (int) x, (int) y, null);
		}
		
		// Draw RockShield
		alpha = 1;
		if (isShieldDead())
			alpha = 1 - (float) shieldDeadTicks / shieldDeadTime;
		if (bossIntro < 3000)
			alpha = (float) bossIntro / 3000;
		if(alpha != 1){
			ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g.setComposite(ac);
			g.drawImage(shieldImage, shieldX - shieldImage.getWidth(null)/2, shieldY - shieldImage.getHeight(null)/2, null);
			ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
			g.setComposite(ac);
		}else{
			g.drawImage(shieldImage, shieldX - shieldImage.getWidth(null)/2, shieldY - shieldImage.getHeight(null)/2, null);
		}
		
		// Draw Sword
		g.drawImage(sword, swordX, swordY, null);
		
		// Draw fire bullets
		for(int i = 0; i < fireBullets.size(); i++){
			fireBullets.get(i).draw(g);
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
		}else{
			shieldTick();
			swordTick();
			shoot();
			dropPowerUp();
		}
		
		checkCollisions(level.getBullets());
		
		for(int i = 0; i < fireBullets.size(); i++){
			fireBullets.get(i).tick();
			if (fireBullets.get(i).isRemoved()) {
				fireBullets.remove(i--);
			}
		}
	}

	/**
	 * Shoots with weapons according to frequency
	 */
	private void shoot(){
		int freq;
		if(hitPoints > 1500)
			freq = 600;
		else
			freq = 300;
		if(ticks % freq == 0){
			level.getSoundManager().playFire();
			fireBullets.add(new FireBullet((int)x, (int)y + 80));
		}
		if(ticks % freq == freq/2){
			level.getSoundManager().playFire();
			fireBullets.add(new FireBullet((int)x, (int)y + 170));
		}
		if(ticks % freq*4 == 0){
			cannon.shoot((int)x + 130, (int)y + 150, (int)player.getX(), (int)player.getY(), false, level);
		}
	}
	
	@Override
	protected void hurt(int damage) {
		hitPoints -= damage;
		if (isDead()) {
			++deadTicks;
			hitPoints = 0;
		}
	}
	
	/**
	 * Creates a bullet hit effect for fire arrows
	 * @param b
	 * - FireBullet object
	 */
	private void hitEffect(FireBullet b){
		Effect e = level.getObjectPool().getEffect();
		e.set(b.x + 15, b.y + 10, b.xa/5, b.ya/5, b.hitAnimation, 85);
		level.addEffect(e);
	}
	
	/**
	 * Checks if shield is dead
	 * @return
	 * True if shield is dead, false otherwise
	 */
	private boolean isShieldDead(){
		if(shieldHP > 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Process the shield's tick
	 */
	private void shieldTick(){
		if(shieldDeadTicks != 0){
			shieldDeadTicks++;
			if(shieldDeadTicks > shieldDeadTime){
				shieldX = 0 - shieldImage.getWidth();
				shieldY = 480;
				shieldHP = 1000;
				shieldDeadTicks = 0;
				level.addPowerUp(new PowerUp(1400, 480, 2, player, level));
			}
		}else{
			int speed;
			if(hitPoints > 1500)
				speed = 1;
			else
				speed = 2;
			
			if(player.getX() > 1100){
				if(shieldY > player.getY()){
					shieldY -= speed;
					if(shieldY < player.getY())
						shieldY = (int)player.getY();
				}
				if(shieldY < player.getY()){
					shieldY += speed;
					if(shieldY > player.getY())
						shieldY = (int)player.getY();
				}
			}else{
				if(shieldY < 480){
					shieldY += speed;
					if(shieldY > 480)
						shieldY = 480;
				}
				if(shieldY > 480){
					shieldY -= speed;
					if(shieldY < 480)
						shieldY = 480;
				}
			}
			
			if(shieldX < x + 133){
				shieldX += speed;
				if(shieldX > x + 133)
					shieldX = (int)x + 133;
			}
		}
	}
	
	/**
	 * Process the sword's tick
	 */
	private void swordTick(){
		int speed;
		
		if(hitPoints > 1500)
			speed = 3;
		else
			speed = 6;
		
		if(swordY < -1200){
			sword = sword2;
			swordX = random.nextInt(1400);
			swordUp = false;
			level.getSoundManager().playSword();
		}
		if(swordY > 1128){
			sword = sword1;
			swordX = random.nextInt(1400);
			swordUp = true;
			level.getSoundManager().playSword();
		}
		
		if(swordUp)
			speed *= -1;
		
		swordY += speed;
	}
	
	/**
	 * Drops a Power Up when HP reaches a certain amount
	 */
	private void dropPowerUp(){
		if(giveHp == false && hitPoints <= 1500){
			level.addPowerUp(new PowerUp((int)x, (int)y, 0, player, level));
			giveHp = true;
		}
	}
	
	/**
	 * Checks collisions with player and bullets
	 */
	private void checkCollisions(ArrayList<Bullet> bullets){
		bounds.setBounds((int)x + 60, (int)y + 70, 180, 120);
		swordBounds.setBounds(swordX + 50, swordY + 125, 115, 750);
		shieldBounds[0].setBounds(shieldX - 300, shieldY - 150, 600, 300);
		shieldBounds[1].setBounds(shieldX - 150, shieldY - 300, 300, 600);
		
		if (!player.isInvincible() && !player.isDead() && !isDead()) {
			for (int i = 0; i < shieldBounds.length; i++) {
				if(isShieldDead())
					break;
				if (player.getBounds().intersects(shieldBounds[i])) {
					player.hurt(300);
					player.invincibleTime = 500;
				}
			}
			if(player.getBounds().intersects(swordBounds)) {
				player.hurt(200);
				player.invincibleTime = 500;
			}
			if(player.getBounds().intersects(bounds)) {
				player.hurt(250);
				player.invincibleTime = 500;
			}
		}
		
		Bullet b;
		for (int i = 0; i < bullets.size(); i++) {
			b = bullets.get(i);
			if(!b.isPlayerBullet())
				continue;
			
			if (!isShieldDead()){
				for (int j = 0; j < shieldBounds.length; j++) {
					if(b.getBounds().intersects(shieldBounds[j])){
						shieldHurt(b.getDamage());
						b.createHitEffect();
						b.remove();
						break;
					}
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
	
	/**
	 * @param damage
	 */
	private void shieldHurt(int damage){
		shieldHP -= damage;
		if(shieldHP <= 0){
			shieldHP = 0;
			shieldDeadTicks++;
		}
	}
	
	/**
	 * Actions to take while boss is dying
	 */
	private void death() {
		if(deadTicks % 20 == 0){
			int x, y;
			x = (int)this.x - body.getWidth(null) + RANDOM.nextInt((int)this.x + body.getWidth(null));
			y = (int)this.y - body.getHeight(null) + RANDOM.nextInt((int)this.y + body.getHeight(null));
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
		
		if(shieldDeadTicks < shieldDeadTime){
			shieldHurt(2000);
		}
		
		if (deadTicks > deadTime){
			player.addScrap(10000);
			level.getSoundManager().playBossDeath();
			remove();
			level.win();
		}
	}
	
	private class FireBullet extends Bullet {
		private final float xSpeed = 2.5f;
		private final float yAcc = 0.04f;
		private final float yMaxSpeed = 3f;;
		private final int damage = 50;
		
		private FireBullet(int xo, int yo){
			this.x = xo;
			this.y = yo;
			this.xa = -xSpeed;
			this.ya = 0;
			this.hitAnimation = Animations.BULLET_HIT_CANNON1.getAnimation();
		}
		
		@Override
		public void tick(){
			move();
			checkCollisions();
		}
		
		@Override
		public void draw(Graphics2D g) {
			g.drawImage(fireArrow, (int)x, (int)y, null);
		}
		
		@Override
		public void move(){
			if(player.getX() < this.x){
				if(player.getY() > this.y)
					ya += yAcc;
				else
					ya -= yAcc;
				if(ya > yMaxSpeed)
					ya = yMaxSpeed;
				if(ya < -yMaxSpeed)
					ya = -yMaxSpeed;
			}
			this.y += ya;
			this.x -= xSpeed;
		}
		
		/**
		 * Checks collisions with player
		 */
		private void checkCollisions(){
			bounds.setBounds((int)x + 15, (int)y + 10, 70, 30);
			if(bounds.intersects(player.getBounds())){
				player.hurt(damage);
				hitEffect(this);
				this.remove();
			}
		}
	}
}
