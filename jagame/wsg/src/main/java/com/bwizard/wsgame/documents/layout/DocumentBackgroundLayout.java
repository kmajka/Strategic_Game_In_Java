package com.bwizard.wsgame.documents.layout;

import java.util.ArrayList;
import java.util.HashMap;

import com.bwizard.cegame.documents.handlers.LayoutAttribute;
import com.bwizard.cegame.documents.interfaces.ICmnDocument;
import com.bwizard.cegame.state.StateInfoManager;
import com.bwizard.cegame.utils.StringUtil;
import com.bwizard.cegame.world.map.cell.CellBackgroundInfo;

public class DocumentBackgroundLayout implements ICmnDocument<CellBackgroundInfo> {
	
	private StateInfoManager stateInfoManager; 
	
	public DocumentBackgroundLayout(StateInfoManager stateInfoManager) {
		this.stateInfoManager = stateInfoManager;
	}
	
	@Override
	public ArrayList<CellBackgroundInfo> createElementFromCellAttribute(
			CellBackgroundInfo parentElement,
			HashMap<String, String> hashCellAttribute) {
		
		ArrayList<CellBackgroundInfo> list = new ArrayList<CellBackgroundInfo>();
		
		//String figureType;
		CellBackgroundInfo component = null;	
		
		String cell = hashCellAttribute.get(LayoutAttribute.BACKGROUND_CELL);
		if (StringUtil.isNotEmpty(cell)) {
			int tmp;
			component = new CellBackgroundInfo(stateInfoManager, stateInfoManager.getWorldMapInfo().getWidthCell(), stateInfoManager.getWorldMapInfo().getHeightCell());
			component.setCellAsKeyImage(cell);
			
			String column = hashCellAttribute.get(LayoutAttribute.COLUMN);
			if (StringUtil.isNotEmpty(column)) {
				tmp = Integer.valueOf(column);
				component.setCellColumn(tmp);
			}
			
			String row = hashCellAttribute.get(LayoutAttribute.ROW);
			if (StringUtil.isNotEmpty(row)) {
				tmp = Integer.valueOf(row);
				component.setCellRow(tmp);
			}
			
			list.add(component);
		}
		
		return list;
	}
	  
}
