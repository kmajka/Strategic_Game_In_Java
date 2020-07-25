package com.bwizard.cegame.documents;

import java.util.ArrayList;
import java.util.Iterator;

import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.tools.ListManager;
import com.bwizard.cegame.utils.StringUtil;

/**
 * This class is used to manage elements laying in game by tags 
 * @author Krzysztof Majka
 * @version 1.0
 */
public class ListFigureManager extends ListManager<BaseDrawFigure> {
		
	public ArrayList<BaseDrawFigure> getElementsForDefaultTag(String configDefaultTag) {
		
		ArrayList<BaseDrawFigure> list = new ArrayList<BaseDrawFigure>();
		
		if (StringUtil.isNotEmpty(configDefaultTag)) {
			
			Iterator<BaseDrawFigure> iterator = getElements().iterator();
			while(iterator.hasNext()) {
				
				BaseDrawFigure baseDrawFigure = iterator.next();
				String baseDefaultTag = baseDrawFigure.getTagDefaultFigure();
				
				if (StringUtil.isNotEmpty(baseDefaultTag) && configDefaultTag.equals(baseDefaultTag)) {
					list.add(baseDrawFigure);
				}
			}
		}
		
		return list;
	}
	
	public ArrayList<BaseDrawFigure> getSimpleElements() {
		
		ArrayList<BaseDrawFigure> list = new ArrayList<BaseDrawFigure>();
		
		Iterator<BaseDrawFigure> iterator = getElements().iterator();
		while(iterator.hasNext()) {
			
			BaseDrawFigure baseDrawFigure = iterator.next();
			String baseDefaultTag = baseDrawFigure.getTagDefaultFigure();
			
			if (StringUtil.isNullOrEmpty(baseDefaultTag)) {
				list.add(baseDrawFigure);
			}
		}
		
		return list;
	}
	
	public ArrayList<BaseDrawFigure> getElementsByTag(String defaultTag) {
		
		ArrayList<BaseDrawFigure> list = new ArrayList<BaseDrawFigure>();
		
		if (StringUtil.isNotEmpty(defaultTag)) {
			
			Iterator<BaseDrawFigure> iterator = getElements().iterator();
			while(iterator.hasNext()) {
				
				BaseDrawFigure baseDrawFigure = iterator.next();
				String baseTag = baseDrawFigure.getTag();
				
				if (StringUtil.isNotEmpty(baseTag) && defaultTag.equals(baseTag)) {
					list.add(baseDrawFigure);
				}
			}

		}
		
		return list;
	}
	
}
