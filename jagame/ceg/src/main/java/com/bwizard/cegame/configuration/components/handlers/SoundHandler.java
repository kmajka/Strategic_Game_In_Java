package com.bwizard.cegame.configuration.components.handlers;

import com.bwizard.cegame.configuration.components.interfaces.ISoundConfiguration;

public class SoundHandler implements ISoundConfiguration {

	private int volume;

	@Override
	public int getVolume() {
		return volume;
	}

	@Override
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
}
