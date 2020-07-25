package com.bwizard.cegame.state.handlers;

/*
 * This enumerator provides information about Entry it control which model will be choose for mouse and keys
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum Entry {

	//game view
	GAME("GAME"),
	//editor view
	EDITOR("EDITOR"),
	//main menu
	MENU("MENU"),
	//all dialogs
	DIALOG("DIALOG"),
	//there is no defined entry
	NONE("NONE");
	
	private final String entry;
	
	private Entry(String entry) {
		this.entry = entry;
	}
	
	public String getEntry() {
        return entry;
    }
	
	public static Entry fromString(String text) {
		if (text != null) {
			for (Entry d : Entry.values()) {
				if (text.toUpperCase().equals(d.entry)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
