package com.bwizard.wsgame.documents.layout;

import com.bwizard.wsgame.world.figure.buildings.Box;
import com.bwizard.wsgame.world.figure.buildings.SimpleCastle;
import com.bwizard.wsgame.world.figure.buildings.SimpleHouse;
import com.bwizard.wsgame.world.figure.buildings.SimpleWindmill;
import com.bwizard.wsgame.world.figure.handlers.FigureName;
import com.bwizard.wsgame.world.figure.handlers.HouseType;
import com.bwizard.wsgame.world.figure.handlers.StoneType;
import com.bwizard.wsgame.world.figure.items.SimpleFire;
import com.bwizard.wsgame.world.figure.nature.SimpleFlower;
import com.bwizard.wsgame.world.figure.nature.SimpleSea;
import com.bwizard.wsgame.world.figure.nature.SimpleStone;
import com.bwizard.wsgame.world.figure.nature.SimpleTree;

import java.util.ArrayList;
import java.util.HashMap;

import com.bwizard.cegame.documents.handlers.LayoutAttribute;
import com.bwizard.cegame.documents.interfaces.ICmnDocument;
import com.bwizard.cegame.figure.BaseFigure;
import com.bwizard.cegame.utils.StringUtil;

public class DocumentFigureLayout implements ICmnDocument<BaseFigure> {
		
	public DocumentFigureLayout() {
	}
	
	@Override
	public ArrayList<BaseFigure> createElementFromCellAttribute(
			BaseFigure parentElement,
			HashMap<String, String> hashCellAttribute) {
		
		ArrayList<BaseFigure> list = new ArrayList<BaseFigure>();
		String relationObject;
		BaseFigure component = null;
		
		String figure = hashCellAttribute.get(LayoutAttribute.FIGURE);
		if (StringUtil.isNotEmpty(figure)) {
			FigureName figureName = FigureName.fromString(figure);
			
			switch (figureName) {
				case SIMPLE_FIRE:
					component = new SimpleFire();
					break;
				case SIMPLE_TREE:
					component = new SimpleTree();	
					break;
				case SIMPLE_CASTLE:
					component = new SimpleCastle();
					break;
				case BOX:
					component = new Box();
					break;
				case SIMPLE_HOUSE:
					relationObject = hashCellAttribute.get(LayoutAttribute.RELATION_OBJECT);
					HouseType houseType = HouseType.fromString(relationObject);
					component = new SimpleHouse(houseType);
					break;
				case SIMPLE_WINDMILL:
					component = new SimpleWindmill();
					break;
				case SIMPLE_STONE:
					relationObject = hashCellAttribute.get(LayoutAttribute.RELATION_OBJECT);
					StoneType stoneType = StoneType.fromString(relationObject);
					component = new SimpleStone(stoneType);
					break;
				case SIMPLE_FLOWER:
					component = new SimpleFlower();
					break;
				case SIMPLE_SEA:
					component = new SimpleSea();
					break;
				default:
					break;
			}
			
			if(component == null) {
				return list;
			}
			
			String positionX = hashCellAttribute.get(LayoutAttribute.POSITION_X);
			if (StringUtil.isNotEmpty(positionX)) {
				int tmp = Integer.valueOf(positionX);
				component.setMapPositionX(tmp);
			}
			
			String positionY = hashCellAttribute.get(LayoutAttribute.POSITION_Y);
			if (StringUtil.isNotEmpty(positionY)) {
				int tmp = Integer.valueOf(positionY);
				component.setMapPositionY(tmp);
			}
			
			if (StringUtil.isNotEmpty(positionX) && StringUtil.isNotEmpty(positionY)) {
				component.intitializePositionIntoMap();
			}
		}
		
		list.add(component);
		
		return list;
	}
	  
}
