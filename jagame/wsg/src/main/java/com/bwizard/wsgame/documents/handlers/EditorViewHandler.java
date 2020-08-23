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
import com.bwizard.cegame.controls.components.interfaces.IEventHandler;
import com.bwizard.cegame.documents.handlers.FigureType;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.logs.LogInfo;

public class EditorViewHandler extends BaseHandler implements IEventHandler {

	private static final LogInfo logInfo = new LogInfo(EditorViewHandler.class);

	public EditorViewHandler(BaseWorldGame baseWorldGame) {
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
					CmnComponentName.QUESTION_EXIT_GAME, FigureType.EDITOR_VIEW );

		} else if (MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON.equals(arg0)) {

			returnToEditorMap();

		} else if (EditorComponentName.RETURN_TO_EDITOR_BUTTON.equals(arg0)) {

			returnToEditorMap();

		} if (EditorComponentName.EXIT_USER_MAP_BUTTON.equals(arg0)) {

			exitFromGame();

		} else if (EditorComponentName.TOOLBAR_MENU_USER_MAP_BUTTON.equals(arg0)) {

			displayLayout(EntryEventManager.EDITOR_MAINMENU_DIALOG);

		} else if (EditorComponentName.NATURE_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.KIND_OF_NATURE_USER_MAP_LIST);
			hideGroup();

		} else if (EditorComponentName.BUILDINGS_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.KIND_OF_BUILDINGS_USER_MAP_LIST);
			hideGroup();
			
		} else if (EditorComponentName.UNIT_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.KIND_OF_CHARACTER_USER_MAP_LIST);
			hideGroup();
		
		} else if (EditorComponentName.BACKGROUND_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.BACKGROUND_OF_NATURE_USER_MAP_LIST);
			
		//natures
		} else if (EditorComponentName.TREE_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.TREE_OF_NATURE_USER_MAP_LIST);
			
		} else if (EditorComponentName.WATER_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.WATER_OF_NATURE_USER_MAP_LIST);
	
		} else if (EditorComponentName.ROCK_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.ROCK_OF_NATURE_USER_MAP_LIST);
			
		} else if (EditorComponentName.ROAD_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.ROAD_OF_NATURE_USER_MAP_LIST);
			
		} else if (EditorComponentName.ANIMAL_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.ANIMAL_OF_NATURE_USER_MAP_LIST);
			
		//buildings
		} else if (EditorComponentName.KNIGHT_BUILDINGS_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.KNIGHT_OF_BUILDINGS_USER_MAP_LIST);
			
		} else if (EditorComponentName.BARBARIAN_BUILDINGS_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.BARBARIAN_OF_BUILDINGS_USER_MAP_LIST);
			
		} else if (EditorComponentName.ELF_BUILDINGS_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.ELF_OF_BUILDINGS_USER_MAP_LIST);
			
		} else if (EditorComponentName.DWARF_BUILDINGS_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.DWARF_OF_BUILDINGS_USER_MAP_LIST);
			
		} else if (EditorComponentName.ORK_BUILDINGS_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.ORK_OF_BUILDINGS_USER_MAP_LIST);
			
		} else if (EditorComponentName.NECROMANCER_BUILDINGS_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.NECROMANCER_OF_BUILDINGS_USER_MAP_LIST);
			
		//units
		} else if (EditorComponentName.KNIGHT_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.KNIGHT_OF_UNITS_USER_MAP_LIST);
			
		} else if (EditorComponentName.BARBARIAN_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.BARBARIAN_OF_UNITS_USER_MAP_LIST);
			
		} else if (EditorComponentName.ELF_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.ELF_OF_UNITS_USER_MAP_LIST);
			
		} else if (EditorComponentName.DWARF_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.DWARF_OF_UNITS_USER_MAP_LIST);
			
		} else if (EditorComponentName.ORK_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.ORK_OF_UNITS_USER_MAP_LIST);
			
		} else if (EditorComponentName.NECROMANCER_USER_MAP_BUTTON.equals(arg0)) {
			
			showComponent(EditorComponentName.NECROMANCER_OF_UNITS_USER_MAP_LIST);
		}
		
		if (CmnObjectName.KNIGHT_1.equals(arg0)) {
			FigureBuilder factory = new CharacterBuilder();
			factory.setStateInfoGame(baseWorldGame.getStateInfoGame());
			BaseFigure baseFigure = factory.build(CmnObjectName.KNIGHT_1);
			baseFigure.updateEditor();
			baseFigure.setSelected(true);
			baseWorldGame.addFigure(baseFigure);
			baseFigure.setPlaceFree(baseWorldGame.getWorldObjectManager().getUserViewManager().getUserViewObjects());
			
		} else if (CmnObjectName.CASTLE_1.equals(arg0)) {
			FigureBuilder factory = new BuildingBuilder();
			factory.setStateInfoGame(baseWorldGame.getStateInfoGame());
			BaseFigure baseFigure = factory.build(CmnObjectName.CASTLE_1);
			baseFigure.updateEditor();
			baseFigure.setSelected(true);
			baseWorldGame.addFigure(baseFigure);
			baseFigure.setPlaceFree(baseWorldGame.getWorldObjectManager().getUserViewManager().getUserViewObjects());
			
		} else if (CmnObjectName.TREE_1.equals(arg0)) {
			FigureBuilder factory = new NatureBuilder();
			factory.setStateInfoGame(baseWorldGame.getStateInfoGame());
			BaseFigure baseFigure = factory.build(CmnObjectName.TREE_1);
			baseFigure.updateEditor();
			baseFigure.setSelected(true);
			baseWorldGame.addFigure(baseFigure);
			baseFigure.setPlaceFree(baseWorldGame.getWorldObjectManager().getUserViewManager().getUserViewObjects());
		}
		
		
		
		System.gc();
		
		logInfo.info("end: invokeEvent(sender, "+ arg0 + ")");
		
	}
	
	private void hideGroup() {
		hideAllGroup("user_map_list");
	}

}
