package com.bluesky.iplatform.component.from.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.object.BatchObject;

import lombok.Data;

@Data
@Component(value = "FormField")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formfield", schema = "public")
public class FormField  extends BatchObject{
	
	private static final long serialVersionUID = 2035178939612371444L;

	/**
	 * 数据类型：字符串 50
	 */
	public static int DATATYPE_STR50 = 100;

	/**
	 * 数据类型：字符串 100
	 */
	public static int DATATYPE_STR100 = 101;

	/**
	 * 数据类型：字符串 250
	 */
	public static int DATATYPE_STR250 = 102;

	/**
	 * 数据类型：日期
	 */
	public static int DATATYPE_DATE = 103;

	/**
	 * 数据类型：枚举
	 */
	public static int DATATYPE_ENUM = 104;

	/**
	 * 数据类型：文本块
	 */
	public static int DATATYPE_TEXTAREA = 105;

	/**
	 * 数据类型：系统用户
	 */
	public static int DATATYPE_USER = 106;

	/**
	 * 数据类型：附件
	 */
	public static int DATATYPE_FILE = 107;

	/**
	 * 字段类型：表单字段
	 */
	public static int FIELDTYPE_FORM = 100;

	/**
	 * 字段类型：引用字段
	 */
	public static int FIELDTYPE_QUOTE = 101;

	/**
	 * 字段类型：工作流字段
	 */
	public static int FIELDTYPE_WORKFLOW = 103;
	
	// Fields
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "schemaid", nullable = false)
	private Integer schemaID;
	
	/**
	 * 字段标识(Str01,num01等)
	 */
	@Column(name = "fieldid", length = 50)
	private String fieldID;

	/**
	 * 名称
	 */
	@Column(name = "name", length = 50)
	private String name;

	/**
	 * 标签名
	 */
	@Column(name = "lablename", length = 50)
	private String lableName;

	/**
	 * 状态
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 描述
	 */
	@Column(name = "description", length =200)
	private String description;

	/**
	 * 字段提示
	 */
	@Column(name = "tips", length =200)
	private String tips;

	/**
	 * 字段类型
	 */
	@Column(name = "fieldtype")
	private Integer fieldType;

	/**
	 * 数据类型
	 */
	@Column(name = "datatype")
	private Integer dataType;

	/**
	 * 数值精度
	 */
	@Column(name = "numprecision")
	private Integer numPrecision;

	/**
	 * 日期精度
	 */
	@Column(name = "dateprecision")
	private Integer datePrecision;

	/**
	 * 序号
	 */
	@Column(name = "seqno")
	private Integer seqno;

	/**
	 * 默认值
	 */
	@Column(name = "defaultvalue", length =500)
	private String defaultvalue;
	
	/**
	 * 公司ID
	 */
	@Column(name = "companyid", nullable = false)
	private Integer companyID;
	
}
