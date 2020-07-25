package com.bwizard.cegame.controls.components;

import java.awt.Color;
import java.awt.Graphics;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controller.WorldObjectManager;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.tools.DrawManager;
import com.bwizard.cegame.utils.PointInfo;

public class DrawMap extends BaseDrawFigure {

	private BaseWorldGame baseWorldGame = null;
	private float scaleMapWidth;
	private float scaleMapHeight;

	public DrawMap() {
		super();
	}
	
	public DrawMap(BaseWorldGame baseWorldGame, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.baseWorldGame = baseWorldGame;
		this.cursorInfo = baseWorldGame.getCursorInfo();
		this.canPressed = true;
		this.autoUnselect = true;
		
		this.scaleMapWidth = (float)width / (float)baseWorldGame.getStateInfoManager().getWorldMapInfo().getWidthMap();
		this.scaleMapHeight = (float)height / (float)baseWorldGame.getStateInfoManager().getWorldMapInfo().getHeightMap();
	}
	
	/**
	 * This method returns the position of the component maps which corresponds to the position on the Map games
	 * @param screenCursorPositionX This variable provides information about mouse position on axis X.
	 * @param screenCursorPositionY This variable provides information about mouse position on axis Y.
	 * @return point User Map into game.
	 */
	public PointInfo getCursorPositionOnViewMap(int screenCursorPositionX, int screenCursorPositionY) {
		
		//define point the middle of the rectangle which corresponds to the position on the Map games
		int tmpX = (int)((x - screenCursorPositionX + (getRectWidth() / 2)) / scaleMapWidth);
		int tmpY = (int)((y - screenCursorPositionY + (getRectHeight() / 2)) / scaleMapHeight);
		
		if (tmpX > 0) {
			tmpX = 0;
		}
		
		if (tmpY > 0) {
			tmpY = 0;
		}
			
		if ((screenCursorPositionX - x + (getRectWidth()/2)) > width) {
			tmpX = (int)((width - getRectWidth()) / -scaleMapWidth);
		}
		
		if ((screenCursorPositionY - y + (getRectHeight()/2)) > height) {
			tmpY = (int)((height - getRectHeight()) / -scaleMapHeight);
		}
		
		return new PointInfo(tmpX,tmpY);
	}
	
	@Override
	public void paint(Graphics g) {
		
		if(visible) {
			
			g.setColor(getMainBackgroundColor());

			super.paint(g);
			
			if (!isTransparent()) {
				DrawManager.fillRect(g, x, y, width, height);
			}
			
			DrawManager.drawImage(g, getImage(), x, y, width, height);
			
			WorldObjectManager v = baseWorldGame.getWorldObjectManager();
			for(BaseFigure baseFigure : v.getAllObjects()) {
				if (baseFigure.isExistOnComponentMap()) {
					baseFigure.DrawOnComponentMap(g, x, y, scaleMapWidth, scaleMapHeight);
				}
			}
			
			int rectPositionX = (int)Math.abs(baseWorldGame.getStateInfoManager().getCameraMapInfo().getCameraX() * this.scaleMapWidth);
			int rectPositionY = (int)Math.abs(baseWorldGame.getStateInfoManager().getCameraMapInfo().getCameraY() * this.scaleMapHeight);			
			
			//draw rectangle into the map
			DrawManager.drawRect(g, x + rectPositionX, y + rectPositionY, getRectWidth(), getRectHeight(), Color.BLACK, 2);
	
		}
	}
	
	private int getRectWidth() {
		return (int)(baseWorldGame.getStateInfoManager().getViewLayout().getViewWidth() * this.scaleMapWidth);
	}
	
	private int getRectHeight() {
		return (int)(baseWorldGame.getStateInfoManager().getViewLayout().getViewHeight() * this.scaleMapHeight);
	}
	
	public float getScaleMapWidth() {
		return scaleMapWidth;
	}
	public float getScaleMapHeight() {
		return scaleMapHeight;
	}
	
	@Override
	public void setWidth(int width) {
		this.width = width;
		this.scaleMapWidth = (float)width / (float)baseWorldGame.getStateInfoManager().getWorldMapInfo().getWidthMap();
	}
	
	@Override
	public void setHeight(int height) {
		this.height = height;
		this.scaleMapHeight = (float)height / (float)baseWorldGame.getStateInfoManager().getWorldMapInfo().getHeightMap();
	}
	
	public BaseWorldGame getBaseWorldGame() {
		return baseWorldGame;
	}

	public void setBaseWorldGame(BaseWorldGame baseWorldGame) {
		this.baseWorldGame = baseWorldGame;
	}

}
