package com.bwizard.cegame.device.model;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controller.WorldObjectManager;
import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.interfaces.ICursorController;

public class CursorDialogModel extends CursorModel {

	public CursorDialogModel(WorldObjectManager worldObjectManager,
			BaseWorldGame baseWorldGame, ICursorController gameCursor) {
		super(worldObjectManager, baseWorldGame, gameCursor);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public void cursorMoved(int x, int y)
    {
    	//Called in response to the user moving the mouse with no mouse buttons pressed
    	setCursor(CursorName.HAND_CURSOR);
    	updateMousePosition(x, y);
    }
    
    @Override
    public void cursorDragged(CursorEvent e) {
    }
    	  
}
