package com.bluesky.iplatform.component.function.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseMyBatisDAOImpl;
import com.bluesky.iplatform.component.function.dao.FunctionRelationDAO;
import com.bluesky.iplatform.component.function.mapper.FunctionRelationMapper;
import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.function.model.FunctionRelation;

@Repository(value = "FunctionRelationDAOImpl")
public class FunctionRelationDAOImpl extends BaseMyBatisDAOImpl implements FunctionRelationDAO{
	
	@Autowired
	private FunctionRelationMapper mapper ;

	@Override
	public void deleteModesByFunctionID(int[] ids) {
		log.debug("delete FunctionRelation modes by functionid ");
		sqlSession = sqlSessionFactory.openSession();
		try {
			this.mapper.deleteModesByFunctionID(ids);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

	@Override
	public void deleteModesByRoleID(int roleID) {
		log.debug("delete FunctionRelation modes by roleID ");
		sqlSession = sqlSessionFactory.openSession();
		try {
			this.mapper.deleteModesByRoleID(roleID);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

	@Override
	public void insertModes(List<FunctionRelation> modes) {
		log.debug("insert FunctionRelation modes into table ");
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			this.mapper.insertModes(modes);
		} catch (RuntimeException re) {
			log.error("insert modes to st_functionRelation failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

	@Override
	public FunctionRelation selectOneMode(int id) {
		log.debug("delete FunctionRelation modes by roleID ");
		sqlSession = sqlSessionFactory.openSession();
		try {
			FunctionRelation mode = this.mapper.selectOneMode(id);
			return mode;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public Map<String, Function> selectFunctionByRoleID(int roleID) {
		log.debug("delete FunctionRelation modes by roleID ");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Map<String, Function> dataMap = this.mapper.selectFunctionByRoleID(roleID);
			return dataMap;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public void deleteModes(List<FunctionRelation> modes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateModes(List<FunctionRelation> modes) {
		// TODO Auto-generated method stub
		
	}

	
}
