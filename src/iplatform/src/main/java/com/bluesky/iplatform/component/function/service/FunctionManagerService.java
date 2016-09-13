package com.bluesky.iplatform.component.function.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.component.function.dao.FunctionRelationDAO;
import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.function.model.FunctionRelation;
import com.bluesky.iplatform.component.profile.model.User;

@Service(value = "FunctionManagerService")
public class FunctionManagerService implements FunctionManager {
	
	@Resource(name="FunctionRelationDAOImpl")
	private FunctionRelationDAO relationDao;

	@Override
	public void saveFunctionRelations(User user, List<FunctionRelation> modes)
			throws Exception {
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
	public Map<String, Function> getFunctionsByRoleID(User user, int roleID)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
