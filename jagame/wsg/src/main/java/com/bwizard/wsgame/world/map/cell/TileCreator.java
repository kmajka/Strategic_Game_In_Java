package com.bwizard.wsgame.world.map.cell;

import com.bwizard.wsgame.world.map.handlers.BackgroundCellName;
import com.bwizard.cegame.frame.BaseFrame;
import com.bwizard.cegame.state.StateInfoManager;

public class TileCreator {
	
	StateInfoManager stateInfoManager = null;
	
	public TileCreator(StateInfoManager stateInfoManager) {	
		this.stateInfoManager = stateInfoManager;
	}
	
	public BaseFrame createTile(BackgroundCellName keyCellName) {
					
		BaseFrame baseFrame = null;
		
		switch (keyCellName) {
			case GRASSGREEN_0_0_BACKGROUND_1:
			case GRASSBRIGHT_0_0_BACKGROUND_1:
			case GRASSDARK_0_0_BACKGROUND_1:
				baseFrame = new Ground(stateInfoManager, stateInfoManager.getWorldMapInfo().getHeightCell(), stateInfoManager.getWorldMapInfo().getWidthCell(), keyCellName);
				break;
			case SEABLUE_0_0_BACKGROUND_1:
			case SEABLUE_2_2_BACKGROUND_1:
			case SEABLUE_1_1_BACKGROUND_1:
			case SEABLUE_1_2_BACKGROUND_1:
			case SEABLUE_2_3_BACKGROUND_1:
			case SEABLUE_4_4_BACKGROUND_1:
			case SEABLUE_3_3_BACKGROUND_1:
			case SEABLUE_3_4_BACKGROUND_1:
			case SEABLUE_4_2_BACKGROUND_1:
			case SEABLUE_4_1_BACKGROUND_1:
				baseFrame = new Water(stateInfoManager, stateInfoManager.getWorldMapInfo().getHeightCell(), stateInfoManager.getWorldMapInfo().getWidthCell(), keyCellName);
				break;
			default:
				break;
		}
		
		return baseFrame;
	}
	
}
