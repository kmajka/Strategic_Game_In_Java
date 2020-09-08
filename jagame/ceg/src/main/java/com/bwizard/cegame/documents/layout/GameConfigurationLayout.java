package com.bwizard.cegame.documents.layout;

import java.util.ArrayList;
import java.util.HashMap;

import com.bwizard.cegame.configuration.components.BaseConfiguration;
import com.bwizard.cegame.configuration.components.CameraConfiguration;
import com.bwizard.cegame.configuration.components.CellConfiguration;
import com.bwizard.cegame.configuration.components.FigureConfiguration;
import com.bwizard.cegame.configuration.components.KeyConfiguration;
import com.bwizard.cegame.configuration.components.MapConfiguration;
import com.bwizard.cegame.configuration.components.MouseConfiguration;
import com.bwizard.cegame.configuration.components.SoundConfiguration;
import com.bwizard.cegame.configuration.components.VideoConfiguration;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.documents.handlers.ConfigurationName;
import com.bwizard.cegame.documents.handlers.LayoutAttribute;
import com.bwizard.cegame.documents.interfaces.ICmnDocument;
import com.bwizard.cegame.utils.BooleanUtil;
import com.bwizard.cegame.utils.StringUtil;

public class GameConfigurationLayout implements ICmnDocument<BaseConfiguration> {

	@Override
	public ArrayList<BaseConfiguration> createElementFromCellAttribute(
			BaseConfiguration parentElement,
			HashMap<String, String> hashCellAttribute) {
		
		ArrayList<BaseConfiguration> list = new ArrayList<BaseConfiguration>();
		BaseConfiguration component = null;
		
		String configuration = hashCellAttribute.get(LayoutAttribute.CONFIGURATION);
		if (StringUtil.isNotEmpty(configuration)) {
			ConfigurationName configurationName = ConfigurationName.fromString(configuration);
			switch (configurationName) {
				case MAP:
					component = new MapConfiguration();
					break;
				case CAMERA:
					component = new CameraConfiguration();
					break;
				case CELL:
					component = new CellConfiguration();
					break;
				case VIDEO:
					component = new VideoConfiguration();
					break;
				case SOUND:
					component = new SoundConfiguration();
					break;
				case KEY:
					component = new KeyConfiguration();
					break;
				case MOUSE:
					component = new MouseConfiguration();
					break;
				case FIGURE:
					component = new FigureConfiguration();
					break;
				default:
					break;
			}
			
			if (component == null) {
				return list;
			}
			
			String positionX = hashCellAttribute.get(LayoutAttribute.POSITION_X);
			if (StringUtil.isNotEmpty(positionX)) {
				int tmp = Integer.valueOf(positionX);
				component.getBasicConfiguration().setPositionX(tmp);
			}
			
			String positionY = hashCellAttribute.get(LayoutAttribute.POSITION_Y);
			if (StringUtil.isNotEmpty(positionY)) {
				int tmp = Integer.valueOf(positionY);
				component.getBasicConfiguration().setPositionY(tmp);
			}
			
			String height = hashCellAttribute.get(LayoutAttribute.HEIGHT);
			if (StringUtil.isNotEmpty(height)) {
				int tmp = Integer.valueOf(height);
				component.getBasicConfiguration().setHeight(tmp);
			}
			
			String width = hashCellAttribute.get(LayoutAttribute.WIDTH);
			if (StringUtil.isNotEmpty(width)) {
				int tmp = Integer.valueOf(width);
				component.getBasicConfiguration().setWidth(tmp);
			}
			
			String name = hashCellAttribute.get(LayoutAttribute.NAME);
			if (StringUtil.isNotEmpty(name)) {
				component.getBasicConfiguration().setName(name);
			}
			
			String descriptionMap = hashCellAttribute.get(LayoutAttribute.DESCRIPTION_MAP);
			if (StringUtil.isNotEmpty(descriptionMap)) {
				component.getBasicConfiguration().setDescriptionMap(descriptionMap);
			}
			
			String imageMap = hashCellAttribute.get(LayoutAttribute.IMAGE_MAP);
			if (StringUtil.isNotEmpty(imageMap)) {
				component.getBasicConfiguration().setImageMap(imageMap);
			}
			
			String imageDpi = hashCellAttribute.get(LayoutAttribute.IMAGE_DPI);
			if (StringUtil.isNotEmpty(imageDpi)) {
				component.getVideoConfiguration().setImageDpi(imageDpi);
			}			
			
			String windowPositionX = hashCellAttribute.get(LayoutAttribute.WINDOW_POSITION_X);
			if (StringUtil.isNotEmpty(windowPositionX)) {
				int tmp = Integer.valueOf(windowPositionX);
				component.getVideoConfiguration().setWindowPositionX(tmp);
			}
			
			String windowPositionY = hashCellAttribute.get(LayoutAttribute.WINDOW_POSITION_Y);
			if (StringUtil.isNotEmpty(windowPositionY)) {
				int tmp = Integer.valueOf(windowPositionY);
				component.getVideoConfiguration().setWindowPositionY(tmp);
			}
			
			String heightResolution = hashCellAttribute.get(LayoutAttribute.HEIGHT_RESOLUTION);
			if (StringUtil.isNotEmpty(heightResolution)) {
				int tmp = Integer.valueOf(heightResolution);
				component.getVideoConfiguration().setHeightResolution(tmp);
			}
			
			String widthResolution = hashCellAttribute.get(LayoutAttribute.WIDTH_RESOLUTION);
			if (StringUtil.isNotEmpty(widthResolution)) {
				int tmp = Integer.valueOf(widthResolution);
				component.getVideoConfiguration().setWidthResolution(tmp);
			}
			
			String figureName = hashCellAttribute.get(LayoutAttribute.FIGURE);
			if (StringUtil.isNotEmpty(figureName)) {
				BaseDrawFigure figure = GameLayoutTool.getComponentFigure(figureName);
				if(figure != null) {
					component.getFigureConfiguration().setFigure(figure);
					
					GameLayoutTool.updateFigure(figure, hashCellAttribute);
				}
			}
			
			String windowedMode = hashCellAttribute.get(LayoutAttribute.WINDOWED_MODE);
			if (StringUtil.isNotEmpty(windowedMode)) {
				component.getVideoConfiguration().setWindowedMode(BooleanUtil.getValue(windowedMode));
			}
			
			String fullScreen = hashCellAttribute.get(LayoutAttribute.FULL_SCREEN);
			if (StringUtil.isNotEmpty(fullScreen)) {
				component.getVideoConfiguration().setFullScreen(BooleanUtil.getValue(fullScreen));
			}
			
		}
		
		list.add(component);
		
		return list;
	}
	  
}

