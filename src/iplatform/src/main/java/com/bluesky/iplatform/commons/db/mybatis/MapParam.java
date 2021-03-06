package com.bluesky.iplatform.commons.db.mybatis;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Mybatis返回的Map对象
 * @author ElwinHe
 *
 */
@Component(value = "MapParam")
@Scope(value = "prototype")
public class MapParam extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 作为Key的字段对应MapParam的Key
	 */
	public static final String  KEY_FIELD = "mapKeyField";
	/**
	 * 作为Value的字段对应MapParam的Key
	 */
	public static final String VALUE_FIELD = "mapValueField";
	
	public MapParam() {
		
	}
	
	/**
	 * 指定keyField和valueField
	 * @param keyField Map中key对应的字段
	 * @param valueField Map中value对应的字段
	 */
	public MapParam(String keyField, String valueField) {
		this.put(KEY_FIELD, keyField);
		this.put(VALUE_FIELD, valueField);
	}
	
}
