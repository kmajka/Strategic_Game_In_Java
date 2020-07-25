package com.bwizard.cegame.configuration.components;

import com.bwizard.cegame.configuration.components.handlers.BasicHandler;
import com.bwizard.cegame.configuration.components.handlers.MapHandler;

public class MapConfiguration extends BaseConfiguration {

	public MapConfiguration() {
		basicConfiguration = new BasicHandler();
		mapConfiguration = new MapHandler();
	}
	
}
