package com.bluesky.iplatform.component.profile.dao.impl;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.profile.dao.UserProfileDAO;
import com.bluesky.iplatform.component.profile.mapper.UserProfileMapper;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.model.UserProfile;

@Repository(value = "UserProfileDAOImpl")
public class UserProfileDAOImpl extends BaseSingleMyBatisDAOImpl<UserProfile> implements UserProfileDAO<UserProfile>{

//	/**
//     * 初始化通用的Mapper
//     */
//	@PostConstruct 
//	public void initMapper(){
//    	ApplicationContext ctx = BaseContext.getApplicationContext();
//    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
//		this.mapper  = (Mapper<UserProfile>) sqlSession.getMapper(UserProfileMapper.class);
//	}
	
	@Override
	public void initMapperType() {
		mapperType = UserProfileMapper.class;
	}

	/**
	 * 删除用户（标记删除用户）
	 * @param user  操作者对应的用户，可以传 null
	 * @param UserProfile 需要删除的用户
	 */
	@Override
	public void deleteMode(User user, UserProfile u)  {
		log.debug("delete " + className + " instance");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Mapper<UserProfile> mapper = this.getMapper(sqlSession, mapperType);
			u.setStatus(UserProfile.STATUS_DELETED);
			mapper.updateByPrimaryKey(u);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

}
