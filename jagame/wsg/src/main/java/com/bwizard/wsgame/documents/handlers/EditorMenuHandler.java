package com.bwizard.wsgame.documents.handlers;

import com.bwizard.wsgame.documents.EntryEventManager;
import com.bwizard.wsgame.documents.names.CmnComponentName;
import com.bwizard.wsgame.documents.names.CmnObjectName;
import com.bwizard.wsgame.documents.names.EditorComponentName;
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

//main_menu
public class EditorMenuHandler extends BaseHandler implements IEventHandler {

	private static final LogInfo logInfo = new LogInfo(EditorMenuHandler.class);

	public EditorMenuHandler(BaseWorldGame baseWorldGame) {
		super(baseWorldGame);
	}

	@Override
	public void invokeEvent(Object sender, String arg0) {

		logInfo.info("start: invokeEvent(sender, "+ arg0 + ")");

		if (MenuComponentName.EXIT_APPLICATION_BUTTON.equals(arg0)) {

			exitApplication();

		} else if (MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON.equals(arg0)) {
				
			performLayout(EntryEventManager.GAME_MENU);
				
		} else if (MenuComponentName.QUESTION_ABOUT_EXIT_APPLICATION_BUTTON.equals(arg0)) {

			performQuestionYesNoDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON, 
					CmnComponentName.QUESTION_EXIT_GAME, FigureType.EDITOR_MENU );

		} else if (MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON.equals(arg0)) {

			returnToEditorMap();

		} else if (EditorComponentName.RETURN_TO_EDITOR_BUTTON.equals(arg0)) {

			returnToEditorMap();

		} else if (EditorComponentName.NEW_USER_MAP_BUTTON.equals(arg0)) {
			
			performLayout(EntryEventManager.EDITOR_NEW_MAP_MENU);
			
		} else if (EditorComponentName.SAVE_USER_MAP_BUTTON.equals(arg0)) {

			displayNewLayout(EntryEventManager.EDITOR_SAVEMAP_DIALOG);
		
		} else if (EditorComponentName.SAVE_EDITOR_NEW_MAP_BUTTON.equals(arg0)) {			
						
			//zapisywanie mapy (nowa klasa) do katalogu maps.
			
			BaseDrawFigure element = baseWorldGame.getElementByTag("EDITOR_MAP_NAME_TEXTEDIT");
			String mapName = element.getText();
			String str = "\"" +mapName + "\" has been saved.";
			
			performInformationDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, EditorComponentName.RETURN_TO_EDITOR_BUTTON, str, FigureType.EDITOR_VIEW );
						
		} else if (EditorComponentName.LOAD_LIST_OF_USER_MAPS_BUTTON.equals(arg0)) {
			
			performLayout(EntryEventManager.EDITOR_LOAD_MAPS_MENU);
			
		} else if (EditorComponentName.EXIT_USER_MAP_BUTTON.equals(arg0)) {
		
			performQuestionYesNoDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON, 
					CmnComponentName.QUESTION_EXIT_GAME, FigureType.EDITOR_MENU );
			
		} else if (EditorComponentName.CASTLE_BUTTON.equals(arg0)) {
			
			BaseFigure selectedFigure = baseWorldGame.getWorldObjectManager().getFirstSelectedObject();
			if (selectedFigure != null) {
				FigureBuilder factory = new BuildingBuilder();
				factory.setStateInfoGame(baseWorldGame.getStateInfoGame());
				BaseFigure baseFigure = factory.build(CmnObjectName.CASTLE_1);
				baseFigure.setAllMapPositions(selectedFigure.getMapPositionX() + (selectedFigure.getWidth() / 2), selectedFigure.getMapPositionY() + selectedFigure.getHeight());
				baseFigure.updateEditor();
				baseWorldGame.addFigure(baseFigure);
			}
			
			baseWorldGame.waitForStartApplication();
			
		} else if (EditorComponentName.KNIGHT_BUTTON.equals(arg0)) {
			
			BaseFigure selectedFigure = baseWorldGame.getWorldObjectManager().getFirstSelectedObject();
			if (selectedFigure != null) {
				FigureBuilder factory = new CharacterBuilder();
				factory.setStateInfoGame(baseWorldGame.getStateInfoGame());
				BaseFigure baseFigure = factory.build(CmnObjectName.KNIGHT_1);
				baseFigure.setAllMapPositions(selectedFigure.getMapPositionX() + (selectedFigure.getWidth() / 2), selectedFigure.getMapPositionY() + selectedFigure.getHeight());
				baseFigure.updateEditor();
				baseWorldGame.addFigure(baseFigure);
			}
			
			baseWorldGame.waitForStartApplication();
			
		} else if(EditorComponentName.TREE_BUTTON.equals(arg0)) {
			
			BaseFigure selectedFigure = baseWorldGame.getWorldObjectManager().getFirstSelectedObject();
			if (selectedFigure != null) {
				FigureBuilder factory = new NatureBuilder();
				factory.setStateInfoGame(baseWorldGame.getStateInfoGame());
				BaseFigure baseFigure = factory.build(CmnObjectName.KNIGHT_1);
				baseFigure.setAllMapPositions(selectedFigure.getMapPositionX() + (selectedFigure.getWidth() / 2), selectedFigure.getMapPositionY() + selectedFigure.getHeight());
				baseFigure.updateEditor();
				baseWorldGame.addFigure(baseFigure);
			}
			
			baseWorldGame.waitForStartApplication();
			
		}
		
		System.gc();
		
		logInfo.info("end: invokeEvent(sender, "+ arg0 + ")");

	}

}
