package ar.zgames.zshot.background;

import java.awt.Graphics2D;
import java.awt.Image;

import ar.zgames.zshot.level.Level;

/**
 * Superclass for the background objects to be used in the game
 * Contains the data needed for parallax movement of background images and the different
 * props in the background
 */
public abstract class Background {

	protected Image bgImage;				// Background image
	protected Parallax[] parallaxes;		// Background parallax behavior
	protected float xa;						// X coordinate acceleration
	protected float ya;						// Y coordinate acceleration
	protected Level level;					// Reference to level object

	/**
	 * Processes the background tick
	 */
	public void tick() {
		for(int i = 0; i < parallaxes.length; i++){
			parallaxes[i].move();
		}
	}

	/**
	 * Draws the background
	 * @param g
	 *  - Graphics2D
	 */
	public void draw(Graphics2D g) {
		for(int i = 0; i < parallaxes.length; i++){
			g.drawImage(bgImage, (int)parallaxes[i].x,
			(int)parallaxes[i].y, null);
		}
	}
	
	/**
	 * Sets the level for this background
	 * @param level
	 * - Level object
	 */
	public void setLevel(Level level){
		this.level = level;
	}
	
	/**
	 * This class is used to handle the parallax effect
	 */
	protected abstract class Parallax {
		protected float x; // image's X position
		protected float y; // image's Y position

		/**
		 * Moves the parallax images
		 */
		protected void move() {
			x += xa;
			y += ya;
		}
	}
}
