package com.bwizard.cegame.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bwizard.cegame.figure.BaseFigure;

/**
 * This class manages collection of objects placed into user view
 * @author Krzysztof Majka
 * @version 1.0
 */

public class UserViewManager {
	
	private List<BaseFigure> listUserViewObjects = null;
	
	public UserViewManager() {
		listUserViewObjects = Collections.synchronizedList(new ArrayList<BaseFigure>());
	}

	public List<BaseFigure> getUserViewObjects() {
		return listUserViewObjects;
	}
	
	public void clear() {
		this.listUserViewObjects.clear();
	}
	
	/**
	 * This method updates properties (isUserView) in games - update specific number into collection for painting figures
	 */
	public void updateUserViewProperties(List<BaseFigure> allObjects) {
		
		this.listUserViewObjects.clear();
		
		for(BaseFigure baseFigure : allObjects) {
			if (baseFigure.isInUserView()) {
				this.listUserViewObjects.add(baseFigure);
			}
		}
		Collections.sort(listUserViewObjects, new VerticalPosition());
	}
	
	private class VerticalPosition implements Comparator<BaseFigure> {
		 
        @Override
        public int compare(BaseFigure o1, BaseFigure o2) {
        	if (o1.getVerticalMapPosition() < o2.getVerticalMapPosition()) {
        		return -1;
        	}
        	return 1;
        }
 
    }
	
}
