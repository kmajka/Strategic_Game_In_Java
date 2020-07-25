package com.bwizard.cegame.logs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogMonitor {

	public static void buildInfo(LogInfo logInfo, Exception ex) {
		
		try (StringWriter result = new StringWriter()) {
		    try (PrintWriter printWriter = new PrintWriter(result)) {
			    ex.printStackTrace(printWriter);
			    logInfo.error(result.toString());
		    }
		} catch(IOException ex1) {
			ex1.printStackTrace();
		}
	}
	
	public static void buildInfo(LogInfo logInfo, Exception ex, String message) {
		
		try (StringWriter result = new StringWriter()) {
		    try (PrintWriter printWriter = new PrintWriter(result)) {
			    ex.printStackTrace(printWriter);
			    logInfo.error(result.toString() + " " + message);
		    }
		} catch(IOException ex1) {
			ex1.printStackTrace();
		}
	}
	
	public static void buildInfo(LogInfo logInfo, String message) {		
		logInfo.error(message);
	}
}
