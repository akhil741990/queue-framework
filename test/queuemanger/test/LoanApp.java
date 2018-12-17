package queuemanger.test;

import queuemanger.core.Task;

public class LoanApp extends Task {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public LoanApp(Integer id) {
		this.id = id;
	}
	
	
}
