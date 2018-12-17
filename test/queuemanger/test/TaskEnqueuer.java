package queuemanger.test;

import queue.framework.exception.TaskNotQueued;
import queuemanger.queues.BoundedQ;

public class TaskEnqueuer implements Runnable{

	BoundedQ<LoanApp> q;
	public TaskEnqueuer(BoundedQ<LoanApp> q) {
		this.q = q;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("start");
		for(int i = 0 ;i < 100;i++) {
			LoanApp t = new LoanApp(i);
			try {
				q.add(t);
			} catch (TaskNotQueued e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i%10 == 0) {
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("End");
	}

}
