package com.bluesky.iplatform.component.function.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.function.model.FunctionRelation;
import com.bluesky.iplatform.component.profile.model.User;

@Service(value = "FunctionManagerService")
public class FunctionManagerService implements FunctionManager {

	@Override
	public void saveFunctionRelations(User user, List<FunctionRelation> modes)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Function> getFunctionsByRoleID(User user, int roleID)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
