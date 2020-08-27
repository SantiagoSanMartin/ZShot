package ar.zgames.zshot.background;

import ar.zgames.zshot.system.ResourceManager;
import ar.zgames.zshot.system.ScreenManager;

public class FireCave extends Background {
	
	/**
	 * Constructs a new fire cave background
	 */
	public FireCave(){
		xa = -0.3f;
		ya = 0f;
		parallaxes = new FireParallax[2];
		bgImage = ResourceManager.createCompatible(ResourceManager.getFireCave());
		for (int i = 0; i < parallaxes.length; i++) {
			parallaxes[i] = new FireParallax(i);
		}
	}
	
    @Override
    public void tick(){
        xa = level.getPlayer().getXLevelAcc();
        super.tick();
    }
	
	private class FireParallax extends Parallax{
		private int w = ScreenManager.getWidth();
		
		/**
		 * Constructs a new fire cave parallax
		 * @param id
		 * - Parallax id
		 */
		private FireParallax(int id) {
			switch (id) {
			case 0:
				x = 0;
				y = 0;
				break;
			case 1:
				x = w;
				y = 0;
				break;
			}
		}
		
		@Override
		protected void move(){
			super.move();
			
			if (x < -w)
				x += w*2;
		}
	}
}
