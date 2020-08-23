package com.bwizard.cegame.controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.bwizard.cegame.camera.CameraMapInfo;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.device.interfaces.ICursorController;
import com.bwizard.cegame.device.interfaces.ICursorModel;
import com.bwizard.cegame.device.interfaces.IKeysController;
import com.bwizard.cegame.device.interfaces.IKeysModel;
import com.bwizard.cegame.device.model.CursorInfo;
import com.bwizard.cegame.device.view.CursorAppearance;
import com.bwizard.cegame.device.view.KeysView;
import com.bwizard.cegame.device.view.MouseView;
import com.bwizard.cegame.documents.interfaces.IPanelLayoutManager;
import com.bwizard.cegame.documents.providers.ConfigurationProvider;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.state.handlers.Entry;
import com.bwizard.cegame.thread.ThreadInfo;
import com.bwizard.cegame.thread.ThreadScheduler;
import com.bwizard.cegame.thread.ThreadStatus;
import com.bwizard.cegame.time.MonitorTime;
import com.bwizard.cegame.tools.DrawManager;
import com.bwizard.cegame.window.BaseWindowGame;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;
import com.bwizard.cegame.world.map.MapBackgroundManager;
import com.bwizard.cegame.world.map.WorldMapInfo;

/**
 * This class represents whole map for updating position and painting
 * @author Krzysztof Majka
 * @version 1.0
 */
public class BaseWorldGame extends Canvas {
	
	private static final long serialVersionUID = -5880974255843436127L;
	
	private static final int FRAME_DELAY = 10;
	private ThreadScheduler threadScheduler = null;
	private ThreadInfo threadInfo = null;

	private IWindowScreen windowScreen = null;
	
	protected WorldObjectManager worldObjectManager = null;
	protected UserViewManager userViewManager = null;
	protected StateInfoGame stateInfoGame = null;
	protected MapBackgroundManager mapBackgroundManager = null;
	private BaseWindowGame baseWindowGame = null;
	private ConfigurationProvider configurationProvider = null;
	protected ICursorController cursorController = null;
	protected IKeysController keysController = null;
	public CleanerManager cleanerManager = null;
	protected MouseView mouseView = null;
	protected KeysView keysView = null;
	protected CursorAppearance gameCursor = null;
	protected IKeysModel keysModel = null;
	protected ICursorModel cursorModel = null;
	private Entry currentEntry = Entry.NONE;

	/*
	 * This constructor runs thread for world
	 * @param mapInfo The value describes information about the map in world
	 * @param gameWorld The value describes custom world created by user
	 */
	public BaseWorldGame(final IWindowScreen windowScreen, final StateInfoGame stateInfoGame,
			BaseWindowGame baseWindowGame, ThreadScheduler threadScheduler) {
				
		this.windowScreen = windowScreen;
		
		this.baseWindowGame = baseWindowGame;
		
		this.threadInfo = new ThreadInfo(ThreadStatus.RUN);	
		
		this.stateInfoGame = stateInfoGame;
		this.stateInfoGame.setThreadInfo(this.threadInfo);
		
		this.worldObjectManager = new WorldObjectManager();
		
		this.userViewManager = new UserViewManager();
		worldObjectManager.setUserViewManager(userViewManager);
		
		this.threadScheduler = threadScheduler;
		
		this.cleanerManager = new CleanerManager();
		
	}

	public void setPanelLayoutManager(IPanelLayoutManager panelLayoutManager) {
		
		getMapBackgroundManager().setPanelLayoutManager(panelLayoutManager);
		stateInfoGame.setPanelLayoutManager(panelLayoutManager);
		cleanerManager.setPanelLayoutManager(panelLayoutManager);
	}
	
	public void run(final IWindowScreen mainWindow) {
				
		createBufferStrategy(2);
		requestFocus();
		final BufferStrategy bufferStrategy = getBufferStrategy();
		
		threadScheduler.setTask( new Runnable() {
			
			@Override
			public void run() {
				
				stateInfoGame.getMonitorTime().start();
				
				//Game loop
				while(threadInfo.getThreadStatus() != ThreadStatus.EXITED) {
					
					threadInfo.updateThreadStatus();
					
					//SUSPEND is use for hold all operations during reload data from menu
					if (threadInfo.getThreadStatus() != ThreadStatus.SUSPEND) {
						
						if (threadInfo.getThreadStatus() != ThreadStatus.PAUSED) {
												
							stateInfoGame.getMonitorTime().refresh();
						} 
						
						// paint tiles into terrain, paint objects (arc, rectanagle ...) into game, displayed selected rectangle, paint the user panels
						updateGamedState(bufferStrategy, threadInfo.getThreadStatus());
					}
					
					cleanUnusedResources();
					
					sleep();
					
				}
				mainWindow.clean();
				
				//update hidden parameter (e.g. class GamePanelHandler => WindowGameName.EXIT or WindowGameName.REOPEN) to skip problem with 2 threads
				baseWindowGame.refresh();
			}
		});
	}

	private void sleep() {
		try {
			Thread.sleep(FRAME_DELAY);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clear() {
	}
	
	public void loadData(String filePath) {
	}
	
	/**
	 * This method gets map info.
	 */
	public WorldMapInfo getWorldMapInfo() {
		return stateInfoGame.getWorldMapInfo();
	}
	
	public void updateAndWaitThreadStatus(ThreadStatus threadSts) {
		threadInfo.setThreadStatus(threadSts);
		threadInfo.waitForUpdateThreadStatus();
	}
	
	protected CursorAppearance getCursorAppearance() {
		return null;
	}
	
	public void addEventAction(Entry entry) {
	}
	
	/**
	 * This method get main screen window 
	 * @return simpleScreenManager The value for main screen window.
	 */
	public IWindowScreen getWindowScreen() {
		return windowScreen;
	}
	
	/**
	 * This method updates position for figures laying into the map taking into account that there is panel layout into games.
	 */
	public void updatePositionDependingOnLayout() {
				
		//set info about center camera
		stateInfoGame.getCameraMapInfo().setCenterPositionX(stateInfoGame.getViewLayout().getLeftBorderLayout());
		stateInfoGame.getCameraMapInfo().setCenterPositionY(stateInfoGame.getViewLayout().getTopBorderLayout());
		
		//set camera in specific position depending on layout
		//stateInfoGame.getCameraMapInfo().updateCameraX(stateInfoGame.getCameraMapInfo().getCameraX());// + stateInfoGame.getViewLayout().getLeftBorderLayout());
		//stateInfoGame.getCameraMapInfo().updateCameraY(stateInfoGame.getCameraMapInfo().getCameraY());// + stateInfoGame.getViewLayout().getTopBorderLayout());
	}
		
	private void cleanUnusedResources() {
		cleanerManager.removeUnusedResources();
	}
	
	/**
	 * This method paints all objects into screen games.
	 * @param elapsedTime This variable provide information about elapsed time in game - since the start of the game.
	 */
	private void updateGamedState(BufferStrategy bufferStrategy, ThreadStatus threadStatus) {

		if (threadInfo.getThreadStatus() != ThreadStatus.PAUSED) {
			stateInfoGame.getCameraMapInfo().AutoCameraInvoke();
			updateWorld(worldObjectManager.getAllObjects());
			userViewManager.updateUserViewProperties(worldObjectManager.getAllObjects());
		}
		
		Graphics g = bufferStrategy.getDrawGraphics();
		
		((Graphics2D)g).setRenderingHint(
				        RenderingHints.KEY_TEXT_ANTIALIASING,
				        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		// paint tiles into terrain
		paintBackgroundInUserView(g);
		// paint objects into game
		paintFiguresInUserView(g, worldObjectManager);
		// displayed selected rectangle
		paintSelected(g);

		// paint the user panels -> have to be displayed at last method for painting
		stateInfoGame.getPanelLayoutManager().paintPanel(g);
		g.dispose();

		if (!bufferStrategy.contentsLost()) {
			bufferStrategy.show();
		}

	}
	
	protected void paintSelected(Graphics g) {
		
		DrawManager.drawRect(g, stateInfoGame.getCursorInfo().getStartScreenSelectionX(), stateInfoGame.getCursorInfo().getStartScreenSelectionY(),
				stateInfoGame.getCursorInfo().getEndScreenSelectionX(), stateInfoGame.getCursorInfo().getEndScreenSelectionX(), Color.YELLOW);
	}
	
	
	public String displayInfoTime(long time) {
		long second = TimeUnit.MILLISECONDS.toSeconds(time) % 60;
		long minute = TimeUnit.MILLISECONDS.toSeconds(time) / 60;
		return (minute > 0 ? String.valueOf(minute) + " min. ":  "") + String.valueOf(second) +" sec. ";
	}
	
	public CameraMapInfo getCameraMapInfo() {
		return stateInfoGame.getCameraMapInfo();
	}
	
	public CursorInfo getCursorInfo() {
		return stateInfoGame.getCursorInfo();
	}
	
	public StateInfoGame getStateInfoGame() {
		return stateInfoGame;
	}
	
	@Override
	public void setCursor(Cursor cursor) {
		super.setCursor(cursor);
	}
	
	public void waitForRestartApplication() {
		updateAndWaitThreadStatus(ThreadStatus.EXITED);
	}
	
	public void waitForExitApplication() {
		updateAndWaitThreadStatus(ThreadStatus.EXITED);
	}
	
	public void waitForSuspendApplication() {
		updateAndWaitThreadStatus(ThreadStatus.SUSPEND);
	}
	
	public void waitForPauseApplication() {
		updateAndWaitThreadStatus(ThreadStatus.PAUSED);
	}
		
	public void waitForStartApplication() {
		updateAndWaitThreadStatus(ThreadStatus.RUN);
	}
		
	public void addFigure(BaseFigure baseFigure) {
		worldObjectManager.addFigureToAllObject(baseFigure);
	}
	
	public IPanelLayoutManager getPanelLayoutManager() {
		return this.stateInfoGame.getPanelLayoutManager();
	}
	
	/**
	 * @override
	 * This method paints world in specific games.
	 * @param g This variable provide graphics bufferStrategy tool.
	 * @param listPoint The information about all objects added to map by clicked mouse.
	 * @return Nothing.
	 */
	protected void paintFiguresInUserView(Graphics g, WorldObjectManager worldObjectManager) {
	}
	
	/**
	 * This method updates world(calculations) in games.
	 * @param worldObjectManager This variable information about all objects in the world.
	 */
	protected void updateWorld(List<BaseFigure> allObjects) {
	}
	
	/**
	 * @override
	 * This method paints layout from management panel 
	 * @param g This variable provide graphics bufferStrategy tool.
	 * @param stateInfoGame
	 */
	protected void paintBackgroundInUserView(Graphics g) {
	}
	
	public WorldObjectManager getWorldObjectManager() {
		return worldObjectManager;
	}

	public MapBackgroundManager getMapBackgroundManager() {
		return mapBackgroundManager;
	}
	
	public BaseWindowGame getBaseWindowGame() {
		return baseWindowGame;
	}

	public ConfigurationProvider getConfigurationProvider() {
		return configurationProvider;
	}

	public void setConfigurationProvider(ConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}
	
	public BaseDrawFigure getElementByTag(String tag) {
		ArrayList<BaseDrawFigure> list = getStateInfoGame().getPanelLayoutManager().getElementsByTag(tag);
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			
			try {
				throw new Exception("There is no tag "+tag+"  element.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public void changeTagElement(String oldTag, String newTag) throws Exception {
		ArrayList<BaseDrawFigure> list = getStateInfoGame().getPanelLayoutManager().getElementsByTag(oldTag);
		if (list != null && list.size() == 1) {
			list.get(0).setTag(newTag);
		} else {
			throw new Exception("There is no tag "+oldTag+" element for change.");
		}
	}
	
	public void setTextForTagElement(String tag, String text) throws Exception {
		ArrayList<BaseDrawFigure> list = getStateInfoGame().getPanelLayoutManager().getElementsByTag(tag);
		if (list != null && list.size() == 1) {
			list.get(0).setText(text);
		} else {
			throw new Exception("There is no tag "+tag+" element for set text.");
		}
	}

	public Entry getCurrentEntry() {
		return currentEntry;
	}

	public void setCurrentEntry(Entry currentEntry) {
		this.currentEntry = currentEntry;
	}
	
	public boolean isPausedGame() {
		return threadInfo.getThreadStatus() == ThreadStatus.PAUSED;
	}
	
	public boolean isRunGame() {
		return threadInfo.getThreadStatus() == ThreadStatus.RUN;
	}
	
	public boolean isSuspendGame() {
		return threadInfo.getThreadStatus() == ThreadStatus.SUSPEND;
	}

}
