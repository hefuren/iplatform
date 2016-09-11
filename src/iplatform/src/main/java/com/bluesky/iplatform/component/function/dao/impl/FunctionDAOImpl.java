package com.bluesky.iplatform.component.function.dao.impl;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.function.dao.FunctionDAO;
import com.bluesky.iplatform.component.function.mapper.FunctionMapper;
import com.bluesky.iplatform.component.function.model.Function;

@Repository(value = "FunctionDAOImpl")
public class FunctionDAOImpl extends BaseSingleMyBatisDAOImpl<Function> implements FunctionDAO<Function>{

	/**
     * 初始化通用的Mapper
     */
	@PostConstruct 
	public void initMapper(){
    	ApplicationContext ctx = BaseContext.getApplicationContext();
    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
		this.mapper  = (Mapper<Function>) sqlSession.getMapper(FunctionMapper.class);
	}
}
