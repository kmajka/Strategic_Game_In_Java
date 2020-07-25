package com.bwizard.cegame.camera;

import com.bwizard.cegame.device.handlers.DirectionMove;
import com.bwizard.cegame.utils.PointInfo;

/*
 * This class stores information about the camera in game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CameraInfo {

	//center camera position in game
	private int centerPositionX;
	private int centerPositionY;
	//camera position in game
	private int cameraX = 0; 
	private int cameraY = 0;
		
	//Automatically moves camera e.g when cursor is moving to the edge of the window
	protected AutoCamera autoCamera = null; 
	
	public CameraInfo() {
		autoCamera = new AutoCamera();
	}
	
	public void updateCamera(PointInfo pointInfo) {
		updateCameraX(getEdgeRestrictionX(pointInfo.getX()));
		updateCameraY(getEdgeRestrictionY(pointInfo.getY()));
	}
	
	public void updateCameraX(int cameraX) {
		this.cameraX = cameraX;
	}

	public void updateCameraY(int cameraY) {
		this.cameraY = cameraY;
	}
	
	public void setCameraX(int cameraX) {
		this.cameraX += cameraX;
	}

	public void setCameraY(int cameraY) {
		this.cameraY += cameraY;
	}
	
	public void AutoCameraInvoke() {
		this.cameraX += autoCamera.getAutoCameraStepX();
		this.cameraY += autoCamera.getAutoCameraStepY();
	}
	
	/*
	 * This method check if position x doesn't exceed value for camera - camera cannot be position outside the map
	 * @param x The value will be check if value for the X axis is correct
	 * @return x return correctly the position which doesn't exceed value for camera. (if exceed x will be set to maximum for camera)
	 */
	protected int getEdgeRestrictionX(int x) {
		return x;
	}
	
	/*
	 * This method check if position y doesn't exceed value for camera - camera cannot be position outside the map
	 * @param y The value will be check if value for the X axis is correct
	 * @return y return correctly the position which doesn't exceed value for camera. (if exceed y will be set to maximum for camera)
	 */
	protected int getEdgeRestrictionY(int y) {
		return y;
	}
	
	public void clearAutoCamera() {
		clearAutoCameraX();
		clearAutoCameraY();
	}
	
	//to omit use Math.abs function 
	public int getRealCameraX() {
		return -cameraX;
	}
	
	//to omit use Math.abs function 
	public int getRealCameraY() {
		return -cameraY;
	}
	
	public int getCameraX() {
		return cameraX;
	}
	
	public int getCameraY() {
		return cameraY;
	}
	
	public int getInViewCameraX() {
		return cameraX + centerPositionX;
	}
	
	public int getInViewCameraY() {
		return cameraY + centerPositionY;
	}
	
	public void clearAutoCameraX() {
		this.autoCamera.setAutoCameraStepX(0);
		this.autoCamera.setDirectionMoveX(null);	
	}
	
	public void clearAutoCameraY() {
		this.autoCamera.setAutoCameraStepY(0);
		this.autoCamera.setDirectionMoveY(null);
	}
	
	public void setAutoCameraX(int step, DirectionMove directionMove) {
		this.autoCamera.setAutoCameraStepX(step);
		this.autoCamera.setDirectionMoveX(directionMove);
	}
	
	public void setAutoCameraY(int step, DirectionMove directionMove) {
		this.autoCamera.setAutoCameraStepY(step);
		this.autoCamera.setDirectionMoveY(directionMove);
	}

	public int getCenterPositionX() {
		return centerPositionX;
	}

	public void setCenterPositionX(int centerPositionX) {
		this.centerPositionX = centerPositionX;
	}

	public int getCenterPositionY() {
		return centerPositionY;
	}

	public void setCenterPositionY(int centerPositionY) {
		this.centerPositionY = centerPositionY;
	}

}
