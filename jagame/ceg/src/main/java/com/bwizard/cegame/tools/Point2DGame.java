package com.bwizard.cegame.tools;

public class Point2DGame extends Object {
	
	public int x = 0;
	public int y = 0;
	
	public Point2DGame() {
	}
	
	public Point2DGame(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object obj)
	{
		boolean res = false;
		
		if (obj instanceof Point2DGame) {
			res = this.x == ((Point2DGame)obj).x && this.y == ((Point2DGame)obj).y;
		}
		return res;
		
	}
	
}
