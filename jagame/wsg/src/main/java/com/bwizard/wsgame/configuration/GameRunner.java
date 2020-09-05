package com.bwizard.wsgame.configuration;

public class GameRunner {

	public void start() {
		GameConfigurationBuilder gameConfigurationBuilder = new GameConfigurationBuilder();
		gameConfigurationBuilder.invoke("Game_Menu.xml");
	}
}