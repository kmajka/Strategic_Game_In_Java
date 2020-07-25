package com.bwizard.cegame.tools;

public class Vector2DGame {
	
	private Point2DGame vector = new Point2DGame(0,0);
	
	public Vector2DGame(Point2DGame a, Point2DGame b) {
		
		vector.x = b.x - a.x;
		vector.y = b.y - a.y;
	}

	public int getX() {
		return vector.x;
	}

	public int getY() {
		return vector.y;
	}

}
