package ar.zgames.zshot.actor;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import ar.zgames.zshot.animations.Animation;
import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;

/**
 * An actor is an entity that can interact with other actors in the game
 */
public abstract class Actor {
	
	protected static final Random RANDOM = new Random(); // To generate random numbers
	protected float x; // Actor's x axis position
	protected float y; // Actor's y axis position
	protected float xa; // Actor's x axis advancement
	protected float ya; // Actor's y axis advancement
	protected int hitPoints; // Current hit points
	protected Animation sprite; // Actor animation
	protected boolean removed; // Indicates if the actor should be ignored by game process
	protected int invincibleTime; // Counts the time the actor is invincible
	protected int deadTime; // Counts the time the actor remains dead
	protected int shotWait; // Ticks to wait between 2 shots
	protected float maxSpeed; // Maximum possible speed
	protected float acceleration; // How fast the player moves per tick
	protected float deceleration; // How fast the player stops moving per tick
	protected GameLevel level; // Reference to level object
	protected Rectangle bounds; // Rectangle object for collision detection
	protected boolean available; // Indicates the object pool if this object is free

	/**
	 * Constructor for Actor
	 * 
	 * @param x
	 *            - initial X coordinate
	 * @param y
	 *            - initial Y coordinate
	 */
	public Actor() {
		/*xa = 0;
		ya = 0;
		invincibleTime = 0;
		deadTime = 0;*/
		removed = false;
		bounds = new Rectangle();
	}

	/**
	 * Draw the actor
	 * 
	 * @param g
	 *            - Graphics2D
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Process the actor's tick
	 */
	public abstract void tick();
	
	/**
	 * Returns actor's collision area
	 * 
	 * @return Rectangle actor's collision area
	 * */
	public Rectangle getBounds() {
		bounds.setBounds((int) (x - sprite.getImage().getWidth(null) / 10),
				(int) (y - sprite.getImage().getHeight(null) / 10),
				(int)Math.floor(sprite.getImage().getWidth(null) * 8/10),
				(int)Math.floor(sprite.getImage().getHeight(null) * 8/10));
		return bounds;
	}

	/**
	 * Sets an actor as removed. <br>
	 * Removed actors are ignored by game processing and eventually removed completely
	 */
	public void remove() {
		removed = true;
	}
	
	
	/**
	 * Deals damage to the actor
	 * @param damage
	 * - Amount of damage to be dealt
	 */
	protected abstract void hurt(int damage);
	
	/**
	 * Indicates if an actor has been removed
	 * 
	 * @return True if actor is removed, false otherwise
	 */
	public boolean isRemoved() {
		return removed;
	}

	/**
	 * Indicates if actor is invincible (Cannot be dealt any damage)
	 * 
	 * @return True if actor is invincible, false otherwise
	 */
	public boolean isInvincible() {
		if (invincibleTime > 0)
			return true;
		else
			return false;
	}

	/**
	 * Returns the actor's current HP value
	 * 
	 * @return actor's hitPoints
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * Indicates if actor is dead (HP = 0)
	 * 
	 * @return True if actor's hitPoints is 0, false otherwise
	 */
	public boolean isDead() {
		if (hitPoints > 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Creates explosion effects around the actor's area randomly
	 * @param big - if true, the effect created will be bigger
	 */
	protected void explosion(boolean big) {
		int x, y;
		x = (int)this.x + RANDOM.nextInt(sprite.getImage().getWidth(null)); // Calculate random X point to add explosion
		y = (int)this.y + RANDOM.nextInt(sprite.getImage().getHeight(null)); // Calculate random Y point to add explosion
		Effect e;
		if(big){
			e = level.getObjectPool().getEffect();
			e.set(x, y, 0, 0, Animations.EXPLOSION_BIG.getAnimation(), 40);
			level.addEffect(e);
			level.getSoundManager().playBigExplosion();
		}
		else{
			e = level.getObjectPool().getEffect();
			e.set(x, y, 0, 0, Animations.EXPLOSION_SMALL.getAnimation(), 40);
			level.addEffect(e);
			level.getSoundManager().playSmallExplosion();
		}
	}
	
	/**
	 * Indicates the object pool whether this object is free
	 * @return
	 * true if object is free, false otherwise
	 */
	public boolean isAvailable(){
		return available;
	}
	
	/**
	 * Sets this object and its Animation object as available for the object pool
	 */
	public void setAvailable(){
		available = true;
		sprite.setAvailable();
	}
}
