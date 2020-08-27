package ar.zgames.zshot.system;

import java.util.ArrayList;

import ar.zgames.zshot.actor.Bullet;
import ar.zgames.zshot.actor.Effect;
import ar.zgames.zshot.actor.Enemy;
import ar.zgames.zshot.animations.Animation;

/**
 * Handles objects for levels so as to not have to create them, saving memory usage
 */
public class ObjectPool {
	private ArrayList<Bullet> bulletPool;
	private ArrayList<Effect> effectPool;
	private ArrayList<Animation> animationPool;
	private ArrayList<Enemy> enemyPool;
	
	/**
	 * Constructs a new ObjectHandler object
	 */
	public ObjectPool(){
		bulletPool = new ArrayList<Bullet>();
		effectPool = new ArrayList<Effect>();
		animationPool = new ArrayList<Animation>();
		enemyPool = new ArrayList<Enemy>();
	}
	
	/**
	 * Returns the next available Effect or creates one if none are available
	 * @return
	 * Available Effect
	 */
	public Effect getEffect(){
		for(int i = 0; i < effectPool.size(); i++){
			if(effectPool.get(i).isAvailable())
				return effectPool.get(i);
		}
		Effect e = new Effect();
		effectPool.add(e);
		return e;
	}
	
	/**
	 * Returns the next available Bullet or creates one if none are available
	 * @return
	 * Available Bullet
	 */
	public Bullet getBullet(){
		for(int i = 0; i < bulletPool.size(); i++){
			if(bulletPool.get(i).isAvailable())
				return bulletPool.get(i);
		}
		Bullet b = new Bullet();
		bulletPool.add(b);
		return b;
	}

	/**
	 * Returns the next available Animation or creates one if none are available
	 * @return
	 * Available Animation
	 */
	public Animation getAnimation() {
		for(int i = 0; i < animationPool.size(); i++){
			if(animationPool.get(i).isAvailable())
				return animationPool.get(i);
		}
		Animation a = new Animation();
		animationPool.add(a);
		return a;
	}
	
	/**
	 * Returns the next available Enemy or creates one if none are available
	 * @return
	 * Available Enemy
	 */
	public Enemy getEnemy() {
		for(int i = 0; i < enemyPool.size(); i++){
			if(enemyPool.get(i).isAvailable())
				return enemyPool.get(i);
		}
		Enemy e = new Enemy(0,0, null);
		enemyPool.add(e);
		return e;
	}
}
