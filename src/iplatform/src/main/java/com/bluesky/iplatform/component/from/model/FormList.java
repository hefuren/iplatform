package com.bluesky.iplatform.component.from.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component(value = "FormList")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formlist", schema = "public")
public class FormList {
	
	/**
	 * 排序方式：ASC 升序排列
	 */
	public static final int ORDER_TYPE_ASC = 0;
	
	/**
	 * 排序方式：DSEC 升序排列
	 */
	public static final int ORDER_TYPE_DSEC = 0;
	
	//Field
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "schemaid", nullable = false)
	private Integer schemaID;
	
	/**
	 * 默认排序的字段名称
	 */
	@Column(name = "orderby", length = 50)
	private String orderBy;
	
	@Column(name = "ordertype")
	private Integer orderType;
	
	@Column(name = "companyid", nullable = false)
	private Integer companyID;
	
	@Transient
	private List<FormListItem> formListItems = new ArrayList<FormListItem>();

}
