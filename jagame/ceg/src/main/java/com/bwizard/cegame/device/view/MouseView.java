package com.bwizard.cegame.device.view;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.bwizard.cegame.device.interfaces.ICursorController;
import com.bwizard.cegame.device.model.CursorEvent;

public class MouseView extends MouseAdapter {

	private ICursorController cursorController;
	private CursorEvent cursorEvent;
	
	public MouseView(ICursorController cursorController)  {
		this.cursorController = cursorController;
		this.cursorEvent = new CursorEvent();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			cursorController.cursorUp(true);
		} else {
			cursorController.cursorUp(false);
		}

	}
		
    @Override
    public void mousePressed(MouseEvent e) {
    	cursorController.performPressed(e.getButton());    	
    }
    
    //Called in response to the user moving the mouse while holding a mouse button down.
    @Override
    public void mouseDragged(MouseEvent e) {
    	
    	updateCursorEvent(e);
    	cursorController.cursorDragged(cursorEvent);
    }
    
    //Called in response to the user moving the mouse with no mouse buttons pressed
    @Override
    public void mouseMoved(MouseEvent e) {
    	
    	updateCursorEvent(e);
    	cursorController.cursorMoved(cursorEvent);
    }
    
    private void updateCursorEvent(MouseEvent e) {
    	cursorEvent.setX(e.getX());
    	cursorEvent.setY(e.getY());
    	cursorEvent.setLeftButton(((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0));
    	cursorEvent.setMiddleButton(((e.getModifiers() & InputEvent.BUTTON2_MASK) != 0));
    }
}
