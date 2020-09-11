package com.bwizard.wsgame.world.figure;

import com.bwizard.wsgame.parameters.GameGlobals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.thread.ThreadStatus;
import com.bwizard.cegame.tools.DrawManager;
import com.bwizard.cegame.utils.ImageUtil;

public abstract class GameFigure extends BaseFigure {
	
	public GameFigure() {
		canFocus(true);
	}
	
	public GameFigure(int x, int y, StateInfoGame stateInfoGame) {
		super(x , y, stateInfoGame);
		canFocus(true);
	}
	
	@Override
	public void paintFigure(Graphics g) {
		
		if(stateInfoGame.isThreadRun()) {
			updateFrame(stateInfoGame.getTimeInGame());
		}
		
		if(isSelected()) {
		
			//if object was not (area) selecting by mouse
			if (!stateInfoGame.getCursorInfo().isSelected()) {
				setSelected(true);
			//if object was (area) selecting by mouse and mouse was released
			}
		}
			
		DrawManager.drawImageInGameView(g, getImage(), 
				getActualMapPosition().x, getActualMapPosition().y, stateInfoGame, isSelected(), isPlaceFree());
		
		if (isDisplayInfo()) {
			int x1_1 = getActualViewPositionX();
			int y1_1 = getActualViewPositionY();
			
			int x2_1 = getEndViewPositionX();
			int y2_1 = getEndViewPositionY();
			//DrawManager.drawLine(g, x1_1, y1_1, x2_1, y2_1, Color.BLACK);
			
			//DrawManager.drawLine(g, x1_1, y1_1, x1_1 + 100, y1_1, Color.BLACK);
			
			DrawManager.drawArc(g, x1_1 - 30, y1_1 - 30, 60, 60, 0, getAngle(), Color.YELLOW);
			
			DrawManager.drawString(g, x1_1, y1_1, Integer.toString(getAngle()) + '\u00B0', Color.YELLOW);
			
			DrawManager.drawLine(g, x1_1, y1_1, x2_1, y2_1, Color.YELLOW);
			DrawManager.drawArc(g, x2_1, y2_1, 12, 12, 0, 360, Color.YELLOW);
			
			
//			for(int i = 0; i <listPoints.size(); i+=3) {
//				Point2DGame ff = listPoints.get(i);
//				x1_1 = ff.x;
//				y1_1 = ff.y;
//				DrawManager.drawArc(g, x1_1, y1_1, 12, 12, 0, 360, Color.YELLOW);
//			}
			
			
		}
		
	}
	
	@Override
	protected BufferedImage getImageSprite(String file) {
		return ImageUtil.loadImage(GameGlobals.IMAGE_PATH + file);
	}

}
