package com.bluesky.iplatform.commons.db.mybatis.utils;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;

import com.bluesky.iplatform.commons.db.mybatis.provider.BatchInsertProvider;

public interface BatchInsertMapper<T> {
	
	 /**
     * 批量插入，支持批量插入的数据库可以使用
     *
     * @param recordList
     * @return
     */
    @InsertProvider(type = BatchInsertProvider.class, method = "dynamicSQL")
    int insertList(List<T> modes);

}
