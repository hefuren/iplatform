package com.bluesky.iplatform.component.profile.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.profile.dao.RoleDAO;
import com.bluesky.iplatform.component.profile.mapper.CompanyMapper;
import com.bluesky.iplatform.component.profile.mapper.RoleMapper;
import com.bluesky.iplatform.component.profile.mapper.RoleRelationMapper;
import com.bluesky.iplatform.component.profile.model.Company;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.RoleRelation;
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "RoleDAOImpl")
public class RoleDAOImpl extends BaseSingleMyBatisDAOImpl<Role> implements RoleDAO<Role> {
	
	/**
     * 初始化通用的Mapper
     */
	@PostConstruct 
	public void initMapper(){
    	ApplicationContext ctx = BaseContext.getApplicationContext();
    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
		this.mapper  = (Mapper<Role>) sqlSession.getMapper(RoleMapper.class);
	}
	
	public void assignRoleUser(User user, Role role, List<RoleRelation> modes){
		log.debug("saving " + className + " instance");
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);  
    	try {
    		RoleRelationMapper relationMap = sqlSession.getMapper(RoleRelationMapper.class);
    		relationMap.deleteByRoleID(new Integer(role.getId()));
			for (RoleRelation t : modes) {
				// 设置循环批量插入数据
				relationMap.insert(t);
			}
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

}
