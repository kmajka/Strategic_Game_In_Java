package com.bwizard.cegame.controls.components;

import java.awt.Graphics;

import com.bwizard.cegame.tools.DrawManager;


public class FramePanel extends BaseDrawFigure  {

	public FramePanel() {
		super();
	}
	
	public FramePanel(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public boolean hasEvent() {
		return false;
	}
	
	@Override
	public void paint(Graphics g) {
		DrawManager.drawImage(g, this);
		super.paint(g);
	}
	
}
