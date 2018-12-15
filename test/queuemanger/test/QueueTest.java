package queuemanger.test;

import queuemanger.queues.BoundedQ;

public class QueueTest {


	public static void main(String args[]){
		BoundedQ<LoanApp> q = new BoundedQ<>(10);
	}
}
