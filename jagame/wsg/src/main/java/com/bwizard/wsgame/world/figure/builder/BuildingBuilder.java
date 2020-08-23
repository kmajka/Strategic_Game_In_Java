package com.bwizard.wsgame.world.figure.builder;

import com.bwizard.wsgame.documents.names.CmnObjectName;
import com.bwizard.wsgame.world.figure.buildings.SimpleCastle;
import com.bwizard.cegame.figure.BaseFigure;

public class BuildingBuilder extends FigureBuilder {

	@Override
	protected BaseFigure composeFigure(String type) {
		BaseFigure baseFigure = null;
		
		if (CmnObjectName.CASTLE_1.equals(type)) {
			int x = stateInfoGame.getCameraMapInfo().getRealCameraX() + (stateInfoGame.getViewLayout().getViewWidth() / 2);
			int y = stateInfoGame.getCameraMapInfo().getRealCameraY() + (stateInfoGame.getViewLayout().getViewHeight() /2);
			baseFigure = new SimpleCastle(x, y, stateInfoGame );
		}
		
		
		return baseFigure;
	}
	
}
