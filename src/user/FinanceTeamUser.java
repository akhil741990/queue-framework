package user;

import queue.framework.exception.QueueNotFound;
import queue.framework.exception.TaskNotQueued;
import queuemanger.core.QueueManger;
import queuemanger.core.Task;

public class FinanceTeamUser<T extends Task> extends User<T> {

	public FinanceTeamUser(QueueManger<T> qMgr, String name) {
		super(qMgr, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(T app) throws QueueNotFound, TaskNotQueued {
		System.out.println("Processing in FinanceTeamUser:"+app.toString());
		appove(app);
	}

}
