package com.bluesky.iplatform.component.profile.service;

import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.component.profile.model.Department;
import com.bluesky.iplatform.component.profile.model.User;

public interface StructureManager {
	
	/**
	 * 保存部门
	 * 附注：以hierarchy结构保存部门树，hiberchy中department对象需要标识“新建”或“更新”
	 * @param user
	 * @param hierarchy
	 */
	public void saveDepartment(User user,Hierarchy hierarchy);
	
	/**
	 * 新增部门
	 * @param user
	 * @param mode
	 */
	public void newDepartment(User user, Department mode);
	
	/**
	 * 更新部门
	 * @param user
	 * @param department
	 * @
	 */
	public void updateDepartment(User user, Department department) ;
	
	/**
	 * 获取部门
	 * @param user
	 * @param departmentID
	 * @return
	 * @
	 */
	public Department getDepartment(User user, int departmentID) ;
	
	/**
	 * 通过用户获取系统部门组织结构
	 * @param user
	 * @return
	 * @
	 */
	public Hierarchy getDepartmentTree(User user) ;
	

}
