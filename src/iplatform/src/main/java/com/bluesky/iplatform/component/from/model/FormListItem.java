package com.bluesky.iplatform.component.from.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component(value = "FormListItem")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formlistitem", schema = "public")
public class FormListItem {
	
	/**
	 * 排序方式：ASC 升序排列
	 */
	public static final int ORDER_TYPE_ASC = 0;
	
	/**
	 * 排序方式：DSEC 升序排列
	 */
	public static final int ORDER_TYPE_DSEC = 0;
	
	/**
	 * 排列位置：居左
	 */
	public static final int ALIGN_LEFT = 0;
	
	/**
	 * 排列位置：居中
	 */
	public static final int ALIGN_MIDDLE = 1;
	
	/**
	 * 排列位置：居右
	 */
	public static final int ALIGN_RIGHT = 2;

	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "schemaid", nullable = false)
	private Integer schemaID;
	
	@Column(name = "listid", nullable = false)
	private Integer listID;
	
	@Column(name = "fieldid", nullable = false)
	private String fieldID;
	
	@Column(name = "width")
	private Integer width;
	
	@Column(name = "align")
	private Integer align;
	
	@Column(name = "ordertype")
	private Integer orderType;
	
	@Column(name = "seqno")
	private Integer seqno;
	
	@Column(name = "companyid")
	private Integer companyID;

}
