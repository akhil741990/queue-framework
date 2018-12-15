package queuemanger.core;

import java.util.Iterator;
import java.util.LinkedHashMap;

import queue.framework.exception.ApplicationRejected;
import queue.framework.exception.QueueNotFound;
import user.User;

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
	
	public void addQToHierrarchy(String queueName, Queue<T> q){
		this.qHierrachy.put(queueName, q);
	}

	public void approve(T app) throws QueueNotFound{
		String currentQ  = app.getCurrentQ();
		String nextQ  = getUpNextQueue(currentQ);
		if(nextQ == null) { // Last queue in the hierrarchy
			notifyLoanDisbural(app);
		}
		this.qHierrachy.get(nextQ).add(app);
	}
	
	public void dissapprove(T app) throws QueueNotFound, ApplicationRejected{
		String currentQ = app.getCurrentQ();
		String nextQ = getDownNextQueue(currentQ);
		if(nextQ == null){
			throw new ApplicationRejected("Loan Application pushed out from the lowest Quueue", app);
		}
		this.qHierrachy.get(nextQ).add(app);
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
		return app;
	}
}
