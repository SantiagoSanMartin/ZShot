package ar.zgames.zshot.level;

import ar.zgames.zshot.actor.Enemy;
import ar.zgames.zshot.actor.Player;
import ar.zgames.zshot.level.Levels.LevelEvents;
import ar.zgames.zshot.upgrades.PowerUp;

/**
 * Contains the information for level events
 */
public class LevelEvent {

	private int endTick; // Indicates how many ticks must pass for this event to end
	private Squad squad; // Contains the squad's information
	private int powerType; // Contains the PowerUp information

	/**
	 * Constructs a new LevelEvent from a LevelEvents enum constant
	 * @param event
	 * - LevelEvents enum constant
	 */
	LevelEvent(LevelEvents event) {
		endTick = event.getEndTick();
		squad = event.getSquad();
		powerType = event.getPowerType();
	}

	/**
	 * Process the event
	 * @param player
	 * - Player object
	 * @param level
	 * - GameLevel object
	 */
	public void start(Player player, GameLevel level) {
		if(squad != null){
			Enemy[] enemies = squad.getEnemies();
			Enemy enemy;
			for (int j = 0; j < enemies.length; j++) {
				enemies[j].setLevel(level);
				enemies[j].setPlayer(player);
				enemy = enemies[j].clone();
				level.addEnemy(enemy);
			}
		}else{
			level.addPowerUp(new PowerUp(1600, 420, powerType, player, level));
		}
	}

	/**
	 * Returns the tick in which this event should end
	 * @return
	 * - The tick in which this event should end
	 */
	public int getEndTick() {
		return endTick;
	}

}
