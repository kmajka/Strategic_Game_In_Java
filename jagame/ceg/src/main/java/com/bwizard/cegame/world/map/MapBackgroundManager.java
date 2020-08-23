package com.bwizard.cegame.world.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.bwizard.cegame.documents.interfaces.IPanelLayoutManager;
import com.bwizard.cegame.frame.BaseFrame;
import com.bwizard.cegame.game.world.map.cell.interfaces.ICellBackgroundProvider;
import com.bwizard.cegame.parameters.CmnGlobals;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.tools.DrawManager;
import com.bwizard.cegame.world.map.cell.CellBackgroundInfo;
import com.bwizard.cegame.world.map.cell.CellRoadInfo;

public class MapBackgroundManager {

	private CellBackgroundInfo[][] cellBackgroundInfoTab = null;
	private CellRoadInfo[][] cellRoadInfoTab = null;
	
	private IPanelLayoutManager panelLayoutManager;
	private StateInfoGame stateInfoGame = null;
	private Set<CellBackgroundInfo> hashSetVisibleCellsInfo = null;
	
	//background
	private int middleBackgroundWidthCell = 0;//determine the average value for the proper camera settings
	private int middleBackgroundHeightCell = 0;//determine the average value for the proper camera settings
	private int maxBackgroundRow = 0;
	private int maxBackgroundColumn = 0;
	
	//road
	//private int middleRoadWidthCell = 0;//determine the average value for the proper camera settings
	//private int middleRoadHeightCell = 0;//determine the average value for the proper camera settings
	private int maxRoadRow = 0;
	private int maxRoadColumn = 0;

	public MapBackgroundManager(StateInfoGame stateInfoGame) {
		
		this.stateInfoGame = stateInfoGame;
		hashSetVisibleCellsInfo = new HashSet<CellBackgroundInfo>();
	}
	
	public void createCellBackgroundInfoTab() {
			
		//added range safety - will be multiply by two (in the isometric map each tiles is moved by half so there is two times more tiles) // '3' it is a safety margin
		maxBackgroundRow = (stateInfoGame.getWorldMapInfo().getNumberHeightCell() * 2) + 3;
		//it is a safety margin for isometric maps to cover the entire map tiles
		maxBackgroundColumn = stateInfoGame.getWorldMapInfo().getNumberWidthCell() + 2;
		//middleWidthCell will be divided by two
		middleBackgroundWidthCell = stateInfoGame.getWorldMapInfo().getWidthCell() / 2;
		//middleHeightCell will be divided by two
		middleBackgroundHeightCell = stateInfoGame.getWorldMapInfo().getHeightCell() / 2;
		
		if (cellBackgroundInfoTab == null) {
					
			cellBackgroundInfoTab = new CellBackgroundInfo[maxBackgroundRow][maxBackgroundColumn];
			
			for (int row = 0; row < maxBackgroundRow; row++) {
				for (int column = 0; column < maxBackgroundColumn; column++) {
					cellBackgroundInfoTab[row][column] = new CellBackgroundInfo(stateInfoGame, stateInfoGame.getWorldMapInfo().getWidthCell(), stateInfoGame.getWorldMapInfo().getHeightCell());
					cellBackgroundInfoTab[row][column].setKeyCell(row + "|" + column);
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(0);
					cellBackgroundInfoTab[row][column].setKeyImages(list);
				}
			}
			
		} else {
			try {
				throw new Exception("cellBackgroundInfoTab[][] hasn't been disposed !");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createCellRoadInfoTab() {
		
		//added range safety - will be multiply by two (in the isometric map each tiles is moved by half so there is two times more tiles) // '3' it is a safety margin
		maxRoadRow = (stateInfoGame.getWorldMapInfo().getNumberHeightCell() * 2) + 3;
		//it is a safety margin for isometric maps to cover the entire map tiles
		maxRoadColumn = stateInfoGame.getWorldMapInfo().getNumberWidthCell() + 2;
		//middleWidthCell will be divided by two
		middleBackgroundWidthCell = stateInfoGame.getWorldMapInfo().getWidthCell() / 2;
		//middleHeightCell will be divided by two
		middleBackgroundHeightCell = stateInfoGame.getWorldMapInfo().getHeightCell() / 2;
		
		if (cellRoadInfoTab == null) {
					
			cellRoadInfoTab = new CellRoadInfo[maxRoadRow][maxRoadColumn];
			
			for (int row = 0; row < maxRoadRow; row++) {
				for (int column = 0; column < maxRoadColumn; column++) {
					cellRoadInfoTab[row][column] = new CellRoadInfo(stateInfoGame.getWorldMapInfo().getWidthCell(), stateInfoGame.getWorldMapInfo().getHeightCell());
					cellRoadInfoTab[row][column].setKeyCell(row + "|" + column);
				}
			}
			
		} else {
			try {
				throw new Exception("createCellRoadInfoTab[][] hasn't been disposed !");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void clear() {
		
		hashSetVisibleCellsInfo.clear();
		
		if (cellRoadInfoTab != null) {
			for(int row = 0; row < cellRoadInfoTab.length; row++) {
				for(int column = 0; column < cellRoadInfoTab[row].length; column++) {
					cellRoadInfoTab[row][column] = null;
				}
			}
			
			cellRoadInfoTab = null;
		}
		
		if (cellBackgroundInfoTab != null) {
			for(int row = 0; row < cellBackgroundInfoTab.length; row++) {
				for(int column = 0; column < cellBackgroundInfoTab[row].length; column++) {
					cellBackgroundInfoTab[row][column] = null;
				}
			}
			
			cellBackgroundInfoTab = null;
		}
		
	}	

   /**
   * This method draws one single cell.
   * @param g This value paints object.
   * @param cellPositionRow This value store information about key[Y] for cell.
   * @param cellPositionColumn This value store information about key[X] for cell.
   * @param pointRow This value store information about position cell in Y-axis. [128x128 cell range: min.-128 ...] 
   * @param pointColumn This value store information about position cell in X-axis. [128x128 cell range: min.-128 ...]
   * @param cellBackground used to obtain the image
   * @return CellBackgroundInfo.
   */
	private CellBackgroundInfo drawCell(Graphics g, int cellPositionRow, int cellPositionColumn, int pointRow, int pointColumn, ICellBackgroundProvider cellBackgroundProvider) {
		
		CellBackgroundInfo currentCellBackgroundInfo = cellBackgroundInfoTab[cellPositionRow][cellPositionColumn];
		
		ArrayList<BaseFrame> baseFrames = null;
		
		if (currentCellBackgroundInfo.isChanged()) {
			
			baseFrames = cellBackgroundProvider.getCellInfo(currentCellBackgroundInfo.getKeyImages());
			currentCellBackgroundInfo.setBaseFrame(baseFrames);
			currentCellBackgroundInfo.setChanged(false);
			
		} else {
			
			if (!hashSetVisibleCellsInfo.contains(currentCellBackgroundInfo)) {
				
				baseFrames = cellBackgroundProvider.getCellInfo(currentCellBackgroundInfo.getKeyImages());
				currentCellBackgroundInfo.setBaseFrame(baseFrames);				
				hashSetVisibleCellsInfo.add(currentCellBackgroundInfo);
			}
		}
		
		cellBackgroundProvider.updateBackgroundCell(currentCellBackgroundInfo.getBaseFrames(), currentCellBackgroundInfo.getKeyImages());
		
		for(BaseFrame baseFrame : currentCellBackgroundInfo.getBaseFrames()) {
			DrawManager.drawImage(g, baseFrame.getImage(), pointColumn, pointRow, baseFrame.getImage().getWidth(), baseFrame.getImage().getHeight());
		}
		
		if (panelLayoutManager.isDisplayBackgroundCells() || CmnGlobals.CELL_IN_BACKGROUND) {
			DrawManager.drawRect(g, pointColumn/* + stateInfoGame.getViewLayout().getLeftBorderLayout()*/, pointRow/* + stateInfoGame.getViewLayout().getTopBorderLayout()*/,
					currentCellBackgroundInfo.getCellWidth(), currentCellBackgroundInfo.getCellHeight(), Color.YELLOW);
			
			int width, height;
	    	Polygon p;
	    	p=new Polygon();
	    	
	    	//assigning values to variables
	    	width = currentCellBackgroundInfo.getCellRow();
	        height = currentCellBackgroundInfo.getCellColumn();
	        
			p.addPoint( pointColumn + (width/2), pointRow);
	        p.addPoint( pointColumn + width, pointRow + (height/2));
	        p.addPoint( pointColumn + (width/2), pointRow + height);
	        p.addPoint( pointColumn, pointRow + (height/2));

	        g.drawPolygon(p);
	        
		}
		
		if (panelLayoutManager.isDisplayBackgroundPositionCells() || CmnGlobals.CELL_IN_BACKGROUND) {
			g.drawString((cellPositionRow -1) + "|" + ((cellPositionRow % 2 == 0) ? cellPositionColumn-1 : cellPositionColumn), 
					pointColumn + (currentCellBackgroundInfo.getCellWidth() * 2) - 10, 
					pointRow + (currentCellBackgroundInfo.getCellHeight() * 2));
		}
		
		
		if (panelLayoutManager.isDisplayBackgroundCells() || CmnGlobals.CELL_IN_ROAD) {
			DrawManager.drawRect(g, pointColumn/* + stateInfoGame.getViewLayout().getLeftBorderLayout()*/, pointRow/* + stateInfoGame.getViewLayout().getTopBorderLayout()*/,
					currentCellBackgroundInfo.getCellWidth(), currentCellBackgroundInfo.getCellHeight(), Color.YELLOW);
			
			int width, height;
	    	Polygon p;
	    	p=new Polygon();
	    	
	    	//assigning values to variables
	    	width = currentCellBackgroundInfo.getCellRow();
	        height = currentCellBackgroundInfo.getCellColumn();
	        
			p.addPoint( pointColumn + (width/2), pointRow);
	        p.addPoint( pointColumn + width, pointRow + (height/2));
	        p.addPoint( pointColumn + (width/2), pointRow + height);
	        p.addPoint( pointColumn, pointRow + (height/2));

	        g.drawPolygon(p);
	        
		}
			
		
		if (panelLayoutManager.isDisplayRoadPositionCells() || CmnGlobals.CELL_IN_ROAD) {
			g.drawString((cellPositionRow -1) + "|" + ((cellPositionRow % 2 == 0) ? cellPositionColumn-1 : cellPositionColumn), 
					pointColumn + (currentCellBackgroundInfo.getCellWidth() * 2) - 10, 
					pointRow + (currentCellBackgroundInfo.getCellHeight() * 2));
		}
		
		return currentCellBackgroundInfo;
		
	}
	
	public void drawBackground(Graphics g, ICellBackgroundProvider cellBackgroundProvider, StateInfoGame stateInfoGame) {

		if (cellBackgroundInfoTab == null) {
			return;
		}
		
		//prepare set collection for not visible element
		Set<CellBackgroundInfo> hashSetDeleteCellsInfo = new HashSet<CellBackgroundInfo>();
		//collection for not visible cells
		hashSetDeleteCellsInfo.addAll(hashSetVisibleCellsInfo);
		
		cellBackgroundProvider.clearFirstUpdate();
		
		//position of the tile on the Y axis (single cell)
		int tileY = 0;
						
		//start position the camera in tile: divide position of camera Y by Height (position of row(Y) will be calculated) e.g. 2000/128
		int currentTileRow = stateInfoGame.getCameraMapInfo().getRealCameraY() / stateInfoGame.getWorldMapInfo().getHeightCell();
		final int startColumn = stateInfoGame.getCameraMapInfo().getRealCameraX() / stateInfoGame.getWorldMapInfo().getWidthCell();
			
		//start position the camera in axis X
		final int positionY = stateInfoGame.getCameraMapInfo().getRealCameraY() + middleBackgroundHeightCell - stateInfoGame.getViewLayout().getTopBorderLayout();
		//start position the camera in axis Y
		final int positionX = stateInfoGame.getCameraMapInfo().getRealCameraX() - stateInfoGame.getViewLayout().getLeftBorderLayout();
		
		final int mapLayoutWidth = stateInfoGame.getViewLayout().getViewWidth() + stateInfoGame.getViewLayout().getLeftBorderLayout();
		final int mapLayoutHeight = stateInfoGame.getViewLayout().getViewHeight() + stateInfoGame.getViewLayout().getTopBorderLayout();

		while ((tileY = ((currentTileRow * middleBackgroundHeightCell) - positionY)) < mapLayoutHeight) {
			
			final boolean isMod = (currentTileRow % 2 == 0);
			
			//position of the tile in the column table
			int currentTileColumn = startColumn;
			
			//position of the tile on the X axis (single cell)
			int tileX = 0;
			
			while ((tileX = ((currentTileColumn * stateInfoGame.getWorldMapInfo().getWidthCell()) - positionX) - (isMod ? middleBackgroundWidthCell : 0)) < mapLayoutWidth) {
									
				CellBackgroundInfo currentCellBackgroundInfo = drawCell(g, currentTileRow, currentTileColumn, tileY, tileX, cellBackgroundProvider);
				
				hashSetDeleteCellsInfo.remove(currentCellBackgroundInfo);
				
				currentTileColumn++;
			}
			
			currentTileRow++;
		}
		
		//remove all cells from current visible view which are not visible
		hashSetVisibleCellsInfo.removeAll(hashSetDeleteCellsInfo);			
			
	
	}
	
	public void setBackgroundCell(int row, int column, ArrayList<Integer> value) {
		
		cellBackgroundInfoTab[row][column].setKeyImages(value);

	}
	
	public void redrawBackground(ArrayList<Integer> value) {

		for (int row = 0; row < cellBackgroundInfoTab.length; row++) {
			for (int column = 0; column < cellBackgroundInfoTab[row].length; column++) {
			
				if (cellBackgroundInfoTab[row][column] != null) {
					cellBackgroundInfoTab[row][column].setChanged(true);
					cellBackgroundInfoTab[row][column].setKeyImages(value);
				}
			}
		}
	}
		
	public CellBackgroundInfo[][] getCellBackgroundInfoTab() {
		return cellBackgroundInfoTab;
	}
	
	public void setCellBackgroundInfoTab(CellBackgroundInfo[][] cellBackgroundInfoTab) {
		this.cellBackgroundInfoTab = cellBackgroundInfoTab;
	}
	
	public IPanelLayoutManager getPanelLayoutManager() {
		return panelLayoutManager;
	}
	
	public void setPanelLayoutManager(IPanelLayoutManager panelLayoutManager) {
		this.panelLayoutManager = panelLayoutManager;
	}
	
}
