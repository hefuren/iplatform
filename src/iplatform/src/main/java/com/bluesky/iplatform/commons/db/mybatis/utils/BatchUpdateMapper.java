package com.bluesky.iplatform.commons.db.mybatis.utils;

import java.util.List;

import org.apache.ibatis.annotations.UpdateProvider;

import com.bluesky.iplatform.commons.db.mybatis.provider.BatchUpdateProvider;

public interface BatchUpdateMapper<T> {
	
	 @UpdateProvider(type = BatchUpdateProvider.class, method = "dynamicSQL")
	 int updateList(List<T> list);

}
