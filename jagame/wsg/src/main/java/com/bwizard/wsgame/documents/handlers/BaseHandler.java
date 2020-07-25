package com.bwizard.wsgame.documents.handlers;

import com.bwizard.wsgame.documents.EntryEventManager;
import com.bwizard.wsgame.documents.names.CmnComponentName;
import java.util.ArrayList;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.documents.handlers.FigureType;
import com.bwizard.cegame.state.handlers.Entry;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;

public class BaseHandler {
	
	protected BaseWorldGame baseWorldGame = null;
	protected EntryEventManager entryEventManager = new EntryEventManager();
	
	public BaseHandler(BaseWorldGame baseWorldGame) {
		this.baseWorldGame = baseWorldGame;
	}
	
	//uses for display menu, game and editor main layout
	protected void performLayout(String name) {
		
		baseWorldGame.waitForSuspendApplication();
		
		// clear all objects added previously
		baseWorldGame.clear();

		// recreate panel view of layout
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().setLayoutName(name);

		baseWorldGame.getStateInfoManager().getPanelLayoutManager().loadData();

		baseWorldGame.addEventAction(entryEventManager.getActionEntry(name));
		
		baseWorldGame.waitForStartApplication();
		
	}
	
	//uses for displayed current dialog and remove all previous dialogs
	protected void displayNewLayout(String name) {
		
		baseWorldGame.waitForSuspendApplication();
		
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().removeDialog();
		
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().setLayoutName(name);

		baseWorldGame.getStateInfoManager().getPanelLayoutManager().reloadData();
				
		baseWorldGame.addEventAction(entryEventManager.getActionEntry(name));
		
		baseWorldGame.waitForPauseApplication();
		
	}
	
	//uses for displayed current dialog
	protected void displayLayout(String name) {
		
		baseWorldGame.waitForPauseApplication();

		baseWorldGame.getStateInfoManager().getPanelLayoutManager().setLayoutName(name);

		baseWorldGame.getStateInfoManager().getPanelLayoutManager().reloadData();
		
		baseWorldGame.addEventAction(entryEventManager.getActionEntry(name));
		
	}
	
	protected void returnToGame() {
		
		baseWorldGame.waitForSuspendApplication();
		
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().removeDialog();
				
		baseWorldGame.addEventAction(Entry.GAME);
		
		baseWorldGame.waitForStartApplication();
	
	}
	
	protected void exitFromGame() {
		
		//baseWorldGame.waitForSuspendApplication();
		baseWorldGame.waitForPauseApplication();

		baseWorldGame.getStateInfoManager().getPanelLayoutManager().removeDialog();
		// clear all objects added previously
		baseWorldGame.clear();

		// load map of game
		baseWorldGame.loadData("");
		// recreate panel view of layout
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().setLayoutName(EntryEventManager.GAME_MENU);

		baseWorldGame.getStateInfoManager().getPanelLayoutManager().loadData();
		
		baseWorldGame.addEventAction(entryEventManager.getActionEntry(EntryEventManager.GAME_MENU));
		
		baseWorldGame.waitForStartApplication();
	}
	
	protected void returnToEditorMap() {
		
		baseWorldGame.waitForPauseApplication();
		
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().removeDialog();

		baseWorldGame.addEventAction(Entry.EDITOR);
		
		baseWorldGame.waitForStartApplication();
	}
	
	protected void exitApplication() {
		
		try {
			baseWorldGame.waitForExitApplication();
			IWindowScreen windowScreen = baseWorldGame.getWindowScreen();
			if (windowScreen != null) {
				windowScreen.close();
			}
		} finally {
			System.exit(0);
		}
	}
	
	public void performInformationDialog(String tagNo, String tagYes, String text, FigureType figureType) {
		
		try {
					
			baseWorldGame.waitForStartApplication();
			
			baseWorldGame.getStateInfoManager().getPanelLayoutManager().removeDialog();
			
			baseWorldGame.waitForPauseApplication();
			
			baseWorldGame.getStateInfoManager().getPanelLayoutManager().setLayoutName(EntryEventManager.INFORMATION_DIALOG);

			baseWorldGame.getStateInfoManager().getPanelLayoutManager().reloadData();

			baseWorldGame.addEventAction(Entry.DIALOG);
			
			BaseDrawFigure baseDrawFigure = baseWorldGame.getElementByTag(CmnComponentName.INFORMATION_BUTTON_OK);
			if (baseDrawFigure != null) {
				baseDrawFigure.setFigureType(figureType);
				baseDrawFigure.setTag(tagYes);
				baseWorldGame.getStateInfoManager().getPanelLayoutManager().setHandler(baseDrawFigure);
			}
			
			setTextForTagElement(CmnComponentName.INFORMATION_DIALOG_TEXT, text);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	
	
	public void performQuestionYesNoDialog(String tagNo, String tagYes, String text, FigureType figureType) {
		
		try {
				
			baseWorldGame.waitForPauseApplication();
			
			baseWorldGame.getStateInfoManager().getPanelLayoutManager().removeDialog();
			
			baseWorldGame.getStateInfoManager().getPanelLayoutManager().setLayoutName(EntryEventManager.QUESTION_YESNO_DIALOG);

			baseWorldGame.getStateInfoManager().getPanelLayoutManager().reloadData();

			baseWorldGame.addEventAction(Entry.DIALOG);
			
			BaseDrawFigure baseDrawFigure = null;
			baseDrawFigure = baseWorldGame.getElementByTag(CmnComponentName.QUESTION_BUTTON_NO);
			if (baseDrawFigure != null) {
				baseDrawFigure.setFigureType(figureType);
				baseDrawFigure.setTag(tagNo);
				baseWorldGame.getStateInfoManager().getPanelLayoutManager().setHandler(baseDrawFigure);
			}
			
			baseDrawFigure = baseWorldGame.getElementByTag(CmnComponentName.QUESTION_BUTTON_YES);
			if (baseDrawFigure != null) {
				baseDrawFigure.setFigureType(figureType);
				baseDrawFigure.setTag(tagYes);
				baseWorldGame.getStateInfoManager().getPanelLayoutManager().setHandler(baseDrawFigure);
			}
			
			setTextForTagElement(CmnComponentName.QUESTION_YESNO_DIALOG_TEXT, text);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public void performInfoSingleObjectDialog(FigureType figureType) {
		
		//name of the last selected object
		//image
		//position x/y the last selected object
		//destination x/y the last selected object
		//action
		//show in simple way how the object have to move to the goal where is the destination point
		baseWorldGame.waitForPauseApplication();
		
		//baseWorldGame.getStateInfoManager().getPanelLayoutManager().removeDialog();
		
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().setLayoutName(EntryEventManager.INFORMATION_SINGLE_OBJECT_DIALOG);

		baseWorldGame.getStateInfoManager().getPanelLayoutManager().reloadData();

		baseWorldGame.addEventAction(Entry.GAME);
						
	}
	
	private void setTextForTagElement(String tag, String text) throws Exception {
		ArrayList<BaseDrawFigure> list = baseWorldGame.getStateInfoManager().getPanelLayoutManager().getElementsByTag(tag);
		if (list != null && list.size() == 1) {
			list.get(0).setText(text);
		} else {
			throw new Exception("There is no tag "+tag+" element for set text.");
		}
	}
	
	protected void showComponent(String name) {
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().setVisiblePanelFromGroup(name, true);
	}
	
	protected void hideAllGroup(String group) {
		baseWorldGame.getStateInfoManager().getPanelLayoutManager().hideAllPanelsFromGroup(group);
	}
	
	
	protected void setVisibleComponent(String name, boolean visible) {
		BaseDrawFigure baseDrawFigure = baseWorldGame.getStateInfoManager().getPanelLayoutManager().getFirstElementsByTag(name);
		if (baseDrawFigure != null) {
			baseWorldGame.getStateInfoManager().getPanelLayoutManager().setVisiblePanelFromGroup(name, visible);
		}
			
	}
	
	protected void changeVisibleComponent(String name) {
		BaseDrawFigure baseDrawFigure = baseWorldGame.getStateInfoManager().getPanelLayoutManager().getFirstElementsByTag(name);
		if (baseDrawFigure != null) {
			baseWorldGame.getStateInfoManager().getPanelLayoutManager().setVisiblePanelFromGroup(name, !baseDrawFigure.isVisible());
		}
			
	}
	
}
