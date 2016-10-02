package com.bluesky.iplatform.component.function.dao.impl;

import org.springframework.stereotype.Repository;



import tk.mybatis.mapper.common.Mapper;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.function.dao.FunctionDAO;
import com.bluesky.iplatform.component.function.mapper.FunctionMapper;
import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "FunctionDAOImpl")
public class FunctionDAOImpl extends BaseSingleMyBatisDAOImpl<Function> implements FunctionDAO<Function>{

//	/**
//     * 初始化通用的Mapper
//     */
//	@PostConstruct 
//	public void initMapper(){
//    	ApplicationContext ctx = BaseContext.getApplicationContext();
//    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
//		this.mapper  = (Mapper<Function>) sqlSession.getMapper(FunctionMapper.class);
//	}
	
	@Override
	public void initMapperType() {
		// TODO Auto-generated method stub
		mapperType = FunctionMapper.class;
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
