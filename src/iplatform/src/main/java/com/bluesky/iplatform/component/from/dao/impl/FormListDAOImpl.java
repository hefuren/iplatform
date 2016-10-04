package com.bluesky.iplatform.component.from.dao.impl;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormListDAO;
import com.bluesky.iplatform.component.from.mapper.FormListMapper;
import com.bluesky.iplatform.component.from.model.FormList;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

public class FormListDAOImpl extends BaseSingleMyBatisDAOImpl<FormList>
implements FormListDAO<FormList>{

	@Override
	public void initMapperType() {
		this.mapperType = FormListMapper.class;
	}

	@Override
	public FormList getMode(User user, FormSchema formSchema) {
		// TODO Auto-generated method stub
		return null;
	}

}
