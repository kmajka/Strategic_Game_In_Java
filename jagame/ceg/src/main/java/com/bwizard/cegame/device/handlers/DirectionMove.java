package com.bwizard.cegame.device.handlers;

/*
 * This enumerator provides information about direction of moving the map
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum DirectionMove {

	MOVE_DOWN("MOVE_DOWN"),
	MOVE_UP("MOVE_UP"),
	MOVE_LEFT("MOVE_LEFT"),
	MOVE_RIGHT("MOVE_RIGHT"),
	NONE("NONE");
	
	private final String directionMove;
	
	private DirectionMove(String directionMove) {
		this.directionMove = directionMove;
	}
	
	public String getDirectionMove() {
        return directionMove;
    }
}