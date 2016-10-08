package com.bluesky.iplatform.component.workflow.utils;

import java.io.FileInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "ActivitiUtils")
@Scope(value = "prototype")
public class ActivitiUtils {
	
	/**
	 * 部署工作流
	 * @param workflowName  例如：userAndGroupInUserTask.bpmn20.xml
	 * @param filePath 流程资源文件的路径
	 * @throws Exception
	 */
	public static void deployProcess(String workflowName, String filePath) throws Exception{
		
		//get Activiti services
		RepositoryService repositoryService =  getRepositoryService();
		
		//发布配置对象
		DeploymentBuilder builder = repositoryService.createDeployment().name("xxx");
		builder.category("ppm");
		//读取classpath的资源为一个输入流
		FileInputStream fileInputStream = new FileInputStream(filePath);
		repositoryService.createDeployment().addInputStream(workflowName, fileInputStream).deploy();
	}
	
	/**
	 * 根据部署的ID启动对应的工作流
	 * @param deploymentId  工作流部署的ID
	 * @throws Exception
	 */
	public static void startProcess(String deploymentId) throws Exception{
		RuntimeService runtimeService = getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(deploymentId);
		
	}
	
	private static ProcessEngine getProcessEngine() throws Exception{
		//Create Activiti process engine
		ProcessEngine processEngine = SpringProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();
		return processEngine;
	}
	
	private static RepositoryService getRepositoryService() throws Exception{
		//Create Activiti process engine
		ProcessEngine processEngine = SpringProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();
		//get Activiti services
		RepositoryService repositoryService =  processEngine.getRepositoryService();
		return repositoryService;
	}
	
	private static RuntimeService getRuntimeService() throws Exception{
		//Create Activiti process engine
		ProcessEngine processEngine = SpringProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		return runtimeService;
	}

}
