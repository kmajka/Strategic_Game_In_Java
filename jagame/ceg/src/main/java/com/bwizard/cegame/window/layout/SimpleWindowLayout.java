package com.bwizard.cegame.window.layout;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.window.layout.interfaces.IWindowLayout;

/*
 * This class create layout for main window 
 * @author Krzysztof Majka
 * @version 1.0
 */
public class SimpleWindowLayout implements IWindowLayout {

	private JPanel mainPanel = null;
	/*
	 * This constructor creates right management panel.
	 * @param worldAreaManager The value for set middle panel where main world will be displayed. 
	 * @param windowSize The value for window size.
	 */
	public SimpleWindowLayout(BaseWorldGame baseWorldGame) {
		mainPanel = new JPanel();		
		mainPanel.setLayout(new GridLayout(1, 1));
	    mainPanel.add(baseWorldGame);
	}
	
	/**
	 * @Override
	 * This method gets main layout for window 
	 * return mainPanel
	 */
	@Override
	public JPanel getMainLayout() {
		return mainPanel;
	}
	
}
