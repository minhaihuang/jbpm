package com.hhm.jbpm.test2;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

/**
 * 判断员工请假天数，若请假天数小于等于7天，有项目组长直接审批，若大于7天，必须经过项目组长同意
 * @author 黄帅哥
 *
 */
public class TestLeaveDaysDecision implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		//获取员工请假天数
		Object leaveDays=execution.getVariable("leaveDays");
		//判断
		if(leaveDays!=null){
			int days=(Integer)leaveDays;
			
			if(days>7){
				return "to 经理审批";
			}
		}
		return "to endl";
	}

}
