package ar.zgames.zshot.actor;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;
import ar.zgames.zshot.level.Levels.Enemies;
import ar.zgames.zshot.upgrades.PowerUp;
import ar.zgames.zshot.weapons.Weapon;

/**
 * An Enemy is an actor that moves around the level and performs actions
 */
public class Enemy extends Actor {

	protected Weapon weapon; // Current weapon
	protected Player player; // Reference to Player object
	protected int ticks; // tick counter
	private int deadTicks; // dead time counter
	private Enemies enemy; // enum Enemies constant
	private ArrayList<MoveAction> moveActions; // Array of actions to be performed
	private int currMoveIndex; // Current position in the moveActions array
	private int boundX; // X position for collision rectangle
	private int boundY; // Y position for collision rectangle
	private int boundW; // Width for collision rectangle
	private int boundH; // Height for collision rectangle
	private int scrap; // Scrap to be given if enemies is defeated

	/**
	 * Constructor for Enemy
	 * @param x
	 * - Initial X coordinate position
	 * @param y
	 * - Initial Y coordinate position
	 * @param enemy
	 * - enum Enemies constant
	 */
	public Enemy(int x, int y, Enemies enemy){
		this.x = x;
		this.y = y;
		this.enemy = enemy;
	}
	
	/**
	 * Sets the enemy's initial data
	 * @param x
	 * - Initial X coordinate position
	 * @param y
	 * - Initial Y coordinate position
	 * @param enemy
	 *  - enum Enemies constant
	 */
	public void set(int x, int y, Enemies enemy) {
		this.x = x;
		this.y = y;
		this.xa = 0;
		this.ya = 0;
		this.invincibleTime = 0;
		this.deadTime = 0;
		this.removed = false;
		this.enemy = enemy;
		this.deadTicks = 0;
		this.currMoveIndex = 0;
		this.ticks = 0;
		this.moveActions = enemy.getMoveActions();
		this.deadTime = enemy.getDeadTime();
		this.hitPoints = enemy.getHp();
		this.acceleration = enemy.getAcceleration();
		this.deceleration = enemy.getDeceleration();
		this.maxSpeed = enemy.getMaxSpeed();
		this.weapon = enemy.getWeapon();
		this.sprite = enemy.getAnimation();
		this.boundX = enemy.getBounds()[0];
		this.boundY = enemy.getBounds()[1];
		this.boundW = enemy.getBounds()[2];
		this.boundH = enemy.getBounds()[3];
		this.scrap = enemy.getScrap();
		this.available = false;
	}
	
	@Override
	public Enemy clone(){
		Enemy e = level.getObjectPool().getEnemy();
		e.set((int)x, (int)y, enemy);
		e.setLevel(level);
		e.setPlayer(player);
		return e;
	}

	@Override
	public void tick() {
		++ticks;
		sprite.tick();
		if (deadTicks != 0) {
			++deadTicks;
			if(deadTicks % 35 == 0)
				explosion(false);
			if(deadTicks % 200 == 0)
				explosion(true);
			brake();
			if (ya == 0) {
				ya = 0.0002f * deadTicks;
			}
			if (deadTicks > deadTime) {
				remove();
			}
		} else {
			if (shotWait != 0) // If a shot was fired
				shotWait--; // Reduce shotWait by 1

			if (ticks > moveActions.get(currMoveIndex).getEndTime()) {
				ticks = 0;
				++currMoveIndex;
				if (moveActions.size() == currMoveIndex) {
					remove();
					return;
				}
			}
			actions();
		}
		move();
	}

	/**
	 * Moves the enemy according to its current advancement attributes and the level acceleration
	 */
	protected void move() {
		float xaLevel = player.getXLevelAcc() / 8;
		float yaLevel = player.getYLevelAcc() / 8;
		
		if(level.getId() == 3)
			yaLevel = 0;
		
		if(Math.abs(xa) > maxSpeed)
			xa = maxSpeed * Math.signum(xa);
		if(Math.abs(ya) > maxSpeed)
			ya = maxSpeed * Math.signum(ya);
		
		if(x < -sprite.getImage().getWidth(null))
			remove();
		
		x += xa + xaLevel;
		y += ya + yaLevel;
	}

	/**
	 * Checks collisions with player
	 * @param player
	 * - Player object
	 */
	public void checkCollisions(Player player) {
		if (!player.isInvincible() && !player.isDead() && !isDead()) {
			if (getBounds().intersects(player.getBounds())) {
				int damage = Math.min(Math.max(100, hitPoints), 500);
				hurt(damage);
				player.hurt(damage);
				if(!player.isShielded())
					player.invincibleTime = 500;
			}
		}
	}
	
	@Override
	public Rectangle getBounds(){
		bounds.setBounds((int)x + boundX, (int)y + boundY, boundW, boundH);
		return bounds;
	}

	@Override
	protected void hurt(int damage) {
		if (!isInvincible()) {
			hitPoints -= damage;
			if (isDead()) {
				++deadTicks;
				player.addScrap(scrap);
				level.getSoundManager().playDeath();
				int chance = RANDOM.nextInt(100);
				if(chance < 20){
					int type;
					switch(chance){
					case 0:
						type = 5;
						break;
					case 1: case 2:
						type = 3;
						break;
					case 3: case 4:
						type = 4;
						break;
					case 5: case 6: case 7: case 8: case 9:
						type = 1;
						break;
					case 10: case 11: case 12: case 13: case 14:
						type = 0;
						break;
					default:
						type = 2;
					}
					level.addPowerUp(new PowerUp((int)this.x, (int)this.y, type, player, level));
				}
			}
		}
	}

	/**
	 * Shoots with equipped Weapon object
	 */
	protected void shoot() {
		if (shotWait == 0) {
			weapon.shoot(
					(int) x + sprite.getImage().getWidth(null) / 2,
					(int) y + sprite.getImage().getHeight(null) / 2,
					(int) player.getX() + player.sprite.getImage().getWidth(null) / 2,
					(int) player.getY() + player.sprite.getImage().getHeight(null) / 2,
					false, level);
			shotWait = weapon.getFireRate();
		}
	}

	/**
	 * Decelerates until speed is 0 in both axis
	 */
	protected void brake() {
		float tx = Math.abs(xa);
		float ty = Math.abs(ya);
		if (tx > 0) {
			tx -= deceleration;
			if (tx < 0)
				tx = 0;
		}
		if (ty > 0) {
			ty -= deceleration;
			if (ty < 0)
				ty = 0;
		}
		xa = tx * Math.signum(xa);
		ya = ty * Math.signum(ya);
	}

	@Override
	public void draw(Graphics2D g) {
		if (isDead()) {
			float alpha = 1 - (float) deadTicks / deadTime;
			AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g.setComposite(ac);
			g.drawImage(sprite.getImage(), (int) x, (int) y, null);
			ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
			g.setComposite(ac);
		} else
			g.drawImage(sprite.getImage(), (int) x, (int) y, null);

	}
	
	/**
	 * Assigns the Player object
	 * @param player
	 * - Player object
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Assigns the GameLevel object
	 * @param level
	 * - GameLevel object
	 */
	public void setLevel(GameLevel level) {
		this.level = level;
	}
	
	/**
	 * Executes current move action in the moveActions array
	 */
	private void actions() {
		int[] actionTypes = moveActions.get(currMoveIndex).getTypes();
		for (int i = 0; i < actionTypes.length; i++){
			switch (actionTypes[i]) {
			case -3: // Wait
				break;
			case -2: // Leave up
				ya -= acceleration;
				if(y < -sprite.getImage().getHeight(null))
					remove();
				break;
			case -1: // Leave down
				ya += acceleration;
				if(y > level.getHeight())
					remove();
				break;
			case 0: // Stop movement instantly
				xa = 0;
				ya = 0;
				break;
			case 1: // Accelerate left
				xa -= acceleration;
				break;
			case 2: // Accelerate up
				ya -= acceleration;
				break;
			case 3: // Accelerate right
				xa += acceleration;
				break;
			case 4: // Accelerate down
				ya += acceleration;
				break;
			case 5: // Stop movement gradually
				brake();
				break;
			case 6: // Shoot
				shoot();
				break;
			case 7:
				xa -= 0.2f;
				break;
			case 8:
				invincibleTime = 1;
				sprite = Animations.CLOSED_EYE.getAnimation();
				level.getSoundManager().playClose();
				break;
			case 9:
				invincibleTime = 0;
				sprite = Animations.OPEN_EYE.getAnimation();
				level.getSoundManager().playOpen();
				break;
			}
		}
	}
}
