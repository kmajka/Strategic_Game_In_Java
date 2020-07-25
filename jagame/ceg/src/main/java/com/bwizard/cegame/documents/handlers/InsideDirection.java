package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about inside direction of component
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum InsideDirection {

	LEFT("LEFT"),
	RIGHT("RIGHT"),
	DOWN("DOWN"),
	UP("UP"),
	NONE("NONE");
	
	private final String insideDirection;
	
	private InsideDirection(String insideDirection) {
		this.insideDirection = insideDirection;
	}
	
	public String getInsideDirection() {
        return insideDirection;
    }
	
	public static InsideDirection fromString(String text) {
		if (text != null) {
			for (InsideDirection d : InsideDirection.values()) {
				if (text.toUpperCase().equals(d.insideDirection)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
