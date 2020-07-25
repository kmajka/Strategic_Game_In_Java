package com.bwizard.cegame.configuration.components;

import com.bwizard.cegame.configuration.components.handlers.BasicHandler;
import com.bwizard.cegame.configuration.components.handlers.CameraHandler;

public class CameraConfiguration extends BaseConfiguration {

	public CameraConfiguration() {
		basicConfiguration = new BasicHandler();
		cameraConfiguration = new CameraHandler();
	}
	
}
