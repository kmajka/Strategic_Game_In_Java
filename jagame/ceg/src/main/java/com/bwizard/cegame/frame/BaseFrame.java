package com.bwizard.cegame.frame;

import java.awt.image.BufferedImage;

import com.bwizard.cegame.figure.ActivityBaseFigure;
import com.bwizard.cegame.time.TimeInfo;

public abstract class BaseFrame {
	
	public FrameManager frameManager = null;
	protected ActivityBaseFigure activityFigure;
	private boolean isDisposed = false;

	public BaseFrame() {
		frameManager = new FrameManager();
	}
	
	public void clear() {
		frameManager.clearAllFrames();
	}
	
	public void dispose() {
		
		if (!isDisposed) {
			frameManager.dispose();
			frameManager = null;
			isDisposed = true;
		}
		
	}
	
	public void setActivityFigure(String activityFigure) {
		this.activityFigure.setActualActivity(activityFigure);
		refreshImage();
	}
	
	public String getActualActivity() {
		return this.activityFigure.getActualActivity();
	}
	
	public void updateFrame(TimeInfo timeInfo) {
		frameManager.update(timeInfo);
	}
	
	public BufferedImage getImage() {
		return frameManager.getImage();
	}
	
	public int getWidth() {
		return getImage().getWidth();
	}

	public int getHeight() {
		return getImage().getHeight();
	}
	
	protected void refreshImage() {
	}
	
	protected abstract BufferedImage getImageSprite(String file);
	
}
