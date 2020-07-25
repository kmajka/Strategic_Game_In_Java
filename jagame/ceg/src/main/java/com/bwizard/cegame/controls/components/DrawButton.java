package com.bwizard.cegame.controls.components;

import java.awt.Graphics;

import com.bwizard.cegame.device.model.CursorInfo;
import com.bwizard.cegame.tools.DrawManager;

public class DrawButton extends BaseDrawFigure {
	
	public DrawButton() {
		super();
		this.canPressed = true;
		this.autoUnselect = true;
	}
	
	public DrawButton(CursorInfo cursorInfo, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cursorInfo = cursorInfo;
		this.canPressed = true;
		this.autoUnselect = true;
	}
	
	@Override
	public void paint(Graphics g) {
		
		if(isVisible()) {
							
			DrawManager.drawPanel(g, this);
			updateImage();
			DrawManager.drawImage(g, this);	
			
			if(textProvider.isNeeded()) {
				textProvider.setCursorOverFigure(isCursorOverFigure());
				textProvider.display(g);
			}

			//border
			super.paint(g);
		}
	}
	
	private void updateImage() {
		if(getFocusedImage() != null  || getSelectedImage() != null){
			//button selected or pressed
			if (this.getCountPressed() == 1 || this.getCountPressed() == 2) {
				setImage(getSelectedImage());
			} else if (isCursorOverFigure()) {
				setImage(getFocusedImage());
			} else {
				setImage(getMainImage());
			}
			
		} else {
			setImage(getMainImage());
		}
	}

}
