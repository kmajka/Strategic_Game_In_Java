package com.bwizard.cegame.device.interfaces;

import java.awt.Cursor;
import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.model.CursorEvent;

public interface ICursorController {
	void cursorUp(boolean clearSelection);
	void cursorDragged(CursorEvent e);
	void cursorMoved(CursorEvent e);
	void setMoveCursor();
	void setPointDestination();
	void performPressed(int numButton);
	void cursorDown();
	Cursor getCursorType(CursorName cursorName);
	void clearComponents();
	void clear();
	void update();
}
