package com.bwizard.wsgame.world.figure.nature;

import com.bwizard.wsgame.world.figure.GameFigure;

import com.bwizard.cegame.state.StateInfoGame;

public class SimpleFlower extends GameFigure {
	
	public SimpleFlower() {
		initialize();
	}
	
	public SimpleFlower(int x, int y, StateInfoGame stateInfoGame) {
		super(x , y, stateInfoGame);
		initialize();
	}
	
	private void initialize() {
		frameManager.addFrame(getImageSprite("f_flower_1.png"), 0);
	}
	
}

