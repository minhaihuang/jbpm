package com.hhm.jbpm.test;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
/**
 * 指定任务办理人的方式三，通过回调函数与配置文件指定任务办理人
 * @author 黄帅哥
 *
 */
public class SetAssignee implements AssignmentHandler{

	@Override
	public void assign(Assignable assignable, OpenExecution arg1) throws Exception {
		System.out.println("方式三启动了");
			assignable.setAssignee("王五");
		
	}

}
