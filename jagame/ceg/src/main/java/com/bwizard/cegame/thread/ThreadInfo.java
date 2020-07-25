package com.bwizard.cegame.thread;

/*
 * This class stores information about the main thread in game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class ThreadInfo {
	
	private ThreadStatus threadStatus = null;
	private ThreadStatus nextThreadStatus = null;
	private byte oneCircuit = 0;
	
	public ThreadInfo(ThreadStatus threadStatus) {
		this.threadStatus = threadStatus;
	}
	public ThreadStatus getThreadStatus() {
		return threadStatus;
	}
	public void setThreadStatus(ThreadStatus threadStatus) {
		oneCircuit = 0;
		this.nextThreadStatus = threadStatus;
	}
	
	public void waitForUpdateThreadStatus() {
		
		try {
			while(nextThreadStatus != threadStatus) {
				Thread.sleep(20);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isThreadStatusDifferent() {
		return nextThreadStatus != threadStatus;
	}
	
	public void updateThreadStatus() {
		if (nextThreadStatus != threadStatus) {
			//after changing thread status at least one circuit loop is needed for updated collection - cleanUnusedResources
			if (oneCircuit > 2) {
				threadStatus = nextThreadStatus;
				oneCircuit = 0;
			} else {
				oneCircuit++;
			}
		} else {
			oneCircuit = 0;
		}
	}

}
