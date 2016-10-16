package com.bluesky.iplatform.component.workflow.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "ProcessService")
public class ProcessService {
	
	@Getter
	@Autowired
	private ProcessEngine processEngine;
	
	@Getter
	@Autowired
	private RepositoryService repositoryService;
	
	@Getter
	@Autowired
	private RuntimeService runtimeService;
	
	/**
	 * 部署工作流
	 * @param workflowName  例如：userAndGroupInUserTask.bpmn20.xml
	 * @param filePath 流程资源文件的路径
	 * @throws Exception
	 */
	public void deployProcess(String workflowName, String filePath) throws Exception{
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
		repositoryService.deleteDeployment(deploymentId,arg);
	}
	
	/**
	 * 挂起流程
	 * （备注：If a process instance is in state suspended）
	 * @param processInstanceId
	 * @throws Exception
	 */
	public void suspendProcessInstanceById(String processInstanceId) throws Exception{
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}
	
	/**
	 * 激活流程
	 * @param processInstanceId
	 * @throws Exception
	 */
	public void activateProcessInstanceById(String processInstanceId)throws Exception{
		runtimeService.activateProcessInstanceById(processInstanceId);
	}
	
	/**
	 * 获取已部署的流程定义列表
	 * @return
	 * @throws Exception
	 */
	public List<ProcessDefinition> getProcessDefinitions() throws Exception{
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		return list;
	}
	
	/**
	 * 获取活动中的流程
	 * @return
	 * @throws Exception
	 */
	public List<ProcessDefinition> getActivateProcessDefinitions() throws Exception{
		List<ProcessDefinition> list = null;
		List<ProcessDefinition> allList = getProcessDefinitions();
		if(allList != null && allList.size() > 0){
			list = new ArrayList<ProcessDefinition>();
			for(ProcessDefinition item : allList){
				if(!item.isSuspended()){
					list.add(item);
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取被挂起中的流程
	 * @return
	 * @throws Exception
	 */
	public List<ProcessDefinition> getSuspendProcessDefinitions() throws Exception{
		List<ProcessDefinition> list = null;
		List<ProcessDefinition> allList = getProcessDefinitions();
		if(allList != null && allList.size() > 0){
			list = new ArrayList<ProcessDefinition>();
			for(ProcessDefinition item : allList){
				if(item.isSuspended()){
					list.add(item);
				}
			}
		}
		return list;
	}
	

}
