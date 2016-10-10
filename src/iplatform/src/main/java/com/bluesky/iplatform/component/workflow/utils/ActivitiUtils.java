package com.bluesky.iplatform.component.workflow.utils;

import java.io.FileInputStream;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Activiti工作流引擎操作通用方法
 * （备注：该类仅仅封装最基本的Activit操作方法，如果不能满足，需要通过Activiti API进行操作）
 * @author ElwinHe
 *
 */
@Component(value = "ActivitiUtils")
@Scope(value = "prototype")
public class ActivitiUtils {
	
	/**
	 * 公共平台工作流引擎名称：iPlatform_Activit_processEngine
	 */
	public static final String PROCESS_ENGINE_NAME = "IPLATFORM_ACTIVITI_PROCESS_ENGINE";//
	
	/**
	 * 部署工作流
	 * @param workflowName  例如：userAndGroupInUserTask.bpmn20.xml
	 * @param filePath 流程资源文件的路径
	 * @throws Exception
	 */
	public void deployProcess(String workflowName, String filePath) throws Exception{
		
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
	 * 删除部署的流程
	 * （附注：删除流程部署时，会删除流程定义及引擎生成的图片，。）
	 * @param deploymentId
	 * @param arg   如果为true , 同时删除该部署包含的流程定义已经被运行或已经结束的流程实例，即相关数据被删除
	 * @throws Exception
	 */
	public void deleteDeployment(String deploymentId, boolean arg) throws Exception{
		//get Activiti services
		RepositoryService repositoryService =  getRepositoryService();
		repositoryService.deleteDeployment(deploymentId,arg);
	}
	
	/**
	 * 根据部署的ID启动对应的工作流
	 * @param deploymentId  工作流部署的ID
	 * @throws Exception
	 */
	public ProcessInstance startProcessById(String deploymentId) throws Exception{
		RuntimeService runtimeService = getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(deploymentId);
		return processInstance;
	}
	
	/**
	 * 根据key启动对应的工作流
	 * @param deploymentId  工作流部署的ID
	 * @throws Exception
	 */
	public ProcessInstance startProcessByKey(String key) throws Exception{
		RuntimeService runtimeService = getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
		return processInstance;
	}
	
	/**
	 * 挂起流程
	 * （备注：If a process instance is in state suspended）
	 * @param processInstanceId
	 * @throws Exception
	 */
	public void suspendProcessInstanceById(String processInstanceId) throws Exception{
		RuntimeService runtimeService = getRuntimeService();
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}
	
	/**
	 * 激活流程
	 * @param processInstanceId
	 * @throws Exception
	 */
	public void activateProcessInstanceById(String processInstanceId)throws Exception{
		RuntimeService runtimeService = getRuntimeService();
		runtimeService.activateProcessInstanceById(processInstanceId);
	}
	
	/**
	 * 获取已部署的流程定义列表
	 * @return
	 * @throws Exception
	 */
	public List<ProcessDefinition> getProcessDefinitions() throws Exception{
		//get Activiti services
		RepositoryService repositoryService =  getRepositoryService();
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		return list;
	}

	
	/**
	 * 获取流程引擎
	 * @return
	 * @throws Exception
	 */
	private ProcessEngine getProcessEngine() throws Exception{
		//Create Activiti process engine
		ProcessEngine processEngine = SpringProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();
		return processEngine;
	}
	
	/**
	 * 获取流程存储服务实例
	 * @return
	 * @throws Exception
	 */
	private RepositoryService getRepositoryService() throws Exception{
		//Create Activiti process engine
		ProcessEngine processEngine = this.getProcessEngine();
		//get Activiti services
		RepositoryService repositoryService =  processEngine.getRepositoryService();
		return repositoryService;
	}
	
	private RuntimeService getRuntimeService() throws Exception{
		//Create Activiti process engine
		ProcessEngine processEngine = this.getProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		return runtimeService;
	}

}
