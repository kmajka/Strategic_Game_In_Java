package com.bwizard.cegame.tools;

public class ArgumentValidator {

	public static void checkForNull(String name, Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Argument " + name + "shouldn't be null.");
		}
	}
	
	public static void checkForEmpty(String name, Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Argument " + name + "shouldn't be empty.");
		}
		
		if (object.toString().trim().equals("")) {
			throw new IllegalArgumentException("Argument " + name + "shouldn't be empty.");
		}
	}
}
