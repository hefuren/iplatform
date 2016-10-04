package com.bluesky.iplatform.component.from.dao.impl;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormViewDAO;
import com.bluesky.iplatform.component.from.mapper.FormViewMapper;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.model.FormView;
import com.bluesky.iplatform.component.from.model.FormViewItem;
import com.bluesky.iplatform.component.profile.model.User;

public class FormViewDAOImpl extends BaseSingleMyBatisDAOImpl<FormView>
implements FormViewDAO<FormView>{

	@Override
	public void initMapperType() {
		mapperType = FormViewMapper.class;
	}

	@Override
	public List<FormView> getModes(User user, FormSchema form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormViewItem> getFormViewItems(User user, FormView formView) {
		// TODO Auto-generated method stub
		return null;
	}


}
