package com.bwizard.cegame.camera;

import com.bwizard.cegame.device.handlers.DirectionMove;
import com.bwizard.cegame.view.layout.interfaces.IViewLayout;
import com.bwizard.cegame.world.map.WorldMapInfo;

/*
 * This class stores information about the camera in the map (e.g camera can not go beyond the map )
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CameraMapInfo extends CameraInfo  {
	
	private WorldMapInfo mapInfo = null;
	private IViewLayout viewLayout = null;

	public IViewLayout getViewLayout() {
		return viewLayout;
	}

	public CameraMapInfo() {
	}
	
	public WorldMapInfo getMapInfo() {
		return mapInfo;
	}

	public void setMapInfo(WorldMapInfo mapInfo) {
		this.mapInfo = mapInfo;
	}
	
	public void setViewLayout(IViewLayout viewLayout) {
		this.viewLayout = viewLayout;
	}

	/*
	 * This method sets position of camera in axis X 
	 * @param cameraX The value describes step moving for axis X
	 * @param actionMove The value describes in which direction camera is moving
	 */
	public void setCameraX(int cameraX, DirectionMove directionMove) {
		setCameraX(cameraX, directionMove, true);
	}
	
	/*
	 * This method sets position of camera in axis X 
	 * @param cameraX The value describes step moving for axis X
	 * @param actionMove The value describes in which direction camera is moving
	 * @param stopAutoMoveCamera The value store information if auto-camera should be turn off/on - when in the same time the cursor is behind the map and user press the key auto-camera is turn off
	 * @return step that is move the camera (e.g. use for correctly selecting object when user uses key to move the map)
	 */
	public int setCameraX(int stepCameraX, DirectionMove directionMove, boolean stopAutoMoveCamera) {
		
		int resultStep = 0;
		
		if(stopAutoMoveCamera) {
			clearAutoCamera();
		}
			
		if (directionMove == DirectionMove.MOVE_LEFT) {
			
			if ((super.getCameraX() + stepCameraX) >= 0) {
				stepCameraX = -super.getCameraX();
			} 
			
			resultStep = stepCameraX;
			
			super.setCameraX(resultStep);
			
		} else if (directionMove == DirectionMove.MOVE_RIGHT) {
		
			//for isometric map we cannot display all titles, edge titles will be not visible
			int calcWidthMap = super.getRealCameraX() + viewLayout.getViewWidth();
			
			if((calcWidthMap + stepCameraX) > mapInfo.getWidthMap()) {
				stepCameraX = mapInfo.getWidthMap() - calcWidthMap;
			}
			
			resultStep = -stepCameraX;
			
			super.setCameraX(resultStep);
		
		}
		
		return resultStep;
		
	}

	/*
	 * This method sets position of camera in axis Y 
	 * @param cameraX The value describes step moving for axis Y
	 * @param actionMove The value describes in which direction camera is moving
	 */
	public void setCameraY(int cameraY, DirectionMove directionMove) {
		setCameraY(cameraY, directionMove, true);
	}
	
	/*
	 * This method sets position of camera in axis Y 
	 * @param cameraY The value describes step moving for axis Y
	 * @param actionMove The value describes in which direction camera is moving
	 * @param stopAutoMoveCamera The value store information if auto-camera should be turn off/on - when in the same time the cursor is behind the map and user press the key auto-camera is turn off
	 * @return step that is move the camera (e.g. use for correctly selecting object when user uses key to move the map)
	 */
	public int setCameraY(int stepCameraY, DirectionMove directionMove, boolean stopAutoMoveCamera) {
		
		int resultStep = 0;
		
		if(stopAutoMoveCamera) {
			clearAutoCamera();
		}
		
		if (directionMove == DirectionMove.MOVE_DOWN) {
			
			int calcHeightMap = super.getRealCameraY() + viewLayout.getViewHeight();
			
			if((calcHeightMap + stepCameraY) > mapInfo.getHeightMap()) {
				stepCameraY = mapInfo.getHeightMap() - calcHeightMap;
			}
			
			resultStep = -stepCameraY;
			
			super.setCameraY(resultStep);
			
		} else if (directionMove == DirectionMove.MOVE_UP) {
			
			if ((super.getCameraY() + stepCameraY) >= 0) {
				stepCameraY = -super.getCameraY();
			} 
			
			resultStep = stepCameraY;
			
			super.setCameraY(resultStep);
			
		}
		
		return resultStep;
			
	}
	
	@Override
	public void AutoCameraInvoke() {
		setCameraX(autoCamera.getAutoCameraStepX(), autoCamera.getDirectionMoveX(), false);
		setCameraY(autoCamera.getAutoCameraStepY(), autoCamera.getDirectionMoveY(), false);
	}
	
	@Override
	protected int getEdgeRestrictionX(int x) {
		if (x <= (viewLayout.getViewWidth() - mapInfo.getWidthMap())) {
			return viewLayout.getViewWidth() - mapInfo.getWidthMap();
		}
			
		return x;
	}
	
	@Override
	protected int getEdgeRestrictionY(int y) {
		if (y <= (viewLayout.getViewHeight() - mapInfo.getHeightMap())) {
			return viewLayout.getViewHeight() - mapInfo.getHeightMap();
		}
			
		return y;
	}
	
	
}
