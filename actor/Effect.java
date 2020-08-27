package ar.zgames.zshot.actor;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import ar.zgames.zshot.animations.Animation;

/**
 * Effects are animations without collision behavior
 */
public class Effect {
	private float x; 			// Effect's x axis position
	private float y; 			// Effect's y axis position
	protected float xa; 		// Effect's x axis advancement
	protected float ya; 		// Effect's y axis advancement
	private Animation sprite;	// Effect's animation
	private int effectTick; 	// Current effect tick
	private int totalTicks; 	// Ticks before effect is removed
	private int fadeTick; 		// Ticks before effect begins fading out
	private boolean removed; 	// Indicates if the effect should be ignored by game process
	private boolean available;  // Indicates the object pool if this object is free
	
	/**
	 * Sets the initial data for the effect
	 * @param x
	 * - X coordinate initial position
	 * @param y
	 * - Y coordinate initial position
	 * @param xa
	 * - X axis advancement
	 * @param ya
	 * - Y axis advancement
	 * @param sprite
	 * - Animation object
	 * @param fadeTick
	 * - Tick to begin fading out
	 */
	public void set(float x, float y, float xa, float ya, Animation sprite, int fadeTick) {
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
		this.sprite = sprite.clone();
		this.effectTick = 0;
		this.fadeTick = fadeTick;
		this.totalTicks = sprite.getTotalDuration();
		this.removed = false;
		this.available = false;
	}
	
	/**
	 * Draw the effect
	 * 
	 * @param g
	 *            - Graphics2D
	 */
	public void draw(Graphics2D g) {
		if (effectTick >= fadeTick) {
			float alpha = 1 - (float) (totalTicks - effectTick) / (totalTicks - fadeTick);
			AlphaComposite ac = java.awt.AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha);
			g.setComposite(ac);
			g.drawImage(sprite.getImage(),
					(int) (x - sprite.getImage().getWidth(null)/2), 
					(int) (y - sprite.getImage().getHeight(null)/2), null);
			ac = java.awt.AlphaComposite
					.getInstance(AlphaComposite.SRC_OVER, 1);
			g.setComposite(ac);
		} else
			g.drawImage(sprite.getImage(),
					(int) (x - sprite.getImage().getWidth(null)/2), 
					(int) (y - sprite.getImage().getHeight(null)/2), null);
	}

	/**
	 * Process the effect's tick
	 */
	public void tick() {
		++effectTick;
		sprite.tick();
		move();
		if(effectTick > totalTicks)
			remove();
	}

	/**
	 * Moves the effect according to its current advancement attributes
	 */
	private void move() {
		x += xa;
		y += ya;	
	}

	/**
	 * Sets the effect as removed. <br>
	 * Removed effects are ignored by game processing and eventually removed
	 * completely
	 */
	public void remove() {
		removed = true;
		
	}

	/**
	 * Indicates if an effect has been removed
	 * 
	 * @return True if effect is removed, false otherwise
	 */
	public boolean isRemoved() {
		return removed;
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
