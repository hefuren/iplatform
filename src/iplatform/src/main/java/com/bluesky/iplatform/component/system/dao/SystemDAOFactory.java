package com.bluesky.iplatform.component.system.dao;

import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.commons.utils.BaseContext;

public class SystemDAOFactory {
	
	public static MenuDAO getMenuDAO(){
		ApplicationContext ctx = BaseContext.getApplicationContext();
		MenuDAO dao = ctx.getBean("MenuDAOImpl", MenuDAO.class);
		return dao;
	}

}
