package com.bluesky.iplatform.component.profile.dao.impl;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.profile.dao.CompanyDAO;
import com.bluesky.iplatform.component.profile.mapper.CompanyMapper;
import com.bluesky.iplatform.component.profile.model.Company;

@Repository(value = "CompanyDAOImpl")
public class CompanyDAOImpl extends BaseSingleMyBatisDAOImpl<Company> implements CompanyDAO<Company>{

	 /**
     * 初始化通用的Mapper
     */
	@PostConstruct 
	public void initMapper(){
    	ApplicationContext ctx = BaseContext.getApplicationContext();
    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
		this.mapper  = (Mapper<Company>) sqlSession.getMapper(CompanyMapper.class);
	}

}
