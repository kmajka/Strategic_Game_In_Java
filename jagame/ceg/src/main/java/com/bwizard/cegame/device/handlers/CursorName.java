package com.bwizard.cegame.device.handlers;

public enum CursorName {
	UP_CURSOR("UP_CURSOR"),
	DOWN_CURSOR("DOWN_CURSOR"),
	LEFT_CURSOR("LEFT_CURSOR"),
	RIGHT_CURSOR("RIGHT_CURSOR"),
	LEFT_UP_CURSOR("LEFT_UP_CURSOR"),
	LEFT_DOWN_CURSOR("LEFT_DOWN_CURSOR"),
	RIGHT_UP_CURSOR("RIGHT_UP_CURSOR"),
	RIGHT_DOWN_CURSOR("RIGHT_DOWN_CURSOR"),
	HAND_CURSOR("HAND_CURSOR"),
	MOVE_CURSOR("MOVE_CURSOR");
	
	private final String cursorName;
	
	private CursorName(String cursorName) {
		this.cursorName = cursorName;
	}
	
	public String getCursorName() {
        return cursorName;
    }
	
	public static CursorName fromString(String text) {
		if (text != null) {
			for (CursorName d : CursorName.values()) {
				if (text.toUpperCase().equals(d.cursorName)) {
					return d;
				}
			}
	    }
	    return null;
    }
}