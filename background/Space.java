package ar.zgames.zshot.background;

import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.system.ScreenManager;

/**
 * Background for space levels
 */
public class Space extends Background {

	/**
	 * Constructs a new space background
	 */
	public Space(){
		xa = -0.3f;
		ya = 0.025f;
		parallaxes = new SpaceParallax[6];
		bgImage = ResourceManager.createCompatible(ResourceManager.getStarfield());
		for (int i = 0; i < parallaxes.length; i++) {
			parallaxes[i] = new SpaceParallax(i);
		}
	}
	
    @Override
    public void tick(){
        xa = level.getPlayer().getXLevelAcc();
        ya = level.getPlayer().getYLevelAcc();
        super.tick();
    }
    
	/**
	 * Space parallax class
	 */
	private class SpaceParallax extends Parallax{
		private int h = ScreenManager.getHeight();
		private int w = ScreenManager.getWidth();
		
		/**
		 * Constructs a new space parallax
		 * @param id
		 * - Parallax id
		 */
		protected SpaceParallax(int id) {
			switch (id) {
			case 0:
				x = 0;
				y = 0;
				break;
			case 1:
				x = w;
				y = 0;
				break;
			case 2:
				x = w;
				y = -h;
				break;
			case 3:
				x = 0;
				y = -h;
				break;
			case 4:
				x = 0;
				y = h;
				break;
			case 5:
				x = w;
				y = h;
				break;
			}
		}

		@Override
		protected void move(){
			super.move();
			
			if (y > h)
				y -= h*2;
			else if (y < -h)
				y += h*2;
			if (x < -w)
				x += w*2;
		}
	}
}
