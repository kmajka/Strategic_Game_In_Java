package com.bwizard.cegame.utils;

import java.awt.Color;

public class ColorUtil {

	//e.g. "RGB(0,255,0)"
	public static Color getValue(Object obj) {
		if (obj instanceof String) {
			
			String str = ((String)obj).replaceAll("RGB\\(", "");
			str = str.replaceAll("\\)", "");
			String[] tmp = str.split(",");
			
			int red = Integer.valueOf(tmp[0]);
			int green = Integer.valueOf(tmp[1]);
			int blue = Integer.valueOf(tmp[2]);
			
			return new Color(red, green, blue);
		}
		
		return Color.GRAY;
	}
}
