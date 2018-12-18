package queuemanger.core;

import java.util.Iterator;
import java.util.LinkedHashMap;

import queue.framework.exception.ApplicationRejected;
import queue.framework.exception.QueueNotFound;
import queue.framework.exception.TaskNotQueued;
import user.User;

/**
 * This class is the entry point into the queue management framework
 * It maintains the hierarchy of the queues. 
 * provides implementation for promotion/demotion of task from one queue to another
 * 
 */
public class QueueManger<T extends Task> {
	
	LinkedHashMap<String, Queue<T>> qHierrachy;
	
	public QueueManger(){
		qHierrachy = new LinkedHashMap<>();
	}
	
	public boolean subscribeToQueue(String queuName, User<T> user){
		
		Queue<T> q = qHierrachy.get(queuName);
		if(q == null){
			return false;
		}
		qHierrachy.get(queuName).subscribe(user);
		return true;
	}
	
	public void addQToHierrarchy(Queue<T> q){
		synchronized(this.qHierrachy){
			this.qHierrachy.put(q.getName(), q);
		}
	}

	public void approve(T app) throws QueueNotFound, TaskNotQueued{
		String currentQ  = app.getCurrentQ();
		String nextQ  = getUpNextQueue(currentQ);
		if(nextQ == null) { // Last queue in the hierrarchy
			notifyLoanDisbural(app);
		}else{
			this.qHierrachy.get(nextQ).add(app);
			System.out.println("Approved - Task promoted from Q :"+currentQ + "to Q:"+nextQ);
		}
	}
	
	public void dissapprove(T app) throws QueueNotFound, ApplicationRejected, TaskNotQueued{
		String currentQ = app.getCurrentQ();
		String nextQ = getDownNextQueue(currentQ);
		if(nextQ == null){
			throw new ApplicationRejected("Loan Application pushed out from the lowest Quueue", app);
		}
		this.qHierrachy.get(nextQ).add(app);
		System.out.println("Disapproved - Task demoted from Q :"+currentQ + "to Q:"+nextQ);
	}
	
	private String getUpNextQueue(String queueName) throws QueueNotFound{
		
		Iterator<String> itr = this.qHierrachy.keySet().iterator();
		String tempQueueName = null;
		String nextQName = null;
		boolean isCurrentQFound = false;
		while(itr.hasNext()){
			tempQueueName = itr.next();
			if(tempQueueName.equals(queueName)){
				isCurrentQFound = true;
				break;
			}
		}
		if(isCurrentQFound){
			if(itr.hasNext()){
				nextQName = itr.next();
			}
		}else{
			throw new QueueNotFound(queueName +" not Found");
		}
		return nextQName;
	}
	
	private String getDownNextQueue(String queueName) throws QueueNotFound{
		
		Iterator<String> itr = this.qHierrachy.keySet().iterator();
		String tempQueueName = null;
		String previousQName = null;
		boolean isCurrentQFound = false;
		while(itr.hasNext()){
			tempQueueName = itr.next();
			if(tempQueueName.equals(queueName)){
				isCurrentQFound = true;
				break;
			}
			previousQName = tempQueueName;
		}
		if(!isCurrentQFound){
			throw new QueueNotFound(queueName +" not Found");
		}
		return previousQName;
	}
	
	public T notifyLoanDisbural(T app){
		System.out.println("Loan Disbursed for Task:"+app.toString());
		return app;
	}
}
