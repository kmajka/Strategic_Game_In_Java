package com.bwizard.cegame.device.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.bwizard.cegame.device.interfaces.IKeysController;

public class KeysView extends KeyAdapter {

	private IKeysController keysController = null;
	
	public KeysView(IKeysController keysController) {
		this.keysController = keysController;
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		keysController.keyReleased(e);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		keysController.keyPressed(arg0);
	}
}
