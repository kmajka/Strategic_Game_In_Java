package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about global position of component
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum GlobalPosition {

	CENTER("CENTER"),
	LEFT("LEFT"),
	RIGHT("RIGHT"),
	TOP("TOP"),
	BOTTOM("BOTTOM");
	
	private final String globalPosition;
	
	private GlobalPosition(String globalPosition) {
		this.globalPosition = globalPosition;
	}
	
	public String getGlobalPosition() {
        return globalPosition;
    }
	
	public static GlobalPosition fromString(String text) {
		if (text != null) {
			for (GlobalPosition d : GlobalPosition.values()) {
				if (text.toUpperCase().equals(d.globalPosition)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
