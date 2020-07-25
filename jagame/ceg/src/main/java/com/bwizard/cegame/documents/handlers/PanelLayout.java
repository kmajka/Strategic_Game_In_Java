package com.bwizard.cegame.documents.handlers;

/*
 * This enumerator provides information about dock layout
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum PanelLayout {

	PANEL_TOP("PANEL_TOP"),
	PANEL_BOTTOM("PANEL_BOTTOM"),
	PANEL_LEFT("PANEL_LEFT"),
	PANEL_RIGHT("PANEL_RIGHT");
	
	private final String panelLayout;
	
	private PanelLayout(String panelLayout) {
		this.panelLayout = panelLayout;
	}
	
	public String getPanelLayout() {
        return panelLayout;
    }
	
	public static PanelLayout fromString(String text) {
		if (text != null) {
			for (PanelLayout d : PanelLayout.values()) {
				if (text.toUpperCase().equals(d.panelLayout)) {
					return d;
				}
			}
	    }
	    return null;
    }
	
}
