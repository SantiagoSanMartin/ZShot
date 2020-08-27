package ar.zgames.zshot.level;

import java.util.ArrayList;

import ar.zgames.zshot.actor.Enemy;
import ar.zgames.zshot.actor.MoveAction;
import ar.zgames.zshot.animations.Animation;
import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.background.Background;
import ar.zgames.zshot.background.FireCave;
import ar.zgames.zshot.background.Mountains;
import ar.zgames.zshot.background.Space;
import ar.zgames.zshot.weapons.*;

/**
 * Contains the information to create a level
 */
public enum Levels {
	LEVEL1(1, new Space(), new LevelEvent[]{
		new LevelEvent(LevelEvents.EVENT1_1), 
		new LevelEvent(LevelEvents.EVENT1_2),
		new LevelEvent(LevelEvents.EVENT1_3),
		new LevelEvent(LevelEvents.EVENT1_2),
		new LevelEvent(LevelEvents.EVENT1_4),
		new LevelEvent(LevelEvents.EVENT1_5),
		new LevelEvent(LevelEvents.EVENT1_2),
		new LevelEvent(LevelEvents.WEAPON_UP),
		new LevelEvent(LevelEvents.EVENT1_6),
		new LevelEvent(LevelEvents.EVENT1_7),
		new LevelEvent(LevelEvents.EVENT1_8),
//		new LevelEvent(LevelEvents.EVENT1_5),
//		new LevelEvent(LevelEvents.EVENT1_9),
//		new LevelEvent(LevelEvents.EVENT1_6),
//		new LevelEvent(LevelEvents.EVENT1_8),
//		new LevelEvent(LevelEvents.EVENT1_7),
		new LevelEvent(LevelEvents.EVENT1_10),
		new LevelEvent(LevelEvents.EVENT1_2),
//		new LevelEvent(LevelEvents.EVENT1_6),
//		new LevelEvent(LevelEvents.EVENT1_3),
//		new LevelEvent(LevelEvents.EVENT1_12), 
//		new LevelEvent(LevelEvents.EVENT1_9),
		new LevelEvent(LevelEvents.EVENT1_10),
		new LevelEvent(LevelEvents.EVENT1_11),
		new LevelEvent(LevelEvents.EVENT1_4),
		new LevelEvent(LevelEvents.SHIELD_UP),
		new LevelEvent(LevelEvents.EVENT1_6),
//		new LevelEvent(LevelEvents.EVENT1_9),
//		new LevelEvent(LevelEvents.EVENT1_10),
//		new LevelEvent(LevelEvents.EVENT1_11),
//		new LevelEvent(LevelEvents.EVENT1_6),
//		new LevelEvent(LevelEvents.EVENT1_12), 
//		new LevelEvent(LevelEvents.EVENT1_7),
		new LevelEvent(LevelEvents.EVENT1_3),
		new LevelEvent(LevelEvents.MOVE_UP),
		new LevelEvent(LevelEvents.EVENT1_8),
		new LevelEvent(LevelEvents.EVENT1_5),
		new LevelEvent(LevelEvents.EVENT1_11),
		new LevelEvent(LevelEvents.EVENT1_8),
		new LevelEvent(LevelEvents.HP_UP),
		new LevelEvent(LevelEvents.EVENT1_1)}),
	LEVEL2(2, new Mountains(), new LevelEvent[]{
		new LevelEvent(LevelEvents.EVENT1_5),
		new LevelEvent(LevelEvents.EVENT2_11),
		new LevelEvent(LevelEvents.EVENT1_11),
		new LevelEvent(LevelEvents.EVENT1_11),
		new LevelEvent(LevelEvents.MOVE_UP),
		new LevelEvent(LevelEvents.EVENT2_9),
		new LevelEvent(LevelEvents.EVENT2_2),
		new LevelEvent(LevelEvents.EVENT1_5),
		new LevelEvent(LevelEvents.EVENT2_6),
		new LevelEvent(LevelEvents.WEAPON_UP),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT2_12),
		new LevelEvent(LevelEvents.EVENT2_1),
		new LevelEvent(LevelEvents.EVENT2_2),
		new LevelEvent(LevelEvents.EVENT2_3),
		new LevelEvent(LevelEvents.EVENT2_4),
		new LevelEvent(LevelEvents.EVENT2_14),
		new LevelEvent(LevelEvents.EVENT2_9),
		new LevelEvent(LevelEvents.EVENT2_5),
		new LevelEvent(LevelEvents.EVENT2_6),
		new LevelEvent(LevelEvents.EVENT2_7),
		new LevelEvent(LevelEvents.EVENT2_8),
		new LevelEvent(LevelEvents.EVENT2_14),
		new LevelEvent(LevelEvents.EVENT2_12),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT2_15),
		new LevelEvent(LevelEvents.EVENT2_16),
		new LevelEvent(LevelEvents.EVENT2_13),
		new LevelEvent(LevelEvents.HP_UP),
		new LevelEvent(LevelEvents.EVENT1_5),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT1_11),
		new LevelEvent(LevelEvents.EVENT1_12), 
		new LevelEvent(LevelEvents.EVENT1_11),
		new LevelEvent(LevelEvents.EVENT2_14),
		new LevelEvent(LevelEvents.EVENT2_9),
		new LevelEvent(LevelEvents.MOVE_UP),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT2_12),
		new LevelEvent(LevelEvents.EVENT1_4),
		new LevelEvent(LevelEvents.EVENT2_15),
		new LevelEvent(LevelEvents.EVENT2_13),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_16),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.WEAPON_UP),
		new LevelEvent(LevelEvents.EVENT2_14),
		new LevelEvent(LevelEvents.EVENT2_12),
		new LevelEvent(LevelEvents.EVENT1_11),
		new LevelEvent(LevelEvents.EVENT1_12), 
		new LevelEvent(LevelEvents.EVENT2_16),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT2_15),
		new LevelEvent(LevelEvents.HP_UP),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_16),
		new LevelEvent(LevelEvents.EVENT2_1),
		new LevelEvent(LevelEvents.EVENT2_2),
		new LevelEvent(LevelEvents.EVENT2_3),
		new LevelEvent(LevelEvents.EVENT2_4),
		new LevelEvent(LevelEvents.EVENT2_5),
		new LevelEvent(LevelEvents.WEAPON_UP),
		new LevelEvent(LevelEvents.EVENT1_11),
		new LevelEvent(LevelEvents.EVENT2_6),
		new LevelEvent(LevelEvents.EVENT2_7),
		new LevelEvent(LevelEvents.EVENT2_8),
		new LevelEvent(LevelEvents.EVENT2_16),
		new LevelEvent(LevelEvents.HP_UP),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18)}),
	LEVEL3(3, new FireCave(), new LevelEvent[]{
		new LevelEvent(LevelEvents.EVENT3_1), 
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT3_2),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.MOVE_UP),
		new LevelEvent(LevelEvents.EVENT3_5),
		new LevelEvent(LevelEvents.EVENT2_13),
		new LevelEvent(LevelEvents.EVENT2_14),
		new LevelEvent(LevelEvents.EVENT2_15),
		new LevelEvent(LevelEvents.EVENT3_3),
		new LevelEvent(LevelEvents.EVENT2_16),
		new LevelEvent(LevelEvents.EVENT3_1),
		new LevelEvent(LevelEvents.EVENT1_5),
		new LevelEvent(LevelEvents.EVENT3_4),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT3_11),
		new LevelEvent(LevelEvents.HP_UP),
		new LevelEvent(LevelEvents.EVENT3_14),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT1_12),
		new LevelEvent(LevelEvents.WEAPON_UP),
		new LevelEvent(LevelEvents.EVENT3_6),
		new LevelEvent(LevelEvents.EVENT3_9),
		new LevelEvent(LevelEvents.EVENT3_7),
		new LevelEvent(LevelEvents.EVENT3_10),
		new LevelEvent(LevelEvents.EVENT3_8),
		new LevelEvent(LevelEvents.EVENT3_4),
		new LevelEvent(LevelEvents.EVENT3_12),
		new LevelEvent(LevelEvents.EVENT3_14),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_10),
		new LevelEvent(LevelEvents.EVENT3_8),
		new LevelEvent(LevelEvents.EVENT3_13),
		new LevelEvent(LevelEvents.EVENT3_4),
		new LevelEvent(LevelEvents.EVENT3_6),
		new LevelEvent(LevelEvents.EVENT3_9),
		new LevelEvent(LevelEvents.EVENT3_7),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT3_14),
		new LevelEvent(LevelEvents.EVENT3_2),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_17),
		new LevelEvent(LevelEvents.EVENT2_18),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT1_12),
		new LevelEvent(LevelEvents.EVENT3_11),
		new LevelEvent(LevelEvents.WEAPON_UP),
		new LevelEvent(LevelEvents.EVENT3_14), 
		new LevelEvent(LevelEvents.EVENT3_8),
		new LevelEvent(LevelEvents.EVENT2_10),
		new LevelEvent(LevelEvents.EVENT3_14),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_2),
		new LevelEvent(LevelEvents.HP_UP),
		new LevelEvent(LevelEvents.EVENT3_5),
		new LevelEvent(LevelEvents.EVENT3_12),
		new LevelEvent(LevelEvents.EVENT3_13),
		new LevelEvent(LevelEvents.EVENT3_14),
		new LevelEvent(LevelEvents.EVENT3_10),
		new LevelEvent(LevelEvents.EVENT3_10),
		new LevelEvent(LevelEvents.EVENT3_14),
		new LevelEvent(LevelEvents.EVENT3_15),
		new LevelEvent(LevelEvents.EVENT3_14),
		new LevelEvent(LevelEvents.EVENT3_16)});
	
	private int id; // Level id
	private LevelEvent[] events; // Contains the events for this level
	private Background background; // Background object used for this level
	
	/**
	 * Constructs a Levels constant
	 * @param id
	 * - Level id
	 * @param background
	 * - Background object
	 * @param events
	 * - LevelEvent array
	 */
	private Levels(int id, Background background, LevelEvent[] events){
		this.id = id;
		this.events = events;
		this.background = background;
	}
	
	/**
	 * Returns the level's id
	 * @return
	 * Level's id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the LevelEvent array
	 * @return
	 * LevelEvent array
	 */
	public LevelEvent[] getEvents(){
		return events;
	}
	
	/**
	 * Returns the Background object
	 * @return
	 * Background object
	 */
	public Background getBackground(){
		return background;
	}
	
	/**
	 * Contains the information to create a LevelEvent
	 */
	public enum LevelEvents{
		EVENT1_1(900, new Squad(Squads.SQUAD1)),
		EVENT1_2(1600, new Squad(Squads.SQUAD2)),
		EVENT1_3(1600, new Squad(Squads.SQUAD3)),
		EVENT1_4(3200, new Squad(Squads.SQUAD4)),
		EVENT1_5(5000, new Squad(Squads.SQUAD5)),
		EVENT1_6(700, new Squad(Squads.SQUAD6)),
		EVENT1_7(4500, new Squad(Squads.SQUAD7)),
		EVENT1_8(700, new Squad(Squads.SQUAD8)),
		EVENT1_9(900, new Squad(Squads.SQUAD9)),
		EVENT1_10(900, new Squad(Squads.SQUAD10)),
		EVENT1_11(900, new Squad(Squads.SQUAD11)),
		EVENT1_12(6000, new Squad(Squads.SQUAD12)),
		EVENT2_1(1, new Squad(Squads.SQUAD13)),
		EVENT2_2(1, new Squad(Squads.SQUAD14)),
		EVENT2_3(1, new Squad(Squads.SQUAD15)),
		EVENT2_4(1, new Squad(Squads.SQUAD16)),
		EVENT2_5(1, new Squad(Squads.SQUAD17)),
		EVENT2_6(1, new Squad(Squads.SQUAD18)),
		EVENT2_7(1, new Squad(Squads.SQUAD19)),
		EVENT2_8(1, new Squad(Squads.SQUAD20)),
		EVENT2_9(2000, new Squad(Squads.SQUAD21)),
		EVENT2_10(1500, new Squad(Squads.SQUAD22)),
		EVENT2_11(3000, new Squad(Squads.SQUAD23)),
		EVENT2_12(8000, new Squad(Squads.SQUAD24)),
		EVENT2_13(5000, new Squad(Squads.SQUAD25)),
		EVENT2_14(1000, new Squad(Squads.SQUAD26)),
		EVENT2_15(1000, new Squad(Squads.SQUAD27)),
		EVENT2_16(8000, new Squad(Squads.SQUAD28)),
		EVENT2_17(200, new Squad(Squads.SQUAD29)),
		EVENT2_18(200, new Squad(Squads.SQUAD30)),
		EVENT3_1(25000, new Squad(Squads.SQUAD33)),
		EVENT3_2(5000, new Squad(Squads.SQUAD31)),
		EVENT3_3(2000, new Squad(Squads.SQUAD32)),
		EVENT3_4(500, new Squad(Squads.SQUAD32)),
		EVENT3_5(1000, new Squad(Squads.SQUAD34)),
		EVENT3_6(1000, new Squad(Squads.SQUAD35)),
		EVENT3_7(1000, new Squad(Squads.SQUAD36)),
		EVENT3_8(1000, new Squad(Squads.SQUAD37)),
		EVENT3_9(5000, new Squad(Squads.SQUAD38)),
		EVENT3_10(5000, new Squad(Squads.SQUAD39)),
		EVENT3_11(12000, new Squad(Squads.SQUAD40)),
		EVENT3_12(12000, new Squad(Squads.SQUAD41)),
		EVENT3_13(12000, new Squad(Squads.SQUAD42)),
		EVENT3_14(1, new Squad(Squads.SQUAD33)),
		EVENT3_15(450, new Squad(Squads.SQUAD11)),
		EVENT3_16(12000, new Squad(Squads.SQUAD43)),
		WEAPON_UP(1, 2),
		HP_UP(1, 0),
		SHIELD_UP(1, 1),
		MOVE_UP(1, 3);
		
		private int endTick; // Indicates how many ticks must pass for this event to end
		private Squad squad; // Contains the squad's information
		private int powerType; // Contains the PowerUp information
		
		/**
		 * Constructs a LevelEvents enum constant from a Squad object
		 * @param endTick
		 * - Tick for the event to end
		 * @param squad
		 * - Squad to be assigned when the event starts
		 */
		private LevelEvents(int endTick, Squad squad){
			this.endTick = endTick;
			this.squad = squad;
		}
		
		/**
		 * Constructs a LevelEvents enum constant for a Power Up event
		 * @param startTick
		 * - Tick for the event to start
		 * @param type
		 * - PowerUp type
		 */
		private LevelEvents(int startTick, int type){
			this.endTick = startTick;
			this.powerType = type;
		}
		
		/**
		 * Returns the tick for this event to end
		 * @return
		 * Tick for this event to end
		 */
		public int getEndTick() {
			return endTick;
		}

		/**
		 * Returns the Squad object to be assigned
		 * @return
		 * Squad object
		 */
		public Squad getSquad() {
			return squad;
		}
		
		/**
		 * Returns the PowerUp object to be assigned
		 * @return
		 * PowerUp object
		 */
		public int getPowerType(){
			return powerType;
		}
	}
	
	/**
	 * Contains the information to create a Squad
	 */
	public enum Squads{
		SQUAD1(new Enemies[]	{Enemies.DRONE1}, 
				new int[][]		{new int[]{1700, 400}}),
		SQUAD2(new Enemies[]	{Enemies.DRONE1,			Enemies.DRONE1, 			Enemies.DRONE1}, 
				new int[][]		{new int[]{1700, 280}, 		new int[]{1700, 480}, 		new int[]{1700, 680}}),
		SQUAD3(new Enemies[]	{Enemies.DRONE3, 			Enemies.DRONE1, 			Enemies.DRONE2},
				new int[][]		{new int[]{1700, -120}, 	new int[]{1700, 480}, 		new int[]{1700, 1080}}),
		SQUAD4(new Enemies[]	{Enemies.TANK1,				Enemies.DRONE3,				Enemies.DRONE2},
				new int[][]		{new int[]{1700, 480},		new int[]{1700, -120},		new int[]{1700, 1080}}),
		SQUAD5(new Enemies[]	{Enemies.TANK2,				Enemies.TANK3},
				new int[][]		{new int[]{1300, 1080},		new int[]{1300, -420}}),
		SQUAD6(new Enemies[]	{Enemies.RAM1,				Enemies.RAM1, 				Enemies.RAM1}, 
				new int[][]		{new int[]{1700, 280}, 		new int[]{1700, 480}, 		new int[]{1700, 680}}),
		SQUAD7(new Enemies[]	{Enemies.TANK1,				Enemies.DRONE3,				Enemies.DRONE2,				Enemies.DRONE4,				Enemies.DRONE5},
				new int[][]		{new int[]{1700, 450},		new int[]{1700, -120},		new int[]{1700, 1080},		new int[]{300, -120},		new int[]{300, 1080}}),
		SQUAD8(new Enemies[]	{Enemies.RAM1,				Enemies.RAM1, 				Enemies.RAM1, 				Enemies.RAM1},
				new int[][]		{new int[]{1700, 120}, 		new int[]{1700, 360}, 		new int[]{1700, 600},		new int[]{1700, 840}}),
		SQUAD9(new Enemies[]	{Enemies.DRONE6},
				new int[][]		{new int[]{1400, 1100}}),
		SQUAD10(new Enemies[]	{Enemies.DRONE7},
				new int[][]		{new int[]{1400, -150}}),
		SQUAD11(new Enemies[]	{Enemies.DRONE7, 			Enemies.DRONE6},
				new int[][]		{new int[]{1400, -150},		new int[]{1400, 1100}}),
		SQUAD12(new Enemies[]	{Enemies.TANK5,				Enemies.TANK4,				Enemies.TANK6,				Enemies.TANK7},
				new int[][]		{new int[]{1700, -120},		new int[]{1700, 1080},		new int[]{300, -120},		new int[]{300, 1080}}),
		SQUAD13(new Enemies[] 	{Enemies.BAT1},
				new int[][]		{new int[]{1400, 1080}}),
		SQUAD14(new Enemies[] 	{Enemies.BAT1},
				new int[][]		{new int[]{1000, 1080}}),
		SQUAD15(new Enemies[] 	{Enemies.BAT1},
				new int[][]		{new int[]{600, 1080}}),
		SQUAD16(new Enemies[] 	{Enemies.BAT1},
				new int[][]		{new int[]{200, 1080}}),
		SQUAD17(new Enemies[] 	{Enemies.BAT2},
				new int[][]		{new int[]{1400, -100}}),
		SQUAD18(new Enemies[] 	{Enemies.BAT2},
				new int[][]		{new int[]{1000, -100}}),
		SQUAD19(new Enemies[] 	{Enemies.BAT2},
				new int[][]		{new int[]{600, -100}}),
		SQUAD20(new Enemies[] 	{Enemies.BAT2},
				new int[][]		{new int[]{200, -100}}),
		SQUAD21(new Enemies[]	{Enemies.KAISER1},
				new int[][]		{new int[]{1700, 480}}),
		SQUAD22(new Enemies[]	{Enemies.TITAN1},
				new int[][]		{new int[]{1700, 480}}),
		SQUAD23(new Enemies[]	{Enemies.DRONE1,			Enemies.KAISER1, 			Enemies.DRONE1}, 
				new int[][]		{new int[]{1700, 280}, 		new int[]{1700, 480}, 		new int[]{1700, 680}}),
		SQUAD24(new Enemies[]	{Enemies.KAISER3,			Enemies.KAISER2,			Enemies.TANK6,				Enemies.TANK7},
				new int[][]		{new int[]{1700, -120},		new int[]{1700, 1080},		new int[]{300, -120},		new int[]{300, 1080}}),
		SQUAD25(new Enemies[]	{Enemies.TITAN1, 			Enemies.TITAN1},
				new int[][]		{new int[]{1700, 280}, 		new int[]{1700, 680}}),
		SQUAD26(new Enemies[]	{Enemies.RAM2,				Enemies.RAM2, 				Enemies.RAM2}, 
				new int[][]		{new int[]{1700, 280}, 		new int[]{1700, 480}, 		new int[]{1700, 680}}),
		SQUAD27(new Enemies[]	{Enemies.RAM2,				Enemies.RAM2, 				Enemies.RAM2, 				Enemies.RAM2},
				new int[][]		{new int[]{1700, 120}, 		new int[]{1700, 360}, 		new int[]{1700, 600},		new int[]{1700, 840}}),
		SQUAD28(new Enemies[]	{Enemies.TITAN2,			Enemies.TITAN3},
				new int[][]		{new int[]{1300, 1080},		new int[]{1300, -420}}),
		SQUAD29(new Enemies[] 	{Enemies.BAT3},
				new int[][]		{new int[]{1700, 850}}),
		SQUAD30(new Enemies[] 	{Enemies.BAT3},
				new int[][]		{new int[]{1700, 100}}),
		SQUAD31(new Enemies[]	{Enemies.KAISER1,			Enemies.KAISER1,			Enemies.KAISER1},
				new int[][]		{new int[]{1700, 60},		new int[]{1700, 360}, 		new int[]{1700, 660}}),
		SQUAD32(new Enemies[]	{Enemies.WALL1, 			Enemies.WALL1, 				Enemies.WALL1, 				Enemies.WALL1, 				Enemies.WALL1, 				Enemies.WALL1, 				Enemies.WALL1,				Enemies.WALL1},
				new int[][]		{new int[]{2100, 800},		new int[]{1700, 180}, 		new int[]{1900, 400},		new int[]{1650, 570},		new int[]{1850, 250}, 		new int[]{2500, 30},		new int[]{2300, 100},		new int[]{1800, 700}}),
		SQUAD33(new Enemies[]	{Enemies.WALL1, 			Enemies.WALL1, 				Enemies.WALL1,				Enemies.WALL1,				Enemies.WALL1,				Enemies.WALL1,				Enemies.WALL1},				
				new int[][]		{new int[]{1600, 20}, 		new int[]{1600, 160},		new int[]{1600, 300},		new int[]{1600, 440},		new int[]{1600, 580},		new int[]{1600, 720},		new int[]{1600, 860}}),
		SQUAD34(new Enemies[]	{Enemies.MINERAM1}, 
				new int[][]		{new int[]{1700, 480}}),
		SQUAD35(new Enemies[]	{Enemies.MINERAM2}, 
				new int[][]		{new int[]{1700, 480}}),
		SQUAD36(new Enemies[]	{Enemies.MINERAM2,			Enemies.MINERAM2, 			Enemies.MINERAM2}, 
				new int[][]		{new int[]{1700, 280}, 		new int[]{1700, 480}, 		new int[]{1700, 680}}),
		SQUAD37(new Enemies[]	{Enemies.MINERAM1,			Enemies.MINERAM1, 			Enemies.MINERAM1, 			Enemies.MINERAM1}, 
				new int[][]		{new int[]{1700, 180}, 		new int[]{1700, 380}, 		new int[]{1700, 580},		new int[]{1700, 780}}),
		SQUAD38(new Enemies[]	{Enemies.EYE1}, 
				new int[][]		{new int[]{1700, 400}}),
		SQUAD39(new Enemies[]	{Enemies.EYE2, 				Enemies.EYE3}, 
				new int[][]		{new int[]{1700, -120},		new int[]{1700, 1080}}),
		SQUAD40(new Enemies[]	{Enemies.HYPERION1}, 
				new int[][]		{new int[]{1400, 1000}}),
		SQUAD41(new Enemies[]	{Enemies.HYPERION2}, 
				new int[][]		{new int[]{1400, -270}}),
		SQUAD42(new Enemies[]	{Enemies.HYPERION3}, 
				new int[][]		{new int[]{1700, 300}}),
		SQUAD43(new Enemies[]	{Enemies.HYPERION1,			Enemies.HYPERION2}, 
				new int[][]		{new int[]{1400, 1000},		new int[]{1000, -270}});
		
				
		private Enemy[] enemies; // Enemy array
		
		/**
		 * Constructs a Squads enum constant
		 * @param enemies
		 * - Enemies enum constant
		 * @param startingPositions
		 * - Array with starting positions for each enemy
		 */
		private Squads(Enemies[] enemies, int[][] startingPositions){
			this.enemies = new Enemy[enemies.length];
			for(int i = 0; i < enemies.length; i++){
				this.enemies[i] = new Enemy(startingPositions[i][0], startingPositions[i][1], enemies[i]);
			}
		}

		/**
		 * Returns the array with the Enemy objects
		 * @return
		 * Enemy array
		 */
		public Enemy[] getEnemies() {
			return enemies;
		}
	}
	
	/**
	 * Contains the information to create an Enemy
	 */
	public enum Enemies {
		DRONE1(200, 30, 50, 0.1f, 0.05f, 2f, new Gun(1), Animations.DRONE1, ActionList.ATTACK1, new int[]{15, 10, 85, 100}),
		DRONE2(200, 30, 50, 0.1f, 0.05f, 2f, new Gun(1), Animations.DRONE1, ActionList.ATTACK2, new int[]{15, 10, 85, 100}),
		DRONE3(200, 30, 50, 0.1f, 0.05f, 2f, new Gun(1), Animations.DRONE1, ActionList.ATTACK3, new int[]{15, 10, 85, 100}),
		DRONE4(200, 30, 50, 0.1f, 0.05f, 2f, new Gun(1), Animations.DRONE1, ActionList.ATTACK7, new int[]{15, 10, 85, 100}),
		DRONE5(200, 30, 50, 0.1f, 0.05f, 2f, new Gun(1), Animations.DRONE1, ActionList.ATTACK8, new int[]{15, 10, 85, 100}),
		DRONE6(200, 30, 50, 1f, 0.05f, 1f, new Blaster(1), Animations.DRONE2, ActionList.ATTACK9, new int[]{15, 10, 85, 100}),
		DRONE7(200, 30, 50, 1f, 0.05f, 1f, new Blaster(1), Animations.DRONE2, ActionList.ATTACK10, new int[]{15, 10, 85, 100}),
		TANK1(400, 100, 200, 0.05f, 0.025f, 1.5f, new GatlingGun(1), Animations.TANK1, ActionList.ATTACK4, new int[]{30, 30, 140, 90}),
		TANK2(400, 100, 200, 0.05f, 0.025f, 1.5f, new GatlingGun(1), Animations.TANK1, ActionList.ATTACK5, new int[]{30, 30, 140, 90}),
		TANK3(400, 100, 200, 0.05f, 0.025f, 1.5f, new GatlingGun(1), Animations.TANK1, ActionList.ATTACK6, new int[]{30, 30, 140, 90}),
		TANK4(400, 100, 200, 0.1f, 0.05f, 2f, new GatlingGun(1), Animations.TANK1, ActionList.ATTACK2, new int[]{30, 30, 140, 90}),
		TANK5(400, 100, 200, 0.1f, 0.05f, 2f, new GatlingGun(1), Animations.TANK1, ActionList.ATTACK3, new int[]{30, 30, 140, 90}),
		TANK6(400, 100, 200, 0.1f, 0.05f, 2f, new GatlingGun(1), Animations.TANK1, ActionList.ATTACK7, new int[]{30, 30, 140, 90}),
		TANK7(400, 100, 200, 0.1f, 0.05f, 2f, new GatlingGun(1), Animations.TANK1, ActionList.ATTACK8, new int[]{30, 30, 140, 90}),
		RAM1(400, 40, 100, 0.05f, 0.025f, 2f, null, Animations.RAM1, ActionList.RAM1, new int[]{20, 30, 180, 95}),
		RAM2(400, 60, 100, 0.05f, 0.025f, 2f, null, Animations.RAM2, ActionList.RAM_OSCILLATION, new int[]{20, 30, 180, 95}),		
		BAT1(200, 80, 150, 0.1f, 0.05f, 2f, new Stream(1), Animations.BAT1, ActionList.ATTACK11, new int[]{30, 0, 140, 55}),
		BAT2(200, 80, 150, 0.1f, 0.05f, 2f, new Stream(1), Animations.BAT1, ActionList.ATTACK12, new int[]{30, 0, 140, 55}),
		BAT3(200, 80, 150, 0.1f, 0.05f, 2f, new Stream(1), Animations.BAT1, ActionList.ATTACK13, new int[]{30, 0, 140, 55}),
		KAISER1(300, 100, 200, 0.1f, 0.1f, 2f, new SpreadGun(2), Animations.KAISER1, ActionList.ATTACK1, new int[]{25, 0, 125, 118}),
		KAISER2(300, 100, 200, 0.1f, 0.1f, 2f, new SpreadGun(2), Animations.KAISER1, ActionList.ATTACK14, new int[]{25, 0, 125, 118}),
		KAISER3(300, 100, 200, 0.1f, 0.1f, 2f, new SpreadGun(2), Animations.KAISER1, ActionList.ATTACK15, new int[]{25, 0, 125, 118}),
		TITAN1(400, 200, 400, 0.05f, 0.05f, 1f, new GatlingGun(4), Animations.TITAN1, ActionList.ATTACK1, new int[]{45, 0, 172, 220}),
		TITAN2(400, 200, 400, 0.05f, 0.05f, 1f, new GatlingGun(4), Animations.TITAN1, ActionList.ATTACK5, new int[]{45, 0, 172, 220}),
		TITAN3(400, 200, 400, 0.05f, 0.05f, 1f, new GatlingGun(4), Animations.TITAN1, ActionList.ATTACK6, new int[]{45, 0, 172, 220}),
		WALL1(300, 300, 250, 0.02f, 0.02f, 0.5f, null, Animations.WALL1, ActionList.WALL1, new int[]{12, 12, 105, 101}),
		MINERAM1(300, 80, 250, 0.05f, 0.025f, 2f, new MineLauncher(3), Animations.MINERAM1, ActionList.ATTACK_OSCILLATION, new int[]{18, 10, 142, 80}),
		MINERAM2(300, 80, 250, 0.05f, 0.025f, 2f, new MineLauncher(3), Animations.MINERAM1, ActionList.ATTACK13, new int[]{18, 10, 142, 80}),
		HYPERION1(800, 500, 750, 0.05f, 0.025f, 0.75f, new PlasmaCannon(2), Animations.HYPERION1, ActionList.ATTACK16, new int[]{40, 20, 300, 190}),
		HYPERION2(800, 500, 750, 0.05f, 0.025f, 0.75f, new PlasmaCannon(2), Animations.HYPERION1, ActionList.ATTACK17, new int[]{40, 20, 300, 190}),
		HYPERION3(800, 500, 750, 0.05f, 0.025f, 0.75f, new PlasmaCannon(4), Animations.HYPERION1, ActionList.ATTACK18, new int[]{40, 20, 300, 190}),
		EYE1(400, 250, 400, 0.1f, 0.05f, 2f, new SpreadGun(4), Animations.OPEN_EYE, ActionList.ATTACK_EYE1, new int[]{10, 10, 85, 65}),
		EYE2(400, 250, 400, 0.1f, 0.05f, 1f, new SpreadGun(4), Animations.OPEN_EYE, ActionList.ATTACK_EYE2, new int[]{10, 10, 85, 65}),
		EYE3(400, 250, 400, 0.1f, 0.05f, 1f, new SpreadGun(4), Animations.OPEN_EYE, ActionList.ATTACK_EYE3, new int[]{10, 10, 85, 65});
		
		private final int deadTime; // Ticks for enemy to disappear after it is dead
		private final int hp; // Enemy's HP
		private final float acceleration; // Enemy's acceleration
		private final float deceleration; // Enemy's decelearation
		private final float maxSpeed; // Enemy's maximum speed
		private final Weapon weapon; // Enemy's weapon
		private final Animation animation; // Enemy's animation
		private final int[] bounds; // Enemy's bounds for collision detection
		private final ArrayList<MoveAction> moveActions; // Enemy's actions
		private final int scrap; // Scrap to give player when enemy is destroyed

		/**
		 * Constructs an Enemies enum constant
		 * @param deadTime
		 * - Ticks for enemy to disappear after it is dead
		 * @param hp
		 * - HP
		 * @param scrap
		 * - Scrap to give player when enemy is destroyed
		 * @param accInc
		 * - Acceleration
		 * @param decInc
		 * - Deceleration
		 * @param maxSpeed
		 * - Maximum speed
		 * @param weapon
		 * - Weapon
		 * @param animation
		 * - Animation
		 * @param actions
		 * - Actions to perform
		 * @param bounds
		 * - Bounds for collision detection
		 */
		private Enemies(int deadTime, int hp, int scrap, float accInc, float decInc, float maxSpeed, Weapon weapon, Animations animation, ActionList actions, int[] bounds){
			this.deadTime = deadTime;
			this.hp = hp;
			this.acceleration = accInc;
			this.deceleration = decInc;
			this.maxSpeed = maxSpeed;
			this.weapon = weapon;
			this.animation = animation.getAnimation();
			this.bounds = bounds;
			this.moveActions = new ArrayList<MoveAction>();
			MoveActions[] moveActions = actions.moveActions;
			for(int i = 0; i < moveActions.length; i++){
				this.moveActions.add(new MoveAction(moveActions[i].endTime, moveActions[i].actionTypes));
			}
			this.scrap = scrap;
		}
		
		/**
		 * Returns bounds array for collision detection
		 * @return
		 * Bounds array
		 */
		public int[] getBounds(){
			return bounds;
		}

		
		/**
		 * Returns number of ticks for enemy to disappear after it is dead
		 * @return
		 * Number of ticks for enemy to disappear after it is dead
		 */
		public int getDeadTime() {
			return deadTime;
		}
		
		
		/**
		 * Returns enemy's HP
		 * @return
		 * HP
		 */
		public int getHp() {
			return hp;
		}

		/**
		 * Returns enemy's acceleration
		 * @return
		 * Acceleration
		 */
		public float getAcceleration() {
			return acceleration;
		}

		/**
		 * Returns enemy's deceleration
		 * @return
		 * Deceleration
		 */
		public float getDeceleration() {
			return deceleration;
		}

		/**
		 * Returns enemy's maximum speed
		 * @return
		 * Maximum Speed
		 */
		public float getMaxSpeed() {
			return maxSpeed;
		}

		/**
		 * Returns enemy's Weapon
		 * @return
		 * Weapon
		 */
		public Weapon getWeapon() {
			return weapon;
		}

		/**
		 * Returns enemy's Animation
		 * @return
		 * Animation
		 */
		public Animation getAnimation() {
			return animation;
		}
		
		/**
		 * Returns actions to be performed
		 * @return
		 * MoveAction array
		 */
		public ArrayList<MoveAction> getMoveActions(){
			return moveActions;
		}

		/**
		 * Returns scrap to gvie player when enemy is destroyed
		 * @return
		 * Scrap
		 */
		public int getScrap() {
			return scrap;
		}
	}
	
	/**
	 * Contains the information to create a MoveAction array
	 */
	private enum ActionList{
		ATTACK1(new MoveActions[]{MoveActions.ACC_LEFT_LIGHT, MoveActions.SHOOT_5SECS, MoveActions.LEAVE_LEFT}),
		ATTACK2(new MoveActions[]{MoveActions.ACC_UP_LEFT_MED, MoveActions.SHOOT_5SECS, MoveActions.LEAVE_DOWN}),
		ATTACK3(new MoveActions[]{MoveActions.ACC_DOWN_LEFT_MED, MoveActions.SHOOT_5SECS, MoveActions.LEAVE_UP}),
		ATTACK4(new MoveActions[]{MoveActions.ACC_LEFT_MED, MoveActions.SHOOT_15SECS, MoveActions.LEAVE_UP}),
		ATTACK5(new MoveActions[]{MoveActions.ACC_UP_MED, MoveActions.SHOOT_15SECS, MoveActions.LEAVE_DOWN}),
		ATTACK6(new MoveActions[]{MoveActions.ACC_DOWN_MED, MoveActions.SHOOT_15SECS, MoveActions.LEAVE_UP}),
		ATTACK7(new MoveActions[]{MoveActions.ACC_DOWN_RIGHT_MED, MoveActions.SHOOT_5SECS, MoveActions.LEAVE_LEFT}),
		ATTACK8(new MoveActions[]{MoveActions.ACC_UP_RIGHT_MED, MoveActions.SHOOT_5SECS, MoveActions.LEAVE_LEFT}),
		ATTACK9(new MoveActions[]{MoveActions.SHOOT_UP, MoveActions.LEAVE_UP}),
		ATTACK10(new MoveActions[]{MoveActions.SHOOT_DOWN, MoveActions.LEAVE_DOWN}),
		ATTACK11(new MoveActions[]{MoveActions.ACC_UP_LIGHT, MoveActions.SHOOT_5SECS, MoveActions.LEAVE_DOWN}),
		ATTACK12(new MoveActions[]{MoveActions.ACC_DOWN_LIGHT, MoveActions.SHOOT_5SECS, MoveActions.LEAVE_UP}),
		ATTACK13(new MoveActions[]{MoveActions.SHOOT_LEFT, MoveActions.LEAVE_LEFT}),
		ATTACK14(new MoveActions[]{MoveActions.ACC_UP_LEFT_MED, MoveActions.SHOOT_15SECS, MoveActions.LEAVE_DOWN}),
		ATTACK15(new MoveActions[]{MoveActions.ACC_DOWN_LEFT_MED, MoveActions.SHOOT_15SECS, MoveActions.LEAVE_UP}),
		ATTACK16(new MoveActions[]{MoveActions.ACC_UP_LIGHT, MoveActions.SHOOT_UP, MoveActions.SHOOT_DOWN, MoveActions.SHOOT_UP, MoveActions.SHOOT_DOWN, MoveActions.LEAVE_DOWN}),
		ATTACK17(new MoveActions[]{MoveActions.ACC_DOWN_LIGHT, MoveActions.SHOOT_DOWN, MoveActions.SHOOT_UP, MoveActions.SHOOT_DOWN, MoveActions.SHOOT_UP, MoveActions.LEAVE_UP}),
		ATTACK18(new MoveActions[]{MoveActions.ACC_LEFT_MED, MoveActions.SHOOT_15SECS, MoveActions.SHOOT_15SECS, MoveActions.LEAVE_LEFT}),
		ATTACK_EYE1(new MoveActions[]{MoveActions.ACC_LEFT_LIGHT, MoveActions.SHOOT_5SECS, MoveActions.EYE_CLOSE, MoveActions.WAIT_5SECS, MoveActions.EYE_OPEN, MoveActions.SHOOT_5SECS, MoveActions.EYE_CLOSE, MoveActions.LEAVE_LEFT}),
		ATTACK_EYE2(new MoveActions[]{MoveActions.ACC_DOWN_LEFT_MED, MoveActions.SHOOT_5SECS, MoveActions.EYE_CLOSE, MoveActions.WAIT_5SECS, MoveActions.EYE_OPEN, MoveActions.SHOOT_5SECS, MoveActions.EYE_CLOSE, MoveActions.LEAVE_LEFT}),
		ATTACK_EYE3(new MoveActions[]{MoveActions.ACC_UP_LEFT_MED, MoveActions.SHOOT_5SECS, MoveActions.EYE_CLOSE, MoveActions.WAIT_5SECS, MoveActions.EYE_OPEN, MoveActions.SHOOT_5SECS, MoveActions.EYE_CLOSE, MoveActions.LEAVE_LEFT}),
		WALL1(new MoveActions[]{MoveActions.LEAVE_LEFT}),
		RAM1(new MoveActions[]{MoveActions.LEAVE_LEFT}),
		ATTACK_OSCILLATION(new MoveActions[]{MoveActions.ACC_DOWN_LEFT_SHOOT, MoveActions.ACC_UP_LEFT_SHOOT, MoveActions.ACC_DOWN_LEFT_SHOOT, MoveActions.ACC_UP_LEFT_SHOOT, MoveActions.ACC_DOWN_LEFT_SHOOT, MoveActions.ACC_UP_LEFT_SHOOT, MoveActions.ACC_DOWN_LEFT_SHOOT, MoveActions.ACC_UP_LEFT_SHOOT, MoveActions.ACC_DOWN_LEFT_SHOOT, MoveActions.ACC_UP_LEFT_SHOOT}),
		RAM_OSCILLATION(new MoveActions[]{MoveActions.ACC_DOWN_LEFT_LIGHT, MoveActions.ACC_UP_LEFT_LIGHT, MoveActions.ACC_DOWN_LEFT_LIGHT, MoveActions.ACC_UP_LEFT_LIGHT, MoveActions.ACC_DOWN_LEFT_LIGHT, MoveActions.ACC_UP_LEFT_LIGHT, MoveActions.ACC_DOWN_LEFT_LIGHT, MoveActions.ACC_UP_LEFT_LIGHT, MoveActions.ACC_DOWN_LEFT_LIGHT, MoveActions.ACC_UP_LEFT_LIGHT});
		
		private MoveActions[] moveActions; // Actions to be performed
		
		/**
		 * Constructs a MoveAction enum constant
		 * @param moveActions
		 * - MoveAction array
		 */
		private ActionList(MoveActions[] moveActions){
			this.moveActions = moveActions;
		}
	}

	/**
	 * Contains the information to create a MoveAction
	 */
	private enum MoveActions{
		ACC_LEFT_LIGHT(100, new int[]{1}),
		ACC_UP_LIGHT(100, new int[]{2}),
		ACC_DOWN_LIGHT(100, new int[]{4}),
		ACC_UP_LEFT_LIGHT(150, new int[]{1,4}),
		ACC_DOWN_LEFT_LIGHT(150, new int[]{1,2}),
		ACC_DOWN_LEFT_SHOOT(150, new int[]{1,2,6}),
		ACC_UP_LEFT_SHOOT(150, new int[]{1,4,6}),
		ACC_LEFT_MED(400, new int[]{1}),
		ACC_UP_MED(400, new int[]{2}),
		ACC_DOWN_MED(400, new int[]{4}),
		ACC_DOWN_LEFT_MED(400, new int[]{1,4}),
		ACC_DOWN_RIGHT_MED(400, new int[]{4,3}),
		ACC_UP_LEFT_MED(400, new int[]{1,2}),
		ACC_UP_RIGHT_MED(400, new int[]{2,3}),
		SHOOT_UP(1500, new int[]{2,6}),
		SHOOT_DOWN(1500, new int[]{4,6}),
		SHOOT_LEFT(1500, new int[]{1,6}),
		STOP(1, new int[]{0}),
		SHOOT_5SECS(1200, new int[]{6,5}),
		SHOOT_15SECS(3600, new int[]{6,5}),
		LEAVE_LEFT(10000, new int[]{1}),
		LEAVE_UP(10000, new int[]{-2}),
		LEAVE_DOWN(10000, new int[]{-1}),
		EYE_OPEN(1, new int[]{9}),
		EYE_CLOSE(1, new int[]{8}),
		WAIT_5SECS(1200, new int[]{-3});
		
		private int endTime; // Time MoveAction lasts before switching to next action
		private int[] actionTypes; // Types of actions to be performed during this MoveAction
		
		/**
		 * Constructs a MoveActions enum constant
		 * @param endTime
		 * - Time MoveAction lasts before switching to next action
		 * @param actionTypes
		 * - Types of actions to be performed during this MoveAction
		 */
		private MoveActions(int endTime, int[] actionTypes){
			this.endTime = endTime;
			this.actionTypes = actionTypes;
		}
	}
}
