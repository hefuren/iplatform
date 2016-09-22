package com.bluesky.iplatform.component.profile.dao;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.profile.model.User;

public interface UserDAO<T>  extends BaseSingleMyBatisDAO<T>{
	
	public T getAdminUser(); 
	
	/**
	 *  通过用户名获取
	 * @param username
	 * @param companyID
	 * @return
	 */
	public User getUser(String username, int companyID); 

}
