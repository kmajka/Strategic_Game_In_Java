package com.bwizard.wsgame.device.model;

import com.bwizard.wsgame.documents.handlers.BaseHandler;
import com.bwizard.wsgame.documents.names.CmnComponentName;
import com.bwizard.wsgame.documents.names.MenuComponentName;

import java.awt.event.KeyEvent;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.device.handlers.DirectionMove;
import com.bwizard.cegame.device.model.KeysModel;
import com.bwizard.cegame.documents.handlers.FigureType;

public class KeysGameCustomModel extends KeysModel {

	private BaseHandler baseHandler = null;
	
	public KeysGameCustomModel(BaseWorldGame baseWorldGame) {
		super(baseWorldGame);
		baseHandler = new BaseHandler(baseWorldGame);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {

		int stepX = 0;
		int stepY = 0;
		
		baseWorldGame.getStateInfoGame().getKeysInfo().setKeyEvent(arg0);
		
		switch(arg0.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				stepY = baseWorldGame.getCameraMapInfo().setCameraY(KEY_STEP, DirectionMove.MOVE_DOWN, true); 
				break;
			case KeyEvent.VK_UP:
				stepY = baseWorldGame.getCameraMapInfo().setCameraY(KEY_STEP, DirectionMove.MOVE_UP, true);
				break;
			case KeyEvent.VK_LEFT:
				stepX = baseWorldGame.getCameraMapInfo().setCameraX(KEY_STEP, DirectionMove.MOVE_LEFT, true); 
				break;
			case KeyEvent.VK_RIGHT:
				stepX = baseWorldGame.getCameraMapInfo().setCameraX(KEY_STEP, DirectionMove.MOVE_RIGHT, true); 
				break;
			case KeyEvent.VK_P:
				
				if (!baseWorldGame.isPausedGame()) {
					baseWorldGame.waitForPauseApplication();
				} else {
					baseWorldGame.waitForStartApplication();
				}
				break;
			case KeyEvent.VK_ESCAPE:
				
				baseHandler.performQuestionYesNoDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON, 
						CmnComponentName.QUESTION_EXIT_GAME, FigureType.GAME_VIEW );
				break;
			case KeyEvent.VK_T:
				
				baseHandler.performInfoSingleObjectDialog(FigureType.GAME_VIEW );
				break;
			case KeyEvent.VK_SPACE:
				baseWorldGame.getWorldObjectManager().unselectedAllFigure();
				break;
			default:
				break;
		}
				
		//when position of map is changed we updated selection
		if (baseWorldGame.getCursorInfo().isSelected()) {
			baseWorldGame.getCursorInfo().updateStartSelectionX(stepX);
			baseWorldGame.getCursorInfo().updateStartSelectionY(stepY);
		}
		
	}
	
	@Override
	public void clear() {
		baseHandler = null;
	}

}
