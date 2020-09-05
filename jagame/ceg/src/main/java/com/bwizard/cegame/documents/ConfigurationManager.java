package com.bwizard.cegame.documents;

import java.util.ArrayList;

import com.bwizard.cegame.configuration.components.BaseConfiguration;
import com.bwizard.cegame.configuration.components.FigureConfiguration;
import com.bwizard.cegame.configuration.components.SoundConfiguration;
import com.bwizard.cegame.configuration.components.VideoConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.ISoundConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.IVideoConfiguration;
import com.bwizard.cegame.documents.interfaces.ICmnDocument;
import com.bwizard.cegame.logs.LogInfo;

/**
 * This class uses the class DocumentManager to fill collection of e.g.(cells,figures,layout of panel)
 * @author Krzysztof Majka
 * @version 1.0
 */
public class ConfigurationManager extends BaseManager {

	private static final LogInfo logInfo = new LogInfo(ConfigurationManager.class);
	
	private ArrayList<BaseConfiguration> listFigureConfiguration = null;
	private BaseConfiguration videoConfiguration = null;
	private BaseConfiguration soundConfiguration = null;
	private ArrayList<BaseConfiguration> listGameConfiguration = null;
	
	//collection stores information about configuration of a game
	private DocumentManager<BaseConfiguration> documentManagerGameConfiguration = null;

	public ConfigurationManager(ICmnDocument<BaseConfiguration> documentGameConfiguration) {
		//create configuration of the game
		listGameConfiguration = new ArrayList<BaseConfiguration>();
		listFigureConfiguration = new ArrayList<BaseConfiguration>();
		documentManagerGameConfiguration= new DocumentManager<BaseConfiguration>();
		documentManagerGameConfiguration.cmnDocument = documentGameConfiguration;
	}
			
	
	public void fillDocumentCollection(String filePath) {
		
		logInfo.info("start: fillDocumentCollection("+filePath+")");
		
		documentManagerGameConfiguration.fillDocumentCollection(listGameConfiguration, filePath);
		
		logInfo.info("end: fillDocumentCollection("+filePath+")");
	}
	
	
	@Override
	public void loadConfigurationGame() {
		for (BaseConfiguration baseConfiguration : listGameConfiguration) {
			
			if (baseConfiguration instanceof VideoConfiguration) {
				videoConfiguration = baseConfiguration;
			} else if (baseConfiguration instanceof SoundConfiguration) {
				soundConfiguration = baseConfiguration;
			} else if (baseConfiguration instanceof FigureConfiguration) {
				listFigureConfiguration.add(baseConfiguration);
			}
		}
		
		listGameConfiguration.clear();
	}

	public IVideoConfiguration getVideoConfiguration() {
		return videoConfiguration.getVideoConfiguration();
	}

	public ISoundConfiguration getSoundConfiguration() {
		return soundConfiguration.getSoundConfiguration();
	}
	
	public ArrayList<BaseConfiguration> getListFigureConfiguration() {
		return listFigureConfiguration;
	}

	
}
