package com.bwizard.cegame.state;

import com.bwizard.cegame.camera.CameraMapInfo;
import com.bwizard.cegame.documents.interfaces.IPanelLayoutManager;
import com.bwizard.cegame.device.model.CursorInfo;
import com.bwizard.cegame.device.model.KeysInfo;
import com.bwizard.cegame.thread.ThreadInfo;
import com.bwizard.cegame.time.MonitorTime;
import com.bwizard.cegame.view.layout.interfaces.IViewLayout;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;
import com.bwizard.cegame.world.map.WorldMapInfo;

/*
 * This class stores actual information about game 
 * @author Krzysztof Majka
 * @version 1.0
 */
public class StateInfoGame {
	
	private CameraMapInfo cameraMapInfo = null;
	private CursorInfo cursorInfo = null;
	private KeysInfo keysInfo = null;
	private MonitorTime monitorTime = null;
	private WorldMapInfo worldMapInfo = null;
	private ThreadInfo threadInfo = null;
	private IWindowScreen windowScreen = null;
	private IViewLayout viewLayout = null;
	private IPanelLayoutManager panelLayoutManager = null;

	public StateInfoGame() {
		monitorTime = new MonitorTime();
		cursorInfo = new CursorInfo();
		keysInfo = new KeysInfo();
		worldMapInfo = new WorldMapInfo();
		
		cameraMapInfo = new CameraMapInfo();
		cameraMapInfo.setMapInfo(worldMapInfo);
		cursorInfo.setCameraMapInfo(cameraMapInfo);
	}
	
	public CameraMapInfo getCameraMapInfo() {
		return cameraMapInfo;
	}

	public KeysInfo getKeysInfo() {
		return keysInfo;
	}

	public WorldMapInfo getWorldMapInfo() {
		return worldMapInfo;
	}

	public CursorInfo getCursorInfo() {
		return cursorInfo;
	}

	public void setCursorInfo(CursorInfo cursorInfo) {
		this.cursorInfo = cursorInfo;
	}

	public IWindowScreen getWindowScreen() {
		return windowScreen;
	}

	public void setWindowScreen(IWindowScreen windowScreen) {
		this.windowScreen = windowScreen;
	}

	public IViewLayout getViewLayout() {
		return viewLayout;
	}

	public void setViewLayout(IViewLayout viewLayout) {
		this.viewLayout = viewLayout;
	}

	public IPanelLayoutManager getPanelLayoutManager() {
		return panelLayoutManager;
	}

	public void setPanelLayoutManager(IPanelLayoutManager panelLayoutManager) {
		this.panelLayoutManager = panelLayoutManager;
	}

	public ThreadInfo getThreadInfo() {
		return threadInfo;
	}

	public void setThreadInfo(ThreadInfo threadInfo) {
		this.threadInfo = threadInfo;
	}

	public MonitorTime getMonitorTime() {
		return monitorTime;
	}
	
}
