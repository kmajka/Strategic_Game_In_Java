package com.bwizard.cegame.documents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class can be used to provide possibility for other class to store only elements which can have own events. This class cannot change anything on stored elements.
 * @author Krzysztof Majka
 * @version 1.0
 */
public class EventManager<T> {
	
	protected Set<T> drawEventList = new HashSet<T>();
	
	public boolean addEvent(T event) {
		return drawEventList.add(event);
	}
	
	public boolean removeEvent(T event) {
		return drawEventList.remove(event);
	}
	
	public boolean removeEvent(ArrayList<T> events) {
		return drawEventList.removeAll(events);
	}
	
	public void clearAll() {		
		drawEventList.clear();
	}
	
	public Set<T> getCollection() {
		return drawEventList;
	}
}
