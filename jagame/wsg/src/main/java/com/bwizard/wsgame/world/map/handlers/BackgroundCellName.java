package com.bwizard.wsgame.world.map.handlers;

import java.util.HashMap;
import java.util.Map;

/*
 * This enumerator provides information about terrain name placed into background map
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum BackgroundCellName {
	
	GRASSGREEN_0_0_BACKGROUND_1(0),
	JESIEN(1),
	ZIMA(2),
	WIOSNA(3),
	NOC(4),
	PUSTYNIA(5),
	ZIEMIA(6),
	PAUZA(7),
	SEABLUE_0_0_BACKGROUND_1(8), 
	SEABLUE_2_2_BACKGROUND_1(9), 
	GRASSBRIGHT_0_0_BACKGROUND_1(10),
	SEABLUE_4_1_BACKGROUND_1(12),
	SEABLUE_3_4_BACKGROUND_1(13), 
	SEABLUE_1_1_BACKGROUND_1(14), 
	SEABLUE_4_4_BACKGROUND_1(15), 
	SEABLUE_1_2_BACKGROUND_1(16), 
	SEABLUE_2_3_BACKGROUND_1(19), 
	SEABLUE_3_3_BACKGROUND_1(20), 
	SEABLUE_4_2_BACKGROUND_1(22),
	GRASSDARK_0_0_BACKGROUND_1(23);
	
	private int backgroundCellName;
	
    private static Map<Integer, BackgroundCellName> map = new HashMap<Integer, BackgroundCellName>();

    static {
        for (BackgroundCellName cell : BackgroundCellName.values()) {
            map.put(cell.backgroundCellName, cell);
        }
    }
    
	private BackgroundCellName(int backgroundCellName) {
		this.backgroundCellName = backgroundCellName;
	}
	
    public static BackgroundCellName valueOf(int backgroundCellNo) {
        return map.get(backgroundCellNo);
    }
    
    public static int getValue(BackgroundCellName cell) {

    	for(BackgroundCellName i : map.values()) {
    		if (i.equals(cell)) {
    			return i.backgroundCellName;
    		}
    	}
    	return -1;
    }

}
