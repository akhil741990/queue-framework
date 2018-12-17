package queuemanger.core;

import queue.framework.exception.TaskNotQueued;
import user.User;

public interface Queue<T extends Task> {

	
	public void  subscribe(User<T> user);
	public void add(T task) throws TaskNotQueued;
	public void setExpiryInMs(long expiry);
	public void dispatchTask(T task);
}
