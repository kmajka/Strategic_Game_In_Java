package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about Align of component
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum Align {

	RIGHT("RIGHT"),
	CENTER("CENTER"),
	LEFT("LEFT"),
	NONE("NONE");
	
	private final String align;
	
	private Align(String align) {
		this.align = align;
	}
	
	public String getAlign() {
        return align;
    }
	
	public static Align fromString(String text) {
		if (text != null) {
			for (Align d : Align.values()) {
				if (text.toUpperCase().equals(d.align)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
