package com.bluesky.iplatform.component.from.dao.impl;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

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
		log.debug("select " + className + " instance");
		try {
			List<FormField> modes = null;
			Example example = new Example(FormField.class);
			example.createCriteria().andEqualTo("schemaid", form.getId());
			Mapper<FormField> mapper = this.getMapper(sqlSession, mapperType);
			modes = mapper.selectByExample(example);
			return modes;
		} catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
	}

	

}
