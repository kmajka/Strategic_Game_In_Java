package com.bwizard.cegame.thread;

/*
 * This enumerator provides information about status of thread
 * @author Krzysztof Majka
 * @version 1.0
 */
public enum ThreadStatus {
	EXITED("EXITED"),
	RUN("RUN"),
	PAUSED("PAUSED"),
	SUSPEND("SUSPEND"),
	NOTHING("NOTHING");
	
	private final String threadStatus;
	
	private ThreadStatus(String threadStatus) {
		this.threadStatus = threadStatus;
	}
	
	public String getThreadStatus() {
        return threadStatus;
    }
}
