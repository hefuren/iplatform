package com.bluesky.iplatform.component.function.service;

import java.util.List;
import java.util.Map;

import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.function.model.FunctionRelation;
import com.bluesky.iplatform.component.profile.model.User;


public interface FunctionManager {
	
	/**
	 * 新增权限
	 * @param user
	 * @param function
	 */
	public void newFunction(User user, Function function);
	
	/**
	 * 更新权限
	 * @param user
	 * @param function
	 */
	public void updateFunction(User user, Function function);
	
	/**
	 * 删除权限
	 * @param user
	 * @param function
	 */
	public void deleteFunction(User user, Function function);
	
	/**
	 * 通过权限ID获取权限对象
	 * @param user
	 * @param id
	 * @return
	 */
	public Function getFunction(User user, int id);
	
	/**
	 * 通过权限IDS获取权限List
	 * @param user
	 * @param ids
	 * @return
	 */
	public List<Function> getFunctions(User user, int[] ids);
	
	/**
	 * 保存权限分配信息
	 * @param user
	 * @param modes
	 * @throws Exception
	 */
	public void saveFunctionRelations(User user, List<FunctionRelation> modes) ;
	
	/**
	 * 通过RoleID获取权限Map
	 * @param user
	 * @param roleID
	 * @return
	 * @throws Exception
	 * Map<String, Function> Key 为权限的Key, Function 为权限对象
	 */
	public Map<String, Function> getFunctionsByRoleID(User user, int roleID) ;

	
}
