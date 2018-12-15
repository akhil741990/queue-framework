package user;

import queuemanger.core.QueueManger;
import queuemanger.core.Task;

public abstract class User<T extends Task> {
	private QueueManger<T> qMgr;
	User(QueueManger<T>  qMgr){
		this.qMgr = qMgr;
	}
	public abstract void process(T app);
	public void subscribeToQueue(String queueName, User<T> user){
		
	}
}
