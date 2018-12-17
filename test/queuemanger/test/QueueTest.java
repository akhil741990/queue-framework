package queuemanger.test;

import queuemanger.core.QueueManger;
import queuemanger.queues.BoundedQ;
import user.FinanceTeamUser;
import user.RiskOfficer;
import user.UnderWriter;
import user.User;

public class QueueTest {


	public static void main(String args[]){
		BoundedQ<LoanApp> qUnderWriter = new BoundedQ<>("UnderWriter",10);
		BoundedQ<LoanApp> qRiskOfficer = new BoundedQ<>("RiskOfficer",10);
		BoundedQ<LoanApp> qFinanceTeam = new BoundedQ<>("FinanceTeam",10);
		
		QueueManger<LoanApp> qMgr = new QueueManger<>();
		qMgr.addQToHierrarchy(qUnderWriter);
		qMgr.addQToHierrarchy(qRiskOfficer);
		qMgr.addQToHierrarchy(qFinanceTeam);
		
		User underWriter = new UnderWriter<LoanApp>(qMgr, "User-UnderWriter");
		User riskOfficer = new RiskOfficer<LoanApp>(qMgr, "User-RiskOfficer");
		User financeTeam = new FinanceTeamUser<LoanApp>(qMgr, "User-FinanceTeam");
		qUnderWriter.subscribe(underWriter);
		qRiskOfficer.subscribe(riskOfficer);
		qFinanceTeam.subscribe(financeTeam);
		
		Thread t = new Thread(new TaskEnqueuer(qUnderWriter));
		t.start();
		
		
	}
}
