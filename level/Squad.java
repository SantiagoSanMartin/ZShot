package ar.zgames.zshot.level;

import ar.zgames.zshot.actor.Enemy;
import ar.zgames.zshot.level.Levels.Squads;

/**
 * Contains the information for a group of enemies
 */
public class Squad {
	private Enemy[] enemies; // Enemy array
	
	/**
	 * Constructs a new squad from a Squads enum contant
	 * @param squad
	 * Squads enum constant
	 */
	Squad(Squads squad){
		this.enemies = squad.getEnemies();
	}
	
	/**
	 * Returns the Enemy array for the squad
	 * @return
	 * Enemy array
	 */
	public Enemy[] getEnemies(){
		return enemies;
	}
}
