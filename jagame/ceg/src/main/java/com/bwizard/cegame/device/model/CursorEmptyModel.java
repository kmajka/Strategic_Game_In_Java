package com.bwizard.cegame.device.model;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controller.WorldObjectManager;
import com.bwizard.cegame.device.interfaces.ICursorController;

public class CursorEmptyModel extends CursorModel {

	public CursorEmptyModel(WorldObjectManager worldObjectManager,
			BaseWorldGame baseWorldGame, ICursorController gameCursor) {
		super(worldObjectManager, baseWorldGame, gameCursor);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public void cursorMoved(int x, int y) {
    }
    
    @Override
    public void cursorDragged(CursorEvent e) {
    }
    
    @Override
	public void selectingComponents() {
    }
  
    @Override
	public void selectedComponents() {
    }
  
    @Override
	public void performPressed(int numButton) {
    }

}
