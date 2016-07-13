package com.hhm.jbpm.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.codeassist.complete.CompletionOnAnnotationMemberValuePair;
import org.jbpm.api.Configuration;
import org.jbpm.api.Deployment;
import org.jbpm.api.DeploymentQuery;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.ProcessInstanceQuery;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

public class JbpmTest {

	public static void main(String[] args) {
		JbpmTest jbpmTest = new JbpmTest();
		//jbpmTest.testDeployment();// 部署工作流程
		// jbpmTest.queryDeployment();//查询工作流程
		// jbpmTest.getDeploymentImg();//查看任务流程定义图片
		// jbpmTest.testDeploymentQuery();//部署查询对象
		// jbpmTest.testStartProcessInstance();//执行任务流程
		// jbpmTest.testProcessInstanceQuery();//流程实例查询对象
		// jbpmTest.testTaskQuery();//任务查询器
		// jbpmTest.testCompleteTask();//完成任务
		// jbpmTest.testSetVariable();//设置全局变量
		// jbpmTest.tesGetVariable();//获取全局变量
		//jbpmTest.testAssigeen();//指定任务办理人、
		jbpmTest.testCompleteTask2();
	}

	public void test01() {
		Configuration configuration = new Configuration();

		ProcessEngine processEngine = configuration.buildProcessEngine();

		System.out.println(processEngine);

	}

	/**
	 * 部署工作流程
	 */
	public void testDeployment() {
		// 解析文jbpm开头的几个文件
		Configuration configuration = new Configuration();
		// 准备获取服务的工具
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 获取工程服务对象
		RepositoryService respoRepositoryService = processEngine
				.getRepositoryService();

		// 获取新工程对象
		NewDeployment newDeployment = respoRepositoryService.createDeployment();

		// 加入需要部署的文件
		// newDeployment.addResourceFromClasspath("com/hhm/jbpm/test/test1.jpdl.xml");
		// newDeployment.addResourceFromClasspath("com/hhm/jbpm/test/test1.png");

		newDeployment
				.addResourceFromClasspath("com/hhm/jbpm/test/test2.jpdl.xml");
		newDeployment.addResourceFromClasspath("com/hhm/jbpm/test/test2.png");

		// 开始部署
		System.out.println(newDeployment.deploy());

	}

	/**
	 * 查询工作流程
	 */
	public void queryDeployment() {
		// 解析文jbpm开头的几个文件
		Configuration configuration = new Configuration();
		// 准备获取服务的工具
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 获取工程服务对象
		RepositoryService respoRepositoryService = processEngine
				.getRepositoryService();
		// 获取查询器
		// DeploymentQuery
		// deploymentQuery=respoRepositoryService.createDeploymentQuery();

		// 查询全部工作流程
		// List<Deployment> deploymentQuerieList=deploymentQuery.list();
		//
		// for (Deployment deployment : deploymentQuerieList) {
		// System.out.println(deployment.getId());
		// }

		ProcessDefinitionQuery processDefinitionQuery = respoRepositoryService
				.createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionId("员工请假流程定义-1");
		ProcessDefinition processDefinition = processDefinitionQuery
				.uniqueResult();
		System.out.println(processDefinition.getName());

		long count = processDefinitionQuery.count();
		System.out.println(count);

	}

	/**
	 * 获取工作流程的图片
	 */
	public void getDeploymentImg() {
		// 解析文jbpm开头的几个文件
		Configuration configuration = new Configuration();
		// 准备获取服务的工具
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 获取工程服务对象
		RepositoryService respoRepositoryService = processEngine
				.getRepositoryService();

		ProcessDefinitionQuery processDefinitionQuery = respoRepositoryService
				.createProcessDefinitionQuery();
		// 获取目标工作流程
		processDefinitionQuery.processDefinitionId("员工请假流程定义-2");
		ProcessDefinition processDefinition = processDefinitionQuery
				.uniqueResult();

		// 读取图片文件
		InputStream in = respoRepositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),
				processDefinition.getImageResourceName());

		// 利用IO流输出图片
		try {
			OutputStream out = new FileOutputStream("e:/123.png");
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 部署查询对象 Deploment部署对象 ProcessDefinition 流程定义对象 一个Deploment
	 * 对应着一个ProcessDefinition 流程定义对象记录了流程定义(文件)的相关信息,而 部署对象只记录了一个id 部署对象就像一座大桥 ,
	 * 流程定义对象就像一座孤岛 , 我们怎么到这座孤岛上去? lob (流程定义的文件) , 也和部署对象相关联
	 */
	public void testDeploymentQuery() {
		Configuration configuration = new Configuration();

		// 获取任务调度引擎
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 获取仓库服务对象
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		// repositoryService.deleteDeployment("1");//根据任务的id来删除任务
		// 获取部署查询对象
		DeploymentQuery deploymentQuery = repositoryService
				.createDeploymentQuery();

		// 查询所有的任务
		List<Deployment> deployments = deploymentQuery.list();

		for (Deployment deployment : deployments) {
			System.out.println(deployment.getId());
		}

	}

	/**
	 * 启动流程实例对象，启动完之后你就变成了流程实例了
	 */
	public void testStartProcessInstance() {
		Configuration configuration = new Configuration();// 读取配置文件
		// 获取任务引擎
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 获取执行服务对象
		ExecutionService executionService = processEngine.getExecutionService();

		// 根据任务的id来开始执行任务
		ProcessInstance processInstance = executionService
				.startProcessInstanceById("员工请假流程定义-3");

		System.out.println(processInstance);
	}

	/**
	 * 获取流程示例查询对象
	 */
	public void testProcessInstanceQuery() {
		Configuration configuration = new Configuration();// 解析配置文件
		// 准备任务引擎对象
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 准备任执行服务对象
		ExecutionService executionService = processEngine.getExecutionService();

		// 获取流程实例查询对象
		ProcessInstanceQuery processInstanceQuery = executionService
				.createProcessInstanceQuery();
		// 获取所有的流程实例
		List<ProcessInstance> processInstances = processInstanceQuery.list();
		for (ProcessInstance processInstance : processInstances) {
			System.out.println(processInstance.getIsProcessInstance());
			System.out.println(processInstance.getKey());// null
			System.out.println(processInstance.getName());// null
			System.out.println(processInstance.getProcessDefinitionId());// 员工请假流程定义-3，流程定义id
		}
	}

	/**
	 * 任务查询器
	 */
	public void testTaskQuery() {
		Configuration configuration = new Configuration();
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 获取任务查询服务对象
		TaskService taskService = processEngine.getTaskService();

		// 获取任务查询器
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 获取所有的任务
		List<Task> tasks = taskQuery.list();

		for (Task task : tasks) {
			System.out.println(task.getAssignee());
			System.out.println(task.getActivityName());
			System.out.println(task.getExecutionId());
		}
	}

	/**
	 * 完成任务
	 */
	public void testCompleteTask() {
		Configuration configuration = new Configuration();
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 任务服务 , service -- dao 业务逻辑类 任务业务类
		TaskService taskService = processEngine.getTaskService();

		// Set<String> outcomes = taskService.getOutcomes("70001");
		// System.out.println(outcomes);
		// 只要你有任务的id,你就可以完成一个任务 , 不管你是不是这个任务的办理人 ,( 为什么还要指定任务的办理人呢 : 方便根据任务办理人查询
		// )
		// taskService.completeTask("110002","to end1");
		// System.out.println(taskService.getOutcomes("180001"));//to 经理审批
		// taskService.completeTask("170002");//请假申请提交。提交人--张三
		// taskService.completeTask("180001");//项目组长处理申请。提交人--项目组长（李四）

		System.out.println(taskService.getOutcomes("190001"));// to end1
		taskService.completeTask("190001");// 经理处理申请。提交人--经理（王五），下一步就是任务完成

		// 注意：若有一个任务有多个下一步的动作，二在代码执行时不指定执行哪一步操作，则会报错
		// 指定方式
		// taskService.completeTask("190001","to endl")；
	}

	/**
	 * 设置全局变量
	 */
	public void testSetVariable() {
		Configuration configuration = new Configuration();
		// 获取流程引擎服务对象
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 获取执行服务对象
		ExecutionService executionService = processEngine.getExecutionService();

		// 设置全局变量
		// executionService.setVariable("员工请假流程定义.200001", "请假天数", "3");
		// executionService.setVariable("员工请假流程定义.200001", "请假缘由", "回家造人");

		// 设置全局变量，还可以javabean类型的全局变量
		Person person = new Person("hhm", 18);

		executionService.setVariable("员工请假流程定义.200001", "person", person);
	}

	/**
	 * 获取全局变量
	 */
	public void tesGetVariable() {
		Configuration configuration = new Configuration();
		// 获取流程引擎服务对象
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 获取执行服务对象
		// ExecutionService
		// executionService=processEngine.getExecutionService();

		// 获取全局变量
		// String days=(String) executionService.getVariable("员工请假流程定义.200001",
		// "请假天数");
		// String cause=(String) executionService.getVariable("员工请假流程定义.200001",
		// "请假缘由");
		//
		// System.out.println("days-->"+days+"||||||"+"cause-->"+cause);
		// Person p=(Person) executionService.getVariable("员工请假流程定义.200001",
		// "person");

		// 使用其他的服务对象来获取
		TaskService taskService = processEngine.getTaskService();

		Person p = (Person) taskService.getVariable("200002", "person");

		System.out.println(p.getName());
	}

	/**
	 * 指定任务办理人
	 */
	public void testAssigeen() {
		Configuration configuration = new Configuration();
		ProcessEngine processEngine = configuration.buildProcessEngine();

		ExecutionService executionService = processEngine.getExecutionService();

		// 在xml文件中直接指定任务办理人，只是方式一

		// 方式二。在执行任务时指定任务办理人
		Map<String, String> varMap = new HashMap<String, String>();
		varMap.put("username", "张三");
		varMap.put("username2", "李四");
		
		executionService.startProcessInstanceById("员工请假流程定义-6",varMap);

	}
	
	
	
	/**
	 * 完成任务
	 */
	public void testCompleteTask2() {
		Configuration configuration = new Configuration();
		ProcessEngine processEngine = configuration.buildProcessEngine();

		// 任务服务 , service -- dao 业务逻辑类 任务业务类
		TaskService taskService = processEngine.getTaskService();


		taskService.completeTask("280001","to 经理审批");// 经理处理申请。提交人--经理（王五），下一步就是任务完成

		// 注意：若有一个任务有多个下一步的动作，二在代码执行时不指定执行哪一步操作，则会报错
		// 指定方式
		// taskService.completeTask("190001","to endl")；
	}

}
