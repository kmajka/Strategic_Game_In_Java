package com.bwizard.cegame.device.interfaces;

import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.model.CursorEvent;

public interface ICursorModel {
	
	void selectingComponents();
	void selectedComponents();
	void cursorMoved(int x, int y);
	void cursorDragged(CursorEvent e);
	
	void clearSelection();
	void clearComponents();
	void update();
	void setCursor(CursorName cursorName);
	void setPointDestination();
	void updateMousePosition(int x, int y);
	
	void performPressed(int numButton);
	
}
