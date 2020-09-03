package com.bwizard.wsgame.configuration;

import com.bwizard.cegame.thread.ThreadScheduler;

public class GameRunner {

	public void start() {
		ThreadScheduler threadScheduler = new ThreadScheduler(1);
		GameConfigurationBuilder gameConfigurationBuilder = new GameConfigurationBuilder(threadScheduler);
		gameConfigurationBuilder.invoke("Game_Menu.xml");
	}
}