package com.bwizard.cegame.window.handlers;

/*
 * This enumerator provides information about window for game
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum WindowGameActionName {

	EXIT("EXIT"),
	NONE("NONE"),
	REOPEN("REOPEN");
	
	private final String windowGameActionName;
	
	private WindowGameActionName(String windowGameName) {
		this.windowGameActionName = windowGameName;
	}
	
	public String getWindowGameActionName() {
        return windowGameActionName;
    }
	
	public static WindowGameActionName fromString(String text) {
		if (text != null) {
			for (WindowGameActionName d : WindowGameActionName.values()) {
				if (text.toUpperCase().equals(d.windowGameActionName)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
