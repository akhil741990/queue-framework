package user;

import queue.framework.exception.QueueNotFound;
import queue.framework.exception.TaskNotQueued;
import queuemanger.core.QueueManger;
import queuemanger.core.Task;

public class RiskOfficer<T extends Task> extends User<T>{

	public RiskOfficer(QueueManger<T> qMgr, String name) {
		super(qMgr, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(T app) throws QueueNotFound, TaskNotQueued {
		System.out.println("Processing in RiskOffficer");
		this.appove(app);
		
	}

}
