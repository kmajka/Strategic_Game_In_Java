package com.bwizard.cegame.frame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.bwizard.cegame.time.TimeInfo;


/*
 * This class manages frames which has been added in AnimateFrame 
 * @author Krzysztof Majka
 * @version 1.0
 */
public class FrameManager {
	
	private ArrayList<AnimateFrame> frames = null;
	private long totalDuration;
	private int currentFrameIndex;
	private long animationTime;
	
	public FrameManager() {
		frames = new ArrayList<AnimateFrame>();
		totalDuration = 0;
		start();
	}
	
	public void start() {
		currentFrameIndex = 0;
		animationTime = 0;
	}
	
	public void update(TimeInfo timeInfo) {
		if (frames.size() > 1) {
			animationTime += timeInfo.getElapsedTime();
			
			if (animationTime > totalDuration) {
				animationTime = animationTime % totalDuration;
			}

			long tmp = 0;
			currentFrameIndex = -1;
			while(animationTime > tmp) {
				currentFrameIndex++;
				tmp += getFrame(currentFrameIndex).endTime;
			}
			if(currentFrameIndex < 0) {
				currentFrameIndex = 0;
			}
		}
	}
	
	public void addFrame(BufferedImage image, long duration) {
		totalDuration += duration;
		frames.add(new AnimateFrame(image, duration));
	}
	
	public void dispose() {
		clearAllFrames();
		frames = null;
	}
	
	
	public void clearAllFrames() {
		currentFrameIndex = 0;
		animationTime = 0;
		totalDuration = 0;
		for(AnimateFrame animateFrame : frames) {
			animateFrame.delete();
		}
		frames.clear();
	}
	
	private AnimateFrame getFrame(int index) {
		return frames.get(index);
	}
	
	public BufferedImage getImage() {
		if (frames == null || frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrameIndex).image;
		}
	}
}
