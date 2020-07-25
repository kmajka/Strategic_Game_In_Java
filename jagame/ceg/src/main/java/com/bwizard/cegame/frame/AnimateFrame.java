package com.bwizard.cegame.frame;

import java.awt.image.BufferedImage;

/*
 * This class stores informations about image and time, how long image should be displayed
 * @author Krzysztof Majka
 * @version 1.0
 */
public class AnimateFrame {
	
	BufferedImage image = null;
	long endTime = 0;
	
	public AnimateFrame(BufferedImage image, long endTime) {
		this.image = image;
		this.endTime = endTime;
	}
	
	public void delete() {
		image.flush();
		image = null;
	}
}
