package com.bwizard.cegame.controls.components;

import java.awt.Graphics;

import com.bwizard.cegame.tools.DrawManager;


public class DrawTextEdit extends BaseDrawFigure {
	
	public DrawTextEdit() {
		super();
	}
	
	public DrawTextEdit(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.autoUnselect = false;
	}
	
	@Override
	public void paint(Graphics g) {
		
		DrawManager.drawPanel(g, this, false);
		
		textProvider.setCursorOverFigure(isCursorOverFigure());
		
		if(textProvider.isNeeded()) {
			textProvider.setCursorOverFigure(isCursorOverFigure());
			textProvider.displayRealText(g, this);
		}
		
		super.paint(g);
		
	}
	
}
