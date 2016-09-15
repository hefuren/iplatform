package com.bluesky.iplatform.component.profile.dao;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.profile.model.Company;
import com.bluesky.iplatform.component.profile.model.User;

public interface CompanyDAO<T> extends BaseSingleMyBatisDAO<T>{
	
	/**
	 * 激活公司对象
	 * @param user
	 * @param mode
	 */
	public void  activateCompany(User user, Company mode);
	
	/**
	 * 将公司对象设置为未激活
	 * @param user
	 * @param mode
	 */
	public void expireCompany(User user, Company mode);
	

}
