package com.bwizard.wsgame.world.figure.buildings;

import com.bwizard.wsgame.world.figure.GameFigure;
import com.bwizard.wsgame.world.figure.handlers.HouseType;

import com.bwizard.cegame.state.StateInfoManager;

public class SimpleHouse extends GameFigure {

	public SimpleHouse(HouseType houseType) {
		initialize(houseType);
	}
	
	public SimpleHouse(int x, int y, StateInfoManager stateInfoManager, HouseType houseType) {
		super(x , y, stateInfoManager);
		initialize(houseType);
	}
	
	private void initialize(HouseType houseType) {
		
		canFocus(true);
		
		switch (houseType) {
			case HOUSE_1:
				frameManager.addFrame(getImageSprite("f_house.png"), 0);
				break;
			case HOUSE_2:
				frameManager.addFrame(getImageSprite("kostka.png"), 0);
				break;
			default:
				break;
			
		}
	}

}
