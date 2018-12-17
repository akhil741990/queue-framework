package queuemanger.queues;

import java.util.concurrent.LinkedBlockingQueue;

import queue.framework.exception.TaskNotQueued;
import queuemanger.core.Task;
import user.User;

public class UnBoundedQ<T extends Task> extends AbstractQ<T>{

	LinkedBlockingQueue<T> queue;
	public UnBoundedQ(String name) {
		super(name);
		this.queue = new LinkedBlockingQueue<>();
	}
	@Override
	public void subscribe(User<T> user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(T task) throws TaskNotQueued {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExpiryInMs(long expiry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispatchTask(T task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T deQ() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

}
