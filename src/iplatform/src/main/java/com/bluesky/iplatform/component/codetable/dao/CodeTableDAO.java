package com.bluesky.iplatform.component.codetable.dao;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.codetable.model.CodeTable;
import com.bluesky.iplatform.component.codetable.model.CommonCode;
import com.bluesky.iplatform.component.profile.model.User;

public interface CodeTableDAO<T> extends BaseSingleMyBatisDAO<T> {
	
	/**
	 * 获取codeTable 对象
	 * @param user
	 * @param codeTalbeName
	 * @return
	 */
	public CodeTable getCodeTalbe(User user, String codeTalbeName);

	/**
	 * 通过codeTable 表面获取commonCode
	 * @param user
	 * @param tableName
	 * @return
	 */
	public List<CommonCode> getCommonCodes(User user, String tableName);

}
