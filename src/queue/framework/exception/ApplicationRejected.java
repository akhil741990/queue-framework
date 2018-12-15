package queue.framework.exception;

import queuemanger.core.Task;

public class ApplicationRejected extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Task task;
	public ApplicationRejected(String msg, Task task){
		super(msg);
		this.task = task;
		
	}
}
