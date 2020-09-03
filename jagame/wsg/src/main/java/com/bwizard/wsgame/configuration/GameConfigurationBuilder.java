package com.bwizard.wsgame.configuration;

import com.bwizard.wsgame.controller.CustomWorldGame;
import com.bwizard.wsgame.documents.CustomPanelLayoutManager;
import com.bwizard.wsgame.documents.layout.DocumentGamePanelLayout;
import com.bwizard.wsgame.parameters.GameGlobals;

import java.awt.EventQueue;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.documents.PanelLayoutManager;
import com.bwizard.cegame.documents.layout.DocumentPanelLayout;
import com.bwizard.cegame.documents.providers.ConfigurationProvider;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.state.handlers.Entry;
import com.bwizard.cegame.thread.ThreadScheduler;
import com.bwizard.cegame.window.BaseWindowGame;
import com.bwizard.cegame.window.handlers.WindowGameActionName;
import com.bwizard.cegame.window.layout.SimpleWindowLayout;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;
import com.bwizard.cegame.window.screen.provider.FullScreenProvider;
import com.bwizard.cegame.window.screen.provider.SimpleScreenProvider;


/**
 * This class creates all needed objects to run game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class GameConfigurationBuilder {
	
	//member to send/receive some parameters from thread
	private BaseWindowGame baseWindowGame;
	private ThreadScheduler threadScheduler = null;
	private static final LogInfo logInfo = new LogInfo(GameConfigurationBuilder.class);
	
	public GameConfigurationBuilder(ThreadScheduler threadScheduler) {
		this.threadScheduler = threadScheduler;
		baseWindowGame = new BaseWindowGame(WindowGameActionName.REOPEN);
	}
	
	public void invoke(String layoutName) {
		
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				logInfo.info("start: invoke()");
				
				try {
					//create main window (e.g. simple screen) with title
					IWindowScreen mainWindow = null;

					//1.SetGeneralConfiguration()
					ConfigurationProvider configurationProvider = new ConfigurationProvider(GameGlobals.CONFIG_DATA + "GameConfiguration.xml");
					configurationProvider.loadData();	

					//2.SetWindowScreen
					if (configurationProvider.getVideoConfiguration().isWindowedMode()) {
						mainWindow = new SimpleScreenProvider(configurationProvider);
					} else {
						mainWindow = new FullScreenProvider(configurationProvider);
					}
					
					//configurationProvider.getVideoConfiguration().getImageDpi();

					StateInfoGame stateInfoGame = new StateInfoGame();
					stateInfoGame.setWindowScreen(mainWindow);
					
					//map->Canvas
					BaseWorldGame baseWorldGame = new CustomWorldGame(mainWindow, stateInfoGame, baseWindowGame, threadScheduler);
					baseWorldGame.setConfigurationProvider(configurationProvider);
					//create layout interface
					DocumentPanelLayout documentPanelLayout = new DocumentGamePanelLayout(baseWorldGame);
					
					//EditorLayout.xml
					//GameLayout.xml
					PanelLayoutManager panelLayoutManager = new CustomPanelLayoutManager(baseWorldGame, documentPanelLayout, configurationProvider);
					panelLayoutManager.setLayoutName(layoutName);
					
					panelLayoutManager.loadData();
					
					baseWorldGame.setPanelLayoutManager(panelLayoutManager);

					//set user view layout
					stateInfoGame.setViewLayout(documentPanelLayout);
					stateInfoGame.getCameraMapInfo().setViewLayout(documentPanelLayout);
					
					//create main Layout for window
					SimpleWindowLayout simpleWindowLayout = new SimpleWindowLayout(baseWorldGame);
					mainWindow.setLayout(simpleWindowLayout);
					
					//add information about events to manage e.g. mouse ,keys
					baseWorldGame.addEventAction(Entry.MENU);
					
					//displaying main window
					mainWindow.createGUI();
					
					//starts main thread
					baseWorldGame.run(mainWindow);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logInfo.info("exit: invoke()");
					
			}
			
		});
	}

	public BaseWindowGame getBaseWindowGame() {
		return baseWindowGame;
	}
		
}
