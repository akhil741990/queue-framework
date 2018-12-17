package user;

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
	public abstract void process(T app);
	public void subscribeToQueue(String queueName, User<T> user){
		qMgr.subscribeToQueue(queueName, user);
	}
}
