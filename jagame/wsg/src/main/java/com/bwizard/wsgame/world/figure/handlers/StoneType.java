package com.bwizard.wsgame.world.figure.handlers;

/*
 * This enumerator provides information about stone
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum StoneType {

	STONE_1("STONE_1"),
	STONE_2("STONE_2");
	
	private final String stoneType;
	
	private StoneType(String stoneType) {
		this.stoneType = stoneType;
	}
	
	public String getStoneType() {
        return stoneType;
    }
	
	public static StoneType fromString(String text) {
		if (text != null) {
			for (StoneType d : StoneType.values()) {
				if (text.toUpperCase().equals(d.stoneType)) {
					return d;
				}
			}
	    }
	    return null;
    }
}