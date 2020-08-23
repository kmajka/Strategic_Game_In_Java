package com.bwizard.cegame.device.model;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.bwizard.cegame.camera.CameraMapInfo;
import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controller.WorldObjectManager;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.DrawMap;
import com.bwizard.cegame.device.handlers.CursorName;
import com.bwizard.cegame.device.handlers.DirectionMove;
import com.bwizard.cegame.device.interfaces.ICursorController;
import com.bwizard.cegame.device.interfaces.ICursorModel;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.figure.interfaces.IFigureMove;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.logs.LogMonitor;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.tools.Point2DGame;
import com.bwizard.cegame.utils.PointInfo;

/**
 * This class adds the cursor position into the list
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CursorModel implements ICursorModel {
	
	private static final LogInfo logInfo = new LogInfo(CursorModel.class);
	
	protected Set<BaseDrawFigure> setSelectingFigures = new HashSet<BaseDrawFigure>();
	protected WorldObjectManager worldObjectManager = null;
	protected CameraMapInfo cameraMapInfo = null;
	protected CursorInfo cursorInfo = null;
	protected KeysInfo keysInfo = null;
	protected ICursorController gameCursor = null;
	protected BaseWorldGame baseWorldGame = null;
	private final int SIZE_MOVE_MAP = 10;
	private final int STEP = 30;
	protected StateInfoGame stateInfoGame = null;
	
	public CursorModel(WorldObjectManager worldObjectManager, BaseWorldGame baseWorldGame, ICursorController gameCursor)  {
		this.worldObjectManager = worldObjectManager;
		this.baseWorldGame = baseWorldGame;
		this.cameraMapInfo = baseWorldGame.getStateInfoGame().getCameraMapInfo();
		this.cursorInfo = baseWorldGame.getStateInfoGame().getCursorInfo();
		this.keysInfo = baseWorldGame.getStateInfoGame().getKeysInfo();
		this.gameCursor = gameCursor;
		this.stateInfoGame = this.baseWorldGame.getStateInfoGame();
	}
	
	
	@Override
	public void setCursor(CursorName cursorName) {
		baseWorldGame.setCursor(gameCursor.getCursorType(cursorName));
	}
				
	@Override
	public void setPointDestination() {

		for(BaseFigure figure : worldObjectManager.getSelectedObjects()) {
			
			if (figure instanceof IFigureMove) {
				figure.setEndMapPosition(new Point2DGame(cursorInfo.getMapCursorPositionX(),cursorInfo.getMapCursorPositionY()));
				figure.setRecalculatePointsForVector(true);
			}
		}
	}
		
	/**
	 * This method pressed components in the game that have been marked as selected.
	 * @return Nothing.
	 */
    @Override
	public void selectedComponents() {

		try {
			Set<BaseDrawFigure> baseDrawFigures = baseWorldGame.getPanelLayoutManager().getSelectingFiguresUnderCursor();
			
			try
			{
				if (baseDrawFigures.size() > 0) {
					baseWorldGame.getPanelLayoutManager().pressedComponent(baseDrawFigures);
				}
			} catch (Exception e1) {
				//EX1
				e1.printStackTrace();
				LogMonitor.buildInfo(logInfo, e1);
				
			}

			//restore selecting components
			baseWorldGame.getPanelLayoutManager().unSelectingComponent(setSelectingFigures);
			setSelectingFigures.clear();

		} catch (Exception e1) {
			
			e1.printStackTrace();
			LogMonitor.buildInfo(logInfo, e1);
			
		}
	}
   
	/**
	 * This method selecting components in the game.
	 * @return Nothing.
	 */
	@Override
	public void selectingComponents() {
		
		//jezeli klikamy na przedmiot ktory mozna zaznaczyc - zaznaczamy
		//jezeli klikamy na powierzchnie malujemy prostokt (wszyskto co w srodu zaznaczamy ale tylko jednostki (bez budynkow))
    	//2 razy klikamy to zaznaczamy wszystkie jednostki z danej grupy, na poczarku defaultowo mamy tylko jedna grupe
    	//CTRL + LPM dodajemy zaznaczona figure
    	//SHIFT + 0 tworzenie grupy
    	//po najechaniu kursorem na obiekt zmieniamy jego kursor
    	//ALT + LPM przesuwanie plansza
    	//UWAGA! zaznaczamy nie prostokt ale obiekt ktory jest w nim namalowany (wszystko co przezroczyste omijamy)	    	
    	//we'e checking all figures laying on the layout
		Set<BaseDrawFigure> baseDrawFigures = baseWorldGame.getPanelLayoutManager().getDrawFiguresUnderCursor();	    	
    	if(baseDrawFigures.size() > 0) {		    		
    		//when user place a cursor over the view map
			DrawMap drawMap = baseWorldGame.getPanelLayoutManager().getMapViewSelected();
			if (drawMap != null) {
				drawMap.setSelecting(true);
				PointInfo pointInfo = drawMap.getCursorPositionOnViewMap(cursorInfo.getScreenCursorPositionX(), cursorInfo.getScreenCursorPositionY());
				this.cameraMapInfo.updateCamera(pointInfo);
    		} else {
    			
    			setSelectingFigures = baseWorldGame.getPanelLayoutManager().getDrawFiguresUnderCursor();	    	
    	    	if(setSelectingFigures.size() > 0) {
    				baseWorldGame.getPanelLayoutManager().selectingComponent(setSelectingFigures);
    	    	}
    		}
    		
    	} else {
    		
    		BaseFigure baseFigure = worldObjectManager.getSelectedFigure(cursorInfo.getMapCursorPositionX(), cursorInfo.getMapCursorPositionY());
			if (baseFigure != null) {
				boolean selected = baseFigure.isSelected();
				boolean allFiguresUnSelected = true;
				if (KeyEvent.VK_SHIFT == keysInfo.getKeyCode()) {
					// dodajemy do zaznaczonych obiektow
				} else if (KeyEvent.VK_CONTROL == keysInfo.getKeyCode()) {
					// dodajemy do grupy
				} else {
					allFiguresUnSelected = worldObjectManager.unselectedAllFigure();
				}

				if (allFiguresUnSelected) {
					if (!baseFigure.isPlacedManualy() || (baseFigure.isPlacedManualy() && !selected)) {
						// if object is selected then we clear selected mouse
						baseFigure.setSelected(true);
						this.cursorInfo.setSelected(false);
					}
				}

			} else {
				worldObjectManager.unselectedAllFigure();
				this.cursorInfo.setStartScreenSelectionX(cursorInfo.getScreenCursorPositionX());
				this.cursorInfo.setStartScreenSelectionY(cursorInfo.getScreenCursorPositionY());
				this.cursorInfo.setEndScreenSelectionX(cursorInfo.getScreenCursorPositionX());
				this.cursorInfo.setEndScreenSelectionY(cursorInfo.getScreenCursorPositionY());
				this.cursorInfo.setSelected(true);
			}
    	}
    	
	}
    
    @Override
    public void cursorDragged(CursorEvent e) {
    	
    	//Called in response to the user moving the mouse while holding a mouse button down.
    	
    	//before update mouse position
    	int previousX = cursorInfo.getScreenCursorPositionX() - cameraMapInfo.getCameraX();
    	int previousY = cursorInfo.getScreenCursorPositionY() - cameraMapInfo.getCameraY();
    	
    	updateMousePosition(e.getX(), e.getY());
    	
    	if (baseWorldGame.getPanelLayoutManager().isCursorOverLayout()) {
    		
    		//when user place a cursor over the view map
    		DrawMap drawMap = baseWorldGame.getPanelLayoutManager().getMapViewSelected();
    		if (drawMap != null && drawMap.isSelecting()) {
    			PointInfo pointInfo = drawMap.getCursorPositionOnViewMap(cursorInfo.getScreenCursorPositionX(), cursorInfo.getScreenCursorPositionY());
    			this.cameraMapInfo.updateCamera(pointInfo);
    		} else {
    			setCursor(CursorName.HAND_CURSOR);
    		}
    		
    		return;
    	}
    	
    	//clicked left mouse and dragged the map
    	if (e.isLeftButton()) {
    		
    		if (this.cursorInfo.isSelected()) {
    			this.cursorInfo.setEndScreenSelectionX(cursorInfo.getScreenCursorPositionX());
    			this.cursorInfo.setEndScreenSelectionY(cursorInfo.getScreenCursorPositionY());
    		}
	    	
    	} else if (e.isMiddleButton()) {
    		
    		setCursor(CursorName.MOVE_CURSOR);
    		
    		//after update mouse position 
    		int currentX = cursorInfo.getScreenCursorPositionX() - cameraMapInfo.getCameraX();
	    	int currentY = cursorInfo.getScreenCursorPositionY() - cameraMapInfo.getCameraY();
    		
    		DirectionMove directionMoveX = DirectionMove.NONE; 
	    	DirectionMove directionMoveY = DirectionMove.NONE; 
	    	if (previousX > currentX) {
	    		directionMoveX = DirectionMove.MOVE_RIGHT;
	    	} else {
	    		directionMoveX = DirectionMove.MOVE_LEFT;
	    	}
	    	
	    	if (previousY > currentY) {
	    		directionMoveY = DirectionMove.MOVE_DOWN;
	    	} else {
	    		directionMoveY = DirectionMove.MOVE_UP;
	    	}
	    	
	    	cameraMapInfo.setCameraX(Math.abs(currentX - previousX), directionMoveX); 
    		cameraMapInfo.setCameraY(Math.abs(currentY - previousY), directionMoveY); 
    	}
    	    
    }
    
    @Override
    public void cursorMoved(int x, int y)
    {
    	//Called in response to the user moving the mouse with no mouse buttons pressed
    	
    	updateMousePosition(x, y);
    	
    	if (baseWorldGame.isPausedGame()) {
    		setCursor(CursorName.HAND_CURSOR);
    	} else {
    			
	    	boolean resX = false;
	    	boolean resY = false;
	    	ArrayList<CursorName> listCursorType = new ArrayList<CursorName>();
	    	
	    	//automatically move cursor to left
	    	if (cursorInfo.getScreenCursorPositionX() < SIZE_MOVE_MAP) {
	    		baseWorldGame.getCameraMapInfo().setAutoCameraX(STEP, DirectionMove.MOVE_LEFT); 
	    		listCursorType.add(CursorName.LEFT_CURSOR);
	    		resX = true;
	    	}
	    	
	    	//automatically move cursor to right
	    	if (cursorInfo.getScreenCursorPositionX() > baseWorldGame.getWidth() - SIZE_MOVE_MAP) {
	    		baseWorldGame.getCameraMapInfo().setAutoCameraX(STEP, DirectionMove.MOVE_RIGHT); 
	    		listCursorType.add(CursorName.RIGHT_CURSOR);
	    		resX = true;
	    	}
	    	
	    	//automatically move cursor to up
	    	if (cursorInfo.getScreenCursorPositionY() < SIZE_MOVE_MAP) {
	    		baseWorldGame.getCameraMapInfo().setAutoCameraY(STEP, DirectionMove.MOVE_UP);
	    		listCursorType.add(CursorName.UP_CURSOR);
	    		resY = true;
	    	}
	    	//automatically move cursor to down
	    	if (cursorInfo.getScreenCursorPositionY() > baseWorldGame.getHeight() - SIZE_MOVE_MAP) {
	    		baseWorldGame.getCameraMapInfo().setAutoCameraY(STEP, DirectionMove.MOVE_DOWN); 
	    		listCursorType.add(CursorName.DOWN_CURSOR);
	    		resY = true;
	    	}
	    	
	    	//when the mouse cursor is outside the automatic scrolling the map then we cleaning automatically moving
	    	if (!resX) {
	    		baseWorldGame.getCameraMapInfo().clearAutoCameraX();
	    	}
	    	
	    	if (!resY) {
	    		baseWorldGame.getCameraMapInfo().clearAutoCameraY();
	    	}
	    	
	    	if (listCursorType.isEmpty()) {
	    		
	        	setCursor(CursorName.HAND_CURSOR);
	        
	    	} else if (listCursorType.size() == 1) {
	    		setCursor(listCursorType.get(0));
	    	} else if (listCursorType.contains(CursorName.LEFT_CURSOR) && listCursorType.contains(CursorName.UP_CURSOR)) {
	    		setCursor(CursorName.LEFT_UP_CURSOR);
	    	} else if (listCursorType.contains(CursorName.LEFT_CURSOR) && listCursorType.contains(CursorName.DOWN_CURSOR)) {
	    		setCursor(CursorName.LEFT_DOWN_CURSOR);
	    	} else if (listCursorType.contains(CursorName.RIGHT_CURSOR) && listCursorType.contains(CursorName.UP_CURSOR)) {
	    		setCursor(CursorName.RIGHT_UP_CURSOR);
	    	} else if (listCursorType.contains(CursorName.RIGHT_CURSOR) && listCursorType.contains(CursorName.DOWN_CURSOR)) {
	    		setCursor(CursorName.RIGHT_DOWN_CURSOR);
	    	}
    	}
    	    	
    }
    
    public void updateMousePosition(int x, int y) {
    	cursorInfo.setScreenCursorPositionX(x);
    	cursorInfo.setScreenCursorPositionY(y);
    }

	@Override
	public void clearComponents() {
		setSelectingFigures.clear();
	}
	
	@Override
	public void update() {
		ArrayList<BaseDrawFigure> listDisposed = new ArrayList<BaseDrawFigure>();
		for(BaseDrawFigure item : setSelectingFigures) {
			if (item.isDisposed() && !listDisposed.contains(item)) {
				listDisposed.add(item);
			}
		}
		setSelectingFigures.removeAll(listDisposed);
	}
	
	@Override
	public void clearSelection() {
		
		try {

			this.cursorInfo.setStartScreenSelectionX(0);
			this.cursorInfo.setStartScreenSelectionY(0);
			this.cursorInfo.setEndScreenSelectionX(0);
			this.cursorInfo.setEndScreenSelectionY(0);
			this.cursorInfo.setSelected(false);

			// when user place a cursor over the view map
			DrawMap drawMap = baseWorldGame.getPanelLayoutManager().getMapViewSelected();
			if (drawMap != null) {
				drawMap.setSelecting(false);
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	@Override
	public void performPressed(int numButton) {
		
		//Left Mouse Bottom	
		if (numButton == MouseEvent.BUTTON1) {  	
			selectingComponents();
		//Middle Mouse Bottom	
		} else if (numButton== MouseEvent.BUTTON2) {
			gameCursor.setMoveCursor();
		//Right Mouse Bottom
		} else if (numButton == MouseEvent.BUTTON3) {
			setPointDestination();
		}
	}

}
