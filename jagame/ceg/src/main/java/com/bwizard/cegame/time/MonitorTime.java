package com.bwizard.cegame.time;

public class MonitorTime {
	
	private TimeInfo timeInfo = null;
	private long startTime;
	private long currentTime;
	
	public MonitorTime() {
		timeInfo = new TimeInfo();
	}
	
	public void start() {
		this.startTime = System.currentTimeMillis();
		this.currentTime = startTime;
	}
	
	public void refresh() {
		long currentSystemTime = System.currentTimeMillis();
		
		getTimeInfo().setElapsedTime(currentSystemTime - currentTime);
		currentTime = currentSystemTime;				
		getTimeInfo().setAllTime(currentSystemTime - startTime);
	}
	
	public long getStartTime() {
		return startTime;
	}

	public TimeInfo getTimeInfo() {
		return timeInfo;
	}

}
