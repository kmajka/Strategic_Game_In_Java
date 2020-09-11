package com.bwizard.cegame.device.model;

import com.bwizard.cegame.camera.CameraMapInfo;
import com.bwizard.cegame.utils.PointInfo;

/*
 * This class manages mouse 
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CursorInfo {

	private CameraMapInfo cameraMapInfo = null;
	private int startScreenSelectionX = 0; 
	private int startScreenSelectionY = 0;
	private int endScreenSelectionX = 0;
	private int endScreenSelectionY = 0;
	private int screenCursorPositionX = 0;
	private int screenCursorPositionY = 0;
	//member for checking if some components 'Canvas', 'DrawFigure' has been selected or using for 'drawing selecting Rectangle') 
	private boolean selected = false;
	//member for checking if some DrawFigure (e.g. DrawMap) has been selected 

	public CursorInfo(CameraMapInfo cameraMapInfo) {
		this.cameraMapInfo = cameraMapInfo;
	}
	/*
	 * This constructor store information about mouse
	 * @param cellWidth value of cell width
	 * @param cellHeight value of cell height
	 */
	public CursorInfo(int positionX, int positionY) {
		this.setScreenCursorPositionX(positionX);
		this.setScreenCursorPositionY(positionY);
	}
	
	public void updateStartSelectionX(int step) {
		this.startScreenSelectionX += step;
	}
	
	public void updateStartSelectionY(int step) {
		this.startScreenSelectionY += step;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public CameraMapInfo getCameraMapInfo() {
		return cameraMapInfo;
	}
	
	public int getStartScreenSelectionX() {
		return startScreenSelectionX;
	}
	
	public void setStartScreenSelectionX(int startScreenSelectionX) {
		this.startScreenSelectionX = startScreenSelectionX;
	}
	
	public int getStartScreenSelectionY() {
		return startScreenSelectionY;
	}
	
	public void setStartScreenSelectionY(int startScreenSelectionY) {
		this.startScreenSelectionY = startScreenSelectionY;
	}
	
	public int getEndScreenSelectionX() {
		return endScreenSelectionX;
	}
	
	public void setEndScreenSelectionX(int endScreenSelectionX) {
		this.endScreenSelectionX = endScreenSelectionX;
	}
	public int getEndScreenSelectionY() {
		return endScreenSelectionY;
	}
	
	public void setEndScreenSelectionY(int endScreenSelectionY) {
		this.endScreenSelectionY = endScreenSelectionY;
	}
	
	public int getStartMapSelectionX() {
		return getStartScreenSelectionX() - getCameraMapInfo().getInViewCameraX();
	}
	
	public int getStartMapSelectionY() {
		return getStartScreenSelectionY() - getCameraMapInfo().getInViewCameraY();
	}
	
	public int getEndMapSelectionX() {
		return getEndScreenSelectionX() - getCameraMapInfo().getInViewCameraX();
	}
	
	public int getEndMapSelectionY() {
		return getEndScreenSelectionY() - getCameraMapInfo().getInViewCameraY();
	}
	
	public int getMapCursorPositionX() {
		return getScreenCursorPositionX() - getCameraMapInfo().getInViewCameraX();
	}
	
	public int getMapCursorPositionY() {
		return getScreenCursorPositionY() - getCameraMapInfo().getInViewCameraY();
	}
	
	public int getScreenCursorPositionX() {
		return screenCursorPositionX;
	}
	
	public void setScreenCursorPositionX(int screenCursorPositionX) {
		this.screenCursorPositionX = screenCursorPositionX;
	}
	
	public int getScreenCursorPositionY() {
		return screenCursorPositionY;
	}
	
	public void setScreenCursorPositionY(int screenCursorPositionY) {
		this.screenCursorPositionY = screenCursorPositionY;
	}
	public PointInfo getScreenCursorPosition() {
		return new PointInfo(getScreenCursorPositionX(), getScreenCursorPositionY());
	}
	
	public boolean isCursorOverViewUser() {
		boolean res = false;
		
		if ((cameraMapInfo.getViewLayout().getTopBorderLayout() < getScreenCursorPositionY()) && (cameraMapInfo.getViewLayout().getViewHeight() + cameraMapInfo.getViewLayout().getTopBorderLayout() > getScreenCursorPositionY())
				&& (cameraMapInfo.getViewLayout().getLeftBorderLayout() < getScreenCursorPositionX()) && (cameraMapInfo.getViewLayout().getViewWidth() + cameraMapInfo.getViewLayout().getLeftBorderLayout() > getScreenCursorPositionY()))	{
			res = true;
		}
		return res;
	}

	
}
