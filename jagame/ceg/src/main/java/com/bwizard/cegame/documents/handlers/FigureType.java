package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about Figure Type of component it controls which Handler will cooperate with Events
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum FigureType {

	MAIN_MENU("MAIN_MENU"),
	
	GAME_VIEW("GAME_VIEW"),
	GAME_MENU("GAME_MENU"),
	
	EDITOR_VIEW("EDITOR_VIEW"),
	EDITOR_MENU("EDITOR_MENU"),
	
	CONFIGURATOR_MENU("CONFIGURATOR_MENU"),

	CHARACTER("CHARACTER"),
	BUILDING("BUILDING"),
	NATURE("NATURE"),
	NONE("NONE");
	
	private final String figureType;
	
	private FigureType(String figureType) {
		this.figureType = figureType;
	}
	
	public String getFigureType() {
        return figureType;
    }
	
	public static FigureType fromString(String text) {
		if (text != null) {
			for (FigureType d : FigureType.values()) {
				if (text.toUpperCase().equals(d.figureType)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
