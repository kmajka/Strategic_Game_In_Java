package com.bwizard.cegame.controller;

import com.bwizard.cegame.device.interfaces.ICursorController;
import com.bwizard.cegame.documents.interfaces.IPanelLayoutManager;

public class CleanerManager {

	private IPanelLayoutManager panelLayoutManager = null;
	private ICursorController cursorController = null;
	
	public void removeUnusedResources() {
		
		if (panelLayoutManager != null) {
			panelLayoutManager.update();
		}
		if (cursorController != null) {
			cursorController.update();
		}
	}

	public void setPanelLayoutManager(IPanelLayoutManager panelLayoutManager) {
		this.panelLayoutManager = panelLayoutManager;
	}

	public void setCursorController(ICursorController cursorController) {
		this.cursorController = cursorController;
	}
	
	public void removeCursorController() {
		if (cursorController != null) {
			this.cursorController.clearComponents();
			this.cursorController = null;
		}
	}
}
