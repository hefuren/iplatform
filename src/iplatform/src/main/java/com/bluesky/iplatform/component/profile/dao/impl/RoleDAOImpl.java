package com.bluesky.iplatform.component.profile.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.profile.dao.RoleDAO;
import com.bluesky.iplatform.component.profile.mapper.RoleMapper;
import com.bluesky.iplatform.component.profile.mapper.RoleRelationMapper;
import com.bluesky.iplatform.component.profile.model.Department;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.RoleRelation;
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "RoleDAOImpl")
public class RoleDAOImpl extends BaseSingleMyBatisDAOImpl<Role> implements RoleDAO<Role> {
	
//	/**
//     * 初始化通用的Mapper
//     */
//	@PostConstruct 
//	public void initMapper(){
//    	ApplicationContext ctx = BaseContext.getApplicationContext();
//    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
//		this.mapper  = (Mapper<Role>) sqlSession.getMapper(RoleMapper.class);
//	}
	
	@Override
	public void initMapperType() {
		mapperType = RoleMapper.class;
		
	}
	
	public void assignUsers(User user, Role role, List<RoleRelation> modes){
		log.debug("saving " + className + " instance");
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);//用于批量操作
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

	@Override
	public void activateRole(User user, Role role) {
		log.debug("updating " + className + " instance");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Mapper<Role> mapper = this.getMapper(sqlSession, mapperType);
			role.setStatus(Role.STATUS_ACTIVE);
			mapper.updateByPrimaryKey(role);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}	
		
	}

	@Override
	public void inactivatingRole(User user, Role role) {
		log.debug("updating " + className + " instance");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Mapper<Role> mapper = this.getMapper(sqlSession, mapperType);
			role.setStatus(Role.STATUS_INACTIVE);
			mapper.updateByPrimaryKey(role);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}	
		
	}

	@Override
	public Set<Role> getRoles(User user) {
		log.debug(" get " + className + " by user object begin");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Set<Role> roleSet = null;
			RoleRelationMapper relationMap = sqlSession.getMapper(RoleRelationMapper.class);
			List<Role> roleList = relationMap.selectRolesByUser(user);
			if(roleList != null && roleList.size() > 0){
				roleSet = new HashSet<Role>();
				for(Role role : roleList){
					roleSet.add(role);
				}
			}
			return roleSet;
		} catch (RuntimeException re) {
			log.error("get Role by user object failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}

	}

	

}
