package com.bluesky.iplatform.component.profile.dao;

import java.util.List;

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
	public void assignRoleUser(User user, Role role, List<RoleRelation> modes);
	

}
