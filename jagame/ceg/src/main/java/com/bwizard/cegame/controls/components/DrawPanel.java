package com.bwizard.cegame.controls.components;

import java.awt.Graphics;

import com.bwizard.cegame.device.model.CursorInfo;
import com.bwizard.cegame.tools.DrawManager;


public class DrawPanel extends BaseDrawFigure {
	
	public DrawPanel() {
		super();
	}

	public DrawPanel(CursorInfo cursorInfo, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cursorInfo = cursorInfo;
	}
	
	@Override
	public void paint(Graphics g) {

			DrawManager.drawPanel(g, this, true);
			DrawManager.drawImage(g, this);
			
			if(textProvider.isNeeded()) {
				textProvider.setCursorOverFigure(isCursorOverFigure());
				textProvider.displayRealText(g, this);
			}
			
			super.paint(g);

	}

}
