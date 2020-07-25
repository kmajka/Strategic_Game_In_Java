package com.bwizard.cegame.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * This class provides possibility to manages thread in game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class ThreadScheduler {
	
	ExecutorService executor = null;
	
	public ThreadScheduler(int threadNumber) {
		executor = Executors.newFixedThreadPool(threadNumber);
	}

	public void setTask(Runnable task) {
		
		if(task != null) {
			executor.execute(task);
		}
	}
	
	public synchronized void close() {
		executor.shutdown();
	}
}
