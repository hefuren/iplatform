package com.bluesky.iplatform.component.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.system.dao.MenuDAO;
import com.bluesky.iplatform.component.system.model.Menu;

@Service(value = "SystemManagerService")
public class SystemManagerService implements SystemManager {
	
	private MenuDAO menuDAO;

	@Override
	public void newMenu(User user, Menu menu){
		try{
			this.menuDAO.newMode(user, menu);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void updateMenu(User user, Menu menu){
		try{
			this.menuDAO.updateMode(user, menu);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteMenus(User user, int[] ids){
		try{
			this.menuDAO.batchDeleteModes(user, ids);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public Hierarchy getModelMenus(User user, int modelID){
		Hierarchy menus = null;
		try{
			menus = this.menuDAO.getModelMenus(user, modelID);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return menus;
	}
	

	@Override
	public List<Menu> getModels(User user){
		List<Menu> models = null;
		try{
			models = this.menuDAO.getModels(user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return models;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}


	
}
