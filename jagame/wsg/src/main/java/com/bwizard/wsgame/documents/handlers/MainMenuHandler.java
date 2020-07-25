package com.bwizard.wsgame.documents.handlers;

import com.bwizard.wsgame.documents.EntryEventManager;
import com.bwizard.wsgame.documents.names.CmnComponentName;
import com.bwizard.wsgame.documents.names.EditorComponentName;
import com.bwizard.wsgame.documents.names.GameComponentName;
import com.bwizard.wsgame.documents.names.MenuComponentName;
import com.bwizard.wsgame.parameters.GameGlobals;

import java.awt.image.BufferedImage;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.interfaces.IEventHandler;
import com.bwizard.cegame.documents.handlers.FigureType;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.state.handlers.Entry;
import com.bwizard.cegame.thread.ThreadStatus;
import com.bwizard.cegame.window.handlers.WindowGameActionName;

//main_menu
public class MainMenuHandler extends BaseHandler implements IEventHandler {

	private static final LogInfo logInfo = new LogInfo(MainMenuHandler.class);

	public MainMenuHandler(BaseWorldGame baseWorldGame) {
		super(baseWorldGame);
	}

	@Override
	public void invokeEvent(Object sender, String arg0) {

		logInfo.info("start: invokeEvent(sender, "+ arg0 + ")");
		
		if (MenuComponentName.EXIT_APPLICATION_BUTTON.equals(arg0)) {

			exitApplication();

		} else if (MenuComponentName.QUESTION_ABOUT_EXIT_APPLICATION_BUTTON.equals(arg0)) {

			performQuestionYesNoDialog(MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON, MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON, 
					CmnComponentName.QUESTION_EXIT_GAME, FigureType.MAIN_MENU );

		} else if (MenuComponentName.RETURN_TO_PREVIOUS_PANEL_BUTTON.equals(arg0)) {

			returnToGame();
			
		} else if (GameComponentName.CANCEL_USER_GAME_BUTTON.equals(arg0)) {

			returnToGame();

		} else if (MenuComponentName.REOPEN_APPLICATION_BUTTON.equals(arg0)) {

			// re-opening application
			baseWorldGame.waitForRestartApplication();
			baseWorldGame.getBaseWindowGame().setHideActionName(WindowGameActionName.REOPEN);
			baseWorldGame.addEventAction(Entry.MENU);
			
		} else if (MenuComponentName.CREDITS_BUTTON.equals(arg0)) {

			performLayout(EntryEventManager.CREDITSGAME_MENU);

		} else if (MenuComponentName.CONFIGURATION_BUTTON.equals(arg0)) {

			performLayout(EntryEventManager.CONFIGURATIONGAME_MENU);

		} else if (MenuComponentName.OPEN_CUSTOM_GAME_BUTTON.equals(arg0)) {
			
			BaseDrawFigure baseDrawFigure = null;
			if (sender instanceof BaseDrawFigure) {
				baseDrawFigure = (BaseDrawFigure)sender;
			
				if (baseDrawFigure.getCountPressed() == 1) {
					BaseDrawFigure element = null;
					String text = baseDrawFigure.getDescriptionField1();
					element = baseWorldGame.getElementByTag("DESCRIPTION_MAP");
					element.setText(text);
					
					BufferedImage imageMap = baseDrawFigure.getImage2();
					element = baseWorldGame.getElementByTag("IMAGE_MAP");
					element.setImage(imageMap);
								
				} else {
					//////////////image for load game/////////////////////
					
					performLayout(EntryEventManager.LOADINGGAME_PANEL);
					//time for refresh image for loading
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					///////////////////////////////////////////////
					
					baseWorldGame.updateAndWaitThreadStatus(ThreadStatus.SUSPEND);
					
					baseWorldGame.clear();
			
					// load map of game
					baseWorldGame.loadData(GameGlobals.MAPS + baseDrawFigure.getFileName());
			
					// recreate panel view of layout
					baseWorldGame.getStateInfoManager().getPanelLayoutManager()
							.setLayoutName(EntryEventManager.GAME_VIEW);
								
					baseWorldGame.getStateInfoManager().getPanelLayoutManager()
							.loadData();
			
					baseWorldGame.updatePositionDependingOnLayout();
			
					baseWorldGame.waitForStartApplication();
					
					baseWorldGame.addEventAction(Entry.GAME);
				}
				
			}

		} else if (MenuComponentName.CUSTOM_GAME_BUTTON.equals(arg0)) {

			performLayout(EntryEventManager.CUSTOMGAME_MENU);

		} else if (MenuComponentName.RETURN_TO_EDITOR_MAIN_MENU_GAME_BUTTON.equals(arg0)) {
			
			performLayout(EntryEventManager.EDITOR_MENU);
			
		} else if (MenuComponentName.RETURN_TO_MAIN_MENU_GAME_BUTTON.equals(arg0)) {
			
			performLayout(EntryEventManager.GAME_MENU);
			
 		} else if (MenuComponentName.EDITOR_GAME_BUTTON.equals(arg0)) {

 			performLayout(EntryEventManager.EDITOR_MENU);
			
 		} else if (MenuComponentName.LOAD_SAVED_GAMES_BUTTON.equals(arg0)) {

 			performLayout(EntryEventManager.LOADGAME_MENU);
			
 		} else if (EditorComponentName.NEW_USER_MAP_BUTTON.equals(arg0)) {
 			
 			performLayout(EntryEventManager.EDITOR_NEW_MAP_MENU);
	
		} else if (EditorComponentName.LOAD_LIST_OF_USER_MAPS_BUTTON.equals(arg0)) {
			
			performLayout(EntryEventManager.EDITOR_LOAD_MAPS_MENU);
			
		} else if (MenuComponentName.GENERATE_USER_MAP_BUTTON.equals(arg0)) {
 						
			baseWorldGame.updateAndWaitThreadStatus(ThreadStatus.SUSPEND);
			
			// clear all objects added previously
			baseWorldGame.clear();

			// load map of game
			baseWorldGame.loadData(GameGlobals.MAPS + "Editor_Map_01.xml");
			// recreate panel view of layout
			baseWorldGame.getStateInfoManager().getPanelLayoutManager()
					.setLayoutName(EntryEventManager.EDITOR_VIEW);

			baseWorldGame.getStateInfoManager().getPanelLayoutManager()
					.loadData();

			baseWorldGame.updatePositionDependingOnLayout();

			baseWorldGame.waitForStartApplication();
		
			baseWorldGame.addEventAction(Entry.EDITOR);

		}
		
		System.gc();

		logInfo.info("end: invokeEvent(sender, "+ arg0 + ")");

	}

}
