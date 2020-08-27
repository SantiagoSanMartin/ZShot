package ar.zgames.zshot.actor;

import java.awt.Graphics2D;

import ar.zgames.zshot.animations.Animation;
import ar.zgames.zshot.animations.Animations;
import ar.zgames.zshot.level.GameLevel;
import ar.zgames.zshot.system.InputHandler;
import ar.zgames.zshot.system.ScreenManager;
import ar.zgames.zshot.upgrades.Upgrades;
import ar.zgames.zshot.weapons.Weapon;

/**
 * A player is a special actor that can be controlled with the keyboard and mouse
 */
public class Player extends Actor {

	private static final float ACC_INC = 0.01f; // Acceleration increment
	private static final float DEC_INC = 0.015f; // Deceleration increment
	private static final int MAX_HP = 1000; // maximum hit points
	private static final int MAX_EN = 1000; // maximum energy
	private static final int DEAD_TIME = 1000; // Time between player is dead and player revives/game over
	private int scrap; // Scrap can be used to buy permanent upgrades
	private float xLevelAcc; // Level acceleration in x axis
	private float yLevelAcc; // Level acceleration in y axis
	private InputHandler input; // Reference to InputHandler class
	private Weapon[] weapons = new Weapon[3]; // Available weapons
	private Weapon currWeapon; // Current weapon
	private float energy; // Current energy
	private int lives; // Number of lives
	private boolean shield; // Indicates if shield whether shield is active
	private Animation shieldAnim; // Shield animation to be displayed when shield is active
	private Upgrades upgrades; // Used to determine starting values for weapons and stats according to what the player purchased
	private int damageReduction; // Percentage to reduce damage, shield is not affected
	/**
	 * Player constructor
	 * 
	 * @param x
	 *            - initial X coordinate
	 * @param y
	 *            - initial Y coordinate
	 * @param input
	 *            - Reference to InputHandler object
	 * @param level
	 *            - Reference to GameLevel object
	 * @param upgrades
	 *            - Reference to Shop object
	 */
	public Player(int x, int y, InputHandler input, GameLevel level, Upgrades upgrades) {
		this.x = x;
		this.y = y;
		this.input = input;
		this.level = level;
		this.upgrades = upgrades;
		lives = 3;
		maxSpeed = 2.5f;
		revive();
		shieldAnim = Animations.SHIELD.getAnimation();
		scrap = 0;
	}

	@Override
	public void tick() {
		sprite.tick();
		shieldAnim.tick();
		if (shotWait > 0) // If a shot was fired
			shotWait--; // Reduce shotWait by 1
		if (isInvincible())
			--invincibleTime;
		if (deadTime >= DEAD_TIME)
			revive();
		int x = (int) this.x;
		int y = (int) this.y;
		checkInput();
		if(isRemoved()){
			deadTime++;
			if(deadTime % 35 == 0)
				explosion(false);
			if(deadTime % 200 == 0)
				explosion(true);
			xa = 0.0002f * deadTime;
			ya = 0.0006f * deadTime;
		}
		move();
		checkLevelBounds(x, y);
		calculateLevelAcc();
	}

	/**
	 * Checks player input for movement and actions
	 */
	private void checkInput() {
		shield = false;
		if (!isRemoved()) {
			if (input.up.down && ya > -maxSpeed)
				ya -= acceleration;
			if (input.down.down && ya < maxSpeed)
				ya += acceleration;
			if (input.left.down && xa > -maxSpeed)
				xa -= acceleration;
			if (input.right.down && xa < maxSpeed)
				xa += acceleration;
			if (!input.up.down && ya < 0)
				ya += deceleration;
			if (!input.down.down && ya > 0) {
				ya -= deceleration;
				if (ya < 0)
					ya = 0;
			}
			if (!input.left.down && xa < 0)
				xa += deceleration;
			if (!input.right.down && xa > 0) {
				xa -= deceleration;
				if (xa < 0)
					xa = 0;
			}
			if (input.weapon1.down) {
				input.weapon1.release();
				setWeapon(0);
			}
			if (input.weapon2.down) {
				input.weapon2.release();
				setWeapon(1);
			}
			if (input.weapon3.down) {
				input.weapon3.release();
				setWeapon(2);
			}
			if (input.togglePWeapon.down) {
				input.togglePWeapon.release();
				prevWeapon();
			}
			if (input.toggleNWeapon.down) {
				input.toggleNWeapon.release();
				nextWeapon();
			}
			if (input.shoot.down)
				shoot();
			if(input.shield.down){
				deplete(0.1f);
				if(energy > 0)
					shield = true;
			}
		}
	}

	/**
	 * Calculates the relative speed of the level according to player's position
	 */
	private void calculateLevelAcc() {
		int screenMiddle = (ScreenManager.getHeight() - level.getConsole().getHeight())/2;
		if(isRemoved()){
			xLevelAcc += 0.002;
			yLevelAcc = (Math.abs(yLevelAcc) - 0.001f ) * Math.signum(yLevelAcc);
		}else {
			xLevelAcc = -8 * (x / ScreenManager.getWidth());
			yLevelAcc = -2 * (y - screenMiddle) / screenMiddle;
		}
		if(xLevelAcc >= -0.5f)
			xLevelAcc = -0.5f;
	}

	@Override
	public void draw(Graphics2D g) {
		if (invincibleTime % 30 < 15)
			g.drawImage(sprite.getImage(), (int) x, (int) y, null);
		if(shield)
			g.drawImage(shieldAnim.getImage(), (int) x - 20, (int) y - 20, null);
	}

	/**
	 * Updates the player's x and y coordinates according to its advancement attributes
	 */
	public void move() {
		x += xa;
		y += ya;
	}

	/**
	 * Selects next weapon in weapons array
	 */
	private void nextWeapon() {
		int w;
		w = getWeaponSlot();
		do {
			int i = 1;
			w = (w + i) % weapons.length;
		} while (weapons[w] == null);
		setWeapon(w);
	}

	/**
	 * Selects previous weapon in weapons array
	 */
	private void prevWeapon() {
		int w;
		w = getWeaponSlot();
		do {
			int i = 1;
			w = (w - i);
			if (w < 0)
				w = weapons.length - 1;
		} while (weapons[w] == null);
		setWeapon(w);
	}

	/**
	 * Gets currently equipped weapon
	 * 
	 * @return Equipped weapon position in weapons array
	 */
	public int getWeaponSlot() {
		for (int i = 0; i < weapons.length; i++) {
			if (weapons[i] != null) {
				if (weapons[i].getClass() == currWeapon.getClass())
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns player's currently equipped weapon
	 * @return Currently equipped weapon
	 */
	public Weapon getCurrWeapon(){
		return currWeapon;
	}

	/**
	 * Gets the player's available weapons
	 * 
	 * @return weapons array
	 */
	public Weapon[] getWeaponArray() {
		return weapons;
	}

	/**
	 * Shoots the equipped weapon and sets the shot wait time by the weapon's
	 * fire rate
	 */
	public void shoot() {
		if (shotWait == 0) {
			currWeapon.shoot((int) x + sprite.getImage().getWidth(null) / 2,
					(int) y	+ sprite.getImage().getHeight(null) / 2, 
					input.getMX(), 
					input.getMY(),
					true, 
					level);
			shotWait = currWeapon.getFireRate();
		}
	}

	/**
	 * Changes the player's equipped weapon
	 * 
	 * @param weapon
	 *            - Weapon to switch to
	 */
	public void setWeapon(int i) {
		if (weapons[i] != null)
			this.currWeapon = weapons[i];
	}

	/**
	 * If the player goes out of level bounds, sets it back to previous position
	 * and sets the corresponding coordinate speed to 0
	 * 
	 * @param x
	 *            - X coordinate before the player moved
	 * @param y
	 *            - Y coordinate before the player moved
	 */
	protected void checkLevelBounds(int x, int y) {
		if (!isRemoved()) {
			if (this.x + this.sprite.getImage().getWidth(null) > level.getWidth()
					|| this.x < 0) {
				this.x = x;
				this.xa = 0;
			}
			if (this.y + sprite.getImage().getHeight(null) > level.getHeight()
					|| this.y < 0) {
				this.y = y;
				this.ya = 0;
			}
		}
	}

	/**
	 * Increases player maneuverability by increasing acceleration and
	 * deceleration by a fixed amount determined by static constants
	 * {@link ACC_INC} and {@link DEC_INC}
	 */
	public void incManeuver() {
		acceleration += ACC_INC;
		deceleration += DEC_INC;
	}

	/**
	 * Deals damage to the player and updates the sprite according to remaining
	 * HP. If the player is invincible, does nothing
	 * 
	 * @param damage
	 *            - Amount of damage to be dealt
	 */
	protected void hurt(int damage) {
		if (!isInvincible()) {
			if(shield){
				deplete(damage);
			}else{
				damage -= (int)Math.ceil((double)(damage * damageReduction) / 10);
				hitPoints -= damage;
				if (isDead()) {
					hitPoints = 0;
					invincibleTime = 0;
					level.getSoundManager().playDeath();
					remove();
				}
			}
			updateAnim();
		}
	}

	/**
	 * Sets player animation according to HP
	 */
	private void updateAnim(){
		if (hitPoints == 0) {
			sprite = Animations.PLAYER_DEAD.getAnimation();
		}else if (hitPoints <= 250)
			sprite = Animations.PLAYER25.getAnimation();
		else if (hitPoints <= 500)
			sprite = Animations.PLAYER50.getAnimation();
		else if (hitPoints <= 750)
			sprite = Animations.PLAYER75.getAnimation();
		else
			sprite = Animations.PLAYER100.getAnimation();
	}

	/**
	 * Restores the players' HP by the specified amount and updates the sprite
	 * @param amount - Amount of HP to restore
	 */
	public void restoreHP(int amount) {
		hitPoints += amount;
		if(hitPoints > MAX_HP)
			hitPoints = MAX_HP;
		updateAnim();
	}

	/**
	 * Returns the player's current energy value
	 * 
	 * @return Player's current EN
	 */
	public float getEnergy() {
		return energy;
	}

	/**
	 * Depletes player's energy attribute
	 * 
	 * @param amount
	 *            - Amount of energy to be depleted
	 */
	public void deplete(float amount) {
		this.energy -= amount;
		if (energy < 0)
			energy = 0;
	}

	/**
	 * Restores the players' energy by the specified amount
	 * @param amount - Amount of energy to restore
	 */
	public void restoreEN(int amount) {
		energy += amount;
		if(energy > MAX_EN)
			energy = MAX_EN;
	}

	/**
	 * Returns player's X position
	 * 
	 * @return - Player's X position
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * Returns player's Y position
	 * 
	 * @return - Player's Y position
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * If the player has no more lives left, sets game over transition,
	 * otherwise, revives the player, which consists of the following actions:
	 * 
	 * <br>
	 * - Resets maneuverability to starting values <br>
	 * - Resets weapons to starting values and equips GatlingGun <br>
	 * - Restores HP/EN <br>
	 * - Sets the player as invincible <br>
	 * - Resets player position and move speeds to generate a "comeback" effect <br>
	 * - Lowers player's life count by 1 <br>
	 * - Sets deadTime to 0 to indicate the player is no longer dead
	 */
	public void revive() {
		if (lives == 0) {
			level.gameOver();
		} else {
			removed = false;
			int maneuverability = upgrades.getManeuverability();
			acceleration = maneuverability * ACC_INC;
			deceleration = maneuverability * DEC_INC;
			weapons = upgrades.cloneWeapons();
			damageReduction = upgrades.getDamageReduction();
			currWeapon = weapons[0];
			restoreHP(MAX_HP);
			restoreEN(MAX_EN);
			invincibleTime = 1000;
			this.x = 0;
			this.y = 0;
			this.xa = 1.8f;
			this.ya = 2f;
			lives--;
			deadTime = 0;
		}
	}

	/**
	 * Returns the level's relative Y axis acceleration
	 * @return
	 * level's relative Y axis acceleration
	 */
	public float getYLevelAcc() {
		return yLevelAcc;
	}
	
	/**
	 * Returns the level's relative X axis acceleration
	 * @return
	 * level's relative X axis acceleration
	 */
	public float getXLevelAcc() {
		return xLevelAcc;
	}

	/**
	 * Returns the player's current number of lives
	 * @return
	 * player's number of lives
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Returns the player's current scrap value
	 * @return
	 * player's current scrap value
	 */
	public int getScrap() {
		return scrap;
	}

	/**
	 * Returns true if player's shield is activated, false otherwise
	 * @return
	 * true if player's shield is activated, false otherwise
	 */
	public boolean isShielded() {
		return shield;
	}
	
	/**
	 * Increases number of lives by 1. If player already has 5 lives,
	 * adds 2000 to the scrap value instead
	 */
	public void addLife(){
		if(lives < 5)
			lives++;
		else
			addScrap(2000);
	}
	
	/**
	 * Adds the specified amount to the player's current crap value
	 * @param amount
	 * amount to add to scrap value
	 */
	public void addScrap(int amount){
		scrap += amount;
	}

	/**
	 * Increases the currently equipped weapon's power by 1.
	 * If the weapon already is at power 5, adds 500 to the scrap value instead
	 */
	public void incWeaponPower() {
		if(currWeapon.getPower() < 5)
			currWeapon.incPower();
		else
			addScrap(500);
	}

	/**
	 * Sets yLevelAcc attributo to specified value
	 * @param acc
	 * - Acceleration to set yLevelAcc to
	 */
	public void setYLevelAcc(int acc) {
		yLevelAcc = acc;		
	}
}
