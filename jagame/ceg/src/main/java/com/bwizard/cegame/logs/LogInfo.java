package com.bwizard.cegame.logs;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class LogInfo {

	private Logger logger = null;

	//TRACE -> DEBUG -> INFO -> WARN -> ERROR
	public LogInfo(Class<?> clazz) {
		logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(clazz);
		logger.setLevel(Level.INFO);
	}

	public void info(String message) {
		logger.info(message);
	}
	
	public void error(String message) {
		logger.error(message);
	}

}
