package queuemanger.queues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import queuemanger.core.Queue;
import queuemanger.core.Task;
import user.User;

public class BoundedQ<T extends Task> implements Queue<T>{

	
	private List<User<T>> subscribers;
	private ArrayBlockingQueue<T> queue;
	private long expiry;  // TODO : Add TimeUnit
	public BoundedQ(int capacity){
		subscribers = new ArrayList<>();
		queue = new ArrayBlockingQueue<>(capacity);
	}
	@Override
	public void subscribe(User<T> user) {
		this.subscribers.add(user);
		
	}

	@Override
	public void add(T task) {
		task.setTimestamp(System.currentTimeMillis());
		
		
	}

	@Override
	public void setExpiryInMs(long expiry) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispatchTask(T task) {
		
		
	}

}
