package com.bwizard.cegame.camera;

import com.bwizard.cegame.device.handlers.DirectionMove;

/*
 * This class stores information about controlling the camera by the mouse
 * @author Krzysztof Majka
 * @version 1.0
 */
public class AutoCamera {
	
	private int autoCameraStepX = 0;
	private int autoCameraStepY = 0;
	private DirectionMove directionMoveX = null;
	private DirectionMove directionMoveY = null;
	
	public AutoCamera() {
		this.setAutoCameraStepX(0);
		this.setAutoCameraStepY(0);
		this.setDirectionMoveX(null);
		this.setDirectionMoveY(null);
	}
	
	public void AutoCameraX(int step, DirectionMove directionMove) {
		this.setAutoCameraStepX(step);
		this.setDirectionMoveX(directionMove);
	}

	public void AutoCameraY(int step, DirectionMove directionMove) {
		this.setAutoCameraStepY(step);
		this.setDirectionMoveY(directionMove);
	}
	
	public int getAutoCameraStepX() {
		return autoCameraStepX;
	}

	public void setAutoCameraStepX(int autoCamera) {
		this.autoCameraStepX = autoCamera;
	}

	public int getAutoCameraStepY() {
		return autoCameraStepY;
	}

	public void setAutoCameraStepY(int autoCamera) {
		this.autoCameraStepY = autoCamera;
	}
	
	public DirectionMove getDirectionMoveX() {
		return directionMoveX;
	}
	
	public DirectionMove getDirectionMoveY() {
		return directionMoveY;
	}

	public void setDirectionMoveX(DirectionMove directionMove) {
		this.directionMoveX = directionMove;
	}
	
	public void setDirectionMoveY(DirectionMove directionMove) {
		this.directionMoveY = directionMove;
	}
}
