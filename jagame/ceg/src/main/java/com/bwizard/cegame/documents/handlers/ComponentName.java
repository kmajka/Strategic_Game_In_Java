package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about components name
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum ComponentName {

	BUTTON("BUTTON"),
	CHECKBOX("CHECKBOX"),
	LIST("LIST"),
	MAP("MAP"),
	PANEL("PANEL"),
	PICTURE("PICTURE"),
	LABEL("LABEL"),
	TEXTEDIT("TEXTEDIT"),
	DIALOG("DIALOG"),
	VIDEO("VIDEO"),
	FRAMEPANEL("FRAMEPANEL");
	
	private final String componentName;
	
	private ComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	public String getComponentName() {
        return componentName;
    }
	
	public static ComponentName fromString(String text) {
		if (text != null) {
			for (ComponentName d : ComponentName.values()) {
				if (text.toUpperCase().equals(d.componentName)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
