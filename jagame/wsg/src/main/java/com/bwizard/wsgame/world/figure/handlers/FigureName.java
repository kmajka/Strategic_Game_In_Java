package com.bwizard.wsgame.world.figure.handlers;

/*
 * This enumerator provides information about figure name placed into map
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum FigureName {

	SIMPLE_FIRE("SIMPLE_FIRE"),
	SIMPLE_TREE("SIMPLE_TREE"),
	SIMPLE_CASTLE("SIMPLE_CASTLE"),
	BOX("BOX"),
	SIMPLE_HOUSE("SIMPLE_HOUSE"),
	SIMPLE_WINDMILL("SIMPLE_WINDMILL"),
	SIMPLE_FLOWER("SIMPLE_FLOWER"),
	SIMPLE_STONE("SIMPLE_STONE"), 
	SIMPLE_SEA("SIMPLE_SEA");
	
	private final String figureName;
	
	private FigureName(String figureName) {
		this.figureName = figureName;
	}
	
	public String getFigureName() {
        return figureName;
    }
	
	public static FigureName fromString(String text) {
		if (text != null) {
			for (FigureName d : FigureName.values()) {
				if (text.toUpperCase().equals(d.figureName)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
