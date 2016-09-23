package com.bluesky.iplatform.component.function.shiro.token;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.utils.CipherUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

@Component(value = "SimpleRealm")
public class SimpleRealm extends AuthorizingRealm {

	public SimpleRealm() {
		super();
	}

	/**
	 * 认证信息（主要针对用户登录）
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
		// User sysAdmin = manager.getAdminUser();

		//token是用户输入的相关信息
		ShiroToken shiroToken = (ShiroToken) token;
		
		//从token中取出身份信息, 即用户的User对象
		String username = shiroToken.getUsername();
		String password = shiroToken.getPswd();
		int companyID = shiroToken.getCompanyID();
		// String host = shiroToken.getHost();

		User user = manager.getUser(username, companyID);
		if (user == null) {
			throw new AccountException("用户不存在！");
		} else {
			String newPwd = user.getPassword();
			if (!newPwd.equals(CipherUtils.toMD5(password))) {
				throw new AccountException("帐号或密码不正确！");
			} else {
				// 更新登录时间 last login time
				// user.setLastLoginTime(new Date());
			}
		}

		// 如果查询到返回认证信息AuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, this.getName());
		
		return simpleAuthenticationInfo;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
		User user = (User)principals.getPrimaryPrincipal();

		 SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		 //根据用户ID查询角色（role），放入到Authorization里
		 Set<Role> roleList = manager.getRoles(user);
		 
		 //Role对象中ID是唯一的，避免名称重复，roles Set 中保存 roleID
		 Set<String> roles = new HashSet<String>();
		 for(Role role : roleList){
			 int roleID = role.getId();
			 roles.add(TypeUtils.nullToString(roleID));
		 }
		 info.setRoles(roles);
		 //根据用户ID查询权限（permission），放入到Authorization里。
		 Set<String> permissions =  new HashSet<String>();
		 info.setStringPermissions(permissions);
		 return info;
	}

	/**
	 * 设置realm的名称
	 */
	@Override
	public void setName(String name) {
		super.setName("SimpleRealm");
	}

	/**
	 * 清空当前用户权限信息
	 */
	public void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject()
				.getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(
			PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
