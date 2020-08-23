package com.bwizard.wsgame.world.figure.builder;

import com.bwizard.wsgame.documents.names.CmnObjectName;
import com.bwizard.wsgame.world.figure.nature.SimpleTree;
import com.bwizard.cegame.figure.BaseFigure;

public class NatureBuilder extends FigureBuilder {

	@Override
	protected BaseFigure composeFigure(String type) {
		BaseFigure baseFigure = null;
		
		if (CmnObjectName.TREE_1.equals(type)) {
			int x = stateInfoGame.getCameraMapInfo().getRealCameraX() + (stateInfoGame.getViewLayout().getViewWidth() / 2);
			int y = stateInfoGame.getCameraMapInfo().getRealCameraY() + (stateInfoGame.getViewLayout().getViewHeight() /2);
			baseFigure = new SimpleTree(x, y, stateInfoGame );
		}
		
		
		return baseFigure;
	}
	
}
