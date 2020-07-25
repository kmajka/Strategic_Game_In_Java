package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about Arrangement of component
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum Arrangement {

	VERTICAL("VERTICAL"),
	HORIZONTAL("HORIZONTAL"),
	NONE("NONE");
	
	private final String arrangement;
	
	private Arrangement(String arrangement) {
		this.arrangement = arrangement;
	}
	
	public String getArrangement() {
        return arrangement;
    }
	
	public static Arrangement fromString(String text) {
		if (text != null) {
			for (Arrangement d : Arrangement.values()) {
				if (text.toUpperCase().equals(d.arrangement)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
