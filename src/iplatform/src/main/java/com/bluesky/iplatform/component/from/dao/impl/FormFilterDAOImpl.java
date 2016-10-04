package com.bluesky.iplatform.component.from.dao.impl;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormFilterDAO;
import com.bluesky.iplatform.component.from.mapper.FormFilterMapper;
import com.bluesky.iplatform.component.from.model.FormFilter;

public class FormFilterDAOImpl extends BaseSingleMyBatisDAOImpl<FormFilter>
implements FormFilterDAO<FormFilter>{

	@Override
	public void initMapperType() {
		this.mapperType = FormFilterMapper.class;
	}

}
