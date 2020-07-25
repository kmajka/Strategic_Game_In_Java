package com.bwizard.cegame.world.map;

/*
 * This class stores information about the map (e.g information about all cells lying on the map)
 * @author Krzysztof Majka
 * @version 1.0
 */
public class WorldMapInfo {
	
	//the size of the entire map
	private int widthMap = 0; 
	private int heightMap = 0;

	private int widthCell = 0;
	private int heightCell = 0;
	
	public WorldMapInfo() {
	}
	
	public void setWidthMap(int widthMap) {
		this.widthMap = widthMap;
	}
	
	public void setHeightMap(int heightMap) {
		this.heightMap = heightMap;
	}
	
	public int getWidthMap() {
		return widthMap;
	}
	
	public int getHeightMap() {
		return heightMap;
	}
	
	public void setWidthCell(int widthCell) {
		this.widthCell = widthCell;
	}
	
	public int getWidthCell() {
		return widthCell;
	}
	
	public void setHeightCell(int heightCell) {
		this.heightCell = heightCell;
	}
	
	public int getHeightCell() {
		return heightCell;
	}
	
	public int getNumberWidthCell() {
		return getWidthMap() / getWidthCell();
	}
	
	public int getNumberHeightCell() {
		return getHeightMap()/getHeightCell();
	}
	
}
