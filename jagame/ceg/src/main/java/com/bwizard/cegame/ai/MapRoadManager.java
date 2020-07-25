package com.bwizard.cegame.ai;

import com.bwizard.cegame.state.StateInfoManager;
import com.bwizard.cegame.world.map.cell.CellRoadInfo;

public class MapRoadManager {

	private CellRoadInfo[][] cellRoadInfoTab = null;
	
	protected StateInfoManager stateInfoManager = null;

	public MapRoadManager(StateInfoManager stateInfoManager) {
		
		this.stateInfoManager = stateInfoManager;
	}

	public CellRoadInfo[][] getCellRoadInfoTab() {
		return cellRoadInfoTab;
	}

	public void setCellRoadInfoTab(CellRoadInfo[][] cellRoadInfoTab) {
		this.cellRoadInfoTab = cellRoadInfoTab;
	}
	
}
