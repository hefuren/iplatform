package com.bluesky.iplatform.component.function.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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
	
	private FunctionRelationMapper mapper ;
	
	@Override
	@PostConstruct
	public void initMapper(){
		sqlSession = sqlSessionFactory.openSession();
		mapper = sqlSession.getMapper(FunctionRelationMapper.class);
	}

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
		log.debug("slect FunctionRelation modes by id ");
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
		log.debug("slect FunctionRelation modes by roleID ");
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
		log.debug("delete FunctionRelation modes by modes ");
		sqlSession = sqlSessionFactory.openSession();
		try {
			if(modes != null && modes.size() >0){
				int[] ids = new int[modes.size()];
				int index = 0;
				for(FunctionRelation mode : modes){
					ids[index++] = mode.getId();
				}
				this.mapper.deleteModesByFunctionID(ids);
			}
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

	@Override
	public void updateModes(List<FunctionRelation> modes) {
		log.debug("update FunctionRelation modes by modes ");
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			this.mapper.updateModes(modes);
		} catch (RuntimeException re) {
			log.error("update FunctionRelation modes failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

	
}
