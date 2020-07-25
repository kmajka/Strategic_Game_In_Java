package com.bwizard.wsgame.documents;

import com.bwizard.wsgame.parameters.GameGlobals;

public class PathGameManager {
	
	public static String getPath(String layoutName) {
		
		String res = GameGlobals.LAYOUT_DATA;
		String fileName;
		
		//remove extension -> Game_Main.xml
		int lastIndex = layoutName.lastIndexOf('.');
		if (lastIndex > 0) {
			fileName = layoutName.substring(0, lastIndex);
		} else {
			fileName = layoutName;
		}
		
		//Game_Main
		lastIndex = fileName.lastIndexOf("_");
		if (lastIndex > 0) {
			if ("Menu".equals(fileName.substring(lastIndex + 1))) {
				res = GameGlobals.LAYOUT_MAIN_MENU_DATA;
			} else if ("Dialog".equals(fileName.substring(lastIndex + 1))) {
				res = GameGlobals.LAYOUT_DIALOG_DATA;
			} else if ("Panel".equals(fileName.substring(lastIndex + 1))) {
				res = GameGlobals.LAYOUT_PANEL_DATA;
			}
		}
		return res + layoutName;
	}
	
}
