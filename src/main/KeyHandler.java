package main;                                         

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upKeyDown, downKeyDown, leftKeyDown, rightKeyDown, turnLeftKeyDown, turnRightKeyDown;
	public int upKey = KeyEvent.VK_W;
	public int downKey = KeyEvent.VK_S;
	public int leftKey = KeyEvent.VK_A;
	public int rightKey = KeyEvent.VK_D;
	public int turnLeftKey = KeyEvent.VK_Q;
	public int turnRightKey = KeyEvent.VK_E;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == upKey) {
			upKeyDown = true;
		}
		
		if (e.getKeyCode() == downKey) {
			downKeyDown = true;
		}
		
		if (e.getKeyCode() == leftKey) {
			leftKeyDown = true;
		}
		
		if (e.getKeyCode() == rightKey) {
			rightKeyDown = true;
		}
		
		if (e.getKeyCode() == turnLeftKey) {
			turnLeftKeyDown = true;
		}
		
		if (e.getKeyCode() == turnRightKey) {
			turnRightKeyDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == upKey) {
			upKeyDown = false;
		}
		
		if (e.getKeyCode() == downKey) {
			downKeyDown = false;
		}
		
		if (e.getKeyCode() == leftKey) {
			leftKeyDown = false;
		}
		
		if (e.getKeyCode() == rightKey) {
			rightKeyDown = false;
		}
		
		if (e.getKeyCode() == turnLeftKey) {
			turnLeftKeyDown = false;
		}
		
		if (e.getKeyCode() == turnRightKey) {
			turnRightKeyDown = false;
		}
	}
}
