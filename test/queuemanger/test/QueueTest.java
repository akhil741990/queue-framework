package queuemanger.test;

import queuemanger.core.QueueManger;
import queuemanger.queues.BoundedQ;
import user.UnderWriter;
import user.User;

public class QueueTest {


	public static void main(String args[]){
		BoundedQ<LoanApp> q = new BoundedQ<>(10);
		QueueManger<LoanApp> qMgr = new QueueManger<>(); 
		User a = new UnderWriter<LoanApp>(qMgr, "A");
		User b = new UnderWriter<LoanApp>(qMgr, "B");
		User c = new UnderWriter<LoanApp>(qMgr, "C");
		q.subscribe(a);
		q.subscribe(b);
		q.subscribe(c);
		
		Thread t = new Thread(new TaskEnqueuer(q));
		t.start();
		
		
	}
}
