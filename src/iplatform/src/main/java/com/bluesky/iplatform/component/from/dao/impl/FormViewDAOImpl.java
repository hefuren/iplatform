package com.bluesky.iplatform.component.from.dao.impl;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormViewDAO;
import com.bluesky.iplatform.component.from.mapper.FormViewMapper;
import com.bluesky.iplatform.component.from.model.FormView;

public class FormViewDAOImpl extends BaseSingleMyBatisDAOImpl<FormView>
implements FormViewDAO<FormView>{

	@Override
	public void initMapperType() {
		mapperType = FormViewMapper.class;
		
	}

}
