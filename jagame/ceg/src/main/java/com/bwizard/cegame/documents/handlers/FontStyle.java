package com.bwizard.cegame.documents.handlers;

import java.awt.Font;

/*
 * This enumerator provides information about Font Style
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum FontStyle {

	BOLD("BOLD"),
	PLAIN("PLAIN"),
	ITALIC("ITALIC");
	
	private final String fontStyle;
	
	private FontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}
	
	public String getFontStyle() {
        return fontStyle;
    }
	
	public static int fromString(String text) {
		
		int res = 0;
		String[] str = text.split(" | ");
		
		for(String s : str ) {
			
			if (text != null) {
				if (s.toUpperCase().equals("PLAIN")) {
					res |= Font.PLAIN;
				} else if (s.toUpperCase().equals("BOLD")) {
					res |= Font.BOLD;
				} else if (s.toUpperCase().equals("ITALIC")) {
					res |= Font.ITALIC;
				}
		    }
		}
	    return res;
    }
	
}
