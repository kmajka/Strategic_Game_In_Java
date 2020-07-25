package com.bwizard.cegame.documents;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bwizard.cegame.configuration.components.interfaces.IFigureConfiguration;
import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.DrawList;
import com.bwizard.cegame.controls.components.DrawMap;
import com.bwizard.cegame.documents.interfaces.IPanelLayoutManager;
import com.bwizard.cegame.documents.layout.DocumentLayoutTool;
import com.bwizard.cegame.documents.layout.DocumentPanelLayout;
import com.bwizard.cegame.documents.providers.ConfigurationProvider;
import com.bwizard.cegame.state.StateInfoManager;


/**
 * This class manages all component show in the games/layout possibility to run game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class PanelLayoutManager implements IPanelLayoutManager {
	
	protected BaseWorldGame baseWorldGame = null;
	private LayoutEventManager layoutEventManager = null;
	protected StateInfoManager stateInfoManager = null;
	public int backgroundName;
	
	protected ListFigureManager listFigureManager = null;
	private FocusedManager focusedManager = null;
	protected DocumentManager<BaseDrawFigure> documentManagerPanelLayout = null;
	protected DocumentPanelLayout documentPanelLayout = null;
	private String layoutName;
	protected ConfigurationProvider configurationProvider = null;
	
	public PanelLayoutManager(BaseWorldGame baseWorldGame, DocumentPanelLayout documentPanelLayout, ConfigurationProvider configurationProvider) throws Exception {
		
		this.listFigureManager = new ListFigureManager();
		this.focusedManager = new FocusedManager();
		this.layoutEventManager = new LayoutEventManager();
		
		this.configurationProvider = configurationProvider;
		
		this.baseWorldGame = baseWorldGame;
		this.stateInfoManager = baseWorldGame.getStateInfoManager();
		
		this.documentManagerPanelLayout = new DocumentManager<BaseDrawFigure>();	
		this.documentManagerPanelLayout.cmnDocument = documentPanelLayout;
		
		this.documentPanelLayout = documentPanelLayout;
		this.documentPanelLayout.setListFigureIntoPanelLayout(listFigureManager.getElements());
		
	}
	
	@Override
	public void loadData() {
	}

	public void addElement(BaseDrawFigure baseDrawFigure) {
		
		if (!listFigureManager.contains(baseDrawFigure)) {
			listFigureManager.add(baseDrawFigure);
		}
	}
	
	public void removeElement(BaseDrawFigure baseDrawFigure) {
		
		if (listFigureManager.contains(baseDrawFigure)) {
			listFigureManager.remove(baseDrawFigure);
		}
	}
	
	protected void fillDocumentCollection(String fileLayoutXmlPath, String fileLayoutImagePath) {
		
		layoutEventManager.clearAll();
		listFigureManager.clearAll();
		
		documentPanelLayout.setLayoutImagePath(fileLayoutImagePath);
		
		documentManagerPanelLayout.fillDocumentCollection(listFigureManager.getElements(), fileLayoutXmlPath);
		
		initializeEventList();
		addEventToFigure();

	}
	
	protected void reloadDocumentCollection(String fileLayoutXmlPath, String fileLayoutImagePath) {
				
		documentPanelLayout.setLayoutImagePath(fileLayoutImagePath);
		
		focusedManager.clearElements();
		//fill list of possible focused elements
		documentManagerPanelLayout.fillDocumentCollection(focusedManager.getElements(), fileLayoutXmlPath);
		//update list of all elements
		listFigureManager.getElements().addAll(focusedManager.getElements());
		
		initializeTemporaryEventList();
		addTemporaryEventToFigure();
	}
	
	protected void removeDialogFromCollection() {
		focusedManager.clearElements();
		removeTemporaryElement();		
	}
	
	@Override
	public void update() {
		listFigureManager.update();
	}
	
	
	@Override
	public void hideAllPanelsFromGroup(String group) {
		
		//hide all tabs in the same group with the exclusion of tag
		for(BaseDrawFigure baseDrawFigure : listFigureManager.getElements()) {
			if (group.equals(baseDrawFigure.getGroup())) {
				baseDrawFigure.setVisible(false);
				baseDrawFigure.setSelected(false);
				baseDrawFigure.setSelecting(false);
			}
		}
	}
	
	@Override
	public void setVisiblePanelFromGroup(String tag, boolean visible) {
		
		try {
			
			String group = "";
			for(BaseDrawFigure baseDrawFigure : listFigureManager.getElements()) {
				if (tag.equals(baseDrawFigure.getTag())) {
					group = baseDrawFigure.getGroup();
					baseDrawFigure.setVisible(visible);
					break;
				}
			}
		
			//hide all tabs in the same group with the exclusion of tag
			for(BaseDrawFigure baseDrawFigure : listFigureManager.getElements()) {
				if (group.equals(baseDrawFigure.getGroup()) && !tag.equals(baseDrawFigure.getTag())) {
					baseDrawFigure.setVisible(!visible);
					
					if (baseDrawFigure instanceof DrawList) {
						((DrawList)baseDrawFigure).clearSelected();
					}
					
					for(BaseDrawFigure childComponent : baseDrawFigure.getChildComponents()) {
						if (childComponent instanceof DrawList) {
							((DrawList)childComponent).clearSelected();
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void addEventToFigure() {
	}
	
	protected void initializeEventList() {
		
		layoutEventManager.clearAll();
		
	    for(BaseDrawFigure baseDrawFigure : listFigureManager.getElements()) {
	    	layoutEventManager.addEvent(baseDrawFigure);
	    }
	}
	
	protected void initializeTemporaryEventList() {

		for (BaseDrawFigure baseDrawFigure : listFigureManager.getElements()) {

			if (baseDrawFigure.isTemporaryDisplaying()) {
				layoutEventManager.addEvent(baseDrawFigure);
			} else {
				//only for figure which is enable. set lock for such figures
				if (baseDrawFigure.hasEvent() && baseDrawFigure.isEnable()) {
					baseDrawFigure.setEnable(false);
					baseDrawFigure.setLock(true);
				}
			}
		}

	}

	private void removeTemporaryElement() {
					    
		ArrayList<BaseDrawFigure> itemsToRemove = new ArrayList<BaseDrawFigure>();
		for(BaseDrawFigure item : listFigureManager.getElements()) {
			if (item.isTemporaryDisplaying()) {
				itemsToRemove.add(item);
				if (item.hasEvent()) {
					item.setEventHandler(null);
				}
			} else {
				//only for lock figure
				if (item.hasEvent() && item.isLock()) {
					item.setEnable(true);
					//release lock for figures
					item.setLock(false);
				}
			}
		}
		
		layoutEventManager.removeEvent(itemsToRemove);
		listFigureManager.remove(itemsToRemove);	  
	}

	@Override
	public int getBackgroundComboBox() {
		return backgroundName;
	}

	@Override
	public void setBackgroundComboBox(int name) {
		backgroundName = name;
	}

	@Override
	public void selectingComponent(BaseDrawFigure setBaseDrawFigures) {

		if (setBaseDrawFigures.canPressedFigure() && setBaseDrawFigures.isVisible()) {
			setBaseDrawFigures.setSelecting(true);
		}
	}
	
	@Override
	public void selectingComponent(Set<BaseDrawFigure> setBaseDrawFigures) {

		for(BaseDrawFigure baseDrawFigure: setBaseDrawFigures) {
			if (baseDrawFigure.canPressedFigure() && baseDrawFigure.isVisible()) {
				baseDrawFigure.setSelecting(true);
			}
		}
	}
	
	@Override
	public void unSelectingComponent(Set<BaseDrawFigure> setBaseDrawFigures) {

		for(BaseDrawFigure baseDrawFigure: setBaseDrawFigures) {
			if (baseDrawFigure.canPressedFigure() && baseDrawFigure.isVisible()) {
				baseDrawFigure.setSelecting(false);
			}
		}
	}
	
	
	@Override
	public void pressedComponent(Set<BaseDrawFigure> setBaseDrawFigures) {

		Iterator<BaseDrawFigure> iter = setBaseDrawFigures.iterator();
		
		while(iter.hasNext()) {
			BaseDrawFigure baseDrawFigure = iter.next();
			if (baseDrawFigure.canPressedFigure() && baseDrawFigure.isVisible()) {
				baseDrawFigure.setPressed(true);
				break;
			}
		}
	}
	
	@Override
	public void pressedComponent() {
		
		for(BaseDrawFigure baseDrawFigure : layoutEventManager.getCollection()) {
			if (baseDrawFigure.canPressedFigure() && baseDrawFigure.isVisible()) {
				baseDrawFigure.setPressed(true);
				break;
			}
		}
	}
	
	@Override
	public boolean isCursorOverLayout() {
		for(BaseDrawFigure baseDrawFigure : layoutEventManager.getCollection()) {
			if (baseDrawFigure.isCursorOverFigure()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Set<BaseDrawFigure> getDrawFiguresUnderCursor() {
		
		Set<BaseDrawFigure> baseDrawFigureList = new HashSet<BaseDrawFigure>();
		for(BaseDrawFigure baseDrawFigure : layoutEventManager.getCollection()) {
			if (baseDrawFigure.isCursorOverFigure()) {
				baseDrawFigureList.add(baseDrawFigure);
			}			
		}
		
		return baseDrawFigureList;
	}
	
	@Override
	public Set<BaseDrawFigure> getSelectingFiguresUnderCursor() {
		
		Set<BaseDrawFigure> baseDrawFigureList = new HashSet<BaseDrawFigure>();
		for(BaseDrawFigure baseDrawFigure : layoutEventManager.getCollection()) {
			if (baseDrawFigure.isCursorOverFigure() && baseDrawFigure.isSelecting()) {
				baseDrawFigureList.add(baseDrawFigure);
			}			
		}
		
		return baseDrawFigureList;
	}	

	public String getLayoutName() {
		return layoutName;
	}

	@Override
	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	@Override
	public void paintPanel(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDisplayBackgroundCells() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDisplayBackgroundPositionCells() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isDisplayRoadCells() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isDisplayRoadPositionCells() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isMapViewSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DrawMap getMapViewSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reloadData() {
		// TODO Auto-generated method stub
		
	}

	protected void addTemporaryEventToFigure() {
		
	}

	@Override
	public void removeDialog() {
		// TODO Auto-generated method stub
		
	}
	
	
	public BaseDrawFigure getFirstElementsByTag(String tag) {
		int index = 0;
		while(index < listFigureManager.size()) {
		
			BaseDrawFigure baseDrawFigure = listFigureManager.get(index);
		
			if (tag.equals(baseDrawFigure.getTag())) {
				return baseDrawFigure;
			}
		
			index++;
		}
	
		return null;
		
	}	

	public ArrayList<BaseDrawFigure> getElementsByTag(String tag) {
		return listFigureManager.getElementsByTag(tag);
	}
	
	protected void updateDataFromConfig() {
		
		//update component from configure
		for (IFigureConfiguration figureConfiguration : configurationProvider.getListFigureConfiguration()) {
			
			BaseDrawFigure configFigure = figureConfiguration.getFigure();
			if (configFigure != null) {
				
				for(BaseDrawFigure baseDrawFigure : listFigureManager.getElementsForDefaultTag(configFigure.getTagDefaultFigure())) {
					DocumentLayoutTool.updateFigure(baseDrawFigure, configFigure);
				}			
			}
		}
		
	}

	@Override
	public BaseDrawFigure getFocusedElement() {
		return focusedManager.getFocusedElement();
	}

	@Override
	public void setHandler(BaseDrawFigure baseDrawFigure) {
		// TODO Auto-generated method stub
		
	}
	
}
