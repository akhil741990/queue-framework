package user;

import queue.framework.exception.ApplicationRejected;
import queue.framework.exception.QueueNotFound;
import queue.framework.exception.TaskNotQueued;
import queuemanger.core.QueueManger;
import queuemanger.core.Task;

public abstract class User<T extends Task> {
	private QueueManger<T> qMgr;
	private String name;
	public String getName() {
		return name;
	}
	User(QueueManger<T>  qMgr, String name){
		this.qMgr = qMgr;
		this.name = name;
	}
	public abstract void process(T app) throws QueueNotFound, TaskNotQueued, ApplicationRejected;
	public void subscribeToQueue(String queueName, User<T> user){
		qMgr.subscribeToQueue(queueName, user);
	}
	
	public void appove(T app) throws QueueNotFound, TaskNotQueued{
		System.out.println("Approved :"+app.toString());
		qMgr.approve(app);
	}
	
	public void disapprove(T app) throws QueueNotFound, ApplicationRejected, TaskNotQueued{
		System.out.println("Disapproved :"+app.toString());
		qMgr.dissapprove(app);
	}
}
