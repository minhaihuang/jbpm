package com.hhm.jbpm.test3;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

public class SetCandidateUsers implements AssignmentHandler{

	public void assign(Assignable assignable, OpenExecution execution)
			throws Exception {
		assignable.addCandidateUser("王五5");
		assignable.addCandidateUser("王五6");
	}

}

