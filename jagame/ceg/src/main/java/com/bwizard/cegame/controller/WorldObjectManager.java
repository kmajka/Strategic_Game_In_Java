package com.bwizard.cegame.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.logs.LogInfo;

/**
 * This class manages collection of objects placed into game map
 * @author Krzysztof Majka
 * @version 1.0
 */
public class WorldObjectManager {
	
	private static final LogInfo logInfo = new LogInfo(WorldObjectManager.class);
	
	//all objects in game
	private List<BaseFigure> listAllObjects = Collections.synchronizedList(new ArrayList<BaseFigure>());
	private List<BaseFigure> listRemoveObjects = new ArrayList<BaseFigure>();
	private UserViewManager userViewManager;
		
	public List<BaseFigure> getAllObjects() {
		return listAllObjects;
	}
	
	public void clear() {
		
		logInfo.info("start: clear()");
		
		for(BaseFigure baseFigure : listAllObjects) {
			baseFigure.dispose();
		}
		listAllObjects.clear();
		
		for(BaseFigure baseFigure : listRemoveObjects) {
			baseFigure.dispose();
		}
		listRemoveObjects.clear();
							
		logInfo.info("end: clear()");
	}
	
	public void addFigureToAllObject(BaseFigure baseFigure) {
		this.listAllObjects.add(baseFigure);
	}
	
	public synchronized List<BaseFigure> getSelectedObjects() {
		
		List<BaseFigure> listSelectedObjects = new ArrayList<BaseFigure>();
		
		for(BaseFigure figure : getAllObjects()) {
			if (figure.isSelected()) {
				listSelectedObjects.add(figure);
			}
		}
		
		return listSelectedObjects;
	}
	
	
	public BaseFigure getFirstSelectedObject() {
		
		for(BaseFigure figure : getAllObjects()) {
			if (figure.isSelected()) {
				return figure;
			}
		}
		return null;
	}
	
	public BaseFigure getSelectedFigure(int x, int y) {
		for(BaseFigure figure : getAllObjects()) {
			if(figure.canFocus() && (figure.getActualMapPosition().x < x) && ((figure.getActualMapPosition().x + figure.getImage().getWidth()) > x) &&
					(figure.getActualMapPosition().y < y) && ((figure.getActualMapPosition().y + figure.getImage().getHeight()) > y)) {
				return figure;
			} 
		}
		return null;
	}
	
	
	public boolean unselectedAllFigure() {
		boolean res = true;
		for(BaseFigure point : getAllObjects()) {
			if (point.isPlaceFree()) {
				point.setSelected(false);
			} else {
				res = false;
			}
		}
		return res;
	}

	public List<BaseFigure> getListRemoveObjects() {
		return listRemoveObjects;
	}

	public void setListRemoveObjects(List<BaseFigure> listRemoveObjects) {
		this.listRemoveObjects = listRemoveObjects;
	}

	public UserViewManager getUserViewManager() {
		return userViewManager;
	}

	public void setUserViewManager(UserViewManager userViewManager) {
		this.userViewManager = userViewManager;
	}
	
}
