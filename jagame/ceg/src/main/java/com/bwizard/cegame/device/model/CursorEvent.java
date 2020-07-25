package com.bwizard.cegame.device.model;

public class CursorEvent {
	
	private int x;
	private int y;
	private boolean leftButton;
	private boolean rightButton;
	private boolean middleButton;
	
	public boolean isLeftButton() {
		return leftButton;
	}
	public void setLeftButton(boolean leftButton) {
		this.leftButton = leftButton;
	}
	public boolean isRightButton() {
		return rightButton;
	}
	public void setRightButton(boolean rightButton) {
		this.rightButton = rightButton;
	}
	public boolean isMiddleButton() {
		return middleButton;
	}
	public void setMiddleButton(boolean middleButton) {
		this.middleButton = middleButton;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
