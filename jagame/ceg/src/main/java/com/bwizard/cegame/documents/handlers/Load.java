package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about load figures for component
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum Load {

	DIR("DIR"),
	FILE("FILE"),
	NONE("NONE");
	
	private final String load;
	
	private Load(String load) {
		this.load = load;
	}
	
	public String getLoad() {
        return load;
    }
	
	public static Load fromString(String text) {
		if (text != null) {
			for (Load d : Load.values()) {
				if (text.toUpperCase().equals(d.load)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
