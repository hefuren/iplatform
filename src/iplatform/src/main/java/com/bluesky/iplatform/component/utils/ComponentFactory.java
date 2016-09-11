package com.bluesky.iplatform.component.utils;

import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.commons.utils.BaseContext;
//import com.bluesky.iplatform.component.codetable.dao.CodeTableDAO;
//import com.bluesky.iplatform.component.codetable.service.CodeTableManager;
//import com.bluesky.iplatform.component.codetable.service.CodeTableManagerService;
//import com.bluesky.iplatform.component.profile.dao.CompanyDAO;
//import com.bluesky.iplatform.component.profile.dao.DepartmentDAO;
//import com.bluesky.iplatform.component.profile.dao.ProfileDAOFactory;
//import com.bluesky.iplatform.component.profile.dao.RoleDAO;
//import com.bluesky.iplatform.component.profile.dao.UserDAO;
//import com.bluesky.iplatform.component.profile.dao.UserProfileDAO;
//import com.bluesky.iplatform.component.profile.service.ProfileManager;
//import com.bluesky.iplatform.component.profile.service.ProfileManagerService;
//import com.bluesky.iplatform.component.profile.service.RoleManager;
//import com.bluesky.iplatform.component.profile.service.RoleManagerService;
//import com.bluesky.iplatform.component.profile.service.StructureManager;
//import com.bluesky.iplatform.component.profile.service.StructureManagerService;
//import com.bluesky.iplatform.component.schema.service.SchemaManager;
//import com.bluesky.iplatform.component.system.dao.MenuDAO;
//import com.bluesky.iplatform.component.system.dao.SystemDAOFactory;
//import com.bluesky.iplatform.component.system.service.SystemManager;
//import com.bluesky.iplatform.component.system.service.SystemManagerService;

public class ComponentFactory {
	
	public static Object getManager(String className){
		ApplicationContext context = BaseContext.getApplicationContext();
		Object manager = null;
		if(className != null && !"".equals(className)){
			try {
				String managerServiceName = className + "Service";
				manager = context.getBean(managerServiceName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return manager;
		
	}
	
//	public static SchemaManager getSchemaManager(){
//		ApplicationContext context = BaseContext.getApplicationContext();
//		SchemaManager manager = context.getBean("SchemaManagerService", SchemaManager.class);
//		return manager;
//	}
//	
//	public static ProfileManager getProfileManager(){
//		ApplicationContext ctx = BaseContext.getApplicationContext();
//		ProfileManagerService manager = ctx.getBean("ProfileManagerService", ProfileManagerService.class);
//		CompanyDAO companyDAO =  ProfileDAOFactory.getCompanyDAO();
//		UserDAO userDAO = ProfileDAOFactory.getUserDAO();
//		UserProfileDAO userProfileDAO = ProfileDAOFactory.getUserProfileDAO();
//		
//		manager.setCompanyDAO(companyDAO);
//		manager.setUserDAO(userDAO);
//		manager.setUserProfileDAO(userProfileDAO);
//		return manager;
//	}
//	
//	public static SystemManager getSystemManager(){
//		ApplicationContext ctx = BaseContext.getApplicationContext();
//		SystemManagerService manager = ctx.getBean("SystemManagerService", SystemManagerService.class);
//		MenuDAO menuDAO = SystemDAOFactory.getMenuDAO();
//		
//		manager.setMenuDAO(menuDAO);
//		return manager;
//	}
//
//	
//	public static StructureManager getStructureManager(){
//		ApplicationContext ctx = BaseContext.getApplicationContext();
//		StructureManagerService manager = ctx.getBean("StructureManagerService", StructureManagerService.class);
//		DepartmentDAO dao = ProfileDAOFactory.getDepartmentDAO();
//		
//		manager.setDepartmentDAO(dao);
//		return manager;
//	}
//	
//	public static RoleManager getRoleManager(){
//		ApplicationContext ctx = BaseContext.getApplicationContext();
//		RoleManagerService manager = ctx.getBean("RoleManagerService", RoleManagerService.class);
//		RoleDAO dao = ProfileDAOFactory.getRoleDAO();
//		
//		manager.setRoleDAO(dao);
//		return manager;
//	}
//	
//	public static CodeTableManager getCodeTableManager(){
//		ApplicationContext ctx = BaseContext.getApplicationContext();
//		CodeTableManagerService manager = ctx.getBean("CodeTableManagerService", CodeTableManagerService.class);
//		CodeTableDAO dao = ctx.getBean("CodeTableDAOImpl", CodeTableDAO.class);
//		
//		manager.setCodeTableDAO(dao);
//		return manager;
//	}
}
