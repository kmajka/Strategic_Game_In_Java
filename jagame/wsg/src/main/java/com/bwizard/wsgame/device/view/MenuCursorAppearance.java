package com.bwizard.wsgame.device.view;

import com.bwizard.wsgame.parameters.GameGlobals;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.model.CursorAppearanceInfo;
import com.bwizard.cegame.device.view.CursorAppearance;
import com.bwizard.cegame.utils.ImageUtil;


public class MenuCursorAppearance extends CursorAppearance {
	
	private Cursor loadCursor(String file)  {
		Image image = ImageUtil.loadImage(GameGlobals.IMAGE_PATH + file);
		return Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0,0), file);
	}

	@Override
	protected void initialize() {

		addCursor(CursorName.DOWN_CURSOR, new CursorAppearanceInfo(loadCursor("c_down_cursor.gif")));
		addCursor(CursorName.LEFT_CURSOR, new CursorAppearanceInfo(loadCursor("c_left_cursor.gif")));
		addCursor(CursorName.LEFT_DOWN_CURSOR, new CursorAppearanceInfo(loadCursor("c_left_down_cursor.gif")));
		addCursor(CursorName.LEFT_UP_CURSOR, new CursorAppearanceInfo(loadCursor("c_left_up_cursor.gif")));
		addCursor(CursorName.RIGHT_CURSOR, new CursorAppearanceInfo(loadCursor("c_right_cursor.gif")));
		addCursor(CursorName.RIGHT_DOWN_CURSOR, new CursorAppearanceInfo(loadCursor("c_right_down_cursor.gif")));
		addCursor(CursorName.RIGHT_UP_CURSOR, new CursorAppearanceInfo(loadCursor("c_right_up_cursor.gif")));
		addCursor(CursorName.UP_CURSOR, new CursorAppearanceInfo(loadCursor("c_up_cursor.gif")));
		addCursor(CursorName.MOVE_CURSOR, new CursorAppearanceInfo(loadCursor("c_move_cursor.png")));
		addCursor(CursorName.HAND_CURSOR, new CursorAppearanceInfo(loadCursor("c_hand_cursor.png")));
	}
	
}
