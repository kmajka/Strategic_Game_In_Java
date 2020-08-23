package com.bwizard.wsgame.world.figure.buildings;

import com.bwizard.wsgame.world.figure.GameFigure;
import com.bwizard.cegame.state.StateInfoGame;

public class Box extends GameFigure {
	
	public Box() {
		initialize();
	}
	
	public Box(int x, int y, StateInfoGame stateInfoGame) {
		super(x , y, stateInfoGame);
		initialize();
	}
	
	private void initialize() {
		
		long time= 100;
		
		frameManager.addFrame(getImageSprite("box.png"), time);
	}
	
}
