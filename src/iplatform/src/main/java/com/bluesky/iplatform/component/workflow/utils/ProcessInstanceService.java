package com.bluesky.iplatform.component.workflow.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.Getter;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.profile.model.User;

/**
 * 提供对流程实例的基本操作
 * @author ElwinHe
 *
 */
public class ProcessInstanceService {
	
	@Getter
	@Resource(name= "processEngine")
	private ProcessEngine processEngine;
	
	@Getter
	@Resource(name= "repositoryService")
	private RepositoryService repositoryService;
	
	@Getter
	@Resource(name= "runtimeService")
	private RuntimeService runtimeService;
	
	@Getter
	@Resource(name= "formService")
	private FormService formService;
	
	@Getter
	@Resource(name= "taskService")
	private TaskService taskService;
	
	/**
	 * 启动流程：根据部署的ID启动流程
	 * @param processDefinitionId  工作流部署的ID
	 * @throws Exception
	 */
	public ProcessInstance startProcessInstanceById(String processDefinitionId) throws Exception{
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
		return processInstance;
	}
	
	/**
	 * 启动流程：根据流程Key启动流程
	 * @param processDefinitionKey  工作流Key
	 * @throws Exception
	 */
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) throws Exception{
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
		
		return processInstance;
	}

	/**
	 * 挂起某一流程实例
	 * @param processInstanceId 流程实例Id
	 * @throws Exception
	 */
	public void suspendProcessInstanceById(String processInstanceId) throws Exception{
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}
	
	/**
	 * 查询流程实例：根据部署ID
	 * @param deploymentId
	 * @return
	 * @throws Exception
	 */
	public List<ProcessInstance> getProcessInstances(String deploymentId) throws Exception{
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().deploymentId(deploymentId);
		List<ProcessInstance> list = query.list();
		return list;
	}
	
	/**
	 * 查询流程实例
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public PageInfo getProcessInstances(PageInfo pageInfo) throws Exception{
		List<ProcessInstance> items = null;
		Map<String, Object> conditions = pageInfo.getConditions();
		pageInfo.isPaged();
		
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
		
		if (conditions != null && conditions.size() > 0) {
			// 带条件查询
			String[] paramNames = new String[conditions.size()];
			conditions.keySet().toArray(paramNames);
			for (String paramName : paramNames) {
				query.variableValueEquals(paramName, conditions.get(paramName));
			}
		}
		
		if(pageInfo.isPaged()){
			int totalRows = TypeUtils.nullToInt(query.count());
			int pageCounts = (totalRows % pageInfo.getPageSize() > 0) ? totalRows / pageInfo.getPageSize() +1 : totalRows / pageInfo.getPageSize(); 
			pageInfo.setPageCount(pageCounts);
			int currentPage = pageInfo.getCurrentPage();
			int pageSize = pageInfo.getPageSize();//每页显示的记录数
			
			int firstResult = currentPage * pageSize;//起始的记录数
			int maxResults = ((currentPage+1) * pageSize < totalRows) ? (currentPage+1) * pageSize : totalRows;//当前页最后的记录数
			
			items = query.listPage(firstResult, maxResults);
		}else {
			items = query.list();
		}
		
		items = query.list();
		
		pageInfo.setItems(items);
		
		return pageInfo;
	}
	
	/**
	 * 获取所属用户的流程任务
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<Task> getTasks(User user) throws Exception{
		int userId = user.getId();
		//读取直接分配给当前人，并且处于代办中的任务
		List<Task> doingTasks = taskService.createTaskQuery().taskCandidateUser(TypeUtils.nullToString(userId)).active().list();
		//受邀任务
		List<Task> involvedTasks = taskService.createTaskQuery().taskInvolvedUser(TypeUtils.nullToString(userId)).active().list();
		//合并两种任务
		List<Task> allTasks = new ArrayList<Task>();
		allTasks.addAll(doingTasks);
		allTasks.addAll(involvedTasks);
		
		return allTasks;
	}
	
//	
//	public void turnTransition(String taskId, String activityId, Map<String, Object> variables) throws Exception{
//		// 当前节点  
//        ActivityImpl currActivity = findActivitiImpl(taskId, null);  
//        // 清空当前流向  
//        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);  
//  
//        // 创建新流向  
//        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
//        // 目标节点  
//        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);  
//        // 设置新流向的目标节点  
//        newTransition.setDestination(pointActivity);  
//  
//        // 执行转向任务  
//        taskService.complete(taskId, variables);  
//        // 删除目标节点新流入  
//        pointActivity.getIncomingTransitions().remove(newTransition);  
//  
//        // 还原以前流向  
//        restoreTransition(currActivity, oriPvmTransitionList); 
//	}
//	
//	 private ActivityImpl findActivitiImpl(String taskId, String activityId) throws Exception {
//		// 取得流程定义  
//         ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);  
//   
//         // 获取当前活动节点ID  
//         if (StringUtil.isNull(activityId)) {  
//             activityId = findTaskById(taskId).getTaskDefinitionKey();  
//         }  
//   
//         // 根据流程定义，获取该流程实例的结束节点  
//         if (activityId.toUpperCase().equals("END")) {  
//             for (ActivityImpl activityImpl : processDefinition.getActivities()) {  
//                 List<PvmTransition> pvmTransitionList = activityImpl  
//                         .getOutgoingTransitions();  
//                 if (pvmTransitionList.isEmpty()) {  
//                     return activityImpl;  
//                 }  
//             }  
//         }  
//   
//         // 根据节点ID，获取对应的活动节点  
//         ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)  
//                 .findActivity(activityId);  
//   
//         return activityImpl;  
//	 }
	
	public void test(){
		String deploymentId = "";
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().deploymentId(deploymentId);
	
	}

}
