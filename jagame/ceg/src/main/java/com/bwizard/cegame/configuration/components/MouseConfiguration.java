package com.bwizard.cegame.configuration.components;

import com.bwizard.cegame.configuration.components.handlers.MouseHandler;

public class MouseConfiguration extends BaseConfiguration {

	public MouseConfiguration() {
		mouseConfiguration = new MouseHandler();
	}
}
