package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about configuration of map and game
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum ConfigurationName {

	MAP("MAP"),
	CELL("CELL"),
	CAMERA("CAMERA"),
	SOUND("SOUND"),
	VIDEO("VIDEO"),
	MOUSE("MOUSE"),
	KEY("KEY"),
	FIGURE("FIGURE");
	
	private final String configurationName;
	
	private ConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
	
	public String getFigureName() {
        return configurationName;
    }
	
	public static ConfigurationName fromString(String text) {
		if (text != null) {
			for (ConfigurationName d : ConfigurationName.values()) {
				if (text.toUpperCase().equals(d.configurationName)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
