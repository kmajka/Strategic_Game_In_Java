package com.bwizard.cegame.documents;

import java.util.ArrayList;

import com.bwizard.cegame.configuration.components.BaseConfiguration;
import com.bwizard.cegame.configuration.components.CameraConfiguration;
import com.bwizard.cegame.configuration.components.CellConfiguration;
import com.bwizard.cegame.configuration.components.MapConfiguration;
import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.documents.interfaces.ICmnDocument;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.world.map.MapBackgroundManager;
import com.bwizard.cegame.world.map.cell.CellBackgroundInfo;

/**
 * This class uses the class DocumentManager to fill collection of e.g.(cells,figures,layout of panel)
 * @author Krzysztof Majka
 * @version 1.0
 */
public class LayoutManager extends BaseManager {

	private static final LogInfo logInfo = new LogInfo(LayoutManager.class);
	
	private BaseWorldGame baseWorldGame;
	private StateInfoGame stateInfoGame = null;
	
	private ArrayList<BaseConfiguration> listMapConfiguration = null;

	private ArrayList<CellBackgroundInfo> listCellBackgroundIntoMapLayout = null;
	private ArrayList<BaseFigure> listFigureIntoMapLayout = null;
	
	//collection stores information about map configuration of a game
	private DocumentManager<BaseConfiguration> documentManagerMapConfigurationLayout = null;
	//collection stores information about cells used in a game
	private DocumentManager<CellBackgroundInfo> documentManagerBackgroundLayout = null;
	//collection stores information about figures laying on the map
	private DocumentManager<BaseFigure> documentManagerFigureLayout = null;

	public LayoutManager() {
	}
	
	public LayoutManager(BaseWorldGame baseWorldGame) {
		this.baseWorldGame = baseWorldGame;
		this.stateInfoGame = baseWorldGame.getStateInfoGame();
	}
	
	public void setDocumentMapConfigurationLayout(ICmnDocument<BaseConfiguration> documentMapConfigurationLayout) {
		//create map configuration of the game
		listMapConfiguration = new ArrayList<BaseConfiguration>();
		documentManagerMapConfigurationLayout = new DocumentManager<BaseConfiguration>();
		documentManagerMapConfigurationLayout.cmnDocument = documentMapConfigurationLayout;
	}

	public void setDocumentManagerBackgroundLayout(ICmnDocument<CellBackgroundInfo> documentBackgroundLayout) {
		//create background into the map
		listCellBackgroundIntoMapLayout = new ArrayList<CellBackgroundInfo>();
		documentManagerBackgroundLayout = new DocumentManager<CellBackgroundInfo>();
		documentManagerBackgroundLayout.cmnDocument = documentBackgroundLayout;
	}

	public void setDocumentManagerFigureLayout(ICmnDocument<BaseFigure> documentFigureLayout) {
		//create figures into the map
		listFigureIntoMapLayout = new ArrayList<BaseFigure>();
		documentManagerFigureLayout= new DocumentManager<BaseFigure>();
		documentManagerFigureLayout.cmnDocument = documentFigureLayout;
	}
	
	public void fillDocumentCollection(String filePath) {
		
		logInfo.info("start: fillDocumentCollection(" + filePath + ")");
		
		if(documentManagerMapConfigurationLayout != null) {
			documentManagerMapConfigurationLayout.fillDocumentCollection(listMapConfiguration, filePath);
		}
		
		if(documentManagerBackgroundLayout != null) {
			documentManagerBackgroundLayout.fillDocumentCollection(listCellBackgroundIntoMapLayout, filePath);
		}
		
		if(documentManagerFigureLayout != null) {
			documentManagerFigureLayout.fillDocumentCollection(listFigureIntoMapLayout, filePath);
		}
		
		logInfo.info("end: fillDocumentCollection(" + filePath + ")");
	}
	
	@Override
	public void loadConfigurationMap() {
		
		for (BaseConfiguration baseConfiguration : listMapConfiguration) {

			if(baseConfiguration instanceof MapConfiguration) {
				
				stateInfoGame.getWorldMapInfo().setWidthMap(baseConfiguration.getBasicConfiguration().getWidth());
				stateInfoGame.getWorldMapInfo().setHeightMap(baseConfiguration.getBasicConfiguration().getHeight());
				
			} else if (baseConfiguration instanceof CellConfiguration) {
				
				stateInfoGame.getWorldMapInfo().setWidthCell(baseConfiguration.getBasicConfiguration().getWidth());
				stateInfoGame.getWorldMapInfo().setHeightCell(baseConfiguration.getBasicConfiguration().getHeight());
				
			} else if (baseConfiguration instanceof CameraConfiguration) {
				
				stateInfoGame.getCameraMapInfo().updateCameraX(baseConfiguration.getBasicConfiguration().getPositionX());
				stateInfoGame.getCameraMapInfo().updateCameraY(baseConfiguration.getBasicConfiguration().getPositionY());
				
			}
			
			baseConfiguration = null;
		}	
		
		listMapConfiguration.clear();
		
	}
	
	@Override
	public void loadFiguresMap() {
		
		for (BaseFigure baseFigure : listFigureIntoMapLayout) {
			baseFigure.setStateInfoGame(stateInfoGame);
			baseWorldGame.getWorldObjectManager().addFigureToAllObject(baseFigure);
		}
		
		listFigureIntoMapLayout.clear();
		
	}
	
	@Override
	public void loadBackgroundMap(MapBackgroundManager mapBackgroundManager) {
		
		for (CellBackgroundInfo cellBackgroundInfo : listCellBackgroundIntoMapLayout) {
			
			mapBackgroundManager.setBackgroundCell(cellBackgroundInfo.getCellRow(), cellBackgroundInfo.getCellColumn(), cellBackgroundInfo.getKeyImages());
			cellBackgroundInfo = null;
		}
		
		listCellBackgroundIntoMapLayout.clear();

	}
	
	public ArrayList<BaseConfiguration> getListMapConfiguration() {
		return listMapConfiguration;
	}

	
}
