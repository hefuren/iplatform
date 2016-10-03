package com.bluesky.iplatform.commons.db.mybatis.dao;

import java.util.List;

import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.component.profile.model.User;

/**
 * 单表操作通用 MyBatis DAO 
 * @author ElwinHe
 *
 * @param <T>
 */
public interface BaseSingleMyBatisDAO<T>  {

	/**
	 * 通过ID获取对象
	 * @param user
	 * @param id
	 * @return
	 */
	public T getMode(User user, int id);
	
	/**
	 * 通过ID数组获取对象
	 * @param user
	 * @param ids
	 * @return
	 */
	public List<T> getModes(User user, int[] ids);
	
	/**
	 * 新增对象
	 * @param user
	 * @param t
	 */
	public void newMode(User user, T t);
	
	/**
	 * 批量保存对象
	 * @param user
	 * @param modes
	 */
	public void batchNewModes(User user, List<T> modes);
	
	/**
	 * 更新对象
	 * @param user
	 * @param t
	 */
	public void updateMode(User user, T t);
	
	/**
	 * 批量更新对象
	 * @param user
	 * @param modes
	 */
	public void batchUpdateModes(User user, List<T> modes);
	
	/**
	 * 新增或修改保存
	 * List<T> 包括了新增、修改和删除的数据，T对象需要集成BatchObject
	 * @param user
	 * @param modes
	 */
	public void saveOrUpdateModes(User user, List<T> modes);
	
	/**
	 * 删除对象
	 * @param user
	 * @param t
	 */
	public void deleteMode(User user,  T t);
	
	/**
	 * 根据ID删除对象
	 * @param user
	 * @param ids
	 */
	public void batchDeleteModes(User user, int[] ids);
	
	/**
	 * 通过用户获取当前公司所有对象
	 * @param user
	 * @return
	 */
	public List<T> getCompanyAllModes(User user);
	
	/**
	 * 根据对象属性获取对象
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public T getModeByProperty(String propertyName, Object value);
	
	/**
	 * 根据对象属性获取对象List
	 * @param propertyName	
	 * @param value
	 * @return
	 */
	public List<T> getModesByProperty(String propertyName, Object value);
	
	/**
	 * 根据对象比较获取对象
	 * @param t
	 * @return
	 */
	public List<T> getModesByExample(T t);
	
	/**
	 * 分页查询
	 * @param user
	 * @param pageInfo 
	 * @return
	 */
	public PageInfo getByPageInfo(User user, PageInfo pageInfo);
}
