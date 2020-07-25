package com.bwizard.cegame.device.model;

import java.awt.event.MouseEvent;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controller.WorldObjectManager;
import com.bwizard.cegame.device.interfaces.ICursorController;
import com.bwizard.cegame.figure.BaseFigure;

public class CursorEditorModel extends CursorModel {

	public CursorEditorModel(WorldObjectManager worldObjectManager,
			BaseWorldGame baseWorldGame, ICursorController gameCursor) {
		super(worldObjectManager, baseWorldGame, gameCursor);
		// TODO Auto-generated constructor stub
	}
	
	
    @Override
    public void cursorMoved(int x, int y)
    {
    	super.cursorMoved(x, y);
    	
    	//for editor
		for(BaseFigure figure : worldObjectManager.getSelectedObjects()) {
			
			if (figure.isPlacedManualy() && cursorInfo.isCursorOverViewUser()) {
				int px = cursorInfo.getMapCursorPositionX() - (figure.getWidth() /2);
				int py = cursorInfo.getMapCursorPositionY() - (figure.getHeight() /2);
				figure.setAllMapPositions(px, py);
				figure.setPlaceFree(worldObjectManager.getUserViewManager().getUserViewObjects());
			}
		}
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
			selectingComponents();
		}
		
		
	}

}
