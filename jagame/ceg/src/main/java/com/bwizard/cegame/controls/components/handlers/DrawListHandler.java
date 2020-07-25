package com.bwizard.cegame.controls.components.handlers;

import com.bwizard.cegame.controls.components.DrawList;
import com.bwizard.cegame.controls.components.interfaces.IEventHandler;
import com.bwizard.cegame.controls.components.list.InfoDrawListFigure;
import com.bwizard.cegame.documents.handlers.Arrangement;

public class DrawListHandler implements IEventHandler {

	private DrawList drawList = null;
	
	public DrawListHandler(DrawList drawList) {
		this.drawList = drawList;
	}
	
	public void reset() {
		for(InfoDrawListFigure figure: drawList.getItems()) {
			figure.resetElementPosition();
		}
	}
	
	@Override
	public void invokeEvent(Object sender, String arg0) {
		
		if ("drawButton1".equals(arg0)) {
			
			//stop if last figure is displayed
			int tmpMinColumn = 0, tmpMinRow = 0;
			for(InfoDrawListFigure figure: drawList.getItems()) {
				
				if (figure.getCurrentColumn() < tmpMinColumn) {
					tmpMinColumn = figure.getCurrentColumn();
				}
				
				if (figure.getCurrentRow() < tmpMinRow) {
					tmpMinRow = figure.getCurrentRow(); 
				}

			}
				
			if (Arrangement.HORIZONTAL.equals(drawList.getArrangement())) {
				if(tmpMinColumn == 0) {
					return;
				}
			} else if (Arrangement.VERTICAL.equals(drawList.getArrangement())) {
				if(tmpMinRow == 0) {
					return;
				}
			}
			
			
			for(InfoDrawListFigure figure: drawList.getItems()) {
				if (Arrangement.HORIZONTAL.equals(drawList.getArrangement())) {
					figure.movePositionCurrentColumn(1);
				} else if (Arrangement.VERTICAL.equals(drawList.getArrangement())) {
					figure.movePositionCurrentRow(1);
				}
			}
			
		} else if ("drawButton2".equals(arg0)) {
			
			//stop if last figure is displayed
			int tmpMaxColumn = 0, tmpMaxRow = 0;
			for(InfoDrawListFigure figure: drawList.getItems()) {
				
				if (figure.getCurrentColumn() > tmpMaxColumn) {
					tmpMaxColumn = figure.getCurrentColumn();
				}
				
				if (figure.getCurrentRow() > tmpMaxRow) {
					tmpMaxRow = figure.getCurrentRow(); 
				}
				
			}
				
			if (Arrangement.HORIZONTAL.equals(drawList.getArrangement())) {
				if(tmpMaxColumn == drawList.getNumberColumns() - 1) {
					return;
				}
			} else if (Arrangement.VERTICAL.equals(drawList.getArrangement())) {
				if(tmpMaxRow == drawList.getNumberRows() - 1) {
					return;
				}
			}
			
			for(InfoDrawListFigure figure: drawList.getItems()) {
				if (Arrangement.HORIZONTAL.equals(drawList.getArrangement())) {
					figure.movePositionCurrentColumn(-1);
				} else if (Arrangement.VERTICAL.equals(drawList.getArrangement())) {
					figure.movePositionCurrentRow(-1);
				}
			}
			
		} 
	}		
}