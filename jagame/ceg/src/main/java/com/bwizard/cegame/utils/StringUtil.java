package com.bwizard.cegame.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	public static final String EMPTY = "";
	
	public static boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			String str = (String)obj;
			return str.isEmpty();
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object obj) {
		if (obj instanceof String) {
			String str = (String)obj;
			return !str.isEmpty();
		}
		return false;
	}
	
	public static boolean isNullOrEmpty(Object obj) {
		
		if (obj == null) {
			return true;
		}
			
		if (obj instanceof String) {
			String str = (String)obj;
			return str.isEmpty();
		} 
		return false;
	}
	
	public static String[] split(String str, String reg) {
		List<String> list = new ArrayList<String>();
		if (isNotEmpty(str)) {
			int i = -1;
			String tmp = "";
			
			do {
				if ((i = str.indexOf(reg)) > -1) {
					tmp = str.substring(0, i);
					str = str.substring(i + 1);
				} else {
					tmp = str;
				}
				

					list.add(tmp);

			} while (i > -1);
		}
		
		return list.toArray(new String[list.size()]);
	}
}
