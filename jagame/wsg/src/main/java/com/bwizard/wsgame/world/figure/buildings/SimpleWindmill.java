package com.bwizard.wsgame.world.figure.buildings;

import com.bwizard.wsgame.world.figure.GameFigure;
import com.bwizard.cegame.state.StateInfoGame;

public class SimpleWindmill extends GameFigure {
	
	public SimpleWindmill() {
		initialize();
	}
	
	public SimpleWindmill(int x, int y, StateInfoGame stateInfoGame) {
		super(x , y, stateInfoGame);
		initialize();
	}
	
	private void initialize() {
		
		long time= 100;
		
		frameManager.addFrame(getImageSprite("f_windmill_1.png"), time);
		frameManager.addFrame(getImageSprite("f_windmill_2.png"), time);
		frameManager.addFrame(getImageSprite("f_windmill_3.png"), time);
		frameManager.addFrame(getImageSprite("f_windmill_4.png"), time);
		frameManager.addFrame(getImageSprite("f_windmill_5.png"), time);
	}
	
}
