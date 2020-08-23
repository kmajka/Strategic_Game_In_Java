package com.bwizard.cegame.ai;

import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.world.map.cell.CellRoadInfo;

public class MapRoadManager {

	private CellRoadInfo[][] cellRoadInfoTab = null;
	
	protected StateInfoGame stateInfoGame = null;

	public MapRoadManager(StateInfoGame stateInfoGame) {
		
		this.stateInfoGame = stateInfoGame;
	}

	public CellRoadInfo[][] getCellRoadInfoTab() {
		return cellRoadInfoTab;
	}

	public void setCellRoadInfoTab(CellRoadInfo[][] cellRoadInfoTab) {
		this.cellRoadInfoTab = cellRoadInfoTab;
	}
	
}
