package com.bluesky.iplatform.component.from.dao.impl;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormListItemDAO;
import com.bluesky.iplatform.component.from.mapper.FormListItemMapper;
import com.bluesky.iplatform.component.from.model.FormListItem;

public class FormListItemDAOImpl extends BaseSingleMyBatisDAOImpl<FormListItem>
implements FormListItemDAO<FormListItem>{

	@Override
	public void initMapperType() {
		this.mapperType = FormListItemMapper.class;
	}

}
