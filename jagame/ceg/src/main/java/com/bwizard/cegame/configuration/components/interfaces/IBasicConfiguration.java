package com.bwizard.cegame.configuration.components.interfaces;

public interface IBasicConfiguration {
	
	int getPositionX();
	void setPositionX(int positionX);
	int getPositionY();
	void setPositionY(int positionY);
	void setHeight(int height);
	int getHeight();
	void setWidth(int width);
	int getWidth();
	String getName();
	void setName(String name);
	String getDescriptionMap();
	void setDescriptionMap(String descriptionMap);
	String getImageMap();
	void setImageMap(String imageMap);
}
