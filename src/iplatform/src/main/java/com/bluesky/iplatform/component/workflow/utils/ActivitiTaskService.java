package com.bluesky.iplatform.component.workflow.utils;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivitiTaskService {
	
	@Autowired
	private TaskService taskService;
	
	public Task newTask() throws Exception{
		Task task = null;
		
		return task;
		
	}

}
