package com.bluesky.iplatform.component.function.service;

import java.util.List;
import java.util.Map;

import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.function.model.FunctionRelation;
import com.bluesky.iplatform.component.profile.model.User;


public interface FunctionManager {
	
	/**
	 * 保存权限分配信息
	 * @param user
	 * @param modes
	 * @throws Exception
	 */
	public void saveFunctionRelations(User user, List<FunctionRelation> modes) throws  Exception;
	
	/**
	 * 通过RoleID获取权限Map
	 * @param user
	 * @param roleID
	 * @return
	 * @throws Exception
	 * Map<String, Function> Key 为权限的Key, Function 为权限对象
	 */
	public Map<String, Function> getFunctionsByRoleID(User user, int roleID) throws Exception;

	
}
