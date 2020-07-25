package com.bwizard.cegame.device.controller;

import java.awt.event.KeyEvent;

import com.bwizard.cegame.device.interfaces.IKeysModel;


/**
 * This class moves whole the map and changing position of cameraX and cameraY
 * @author Krzysztof Majka
 * @version 1.0
 */
public class KeysController extends KeysAdapterController {

	private IKeysModel keysModel;
	
	public KeysController(IKeysModel keysModel)  {
		this.keysModel = keysModel;
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		keysModel.keyReleased(e);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		keysModel.keyPressed(arg0);
	}
	
}
