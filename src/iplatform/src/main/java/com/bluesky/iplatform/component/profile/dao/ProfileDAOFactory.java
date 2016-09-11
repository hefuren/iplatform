package com.bluesky.iplatform.component.profile.dao;

import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.commons.utils.BaseContext;

public class ProfileDAOFactory {
	
	public static CompanyDAO getCompanyDAO(){
		ApplicationContext ctx = BaseContext.getApplicationContext();
		CompanyDAO dao = ctx.getBean("CompanyDAOImpl", CompanyDAO.class);
		return dao;
	}
	
	public static UserDAO getUserDAO(){
		ApplicationContext ctx = BaseContext.getApplicationContext();
		UserDAO dao = ctx.getBean("UserDAOImpl", UserDAO.class);
		return dao;
	}
	

	public static UserProfileDAO getUserProfileDAO(){
		ApplicationContext ctx = BaseContext.getApplicationContext();
		UserProfileDAO dao = ctx.getBean("UserProfileDAOImpl", UserProfileDAO.class);
		return dao;
	}
	
	public static RoleDAO getRoleDAO(){
		ApplicationContext ctx = BaseContext.getApplicationContext();
		RoleDAO dao = ctx.getBean("RoleDAOImpl", RoleDAO.class);
		return dao;
	}
	
	public static DepartmentDAO getDepartmentDAO(){
		ApplicationContext ctx = BaseContext.getApplicationContext();
		DepartmentDAO dao = ctx.getBean("DepartmentDAOImpl", DepartmentDAO.class);
		return dao;
	}

}
