package com.bwizard.wsgame.device.model;

import com.bwizard.wsgame.documents.handlers.BaseHandler;
import com.bwizard.wsgame.documents.names.CmnComponentName;
import com.bwizard.wsgame.documents.names.MenuComponentName;

import java.awt.event.KeyEvent;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.device.handlers.DirectionMove;
import com.bwizard.cegame.device.model.KeysModel;
import com.bwizard.cegame.documents.handlers.FigureType;
import com.bwizard.cegame.figure.BaseFigure;

public class KeysEditorCustomModel extends KeysModel {

	private BaseHandler baseHandler = null;
	
	public KeysEditorCustomModel(BaseWorldGame baseWorldGame) {
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
			case KeyEvent.VK_ESCAPE:
				baseHandler.performQuestionYesNoDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON, 
						CmnComponentName.QUESTION_EXIT_GAME, FigureType.EDITOR_VIEW );
				break;
		}
				
		//when position of map is changed we updated selection
		if (baseWorldGame.getCursorInfo().isSelected()) {
			baseWorldGame.getCursorInfo().updateStartSelectionX(stepX);
			baseWorldGame.getCursorInfo().updateStartSelectionY(stepY);
		}		
			
    	//for editor
		for(BaseFigure figure : baseWorldGame.getWorldObjectManager().getSelectedObjects()) {
			
			if (figure.isPlacedManualy() && baseWorldGame.getCursorInfo().isCursorOverViewUser()) {
				int px = baseWorldGame.getCursorInfo().getMapCursorPositionX() - (figure.getWidth() /2);
				int py = baseWorldGame.getCursorInfo().getMapCursorPositionY() - (figure.getHeight() /2);
				figure.setAllMapPositions(px, py);
				figure.setPlaceFree(baseWorldGame.getWorldObjectManager().getUserViewManager().getUserViewObjects());
			}
		}
	}
	
	@Override
	public void clear() {
		baseHandler = null;
	}

}
