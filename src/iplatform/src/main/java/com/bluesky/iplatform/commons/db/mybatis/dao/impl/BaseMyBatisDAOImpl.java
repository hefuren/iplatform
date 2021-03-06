package com.bluesky.iplatform.commons.db.mybatis.dao.impl;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "BaseMyBatisDAOImpl")
public class BaseMyBatisDAOImpl extends SqlSessionDaoSupport{
	
	public static final Logger log = LoggerFactory.getLogger(BaseMyBatisDAOImpl.class);
	
	protected SqlSession sqlSession;  
	
	@Autowired
	 public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
	     super.setSqlSessionTemplate(sqlSessionTemplate);
	 }
	
	/**
	 * 子类可重新该方法，初始化Mapper
	 */
	@PostConstruct
	public void initMapper(){
		
	}
	
	
	


}
