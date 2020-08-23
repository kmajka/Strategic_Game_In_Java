package com.bwizard.wsgame.world.figure.nature;

import com.bwizard.wsgame.parameters.GameGlobals;
import com.bwizard.wsgame.world.figure.GameFigure;
import com.bwizard.wsgame.world.figure.handlers.StoneType;

import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.utils.ImageUtil;

public class SimpleStone extends GameFigure {
	
	public SimpleStone(StoneType stoneType) {	
		initialize(stoneType);
	}
	
	public SimpleStone(int x, int y, StateInfoGame stateInfoGame, StoneType stoneType) {
		super(x , y, stateInfoGame);
		initialize(stoneType);
	}
	
	private void initialize(StoneType stoneType) {
		
		canFocus(true);
		
		switch (stoneType) {
			case STONE_1:
				frameManager.addFrame(ImageUtil.loadImage(GameGlobals.IMAGE_PATH + "f_stone_1.png"), 0);
				break;
			case STONE_2:
				frameManager.addFrame(ImageUtil.loadImage(GameGlobals.IMAGE_PATH + "mountain.png"), 0);
				break;
			default:
				break;
			
		}
	}

}
