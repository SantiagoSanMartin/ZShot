package ar.zgames.zshot.actor;

/**
 * A move action indicates an enemy how it should move
 * Each move action is composed of one or more move types and the duration in ticks to change
 * to the next move action
 */
public class MoveAction {
	private int endTime; // number of ticks to switch to the next MoveAction
	private int[] actionTypes; // array with types of MoveAction to be executed
	/**
	 * Constructs a new MoveAction
	 * @param endTime
	 * - The number of ticks to switch to the next MoveAction
	 * @param actionTypes
	 * - The types of MoveAction, several types can be executed at once
	 */
	public MoveAction(int endTime, int[] actionTypes) {
		this.actionTypes = actionTypes;
		this.endTime = endTime;
	}

	/**
	 * Returns the MoveAction types
	 * @return
	 * MoveAction types
	 */
	public int[] getTypes() {
		return actionTypes;
	}
	
	/**
	 * Returns the number of ticks to switch to the next MoveAction
	 * @return
	 * Number of ticks to switch to the next MoveAction
	 */
	public int getEndTime(){
		return endTime;
	}
}
