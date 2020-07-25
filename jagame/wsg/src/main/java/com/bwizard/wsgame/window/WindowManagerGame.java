package com.bwizard.wsgame.window;

import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.thread.ThreadScheduler;
import com.bwizard.cegame.window.handlers.WindowGameActionName;

public class WindowManagerGame {

	private WindowProviderGame windowProviderMenu = null;
	private ThreadScheduler threadScheduler = null;
	private static final LogInfo logInfo = new LogInfo(WindowManagerGame.class);
	
	public WindowManagerGame() {
	}

	public void runGame() {
		invokeGameInOneThread();
	}

	//displayed game in one thread
	public void invokeGameInOneThread() {
		threadScheduler = new ThreadScheduler(1);		
		windowProviderMenu = new WindowProviderGame(threadScheduler);
		windowProviderMenu.invoke("Game_Menu.xml");
	}
	
	//displayed game in one thread and configuration is controlled by second thread
	public void invokeGameInMoreThreads() {
		
		threadScheduler = new ThreadScheduler(5);		
		windowProviderMenu = new WindowProviderGame(threadScheduler);
		
		threadScheduler.setTask( new Runnable() {
			
			@Override
			public void run() {
				
				logInfo.info("start: invoke()");
				
				boolean firstRun = true;
				while(!WindowGameActionName.EXIT.equals(windowProviderMenu.getBaseWindowGame().getActionName())) {
					
		            try {
		            	//it will be use only for restart application
		            	if (WindowGameActionName.REOPEN.equals(windowProviderMenu.getBaseWindowGame().getActionName())) {	
		            		if (firstRun) {
		            			windowProviderMenu.invoke("Game_Menu.xml");
		            			firstRun = false;
		            		} else {
		            			windowProviderMenu.invoke("ConfigurationGame_Menu.xml");
		            		}
		            		windowProviderMenu.getBaseWindowGame().setActionName(WindowGameActionName.NONE);
		            		
		            	}

		        		Thread.sleep(500);
		                
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		           
		        }
		        
				threadScheduler.close();
				
				logInfo.info("end: invoke()");
		        System.exit(0);
			}
		});
	}
    
}
