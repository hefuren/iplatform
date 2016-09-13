package com.bluesky.iplatform.component.profile.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.component.profile.dao.RoleDAO;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.RoleRelation;
import com.bluesky.iplatform.component.profile.model.User;

@Service(value = "RoleManagerService")
public class RoleManagerService implements RoleManager {

	@Resource(name="RoleDAOImpl")
	private RoleDAO roleDAO;
	
//	@PostConstruct  
//	public void initDAO(){
//		roleDAO = ProfileDAOFactory.getRoleDAO();
//	}


	@Override
	public void newRole(User user, Role role) {
		try {
			this.roleDAO.newMode(user, role);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateRole(User user, Role role) {
		try {
			this.roleDAO.updateMode(user, role);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteRole(User user, Role role) {
		try {
			this.roleDAO.deleteMode(user, role);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<User> getRoleUser(User user, Role role) {
		try {
			return this.roleDAO.getModesByProperty("roleID",
					new Integer(role.getId()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void assignRoleUser(User user, Role role, List<RoleRelation> modes) {
		try {
			this.roleDAO.batchNewModes(user, modes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
