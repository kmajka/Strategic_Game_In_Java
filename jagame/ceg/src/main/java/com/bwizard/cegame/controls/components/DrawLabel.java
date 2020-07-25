package com.bwizard.cegame.controls.components;

import java.awt.Graphics;

import com.bwizard.cegame.tools.DrawManager;


public class DrawLabel extends BaseDrawFigure {
	
	public DrawLabel() {
		super();
	}
	
	public DrawLabel(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void paint(Graphics g) {
		
		DrawManager.drawPanel(g, this, false);
		
		textProvider.setCursorOverFigure(isCursorOverFigure());
		textProvider.display(g);
		
	}
	
}
