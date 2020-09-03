package com.bwizard.wsgame.documents.names;


import com.bwizard.cegame.validation.interfaces.IValidField;

/**
 * This class stores all components name like button/checkbox/list/etc. used commonly both in editor and game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CmnComponentName implements IValidField {
	
	public static final String QUESTION_BUTTON_NO = "QUESTION_BUTTON_NO";
	public static final String QUESTION_BUTTON_YES = "QUESTION_BUTTON_YES";
	public static final String QUESTION_YESNO_DIALOG_TEXT = "QUESTION_YESNO_DIALOG_TEXT";
	
	public static final String INFORMATION_BUTTON_OK = "INFORMATION_BUTTON_OK";
	public static final String INFORMATION_DIALOG_TEXT = "INFORMATION_DIALOG_TEXT";
	
	public static final String QUESTION_EXIT_GAME = "Do you really want to exit ? You will lose all unsaved changes.";	
}
