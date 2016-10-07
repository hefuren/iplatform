package com.bluesky.iplatform.component.from.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.object.BatchObject;

@Data
@Component(value = "FormFilterItem")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formfilteritem", schema = "public")
public class FormFilterItem extends BatchObject{
	
	/**
	 * 相等比较
	 */
	public static final int FIELD_TYPE_EQUAL = 0;
	
	/**
	 * 枚举字段：单选
	 */
	public static final int FIELD_TYPE_ENUM_SINGLE = 1;
	
	/**
	 * 枚举字段：多选
	 */
	public static final int FIELD_TYPE_ENUM_MULTIPLE = 2;
	
	/**
	 * 时间：相等比较
	 */
	public static final int FIELD_TYPE_DATE_EQUAL = 3;
	
	/**
	 * 时间：区间比较
	 */
	public static final int FIELD_TYPE_DATE_INTERVAL = 4;
	
	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)	
	private Integer id;
	
	@Column(name = "filterid", nullable = false)
	private Integer filterID;
	
	@Column(name = "schemaid", nullable = false)
	private Integer schemaID;
	
	@Column(name = "fieldkey", length = 50)
	private String fieldKey;
	
	@Column(name = "fieldtype")
	private Integer fieldType;
	
	@Column(name = "companyid")
	private Integer companyID;
	
}
