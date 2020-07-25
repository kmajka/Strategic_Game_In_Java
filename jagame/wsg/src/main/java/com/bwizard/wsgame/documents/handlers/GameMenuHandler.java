package com.bwizard.wsgame.documents.handlers;

import com.bwizard.wsgame.documents.EntryEventManager;
import com.bwizard.wsgame.documents.names.CmnComponentName;
import com.bwizard.wsgame.documents.names.GameComponentName;
import com.bwizard.wsgame.documents.names.MenuComponentName;
import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.interfaces.IEventHandler;
import com.bwizard.cegame.documents.handlers.FigureType;
import com.bwizard.cegame.logs.LogInfo;

public class GameMenuHandler extends BaseHandler implements IEventHandler {

	private static final LogInfo logInfo = new LogInfo(GameMenuHandler.class);

	public GameMenuHandler(BaseWorldGame baseWorldGame) {
		super(baseWorldGame);
	}

	@Override
	public void invokeEvent(Object sender, String arg0) {

		logInfo.info("start: invokeEvent(sender, "+ arg0 + ")");
		
//		NEW_USER_GAME_BUTTON
//		LOAD_USER_GAME_BUTTON
//		SAVE_USER_GAME_BUTTON
//		EXIT_USER_GAME_BUTTON
		
		if (GameComponentName.CANCEL_USER_GAME_BUTTON.equals(arg0)) {

			returnToGame();

		} else if (GameComponentName.EXIT_USER_GAME_BUTTON.equals(arg0)) {

			performQuestionYesNoDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON, 
					CmnComponentName.QUESTION_EXIT_GAME, FigureType.GAME_MENU);

		} else if (MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON.equals(arg0)) {
			
			performLayout(EntryEventManager.GAME_MENU);
			
		} else if (MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON.equals(arg0)) {

			returnToGame();
		}
		
		System.gc();

		logInfo.info("end: invokeEvent(sender, "+ arg0 + ")");

	}

}
