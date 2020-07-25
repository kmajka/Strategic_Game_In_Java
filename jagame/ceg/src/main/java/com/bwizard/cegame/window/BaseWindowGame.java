package com.bwizard.cegame.window;

import com.bwizard.cegame.window.handlers.WindowGameActionName;

public class BaseWindowGame {
	
	private WindowGameActionName actionName;
	private WindowGameActionName hideActionName;
	
	public BaseWindowGame() {
		this.hideActionName = WindowGameActionName.NONE;
	}
	
	public BaseWindowGame(WindowGameActionName actionName) {
		super();
		this.setActionName(actionName);
	}
	
	//transfer of hidden parameter
	public void refresh() {
		if (!WindowGameActionName.NONE.equals(hideActionName)) {
			actionName = hideActionName;
		}
		this.hideActionName = WindowGameActionName.NONE;
	}

	public WindowGameActionName getActionName() {
		return actionName;
	}

	public void setActionName(WindowGameActionName actionName) {
		this.actionName = actionName;
	}

	public WindowGameActionName getHideActionName() {
		return hideActionName;
	}

	public void setHideActionName(WindowGameActionName hideActionName) {
		this.hideActionName = hideActionName;
	}

}
