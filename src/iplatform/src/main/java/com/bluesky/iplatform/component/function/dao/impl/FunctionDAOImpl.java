package com.bluesky.iplatform.component.function.dao.impl;

import org.springframework.stereotype.Repository;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.function.dao.FunctionDAO;
import com.bluesky.iplatform.component.function.mapper.FunctionMapper;
import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "FunctionDAOImpl")
public class FunctionDAOImpl extends BaseSingleMyBatisDAOImpl<Function> implements FunctionDAO<Function>{

	
	@Override
	public void initMapperType() {
		mapperType = FunctionMapper.class;
	}

	@Override
	public boolean isExistFuncion(User user, String key) {
		log.debug("getting " + className + " instance with ids. ");
		boolean result = false;
		try {
			Function mode = this.getModeByProperty("name", key);
			if(mode != null){
				result = true;
			}
			return result;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		
	}

	

}
