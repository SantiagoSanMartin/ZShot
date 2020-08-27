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
import ar.zgames.zshot.weapons.GatlingGun;
import ar.zgames.zshot.weapons.SpreadGun;
import ar.zgames.zshot.weapons.Weapon;

/**
 * Level 1 boss
 */
public class Boss1 extends Actor {
	private final Weapon[] weapons = new Weapon[2]; // weapons
	private int ticks; // tick counter
	private int deadTicks; // dead ticks counter
	private Player player; // reference to player
	private final BufferedImage eyeOpen = ResourceManager.getEyeOpen(); // image for open eye
	private final BufferedImage eyeClosed = ResourceManager.getEyeClosed(); // image for closed eye
	private final BufferedImage body = ResourceManager.getBoss1(); // image for body
	private Rectangle[] areas; // collision areas
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
	public Boss1(int x, int y, Player player, GameLevel level) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.level = level;
		weapons[0] = new GatlingGun(3);
		weapons[1] = new SpreadGun(3);
		hitPoints = 2000;
		deadTime = 3000;
		areas = new Rectangle[2];
		areas[0] = new Rectangle();
		areas[1] = new Rectangle();
		giveHp = false;
		giveEn = false;
	}

	@Override
	public void draw(Graphics2D g) {
		int bossIntro = level.getBossIntro();

		float alpha = 1;
		if (isDead())
			alpha = 1 - (float) deadTicks / deadTime;
		if (bossIntro < 3000)
			alpha = (float) bossIntro / 3000;
		if(alpha != 1){
			AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g.setComposite(ac);
			g.drawImage(body, (int) x, (int) y, null);
			g.drawImage(eyeClosed, (int) x-10, (int) y + 210, null);
			ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
			g.setComposite(ac);
		}else{
			g.drawImage(body, (int) x, (int) y, null);
			if (isInvincible())
				g.drawImage(eyeClosed, (int) x-10, (int) y + 210, null);
			else
				g.drawImage(eyeOpen, (int) x-10, (int) y + 210, null);
		}

	}

	@Override
	public void tick() {
		ticks++;
		move();
		checkCollisions();
		if (deadTicks != 0) {
			if(!isRemoved()){
				++deadTicks;
				death();
			}
		} else {
			shoot();
			toggleEye();
			dropPowerUp();
		}
	}
	
	/**
	 * Drops a Power Up when HP reaches a certain amount
	 */
	private void dropPowerUp(){
		if(giveHp == false && ((hitPoints < 1500 && hitPoints > 1000) || hitPoints < 500)){
			level.addPowerUp(new PowerUp((int) x + 50, (int) y + 250, 0, player, level));
			giveHp = true;
		}
		if(giveEn == false && hitPoints < 1000){
			level.addPowerUp(new PowerUp((int) x + 50, (int) y + 250, 1, player, level));
			giveEn = true;
			giveHp = false;
		}
	}
	
	/**
	 * Opens/closes eye
	 */
	private void toggleEye(){
		if (ticks % 5000 == 4000){
			invincibleTime = 1;
			level.getSoundManager().playClose();
			level.addPowerUp(new PowerUp((int) x + 50, (int) y + 250, 2, player, level));
		}
		if(ticks % 5000 == 0){
			invincibleTime = 0;
			level.getSoundManager().playOpen();
		}
	}
	
	/**
	 * Shoots with weapons according to frequency
	 */
	private void shoot(){
		boolean wait;
		if (ticks % 2000 > 1000)
			wait = false;
		else
			wait = true;
		if (!wait) {
			if (ticks % 50 == 0) {
				weapons[0].shoot((int) x + 50, (int) y + 50,
						(int) player.getX() + player.sprite.getImage().getWidth(null)/2, 
						(int) player.getY() + player.sprite.getImage().getHeight(null)/2, 
						false, level);
			}
			if (ticks % 50 == 25) {
				weapons[0].shoot((int) x + 50, (int) y + 450,
						(int) player.getX()
								+ player.sprite.getImage().getWidth(null)
								/ 2, (int) player.getY()
								+ player.sprite.getImage().getHeight(null)
								/ 2, false, level);
			}
		}
		int freq;
		if (hitPoints > 1000)
			freq = 400;
		else
			freq = 200;
		if (ticks % freq == 0) {
			weapons[1].shoot((int) x + 50, (int) y + 250,
					(int) player.getX()
							+ player.sprite.getImage().getWidth(null) / 2,
					(int) player.getY()
							+ player.sprite.getImage().getHeight(null) / 2,
					false, level);
		}
	}

	/**
	 * Actions to take while boss is dying
	 */
	private void death() {
		if(deadTicks % 20 == 0){
			int x, y;
			x = (int)this.x + RANDOM.nextInt(body.getWidth(null));
			y = (int)this.y + RANDOM.nextInt(body.getHeight(null));
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
		xa = 0.00002f * deadTicks;
		ya = 0.0002f * deadTicks;
		if (deadTicks > deadTime){
			player.addScrap(5000);
			level.getSoundManager().playBossDeath();
			remove();
			level.win();
		}
	}

	/**
	 * Returns multiple collision areas
	 * @return
	 * Rectangle array for collision detection
	 */
	protected Rectangle[] getBoundAreas() {
		areas[0].setBounds((int) x-10, (int) y + 210, eyeOpen.getWidth(), eyeOpen.getHeight());
		areas[1].setBounds((int) x, (int) y, body.getWidth(), body.getHeight());
		return areas;
	}

	@Override
	public void hurt(int damage) {
		if (!isInvincible()) {
			hitPoints -= damage;
			if (isDead()) {
				++deadTicks;
				hitPoints = 0;
			}
		}
	}

	/**
	 * Moves the boss and sets speed according to position
	 */
	private void move() {
		x += xa;
		y += ya;

		if (x > 1000)
			xa -= 0.01f;
		else
			xa += 0.01f;
		if (y > 200)
			ya -= 0.01f;
		else
			ya += 0.01f;
	}

	/**
	 * Checks collisions with player and bullets
	 */
	public void checkCollisions() {
		if (!player.isInvincible() && !player.isDead() && !isDead()) {
			Rectangle[] bounds = getBoundAreas();
			for (int i = 0; i < bounds.length; i++) {
				if (player.getBounds().intersects(bounds[i])) {
					player.hurt(250);
					player.invincibleTime = 500;
					break;
				}
			}
		}
		
		ArrayList<Bullet> bullets = level.getBullets();
		Bullet b;
		for (int i = 0; i < bullets.size(); i++) {
			b = bullets.get(i);
			if (isDead() || !b.isPlayerBullet())
				continue;
			Rectangle[] areas = getBoundAreas();
			for (int j = 0; j < areas.length; j++) {
				if (areas[j].intersects(b.getBounds())) {
					if (j == 0)
						hurt(b.getDamage());
					b.createHitEffect();
					b.remove();
					break;
				}
			}
		}
	}
	
	@Override
	public void remove() {
		removed = true;
	}
}
