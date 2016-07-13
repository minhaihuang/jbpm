package com.hhm.jbpm.test3;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;

public class JbpmTest {
	
	public static void main(String[] args){
		//deploymentTask();
		//startTaskProcesssInstance();
		completeTask();
		//testStartProcessInstance();
		
	}
	
	/**
	 * 部署任务
	 */
	public static void deploymentTask(){
		Configuration configuration=new Configuration();
		//获取流程引擎
		ProcessEngine processEngine=configuration.buildProcessEngine();
		
		//获取仓库服务对象
		RepositoryService repositoryService=processEngine.getRepositoryService();
		
		//部署任务
		NewDeployment newDeployment=repositoryService.createDeployment();
		
		newDeployment.addResourceFromClasspath("com/hhm/jbpm/test3/forkJoin.jpdl.xml");
		newDeployment.addResourceFromClasspath("com/hhm/jbpm/test3/forkJoin.png");
		
		//开始部署；
		newDeployment.deploy();
	}
	
	/**
	 * 开始流程
	 */
	public static void startTaskProcesssInstance(){
		Configuration configuration=new Configuration();
		//获取流程引擎
		ProcessEngine processEngine=configuration.buildProcessEngine();
		
		//获取执行服务
		ExecutionService executionService=processEngine.getExecutionService();
		
		//开始流程实例。
		executionService.startProcessInstanceById("forkJoin-1");
		
		
	}
	
	/**
	 * 执行任务与完成任务
	 */
	public static void completeTask(){
		Configuration configuration=new Configuration();
		
		ProcessEngine processEngine=configuration.buildProcessEngine();
		
		//获取任务服务对象
		TaskService taskService=processEngine.getTaskService();
		
		String username=(String) taskService.getVariable("370001", "username");
		
		System.out.println(username);
		System.out.println("********************************************");
		//完成任务
		taskService.completeTask("370001","to 经理审批");
		
	}
	
	
	/**
	 * 指定一个任务由一组人来完成的流程实例
	 */

	public static void testStartProcessInstance(){
		Configuration configuration = new Configuration();
		ProcessEngine processEngine = configuration.buildProcessEngine();
		
		//执行服务对象
		ExecutionService executionService = processEngine.getExecutionService();
		
		
		//流程变量的map
		Map<String,Object> varMap = new HashMap<String,Object>();
		varMap.put("username", "张三3");
		varMap.put("username2", "李四3");
		varMap.put("usernames", "李四5,李四6");
		
		ProcessInstance processInstance = executionService.startProcessInstanceById("员工请假流程定义-6",varMap);
		
		
		System.out.println(processInstance);
		
	}
}
