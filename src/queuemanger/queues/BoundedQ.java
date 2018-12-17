package queuemanger.queues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import queue.framework.exception.TaskNotQueued;
import queuemanger.core.Queue;
import queuemanger.core.Task;
import user.User;

public class BoundedQ<T extends Task> implements Queue<T>{

	
	private List<User<T>> subscribers;
	private int dispatchIndex;
	private ArrayBlockingQueue<T> queue;
	private long expiry;  // TODO : Add TimeUnit
	private Thread dispatcherThread;
	public BoundedQ(int capacity){
		subscribers = new ArrayList<>();
		queue = new ArrayBlockingQueue<>(capacity);
		dispatcherThread = new Thread(new DispatcherThread());
		dispatcherThread.start();
	}
	@Override
	public void subscribe(User<T> user) {
		this.subscribers.add(user);
		
	}

	@Override
	public void add(T task) throws TaskNotQueued {
		task.setTimestamp(System.currentTimeMillis());
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
 		user.process(task);
	}

	
	class DispatcherThread  implements Runnable{

		@Override
		public void run() {
			System.out.println("Dispatcher Thread Started ");
			while(true) {
				try {
					T task = queue.take();
					dispatchTask(task);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
}
