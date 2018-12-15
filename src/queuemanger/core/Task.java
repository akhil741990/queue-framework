package queuemanger.core;

import java.io.Serializable;

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
