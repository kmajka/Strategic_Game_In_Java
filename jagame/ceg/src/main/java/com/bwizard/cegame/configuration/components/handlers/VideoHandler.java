package com.bwizard.cegame.configuration.components.handlers;

import com.bwizard.cegame.configuration.components.interfaces.IVideoConfiguration;

public class VideoHandler implements IVideoConfiguration {

	private int windowPositionX;
	private int windowPositionY;
	private int widthResolution;
	private int heightResolution;
	
	private boolean windowedMode = false;
	private boolean fullScreen = false;
	private int brightnes;
	
	private String imageDpi;
	
	@Override
	public int getWindowPositionX() {
		return windowPositionX;
	}

	@Override
	public void setWindowPositionX(int windowPositionX) {
		this.windowPositionX = windowPositionX;
	}

	@Override
	public int getWindowPositionY() {
		return windowPositionY;
	}

	@Override
	public void setWindowPositionY(int windowPositionY) {
		this.windowPositionY = windowPositionY;
	}

	@Override
	public void setHeightResolution(int heightResolution) {
		this.heightResolution = heightResolution;
	}

	@Override
	public int getHeightResolution() {
		return heightResolution;
	}

	@Override
	public void setWidthResolution(int widthResolution) {
		this.widthResolution = widthResolution;
	}

	@Override
	public int getWidthResolution() {
		return widthResolution;
	}

	@Override
	public int getBrightnes() {
		return brightnes;
	}

	@Override
	public void setBrightnes(int brightnes) {
		this.brightnes = brightnes;
	}

	@Override
	public boolean isWindowedMode() {
		return windowedMode;
	}

	@Override
	public void setWindowedMode(boolean windowedMode) {
		this.windowedMode = windowedMode;
	}

	@Override
	public boolean isFullScreen() {
		return fullScreen;
	}

	@Override
	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}

	@Override
	public void setImageDpi(String imageDpi) {
		this.imageDpi = imageDpi;	
	}

	@Override
	public String getImageDpi() {
		return imageDpi;
	}

}
