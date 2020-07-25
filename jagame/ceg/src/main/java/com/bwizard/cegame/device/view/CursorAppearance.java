package com.bwizard.cegame.device.view;

import java.awt.Cursor;
import java.util.HashMap;

import com.bwizard.cegame.device.controller.CursorAdapterController;
import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.model.CursorAppearanceInfo;

/*
 * This class manages appearance of the cursor
 * @author Krzysztof Majka
 * @version 1.0
 */
public abstract class CursorAppearance extends CursorAdapterController {

	private HashMap<CursorName, CursorAppearanceInfo> hmCursors = null;
	
	public CursorAppearance() {
		hmCursors = new HashMap<CursorName, CursorAppearanceInfo>();
		initialize();
	}
	
	protected void initialize() {
	}
	
	protected void addCursor(CursorName cursorName, CursorAppearanceInfo cursorAppearanceInfo) {
		if (!hmCursors.containsKey(cursorName)) {
			hmCursors.put(cursorName, cursorAppearanceInfo);
		}
	}
	
	public Cursor getCursor(CursorName cursorType) {
		if (hmCursors.containsKey(cursorType)) {
			return hmCursors.get(cursorType).getCursorInfo();
		}
		return null;
	}
	
	@Override
	public Cursor getCursorType(CursorName cursorName) {
		return getCursor(cursorName);
	}
	
	public void clear() {
		hmCursors.clear();
	}
	
}
