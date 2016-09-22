package com.bluesky.iplatform.component.profile.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.profile.dao.UserDAO;
import com.bluesky.iplatform.component.profile.mapper.UserMapper;
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "UserDAOImpl")
public class UserDAOImpl  extends BaseSingleMyBatisDAOImpl<User> implements UserDAO<User>{
	
	/**
     * 初始化通用的Mapper
     */
	@PostConstruct 
	public void initMapper(){
    	ApplicationContext ctx = BaseContext.getApplicationContext();
    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
		this.mapper  = (Mapper<User>) sqlSession.getMapper(UserMapper.class);
	}
	
	
	/**
	 * 删除用户（标记删除用户）
	 * @param user  操作者对应的用户，可以传 null
	 * @param model 需要删除的用户
	 */
	@Override
	public void deleteMode(User user,  User model){
		log.debug("delete User instance");
		sqlSession = sqlSessionFactory.openSession();
		try {
			model.setStatus(User.STATUS_DELETED);
			this.mapper.updateByPrimaryKey(model);
			log.debug("delete User successful");
		} catch (RuntimeException re) {
			log.error("delete User failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public User getAdminUser() {
		log.debug("getting " + className + " instance with ids. ");
		try {
			User adminUser = null;
			Object value = new Integer(User.SYS_ADMIN_ID);//系统管理员ID
			adminUser = this.mapper.selectByPrimaryKey(value);
			return adminUser;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	@Override
	public User getUser(String username, int companyID) {
		log.debug("finding all " + className + " instances");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Example example = new Example(entityClass);
			example.createCriteria().andEqualTo("name", username);
			example.createCriteria().andEqualTo("companyID", companyID);
			List<User> modes = mapper.selectByExample(example);
			User mode = null;
			if(modes.size()>0 && modes.size()==1){
				mode = modes.get(0); 
			}
			return mode;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}
}
