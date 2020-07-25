package com.bwizard.wsgame.world.map.cell;

import com.bwizard.wsgame.parameters.GameGlobals;
import com.bwizard.wsgame.world.map.handlers.BackgroundCellName;

import java.awt.image.BufferedImage;

import com.bwizard.cegame.frame.BaseFrame;
import com.bwizard.cegame.state.StateInfoManager;
import com.bwizard.cegame.utils.ImageUtil;

//SeaBlue_<inside_num_tile>_<outside_num_tile>_Background_<version>
public class Ground extends BaseFrame {

	public Ground(StateInfoManager stateInfoManager, int cellWidth,
			int cellHeight, BackgroundCellName keyCellName) {
		
		initialize(keyCellName);
	}

	@Override
	protected BufferedImage getImageSprite(String file) {
		return ImageUtil.loadImage(GameGlobals.IMAGE_PATH + "ground/grass/" + file);
	}
	
	private void initialize(BackgroundCellName keyCellName) {
		
		long time = 0; 
		
		switch (keyCellName) {
			case GRASSGREEN_0_0_BACKGROUND_1:
				time = 10000;
				
				//grass_green_<inside_num_tile>_<outside_num_tile>_<num_animation>_background_<version>.png
				frameManager.addFrame(getImageSprite("grass_green_0_0_1_background_1.png"), time);
				break;
			case GRASSBRIGHT_0_0_BACKGROUND_1:
				time = 10000;
				
				//grass_bright_<inside_num_tile>_<outside_num_tile>_<num_animation>_background_<version>.png
				frameManager.addFrame(getImageSprite("grass_bright_0_0_1_background_1.png"), time);
				break;
			case GRASSDARK_0_0_BACKGROUND_1:	
				time = 10000;
				
				//grass_dark_<inside_num_tile>_<num_animation>_<num_animation>_background_<version>.png
				frameManager.addFrame(getImageSprite("grass_dark_0_0_1_background_1.png"), time);
			default:
				break;
		}
		
	}
	
}