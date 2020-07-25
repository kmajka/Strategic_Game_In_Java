package com.bwizard.wsgame.world.figure.builder;

import com.bwizard.wsgame.documents.names.CmnObjectName;
import com.bwizard.wsgame.world.figure.characters.SimpleKnight;
import com.bwizard.cegame.figure.ActivityBaseFigure;
import com.bwizard.cegame.figure.BaseFigure;

public class CharacterBuilder extends FigureBuilder {

	@Override
	protected BaseFigure composeFigure(String type) {
		BaseFigure baseFigure = null;
		
		if (CmnObjectName.KNIGHT_1.equals(type)) {
			int x = stateInfoManager.getCameraMapInfo().getRealCameraX() + (stateInfoManager.getViewLayout().getViewWidth() / 2);
			int y = stateInfoManager.getCameraMapInfo().getRealCameraY() + (stateInfoManager.getViewLayout().getViewHeight() /2);
			baseFigure = new SimpleKnight(x, y, stateInfoManager );
			baseFigure.setActivityFigure(ActivityBaseFigure.MOVE_RIGHT);
		}
		
		
		return baseFigure;
	}
	
}
