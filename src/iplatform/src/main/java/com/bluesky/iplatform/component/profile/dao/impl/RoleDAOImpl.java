package com.bluesky.iplatform.component.profile.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.profile.dao.RoleDAO;
import com.bluesky.iplatform.component.profile.mapper.RoleMapper;
import com.bluesky.iplatform.component.profile.mapper.RoleRelationMapper;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.RoleRelation;
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "RoleDAOImpl")
public class RoleDAOImpl extends BaseSingleMyBatisDAOImpl<Role> implements RoleDAO<Role> {
	
	
	@Override
	public void initMapperType() {
		mapperType = RoleMapper.class;
		
	}
	
	public void assignUsers(User user, Role role, List<RoleRelation> modes){
		log.debug("saving " + className + " instance");
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
		}
	}

	@Override
	public void activateRole(User user, Role role) {
		log.debug("updating " + className + " instance");
		try {
			Mapper<Role> mapper = this.getMapper(sqlSession, mapperType);
			role.setStatus(Role.STATUS_ACTIVE);
			mapper.updateByPrimaryKey(role);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		
	}

	@Override
	public void inactivatingRole(User user, Role role) {
		log.debug("updating " + className + " instance");
		try {
			Mapper<Role> mapper = this.getMapper(sqlSession, mapperType);
			role.setStatus(Role.STATUS_INACTIVE);
			mapper.updateByPrimaryKey(role);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}	
	}

	@Override
	public Set<Role> getRoles(User user) {
		log.debug(" get " + className + " by user object begin");
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
		}
	}
}
