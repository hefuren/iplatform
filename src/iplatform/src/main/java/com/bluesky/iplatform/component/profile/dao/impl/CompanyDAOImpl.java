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
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "CompanyDAOImpl")
public class CompanyDAOImpl extends BaseSingleMyBatisDAOImpl<Company> implements CompanyDAO<Company>{

//	 /**
//     * 初始化通用的Mapper
//     */
//	@PostConstruct 
//	public void initMapper(){
//    	ApplicationContext ctx = BaseContext.getApplicationContext();
//    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
//		this.mapper  = (Mapper<Company>) sqlSession.getMapper(CompanyMapper.class);
//	}
	
	@Override
	public void initMapperType() {
		// TODO Auto-generated method stub
		mapperType = CompanyMapper.class;
	}
	
	/**
	 * 删除公司时，需要删除公司/租户的所有数据
	 */
	@Override
	public void deleteMode(User user, Company company){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateCompany(User user, Company mode) {
		log.debug("Active company begining...");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Mapper<Company> mapper = this.getMapper(sqlSession, mapperType);
			mode.setStatus(Company.STATUS_ACTIVATED);
			mapper.updateByPrimaryKey(mode);
			log.debug("Active company  successful");
		} catch (RuntimeException re) {
			log.error("Active company  failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}	
		
	}

	@Override
	public void expireCompany(User user, Company mode) {
		log.debug("expire company begining...");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Mapper<Company> mapper = this.getMapper(sqlSession, mapperType);
			mode.setStatus(Company.STATUS_UNACTIVATED);
			mapper.updateByPrimaryKey(mode);
			log.debug("expire company successful");
		} catch (RuntimeException re) {
			log.error("expire company failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

}
