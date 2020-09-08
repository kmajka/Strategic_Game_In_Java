package com.bwizard.wsgame.controller;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controller.WorldObjectManager;
import com.bwizard.cegame.device.controller.CursorController;
import com.bwizard.cegame.device.controller.KeysController;
import com.bwizard.cegame.device.model.*;
import com.bwizard.cegame.device.view.CursorAppearance;
import com.bwizard.cegame.device.view.KeysView;
import com.bwizard.cegame.device.view.MouseView;
import com.bwizard.cegame.documents.LayoutManager;
import com.bwizard.cegame.documents.layout.GameConfigurationLayout;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.figure.interfaces.IFigureMove;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.state.handlers.Entry;
import com.bwizard.cegame.window.BaseWindowGame;
import com.bwizard.cegame.world.map.MapBackgroundManager;
import com.bwizard.wsgame.device.model.KeysEditorCustomModel;
import com.bwizard.wsgame.device.model.KeysGameCustomModel;
import com.bwizard.wsgame.device.view.GameCursorAppearance;
import com.bwizard.wsgame.documents.layout.CustomBackgroundLayout;
import com.bwizard.wsgame.documents.layout.CustomFigureLayout;
import com.bwizard.wsgame.world.map.provider.CellBackgroundProvider;

import java.awt.*;
import java.util.List;

/**
 * This class creates world for specific game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CustomWorldGame extends BaseWorldGame {

	private static final LogInfo logInfo = new LogInfo(CustomWorldGame.class);

	private static final long serialVersionUID = -3549159518493868586L;
	private final CellBackgroundProvider cellBackgroundProvider;
	private final LayoutManager layoutManager;

	public CustomWorldGame(StateInfoGame stateInfoGame, BaseWindowGame baseWindowGame) {
		super(stateInfoGame, baseWindowGame);
				
		cellBackgroundProvider = new CellBackgroundProvider(stateInfoGame);
		this.stateInfoGame = stateInfoGame;
		
		//create map from xml

		GameConfigurationLayout documentMapConfigurationLayout = new GameConfigurationLayout();
		CustomBackgroundLayout customBackgroundLayout = new CustomBackgroundLayout(stateInfoGame);
		CustomFigureLayout customFigureLayout = new CustomFigureLayout();
			
		mapBackgroundManager = new MapBackgroundManager(stateInfoGame);
		
		layoutManager = new LayoutManager(this);
		layoutManager.setDocumentMapConfigurationLayout(documentMapConfigurationLayout);
		layoutManager.setDocumentManagerBackgroundLayout(customBackgroundLayout);
		layoutManager.setDocumentManagerFigureLayout(customFigureLayout);
			
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
		this.getMapBackgroundManager().drawBackground(g, cellBackgroundProvider, stateInfoGame);
	}	

	/**
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
		
		int x1 = stateInfoGame.getCursorInfo().getStartScreenSelectionX();
		int y1 = stateInfoGame.getCursorInfo().getStartScreenSelectionY();
		
		int x3 = stateInfoGame.getCursorInfo().getEndScreenSelectionX();
		int y3 = stateInfoGame.getCursorInfo().getEndScreenSelectionY();

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
