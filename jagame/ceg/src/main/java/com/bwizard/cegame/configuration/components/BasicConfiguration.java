package com.bwizard.cegame.configuration.components;

import com.bwizard.cegame.configuration.components.handlers.BasicHandler;
import com.bwizard.cegame.configuration.components.interfaces.IBasicConfiguration;

public class BasicConfiguration extends BaseConfiguration {

	public BasicConfiguration() {
		basicConfiguration = new BasicHandler();
	}
	
	public IBasicConfiguration getData() {
		return basicConfiguration;
	}
}
