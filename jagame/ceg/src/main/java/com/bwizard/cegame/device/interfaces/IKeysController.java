package com.bwizard.cegame.device.interfaces;

import java.awt.event.KeyEvent;

public interface IKeysController {
	
	void keyReleased(KeyEvent e);
	void keyPressed(KeyEvent arg0);
	void clear();
	
}
