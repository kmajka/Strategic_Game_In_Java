package com.bwizard.cegame.configuration.components;

import com.bwizard.cegame.configuration.components.handlers.KeyHandler;

public class KeyConfiguration extends BaseConfiguration {

	public KeyConfiguration() {
		keyConfiguration = new KeyHandler();
	}
}
