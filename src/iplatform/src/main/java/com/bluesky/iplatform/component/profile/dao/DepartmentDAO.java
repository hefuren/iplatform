package com.bluesky.iplatform.component.profile.dao;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.component.profile.model.User;

public interface DepartmentDAO<T> extends BaseSingleMyBatisDAO<T> {
	
	/**
	 * 保存组织结构(删除原有的，再新增)
	 * @param user
	 * @param hierarchy
	 * @throws Exception
	 */
	public void saveDepartment(User user, Hierarchy hierarchy)  throws Exception;
	
	/**
	 * 通过用户获取系统部门组织结构
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Hierarchy getDepartmentTree(User user) throws Exception;

}
