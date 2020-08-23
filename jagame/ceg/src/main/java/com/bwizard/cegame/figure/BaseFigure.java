package com.bwizard.cegame.figure;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bwizard.cegame.calculation.MathOperation;
import com.bwizard.cegame.frame.BaseFrame;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.tools.Point2DGame;

public abstract class BaseFigure extends BaseFrame {

	private boolean recalculatePointsForVector = false;
	protected ArrayList<Point2DGame> listPoints = new ArrayList<Point2DGame>();
	
	//when object has been selected  
	private boolean selected;
	//when object is selecting
	private boolean selecting;
	//object can be move
	private boolean canMove;
	//object can be selected/focused
	private boolean canFocus;
	private Point2DGame startPosition;
	private Point2DGame actualPosition;
	private Point2DGame endPosition;
	protected StateInfoGame stateInfoGame;
	private boolean existOnMap;
	private int positionX;
	private int positionY;
	
	//figure using in editor
	private boolean placedManualy;
	//figure can be put on free place
	private boolean placeFree;
	protected List<Point2DGame> currentRoad = new ArrayList<Point2DGame>();
	
	private String name = null;
	private int angle;
	private boolean displayInfo;

	public BaseFigure() {
		startPosition = new Point2DGame(0,0);
		actualPosition = new Point2DGame(0,0);
		endPosition = new Point2DGame(0,0);
		selected = false;
		canMove = false;
		canFocus = false;
		selecting = false;
		placeFree = true;
	}
	
	public BaseFigure(int x, int y, StateInfoGame stateInfoGame) {
		this.stateInfoGame = stateInfoGame;
		this.actualPosition = setCorrectlyPosition(x, y);
		this.startPosition = setCorrectlyPosition(x, y);
		this.endPosition = setCorrectlyPosition(x, y);
		selected = false;
		canMove = false;
		canFocus = false;
		selecting = false;
		placeFree = true;
	}
	
	private Point2DGame setCorrectlyPosition(int positionX, int positionY) {
		return new Point2DGame(positionX , positionY);
	}
	
	public boolean isSelected() {
		
		if (!selected && stateInfoGame.getCursorInfo().isSelected() && canMove) {

			int firstRectX1 = stateInfoGame.getCursorInfo().getStartMapSelectionX();//a2.x
			int firstRectY1 = stateInfoGame.getCursorInfo().getStartMapSelectionY();//a2.y
			int firstRectX2 = stateInfoGame.getCursorInfo().getEndMapSelectionX();//b2.x
			int firstRectY2 = stateInfoGame.getCursorInfo().getEndMapSelectionY();//b2.y
			
			int secondRectX1 = getActualMapPosition().x; //a1.x
			int secondRectY1 = getActualMapPosition().y;//a1.y
			int secondRectX2 = getActualMapPosition().x + getImage().getWidth();//b1.x
			int secondRectY2 = getActualMapPosition().y + getImage().getHeight();//b1.y
			
			if (MathOperation.overlapRect(firstRectX1, firstRectY1, firstRectX2, firstRectY2, secondRectX1, secondRectY1, secondRectX2, secondRectY2)) {
				selecting = true;
			} else {
				selecting = false;
			}
			
		}
		return selected || selecting;
	}

	@Override
	public void clear() {
		frameManager.clearAllFrames();
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		//clear selecting
		setSelecting(false);
	}
	
	public int getEndViewPositionX() {
		return endPosition.x + stateInfoGame.getCameraMapInfo().getInViewCameraX();
	}
	
	public int getEndViewPositionY() {
		return endPosition.y + stateInfoGame.getCameraMapInfo().getInViewCameraY();
	}
	
	//position on the view
	public int getActualViewPositionX() {
		return actualPosition.x + stateInfoGame.getCameraMapInfo().getInViewCameraX();
	}
	
	//position on the view
	public int getActualViewPositionY() {
		return actualPosition.y + stateInfoGame.getCameraMapInfo().getInViewCameraY();
	}
	
	//position on the map
	public Point2DGame getActualMapPosition() {
		return actualPosition;
	}
	
	//position on the map
	public void setActualMapPosition(int x, int y) {
		actualPosition.x = x;
		actualPosition.y = y;
	}
	
	//calculate position on the map
	public void calculateActualMapPosition(int x, int y) {
		actualPosition.x += x;
		actualPosition.y += y;
	}
	
	//Calculate position on the map
	public void calculateActualMapPositionX(int x) {
		actualPosition.x += x;
	}
	
	//calcualte position on the map
	public void calculateActualMapPositionY(int y) {
		actualPosition.y += y;
	}

	public void setAllMapPositions(int x, int y) {
		this.actualPosition = new Point2DGame(x, y);
		this.startPosition = new Point2DGame(x, y);
		this.endPosition = new Point2DGame(x, y);
	}
	
	public Point2DGame getStartMapPosition() {
		return startPosition;
	}

	public void setStartMapPosition(Point2DGame startPosition) {
		
		this.startPosition = startPosition;
	}
	
	public Point2DGame getEndMapPosition() {
		return endPosition;
	}

	public void setEndMapPosition(Point2DGame endPosition) {
		this.endPosition = endPosition;
	}
	
	public void clearEndMapPositionX() {
		this.endPosition.x = this.actualPosition.x;
	}
	
	public void clearEndMapPositionY() {
		this.endPosition.y = this.actualPosition.y;
	}
	
	public abstract void paintFigure(Graphics g);

	public boolean canMove() {
		return canMove;
	}

	public void canMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	public boolean canFocus() {
		return canFocus;
	}

	public void canFocus(boolean canFocus) {
		this.canFocus = canFocus;
	}
	
	public boolean isSelecting() {
		return selecting;
	}

	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
	}

	public boolean isInUserView() {
		
		int firstRectX1 = Math.abs(stateInfoGame.getCameraMapInfo().getCameraX());//a2.x
		int firstRectY1 = Math.abs(stateInfoGame.getCameraMapInfo().getCameraY());//a2.y
		int firstRectX2 = Math.abs(stateInfoGame.getCameraMapInfo().getCameraX()) + stateInfoGame.getViewLayout().getViewWidth();//b2.x
		int firstRectY2 = Math.abs(stateInfoGame.getCameraMapInfo().getCameraY()) + stateInfoGame.getViewLayout().getViewHeight();//b2.y
		
		int secondRectX1 = getActualMapPosition().x; //a1.x
		int secondRectY1 = getActualMapPosition().y;//a1.y
		int secondRectX2 = getActualMapPosition().x + getImage().getWidth();//b1.x
		int secondRectY2 = getActualMapPosition().y + getImage().getHeight();//b1.y
		
		return MathOperation.overlapRect(firstRectX1, firstRectY1, firstRectX2, firstRectY2, secondRectX1, secondRectY1, secondRectX2, secondRectY2);
		
	}
	
	public int getVerticalMapPosition() {
		return (int)getActualMapPosition().y + getHeight();
	}
		
	public int getHorizontalMapPosition() {
		return (int)getActualMapPosition().x + getWidth();
	}

	public void setMapPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setMapPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public int getMapPositionX() {
		return this.positionX;
	}

	public int getMapPositionY() {
		return this.positionY;
	}
	
	/**
	 * This method set position of all figures into map without depends on left-top game layout.
	 * @return Nothing.
	 */
	public void intitializePositionIntoMap() {
		this.actualPosition = setCorrectlyPosition(positionX, positionY);
		//this.startPosition = setCorrectlyPosition(positionX, positionY);
		this.endPosition = setCorrectlyPosition(positionX, positionY);
	}
	
	public void setStateInfoGame(StateInfoGame stateInfoGame) {
		this.stateInfoGame = stateInfoGame;
	}

	public boolean isExistOnComponentMap() {
		return existOnMap;
	}
	
	public void DrawOnComponentMap(Graphics g, int x, int y, float scaleMapWidth, float scaleMapHeight) {
	}
	
	public void updateEditor() {
	}	
	
	public void setPlaceFree(List<BaseFigure> list) {
		
		synchronized (list) {
			Iterator<BaseFigure> iterator = list.iterator();
			while(iterator.hasNext()) {
				BaseFigure baseFigure = iterator.next();
				
				if (!baseFigure.isSelected()) {
					int firstRectX1 = actualPosition.x;//a2.x
					int firstRectY1 = actualPosition.y;//a2.y
					int firstRectX2 = actualPosition.x + getImage().getWidth();//b2.x
					int firstRectY2 = actualPosition.y + getImage().getHeight();//b2.y
					
					int secondRectX1 = baseFigure.getActualMapPosition().x; //a1.x
					int secondRectY1 = baseFigure.getActualMapPosition().y;//a1.y
					int secondRectX2 = baseFigure.getActualMapPosition().x + baseFigure.getImage().getWidth();//b1.x
					int secondRectY2 = baseFigure.getActualMapPosition().y + baseFigure.getImage().getHeight();//b1.y
					
					if (MathOperation.overlapRect(firstRectX1, firstRectY1, firstRectX2, firstRectY2, secondRectX1, secondRectY1, secondRectX2, secondRectY2)) {
						placeFree = false;
						break;
					} else {
						placeFree = true;
					}
				} else {
					placeFree = true;
				}
			}
		}
	}

	public boolean isPlaceFree() {
		return placeFree;
	}

	public void setPlaceFree(boolean placeFree) {
		this.placeFree = placeFree;
	}

	public boolean isPlacedManualy() {
		return placedManualy;
	}

	public void setPlacedManualy(boolean placedManualy) {
		this.placedManualy = placedManualy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public boolean isDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(boolean displayInfo) {
		this.displayInfo = displayInfo;
	}

	public boolean isRecalculatePointsForVector() {
		return recalculatePointsForVector;
	}

	public void setRecalculatePointsForVector(boolean recalculatePointsForVector) {
		this.recalculatePointsForVector = recalculatePointsForVector;
	}

}
