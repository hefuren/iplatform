package com.bluesky.iplatform.component.profile.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.component.profile.dao.DepartmentDAO;
import com.bluesky.iplatform.component.profile.model.Department;
import com.bluesky.iplatform.component.profile.model.User;

@Service(value = "StructureManagerService")
public class StructureManagerService implements StructureManager {

	@Resource(name="DepartmentDAOImpl")
	private DepartmentDAO departmentDAO;
	
//	@PostConstruct  
//	public void initDAO(){
//		departmentDAO = ProfileDAOFactory.getDepartmentDAO();
//	}


	@Override
	public void saveDepartment(User user, Hierarchy hierarchy) {
		try {
			this.departmentDAO.saveDepartment(user, hierarchy);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void updateDepartment(User user, Department department) {
		try {
			this.departmentDAO.updateMode(user, department);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Department getDepartment(User user, int departmentID) {
		try {
			return (Department) this.departmentDAO.getMode(user, departmentID);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Hierarchy getDepartmentTree(User user) {
		try {
			return this.departmentDAO.getDepartmentTree(user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
