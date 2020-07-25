package com.bwizard.wsgame.documents.handlers;

import com.bwizard.wsgame.documents.EntryEventManager;
import com.bwizard.wsgame.documents.names.CmnComponentName;
import com.bwizard.wsgame.documents.names.CmnObjectName;
import com.bwizard.wsgame.documents.names.EditorComponentName;
import com.bwizard.wsgame.documents.names.GameComponentName;
import com.bwizard.wsgame.documents.names.MenuComponentName;
import com.bwizard.wsgame.world.figure.builder.BuildingBuilder;
import com.bwizard.wsgame.world.figure.builder.FigureBuilder;
import com.bwizard.wsgame.world.figure.builder.CharacterBuilder;
import com.bwizard.wsgame.world.figure.builder.NatureBuilder;
import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.interfaces.IEventHandler;
import com.bwizard.cegame.documents.handlers.FigureType;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.logs.LogInfo;

public class GameViewHandler extends BaseHandler implements IEventHandler {

	private static final LogInfo logInfo = new LogInfo(GameViewHandler.class);

	public GameViewHandler(BaseWorldGame baseWorldGame) {
		super(baseWorldGame);
	}

	@Override
	public void invokeEvent(Object sender, String arg0) {

		logInfo.info("start: invokeEvent(sender, "+ arg0 + ")");
		
		if (MenuComponentName.EXIT_APPLICATION_BUTTON.equals(arg0)) {

			exitApplication();

		} else if (MenuComponentName.QUESTION_ABOUT_EXIT_APPLICATION_BUTTON.equals(arg0)) {

			performQuestionYesNoDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON, 
					CmnComponentName.QUESTION_EXIT_GAME, FigureType.GAME_VIEW);

		} else if (MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON.equals(arg0)) {

			returnToGame();

		} else if (GameComponentName.EXIT_USER_GAME_BUTTON.equals(arg0)) {

			performQuestionYesNoDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON, 
					CmnComponentName.QUESTION_EXIT_GAME, FigureType.GAME_VIEW);

		} else if (GameComponentName.TOOLBAR_MENU_USER_GAME_BUTTON.equals(arg0)) {

			displayLayout(EntryEventManager.GAME_MAINMENU_DIALOG);

		} else if (GameComponentName.CANCEL_USER_GAME_BUTTON.equals(arg0)) {

			returnToGame();

		} else if (GameComponentName.DISPLAY_INFO_FIGURE.equals(arg0)) {
			
			BaseFigure selectedFigure = baseWorldGame.getWorldObjectManager().getFirstSelectedObject();
			if (selectedFigure != null) {
				if (sender instanceof BaseDrawFigure) {
					selectedFigure.setDisplayInfo(((BaseDrawFigure)sender).isSelected());
				}
			}

		} else if (MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON.equals(arg0)) {
			
			performLayout(EntryEventManager.GAME_MENU);

		} else if (EditorComponentName.CASTLE_BUTTON.equals(arg0)) {
			
			BaseFigure selectedFigure = baseWorldGame.getWorldObjectManager().getFirstSelectedObject();
			if (selectedFigure != null) {
				FigureBuilder factory = new BuildingBuilder();
				factory.setStateInfoManager(baseWorldGame.getStateInfoManager());
				BaseFigure baseFigure = factory.build(CmnObjectName.CASTLE_1);
				baseFigure.setAllMapPositions(selectedFigure.getMapPositionX() + (selectedFigure.getWidth() / 2), selectedFigure.getMapPositionY() + selectedFigure.getHeight());
				baseWorldGame.addFigure(baseFigure);
			}
			baseWorldGame.waitForStartApplication();
			
		}
		else if (EditorComponentName.KNIGHT_BUTTON.equals(arg0)) {
			
			BaseFigure selectedFigure = baseWorldGame.getWorldObjectManager().getFirstSelectedObject();
			if (selectedFigure != null) {
				FigureBuilder factory = new CharacterBuilder();
				factory.setStateInfoManager(baseWorldGame.getStateInfoManager());
				BaseFigure baseFigure = factory.build(CmnObjectName.KNIGHT_1);
				baseFigure.setAllMapPositions(selectedFigure.getMapPositionX() + (selectedFigure.getWidth() / 2), selectedFigure.getMapPositionY() + selectedFigure.getHeight());
				baseWorldGame.addFigure(baseFigure);
			}
			baseWorldGame.waitForStartApplication();
			
		} else if(EditorComponentName.TREE_BUTTON.equals(arg0)) {
			
			BaseFigure selectedFigure = baseWorldGame.getWorldObjectManager().getFirstSelectedObject();
			if (selectedFigure != null) {
				FigureBuilder factory = new NatureBuilder();
				factory.setStateInfoManager(baseWorldGame.getStateInfoManager());
				BaseFigure baseFigure = factory.build(CmnObjectName.TREE_1);
				baseFigure.setAllMapPositions(selectedFigure.getMapPositionX() + (selectedFigure.getWidth() / 2), selectedFigure.getMapPositionY() + selectedFigure.getHeight());
				baseFigure.updateEditor();
				baseWorldGame.addFigure(baseFigure);
			}
			baseWorldGame.waitForStartApplication();
		}
		
		else if (GameComponentName.CHECKBOX_DISPLAY_INFO_POSITION_MAP.equals(arg0)) {
			
			changeVisibleComponent(GameComponentName.INFO_POSITION_MAP);
			
		} else if (EditorComponentName.CHECKBOX_DISPLAY_INFO_POSITION_CHARACTER.equals(arg0)) {
			
			changeVisibleComponent(EditorComponentName.INFO_POSITION_CHARACTER);
		}
		
		
		logInfo.info("end: invokeEvent(sender, "+ arg0 + ")");

	}
	
}
