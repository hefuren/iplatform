package com.bluesky.iplatform.component.system.service;

import java.util.List;

import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.system.model.Menu;

public interface SystemManager {
	
	/**
	 * 新增菜单
	 * @param user
	 * @param menu
	 * @throws Exception
	 */
	public void newMenu(User user, Menu menu);
	
	/**
	 * 更新菜单
	 * @param user
	 * @param menu
	 * @throws Exception
	 */
	public void updateMenu(User user, Menu menu);

	/**
	 * 批量删除菜单
	 * @param user
	 * @param ids
	 * @throws Exception
	 */
	public void deleteMenus(User user, int[] ids);
	
	/**
	 * 获取模块菜单
	 * @param user
	 * @param modelID
	 * @return
	 * @throws Exception
	 */
	public Hierarchy getModelMenus(User user, int modelID);
	
	/**
	 * 根据用户获取模块
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getModels(User user);
	
}
