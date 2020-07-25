package com.bwizard.cegame.game.world.map.cell.interfaces;

import java.util.ArrayList;

import com.bwizard.cegame.frame.BaseFrame;

public interface ICellBackgroundProvider {
	
	ArrayList<BaseFrame> getCellInfo(ArrayList<Integer> keyImage);
	void updateBackgroundCell(ArrayList<BaseFrame> baseFrames, ArrayList<Integer> keyImages);
	void clearFirstUpdate();
	
}
