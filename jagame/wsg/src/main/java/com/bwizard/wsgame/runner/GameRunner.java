package com.bwizard.wsgame.runner;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.documents.PanelLayoutManager;
import com.bwizard.cegame.documents.interfaces.IPanelLayoutManager;
import com.bwizard.cegame.documents.layout.GamePanelLayout;
import com.bwizard.cegame.documents.providers.ConfigurationProvider;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.state.handlers.Entry;
import com.bwizard.cegame.window.layout.SimpleWindowLayout;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;
import com.bwizard.cegame.window.screen.provider.ScreenWindowFactory;
import com.bwizard.wsgame.controller.CustomWorldGame;
import com.bwizard.wsgame.documents.CustomPanelLayoutManager;
import com.bwizard.wsgame.documents.layout.CustomPanelLayout;
import com.bwizard.wsgame.parameters.GameGlobals;

import java.awt.*;

/**
 * This class creates all needed objects to run game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class GameRunner {

	private static final LogInfo logInfo = new LogInfo(GameRunner.class);
	private final ConfigurationProvider configurationProvider;

	public GameRunner() {
		configurationProvider = new ConfigurationProvider(GameGlobals.CONFIG_DATA + "GameConfiguration.xml");
		configurationProvider.loadData();
	}

	private IWindowScreen getWindow() {
		ScreenWindowFactory windowFactory = new ScreenWindowFactory(configurationProvider);
		return windowFactory.createWindow();
	}

	private IPanelLayoutManager getMenuLayout(BaseWorldGame baseWorldGame, GamePanelLayout gamePanelLayout) throws Exception {
		PanelLayoutManager panelLayoutManager = new CustomPanelLayoutManager(baseWorldGame, gamePanelLayout, configurationProvider);
		panelLayoutManager.setLayoutName("Game_Menu.xml");
		panelLayoutManager.loadData();
		return panelLayoutManager;
	}

	public void start() {

		EventQueue.invokeLater(() -> {
			logInfo.info("start: invoke()");

			try {
				IWindowScreen mainWindow = getWindow();
				StateInfoGame stateInfoGame = new StateInfoGame(mainWindow);

				//prepare map->Canvas
				BaseWorldGame baseWorldGame = new CustomWorldGame(stateInfoGame);

				//create layout interface
				GamePanelLayout gamePanelLayout = new CustomPanelLayout(baseWorldGame);

				//EditorLayout.xml
				//GameLayout.xml
				IPanelLayoutManager panelLayoutManager = getMenuLayout(baseWorldGame, gamePanelLayout);
				baseWorldGame.setPanelLayoutManager(panelLayoutManager);

				//set user view layout
				stateInfoGame.setViewLayout(gamePanelLayout);

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
		});
	}
}
