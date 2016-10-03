package com.bluesky.iplatform.commons.db.mybatis.utils;

import tk.mybatis.mapper.common.Mapper;

/**
 * 实现通用批量操作Mapper
 * @author ElwinHe
 *
 * @param <T>
 */
public interface AllMapper<T> extends Mapper<T>, BatchInsertMapper<T> {

}
