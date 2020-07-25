package com.bwizard.cegame.device.controller;

import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.interfaces.ICursorModel;
import com.bwizard.cegame.device.model.CursorEvent;

/**
 * This class adds the cursor position into the list
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CursorController extends CursorAdapterController {
	
	private ICursorModel cursorModel;

	public CursorController(ICursorModel model)  {
		this.cursorModel = model;
	}
	
	@Override
	public void cursorUp(boolean clearSelection) {
		
		cursorModel.setCursor(CursorName.HAND_CURSOR);
		
		cursorModel.selectedComponents();
		
		if(cursorModel!= null && clearSelection) {
			cursorModel.clearSelection();
		}
		
	}
    
    @Override
    public void cursorDragged(CursorEvent e) {
    	cursorModel.cursorDragged(e);
    }
    
    @Override
    public void cursorMoved(CursorEvent e) {
    	cursorModel.cursorMoved(e.getX(), e.getY());
    }

	@Override
	public void setMoveCursor() {
		cursorModel.setCursor(CursorName.MOVE_CURSOR);
	}

	@Override
	public void setPointDestination() {
		cursorModel.setPointDestination();
	}

	@Override
	public void cursorDown() {
		cursorModel.selectingComponents();
	}
	
	@Override
	public void clearComponents() {
		cursorModel.clearComponents();
	}
	
	@Override
	public void update() {
		cursorModel.update();
	}
	
	@Override
	public void performPressed(int numButton) {
		cursorModel.performPressed(numButton);		
	}
	
	@Override
	public void clear() {
		cursorModel.clearComponents();
		cursorModel.clearSelection();
		cursorModel = null;
	}
	
	
}
