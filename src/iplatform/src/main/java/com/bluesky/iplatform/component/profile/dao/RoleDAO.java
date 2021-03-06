package com.bluesky.iplatform.component.profile.dao;

import java.util.List;
import java.util.Set;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.RoleRelation;
import com.bluesky.iplatform.component.profile.model.User;

public interface RoleDAO<T> extends BaseSingleMyBatisDAO<T> {
	
	/**
	 * 为角色分配用户
	 * @param user
	 * @param role
	 * @param modes
	 */
	public void assignUsers(User user, Role role, List<RoleRelation> modes);
	
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
	 * 根据用户查找其角色
	 * @param user 当前用户对象
	 * @return
	 */
	public Set<Role> getRoles(User user);	

}
