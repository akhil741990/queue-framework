package queuemanger.queues;

import queuemanger.core.Queue;
import queuemanger.core.Task;

public abstract class AbstractQ<T extends Task> implements Queue<T>{

	protected AbstractQ(){
		initDispatcherThread();
	}
	Thread dispacther;
	protected void initDispatcherThread(){
		dispacther = new Thread(new DispatcherThread());
	}
	
	protected void startDispactherThread(){
		dispacther.start();
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
