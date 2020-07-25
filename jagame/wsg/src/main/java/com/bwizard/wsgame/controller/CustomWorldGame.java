package com.bwizard.wsgame.controller;

import com.bwizard.wsgame.device.model.KeysEditorCustomModel;
import com.bwizard.wsgame.device.model.KeysGameCustomModel;
import com.bwizard.wsgame.device.view.GameCursorAppearance;
import com.bwizard.wsgame.documents.layout.DocumentBackgroundLayout;
import com.bwizard.wsgame.documents.layout.DocumentFigureLayout;
import com.bwizard.wsgame.world.map.provider.CellBackgroundProvider;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controller.WorldObjectManager;
import com.bwizard.cegame.device.controller.CursorController;
import com.bwizard.cegame.device.controller.KeysController;
import com.bwizard.cegame.device.model.CursorDialogModel;
import com.bwizard.cegame.device.model.CursorEditorModel;
import com.bwizard.cegame.device.model.CursorEmptyModel;
import com.bwizard.cegame.device.model.CursorGameModel;
import com.bwizard.cegame.device.model.CursorMenuModel;
import com.bwizard.cegame.device.model.KeysDialogModel;
import com.bwizard.cegame.device.model.KeysEmptyModel;
import com.bwizard.cegame.device.model.KeysMenuModel;
import com.bwizard.cegame.device.view.CursorAppearance;
import com.bwizard.cegame.device.view.KeysView;
import com.bwizard.cegame.device.view.MouseView;
import com.bwizard.cegame.documents.LayoutManager;
import com.bwizard.cegame.documents.layout.DocumentConfigurationLayout;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.figure.interfaces.IFigureMove;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.state.StateInfoManager;
import com.bwizard.cegame.state.handlers.Entry;
import com.bwizard.cegame.thread.ThreadScheduler;
import com.bwizard.cegame.window.BaseWindowGame;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;
import com.bwizard.cegame.world.map.MapBackgroundManager;


/**
 * This class creates world for specific game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CustomWorldGame extends BaseWorldGame {

	private static final LogInfo logInfo = new LogInfo(CustomWorldGame.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3549159518493868586L;
	private CellBackgroundProvider cellBackgroundProvider = null;
	private LayoutManager layoutManager = null;
	/*
	 * This constructor creates image for ball which will be managed by FrameManager
	 * windowScreen - add information about window to main world to serve actions like close application etc.
	 */
	public CustomWorldGame(IWindowScreen windowScreen, StateInfoManager stateInfoManager, BaseWindowGame baseWindowGame, ThreadScheduler threadScheduler) throws Exception {
		
		super(windowScreen, stateInfoManager, baseWindowGame, threadScheduler);
				
		cellBackgroundProvider = new CellBackgroundProvider(stateInfoManager);
		this.stateInfoManager = stateInfoManager;
		
		//create map from xml

		DocumentConfigurationLayout documentMapConfigurationLayout = new DocumentConfigurationLayout();
		DocumentBackgroundLayout documentBackgroundLayout = new DocumentBackgroundLayout(stateInfoManager);
		DocumentFigureLayout documentFigureLayout = new DocumentFigureLayout();
			
		mapBackgroundManager = new MapBackgroundManager(stateInfoManager);
		
		layoutManager = new LayoutManager(this);
		layoutManager.setDocumentMapConfigurationLayout(documentMapConfigurationLayout);
		layoutManager.setDocumentManagerBackgroundLayout(documentBackgroundLayout);
		layoutManager.setDocumentManagerFigureLayout(documentFigureLayout);
			
	}
	
	@Override
	protected CursorAppearance getCursorAppearance() {
		return new GameCursorAppearance();
	}
	
	@Override
	public void clear() {
		
		userViewManager.clear();
		worldObjectManager.clear();
		cellBackgroundProvider.clear();
		mapBackgroundManager.clear();
		cursorController.clearComponents();
		cleanerManager.removeUnusedResources();
		
	}
	
	@Override
	public void loadData(String filePath) {
		
		logInfo.info("start: loadData(" + filePath + ")");
		
		layoutManager.fillDocumentCollection(filePath);
		layoutManager.loadConfigurationMap();
		mapBackgroundManager.createCellBackgroundInfoTab();
		mapBackgroundManager.createCellRoadInfoTab();
		
		layoutManager.loadBackgroundMap(mapBackgroundManager);
		layoutManager.loadFiguresMap();

		logInfo.info("end:loadData(" + filePath + ")");
	}
	
	@Override
	public void paintBackgroundInUserView(Graphics g) {
		this.getMapBackgroundManager().drawBackground(g, cellBackgroundProvider, stateInfoManager); 
	}	

	/**
	 * @override
	 * This method paints figures world in user view games.
	 * @param g This variable provide graphics bufferStrategy tool.
	 * @param worldObjectManager The information about object in game.
	 * @return Nothing.
	 */
	@Override
	public void paintFiguresInUserView(Graphics g, WorldObjectManager worldObjectManager) {
		
		for(BaseFigure baseFigure : userViewManager.getUserViewObjects()) {
			baseFigure.paintFigure(g);
		}

	}

	@Override
	protected void updateWorld(List<BaseFigure> allObjects) {
			
		for(BaseFigure point : allObjects) {
			
			if (point instanceof IFigureMove && point.canMove()) {
				((IFigureMove)point).move();
			}
		}
	}

	
	@Override
	protected void paintSelected(Graphics g) {
		g.setColor(Color.YELLOW);
		
		int x1 = stateInfoManager.getCursorInfo().getStartScreenSelectionX();
		int y1 = stateInfoManager.getCursorInfo().getStartScreenSelectionY();
		
		int x3 = stateInfoManager.getCursorInfo().getEndScreenSelectionX();
		int y3 = stateInfoManager.getCursorInfo().getEndScreenSelectionY();
		
		int x2 = x1;
		int y2 = y3;
		
		int x4 = x3;
		int y4 = y1;
		
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x2, y2, x3, y3);
		g.drawLine(x3, y3, x4, y4);
		g.drawLine(x4, y4, x1, y1);

	}
	
	@Override
	public void addEventAction(Entry entry) {
		
		if (!getCurrentEntry().equals(entry)) {
			//clean & remove
			removeMouseListener(mouseView);
			removeMouseMotionListener(mouseView);
			removeKeyListener(keysView);
			
			if (cleanerManager != null) {
				cleanerManager.removeCursorController();
			}
			
			if(cursorController != null) {
				cursorController.clear();
				cursorController = null;
			}
			
			if(keysController != null) {
				keysController.clear();
				keysController = null;
			}
	
			mouseView = null;
			keysView = null;
			
			if (gameCursor != null) {
				gameCursor.clearComponents();
				gameCursor.clear();
				gameCursor = null;
			}
			
			if (cursorModel != null) {
				cursorModel.clearSelection();
				cursorModel.update();
				cursorModel.clearComponents();
				//cursorModel.setCursor(null);
				cursorModel = null;
			}
			
			if (keysModel != null) {
				keysModel.clear();
				keysModel = null;
			}
			
			//create new listener
			gameCursor = getCursorAppearance();
			
			switch(entry)
			{
				case GAME:
					cursorModel = new CursorGameModel(worldObjectManager, this, gameCursor);
					keysModel = new KeysGameCustomModel(this);
					break;
				case EDITOR:
					cursorModel = new CursorEditorModel(worldObjectManager, this, gameCursor);
					keysModel = new KeysEditorCustomModel(this);
					break;
				case MENU:
					cursorModel = new CursorMenuModel(worldObjectManager, this, gameCursor);
					keysModel = new KeysMenuModel(this);
					break;
				case DIALOG:
					cursorModel = new CursorDialogModel(worldObjectManager, this, gameCursor);
					keysModel = new KeysDialogModel(this);
					break;
				default:
					cursorModel = new CursorEmptyModel(worldObjectManager, this, gameCursor);
					keysModel = new KeysEmptyModel(this);
					break;
			}
			
			setCurrentEntry(entry);
				
			cursorController = new CursorController(cursorModel);
			cleanerManager.setCursorController(cursorController);
			mouseView = new MouseView(cursorController);
			
			keysController = new KeysController(keysModel);
			keysView = new KeysView(keysController);
			
			//add listener for pressed mouse
			addMouseListener(mouseView);
			//add listener for get mouse position
			addMouseMotionListener(mouseView);
			
			//add listener for keyboard
			addKeyListener(keysView);
		}
	}

}
