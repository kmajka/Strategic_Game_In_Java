package com.bwizard.cegame.configuration.components;

import com.bwizard.cegame.configuration.components.interfaces.IBasicConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.ICameraConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.ICellConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.IFigureConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.IKeyConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.IMapConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.IMouseConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.ISoundConfiguration;
import com.bwizard.cegame.configuration.components.interfaces.IVideoConfiguration;

public class BaseConfiguration {
		
	protected IBasicConfiguration basicConfiguration = null;
	
	protected ICameraConfiguration cameraConfiguration = null;
	protected ICellConfiguration cellConfiguration = null;
	protected IMapConfiguration mapConfiguration = null;
	protected IFigureConfiguration figureConfiguration = null;
	
	protected IVideoConfiguration videoConfiguration = null;
	protected ISoundConfiguration soundConfiguration = null;
	protected IKeyConfiguration keyConfiguration = null;
	protected IMouseConfiguration mouseConfiguration = null;

	public BaseConfiguration() {
	}
	
	public IBasicConfiguration getBasicConfiguration() {
		return basicConfiguration;
	}
	
	public ICameraConfiguration getCameraConfiguration() {
		return cameraConfiguration;
	}
	
	public ICellConfiguration getCellConfiguration() {
		return cellConfiguration;
	}
	
	public IVideoConfiguration getVideoConfiguration() {
		return videoConfiguration;
	}
	
	public ISoundConfiguration getSoundConfiguration() {
		return soundConfiguration;
	}
	
	public IKeyConfiguration getKeyConfiguration() {
		return keyConfiguration;
	}

	public IMouseConfiguration getMouseConfiguration() {
		return mouseConfiguration;
	}
	
	public IFigureConfiguration getFigureConfiguration() {
		return figureConfiguration;
	}
		
}
