package com.bwizard.cegame.configuration.components;

import com.bwizard.cegame.configuration.components.handlers.BasicHandler;
import com.bwizard.cegame.configuration.components.handlers.CellHandler;

public class CellConfiguration extends BaseConfiguration {

	public CellConfiguration() {
		basicConfiguration = new BasicHandler();
		cellConfiguration = new CellHandler();
	}
}
