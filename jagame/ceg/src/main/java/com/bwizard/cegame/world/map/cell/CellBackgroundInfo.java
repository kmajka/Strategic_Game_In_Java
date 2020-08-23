package com.bwizard.cegame.world.map.cell;

import java.awt.Graphics;
import java.util.ArrayList;

import com.bwizard.cegame.frame.BaseFrame;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.thread.ThreadStatus;

/*
 * This class stores information about one single cell
 * @author Krzysztof Majka
 * @version 1.0
 */
public class CellBackgroundInfo {
	
	//the size of rectangle (a single cell) on the map
	private int cellWidth = 0;
	private int cellHeight = 0;
	private boolean changed;
	private String keyCell;
	//can store information about a lot of part background
	private ArrayList<Integer> keyImages;
	private int cellRow = -1;
	private int cellColumn = -1;
	
	private StateInfoGame stateInfoGame = null;
	
	private ArrayList<BaseFrame> baseFrames = null;
	
	/*
	 * This constructor store information about one single cell
	 * @param imageBackground value of image name
	 */
	public CellBackgroundInfo(StateInfoGame stateInfoGame, int cellRow, int cellColumn) {
		keyImages = new ArrayList<Integer>();
		this.changed = false;
		this.cellRow = cellRow;
		this.cellColumn = cellColumn;
		this.stateInfoGame = stateInfoGame;
	}

	
	@Override
	public boolean equals(Object other) {   
	    if (this == other) {
	    	return true;   
	    }
	    if (other == null || !(other instanceof CellBackgroundInfo)) {
	    	return false;   
	    }
	    CellBackgroundInfo cellInfo = (CellBackgroundInfo)other;  
	    return this.keyCell.equals(cellInfo.keyCell);  
	}

	@Override
	public int hashCode() {  
		return keyCell.hashCode();  
	}
	
	public int getCellWidth() {
		return cellWidth;
	}

	public int getCellHeight() {
		return cellHeight;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	public String getKeyCell() {
		return keyCell;
	}

	public void setKeyCell(String keyCell) {
		this.keyCell = keyCell;
	}
	
	public ArrayList<Integer> getKeyImages() {
		return keyImages;
	}

	public void setKeyImages(ArrayList<Integer> keyImage) {
		this.keyImages = keyImage;
	}
	
	public void setCellAsKeyImage(String cells) {
		
		this.keyImages.clear();
		
		String regex = ",";
		String[] tabCells = cells.split(regex);
		for(String cell : tabCells) {
			int tmp = Integer.valueOf(cell);
			this.keyImages.add(tmp);
		}
	}

	public int getCellRow() {
		return cellRow;
	}

	public void setCellRow(int cellRow) {
		this.cellRow = cellRow;
	}

	public int getCellColumn() {
		return cellColumn;
	}

	public void setCellColumn(int cellColumn) {
		this.cellColumn = cellColumn;
	}

	public ArrayList<BaseFrame> getBaseFrames() {
		return baseFrames;
	}

	public void setBaseFrame(ArrayList<BaseFrame> baseFrame) {
		this.baseFrames = baseFrame;
	}
	
	public void paintFigure(Graphics g, int x, int y) {
		
		if(ThreadStatus.RUN.equals(stateInfoGame.getThreadInfo().getThreadStatus())) {
			for(BaseFrame baseFrame : baseFrames) {
				baseFrame.update(stateInfoGame.getMonitorTime().getTimeInfo());
			}
		}
		
		//DrawManager.drawImageInView(g, baseFrame.getImage(), x, y, stateInfoGame, false, true);
	}
	
}
