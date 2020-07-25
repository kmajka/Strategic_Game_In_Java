package com.bwizard.cegame.documents.interfaces;

import com.bwizard.cegame.world.map.MapBackgroundManager;

public interface ICmnManager {
	
	void loadConfigurationGame();
	void loadConfigurationMap();
	void loadBackgroundMap(MapBackgroundManager mapBackgroundManager);
	void loadFiguresMap();

}
