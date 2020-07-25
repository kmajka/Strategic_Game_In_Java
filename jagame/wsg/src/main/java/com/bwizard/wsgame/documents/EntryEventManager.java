package com.bwizard.wsgame.documents;

import java.util.HashMap;

import com.bwizard.cegame.state.handlers.Entry;

public class EntryEventManager {
	
	private HashMap<String,Entry> hmEventAction = new HashMap<String, Entry>();

	//layout of game
	public final static String GAME_VIEW = "Game_View.xml";
	//layout of editor
	public final static String EDITOR_VIEW = "Editor_View.xml";
	
	/////////////////////////////MAIN MENU///////////////////////////
	
	//layout of Main Menu (first level of menu)
	public final static String GAME_MENU = "Game_Menu.xml";
	
	//layout of editor menu available from Main Menu (second level of menu)
	public final static String EDITOR_MENU = "Editor_Menu.xml";
	
	//layout of credits menu available from Main Menu (second level of menu)
	public final static String CREDITSGAME_MENU = "CreditsGame_Menu.xml";
	
	//layout of configuration menu available from Main Menu (second level of menu)
	public final static String CONFIGURATIONGAME_MENU = "ConfigurationGame_Menu.xml";
	
	//layout of custom game menu available from Main Menu (second level of menu)
	public final static String CUSTOMGAME_MENU = "CustomGame_Menu.xml";
	
	//layout of load saved game menu available from Main Menu (second level of menu)
	public final static String LOADGAME_MENU = "LoadGame_Menu.xml";
	
	//layout of new map menu available from Main Menu (third level of menu)
	public final static String EDITOR_NEW_MAP_MENU = "Editor_New_Map_Menu.xml";
	
	//layout of load maps menu available from Main Menu (third level of menu)
	public final static String EDITOR_LOAD_MAPS_MENU = "Editor_Load_Maps_Menu.xml";
	
	/////////////////////////MAIN DIALOG MENU FROM GAME/EDITOR/////////////////////////
	
	public final static String GAME_MAINMENU_DIALOG = "Game_MainMenu_Dialog.xml";
	public final static String EDITOR_MAINMENU_DIALOG = "Editor_MainMenu_Dialog.xml";
	
	/////////////////////////////DIALOG MENU FROM GAME/EDITOR///////////////////////////
	
	public final static String EDITOR_SAVEMAP_DIALOG = "Editor_SaveMap_Dialog.xml";
	public final static String INFORMATION_DIALOG = "Information_Dialog.xml";
	public final static String QUESTION_YESNO_DIALOG = "Question_YesNo_Dialog.xml";
	public final static String QUESTION_YESNOCANCEL_DIALOG = "Question_YesNoCancel_Dialog.xml";
	public final static String ERROR_DIALOG = "Error_Dialog.xml";
	
	//panel for manage single object
	public final static String INFORMATION_SINGLE_OBJECT_DIALOG = "Information_Single_Object_Dialog.xml";
	
	public final static String LOADINGGAME_PANEL = "LoadingGame_Panel.xml"; 
	
	
	public EntryEventManager() {
		init();
	}
	
	private void init() {
		hmEventAction.put(GAME_MAINMENU_DIALOG, Entry.MENU);
		hmEventAction.put(EDITOR_MAINMENU_DIALOG, Entry.MENU);
		hmEventAction.put(GAME_MENU, Entry.MENU);
		
		hmEventAction.put(EDITOR_MENU, Entry.MENU);
		hmEventAction.put(CREDITSGAME_MENU, Entry.MENU);
		hmEventAction.put(CONFIGURATIONGAME_MENU, Entry.MENU);
		hmEventAction.put(CUSTOMGAME_MENU, Entry.MENU);
		hmEventAction.put(LOADGAME_MENU, Entry.MENU);
		hmEventAction.put(EDITOR_NEW_MAP_MENU, Entry.EDITOR);
		hmEventAction.put(EDITOR_LOAD_MAPS_MENU, Entry.MENU);
		hmEventAction.put(EDITOR_SAVEMAP_DIALOG, Entry.DIALOG);
		hmEventAction.put(INFORMATION_DIALOG, Entry.DIALOG);
		hmEventAction.put(ERROR_DIALOG, Entry.DIALOG);
		hmEventAction.put(QUESTION_YESNO_DIALOG, Entry.DIALOG);
		hmEventAction.put(QUESTION_YESNOCANCEL_DIALOG, Entry.DIALOG);
		
		hmEventAction.put(INFORMATION_SINGLE_OBJECT_DIALOG, Entry.GAME);
		
		hmEventAction.put(LOADINGGAME_PANEL, Entry.NONE);
		
	}
	
	
	public Entry getActionEntry(String name) {
		
		Entry entry = Entry.NONE;
		if (hmEventAction.containsKey(name)) {
			entry = hmEventAction.get(name);
		} else {
			
			try {
				throw new Exception("empty key for hmEventAction");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return entry;
	}
	
}
