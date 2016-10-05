package com.bluesky.iplatform.component.from.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormFilterDAO;
import com.bluesky.iplatform.component.from.dao.FormFilterItemDAO;
import com.bluesky.iplatform.component.from.mapper.FormFilterMapper;
import com.bluesky.iplatform.component.from.model.FormFilter;
import com.bluesky.iplatform.component.from.model.FormFilterItem;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "FormFilterDAOImpl")
public class FormFilterDAOImpl extends BaseSingleMyBatisDAOImpl<FormFilter>
implements FormFilterDAO<FormFilter>{
	
	@Resource(name = "FormFilterItemDAOImpl")
	private FormFilterItemDAO itemDAO;

	@Override
	public void initMapperType() {
		this.mapperType = FormFilterMapper.class;
	}

	@Override
	public FormFilter getMode(User user, int id) {
		log.debug("getting " + className + " instance with id: " + id);
		try {
			Mapper<FormFilter> mapper = this.getMapper(sqlSession, mapperType);
			FormFilter instance = mapper.selectByPrimaryKey(new Integer(id));
			//查找过滤器对应的 item 信息
			List<FormFilterItem> items = itemDAO.getModesByProperty("filterid", new Integer(instance.getId()));
			instance.setFormFilterItems(items);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@Override
	public List<FormFilter> getModes(User user, FormSchema form) {
		log.debug("select " + className + " instance");
		try {
			List<FormFilter> modes = null;
			Example example = new Example(FormFilter.class);
			example.createCriteria().andEqualTo("schemaid", form.getId());
			Mapper<FormFilter> mapper = this.getMapper(sqlSession, mapperType);
			modes = mapper.selectByExample(example);
			return modes;
		} catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
	}

}
