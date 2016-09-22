package com.bluesky.iplatform.component.function.shiro.token;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.utils.CipherUtils;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

@Component(value = "SimpleRealm")
public class SimpleRealm extends AuthorizingRealm{

	public SimpleRealm() {
		super();
	}
	

	/**
	 * 认证信息（主要针对用户登录）
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
//		User sysAdmin = manager.getAdminUser();
		
		ShiroToken shiroToken = (ShiroToken) token;
		String username = shiroToken.getUsername();
		String password = shiroToken.getPswd();
	    int companyID = shiroToken.getCompanyID();
//		String host = shiroToken.getHost();
		
		User user = manager.getUser(username,companyID);
		if(user == null){
			throw new AccountException("用户不存在！");
		}else{
			String newPwd = user.getPassword();
			if(!newPwd.equals(CipherUtils.toMD5(password))){
				throw new AccountException("帐号或密码不正确！");
			}else {
				//更新登录时间 last login time
//				user.setLastLoginTime(new Date());
			}
		}
		
		return new SimpleAuthenticationInfo(user,user.getPassword(), getName());
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
//		Long userId = TokenManager.getUserId();
//		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
//		//根据用户ID查询角色（role），放入到Authorization里。
//		Set<String> roles = roleService.findRoleByUserId(userId);
//		info.setRoles(roles);
//		//根据用户ID查询权限（permission），放入到Authorization里。
//		Set<String> permissions = permissionService.findPermissionByUserId(userId);
//		info.setStringPermissions(permissions);
//        return info; 
		return null;
	}
	
	/**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	
	
	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
