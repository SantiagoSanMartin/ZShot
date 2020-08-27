package ar.zgames.zshot.background;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.system.ScreenManager;

/**
 * Background for space levels
 */
public class Mountains extends Background {
	private final int totalHeight = 1000;
	private BufferedImage bgImage2;
	private BufferedImage bgImage3;
	private float currHeight;

	/**
	 * Constructs a new mountain background
	 */
	public Mountains() {
		parallaxes = new Parallax[6];
		bgImage = ResourceManager.createCompatible(ResourceManager
				.getMountain());
		bgImage2 = ResourceManager.createCompatible(ResourceManager
				.getMountain2());
		bgImage3 = ResourceManager.createCompatible(ResourceManager
				.getBlueSky());
		currHeight = 0;
		for (int i = 0; i < parallaxes.length; i++) {
			parallaxes[i] = new MountainParallax(i);
		}
	}

	@Override
	public void tick() {
		ya = level.getPlayer().getYLevelAcc();
		if ((currHeight + ya < 0) || (currHeight + ya > totalHeight)) {
			ya = 0;
		}
		currHeight += ya;
		super.tick();
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < parallaxes.length; i++) {
			int j = i / 2;
			switch (j) {
			case 0:
				g.drawImage(bgImage3, (int)parallaxes[i].x,
						(int)parallaxes[i].y, null);
				break;
			case 1:
				g.drawImage(bgImage2, (int)parallaxes[i].x,
						(int)parallaxes[i].y, null);
				break;
			case 2:
				g.drawImage(bgImage, (int)parallaxes[i].x,
						(int)parallaxes[i].y, null);
				break;
			}
		}
	}

	/**
	 * Mountain parallax class
	 */
	private class MountainParallax extends Parallax {
		private int speedMod;
		int w = ScreenManager.getWidth();
		
		/**
		 * Constructs a new mountain parallax
		 * @param id
		 * - Parallax id
		 */
		private MountainParallax(int id) {
			switch (id) {
			case 0:
				x = w;
				y = -1000;
				speedMod = 4;
				break;
			case 1:
				x = 0;
				y = -1000;
				speedMod = 4;
				break;
			case 2:
				x = w;
				y = -300;
				speedMod = 2;
				break;
			case 3:
				x = 0;
				y = -300;
				speedMod = 2;
				break;
			case 4:
				x = w;
				y = 100;
				speedMod = 1;
				break;
			case 5:
				x = 0;
				y = 100;
				speedMod = 1;
				break;
			}
		}

		@Override
		protected void move() {
			xa = level.getPlayer().getXLevelAcc() / speedMod;
			super.move();
			if (x < -w)
				x += w*2;
		}
	}
}
