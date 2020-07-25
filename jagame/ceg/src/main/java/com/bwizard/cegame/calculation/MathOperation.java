package com.bwizard.cegame.calculation;

import java.util.ArrayList;

import com.bwizard.cegame.tools.Point2DGame;
import com.bwizard.cegame.tools.Vector2DGame;

public class MathOperation {

	public static boolean overlapRect(int firstRectX1, int firstRectY1, int firstRectX2, int firstRectY2,
			int secondRectX1, int secondRectY1, int secondRectX2, int secondRectY2) {
		
		boolean res = false;
		
		if (firstRectX1 > firstRectX2) {
			int tmpX = firstRectX1;
			firstRectX1 = firstRectX2;
			firstRectX2 = tmpX;
		}
		
		if (firstRectY1 > firstRectY2) {
			int tmpY = firstRectY1;
			firstRectY1 = firstRectY2;
			firstRectY2 = tmpY;
		}
		
		//b.x>a.x && b.y>a.y :
		//b2.x>a1.x && b1.x>a2.x && b2.y>a1.y && b1.y>a2.y
		if (firstRectX2>secondRectX1 && secondRectX2>firstRectX1 && firstRectY2>secondRectY1 && secondRectY2>firstRectY1) {
			res = true;
		} 
		
		return res;
	}
	
	public static int calculateAngle(Vector2DGame vectorA, Vector2DGame vectorB ) {
		
		double dotProduct = (vectorA.getX() * vectorB.getX()) + (vectorA.getY() * vectorB.getY());
		double lengthA = Math.sqrt(Math.pow(vectorA.getX(), 2) + Math.pow(vectorA.getY(), 2));
		double lengthB = Math.sqrt(Math.pow(vectorB.getX(), 2) + Math.pow(vectorB.getY(), 2));
		double angle = Math.acos(dotProduct / (lengthA * lengthB));
		
		int result = (int) (angle * 180 / Math.PI);
		
		if (vectorA.getY() > vectorB.getY()) {
			return 360 - result;
		}
		return result;
	}
	
	public static ArrayList<Point2DGame> getPointsForVector(Point2DGame pointA, Point2DGame pointB, int step) {
		
		ArrayList<Point2DGame> listPoints = new ArrayList<Point2DGame>();
		Vector2DGame vectorAB = new Vector2DGame(pointA, pointB);
		
		long lengthAB = (long)Math.sqrt(Math.pow(vectorAB.getX(), 2) + Math.pow(vectorAB.getY(), 2));
		
		for(int i=0; i < lengthAB; i += step) {
			Point2DGame pointC = new Point2DGame();
			pointC.x = pointA.x + (int)((i/(double)(lengthAB)) * (pointB.x - pointA.x));
			pointC.y = pointA.y + (int)((i/(double)(lengthAB)) * (pointB.y - pointA.y));
			if (!listPoints.contains(pointC))
			{
				listPoints.add(pointC);
			}
		}
		
		//try add end point
		if (!listPoints.contains(pointB))
		{
			listPoints.add(pointB);
		}
		
		return listPoints;
	}
	
	public static Point2DGame getNextPointForVector(Point2DGame pointA, Point2DGame pointB, int step) {
		
		Vector2DGame vectorAB = new Vector2DGame(pointA, pointB);
		
		long lengthAB = (long)Math.sqrt(Math.pow(vectorAB.getX(), 2) + Math.pow(vectorAB.getY(), 2));
		
		Point2DGame pointC = new Point2DGame();
		pointC.x = pointA.x + (int)((step/(double)(lengthAB)) * (pointB.x - pointA.x));
		pointC.y = pointA.y + (int)((step/(double)(lengthAB)) * (pointB.y - pointA.y));
		
		return pointC;
	}
	
	
}
