package ar.zgames.zshot.animations;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ar.zgames.zshot.system.ObjectPool;

/**
 * An animation is an object that contains the data for an animation.
 * It is used for both actors and effects.
 */
public class Animation {
	private static ObjectPool objectPool;
	private ArrayList<AnimFrame> frames;		// Array of frames with image and duration
	private int currFrameIndex;					// Currently active frame
	private int animTick;						// Current animation tick
	private int totalDuration;					// Total animation ticks from beginning to end
	private boolean available;					// Indicates the object pool if this object is free

	/**
	 * Creates a new, empty Animation.
	 */
	public Animation() {
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0;
		start();
	}
	
	/**
	 * Constructor for copying an animation object from another existing animation
	 * @param frames
	 * - animation frames with image and duration
	 * @param totalDuration
	 * - total animation ticks from beginning to end
	 */
	public void set(ArrayList<AnimFrame> frames, int totalDuration) {
		this.frames = frames;
		this.totalDuration = totalDuration;
		available = false;
		start();
	}
	
	@Override
	public Animation clone() {
		Animation a = objectPool.getAnimation();
		a.set(this.frames, this.totalDuration);
		return a;
	}
	
	/**
	 * Adds an image to the animation with the specified duration (time to
	 * display the image).
	 * @param string
	 * - file path to the image
	 * @param duration
	 * - number of ticks before changing to next frame
	 */
	public void addFrame(BufferedImage image, int duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}
	
	/**
	 * Starts this animation over from the beginning.
	 */
	public void start() {
		animTick = 0;
		currFrameIndex = 0;
	}
	
	/**
	 * Updates this animation's current frame, if necessary.
	 */
	public void tick() {
		if (frames.size() > 1) {
			++animTick;
			if (animTick >= totalDuration) {
				animTick %= totalDuration;
				currFrameIndex = 0;
			}
			if (animTick > getFrame(currFrameIndex).endTicks) {
				++currFrameIndex;
			}
		}
	}
	
	/**
	 * Gets this Animation's current image. Returns null if this animation has
	 * no images.
	 */
	public Image getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currFrameIndex).image;
		}
	}
	
	/**
	 * Returns the AnimFrame object in the referenced position from the frames ArrayList
	 * @param i
	 * - ArrayList position
	 * @return
	 * animation frame with image and duration
	 */
	private AnimFrame getFrame(int i) {
		return frames.get(i);
	}
	
	/**
	 * Returns the tick in which animation should end
	 * @return
	 * Tick in which animation should end
	 */
	public int getTotalDuration() {
		return totalDuration;
	}
	
	/**
	 * Sets the ObjectPool object from which to draw animation objects
	 * @param pool
	 * Reference to ObjectPool object
	 */
	public static void setObjectPool(ObjectPool pool){
		Animation.objectPool = pool;
	}
	
	/**
	 * Indicates the object pool whether this object is free
	 * @return
	 * true if object is free, false otherwise
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Sets this object as available for the object pool
	 */
	public void setAvailable() {
		available = true;
	}

	/**
	 * An AnimFrame is used to store data for each frame in the animation
	 */
	private class AnimFrame {

		private BufferedImage image; 	// Image to be drawn
		private int endTicks;			// Number of ticks to process before changing frame

		/**
		 * Constructs a new frame
		 * @param image
		 * - image to be displayed
		 * @param endTicks
		 * - number of ticks to process before changing frame
		 */
		private AnimFrame(BufferedImage image, int endTicks) {
			this.endTicks = endTicks;
			this.image = image;
		}
	}
}
	