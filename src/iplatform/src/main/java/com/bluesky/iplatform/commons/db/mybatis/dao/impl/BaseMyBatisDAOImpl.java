package com.bluesky.iplatform.commons.db.mybatis.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "BaseMyBatisDAOImpl")
public class BaseMyBatisDAOImpl extends SqlSessionDaoSupport{
	
	@Resource(name = "sqlSessionFactory")  
	protected SqlSessionFactory sqlSessionFactory;  
	
	@Autowired
	 public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
	     super.setSqlSessionTemplate(sqlSessionTemplate);
	 }
	
	protected SqlSession sqlSession;  
	
	public static final Logger log = LoggerFactory.getLogger(BaseMyBatisDAOImpl.class);


}
