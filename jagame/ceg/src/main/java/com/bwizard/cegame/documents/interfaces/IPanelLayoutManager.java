package com.bwizard.cegame.documents.interfaces;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;

import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.DrawMap;

public interface IPanelLayoutManager {
	
	boolean isCursorOverLayout();
	void pressedComponent();
	void setVisiblePanelFromGroup(String tag, boolean visible);
	void hideAllPanelsFromGroup(String tag);
	void paintPanel(Graphics g);
	Set<BaseDrawFigure> getDrawFiguresUnderCursor();
	Set<BaseDrawFigure> getSelectingFiguresUnderCursor();
	
	void pressedComponent(Set<BaseDrawFigure> setBaseDrawFigures);
	void selectingComponent(BaseDrawFigure setBaseDrawFigures);
	void selectingComponent(Set<BaseDrawFigure> setBaseDrawFigures);
	void unSelectingComponent(Set<BaseDrawFigure> setBaseDrawFigures);
	boolean isMapViewSelected();
	DrawMap getMapViewSelected();
	
	//these methods are useless - only for testing
	boolean isDisplayBackgroundCells();
	boolean isDisplayBackgroundPositionCells();
	
	boolean isDisplayRoadCells();
	boolean isDisplayRoadPositionCells();

	int getBackgroundComboBox();
	void setBackgroundComboBox(int selected);
	
	void setLayoutName(String layoutName) ;
	void loadData();
	void reloadData();
	void removeDialog();
		
	void addElement(BaseDrawFigure baseDrawFigure); 
	//void removeElement(BaseDrawFigure baseDrawFigure);
	
	void update();
	
	ArrayList<BaseDrawFigure> getElementsByTag(String defaultTag);
	BaseDrawFigure getFirstElementsByTag(String defaultTag);
	
	BaseDrawFigure getFocusedElement();
	void setHandler(BaseDrawFigure baseDrawFigure);


}
