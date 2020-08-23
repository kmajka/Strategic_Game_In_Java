package com.bwizard.wsgame.world.figure.items;

import com.bwizard.wsgame.world.figure.GameFigure;

import com.bwizard.cegame.state.StateInfoGame;

public class SimpleFire extends GameFigure {
	
	public SimpleFire() {	
		initialize();
	}
	
	public SimpleFire(int x, int y, StateInfoGame stateInfoGame) {
		super(x , y, stateInfoGame);
		initialize();

	}
	
	private void initialize() {
		long timeFire= 1000;
		
		frameManager.addFrame(getImageSprite("f_fire_1.png"), timeFire);
		frameManager.addFrame(getImageSprite("f_fire_2.png"), timeFire);
	}
}
