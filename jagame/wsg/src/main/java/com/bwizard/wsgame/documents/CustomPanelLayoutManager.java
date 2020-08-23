package com.bwizard.wsgame.documents;

import java.awt.Color;
import java.awt.Graphics;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.DrawMap;
import com.bwizard.cegame.controls.components.interfaces.IEventHandler;
import com.bwizard.cegame.documents.PanelLayoutManager;
import com.bwizard.cegame.documents.layout.DocumentPanelLayout;
import com.bwizard.cegame.documents.providers.ConfigurationProvider;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.logs.LogMonitor;
import com.bwizard.wsgame.documents.handlers.ConfiguratorMenuHandler;
import com.bwizard.wsgame.documents.handlers.EditorMenuHandler;
import com.bwizard.wsgame.documents.handlers.EditorViewHandler;
import com.bwizard.wsgame.documents.handlers.GameMenuHandler;
import com.bwizard.wsgame.documents.handlers.GameViewHandler;
import com.bwizard.wsgame.documents.handlers.MainMenuHandler;
import com.bwizard.wsgame.documents.names.GameComponentName;
import com.bwizard.wsgame.parameters.GameGlobals;


public class CustomPanelLayoutManager extends PanelLayoutManager {
	
	private static final LogInfo logInfo = new LogInfo(CustomPanelLayoutManager.class);
	
	public CustomPanelLayoutManager(BaseWorldGame baseWorldGame, DocumentPanelLayout documentPanelLayout, ConfigurationProvider configurationProvider) throws Exception {
		super(baseWorldGame, documentPanelLayout, configurationProvider);
	}
	
	@Override
	protected void addEventToFigure() {	
				 
	    for(BaseDrawFigure baseDrawFigure : listFigureManager.getElements()) {
	    	
	    	if (baseDrawFigure.hasEvent()) {
	    		setHandler(baseDrawFigure);
	    	}
	    } 
	}
	
	@Override
	public void setHandler(BaseDrawFigure baseDrawFigure) {
		
		IEventHandler eventHandler = null;
		
		switch(baseDrawFigure.getFigureType()) {
		case MAIN_MENU:
			eventHandler = new MainMenuHandler(baseWorldGame);
			break;
		case CONFIGURATOR_MENU:
			eventHandler = new ConfiguratorMenuHandler(baseWorldGame);
			break;
		case EDITOR_MENU:
			eventHandler = new EditorMenuHandler(baseWorldGame);
			break;
		case EDITOR_VIEW:
			eventHandler = new EditorViewHandler(baseWorldGame);
			break;
		case GAME_VIEW:
			eventHandler = new GameViewHandler(baseWorldGame);
			break;
		case GAME_MENU:
			eventHandler = new GameMenuHandler(baseWorldGame);
			break;
		default:
			eventHandler = new ConfiguratorMenuHandler(baseWorldGame);
			break;
		}
	
		baseDrawFigure.setEventHandler(eventHandler);
	}
	
	@Override
	protected void addTemporaryEventToFigure() {	
		
		try
		{
		    for(BaseDrawFigure baseDrawFigure : listFigureManager.getElements()) {
		    	if (baseDrawFigure.isTemporaryDisplaying() && baseDrawFigure.hasEvent()) {
		    		
		    		setHandler(baseDrawFigure);
		    	}
		    } 
		} 
		catch (Exception e1) {
			//EX1
			e1.printStackTrace();
			LogMonitor.buildInfo(logInfo, e1);
			
		}
	}
		
	@Override
	public void loadData() {
		
		fillDocumentCollection(PathGameManager.getPath(getLayoutName()), GameGlobals.IMAGE_PATH);

		updateDataFromConfig();
	}
	
	@Override
	public void reloadData() {
		reloadDocumentCollection(PathGameManager.getPath(getLayoutName()), GameGlobals.IMAGE_PATH);
		
		updateDataFromConfig();
	}
	
	@Override
	public void removeDialog() {
		removeDialogFromCollection();
	}
	
	@Override
	public void paintPanel(Graphics g) {
		
		int x = -1,y = -1;
		BaseFigure selectedFigure = baseWorldGame.getWorldObjectManager().getFirstSelectedObject();
		
		g.setColor(Color.GRAY);

		int index = 0;
		while(index < listFigureManager.size()) {
			
			BaseDrawFigure baseDrawFigure = listFigureManager.get(index);

			
			if (GameComponentName.NAME_OF_LAST_SELECTED_OBJECT.equals(baseDrawFigure.getTag())) {
				
				String name = "";
				if (selectedFigure != null) {
					name = selectedFigure.getName();
				}
				baseDrawFigure.setText("Name: " + name);
				
			} else if (GameComponentName.MAP_ACTUAL_POSITION_XY_OF_LAST_SELECTED_OBJECT.equals(baseDrawFigure.getTag())) {
				
				if (selectedFigure != null) {
					x = selectedFigure.getActualMapPosition().x;
					y = selectedFigure.getActualMapPosition().y;
				}
				baseDrawFigure.setText("Actual map object X:" + x + " / Y:"+ y);
				
			} else if (GameComponentName.MAP_START_POSITION_XY_OF_LAST_SELECTED_OBJECT.equals(baseDrawFigure.getTag())) {
				
					if (selectedFigure != null) {
						x = selectedFigure.getStartMapPosition().x;
						y = selectedFigure.getStartMapPosition().y;
						baseDrawFigure.setText("Start map object X:" + x + " / Y:"+ y);
					}
					
			} else if (GameComponentName.MAP_DESTINATION_POSITION_XY_OF_LAST_SELECTED_OBJECT.equals(baseDrawFigure.getTag())) {
				
				if (selectedFigure != null) {
					x = selectedFigure.getEndMapPosition().x;
					y = selectedFigure.getEndMapPosition().y;
				}
				baseDrawFigure.setText("Destin. map object X:" + x + " / Y:"+ y);
			
			} else if (GameComponentName.VIEW_ACTUAL_POSITION_XY_OF_LAST_SELECTED_OBJECT.equals(baseDrawFigure.getTag())) {
				
				if (selectedFigure != null) {
					x = selectedFigure.getActualMapPosition().x;
					y = selectedFigure.getActualMapPosition().y;
				}
				baseDrawFigure.setText("Actual view object X:" + x + " / Y:"+ y);
				
			} else if (GameComponentName.VIEW_START_POSITION_XY_OF_LAST_SELECTED_OBJECT.equals(baseDrawFigure.getTag())) {
				
				if (selectedFigure != null) {
					x = selectedFigure.getStartMapPosition().x;
					y = selectedFigure.getStartMapPosition().y;
					baseDrawFigure.setText("Start view object X:" + x + " / Y:"+ y);
				}
				
			} else if (GameComponentName.VIEW_DESTINATION_POSITION_XY_OF_LAST_SELECTED_OBJECT.equals(baseDrawFigure.getTag())) {
				
				if (selectedFigure != null) {
					x = selectedFigure.getEndMapPosition().x;
					y = selectedFigure.getEndMapPosition().y;
				}
				baseDrawFigure.setText("Destin. view object X:" + x + " / Y:"+ y);
				
			} else if (GameComponentName.ANGLE_LAST_SELECTED_OBJECT.equals(baseDrawFigure.getTag())) {
				
				int angle = -1;
				if (selectedFigure != null) {
					angle = selectedFigure.getAngle();
				}
				
				baseDrawFigure.setText("Angle: " + angle);
				
			} else if (GameComponentName.DISPLAY_INFO_FIGURE.equals(baseDrawFigure.getTag())) {
				
				if (selectedFigure != null && selectedFigure.isDisplayInfo() != baseDrawFigure.isSelected()) {
					baseDrawFigure.setSelected(selectedFigure.isDisplayInfo());
				}
				
			} else if (GameComponentName.CZAS_GRY.equals(baseDrawFigure.getTag())) {
				
				String timeInfo = baseWorldGame.displayInfoTime(stateInfoGame.getMonitorTime().getTimeInfo().getAllTime());
				baseDrawFigure.setText("Czas gry: " + timeInfo);
				
			} else if (GameComponentName.POZYCJA_KURSORA_EKRAN_GRY.equals(baseDrawFigure.getTag())) {
				
				baseDrawFigure.setText("Pozycja kursora [ekran gry]: X:" + stateInfoGame.getCursorInfo().getScreenCursorPositionX() + " / Y:"+ stateInfoGame.getCursorInfo().getScreenCursorPositionY());
				baseDrawFigure.setText("Start map selection: X:" + (stateInfoGame.getCursorInfo().getStartMapSelectionX())
						+ " / Y:"+ (stateInfoGame.getCursorInfo().getStartMapSelectionY()));
				//baseDrawFigure.setText("liczba wyswietlanych obiektow:" + this.baseWorldGame.getWorldObjectManager().getUserViewObjects().size());
				
			} else if (GameComponentName.POZYCJA_KURSORA_MAPA.equals(baseDrawFigure.getTag())) {
				
				baseDrawFigure.setText("Pozycja kursora [mapa]: X:" + (stateInfoGame.getCursorInfo().getMapCursorPositionX()) + " / Y:"+ (stateInfoGame.getCursorInfo().getMapCursorPositionY()));
				//baseDrawFigure.setText("End selection: X:" + (stateInfoGame.getMouseInfo().getEndScreenSelectionX() - baseWorldGame.getCameraMapInfo().getCameraX())
				//		+ " / Y:"+ (stateInfoGame.getMouseInfo().getEndScreenSelectionY() - baseWorldGame.getCameraMapInfo().getCameraY()));
				
			} else if (GameComponentName.ROZMIAR_PLANSZY.equals(baseDrawFigure.getTag())) {
				
				baseDrawFigure.setText("Rozmiar planszy:" + baseWorldGame.getWorldMapInfo().getWidthMap() + " x " + baseWorldGame.getWorldMapInfo().getHeightMap() + " px");
				
			} else if (GameComponentName.POLOZENIE_WIERZCHOKOW.equals(baseDrawFigure.getTag())) {
				
				//baseDrawFigure.setText("Po�o�enie wierzcho�k�w mapy:");
				baseDrawFigure.setText("Po�o�enie wierzcho�k�w kamery:");
				
			} else if (GameComponentName.POLOZENIE_WIERZCHOKOW_X1Y1.equals(baseDrawFigure.getTag())) {
				
				//baseDrawFigure.setText("X: " + Math.abs(stateInfoGame.getCameraMapInfo().getCameraX()) / stateInfoGame.getWorldMapInfo().getWidthCell());
				baseDrawFigure.setText("X: " + Integer.toString(stateInfoGame.getCameraMapInfo().getCameraX()));
				//baseDrawFigure.setText("X1,Y1: ["+ x +","+ y +"]");
				
			} else if (GameComponentName.POLOZENIE_WIERZCHOKOW_X2Y1.equals(baseDrawFigure.getTag())) {		

				//baseDrawFigure.setText("Y: " + Math.abs(stateInfoGame.getCameraMapInfo().getCameraY()) / stateInfoGame.getWorldMapInfo().getHeightCell());
				//baseDrawFigure.setText("X2,Y1: ["+ (x + w) +","+ y +"]");
				baseDrawFigure.setText("Y: " + Integer.toString(stateInfoGame.getCameraMapInfo().getCameraY()));
				
			} else if (GameComponentName.POLOZENIE_WIERZCHOKOW_X1Y2.equals(baseDrawFigure.getTag())) {
				
				baseDrawFigure.setText("X: " + Math.abs(stateInfoGame.getCameraMapInfo().getCameraX()) / stateInfoGame.getWorldMapInfo().getWidthCell());
				
				//baseDrawFigure.setText("X1,Y2: ["+ x +","+ (y + h)+"]");
				
			} else if (GameComponentName.POLOZENIE_WIERZCHOKOW_X2Y2.equals(baseDrawFigure.getTag())) {
				
				baseDrawFigure.setText("Y: " + Math.abs(stateInfoGame.getCameraMapInfo().getCameraY()) / stateInfoGame.getWorldMapInfo().getHeightCell());
				
				//baseDrawFigure.setText("X2,Y2: ["+ (x + w) +","+ (y + h) +"]");
				
			}
							

			if (baseDrawFigure.isVisible() && !baseDrawFigure.isPainted()) {
				baseDrawFigure.paint(g);
			}

			index++;
				
	    }
		
	}
	
	@Override
	public boolean isDisplayBackgroundCells() {
		
		BaseDrawFigure baseDrawFigure = baseWorldGame.getStateInfoGame().getPanelLayoutManager().getFirstElementsByTag(GameComponentName.DISPLAY_CELL_BACKGROUND_CHECKBOX);
		if (baseDrawFigure != null) {
			return baseDrawFigure.isSelected();
		}
		
		return false;
		
	}
	
	@Override
	public boolean isDisplayBackgroundPositionCells() {
		
		BaseDrawFigure baseDrawFigure = baseWorldGame.getStateInfoGame().getPanelLayoutManager().getFirstElementsByTag(GameComponentName.DISPLAY_CELL_BACKGROUND_POSITION_CHECKBOX);
		if (baseDrawFigure != null) {
			return baseDrawFigure.isSelected();
		}
		
		return false;
	}	
	
	@Override
	public boolean isDisplayRoadCells() {
		
		BaseDrawFigure baseDrawFigure = baseWorldGame.getStateInfoGame().getPanelLayoutManager().getFirstElementsByTag(GameComponentName.DISPLAY_CELL_ROAD_CHECKBOX);
		if (baseDrawFigure != null) {
			return baseDrawFigure.isSelected();
		}
		
		return false;
		
	}
	
	@Override
	public boolean isDisplayRoadPositionCells() {
		
		BaseDrawFigure baseDrawFigure = baseWorldGame.getStateInfoGame().getPanelLayoutManager().getFirstElementsByTag(GameComponentName.DISPLAY_CELL_ROAD_POSITION_CHECKBOX);
		if (baseDrawFigure != null) {
			return baseDrawFigure.isSelected();
		}
		
		return false;
	}
	
	@Override
	public boolean isMapViewSelected() {
		
		for (BaseDrawFigure baseDrawFigure : getDrawFiguresUnderCursor()) {
			
			if (baseDrawFigure instanceof DrawMap && GameComponentName.MAP_VIEW.equals(baseDrawFigure.getTag())) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public DrawMap getMapViewSelected() {
		
		for (BaseDrawFigure baseDrawFigure : getDrawFiguresUnderCursor()) {
			
			if (baseDrawFigure instanceof DrawMap && GameComponentName.MAP_VIEW.equals(baseDrawFigure.getTag())) {
				return (DrawMap)baseDrawFigure;
			}
		}
		
		return null;
	}
		
}