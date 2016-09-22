package com.bluesky.iplatform.component.function.utils;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * iPlatform基本权限对象
 * 附注：集成WildcardPermission 通配符权限对象
 * @author ElwinHe
 *
 */
@Component(value= "BasePermission")
@Scope(value = "prototype")
public class BasePermission extends WildcardPermission{

	private static final long serialVersionUID = 6124762279093290127L;

	/**
	 * 构造器1
	 */
	public BasePermission(){
		super();
	}
	
	/**
	 * 构造器2
	 * @param permission 权限字符串（不区分大小写）
	 */
	public BasePermission(String permission){
		super(permission);
	}
	
	/**
	 * 构造器3
	 * @param permission 权限字符串
	 * @param caseSensitive 区分大小写
	 */
	public BasePermission(String permission, boolean caseSensitive){
		super(permission,caseSensitive);
	}

}
