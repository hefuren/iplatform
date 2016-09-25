package com.bluesky.iplatform.component.function.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.profile.model.Role;

public class FunctionUtils {
	
	/**
	 * 判断当前用户是否具有 role 角色
	 * @param role
	 * @return
	 */
	public static boolean checkRole(Role role){
		boolean result = false;
		Subject currentUser = SecurityUtils.getSubject();
		String roleID = TypeUtils.nullToString(role.getId());
		//判断当前用户是否包含该角色
		result = currentUser.hasRole(roleID);
		return result;
	}
	
	/**
	 * 通过权限Key检查用户是否有权限
	 * 附注：key 为 Function.Name 属性）
	 * @param permissionKey
	 * @return
	 */
	public static boolean checkPermission(String permissionKey){
		boolean result = false;
		Subject currentUser = SecurityUtils.getSubject();
		result = currentUser.isPermitted(permissionKey);
		if (result) {  
			//用户有权限, do what ……
			System.out.println("权限校验说明：当前用户具备 " + permissionKey + " 权限");
		} else {  
			//用户无权限, do what ……
			System.out.println("权限校验说明：当前用户不具备 " + permissionKey + " 权限");
		}
		return result;
	}
	
	/**
	 * 检查用户是否具有权限
	 * @param permission
	 * @return
	 */
	public static boolean checkPermission(BasePermission permission){
		boolean result = false;
		Subject currentUser = SecurityUtils.getSubject();
		result = currentUser.isPermitted(permission);
		if (result) {  
			//用户有权限, do what ……
			System.out.println("权限校验说明：当前用户具备 " + permission.toString() + " 权限");
		} else {  
			//用户无权限, do what ……
			System.out.println("权限校验说明：当前用户不具备 " + permission.toString() + " 权限");
		}
		return result;
	}

}
