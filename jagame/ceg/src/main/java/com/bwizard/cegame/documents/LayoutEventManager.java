package com.bwizard.cegame.documents;

import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.DrawList;

/**
 * This class is used for operate in specific way only for store elements which can have own events. This class cannot change anything on stored elements.
 * @author Krzysztof Majka
 * @version 1.0
 */
public class LayoutEventManager extends EventManager<BaseDrawFigure> {
	
	@Override
	public boolean addEvent(BaseDrawFigure baseDrawFigure) {
		boolean res = false;
		if (baseDrawFigure.hasEvent() && validateEvent(baseDrawFigure) && !drawEventList.contains(baseDrawFigure)) {
			drawEventList.add(baseDrawFigure);
			res = true; 
		}
		return res;
	}
	
	private boolean validateEvent(BaseDrawFigure baseDrawFigure) {
		//DrawList has own mechanism for control of the components within it so we don't need to add event.
		return !(baseDrawFigure.getParentComponent() instanceof DrawList);
	}
	
}
