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
@Component(value = "FormFilter")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formfilter", schema = "public")
public class FormFilter {
	
	/**
	 * 类型：默认过滤器
	 */
	public static final int TYPE_DEFAULT = 0;
	
	/**
	 * 类型：用户自定义过滤器
	 */
	public static final int TYPE_CUSTOMIZATION = 1;

	//Field
	@Id
	@Column(name = "id", unique = true, nullable = false)	
	private Integer id;
	
	/**
	 * 过滤器对应的表单ID
	 */
	@Column(name = "schemaid", nullable = false)	
	private Integer schemaID;
	
	
	/**
	 * 过滤器类型：默认、用户定义
	 */
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "name", length = 50)
	private String name;
	
	/**
	 * 过滤器关联ID
	 * （如果为用户自定义，关联userid;如果为默认，关联表单ID）
	 */
	@Column(name = "referenceid")
	private Integer referenceID;
	
	@Column(name = "description", length = 200)
	private String description;
	
	@Column(name = "companyid", nullable = false)	
	private Integer companyID;
	
	@Transient
	private List<FormFilterItem> formFilterItems = new ArrayList<FormFilterItem>();
}
