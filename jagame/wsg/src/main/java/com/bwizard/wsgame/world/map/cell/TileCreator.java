package com.bwizard.wsgame.world.map.cell;

import com.bwizard.wsgame.world.map.handlers.BackgroundCellName;
import com.bwizard.cegame.frame.BaseFrame;
import com.bwizard.cegame.state.StateInfoGame;

public class TileCreator {
	
	StateInfoGame stateInfoGame = null;
	
	public TileCreator(StateInfoGame stateInfoGame) {
		this.stateInfoGame = stateInfoGame;
	}
	
	public BaseFrame createTile(BackgroundCellName keyCellName) {
					
		BaseFrame baseFrame = null;
		
		switch (keyCellName) {
			case GRASSGREEN_0_0_BACKGROUND_1:
			case GRASSBRIGHT_0_0_BACKGROUND_1:
			case GRASSDARK_0_0_BACKGROUND_1:
				baseFrame = new Ground(stateInfoGame, stateInfoGame.getWorldMapInfo().getHeightCell(), stateInfoGame.getWorldMapInfo().getWidthCell(), keyCellName);
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
				baseFrame = new Water(stateInfoGame, stateInfoGame.getWorldMapInfo().getHeightCell(), stateInfoGame.getWorldMapInfo().getWidthCell(), keyCellName);
				break;
			default:
				break;
		}
		
		return baseFrame;
	}
	
}
