package ar.zgames.zshot.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ar.zgames.zshot.upgrades.Upgrades;
import ar.zgames.zshot.weapons.Cannon;
import ar.zgames.zshot.weapons.GatlingGun;
import ar.zgames.zshot.weapons.SpreadGun;
import ar.zgames.zshot.weapons.Weapon;

/**
 * Handles game save and load
 */
public class SaveManager implements Serializable {
	private static final long serialVersionUID = 6839417356425916551L;
	private final String savePath = "ZShot.dat"; // Save file name
	private int[] saveData = new int[7]; // Array to store game data as integers

	/**
	 * Encodes game data as integers
	 * @param upgrades
	 * - Upgrades object
	 */
	public void encode(Upgrades upgrades) {
		saveData[0] = upgrades.getScrap();
		saveData[1] = upgrades.getManeuverability();
		Weapon[] weapons = upgrades.getWeapons();
		saveData[2] = weapons[0].getPower();
		if(weapons[1] != null)
			saveData[3] = weapons[1].getPower();
		else
			saveData[3] = 0;
		if(weapons[2] != null)
			saveData[4] = weapons[2].getPower();
		else
			saveData[4] = 0;
		saveData[5] = upgrades.getLevel();
		saveData[6] = upgrades.getDamageReduction();
	}
	
	/**
	 * Decodes integer data to game data
	 * @param save
	 * - SaveManager object
	 * @return
	 * Upgrades object
	 */
	public Upgrades decode(SaveManager save) {
		Upgrades upgrades = new Upgrades();
		upgrades.addScrap(saveData[0]);
		upgrades.setManeuverability(saveData[1]);
		Weapon[] weapons = upgrades.getWeapons();
		weapons[0] = new GatlingGun(saveData[2]);
		if(saveData[3] != 0)
			weapons[1] = new SpreadGun(saveData[3]);
		if(saveData[4] != 0)
			weapons[2] = new Cannon(saveData[4]);
		upgrades.setWeapons(weapons);
		upgrades.setLevel(saveData[5]);
		upgrades.setDamageReduction(saveData[6]);
		return upgrades;
	}

	/**
	 * Saves the game
	 * @param upgrades
	 * - Upgrades object
	 */
	public void saveGame(Upgrades upgrades) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath))){
			encode(upgrades);
			oos.writeObject(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Loads a save file or creates new initial data if no save file is found
	 * @return
	 * Shop object
	 */
	public Upgrades load(){
		if((new File(savePath)).exists()){
			SaveManager save;
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath))){
				save = (SaveManager) ois.readObject();
				this.saveData = save.saveData;
				return decode(save);
			} catch (Exception e) {
				e.printStackTrace();
				return new Upgrades();
			}
		}else
			return new Upgrades();
	}
}
