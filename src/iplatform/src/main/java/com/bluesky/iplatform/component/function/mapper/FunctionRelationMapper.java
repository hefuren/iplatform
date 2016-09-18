package com.bluesky.iplatform.component.function.mapper;
import java.util.List;
import java.util.Map;

import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.function.model.FunctionRelation;

public interface FunctionRelationMapper{
	
	/**
	 * 根据ids删除权限分配关系
	 * @param functionIDs
	 */
	public void deleteModesByFunctionID(int[] ids) ;

	/**
	 * 根据角色ID，删除角色对应的权限分配关系
	 * @param roleID
	 */
	public void deleteModesByRoleID(int roleID) ;

	/**
	 * 新增权限分配关系
	 * @param modes
	 */
	public void insertModes(List<FunctionRelation> modes);
	
	/**
	 * 通过权限分配对象ID，查询Function对象
	 * @param id FunctionRelation 对象ID
	 * @return
	 */
	public FunctionRelation selectOneMode(int id);
	
	/**
	 * 通过RoeID获取该角色的权限集
	 * @param RoleID 角色ID
	 * @return 权限集Map<k,v> k为权限Function Name(Key),v 为Function对象
	 */
	public Map<String,Function> selectFunctionByRoleID(int roleID);
	
	/**
	 * 更新权限分配关系
	 * @param modes
	 */
	public void updateModes(List<FunctionRelation> modes);

}
