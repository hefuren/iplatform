package com.bluesky.iplatform.component.function.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.function.dao.FunctionDAO;
import com.bluesky.iplatform.component.function.mapper.FunctionMapper;
import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.profile.model.User;

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

	@Override
	public boolean isExistFuncion(User user, String key) {
		log.debug("getting " + className + " instance with ids. ");
		sqlSession = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			Function mode = this.getModeByProperty("name", key);
			if(mode != null){
				result = true;
			}
			return result;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

}
