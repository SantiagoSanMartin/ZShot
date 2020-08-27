package ar.zgames.zshot.system;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 * The ScreenManager class manages initializing and displaying full screen
 * graphics modes.
 */
public class ScreenManager {
	private static final int WIDTH = 1600; // Design width
	private static final int HEIGHT = 1200; // Design height
	private static GraphicsDevice device; // GraphicsDevice object
	private static final DisplayMode POSSIBLE_MODES[] = { // Supported display modes
        new DisplayMode(1600, 1200, 32, 85),
        new DisplayMode(1600, 1200, 32, 75),
        new DisplayMode(1024, 768, 32, 85),
        new DisplayMode(1024, 768, 32, 75),
        new DisplayMode(800, 600, 24, 85),
        new DisplayMode(800, 600, 24, 75),
        new DisplayMode(800, 600, 16, 75),
        new DisplayMode(640, 480, 32, 75),
        new DisplayMode(640, 480, 24, 75),
        new DisplayMode(640, 480, 16, 75)
		};

	/**
	 * Creates a new ScreenManager object.
	 */
	public ScreenManager() {
		GraphicsEnvironment environment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
		setScreen(findFirstCompatibleMode(POSSIBLE_MODES));
	}

	/**
	 * Returns the current display mode.
	 */
	public DisplayMode getCurrentDisplayMode() {
		return device.getDisplayMode();
	}

	/**
	 * Checks if two display modes match
	 * @param mode1
	 * - DisplayMode 1
	 * @param mode2
	 * - DisplayMode 2
	 * @return
	 * true if display modes match, false otherwise
	 */
	public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2) {
		if (mode1.getWidth() != mode2.getWidth()
				|| mode1.getHeight() != mode2.getHeight()) {
			return false;
		}

		if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& mode1.getBitDepth() != mode2.getBitDepth()) {
			return false;
		}

		if (mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& mode1.getRefreshRate() != mode2.getRefreshRate()) {
			return false;
		}

		return true;
	}

	/**
	 * Returns the first compatible mode in a list of modes. Returns null if no
	 * modes are compatible.
	 */
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {
		DisplayMode goodModes[] = device.getDisplayModes();
		for (int i = 0; i < modes.length; i++) {
			for (int j = 0; j < goodModes.length; j++) {
				if (displayModesMatch(modes[i], goodModes[j])) {
					return modes[i];
				}
			}

		}

		return null;
	}

	/**
	 * Returns a list of compatible display modes for the default device on the
	 * system.
	 */
	public DisplayMode[] getCompatibleDisplayModes() {
		return device.getDisplayModes();
	}

	/**
	 * Enters full screen mode and changes the display mode. If the specified
	 * display mode is null or not compatible with this device, or if the
	 * display mode cannot be changed on this system, the current display mode
	 * is used.
	 * <p>
	 * The display uses a BufferStrategy with 2 buffers.
	 */
	public void setScreen(DisplayMode displayMode) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		device.setFullScreenWindow(frame);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor cursor = toolkit.createCustomCursor(ResourceManager.createCompatible(ResourceManager.getCursor()), new Point(12,
			     12), "ZCursor");
		frame.setCursor(cursor);
		if (displayMode != null && device.isDisplayChangeSupported()) {
			try {
				device.setDisplayMode(displayMode);
			} catch (IllegalArgumentException ex) {
			}
		}
		frame.createBufferStrategy(3);
	}

	/**
	 * Gets the graphics context for the display. The ScreenManager uses double
	 * buffering, so applications must call update() to show any graphics drawn.
	 * <p>
	 * The application must dispose of the graphics object.
	 */
	public Graphics2D getGraphics() {
		Window window = device.getFullScreenWindow();
		if (window != null) {
			BufferStrategy strategy = window.getBufferStrategy();
			Graphics2D scaledGraphics = (Graphics2D) strategy.getDrawGraphics();
			scaledGraphics.scale((double) window.getWidth() / WIDTH, (double) window.getHeight() / HEIGHT);
			return scaledGraphics;
		} else {
			return null;
		}
	}

	/**
	 * Updates the display.
	 */
	public void update() {
		Window window = device.getFullScreenWindow();
		if (window != null) {
			BufferStrategy strategy = window.getBufferStrategy();
			if (!strategy.contentsLost()) {
				strategy.show();
			}
		}
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Returns the window currently used in full screen mode. Returns null if
	 * the device is not in full screen mode.
	 */
	public Window getFullScreenWindow() {
		return device.getFullScreenWindow();
	}

    /**
     * Gets graphic configuration of current display.
     */
	public static GraphicsConfiguration getGraphicConfig() {
	    Window window = ScreenManager.device.getFullScreenWindow();
	    if (window != null) {
	        return window.getGraphicsConfiguration();
	    }
	    return null;
	}

	/**
	 * Returns design width
	 * @return
	 * Design width
	 */
	public static int getWidth() {
		return WIDTH;
	}

	/**
	 * Returns design height
	 * @return
	 * Design height
	 */
	public static int getHeight() {
		return HEIGHT;
	}
}
