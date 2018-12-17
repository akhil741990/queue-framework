package queuemanger.queues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import queue.framework.exception.ApplicationRejected;
import queue.framework.exception.QueueNotFound;
import queue.framework.exception.TaskNotQueued;
import queuemanger.core.Task;
import user.User;

public class BoundedQ<T extends Task> extends AbstractQ<T>{

	
	private List<User<T>> subscribers;
	private int dispatchIndex;
	private ArrayBlockingQueue<T> queue;
	private long expiry;  // TODO : Add TimeUnit
	private String name;
	public BoundedQ(String name, int capacity){
		super();
		this.name = name;
		subscribers = new ArrayList<>();
		queue = new ArrayBlockingQueue<>(capacity);
		startDispactherThread();
	}
	@Override
	public void subscribe(User<T> user) {
		this.subscribers.add(user);
		
	}

	@Override
	public void add(T task) throws TaskNotQueued {
		task.setTimestamp(System.currentTimeMillis());
		task.setCurrentQ(this.getName());
		try {
			queue.put(task);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new TaskNotQueued(e.getMessage());
		}
		
	}

	@Override
	public void setExpiryInMs(long expiry) {
		this.expiry = expiry;
	}
	@Override
	public void dispatchTask(T task) {
		
		int nextdispatchIndex =  dispatchIndex++ % subscribers.size();
		User<T> user = subscribers.get(nextdispatchIndex); 
		System.out.println("Dispacthed to user:"+user.getName());
 		try {
			user.process(task);
		} catch (QueueNotFound | TaskNotQueued e) {
			// TODO handle error processing and add it back to the relevantQ
			e.printStackTrace();
		} catch (ApplicationRejected e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public T deQ() throws InterruptedException {
		// TODO Auto-generated method stub
		return queue.take();
	}
	
}
