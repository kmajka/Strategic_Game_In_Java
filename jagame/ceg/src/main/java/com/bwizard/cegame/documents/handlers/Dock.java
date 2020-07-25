package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about dock of component
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum Dock {

	TOP("TOP"),
	BOTTOM("BOTTOM"),
	LEFT("LEFT"),
	RIGHT("RIGHT"),
	FILL("FILL");
	
	private final String dock;
	
	private Dock(String dock) {
		this.dock = dock;
	}
	
	public String getDock() {
        return dock;
    }
	
	public static Dock fromString(String text) {
		if (text != null) {
			for (Dock d : Dock.values()) {
				if (text.toUpperCase().equals(d.dock)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
