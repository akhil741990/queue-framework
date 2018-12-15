package queuemanger.core;

import user.User;

public interface Queue<T extends Task> {

	
	public void  subscribe(User<T> user);
	public void add(T task);
	public void setExpiryInMs(long expiry);
	public void dispatchTask(T task);
}
