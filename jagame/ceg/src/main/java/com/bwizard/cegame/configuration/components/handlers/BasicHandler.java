package com.bwizard.cegame.configuration.components.handlers;

import com.bwizard.cegame.configuration.components.interfaces.IBasicConfiguration;

public class BasicHandler implements IBasicConfiguration {

	private int positionX;
	private int positionY;
	private int width;
	private int height;
	private String name;
	private String descriptionMap;
	private String imageMap;
	
	@Override
	public int getPositionX() {
		return positionX;
	}

	@Override
	public void setPositionX(int positionX) {
		this.positionX = positionX;
		
	}

	@Override
	public int getPositionY() {
		return positionY;
	}

	@Override
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
		
	}

	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescriptionMap() {
		return descriptionMap;
	}

	@Override
	public void setDescriptionMap(String descriptionMap) {
		this.descriptionMap = descriptionMap;
	}

	@Override
	public String getImageMap() {
		return imageMap;
	}

	@Override
	public void setImageMap(String imageMap) {
		this.imageMap = imageMap;
	}

}
