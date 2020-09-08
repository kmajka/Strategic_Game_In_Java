package com.bwizard.wsgame.documents.layout;

import java.util.ArrayList;

import com.bwizard.wsgame.parameters.GameGlobals;
import com.bwizard.cegame.configuration.components.BaseConfiguration;
import com.bwizard.cegame.configuration.components.MapConfiguration;
import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.documents.LayoutManager;
import com.bwizard.cegame.documents.layout.GameConfigurationLayout;
import com.bwizard.cegame.documents.layout.GamePanelLayout;

public class CustomPanelLayout extends GamePanelLayout {

	public CustomPanelLayout(BaseWorldGame baseWorldGame) {
		super(baseWorldGame);
	}
	
	@Override
	protected void addInfoToMainMenuPanel(BaseDrawFigure component) {

		try {
			
			LayoutManager layoutManager = new LayoutManager();
			//set object to read configuration for map
			layoutManager.setDocumentMapConfigurationLayout(new GameConfigurationLayout());
			//read configuration of map
			layoutManager.fillDocumentCollection(GameGlobals.MAPS + component.getFileName());
			//get configuration of map
			ArrayList<BaseConfiguration> listConfiguration = layoutManager.getListMapConfiguration();
			
			MapConfiguration mapConfiguration = null;
			
			//search Map configuration object. it can also contains Cell,Camera,...
			for (BaseConfiguration baseConfiguration : listConfiguration) {
				if (baseConfiguration instanceof MapConfiguration) {
					mapConfiguration = (MapConfiguration)baseConfiguration;
					break;
				}
			}
			
			if(mapConfiguration != null) {
				component.setText(mapConfiguration.getBasicConfiguration().getName());
				component.setDescriptionField1(mapConfiguration.getBasicConfiguration().getDescriptionMap());
				component.setImage2(getImageSprite(GameGlobals.IMAGE_MAP_PATH + mapConfiguration.getBasicConfiguration().getImageMap()));
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
