package com.bwizard.cegame.controls.components.list;

import com.bwizard.cegame.controls.components.BaseDrawFigure;

public class InfoDrawListFigure {
		
	private BaseDrawFigure baseDrawFigure = null;
	private int currentColumn;
	private int currentRow;
	private int oryginalColumn;
	private int oryginalRow;
	
	public InfoDrawListFigure(BaseDrawFigure baseDrawFigure) {
		this.setBaseDrawFigure(baseDrawFigure);
	}

	public int getCurrentColumn() {
		return currentColumn;
	}

	public void setCurrentColumn(int currentColumn) {
		this.currentColumn = currentColumn;
	}
	
	public void movePositionCurrentColumn(int index) {
		this.currentColumn += index;
	}

	public void movePositionCurrentRow(int index) {
		this.currentRow += index;
	}
	
	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public BaseDrawFigure getBaseDrawFigure() {
		return baseDrawFigure;
	}

	public void setBaseDrawFigure(BaseDrawFigure baseDrawFigure) {
		this.baseDrawFigure = baseDrawFigure;
	}
	
	public void dispose() {
		baseDrawFigure.dispose();
	}

	public int getOryginalColumn() {
		return oryginalColumn;
	}

	public void setOryginalColumn(int oryginalColumn) {
		this.oryginalColumn = oryginalColumn;
	}

	public int getOryginalRow() {
		return oryginalRow;
	}

	public void setOryginalRow(int oryginalRow) {
		this.oryginalRow = oryginalRow;
	}
	
	public void resetElementPosition() {
		currentRow = oryginalRow;
		currentColumn =  oryginalColumn;
	}
		
}
