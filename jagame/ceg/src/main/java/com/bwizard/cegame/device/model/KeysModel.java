package com.bwizard.cegame.device.model;

import java.awt.event.KeyEvent;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.device.interfaces.IKeysModel;

public class KeysModel implements IKeysModel {
	
	protected BaseWorldGame baseWorldGame = null;
	
	protected final int KEY_STEP = 40;
	
	public KeysModel(BaseWorldGame baseWorldGame) {
		this.baseWorldGame = baseWorldGame;
	}

	@Override
	public void keyReleased(KeyEvent e){
		baseWorldGame.getStateInfoGame().getKeysInfo().setKeyEvent(null);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void clear() {
	}
	
}
