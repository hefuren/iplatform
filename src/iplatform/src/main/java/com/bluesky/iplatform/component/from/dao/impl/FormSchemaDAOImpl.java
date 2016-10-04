package com.bluesky.iplatform.component.from.dao.impl;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormSchemaDAO;
import com.bluesky.iplatform.component.from.mapper.FormSchemaMapper;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

public class FormSchemaDAOImpl extends BaseSingleMyBatisDAOImpl<FormSchema>
		implements FormSchemaDAO<FormSchema> {

	@Override
	public void initMapperType() {
		mapperType = FormSchemaMapper.class;
	}
	
	@Override
	public void batchDeleteModes(User user, int[] ids) {
		log.debug("deleting " + className + " instance");
		try {
			
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}		
	}

}
