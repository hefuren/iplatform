package com.bluesky.iplatform.component.function;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.component.function.shiro.token.ShiroToken;
import com.bluesky.iplatform.component.function.utils.FunctionUtils;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.service.RoleManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class SimpleRealmTest extends BaseUnitlsTest{

	@Test
	public void testSimpleRealm() {
		// 创建SecurityManager工厂，通过ini配置文件创建 SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:com/bluesky/iplatform/component/function/shiro/shiro-realm.ini");
        // 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        // 设置SecurityManager到运行环境中，保持单例模式
        SecurityUtils.setSecurityManager(securityManager);
        // 从SecurityUtils里边创建一个subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备token（令牌）
        // 这里的账号和密码 将来是由用户输入进去
        ShiroToken token = new ShiroToken("guanyu", "123456",1000);
        try {
            // 执行认证提交
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        // 是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过：" + isAuthenticated);
	}
	
	@Test
	public void testCheckRole(){
		String username = "guanyu";
		String password = "123456";
		int companyID = 1000;
		this.login(username, password, companyID);
		
		RoleManager manager = (RoleManager)ComponentFactory.getManager("RoleManager");
		Role role = manager.getRole(systemAdmin, 1000);
		boolean result = FunctionUtils.checkRole(role);
		if (result) {  
			System.out.println("权限校验说明：当前用户具有\" " + role.getName() + "\" 角色");
		} else {  
			System.out.println("权限校验说明：当前用户不具备\" " + role.getName() + "\" 权限");
		}
		
	}
	
	/**
	 * 用户登录方法
	 * @param username
	 * @param password
	 * @param companyID
	 */
	public void login(String username, String password, int companyID){
		// 创建SecurityManager工厂，通过ini配置文件创建 SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:com/bluesky/iplatform/component/function/shiro/shiro-realm.ini");
		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		// 设置SecurityManager到运行环境中，保持单例模式
		SecurityUtils.setSecurityManager(securityManager);
		// 从SecurityUtils里边创建一个subject
		Subject subject = SecurityUtils.getSubject();
		ShiroToken token = new ShiroToken(username, password, companyID);
		try {
			// 执行认证提交
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();
		System.out.println("是否认证通过：" + isAuthenticated);
	}

}
