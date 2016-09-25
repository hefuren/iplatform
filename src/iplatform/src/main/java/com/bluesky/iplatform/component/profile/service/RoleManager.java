package com.bluesky.iplatform.component.profile.service;

import java.util.List;
import java.util.Set;

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
	 * 激活角色
	 * @param user
	 * @param role
	 */
	public void activateRole(User user, Role role);
	
	/**
	 * 使角色失效
	 * @param user
	 * @param role
	 */
	public void inactivatingRole(User user, Role role);
	
	/**
	 * 删除角色
	 * @param user
	 * @param role
	 * @
	 */
	public void deleteRole(User user, Role role) ;
	
	/**
	 * 通过ID获取Role
	 * @param user
	 * @param id
	 * @return
	 */
	public Role getRole(User user, int id);
	
	/**
	 * 通过IDS集获取Role
	 * @param user
	 * @param ids
	 * @return
	 */
	public List<Role> getRoles(User user, int[] ids);
	
	/**
	 * 通过角色获取角色对应的用户
	 * @param user
	 * @param role
	 * @return
	 * @
	 */
	public List<User> getUsersByRole(User user, Role role) ;
	
	/**
	 * 根据用户查找其角色
	 * @param user 当前用户对象
	 * @return
	 */
	public Set<Role> getRoles(User user);	

	
	/**
	 * 为角色分配用户
	 * @param user
	 * @param modes
	 * @
	 */
	public void assignUsers(User user, Role role, List<RoleRelation> modes);


}
