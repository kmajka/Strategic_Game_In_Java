package com.bwizard.wsgame.world.map.cell;

import com.bwizard.wsgame.parameters.GameGlobals;
import com.bwizard.wsgame.world.map.handlers.BackgroundCellName;

import java.awt.image.BufferedImage;

import com.bwizard.cegame.frame.BaseFrame;
import com.bwizard.cegame.state.StateInfoGame;
import com.bwizard.cegame.utils.ImageUtil;

//SeaBlue_<inside_num_tile>_<outside_num_tile>_Background_<version>
public class Water extends BaseFrame {

	public Water(StateInfoGame stateInfoGame, int cellWidth,
			int cellHeight, BackgroundCellName keyCellName) {
		
		initialize(keyCellName);
	}

	@Override
	protected BufferedImage getImageSprite(String file) {
		return ImageUtil.loadImage(GameGlobals.IMAGE_PATH + "water/sea/" + file);
	}
	
	private void initialize(BackgroundCellName keyCellName) {
		
		long time = 0; 
		
		switch (keyCellName) {
			case SEABLUE_0_0_BACKGROUND_1:
				time = 200;
				
				//sea_blue_<inside_num_tile>_<outside_num_tile>_<num_animation>_background_<version>.png
				frameManager.addFrame(getImageSprite("sea_blue_0_0_1_background_1.png"), time);				
				frameManager.addFrame(getImageSprite("sea_blue_0_0_2_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_3_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_4_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_5_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_6_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_7_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_8_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_9_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_10_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_11_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_0_0_12_background_1.png"), time);
				break;
			case  SEABLUE_2_2_BACKGROUND_1:
				time = 500;

				frameManager.addFrame(getImageSprite("sea_blue_2_2_1_background_1.png"), time);
				break;
			case SEABLUE_1_1_BACKGROUND_1:
				time= 500;
				
				frameManager.addFrame(getImageSprite("sea_blue_1_1_1_background_1.png"), time);
				break;
			case SEABLUE_1_2_BACKGROUND_1:
				time= 500;

				frameManager.addFrame(getImageSprite("sea_blue_1_2_1_background_1.png"), time);
				break;
			case SEABLUE_2_3_BACKGROUND_1:
				time= 500;
				
				frameManager.addFrame(getImageSprite("sea_blue_2_3_1_background_1.png"), time);
				break;
			case SEABLUE_4_4_BACKGROUND_1:
				time= 500;

				frameManager.addFrame(getImageSprite("sea_blue_4_4_1_background_1.png"), time);
				break;
			case SEABLUE_3_3_BACKGROUND_1:
				time= 500;
				
				frameManager.addFrame(getImageSprite("sea_blue_3_3_1_background_1.png"), time);
				break;
			case SEABLUE_3_4_BACKGROUND_1:
				time= 500;
				
				frameManager.addFrame(getImageSprite("sea_blue_3_4_1_background_1.png"), time);
				break;
			case SEABLUE_4_2_BACKGROUND_1:
				time= 300;
				
				frameManager.addFrame(getImageSprite("sea_blue_4_2_1_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_2_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_3_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_4_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_5_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_6_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_5_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_4_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_3_background_1.png"), time);
				frameManager.addFrame(getImageSprite("sea_blue_4_2_2_background_1.png"), time);
				break;
			case SEABLUE_4_1_BACKGROUND_1:
				time= 500;
				
				frameManager.addFrame(getImageSprite("sea_blue_4_1_1_background_1.png"), time);
				break;
			default:
				break;
		}
		
	}
	
}