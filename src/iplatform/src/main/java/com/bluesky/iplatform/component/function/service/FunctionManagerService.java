package com.bluesky.iplatform.component.function.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.component.function.dao.FunctionDAO;
import com.bluesky.iplatform.component.function.dao.FunctionRelationDAO;
import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.function.model.FunctionRelation;
import com.bluesky.iplatform.component.profile.model.User;

@Service(value = "FunctionManagerService")
public class FunctionManagerService implements FunctionManager {
	
	@Resource(name="FunctionRelationDAOImpl")
	private FunctionRelationDAO relationDao;
	
	@Resource(name="FunctionDAOImpl")
	private FunctionDAO functionDAO;

	@Override
	public void saveFunctionRelations(User user, List<FunctionRelation> modes) {
		try {
			List<FunctionRelation> newList = new ArrayList<FunctionRelation>();
			List<FunctionRelation> updateList = new ArrayList<FunctionRelation>();
			List<FunctionRelation> delList = new ArrayList<FunctionRelation>();
			for(FunctionRelation mode : modes){
				if(mode.isNew()){
					newList.add(mode);
				}else if(mode.isDeleted()){
					delList.add(mode);
				}else {
					updateList.add(mode);
				}
			}
			this.relationDao.deleteModes(delList);
			this.relationDao.insertModes(newList);
			this.relationDao.updateModes(updateList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public Map<String, Function> getFunctionsByRoleID(User user, int roleID){
		try {
			Map<String, Function> functionMap = this.relationDao.selectFunctionByRoleID(roleID);
			return functionMap;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void newFunction(User user, Function function) {
		try {
			this.functionDAO.newMode(user, function);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateFunction(User user, Function function) {
		try {
			this.functionDAO.updateMode(user, function);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void deleteFunction(User user, Function function) {
		try {
			this.functionDAO.deleteMode(user, function);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Function getFunction(User user, int id) {
		try {
			Function mode = (Function)this.functionDAO.getMode(user, id);
			return mode;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Function> getFunctions(User user, int[] ids) {
		try {
			List<Function> modes = this.functionDAO.getModes(user, ids);
			return modes;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Function getFunction(User user, String key) {
		try {
			Function mode = (Function)this.functionDAO.getModeByProperty("name", key);
			return mode;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isExistFuncion(User user, String key) {
		try {
			return this.functionDAO.isExistFuncion(user, key);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
}
