package com.bwizard.cegame.configuration.components.interfaces;

public interface IVideoConfiguration {
	
	int getWindowPositionX();
	void setWindowPositionX(int windowPositionX);
	int getWindowPositionY();
	void setWindowPositionY(int windowPositionY);
	
	void setHeightResolution(int heightResolution);
	int getHeightResolution();
	void setWidthResolution(int widthResolution);
	int getWidthResolution();
	
	int getBrightnes();
	void setBrightnes(int brightnes);
	boolean isWindowedMode();
	void setWindowedMode(boolean windowedMode);
	boolean isFullScreen();
	void setFullScreen(boolean fullScreen);
	
	void setImageDpi(String imageDpi);
	String getImageDpi();
}
