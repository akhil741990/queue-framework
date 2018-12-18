package queuemanger.core;

import java.io.Serializable;

/**
 * The base Class for all the tasks that will be enqueued in 
 * to the Queues.
 * It maintains fields like timestamp and the name of the currentQ in which 
 * the task is enqueued
 */
public abstract class Task implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currentQ;
	private long timestamp;
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getCurrentQ() {
		return currentQ;
	}

	public void setCurrentQ(String currentQ) {
		this.currentQ = currentQ;
	} 
}
