package com.bluesky.iplatform.component.function.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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
		sqlSession = this.getSqlSession();
		mapper = sqlSession.getMapper(FunctionRelationMapper.class);
	}

	@Override
	public void deleteModesByFunctionID(int[] ids) {
		log.debug("delete FunctionRelation modes by functionid ");
		try {
			this.mapper.deleteModesByFunctionID(ids);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void deleteModesByRoleID(int roleID) {
		log.debug("delete FunctionRelation modes by roleID ");
		try {
			this.mapper.deleteModesByRoleID(roleID);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void insertModes(List<FunctionRelation> modes) {
		log.debug("insert FunctionRelation modes into table ");
		try {
			FunctionRelationMapper relationMapper = sqlSession.getMapper(FunctionRelationMapper.class);
			relationMapper.insertModes(modes);
		} catch (RuntimeException re) {
			log.error("insert modes to st_functionRelation failed", re);
			throw re;
		}
		
	}

	@Override
	public FunctionRelation selectOneMode(int id) {
		log.debug("slect FunctionRelation modes by id ");
		try {
			mapper = sqlSession.getMapper(FunctionRelationMapper.class);
			FunctionRelation mode = this.mapper.selectOneMode(id);
			return mode;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public Map<String, Function> selectFunctionByRoleID(int roleID) {
		log.debug("slect FunctionRelation modes by roleID ");
		try {
			mapper = sqlSession.getMapper(FunctionRelationMapper.class);
			Map<String, Function> dataMap = null;
			List<Function> list = this.mapper.selectFunctionByRoleID(roleID);
			if(list != null && list.size()>0){
				dataMap = new HashMap<String, Function>();
				for(Function item : list){
					dataMap.put(item.getName(), item);
				}
			}
			
			return dataMap;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	@Override
	public Map<String, Function> selectFunctionByRoleID(int[] roleIDs) {
		log.debug("slect FunctionRelation modes by roleID ");
		try {
			mapper = sqlSession.getMapper(FunctionRelationMapper.class);
			Map<String, Function> dataMap = null;
			List<Function> list = this.mapper.selectFunctionByRoleIDArray(roleIDs);
			if(list != null && list.size()>0){
				dataMap = new HashMap<String, Function>();
				for(Function item : list){
					dataMap.put(item.getName(), item);
				}
			}
			return dataMap;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public void deleteModes(List<FunctionRelation> modes) {
		log.debug("delete FunctionRelation modes by modes ");
		try {
			mapper = sqlSession.getMapper(FunctionRelationMapper.class);
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
		}
		
	}

	@Override
	public void updateModes(List<FunctionRelation> modes) {
		log.debug("update FunctionRelation modes by modes ");
		try {
			mapper = sqlSession.getMapper(FunctionRelationMapper.class);
			this.mapper.updateModes(modes);
		} catch (RuntimeException re) {
			log.error("update FunctionRelation modes failed", re);
			throw re;
		}
		
	}
	
}
