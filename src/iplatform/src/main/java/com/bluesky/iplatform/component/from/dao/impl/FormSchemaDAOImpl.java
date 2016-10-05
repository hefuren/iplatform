package com.bluesky.iplatform.component.from.dao.impl;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormSchemaDAO;
import com.bluesky.iplatform.component.from.mapper.FormSchemaMapper;
import com.bluesky.iplatform.component.from.model.FormSchema;

public class FormSchemaDAOImpl extends BaseSingleMyBatisDAOImpl<FormSchema>
		implements FormSchemaDAO<FormSchema> {

	@Override
	public void initMapperType() {
		mapperType = FormSchemaMapper.class;
	}
	
}
