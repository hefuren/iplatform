package com.bluesky.iplatform.component.profile.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.component.profile.dao.CompanyDAO;
import com.bluesky.iplatform.component.profile.dao.UserDAO;
import com.bluesky.iplatform.component.profile.dao.UserProfileDAO;
import com.bluesky.iplatform.component.profile.model.Company;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.model.UserProfile;

@Service(value = "ProfileManagerService")
public class ProfileManagerService implements ProfileManager {

	@Resource(name="CompanyDAOImpl")
	private CompanyDAO companyDAO;
	
	@Resource(name="UserDAOImpl")
	private UserDAO userDAO;
	
	@Resource(name="UserProfileDAOImpl")
	private UserProfileDAO userProfileDAO;
	
//	@PostConstruct  
//	public void initDAO(){
//		companyDAO = ProfileDAOFactory.getCompanyDAO();
//		userDAO = ProfileDAOFactory.getUserDAO();
//		userProfileDAO = ProfileDAOFactory.getUserProfileDAO();
//	}

	@Override
	public void newCompany(Company company) {
		try {
			this.companyDAO.newMode(null, company);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateCompany(Company company) {
		try {
			this.companyDAO.updateMode(null, company);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Company getCompany(int companyID) {
		Company company = null;
		try {
			company = (Company) companyDAO.getMode(null, companyID);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return company;
	}

	@Override
	public void deleteCompany(Company company) {
		try {
			this.companyDAO.deleteMode(null, company);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Company getCompany(String companyName) {
		try {
			 return (Company) this.companyDAO.getModesByProperty("name", companyName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void newUser(User user) {
		try {
			this.userDAO.newMode(null, user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void batchNewUsers(List<User> users) {
		try {
			this.userDAO.batchNewModes(null, users);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateUser(User user) {
		try {
			this.userDAO.updateMode(null, user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void batchUpdateUsers(List<User> users) {
		try {
			this.userDAO.batchUpdateModes(null, users);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User user) {
		try {
			this.userDAO.deleteMode(null, user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void batchDeleteUsers(User user, int[] ids) {
		try {
			this.userDAO.batchDeleteModes(user, ids);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public User getUser(User user, int userID) {
		try {
			return (User) this.userDAO.getMode(user, userID);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUser(User user, String username) {
		try {
			 User mode = (User)this.userDAO.getModeByProperty("name",
			 username);
			 return mode;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public PageInfo getUsers(User user, PageInfo pageInfo) {
		try {
			 return this.userDAO.getByPageInfo(user, pageInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public UserProfile getUserProfile(User user, int userID) {
		try {
			 return (UserProfile)
			 this.userProfileDAO.getModesByProperty("userID",
			 userID);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void newUserProfile(User user, UserProfile userProfile) {
		try {
			 this.userProfileDAO.newMode(user, userProfile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void batchNewUserProfiles(User user, List<UserProfile> modes) {
		try {
			 this.userProfileDAO.batchNewModes(user, modes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateUserProfile(User user, UserProfile userProfile) {
		try {
			 this.userProfileDAO.updateMode(user, userProfile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void batchUpdateUserProfiles(User user, List<UserProfile> modes) {
		try {
			 this.userProfileDAO.batchUpdateModes(user, modes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteUserProfile(User user, UserProfile userProfile) {
		try {
			 this.userProfileDAO.deleteMode(user, userProfile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public User getUserFormCache(int companyID, int userID) {
		try {

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public User getAdminUser() {
		try {
			 return (User) this.userDAO.getAdminUser();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
