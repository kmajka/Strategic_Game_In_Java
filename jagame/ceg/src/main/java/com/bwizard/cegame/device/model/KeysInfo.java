package com.bwizard.cegame.device.model;

import java.awt.event.KeyEvent;

public class KeysInfo {
	private KeyEvent keyEvent;
	public KeysInfo() {
	}
	
	public int getKeyCode() {
		return keyEvent != null ? keyEvent.getKeyCode() : -1;
	}
	
	public KeyEvent getKeyEvent() {
		return keyEvent;
	}

	public void setKeyEvent(KeyEvent keyEvent) {
		this.keyEvent = keyEvent;
	}

}
