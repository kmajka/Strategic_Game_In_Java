package com.bwizard.cegame.window.screen.provider;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;

import com.bwizard.cegame.documents.providers.ConfigurationProvider;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;

/*
 * This class is not used in project right now - it is only for test
 * @author Krzysztof Majka
 * @version 1.0
 */
public class FullScreenProvider extends WindowScreen implements IWindowScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2592717624133003306L;
	private GraphicsDevice graphicsDevice = null;
	private DisplayMode currentdisplayMode;
	private DisplayMode originalDisplayMode;
	private int screenWidth = 0;
	private int screenHeight = 0;
	
	public FullScreenProvider(ConfigurationProvider configurationProvider) {
		
		if(configurationProvider.getVideoConfiguration().isFullScreen()) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        screenWidth = screenSize.width;
	        screenHeight = screenSize.height;
		} else {
			screenWidth = configurationProvider.getVideoConfiguration().getWidthResolution();
			screenHeight = configurationProvider.getVideoConfiguration().getHeightResolution();
		}
		
		currentdisplayMode = new DisplayMode(screenWidth, screenHeight, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		//information about operation system
		GraphicsEnvironment graphicsEnironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//information about graphic card
		graphicsDevice = graphicsEnironment.getDefaultScreenDevice();
		originalDisplayMode = graphicsDevice.getDisplayMode();
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
	public void createGUI() {
		//turn off all additional of window
		this.setUndecorated(true);
		this.setResizable(false);
		
		graphicsDevice.setFullScreenWindow(this);
		if (currentdisplayMode != null && graphicsDevice.isDisplayChangeSupported()) {
			try {
				graphicsDevice.setDisplayMode(currentdisplayMode);
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Window getFullScreenWindow() {
		return graphicsDevice.getFullScreenWindow();
	}
	
	public void restoreScreen() {
		Window window = graphicsDevice.getFullScreenWindow();
		if (window != null) {
			window.dispose();
		}
		
		//restore previous window
		graphicsDevice.setFullScreenWindow(null);
	}
	
	@Override
	public void close() {
		graphicsDevice.setDisplayMode(originalDisplayMode);
	}
	
}
