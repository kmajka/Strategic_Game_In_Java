package com.bwizard.cegame.window.screen.interfaces;

import java.awt.Cursor;
import java.awt.Dimension;

import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.interfaces.ICursorController;
import com.bwizard.cegame.window.layout.interfaces.IWindowLayout;

/*
 * This interface allows to invoke common methods for screen
 * @author Krzysztof Majka
 * @version 1.0
 */
public interface IWindowScreen {
	int getHeight();
	int getWidth();
	Dimension getSize();
	void setDefaultCloseOperation(int operation);
	void setMainLayout(IWindowLayout windowLayout);
	void setLayout(IWindowLayout windowLayout);
	void createGUI();
	void setCursor(Cursor cursor);
	void setCursor(ICursorController gameCursor, CursorName cursorName);
	void clean();
	void close();
}
