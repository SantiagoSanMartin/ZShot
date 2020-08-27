package ar.zgames.zshot.system;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Input handler for keyboard and mouse
 */
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener{
	
	private Window window;
	
	/**
	 * Constructs a new InputHandler object
	 * @param window
	 * - Window object
	 */
	public InputHandler(Window window){
        window.addMouseListener(this);
        window.addKeyListener(this);
        window.addMouseMotionListener(this);
		this.window = window;
	}
	
	/**
	 * Handle action commands. 
	 * <p>
	 * If the down attribute is true, it means the input 
	 * key for that action is being pressed. More than
	 * one input key might activate a single action.
	 */
	public class Action {

		public boolean down;

		/**
		 * Constructor for each action. Adds the new 
		 * action to the actions array attribute of the
		 * InputHandler class
		 */
		public Action() {
			actions.add(this);
		}

		/**
		 * Used to change the value of down to true if the
		 * key for this action is being pressed
		 * @param pressed - true if key for this action is 
		 * being pressed, false otherwise
		 */
		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
		}
		
		/**
		 * Force the key to be released so it does not activate multiple times
		 */
		public void release(){
			down = false;
		}
	}

	private List<Action> actions = new ArrayList<Action>(); // Input actions array

	public Action up = new Action();			// Go up action
	public Action down = new Action();			// Go down action
	public Action left = new Action();			// Go left action
	public Action right = new Action();			// Go right action
	public Action weapon1 = new Action();		// Set weapon 1
	public Action weapon2 = new Action();		// Set weapon 2
	public Action weapon3 = new Action();		// Set weapon 3
	public Action togglePWeapon = new Action();	// Toggle to previous weapon
	public Action toggleNWeapon = new Action();	// Toggle to next weapon
	public Action shoot = new Action();			// Shoot action
	public Action menu = new Action();			// Open menu action
	public Action shield = new Action();		// Activate shield
	public Action pause = new Action();			// Pause game
	
	private int mouseX;	// Mouse X axis position
	private int mouseY; // Mouse Y axis position
	
	@Override
	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	/**
	 * Interprets KeyEvent from KeyListener
	 * @param ke - KeyEvent to be interpreted
	 * @param pressed - true when key is being pressed, false otherwise
	 */
	private void toggle(KeyEvent ke, boolean pressed) {
		if (ke.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_1) weapon1.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_2) weapon2.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_3) weapon3.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_NUMPAD1) weapon1.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_NUMPAD2) weapon2.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_NUMPAD3) weapon3.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_Q) togglePWeapon.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_E) toggleNWeapon.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) menu.toggle(pressed);	
		if (ke.getKeyCode() == KeyEvent.VK_SHIFT) shield.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_P) pause.toggle(pressed);
	}
	
	/**
	 * Returns the current mouse X coordinate
	 * @return Integer representing the mouse X coordinate
	 */
	public int getMX(){
		return mouseX;
		
	}
	
	/**
	 * Returns the current mouse Y coordinate
	 * @return Integer representing the mouse Y coordinate
	 */
	public int getMY(){
		return mouseY;
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void mouseClicked(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	@Override
	public void mousePressed(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1){
			setMouseCoords(me);
			shoot.toggle(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1){
			shoot.toggle(false);
		}
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		setMouseCoords(me);
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		
	}
	
	private void setMouseCoords(MouseEvent me){
		mouseX = (int) Math.round((double)me.getX() * (double)ScreenManager.getWidth()/(double)window.getWidth());
		mouseY = (int) Math.round((double)me.getY() * (double)ScreenManager.getHeight()/(double)window.getHeight());
	}
}
