package com.bwizard.wsgame.world.figure.nature;

import com.bwizard.wsgame.world.figure.GameFigure;
import com.bwizard.cegame.state.StateInfoGame;

public class SimpleTree extends GameFigure {
	
	public SimpleTree() {
		initialize();
	}
	
	public SimpleTree(int x, int y, StateInfoGame stateInfoGame) {
		super(x , y, stateInfoGame);
		setName("SimpleTree");
		
		initialize();
	}
	
	@Override
	public void updateEditor() {
		setPlacedManualy(true);
		canMove(false);
		canFocus(true);
	}
	
	private void initialize() {
		
		long timeTree = (int)(Math.random() * 90) + 250;
		
		frameManager.addFrame(getImageSprite("f_tree1.png"), 5*timeTree);
		frameManager.addFrame(getImageSprite("f_tree2.png"), timeTree);
		frameManager.addFrame(getImageSprite("f_tree3.png"), timeTree);
		frameManager.addFrame(getImageSprite("f_tree4.png"), 3*timeTree);
		frameManager.addFrame(getImageSprite("f_tree3.png"), timeTree);
		frameManager.addFrame(getImageSprite("f_tree2.png"), timeTree);
	}
	
}

