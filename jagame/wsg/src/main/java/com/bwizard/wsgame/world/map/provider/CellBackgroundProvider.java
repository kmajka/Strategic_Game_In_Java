package com.bwizard.wsgame.world.map.provider;

import com.bwizard.wsgame.world.map.cell.TileCreator;
import com.bwizard.wsgame.world.map.handlers.BackgroundCellName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bwizard.cegame.frame.BaseFrame;
import com.bwizard.cegame.game.world.map.cell.interfaces.ICellBackgroundProvider;
import com.bwizard.cegame.state.StateInfoManager;

public class CellBackgroundProvider implements ICellBackgroundProvider {

	//collection for store only one single frame
	HashMap<BackgroundCellName, BaseFrame> mapKeyCellInfo = new HashMap<BackgroundCellName, BaseFrame>();
	HashMap<BackgroundCellName, Boolean> mapFirstUpdate = new HashMap<BackgroundCellName, Boolean>();
	private StateInfoManager stateInfoManager = null;
	private TileCreator tileCreator = null;

	public CellBackgroundProvider(StateInfoManager stateInfoManager) {
		this.stateInfoManager = stateInfoManager;
		this.tileCreator = new TileCreator(stateInfoManager);
	}

	@Override
	public ArrayList<BaseFrame> getCellInfo(ArrayList<Integer> keyImages) {

		ArrayList<BaseFrame> baseFrames = new ArrayList<BaseFrame>();
		BackgroundCellName keyCellName = null;
		
		for(int keyImage: keyImages) {
			
			keyCellName = BackgroundCellName.valueOf(keyImage);
	
			if (!mapKeyCellInfo.containsKey(keyCellName)) {
			
				BaseFrame baseFrame = tileCreator.createTile(keyCellName);
				
				if (baseFrame != null) {
					mapKeyCellInfo.put(keyCellName, baseFrame);
					mapFirstUpdate.put(keyCellName, false);
				}
			} 
			
			if(!mapFirstUpdate.containsKey(keyCellName)) {
				mapFirstUpdate.put(keyCellName, false);
			}
			
			baseFrames.add(mapKeyCellInfo.get(keyCellName));
		}
		
		return baseFrames;
	}
	
	@Override
	public void updateBackgroundCell(ArrayList<BaseFrame> baseFrames, ArrayList<Integer> keyImages) {
		
		for(int keyImage: keyImages) {
			
			BackgroundCellName keyCellName = BackgroundCellName.valueOf(keyImage);
			
			//only one update for 'frame' related with the category (e.g. LATO) for painting background in the iteration
			if (!mapFirstUpdate.get(keyCellName)) {
				for(BaseFrame baseFrame : baseFrames) {
					baseFrame.update(stateInfoManager.getMonitorTime().getTimeInfo());
				}
				mapFirstUpdate.put(keyCellName, true);
			}
		}
	}
	
	@Override
	public void clearFirstUpdate() {
		
		Iterator<BackgroundCellName> keySetIterator = mapFirstUpdate.keySet().iterator();

		while(keySetIterator.hasNext()) {
			BackgroundCellName key = keySetIterator.next();
			mapFirstUpdate.put(key, false);
		}
		
	}
	
	public void clear() {
		
		//dispose all images background before reload new data 
		
		Iterator<BackgroundCellName> keySetIterator = mapKeyCellInfo.keySet().iterator();

		while(keySetIterator.hasNext()) {
			BackgroundCellName key = keySetIterator.next();
			mapKeyCellInfo.get(key).dispose();
		}
		
		mapKeyCellInfo.clear();
		mapFirstUpdate.clear();
	}
	
}
