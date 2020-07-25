package com.bwizard.cegame.device.model;

import java.awt.event.MouseEvent;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controller.WorldObjectManager;
import com.bwizard.cegame.device.interfaces.ICursorController;

public class CursorGameModel extends CursorModel {

	public CursorGameModel(WorldObjectManager worldObjectManager,
			BaseWorldGame baseWorldGame, ICursorController gameCursor) {
		super(worldObjectManager, baseWorldGame, gameCursor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void performPressed(int numButton) {
		
		//Left Mouse Bottom	
		if (numButton == MouseEvent.BUTTON1) {  	
			selectingComponents();
		//Middle Mouse Bottom	
		} else if (numButton== MouseEvent.BUTTON2) {
			gameCursor.setMoveCursor();
		//Right Mouse Bottom
		} else if (numButton == MouseEvent.BUTTON3) {
			setPointDestination();
		}
	}

}
