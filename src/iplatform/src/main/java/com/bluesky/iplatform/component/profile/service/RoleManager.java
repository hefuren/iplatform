package com.bluesky.iplatform.component.profile.service;

import java.util.List;

import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.RoleRelation;
import com.bluesky.iplatform.component.profile.model.User;

public interface RoleManager {
	
	/**
	 * 新建角色
	 * @param user
	 * @param role
	 * @
	 */
	public void newRole(User user, Role role) ;
	
	/**
	 * 更新角色
	 * @param user
	 * @param role
	 * @
	 */
	public void updateRole(User user, Role role) ;
	
	/**
	 * 删除角色
	 * @param user
	 * @param role
	 * @
	 */
	public void deleteRole(User user, Role role) ;
	
	/**
	 * 通过角色获取角色对应的用户
	 * @param user
	 * @param role
	 * @return
	 * @
	 */
	public List<User> getRoleUser(User user, Role role) ;
	
	/**
	 * 为角色分配用户
	 * @param user
	 * @param modes
	 * @
	 */
	public void assignRoleUser(User user, Role role, List<RoleRelation> modes);


}
