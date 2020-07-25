package com.bwizard.cegame.documents;

import java.util.ArrayList;

import com.bwizard.cegame.controls.components.BaseDrawFigure;

public class FocusedManager {
	
	private ArrayList<BaseDrawFigure> elements = new ArrayList<BaseDrawFigure>();
	
	public FocusedManager() {
	}

	public ArrayList<BaseDrawFigure> getElements() {
		return elements;
	}

	public void setElements(ArrayList<BaseDrawFigure> listElement) {
		this.elements = listElement;
	}
	
	public void clearElements() {
		elements.clear();
	}
	
	public BaseDrawFigure getFocusedElement() {
		for(BaseDrawFigure baseDrawFigure : elements) {
			if (baseDrawFigure.isDefaultFocused()) {
				return baseDrawFigure;
			}
		}
		
		return elements.get(0);
	}
}
