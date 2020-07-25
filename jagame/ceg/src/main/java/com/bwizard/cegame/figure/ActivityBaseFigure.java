package com.bwizard.cegame.figure;

public class ActivityBaseFigure {

	public final static String MOVE_DOWN = "MOVE_DOWN";
	public final static String MOVE_UP = "MOVE_UP";
	public final static String MOVE_LEFT = "MOVE_LEFT";
	public final static String MOVE_RIGHT = "MOVE_RIGHT";
	public final static String MOVE_UP_LEFT = "MOVE_UP_LEFT";
	public final static String MOVE_DOWN_LEFT = "MOVE_DOWN_LEFT";
	public final static String MOVE_UP_RIGHT = "MOVE_UP_RIGHT";
	public final static String MOVE_DOWN_RIGHT = "MOVE_DOWN_RIGHT";
	public final static String STAND = "STAND";
	public final static String PLACED_MANUALY = "PLACED_MANUALY";
	public final static String NONE = "NONE";
	
	private String actualActivity;
	
	public ActivityBaseFigure() {
		actualActivity = NONE;
	}

	public String getActualActivity() {
		return actualActivity;
	}

	public void setActualActivity(String actualActivity) {
		this.actualActivity = actualActivity;
	}


	

	
}
