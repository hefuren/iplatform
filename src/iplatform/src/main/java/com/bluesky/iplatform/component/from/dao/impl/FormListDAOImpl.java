package com.bluesky.iplatform.component.from.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormListDAO;
import com.bluesky.iplatform.component.from.dao.FormListItemDAO;
import com.bluesky.iplatform.component.from.mapper.FormListMapper;
import com.bluesky.iplatform.component.from.model.FormList;
import com.bluesky.iplatform.component.from.model.FormListItem;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

public class FormListDAOImpl extends BaseSingleMyBatisDAOImpl<FormList>
implements FormListDAO<FormList>{
	
	@Resource(name = "FormListItemDAOImpl")
	private FormListItemDAO itemDAO;

	@Override
	public void initMapperType() {
		this.mapperType = FormListMapper.class;
	}

	@Override
	public FormList getMode(User user, FormSchema from) {
		log.debug("select " + className + " instance");
		try {
			FormList mode = getModeByProperty("schemaid", new Integer(from.getId()));
			//查找 formList 对应的 item 信息
			List<FormListItem> items = itemDAO.getModesByProperty("listid", new Integer(mode.getId()));
			mode.setFormListItems(items);
			return mode;
		} catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
	}

}
