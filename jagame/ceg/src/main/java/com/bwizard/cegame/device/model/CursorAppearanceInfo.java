package com.bwizard.cegame.device.model;

import java.awt.Cursor;

/*
 * This class stores informations about cursor image
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CursorAppearanceInfo {
	
	private Cursor cursor = null;
	
	public CursorAppearanceInfo(Cursor cursor) {
		this.setCursor(cursor);
	}

	public Cursor getCursorInfo() {
		return cursor;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

}
