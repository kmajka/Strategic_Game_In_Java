package com.bwizard.cegame.device.interfaces;

import java.awt.event.KeyEvent;

public interface IKeysModel {
	
	void keyReleased(KeyEvent e);
	void keyPressed(KeyEvent arg0);
	void clear();
	
}
