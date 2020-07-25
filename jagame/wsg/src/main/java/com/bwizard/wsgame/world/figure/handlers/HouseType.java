package com.bwizard.wsgame.world.figure.handlers;

/*
 * This enumerator provides information about houses
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum HouseType {

	HOUSE_1("HOUSE_1"),
	HOUSE_2("HOUSE_2"),
	HOUSE_3("HOUSE_3"),
	HOUSE_4("HOUSE_4");
	
	private final String houseType;
	
	private HouseType(String houseType) {
		this.houseType = houseType;
	}
	
	public String getHouseType() {
        return houseType;
    }
	
	public static HouseType fromString(String text) {
		if (text != null) {
			for (HouseType d : HouseType.values()) {
				if (text.toUpperCase().equals(d.houseType)) {
					return d;
				}
			}
	    }
	    return null;
    }
}