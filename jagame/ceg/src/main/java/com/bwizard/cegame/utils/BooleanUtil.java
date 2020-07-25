package com.bwizard.cegame.utils;

public class BooleanUtil {
	public static final String NO = "N";
	public static final String YES = "Y";
	
	public static boolean getValue(Object obj) {
		if (obj instanceof String) {
			return "Y".equals(obj);
		}
		return false;
	}
}
