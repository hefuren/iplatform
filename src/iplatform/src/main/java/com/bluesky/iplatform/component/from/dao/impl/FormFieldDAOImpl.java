package com.bluesky.iplatform.component.from.dao.impl;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormFieldDAO;
import com.bluesky.iplatform.component.from.mapper.FormFieldMapper;
import com.bluesky.iplatform.component.from.model.FormField;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

public class FormFieldDAOImpl extends BaseSingleMyBatisDAOImpl<FormField>
implements FormFieldDAO<FormField>{

	@Override
	public void initMapperType() {
		this.mapperType = FormFieldMapper.class;
	}

	@Override
	public List<FormField> getFormFields(User user, FormSchema form) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
