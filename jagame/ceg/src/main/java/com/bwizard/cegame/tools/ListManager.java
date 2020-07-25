package com.bwizard.cegame.tools;

import java.util.ArrayList;

import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.DrawList;

/**
 * This class can be used to provide possibility to manage elements in other class.
 * @author Krzysztof Majka
 * @version 1.0
 */
public class ListManager<T> {
	
	protected ArrayList<T> currentElements = new ArrayList<T>();
	private ArrayList<T> removeElements = new ArrayList<T>();
		
	public ArrayList<T> getElements() {
		return currentElements;
	}

	public void setList(ArrayList<T> listElement) {
		this.currentElements = listElement;
	}
	
	public boolean contains(T element) {
		return currentElements.contains(element);
	}
	
	public boolean add(T element) {
		return currentElements.add(element);
	}
	
	public void update() {
		
		if (removeElements.size() > 0) {
			
			currentElements.removeAll(removeElements);
			
			for(T element : removeElements ) {
				
				if (element instanceof DrawList) {
					((DrawList)element).dispose();
				} else if (element instanceof BaseDrawFigure) {
					((BaseDrawFigure)element).dispose();
				}
			}
			
			removeElements.clear();
		}
	}
	
	
	public boolean remove(T element) {		
		return removeElements.add(element);
	}
	
	public boolean remove(ArrayList<T> figures) {		
		return removeElements.addAll(figures);
	}
	
	public void clearAll() {
		
		for(T element : currentElements) {
			
			   if (element instanceof DrawList) {
					((DrawList)element).dispose();
				} else if (element instanceof BaseDrawFigure) {
					((BaseDrawFigure)element).dispose();
				}
		}
		currentElements.clear();
	}
	
	public int size() {
		return currentElements.size();
	}
	
	public T get(int index) {
		return currentElements.get(index);
	}
	
}
