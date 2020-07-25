package com.bwizard.wsgame.world.figure.builder;

import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.state.StateInfoManager;

public abstract class FigureBuilder {
	
	protected StateInfoManager stateInfoManager = null;
	
	public StateInfoManager getStateInfoManager() {
		return stateInfoManager;
	}

	public void setStateInfoManager(StateInfoManager stateInfoManager) {
		this.stateInfoManager = stateInfoManager;
	}

	protected abstract BaseFigure composeFigure(String type);
	
	public BaseFigure build(String type) {
		BaseFigure baseFigure = composeFigure(type);
		return baseFigure;
	}
}
