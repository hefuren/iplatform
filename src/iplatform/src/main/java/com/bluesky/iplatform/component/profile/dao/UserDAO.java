package com.bluesky.iplatform.component.profile.dao;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;

public interface UserDAO<T>  extends BaseSingleMyBatisDAO<T>{
	
	public T getAdminUser(); 

}
