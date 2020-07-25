package com.bwizard.cegame.window.screen.provider;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.bwizard.cegame.documents.providers.ConfigurationProvider;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;

/*
 * This class displays the main application window
 * @author Krzysztof Majka
 * @version 1.0
 */
public class SimpleScreenProvider extends WindowScreen implements IWindowScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159210109676022841L;
	private int screenWidth = 0;
	private int screenHeight = 0;
	private ConfigurationProvider configurationProvider = null;
	
	public SimpleScreenProvider(ConfigurationProvider configurationProvider) {
		
		this.configurationProvider = configurationProvider;
	
		if(configurationProvider.getVideoConfiguration().isFullScreen()) {
			GraphicsConfiguration config = this.getGraphicsConfiguration();
			//additional information on the internal space of the display window
			Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
			//information about client screen size
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        
	        screenWidth = screenSize.width - insets.left - insets.right;
	        screenHeight = screenSize.height - insets.top - insets.bottom;
		} else {
			screenWidth = configurationProvider.getVideoConfiguration().getWidthResolution();
			screenHeight = configurationProvider.getVideoConfiguration().getHeightResolution();
		}
        
        this.setSize(screenWidth, screenHeight);
	}
	
	@Override
	public int getWidth() {
		return screenWidth;
	}
	
	@Override
	public int getHeight() {
		return screenHeight;
	}

	@Override
	public Component add(Component panel) {
		return super.add(panel);		
	}
	
	/**
	 * This method creates Graphical User Interface for main window.
	 */
	@Override
	public void createGUI() {
		if(configurationProvider.getVideoConfiguration().isFullScreen()) {
			setUndecorated(true);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

}
