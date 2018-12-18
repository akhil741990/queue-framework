package queuemanger.queues;

import java.util.ArrayList;
import java.util.List;

import queuemanger.core.Queue;
import queuemanger.core.Task;
import user.User;

/**
 * The base Class for all the queues that will be added into the  
 * queue hierarchy of queue management framework.
 * It holds the users subscribed to the queue and alos provide the default
 * task dispatching implementation
 */

public abstract class AbstractQ<T extends Task> implements Queue<T>{

	protected List<User<T>> subscribers;
	private String name;
	private Thread dispacther;
	protected AbstractQ(String name){
		this.name = name;
		subscribers = new ArrayList<>();
		initDispatcherThread();
	}
	
	protected void initDispatcherThread(){
		dispacther = new Thread(new DispatcherThread());
	}
	
	protected void startDispactherThread(){
		dispacther.start();
	}
	
	@Override
	public  void subscribe(User<T> user) {
		synchronized(this.subscribers){
			this.subscribers.add(user);
		}
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	class DispatcherThread  implements Runnable{

		@Override
		public void run() {
			System.out.println("Dispatcher Thread Started ");
			while(true) {
				try {
					T task = deQ();
					dispatchTask(task);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
}
