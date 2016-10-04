package com.bluesky.iplatform.component.from.dao.impl;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormFieldDAO;
import com.bluesky.iplatform.component.from.mapper.FormFieldMapper;
import com.bluesky.iplatform.component.from.model.FormField;

public class FormFieldDAOImpl extends BaseSingleMyBatisDAOImpl<FormField>
implements FormFieldDAO<FormField>{

	@Override
	public void initMapperType() {
		this.mapperType = FormFieldMapper.class;
	}

	

}
