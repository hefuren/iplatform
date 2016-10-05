package com.bluesky.iplatform.component.from.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.from.dao.FormViewDAO;
import com.bluesky.iplatform.component.from.dao.FormViewItemDAO;
import com.bluesky.iplatform.component.from.mapper.FormViewItemMapper;
import com.bluesky.iplatform.component.from.mapper.FormViewMapper;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.model.FormView;
import com.bluesky.iplatform.component.from.model.FormViewItem;
import com.bluesky.iplatform.component.profile.model.User;

public class FormViewDAOImpl extends BaseSingleMyBatisDAOImpl<FormView>
implements FormViewDAO<FormView>{
	
	@Resource(name = "FormViewItemDAOImpl")
	private FormViewItemDAO viewItemDAO;

	@Override
	public void initMapperType() {
		mapperType = FormViewMapper.class;
	}

	@Override
	public FormView getMode(User user, int id) {
		log.debug("getting " + className + " instance with id: " + id);
		try {
			Mapper<FormView> mapper = this.getMapper(sqlSession, mapperType);
			FormView instance = mapper.selectByPrimaryKey(new Integer(id));
			//查找 FormView 对应的 item 信息
			List<FormViewItem> items = this.getFormViewItems(user, instance);
			instance.setFormViewItems(items);
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@Override
	public List<FormView> getModes(User user, FormSchema form) {
		log.debug("select " + className + " instance");
		try {
			List<FormView> modes = null;
			Example example = new Example(FormView.class);
			example.createCriteria().andEqualTo("schemaid", form.getId());
			Mapper<FormView> mapper = this.getMapper(sqlSession, mapperType);
			modes = mapper.selectByExample(example);
			return modes;
		} catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
	}

	@Override
	public List<FormViewItem> getFormViewItems(User user, FormView view) {
		log.debug("select " + className + " instance");
		try {
			List<FormViewItem> modes = null;
			Example example = new Example(FormViewItem.class);
			example.createCriteria().andEqualTo("viewid", view.getId());
			Mapper<FormViewItem> mapper = (Mapper<FormViewItem>) sqlSession.getMapper(FormViewItemMapper.class);
			modes = mapper.selectByExample(example);
			return modes;
		} catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
	}


}
