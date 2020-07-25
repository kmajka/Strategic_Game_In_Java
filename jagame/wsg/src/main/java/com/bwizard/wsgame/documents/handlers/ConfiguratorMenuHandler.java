package com.bwizard.wsgame.documents.handlers;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.interfaces.IEventHandler;
import com.bwizard.cegame.logs.LogInfo;

public class ConfiguratorMenuHandler extends BaseHandler implements IEventHandler {

	private static final LogInfo logInfo = new LogInfo(ConfiguratorMenuHandler.class);

	public ConfiguratorMenuHandler(BaseWorldGame baseWorldGame) {
		super(baseWorldGame);
	}

	@Override
	public void invokeEvent(Object sender, String arg0) {

		logInfo.info("start: invokeEvent(sender, "+ arg0 + ")");

		logInfo.info("end: invokeEvent(sender, "+ arg0 + ")");

	}

}
