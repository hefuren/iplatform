package com.bluesky.iplatform.component.from.dao.impl;

import org.springframework.stereotype.Repository;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormViewItemDAO;
import com.bluesky.iplatform.component.from.mapper.FormViewItemMapper;
import com.bluesky.iplatform.component.from.model.FormViewItem;

@Repository(value = "FormViewItemDAOImpl")
public class FormViewItemDAOImpl extends BaseSingleMyBatisDAOImpl<FormViewItem>
implements FormViewItemDAO<FormViewItem>{

	@Override
	public void initMapperType() {
		this.mapperType = FormViewItemMapper.class;		
	}

}
