package com.bluesky.iplatform.component.from.dao.impl;

import org.springframework.stereotype.Repository;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormFilterItemDAO;
import com.bluesky.iplatform.component.from.mapper.FormFilterItemMapper;
import com.bluesky.iplatform.component.from.model.FormFilterItem;

@Repository(value = "FormFilterItemDAOImpl")
public class FormFilterItemDAOImpl extends BaseSingleMyBatisDAOImpl<FormFilterItem>
implements FormFilterItemDAO<FormFilterItem>{

	@Override
	public void initMapperType() {
		this.mapperType = FormFilterItemMapper.class;
	}

}
