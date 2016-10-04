package com.bluesky.iplatform.component.from.dao.impl;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormFilterDAO;
import com.bluesky.iplatform.component.from.mapper.FormFilterMapper;
import com.bluesky.iplatform.component.from.model.FormFilter;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

public class FormFilterDAOImpl extends BaseSingleMyBatisDAOImpl<FormFilter>
implements FormFilterDAO<FormFilter>{

	@Override
	public void initMapperType() {
		this.mapperType = FormFilterMapper.class;
	}

	@Override
	public List<FormFilter> getModes(User user, FormSchema formSchema) {
		// TODO Auto-generated method stub
		return null;
	}

}
