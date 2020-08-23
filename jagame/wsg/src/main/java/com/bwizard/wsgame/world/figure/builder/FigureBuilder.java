package com.bwizard.wsgame.world.figure.builder;

import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.state.StateInfoGame;

public abstract class FigureBuilder {
	
	protected StateInfoGame stateInfoGame = null;
	
	public StateInfoGame getStateInfoGame() {
		return stateInfoGame;
	}

	public void setStateInfoGame(StateInfoGame stateInfoGame) {
		this.stateInfoGame = stateInfoGame;
	}

	protected abstract BaseFigure composeFigure(String type);
	
	public BaseFigure build(String type) {
		BaseFigure baseFigure = composeFigure(type);
		return baseFigure;
	}
}
