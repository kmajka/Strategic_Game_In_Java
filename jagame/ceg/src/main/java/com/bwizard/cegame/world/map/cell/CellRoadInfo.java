package com.bwizard.cegame.world.map.cell;

public class CellRoadInfo {
	
	private String keyCell;
	private int cellRow = -1;
	private int cellColumn = -1;
	
	public CellRoadInfo(int cellRow, int cellColumn) {
		this.cellRow = cellRow;
		this.cellColumn = cellColumn;
	}
	
	@Override
	public boolean equals(Object other) {   
	    if (this == other) {
	    	return true;   
	    }
	    if (other == null || !(other instanceof CellBackgroundInfo)) {
	    	return false;   
	    }
	    CellRoadInfo cellInfo = (CellRoadInfo)other;  
	    return this.keyCell.equals(cellInfo.keyCell);  
	}
	
	@Override
	public int hashCode() {  
		return keyCell.hashCode();  
	}
	
	public String getKeyCell() {
		return keyCell;
	}

	public void setKeyCell(String keyCell) {
		this.keyCell = keyCell;
	}
	
	public int getCellRow() {
		return cellRow;
	}

	public void setCellRow(int cellRow) {
		this.cellRow = cellRow;
	}

	public int getCellColumn() {
		return cellColumn;
	}

	public void setCellColumn(int cellColumn) {
		this.cellColumn = cellColumn;
	}
	
}
