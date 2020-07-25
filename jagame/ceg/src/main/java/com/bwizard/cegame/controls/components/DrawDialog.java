package com.bwizard.cegame.controls.components;

import com.bwizard.cegame.device.model.CursorInfo;

public class DrawDialog extends DrawPanel {
	
	public DrawDialog() {
		super();
	}
	
	public DrawDialog(CursorInfo cursorInfo, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cursorInfo = cursorInfo;
	}
}
