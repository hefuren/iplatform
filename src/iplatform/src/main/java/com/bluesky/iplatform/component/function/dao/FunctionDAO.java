package com.bluesky.iplatform.component.function.dao;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.profile.model.User;

public interface FunctionDAO<T> extends BaseSingleMyBatisDAO<T>{
	
	/**
	 * 通过Function Key (Name) 判断权限是否存在
	 * 不允许存在相同key的权限
	 * @param user
	 * @param key
	 * @return
	 */
	public boolean isExistFuncion(User user ,String key);
	

}
