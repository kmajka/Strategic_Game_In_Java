package com.bwizard.wsgame.world.figure.characters;

import com.bwizard.wsgame.world.figure.GameFigure;
import com.bwizard.cegame.calculation.MathOperation;
import com.bwizard.cegame.figure.ActivityBaseFigure;
import com.bwizard.cegame.figure.interfaces.IFigureMove;
import com.bwizard.cegame.state.StateInfoManager;
import com.bwizard.cegame.tools.Point2DGame;
import com.bwizard.cegame.tools.Vector2DGame;

public class SimpleKnight extends GameFigure implements IFigureMove {
	
	final int step = 3;
	
	public SimpleKnight() {
	}
	
	public SimpleKnight(int x, int y, StateInfoManager stateInfoManager) {
		super(x, y, stateInfoManager);
		canMove(true);
		canFocus(true);
		activityFigure = new ActivityKnightFigure();
		
		setActivityFigure(ActivityBaseFigure.MOVE_RIGHT);
		setName("Knight");

	}
	
	@Override
	public void updateEditor() {
		setPlacedManualy(true);
		canMove(false);
		canFocus(true);
		setActivityFigure(ActivityBaseFigure.PLACED_MANUALY);
	}
	
	@Override
	protected void refreshImage() {
		
		long timeKnight= 125;
		
		if (getActualActivity().equals(ActivityBaseFigure.MOVE_DOWN_RIGHT)) {
		
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_315_1.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_315_2.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_315_3.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_315_4.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_315_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_315_6.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_315_7.png"), timeKnight);
		
		} else if (getActualActivity().equals(ActivityBaseFigure.MOVE_RIGHT)) {
			
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_360_1.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_360_2.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_360_3.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_360_4.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_360_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_360_6.png"), timeKnight);

		} else if (getActualActivity().equals(ActivityBaseFigure.MOVE_UP)) {
			
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_90_1.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_90_2.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_90_3.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_90_4.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_90_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_90_6.png"), timeKnight);

		} else if (getActualActivity().equals(ActivityBaseFigure.MOVE_LEFT)) {
			
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_180_1.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_180_2.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_180_3.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_180_4.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_180_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_180_6.png"), timeKnight);

		} else if (getActualActivity().equals(ActivityBaseFigure.MOVE_DOWN)) {
			
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_270_1.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_270_2.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_270_3.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_270_4.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_270_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_270_6.png"), timeKnight);

		} else if (getActualActivity().equals(ActivityBaseFigure.MOVE_UP_RIGHT)) {
					
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_45_1.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_45_2.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_45_3.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_45_4.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_45_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_45_6.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_45_7.png"), timeKnight);

		} else if (getActualActivity().equals(ActivityBaseFigure.MOVE_DOWN_LEFT)) {
			
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_225_1.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_225_2.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_225_3.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_225_4.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_225_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_225_6.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_225_7.png"), timeKnight);
			

		} else if (getActualActivity().equals(ActivityBaseFigure.MOVE_UP_LEFT)) {
		
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_135_1.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_135_2.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_135_3.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_135_4.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_135_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_135_6.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_135_7.png"), timeKnight);
			
		} else if (getActualActivity().equals(ActivityBaseFigure.STAND)) {
		
			timeKnight = 1500;
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_225_5.png"), timeKnight);
			frameManager.addFrame(getImageSprite("knight_move_45_5.png"), timeKnight);
			
			
		} else if (getActualActivity().equals(ActivityBaseFigure.PLACED_MANUALY)) {
			frameManager.clearAllFrames();
			frameManager.addFrame(getImageSprite("knight_move_45_5.png"), timeKnight);

		}
	
	}
	
	@Override
	public void move() {
		
		if (!getActualMapPosition().equals(getEndMapPosition())) {
			
			Point2DGame pointA = new Point2DGame(getActualMapPosition().x, getActualMapPosition().y);
			Point2DGame pointB = new Point2DGame(getEndMapPosition().x, getEndMapPosition().y);
			
			if (isRecalculatePointsForVector()) {
				
				listPoints = MathOperation.getPointsForVector(pointA, pointB, step);
				setRecalculatePointsForVector(false);
			}
			
			if (listPoints.size() > 1) {
				
				Point2DGame pointC2 = MathOperation.getNextPointForVector(pointA, pointB, step);
				Point2DGame pointC = listPoints.get(1);
				
				Vector2DGame vectorA = new Vector2DGame(new Point2DGame(0, 0), new Point2DGame(1, 0));
				//Vector2DGame vectorB = new Vector2DGame(new Point2DGame(0, 0), new Point2DGame(getEndMapPosition().x - getActualMapPosition().x, getActualMapPosition().y - getEndMapPosition().y));
				Vector2DGame vectorB = new Vector2DGame(new Point2DGame(0, 0), new Point2DGame(pointC2.x - getActualMapPosition().x, getActualMapPosition().y - pointC2.y));
				
				setAngle(MathOperation.calculateAngle(vectorA, vectorB));
				
				
				setActualMapPosition(pointC.x, pointC.y);
				listPoints.remove(0);
			} else {
				clearEndMapPositionX();
				clearEndMapPositionY();
			}

			if (getAngle() >= 350 || getAngle() <= 10) {
				
				if(!ActivityBaseFigure.MOVE_RIGHT.equals(getActualActivity())) {
					setActivityFigure(ActivityBaseFigure.MOVE_RIGHT);
				}
				
			} else if (getAngle() >= 10 && getAngle() <= 80) {
				
				if(!ActivityBaseFigure.MOVE_UP_RIGHT.equals(getActualActivity())) {
					setActivityFigure(ActivityBaseFigure.MOVE_UP_RIGHT);
				}
			
			} else if (getAngle() > 80 && getAngle() <= 100) {
				
				if(!ActivityBaseFigure.MOVE_UP.equals(getActualActivity())) {
					setActivityFigure(ActivityBaseFigure.MOVE_UP);
				}
			
			} else if (getAngle() > 100 && getAngle() <= 170) {
				
				if(!ActivityBaseFigure.MOVE_UP_LEFT.equals(getActualActivity())) {
					setActivityFigure(ActivityBaseFigure.MOVE_UP_LEFT);
				}
				
			} else if (getAngle() > 170 && getAngle() <= 190) {
				
				if(!ActivityBaseFigure.MOVE_LEFT.equals(getActualActivity())) {
					setActivityFigure(ActivityBaseFigure.MOVE_LEFT);
				}
			
			} else if (getAngle() > 190 && getAngle() <= 260) {
				
				if(!ActivityBaseFigure.MOVE_DOWN_LEFT.equals(getActualActivity())) {
					setActivityFigure(ActivityBaseFigure.MOVE_DOWN_LEFT);
				}
			
			} else if (getAngle() > 260 && getAngle() <= 280) {
				
				if(!ActivityBaseFigure.MOVE_DOWN.equals(getActualActivity())) {
					setActivityFigure(ActivityBaseFigure.MOVE_DOWN);
				}
				
			} else if (getAngle() > 280 && getAngle() <= 350 ) {
				
				if(!ActivityBaseFigure.MOVE_DOWN_RIGHT.equals(getActualActivity())) {
					setActivityFigure(ActivityBaseFigure.MOVE_DOWN_RIGHT);
				}
			}
			
		} else {
			if (!ActivityBaseFigure.STAND.equals(getActualActivity())) {
				setActivityFigure(ActivityBaseFigure.STAND);
			}
		}
		
	}
	
}
