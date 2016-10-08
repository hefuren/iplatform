package com.bluesky.iplatform.component.workflow.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.bluesky.iplatform.component.workflow.model.BaseWorkflow;
import com.bluesky.iplatform.component.profile.model.User;

public interface WorkflowManager {
	
	/**
	 * 启动引擎
	 * @param user
	 * @param businessKey
	 * @param variables
	 * @return
	 */
	public ProcessInstance startWorkflow(User user, String businessKey, Map<String, Object> variables);
	
	/**
	 * 保存流程对象
	 * @param user
	 * @param wf
	 */
	public void saveWorkflow(User user, BaseWorkflow wf);
	
	/**
	 * 删除流程对象
	 * @param user
	 * @param flowID
	 */
	public void deleteWorkflow(User user, int flowID);
	
	public BaseWorkflow getWorkflow(User user, int flowID);
	
	public List<BaseWorkflow> findTodoTasks(String userID, String businessKey);

}
