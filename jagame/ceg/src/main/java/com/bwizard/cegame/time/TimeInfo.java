package com.bwizard.cegame.time;

/*
 * This class stores information about time in game
 * @author Krzysztof Majka
 * @version 1.0
 */

public class TimeInfo {
	
	//time which was elapsed from last tick
	private long elapsedTime = 0;
	//time wich was elapsed from beginning
	private long allTime = 0;
	
	public long getAllTime() {
		return allTime;
	}

	public void setAllTime(long allTime) {
		this.allTime = allTime;
	}

	public TimeInfo() {
		this.setElapsedTime(0);
	}
	
	public TimeInfo(long elapsedTime) {
		this.setElapsedTime(elapsedTime);
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	@Override
	public String toString() {
		return String.valueOf(elapsedTime);
	}
}
