package com.bwizard.cegame.configuration.components.handlers;

import com.bwizard.cegame.configuration.components.interfaces.IFigureConfiguration;
import com.bwizard.cegame.controls.components.BaseDrawFigure;

public class FigureHandler implements IFigureConfiguration {

	private BaseDrawFigure figure;

	@Override
	public BaseDrawFigure getFigure() {
		return figure;
	}

	@Override
	public void setFigure(BaseDrawFigure figure) {
		this.figure = figure;
		
	}


}
