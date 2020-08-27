package ar.zgames.zshot.upgrades;

import ar.zgames.zshot.weapons.Cannon;
import ar.zgames.zshot.weapons.GatlingGun;
import ar.zgames.zshot.weapons.SpreadGun;
import ar.zgames.zshot.weapons.Weapon;

/**
 * Contains player upgrade data
 */
public class Upgrades {
	private Weapon[] weapons; // Weapon array
	private int maneuverability; // Maneuverability value
	private int scrap; // Scrap amount
	private int level; // Number of levels already passed
	private int damageReduction;
	
	/**
	 * Constructs a new Shop object
	 */
	public Upgrades(){
		weapons = new Weapon[3];
		weapons[0] = new GatlingGun(1);
		weapons[1] = new SpreadGun(1);
		weapons[2] = new Cannon(1);
		scrap = 0;
		maneuverability = 1;
		level = 5;
		damageReduction = 0;
	}
	
	/**
	 * Returns maneuverability value
	 * @return
	 * - Maneuverability value
	 */
	public int getManeuverability(){
		return maneuverability;
	}
	
	/**
	 * Returns scrap amount
	 * @return
	 * - Scrap amount
	 */
	public int getScrap(){
		return scrap;
	}
	
	/**
	 * Sets maneuverability to the specified value
	 * @param value
	 * - Value to set
	 */
	public void setManeuverability(int value){
		maneuverability = value;
	}
	
	/**
	 * Increases maneuverability by 1
	 */
	public void incManeuverability(){
		if(maneuverability < 5)
			++maneuverability;
	}
	
	/**
	 * Decreases maneuverability by 1
	 */
	public void decManeuverability(){
		if(maneuverability > 1)
			--maneuverability;
	}
	
	/**
	 * Sets the damage reduction to the passed parameter
	 * @param damageReduction
	 * - Damage reduction
	 */
	public void setDamageReduction(int damageReduction){
		this.damageReduction = damageReduction;
	}
	
	/**
	 * Increases damage reduction by 10%
	 */
	public void incDamageReduction(){
		damageReduction += 1;
	}
	
	/**
	 * Decreases damage reduction by 10%
	 */
	public void decDamageReduction(){
		damageReduction -= 1;
	}
	
	/**
	 * Increases scrap by specified amount
	 * @param amount
	 * - Amount to increase
	 */
	public void addScrap(int amount){
		scrap += amount;
	}
	
	/**
	 * Adds the SpreadGun as an available weapon
	 */
	public void addSpreadGun(){
		weapons[1] = new SpreadGun(1);
	}
	
	/**
	 * Adds the Cannon as an available weapon
	 */
	public void addCannon(){
		weapons[2] = new Cannon(1);
	}

	/**
	 * Returns the weapons array
	 * @return
	 * Weapons array
	 */
	public Weapon[] getWeapons() {
		return weapons;
	}
	
	/**
	 * Returns the damage reduction value
	 * @return
	 * Damage reduction
	 */
	public int getDamageReduction() {
		return damageReduction;
	}
	
	/**
	 * Creates a deep copy of the weapons array
	 * @return
	 * Copy of weapons array
	 */
	public Weapon[] cloneWeapons() {
		Weapon[] weapons = new Weapon[3];
		weapons[0] = new GatlingGun(this.weapons[0].getPower());
		if(this.weapons[1] != null)
			weapons[1] = new SpreadGun(this.weapons[1].getPower());
		if(this.weapons[2] != null)
			weapons[2] = new Cannon(this.weapons[2].getPower());
		return weapons;
	}
	
	/**
	 * Sets the weapons array to the passed parameter
	 * @param weapons
	 * - Weapon array
	 */
	public void setWeapons(Weapon[] weapons){
		this.weapons = weapons;
	}

	/**
	 * Returns the number of levels already passed
	 * @return
	 * Number of levels already passed
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the number of levels already passed
	 * @param level
	 * - Number of levels already passed
	 */
	public void setLevel(int level) {
		if(this.level < level)
			this.level = level;
	}
}
