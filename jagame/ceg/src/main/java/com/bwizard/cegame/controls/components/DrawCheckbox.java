package com.bwizard.cegame.controls.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bwizard.cegame.device.model.CursorInfo;
import com.bwizard.cegame.parameters.CmnGlobals;
import com.bwizard.cegame.tools.DrawManager;
import com.bwizard.cegame.utils.ImageUtil;

public class DrawCheckbox extends BaseDrawFigure {
	
	private String markCheckbox;
	private String unMarkCheckbox;
	
	private BufferedImage markCheckboxImage = null;
	private BufferedImage unMarkCheckboxImage = null;
	
	public DrawCheckbox() {
		super();
		markCheckbox = "checkbox_mark.png";
		unMarkCheckbox = "checkbox_unmark.png";
		this.canPressed = true;
		this.autoUnselect = false;
		this.markCheckboxImage = getImageSprite(markCheckbox);
		this.unMarkCheckboxImage = getImageSprite(unMarkCheckbox);
	}
	
	public DrawCheckbox(CursorInfo cursorInfo, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cursorInfo = cursorInfo;
		this.canPressed = true;
		this.autoUnselect = false;
	}
		
	@Override
	public void paint(Graphics g) {
		
		DrawManager.drawPanel(g, this);
		
		textProvider.setCursorOverFigure(isCursorOverFigure());
		textProvider.display(g);
				
		if (isSelected()) {
			setImage(markCheckboxImage);
		} else {
			setImage(unMarkCheckboxImage);
		}
		
		DrawManager.drawImage(g, this, false);	
				
		if(textProvider.isNeeded()) {
			textProvider.setCursorOverFigure(isCursorOverFigure());
			textProvider.display(g);
		}
		
		super.paint(g);
	}
	
	private BufferedImage getImageSprite(String file) {
		return ImageUtil.loadImage(CmnGlobals.CONTROLS_CHECKBOX_IMAGE_PATH + file);
	}
	
	@Override
	public void setPressed(boolean selected) {
		
		this.selected ^= selected;
		invokeEvent();
				
	}
	
	@Override
	protected void invokeEvent() {
		
		if (eventHandler != null) {
			eventHandler.invokeEvent(this, tag);
		}
	}
	
	@Override
	public void setPressed(boolean selected, String currentTag) throws Exception {
		
		this.selected ^= selected;
		invokeEvent(currentTag);

	}

	public String getMarkCheckbox() {
		return markCheckbox;
	}

	public void setMarkCheckbox(String markCheckbox) {
		this.markCheckbox = markCheckbox;
		this.markCheckboxImage = getImageSprite(markCheckbox);
	}

	public String getUnMarkCheckbox() {
		return unMarkCheckbox;
	}

	public void setUnMarkCheckbox(String unMarkCheckbox) {
		this.unMarkCheckbox = unMarkCheckbox;
		this.unMarkCheckboxImage = getImageSprite(unMarkCheckbox);
	}
	
}
