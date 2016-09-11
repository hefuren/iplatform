package com.bluesky.iplatform.component.codetable.service;

import java.util.List;

import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.component.codetable.model.CodeTable;
import com.bluesky.iplatform.component.codetable.model.CommonCode;
import com.bluesky.iplatform.component.profile.model.User;

public interface CodeTableManager {
	
	/**
	 * 获取CodeTable对象
	 * @param user
	 * @param codeTableID
	 * @return
	 */
	public CodeTable getCodeTable(User user, int codeTableID);
	
	/**
	 * 获取CodeTable对象
	 * @param user
	 * @param codeTalbeName
	 * @return
	 */
	public CodeTable getCodeTalbe(User user, String codeTalbeName);
	
	/**
	 * 新建系统代表
	 * @param user
	 * @param codeTable
	 */
	public void newCodeTable(User user, CodeTable codeTable);
	
	/**
	 * 更新系统代码表
	 * @param user
	 * @param codeTable
	 */
	public void updateCodeTable(User user, CodeTable codeTable);
	
	/**
	 * 删除系统代码表
	 * @param user
	 * @param codeTable
	 */
	public void deleteCodeTable(User user, CodeTable codeTable);
	
	/**
	 * 批量删除系统代码表
	 * @param user
	 * @param ids
	 */
	public void batchDeleteCodeTables(User user, int[] ids);

	/**
	 * 获取指定CodeTalbe的Codes
	 * @param user
	 * @param codeTable
	 * @return
	 */
	public List<CommonCode> getCommonCodes(User user, String tableName);
	
	/**
	 * 通过PageInfo获取代码表
	 * @param user
	 * @param pageInfo
	 * @return
	 */
	public PageInfo getCodeTables(User user, PageInfo pageInfo);
}
