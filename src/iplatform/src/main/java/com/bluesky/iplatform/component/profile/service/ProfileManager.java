package com.bluesky.iplatform.component.profile.service;

import java.util.List;
import java.util.Set;

import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.component.profile.model.Company;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.model.UserProfile;

public interface ProfileManager {
	
	/**
	 * 新建公司
	 * @param company
	 * @
	 */
	public void newCompany(Company company) ;
	
	/**
	 * 更新公司
	 * @param company
	 * @
	 */
	public void updateCompany(Company company) ;
	
	/**
	 * 通过ID获取公司
	 * @param companyID
	 * @return
	 * @
	 */
	public Company getCompany(int companyID) ;
	
	/**
	 * 删除公司
	 * (删除公司时，需要删除该公司/租户所有的数据)
	 * @param companyID
	 * @
	 */
	public void deleteCompany(Company company) ;
	
	/**
	 * 通过名称获取公司
	 * @param companyName
	 * @return
	 * @
	 */
	public Company getCompany(String companyName) ;
	
	/**
	 * 激活公司/租户账号
	 * @param user
	 * @param mode
	 */
	public void activateCompany(User user, Company mode) ;
	
	/**
	 * 失效公司/租户账号
	 * @param user
	 * @param mode
	 */
	public void expireCompany(User user, Company mode);
	
	/**
	 * 新建用户
	 * @param user
	 * @
	 */
	public void newUser(User user) ;
	
	/**
	 * 批量新增用户
	 * @param users
	 * @
	 */
	public void batchNewUsers(List<User> users) throws  Exception;
	
	/**
	 * 更新用户
	 * @param user
	 * @
	 */
	public void updateUser(User user) ;
	
	/**
	 * 批量更新用户
	 * @param users
	 * @
	 */
	public void batchUpdateUsers(List<User> users) throws  Exception;
	
	/**
	 * 删除用户(标记删除，将用户status置为-1)
	 * @param user
	 * @
	 */
	public void deleteUser(User user) ;
	
	/**
	 * 删除用户(标记删除，将用户status置为-1)
	 * @param user
	 * @param ids
	 * @
	 */
	public void batchDeleteUsers(User user, int[] ids) ;
	
	/**
	 * 通过ID获取用户
	 * @param userID
	 * @
	 */
	public User getUser(User user, int userID) ;
	
	/**
	 * 从缓存中获取用户
	 * @param companyID
	 * @param userID
	 * @return
	 */
	public User getUserFormCache(int companyID, int userID);
	
	/**
	 *  通过用户名获取
	 * @param username
	 * @param companyID
	 * @return
	 */
	public User getUser(String username, int companyID);
	
	/**
	 * 通过PageInfo获取用户
	 * @param user
	 * @param pageInfo
	 * @return
	 * @
	 */
	public PageInfo getUsers(User user, PageInfo pageInfo) ;
	
	/**
	 * 根据用户查找其角色
	 * @param user 当前用户对象
	 * @return
	 */
	public Set<Role> getRoles(User user);	
	
	/**
	 * 通过UserID获取 UserProfile信息
	 * @param user
	 * @param userID
	 * @
	 */
	public UserProfile getUserProfile(User user, int userID);
	
	/**
	 * 新建 userProfile
	 * @param user
	 * @param userProfile
	 * @
	 */
	public void newUserProfile(User user, UserProfile userProfile) ;
	
	/**
	 * 批量新增 userProfile
	 * @param user
	 * @param modes
	 * @
	 */
	public void batchNewUserProfiles(User user, List<UserProfile> modes) ;
	
	/**
	 * 更新 userProfile
	 * @param user
	 * @param userProfile
	 * @
	 */
	public void updateUserProfile(User user, UserProfile userProfile) ;
	
	/**
	 * 批量修改userProfile
	 * @param user
	 * @param modes
	 * @
	 */
	public void batchUpdateUserProfiles(User user, List<UserProfile> modes) ;
	
	/**
	 * 删除UserProfile(标记删除)
	 * @param user
	 * @param ids
	 * @
	 */
	public void deleteUserProfile(User user, UserProfile userProfile) ;
	
	/**
	 * 获取系统管理员账号
	 * @return
	 */
	public User getAdminUser();
	
}
