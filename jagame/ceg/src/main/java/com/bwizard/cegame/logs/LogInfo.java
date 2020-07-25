package com.bwizard.cegame.logs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.bwizard.cegame.tools.FileManager;

public class LogInfo {

	private Logger log = null;
	
	public LogInfo(Class<?> clazz) {
		log = Logger.getLogger(clazz);
	}
	
	public static void configure(String pathFile) {
		
		FileManager fileManager = new FileManager(pathFile);
		InputStream is = fileManager.getInputStream();
		Properties pr = new Properties();
		try {
			pr.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PropertyConfigurator.configure(pr);
	}

	public void info(String message) {
		log.info(message);
	}
	
	public void error(String message) {
		log.error(message);
	}

}
