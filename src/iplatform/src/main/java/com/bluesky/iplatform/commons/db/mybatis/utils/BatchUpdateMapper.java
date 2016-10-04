package com.bluesky.iplatform.commons.db.mybatis.utils;

import java.util.List;

import org.apache.ibatis.annotations.UpdateProvider;

import com.bluesky.iplatform.commons.db.mybatis.provider.BatchUpdateProvider;

public interface BatchUpdateMapper<T> {
	
	/**
	 * 批量更新对象方法
	 * @param list
	 * @return
	 */
//	 @UpdateProvider(type = BatchUpdateProvider.class, method = "dynamicSQL")
//	 public int updateList(List<T> list);

}
