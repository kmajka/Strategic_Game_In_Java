package com.bwizard.cegame.window.screen.provider;

import javax.swing.JFrame;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.interfaces.ICursorController;
import com.bwizard.cegame.window.layout.interfaces.IWindowLayout;


/*
 * This class create layout for main window
 * @author Krzysztof Majka
 * @version 1.0
 */
public class WindowScreen extends JFrame {

	private static final long serialVersionUID = -5538586404546200239L;
	protected BaseWorldGame baseWorldGame = null;
		
	public void setMainLayout(IWindowLayout windowLayout) {
		add(windowLayout.getMainLayout());
	}
	
	public void setLayout(IWindowLayout windowLayout) {
		add(windowLayout.getMainLayout());
	}	
	
	public void closeWindow() {
		java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new java.awt.event.WindowEvent(this, java.awt.event.WindowEvent.WINDOW_CLOSING));
	}
	
	public void setCursor(ICursorController gameCursor, CursorName cursorName) {
		setCursor(gameCursor.getCursorType(cursorName));
	}
	
	public void clean() {
		setVisible(false);
		dispose();
	}
	
	public void close() {		
	}	
}
