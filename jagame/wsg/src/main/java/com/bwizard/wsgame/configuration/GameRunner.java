package com.bwizard.wsgame.configuration;

public class GameRunner {

	public void start() {
		GameConfiguration gameConfigurationBuilder = new GameConfiguration();
		gameConfigurationBuilder.invoke("Game_Menu.xml");
	}
}