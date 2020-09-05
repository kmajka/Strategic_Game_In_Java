package com.bwizard.cegame.documents.providers;

import java.util.ArrayList;

import com.bwizard.cegame.configuration.components.BaseConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.IFigureConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.ISoundConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.IVideoConfiguration;
import com.bwizard.cegame.documents.ConfigurationManager;
import com.bwizard.cegame.documents.layout.DocumentConfigurationLayout;

public class ConfigurationProvider {

	private String pathFileName;
	private ConfigurationManager configurationManager = null;
	
	public ConfigurationProvider(String pathFileName) {
		this.pathFileName = pathFileName;
		configurationManager = new ConfigurationManager(new DocumentConfigurationLayout());
	}
	
	public void loadData() {
		configurationManager.fillDocumentCollection(pathFileName);
		configurationManager.loadConfigurationGame();
	}
	
	public IVideoConfiguration getVideoConfiguration() {
		return configurationManager.getVideoConfiguration();
	}

	public ISoundConfiguration getSoundConfiguration() {
		return configurationManager.getSoundConfiguration();
	}
	
	public ArrayList<IFigureConfiguration> getListFigureConfiguration() {
		
		ArrayList<IFigureConfiguration> list = new ArrayList<IFigureConfiguration>();
		
		for (BaseConfiguration baseConfiguration : configurationManager.getListFigureConfiguration()) {
			
			IFigureConfiguration figureConfiguration = baseConfiguration.getFigureConfiguration();
			if(figureConfiguration != null) {
				list.add(figureConfiguration);
			}
		}
			
		return list;
	}

}
