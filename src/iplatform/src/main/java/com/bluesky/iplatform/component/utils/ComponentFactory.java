package com.bluesky.iplatform.component.utils;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.springframework.context.ApplicationContext;
import org.apache.shiro.mgt.SecurityManager;

import com.bluesky.iplatform.commons.utils.BaseContext;

public class ComponentFactory {
	
	/**
	 * 获取各个模块业务操作接口
	 * @param className
	 * 附注：各个模块Manager接口与ManagerService必须在同一个包内，并且遵从统一的命名规则XxxManager,XxxManagerService
	 * @return
	 */
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

	/**
	 * 获得SecurityManager
	 * @param configFile  初始化文件
	 * @return
	 */
	public static SecurityManager getSecurityManager(String configFile) {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		return securityManager;
	}
	
}
